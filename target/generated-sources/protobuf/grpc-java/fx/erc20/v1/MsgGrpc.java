package fx.erc20.v1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Msg defines the erc20 Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.44.1)",
    comments = "Source: fx/erc20/v1/tx.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "fx.erc20.v1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<fx.erc20.v1.Tx.MsgConvertCoin,
      fx.erc20.v1.Tx.MsgConvertCoinResponse> getConvertCoinMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ConvertCoin",
      requestType = fx.erc20.v1.Tx.MsgConvertCoin.class,
      responseType = fx.erc20.v1.Tx.MsgConvertCoinResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.erc20.v1.Tx.MsgConvertCoin,
      fx.erc20.v1.Tx.MsgConvertCoinResponse> getConvertCoinMethod() {
    io.grpc.MethodDescriptor<fx.erc20.v1.Tx.MsgConvertCoin, fx.erc20.v1.Tx.MsgConvertCoinResponse> getConvertCoinMethod;
    if ((getConvertCoinMethod = MsgGrpc.getConvertCoinMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getConvertCoinMethod = MsgGrpc.getConvertCoinMethod) == null) {
          MsgGrpc.getConvertCoinMethod = getConvertCoinMethod =
              io.grpc.MethodDescriptor.<fx.erc20.v1.Tx.MsgConvertCoin, fx.erc20.v1.Tx.MsgConvertCoinResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ConvertCoin"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.erc20.v1.Tx.MsgConvertCoin.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.erc20.v1.Tx.MsgConvertCoinResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("ConvertCoin"))
              .build();
        }
      }
    }
    return getConvertCoinMethod;
  }

  private static volatile io.grpc.MethodDescriptor<fx.erc20.v1.Tx.MsgConvertERC20,
      fx.erc20.v1.Tx.MsgConvertERC20Response> getConvertERC20Method;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ConvertERC20",
      requestType = fx.erc20.v1.Tx.MsgConvertERC20.class,
      responseType = fx.erc20.v1.Tx.MsgConvertERC20Response.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.erc20.v1.Tx.MsgConvertERC20,
      fx.erc20.v1.Tx.MsgConvertERC20Response> getConvertERC20Method() {
    io.grpc.MethodDescriptor<fx.erc20.v1.Tx.MsgConvertERC20, fx.erc20.v1.Tx.MsgConvertERC20Response> getConvertERC20Method;
    if ((getConvertERC20Method = MsgGrpc.getConvertERC20Method) == null) {
      synchronized (MsgGrpc.class) {
        if ((getConvertERC20Method = MsgGrpc.getConvertERC20Method) == null) {
          MsgGrpc.getConvertERC20Method = getConvertERC20Method =
              io.grpc.MethodDescriptor.<fx.erc20.v1.Tx.MsgConvertERC20, fx.erc20.v1.Tx.MsgConvertERC20Response>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ConvertERC20"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.erc20.v1.Tx.MsgConvertERC20.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.erc20.v1.Tx.MsgConvertERC20Response.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("ConvertERC20"))
              .build();
        }
      }
    }
    return getConvertERC20Method;
  }

  private static volatile io.grpc.MethodDescriptor<fx.erc20.v1.Tx.MsgConvertDenom,
      fx.erc20.v1.Tx.MsgConvertDenomResponse> getConvertDenomMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ConvertDenom",
      requestType = fx.erc20.v1.Tx.MsgConvertDenom.class,
      responseType = fx.erc20.v1.Tx.MsgConvertDenomResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.erc20.v1.Tx.MsgConvertDenom,
      fx.erc20.v1.Tx.MsgConvertDenomResponse> getConvertDenomMethod() {
    io.grpc.MethodDescriptor<fx.erc20.v1.Tx.MsgConvertDenom, fx.erc20.v1.Tx.MsgConvertDenomResponse> getConvertDenomMethod;
    if ((getConvertDenomMethod = MsgGrpc.getConvertDenomMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getConvertDenomMethod = MsgGrpc.getConvertDenomMethod) == null) {
          MsgGrpc.getConvertDenomMethod = getConvertDenomMethod =
              io.grpc.MethodDescriptor.<fx.erc20.v1.Tx.MsgConvertDenom, fx.erc20.v1.Tx.MsgConvertDenomResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ConvertDenom"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.erc20.v1.Tx.MsgConvertDenom.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.erc20.v1.Tx.MsgConvertDenomResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("ConvertDenom"))
              .build();
        }
      }
    }
    return getConvertDenomMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static MsgStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MsgStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MsgStub>() {
        @java.lang.Override
        public MsgStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MsgStub(channel, callOptions);
        }
      };
    return MsgStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static MsgBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MsgBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MsgBlockingStub>() {
        @java.lang.Override
        public MsgBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MsgBlockingStub(channel, callOptions);
        }
      };
    return MsgBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static MsgFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MsgFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MsgFutureStub>() {
        @java.lang.Override
        public MsgFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MsgFutureStub(channel, callOptions);
        }
      };
    return MsgFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * Msg defines the erc20 Msg service.
   * </pre>
   */
  public static abstract class MsgImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * ConvertCoin mints a ERC20 representation of the SDK Coin denom that is
     * registered on the token mapping.
     * </pre>
     */
    public void convertCoin(fx.erc20.v1.Tx.MsgConvertCoin request,
        io.grpc.stub.StreamObserver<fx.erc20.v1.Tx.MsgConvertCoinResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getConvertCoinMethod(), responseObserver);
    }

    /**
     * <pre>
     * ConvertERC20 mints a Cosmos coin representation of the ERC20 token contract
     * that is registered on the token mapping.
     * </pre>
     */
    public void convertERC20(fx.erc20.v1.Tx.MsgConvertERC20 request,
        io.grpc.stub.StreamObserver<fx.erc20.v1.Tx.MsgConvertERC20Response> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getConvertERC20Method(), responseObserver);
    }

    /**
     * <pre>
     * ConvertDenom convert denom to other denom
     * </pre>
     */
    public void convertDenom(fx.erc20.v1.Tx.MsgConvertDenom request,
        io.grpc.stub.StreamObserver<fx.erc20.v1.Tx.MsgConvertDenomResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getConvertDenomMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getConvertCoinMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.erc20.v1.Tx.MsgConvertCoin,
                fx.erc20.v1.Tx.MsgConvertCoinResponse>(
                  this, METHODID_CONVERT_COIN)))
          .addMethod(
            getConvertERC20Method(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.erc20.v1.Tx.MsgConvertERC20,
                fx.erc20.v1.Tx.MsgConvertERC20Response>(
                  this, METHODID_CONVERT_ERC20)))
          .addMethod(
            getConvertDenomMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.erc20.v1.Tx.MsgConvertDenom,
                fx.erc20.v1.Tx.MsgConvertDenomResponse>(
                  this, METHODID_CONVERT_DENOM)))
          .build();
    }
  }

  /**
   * <pre>
   * Msg defines the erc20 Msg service.
   * </pre>
   */
  public static final class MsgStub extends io.grpc.stub.AbstractAsyncStub<MsgStub> {
    private MsgStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MsgStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MsgStub(channel, callOptions);
    }

    /**
     * <pre>
     * ConvertCoin mints a ERC20 representation of the SDK Coin denom that is
     * registered on the token mapping.
     * </pre>
     */
    public void convertCoin(fx.erc20.v1.Tx.MsgConvertCoin request,
        io.grpc.stub.StreamObserver<fx.erc20.v1.Tx.MsgConvertCoinResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getConvertCoinMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ConvertERC20 mints a Cosmos coin representation of the ERC20 token contract
     * that is registered on the token mapping.
     * </pre>
     */
    public void convertERC20(fx.erc20.v1.Tx.MsgConvertERC20 request,
        io.grpc.stub.StreamObserver<fx.erc20.v1.Tx.MsgConvertERC20Response> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getConvertERC20Method(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ConvertDenom convert denom to other denom
     * </pre>
     */
    public void convertDenom(fx.erc20.v1.Tx.MsgConvertDenom request,
        io.grpc.stub.StreamObserver<fx.erc20.v1.Tx.MsgConvertDenomResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getConvertDenomMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Msg defines the erc20 Msg service.
   * </pre>
   */
  public static final class MsgBlockingStub extends io.grpc.stub.AbstractBlockingStub<MsgBlockingStub> {
    private MsgBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MsgBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MsgBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * ConvertCoin mints a ERC20 representation of the SDK Coin denom that is
     * registered on the token mapping.
     * </pre>
     */
    public fx.erc20.v1.Tx.MsgConvertCoinResponse convertCoin(fx.erc20.v1.Tx.MsgConvertCoin request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getConvertCoinMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ConvertERC20 mints a Cosmos coin representation of the ERC20 token contract
     * that is registered on the token mapping.
     * </pre>
     */
    public fx.erc20.v1.Tx.MsgConvertERC20Response convertERC20(fx.erc20.v1.Tx.MsgConvertERC20 request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getConvertERC20Method(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ConvertDenom convert denom to other denom
     * </pre>
     */
    public fx.erc20.v1.Tx.MsgConvertDenomResponse convertDenom(fx.erc20.v1.Tx.MsgConvertDenom request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getConvertDenomMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Msg defines the erc20 Msg service.
   * </pre>
   */
  public static final class MsgFutureStub extends io.grpc.stub.AbstractFutureStub<MsgFutureStub> {
    private MsgFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MsgFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MsgFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * ConvertCoin mints a ERC20 representation of the SDK Coin denom that is
     * registered on the token mapping.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<fx.erc20.v1.Tx.MsgConvertCoinResponse> convertCoin(
        fx.erc20.v1.Tx.MsgConvertCoin request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getConvertCoinMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ConvertERC20 mints a Cosmos coin representation of the ERC20 token contract
     * that is registered on the token mapping.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<fx.erc20.v1.Tx.MsgConvertERC20Response> convertERC20(
        fx.erc20.v1.Tx.MsgConvertERC20 request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getConvertERC20Method(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ConvertDenom convert denom to other denom
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<fx.erc20.v1.Tx.MsgConvertDenomResponse> convertDenom(
        fx.erc20.v1.Tx.MsgConvertDenom request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getConvertDenomMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CONVERT_COIN = 0;
  private static final int METHODID_CONVERT_ERC20 = 1;
  private static final int METHODID_CONVERT_DENOM = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final MsgImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(MsgImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CONVERT_COIN:
          serviceImpl.convertCoin((fx.erc20.v1.Tx.MsgConvertCoin) request,
              (io.grpc.stub.StreamObserver<fx.erc20.v1.Tx.MsgConvertCoinResponse>) responseObserver);
          break;
        case METHODID_CONVERT_ERC20:
          serviceImpl.convertERC20((fx.erc20.v1.Tx.MsgConvertERC20) request,
              (io.grpc.stub.StreamObserver<fx.erc20.v1.Tx.MsgConvertERC20Response>) responseObserver);
          break;
        case METHODID_CONVERT_DENOM:
          serviceImpl.convertDenom((fx.erc20.v1.Tx.MsgConvertDenom) request,
              (io.grpc.stub.StreamObserver<fx.erc20.v1.Tx.MsgConvertDenomResponse>) responseObserver);
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

  private static abstract class MsgBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MsgBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return fx.erc20.v1.Tx.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Msg");
    }
  }

  private static final class MsgFileDescriptorSupplier
      extends MsgBaseDescriptorSupplier {
    MsgFileDescriptorSupplier() {}
  }

  private static final class MsgMethodDescriptorSupplier
      extends MsgBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    MsgMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (MsgGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new MsgFileDescriptorSupplier())
              .addMethod(getConvertCoinMethod())
              .addMethod(getConvertERC20Method())
              .addMethod(getConvertDenomMethod())
              .build();
        }
      }
    }
    return result;
  }
}
