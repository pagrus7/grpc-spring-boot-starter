package org.lognet.springboot.grpc.demo;

import io.grpc.*;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;

public class AuthExceptionTranslator implements ServerInterceptor {
    public static final String DEFAULT_DESCRIPTION =  "Authentication/Authorization failed";

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> call, Metadata headers, ServerCallHandler<ReqT, RespT> next) {

        ServerCall.Listener<ReqT> listener = null;
        try {
            // streaming calls may error out in startCall
            listener = next.startCall(call, headers);
        } catch (AuthenticationException | AccessDeniedException aex) {
            closeCallAndRethrow(call, aex);
        }

        ForwardingServerCallListener.SimpleForwardingServerCallListener exceptionTranslationListener = new ForwardingServerCallListener.SimpleForwardingServerCallListener<ReqT>(listener) {
            @Override
            public void onHalfClose() {
                try {
                    // unary calls may error out here
                    super.onHalfClose();
                } catch (AuthenticationException | AccessDeniedException aex) {
                    closeCallAndRethrow(call, aex);
                }
            }
        };

        return exceptionTranslationListener;
    }

    private <ReqT, RespT> void closeCallAndRethrow(ServerCall<ReqT, RespT> call, RuntimeException aex) {
        call.close(Status.PERMISSION_DENIED.withCause(aex).withDescription(DEFAULT_DESCRIPTION), new Metadata());
        throw aex;
    }
}
