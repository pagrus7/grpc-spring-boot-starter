package org.lognet.springboot.grpc.demo;

import io.grpc.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collections;

public class GrantRoleInterceptor implements ServerInterceptor {
    private final String role;

    public GrantRoleInterceptor(String role) {
        this.role = role;
    }

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> call, Metadata headers, ServerCallHandler<ReqT, RespT> next) {
        final Authentication authentication = new UsernamePasswordAuthenticationToken("user", "user",
                Collections.singletonList(new SimpleGrantedAuthority(role)));

        ServerCall.Listener<ReqT> listener = next.startCall(call, headers);
        ForwardingServerCallListener.SimpleForwardingServerCallListener authEnabledListener = new ForwardingServerCallListener.SimpleForwardingServerCallListener(listener) {
            @Override
            public void onHalfClose() {
                SecurityContextHolder.getContext().setAuthentication(authentication);
                try {
                    super.onHalfClose();
                } finally {
                    SecurityContextHolder.getContext().setAuthentication(null);
                }
            }

        };

        return authEnabledListener;
    }
}
