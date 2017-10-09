package io.grpc.examples.translator;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.6.1)",
    comments = "Source: translator.proto")
public final class TranslatorGrpc {

  private TranslatorGrpc() {}

  public static final String SERVICE_NAME = "Translator";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<io.grpc.examples.translator.TranslatorOuterClass.AddTranslationRequest,
      io.grpc.examples.translator.TranslatorOuterClass.KnownTranslationsMessage> METHOD_ADD_TRANSLATION =
      io.grpc.MethodDescriptor.<io.grpc.examples.translator.TranslatorOuterClass.AddTranslationRequest, io.grpc.examples.translator.TranslatorOuterClass.KnownTranslationsMessage>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "Translator", "addTranslation"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              io.grpc.examples.translator.TranslatorOuterClass.AddTranslationRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              io.grpc.examples.translator.TranslatorOuterClass.KnownTranslationsMessage.getDefaultInstance()))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<io.grpc.examples.translator.TranslatorOuterClass.GetTranslationRequest,
      io.grpc.examples.translator.TranslatorOuterClass.KnownTranslationsMessage> METHOD_GET_TRANSLATIONS =
      io.grpc.MethodDescriptor.<io.grpc.examples.translator.TranslatorOuterClass.GetTranslationRequest, io.grpc.examples.translator.TranslatorOuterClass.KnownTranslationsMessage>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "Translator", "getTranslations"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              io.grpc.examples.translator.TranslatorOuterClass.GetTranslationRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              io.grpc.examples.translator.TranslatorOuterClass.KnownTranslationsMessage.getDefaultInstance()))
          .build();

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static TranslatorStub newStub(io.grpc.Channel channel) {
    return new TranslatorStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static TranslatorBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new TranslatorBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static TranslatorFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new TranslatorFutureStub(channel);
  }

  /**
   */
  public static abstract class TranslatorImplBase implements io.grpc.BindableService {

    /**
     */
    public void addTranslation(io.grpc.examples.translator.TranslatorOuterClass.AddTranslationRequest request,
        io.grpc.stub.StreamObserver<io.grpc.examples.translator.TranslatorOuterClass.KnownTranslationsMessage> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_ADD_TRANSLATION, responseObserver);
    }

    /**
     */
    public void getTranslations(io.grpc.examples.translator.TranslatorOuterClass.GetTranslationRequest request,
        io.grpc.stub.StreamObserver<io.grpc.examples.translator.TranslatorOuterClass.KnownTranslationsMessage> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_TRANSLATIONS, responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_ADD_TRANSLATION,
            asyncUnaryCall(
              new MethodHandlers<
                io.grpc.examples.translator.TranslatorOuterClass.AddTranslationRequest,
                io.grpc.examples.translator.TranslatorOuterClass.KnownTranslationsMessage>(
                  this, METHODID_ADD_TRANSLATION)))
          .addMethod(
            METHOD_GET_TRANSLATIONS,
            asyncUnaryCall(
              new MethodHandlers<
                io.grpc.examples.translator.TranslatorOuterClass.GetTranslationRequest,
                io.grpc.examples.translator.TranslatorOuterClass.KnownTranslationsMessage>(
                  this, METHODID_GET_TRANSLATIONS)))
          .build();
    }
  }

  /**
   */
  public static final class TranslatorStub extends io.grpc.stub.AbstractStub<TranslatorStub> {
    private TranslatorStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TranslatorStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TranslatorStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TranslatorStub(channel, callOptions);
    }

    /**
     */
    public void addTranslation(io.grpc.examples.translator.TranslatorOuterClass.AddTranslationRequest request,
        io.grpc.stub.StreamObserver<io.grpc.examples.translator.TranslatorOuterClass.KnownTranslationsMessage> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_ADD_TRANSLATION, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getTranslations(io.grpc.examples.translator.TranslatorOuterClass.GetTranslationRequest request,
        io.grpc.stub.StreamObserver<io.grpc.examples.translator.TranslatorOuterClass.KnownTranslationsMessage> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_TRANSLATIONS, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class TranslatorBlockingStub extends io.grpc.stub.AbstractStub<TranslatorBlockingStub> {
    private TranslatorBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TranslatorBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TranslatorBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TranslatorBlockingStub(channel, callOptions);
    }

    /**
     */
    public io.grpc.examples.translator.TranslatorOuterClass.KnownTranslationsMessage addTranslation(io.grpc.examples.translator.TranslatorOuterClass.AddTranslationRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_ADD_TRANSLATION, getCallOptions(), request);
    }

    /**
     */
    public io.grpc.examples.translator.TranslatorOuterClass.KnownTranslationsMessage getTranslations(io.grpc.examples.translator.TranslatorOuterClass.GetTranslationRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_TRANSLATIONS, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class TranslatorFutureStub extends io.grpc.stub.AbstractStub<TranslatorFutureStub> {
    private TranslatorFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TranslatorFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TranslatorFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TranslatorFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<io.grpc.examples.translator.TranslatorOuterClass.KnownTranslationsMessage> addTranslation(
        io.grpc.examples.translator.TranslatorOuterClass.AddTranslationRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_ADD_TRANSLATION, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<io.grpc.examples.translator.TranslatorOuterClass.KnownTranslationsMessage> getTranslations(
        io.grpc.examples.translator.TranslatorOuterClass.GetTranslationRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_TRANSLATIONS, getCallOptions()), request);
    }
  }

  private static final int METHODID_ADD_TRANSLATION = 0;
  private static final int METHODID_GET_TRANSLATIONS = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final TranslatorImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(TranslatorImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_ADD_TRANSLATION:
          serviceImpl.addTranslation((io.grpc.examples.translator.TranslatorOuterClass.AddTranslationRequest) request,
              (io.grpc.stub.StreamObserver<io.grpc.examples.translator.TranslatorOuterClass.KnownTranslationsMessage>) responseObserver);
          break;
        case METHODID_GET_TRANSLATIONS:
          serviceImpl.getTranslations((io.grpc.examples.translator.TranslatorOuterClass.GetTranslationRequest) request,
              (io.grpc.stub.StreamObserver<io.grpc.examples.translator.TranslatorOuterClass.KnownTranslationsMessage>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static final class TranslatorDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return io.grpc.examples.translator.TranslatorOuterClass.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (TranslatorGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new TranslatorDescriptorSupplier())
              .addMethod(METHOD_ADD_TRANSLATION)
              .addMethod(METHOD_GET_TRANSLATIONS)
              .build();
        }
      }
    }
    return result;
  }
}
