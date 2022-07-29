package fx.ibc.applications.transfer.v1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Query provides defines the gRPC querier service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.44.1)",
    comments = "Source: fx/ibc/applications/transfer/v1/query.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "fx.ibc.applications.transfer.v1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<fx.ibc.applications.transfer.v1.QueryOuterClass.QueryDenomTraceRequest,
      fx.ibc.applications.transfer.v1.QueryOuterClass.QueryDenomTraceResponse> getDenomTraceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DenomTrace",
      requestType = fx.ibc.applications.transfer.v1.QueryOuterClass.QueryDenomTraceRequest.class,
      responseType = fx.ibc.applications.transfer.v1.QueryOuterClass.QueryDenomTraceResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.ibc.applications.transfer.v1.QueryOuterClass.QueryDenomTraceRequest,
      fx.ibc.applications.transfer.v1.QueryOuterClass.QueryDenomTraceResponse> getDenomTraceMethod() {
    io.grpc.MethodDescriptor<fx.ibc.applications.transfer.v1.QueryOuterClass.QueryDenomTraceRequest, fx.ibc.applications.transfer.v1.QueryOuterClass.QueryDenomTraceResponse> getDenomTraceMethod;
    if ((getDenomTraceMethod = QueryGrpc.getDenomTraceMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getDenomTraceMethod = QueryGrpc.getDenomTraceMethod) == null) {
          QueryGrpc.getDenomTraceMethod = getDenomTraceMethod =
              io.grpc.MethodDescriptor.<fx.ibc.applications.transfer.v1.QueryOuterClass.QueryDenomTraceRequest, fx.ibc.applications.transfer.v1.QueryOuterClass.QueryDenomTraceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DenomTrace"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.ibc.applications.transfer.v1.QueryOuterClass.QueryDenomTraceRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.ibc.applications.transfer.v1.QueryOuterClass.QueryDenomTraceResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("DenomTrace"))
              .build();
        }
      }
    }
    return getDenomTraceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<fx.ibc.applications.transfer.v1.QueryOuterClass.QueryDenomTracesRequest,
      fx.ibc.applications.transfer.v1.QueryOuterClass.QueryDenomTracesResponse> getDenomTracesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DenomTraces",
      requestType = fx.ibc.applications.transfer.v1.QueryOuterClass.QueryDenomTracesRequest.class,
      responseType = fx.ibc.applications.transfer.v1.QueryOuterClass.QueryDenomTracesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.ibc.applications.transfer.v1.QueryOuterClass.QueryDenomTracesRequest,
      fx.ibc.applications.transfer.v1.QueryOuterClass.QueryDenomTracesResponse> getDenomTracesMethod() {
    io.grpc.MethodDescriptor<fx.ibc.applications.transfer.v1.QueryOuterClass.QueryDenomTracesRequest, fx.ibc.applications.transfer.v1.QueryOuterClass.QueryDenomTracesResponse> getDenomTracesMethod;
    if ((getDenomTracesMethod = QueryGrpc.getDenomTracesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getDenomTracesMethod = QueryGrpc.getDenomTracesMethod) == null) {
          QueryGrpc.getDenomTracesMethod = getDenomTracesMethod =
              io.grpc.MethodDescriptor.<fx.ibc.applications.transfer.v1.QueryOuterClass.QueryDenomTracesRequest, fx.ibc.applications.transfer.v1.QueryOuterClass.QueryDenomTracesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DenomTraces"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.ibc.applications.transfer.v1.QueryOuterClass.QueryDenomTracesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.ibc.applications.transfer.v1.QueryOuterClass.QueryDenomTracesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("DenomTraces"))
              .build();
        }
      }
    }
    return getDenomTracesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<fx.ibc.applications.transfer.v1.QueryOuterClass.QueryParamsRequest,
      fx.ibc.applications.transfer.v1.QueryOuterClass.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = fx.ibc.applications.transfer.v1.QueryOuterClass.QueryParamsRequest.class,
      responseType = fx.ibc.applications.transfer.v1.QueryOuterClass.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.ibc.applications.transfer.v1.QueryOuterClass.QueryParamsRequest,
      fx.ibc.applications.transfer.v1.QueryOuterClass.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<fx.ibc.applications.transfer.v1.QueryOuterClass.QueryParamsRequest, fx.ibc.applications.transfer.v1.QueryOuterClass.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<fx.ibc.applications.transfer.v1.QueryOuterClass.QueryParamsRequest, fx.ibc.applications.transfer.v1.QueryOuterClass.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.ibc.applications.transfer.v1.QueryOuterClass.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.ibc.applications.transfer.v1.QueryOuterClass.QueryParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Params"))
              .build();
        }
      }
    }
    return getParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<fx.ibc.applications.transfer.v1.QueryOuterClass.QueryDenomHashRequest,
      fx.ibc.applications.transfer.v1.QueryOuterClass.QueryDenomHashResponse> getDenomHashMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DenomHash",
      requestType = fx.ibc.applications.transfer.v1.QueryOuterClass.QueryDenomHashRequest.class,
      responseType = fx.ibc.applications.transfer.v1.QueryOuterClass.QueryDenomHashResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.ibc.applications.transfer.v1.QueryOuterClass.QueryDenomHashRequest,
      fx.ibc.applications.transfer.v1.QueryOuterClass.QueryDenomHashResponse> getDenomHashMethod() {
    io.grpc.MethodDescriptor<fx.ibc.applications.transfer.v1.QueryOuterClass.QueryDenomHashRequest, fx.ibc.applications.transfer.v1.QueryOuterClass.QueryDenomHashResponse> getDenomHashMethod;
    if ((getDenomHashMethod = QueryGrpc.getDenomHashMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getDenomHashMethod = QueryGrpc.getDenomHashMethod) == null) {
          QueryGrpc.getDenomHashMethod = getDenomHashMethod =
              io.grpc.MethodDescriptor.<fx.ibc.applications.transfer.v1.QueryOuterClass.QueryDenomHashRequest, fx.ibc.applications.transfer.v1.QueryOuterClass.QueryDenomHashResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DenomHash"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.ibc.applications.transfer.v1.QueryOuterClass.QueryDenomHashRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.ibc.applications.transfer.v1.QueryOuterClass.QueryDenomHashResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("DenomHash"))
              .build();
        }
      }
    }
    return getDenomHashMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static QueryStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<QueryStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<QueryStub>() {
        @java.lang.Override
        public QueryStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new QueryStub(channel, callOptions);
        }
      };
    return QueryStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static QueryBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<QueryBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<QueryBlockingStub>() {
        @java.lang.Override
        public QueryBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new QueryBlockingStub(channel, callOptions);
        }
      };
    return QueryBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static QueryFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<QueryFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<QueryFutureStub>() {
        @java.lang.Override
        public QueryFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new QueryFutureStub(channel, callOptions);
        }
      };
    return QueryFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * Query provides defines the gRPC querier service.
   * </pre>
   */
  public static abstract class QueryImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * DenomTrace queries a denomination trace information.
     * </pre>
     */
    public void denomTrace(fx.ibc.applications.transfer.v1.QueryOuterClass.QueryDenomTraceRequest request,
        io.grpc.stub.StreamObserver<fx.ibc.applications.transfer.v1.QueryOuterClass.QueryDenomTraceResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDenomTraceMethod(), responseObserver);
    }

    /**
     * <pre>
     * DenomTraces queries all denomination traces.
     * </pre>
     */
    public void denomTraces(fx.ibc.applications.transfer.v1.QueryOuterClass.QueryDenomTracesRequest request,
        io.grpc.stub.StreamObserver<fx.ibc.applications.transfer.v1.QueryOuterClass.QueryDenomTracesResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDenomTracesMethod(), responseObserver);
    }

    /**
     * <pre>
     * Params queries all parameters of the ibc-transfer module.
     * </pre>
     */
    public void params(fx.ibc.applications.transfer.v1.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<fx.ibc.applications.transfer.v1.QueryOuterClass.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }

    /**
     * <pre>
     * DenomHash queries a denomination hash information.
     * </pre>
     */
    public void denomHash(fx.ibc.applications.transfer.v1.QueryOuterClass.QueryDenomHashRequest request,
        io.grpc.stub.StreamObserver<fx.ibc.applications.transfer.v1.QueryOuterClass.QueryDenomHashResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDenomHashMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getDenomTraceMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.ibc.applications.transfer.v1.QueryOuterClass.QueryDenomTraceRequest,
                fx.ibc.applications.transfer.v1.QueryOuterClass.QueryDenomTraceResponse>(
                  this, METHODID_DENOM_TRACE)))
          .addMethod(
            getDenomTracesMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.ibc.applications.transfer.v1.QueryOuterClass.QueryDenomTracesRequest,
                fx.ibc.applications.transfer.v1.QueryOuterClass.QueryDenomTracesResponse>(
                  this, METHODID_DENOM_TRACES)))
          .addMethod(
            getParamsMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.ibc.applications.transfer.v1.QueryOuterClass.QueryParamsRequest,
                fx.ibc.applications.transfer.v1.QueryOuterClass.QueryParamsResponse>(
                  this, METHODID_PARAMS)))
          .addMethod(
            getDenomHashMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.ibc.applications.transfer.v1.QueryOuterClass.QueryDenomHashRequest,
                fx.ibc.applications.transfer.v1.QueryOuterClass.QueryDenomHashResponse>(
                  this, METHODID_DENOM_HASH)))
          .build();
    }
  }

  /**
   * <pre>
   * Query provides defines the gRPC querier service.
   * </pre>
   */
  public static final class QueryStub extends io.grpc.stub.AbstractAsyncStub<QueryStub> {
    private QueryStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected QueryStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new QueryStub(channel, callOptions);
    }

    /**
     * <pre>
     * DenomTrace queries a denomination trace information.
     * </pre>
     */
    public void denomTrace(fx.ibc.applications.transfer.v1.QueryOuterClass.QueryDenomTraceRequest request,
        io.grpc.stub.StreamObserver<fx.ibc.applications.transfer.v1.QueryOuterClass.QueryDenomTraceResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDenomTraceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * DenomTraces queries all denomination traces.
     * </pre>
     */
    public void denomTraces(fx.ibc.applications.transfer.v1.QueryOuterClass.QueryDenomTracesRequest request,
        io.grpc.stub.StreamObserver<fx.ibc.applications.transfer.v1.QueryOuterClass.QueryDenomTracesResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDenomTracesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Params queries all parameters of the ibc-transfer module.
     * </pre>
     */
    public void params(fx.ibc.applications.transfer.v1.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<fx.ibc.applications.transfer.v1.QueryOuterClass.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * DenomHash queries a denomination hash information.
     * </pre>
     */
    public void denomHash(fx.ibc.applications.transfer.v1.QueryOuterClass.QueryDenomHashRequest request,
        io.grpc.stub.StreamObserver<fx.ibc.applications.transfer.v1.QueryOuterClass.QueryDenomHashResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDenomHashMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Query provides defines the gRPC querier service.
   * </pre>
   */
  public static final class QueryBlockingStub extends io.grpc.stub.AbstractBlockingStub<QueryBlockingStub> {
    private QueryBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected QueryBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new QueryBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * DenomTrace queries a denomination trace information.
     * </pre>
     */
    public fx.ibc.applications.transfer.v1.QueryOuterClass.QueryDenomTraceResponse denomTrace(fx.ibc.applications.transfer.v1.QueryOuterClass.QueryDenomTraceRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDenomTraceMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * DenomTraces queries all denomination traces.
     * </pre>
     */
    public fx.ibc.applications.transfer.v1.QueryOuterClass.QueryDenomTracesResponse denomTraces(fx.ibc.applications.transfer.v1.QueryOuterClass.QueryDenomTracesRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDenomTracesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Params queries all parameters of the ibc-transfer module.
     * </pre>
     */
    public fx.ibc.applications.transfer.v1.QueryOuterClass.QueryParamsResponse params(fx.ibc.applications.transfer.v1.QueryOuterClass.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * DenomHash queries a denomination hash information.
     * </pre>
     */
    public fx.ibc.applications.transfer.v1.QueryOuterClass.QueryDenomHashResponse denomHash(fx.ibc.applications.transfer.v1.QueryOuterClass.QueryDenomHashRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDenomHashMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Query provides defines the gRPC querier service.
   * </pre>
   */
  public static final class QueryFutureStub extends io.grpc.stub.AbstractFutureStub<QueryFutureStub> {
    private QueryFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected QueryFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new QueryFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * DenomTrace queries a denomination trace information.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<fx.ibc.applications.transfer.v1.QueryOuterClass.QueryDenomTraceResponse> denomTrace(
        fx.ibc.applications.transfer.v1.QueryOuterClass.QueryDenomTraceRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDenomTraceMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * DenomTraces queries all denomination traces.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<fx.ibc.applications.transfer.v1.QueryOuterClass.QueryDenomTracesResponse> denomTraces(
        fx.ibc.applications.transfer.v1.QueryOuterClass.QueryDenomTracesRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDenomTracesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Params queries all parameters of the ibc-transfer module.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<fx.ibc.applications.transfer.v1.QueryOuterClass.QueryParamsResponse> params(
        fx.ibc.applications.transfer.v1.QueryOuterClass.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * DenomHash queries a denomination hash information.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<fx.ibc.applications.transfer.v1.QueryOuterClass.QueryDenomHashResponse> denomHash(
        fx.ibc.applications.transfer.v1.QueryOuterClass.QueryDenomHashRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDenomHashMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_DENOM_TRACE = 0;
  private static final int METHODID_DENOM_TRACES = 1;
  private static final int METHODID_PARAMS = 2;
  private static final int METHODID_DENOM_HASH = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final QueryImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(QueryImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_DENOM_TRACE:
          serviceImpl.denomTrace((fx.ibc.applications.transfer.v1.QueryOuterClass.QueryDenomTraceRequest) request,
              (io.grpc.stub.StreamObserver<fx.ibc.applications.transfer.v1.QueryOuterClass.QueryDenomTraceResponse>) responseObserver);
          break;
        case METHODID_DENOM_TRACES:
          serviceImpl.denomTraces((fx.ibc.applications.transfer.v1.QueryOuterClass.QueryDenomTracesRequest) request,
              (io.grpc.stub.StreamObserver<fx.ibc.applications.transfer.v1.QueryOuterClass.QueryDenomTracesResponse>) responseObserver);
          break;
        case METHODID_PARAMS:
          serviceImpl.params((fx.ibc.applications.transfer.v1.QueryOuterClass.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<fx.ibc.applications.transfer.v1.QueryOuterClass.QueryParamsResponse>) responseObserver);
          break;
        case METHODID_DENOM_HASH:
          serviceImpl.denomHash((fx.ibc.applications.transfer.v1.QueryOuterClass.QueryDenomHashRequest) request,
              (io.grpc.stub.StreamObserver<fx.ibc.applications.transfer.v1.QueryOuterClass.QueryDenomHashResponse>) responseObserver);
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

  private static abstract class QueryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    QueryBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return fx.ibc.applications.transfer.v1.QueryOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Query");
    }
  }

  private static final class QueryFileDescriptorSupplier
      extends QueryBaseDescriptorSupplier {
    QueryFileDescriptorSupplier() {}
  }

  private static final class QueryMethodDescriptorSupplier
      extends QueryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    QueryMethodDescriptorSupplier(String methodName) {
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
      synchronized (QueryGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new QueryFileDescriptorSupplier())
              .addMethod(getDenomTraceMethod())
              .addMethod(getDenomTracesMethod())
              .addMethod(getParamsMethod())
              .addMethod(getDenomHashMethod())
              .build();
        }
      }
    }
    return result;
  }
}
