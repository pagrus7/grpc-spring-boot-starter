package org.lognet.springboot.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.examples.GreeterGrpc;
import io.grpc.examples.GreeterOuterClass;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.lognet.springboot.grpc.demo.AuthExceptionTranslator;
import org.lognet.springboot.grpc.demo.DemoApp;
import org.lognet.springboot.grpc.demo.GrantRoleInterceptor;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DemoApp.class, GreeterWithAuthTest.Config.class},
        webEnvironment = SpringBootTest.WebEnvironment.NONE,
        properties = {"grpc.port=8081",
                "spring.aop.proxy-target-class=true"
        })
public class GreeterWithAuthTest {
    private ManagedChannel channel;
    private GreeterGrpc.GreeterBlockingStub client;
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setupChannel() {
        channel = ManagedChannelBuilder.forAddress("localhost", 8081)
                .usePlaintext(true).build();
        client = GreeterGrpc.newBlockingStub(channel);
    }

    @After
    public void shutdownChannel() {
        channel.shutdownNow();
    }

    @Test
    public void securityExceptionPropagatesToClient() {
        // given
        exception.expect(StatusRuntimeException.class);
        exception.expect(hasProperty("status", hasProperty("code", is(Status.Code.PERMISSION_DENIED))));
        exception.expectMessage(AuthExceptionTranslator.DEFAULT_DESCRIPTION);

        // when
        GreeterOuterClass.HelloRequest request = GreeterOuterClass.HelloRequest.newBuilder().setName("world").build();
        GreeterOuterClass.HelloReply helloReply = client.sayHello(request);

        // then expected exception occurs
    }

    @Configuration
    @EnableGlobalMethodSecurity(securedEnabled = true)
    static class Config {
        @Bean
        @GRpcGlobalInterceptor
        public AuthExceptionTranslator authExceptionTranslator() {
            return new AuthExceptionTranslator();
        }

        @Bean
        @GRpcGlobalInterceptor
        public GrantRoleInterceptor grantRole() {
            return new GrantRoleInterceptor("NON_ADMIN");
        }

    }
}
