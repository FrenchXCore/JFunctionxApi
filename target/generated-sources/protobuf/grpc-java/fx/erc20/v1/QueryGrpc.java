package fx.erc20.v1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Query defines the gRPC querier service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.44.1)",
    comments = "Source: fx/erc20/v1/query.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "fx.erc20.v1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<fx.erc20.v1.QueryOuterClass.QueryTokenPairsRequest,
      fx.erc20.v1.QueryOuterClass.QueryTokenPairsResponse> getTokenPairsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "TokenPairs",
      requestType = fx.erc20.v1.QueryOuterClass.QueryTokenPairsRequest.class,
      responseType = fx.erc20.v1.QueryOuterClass.QueryTokenPairsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.erc20.v1.QueryOuterClass.QueryTokenPairsRequest,
      fx.erc20.v1.QueryOuterClass.QueryTokenPairsResponse> getTokenPairsMethod() {
    io.grpc.MethodDescriptor<fx.erc20.v1.QueryOuterClass.QueryTokenPairsRequest, fx.erc20.v1.QueryOuterClass.QueryTokenPairsResponse> getTokenPairsMethod;
    if ((getTokenPairsMethod = QueryGrpc.getTokenPairsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getTokenPairsMethod = QueryGrpc.getTokenPairsMethod) == null) {
          QueryGrpc.getTokenPairsMethod = getTokenPairsMethod =
              io.grpc.MethodDescriptor.<fx.erc20.v1.QueryOuterClass.QueryTokenPairsRequest, fx.erc20.v1.QueryOuterClass.QueryTokenPairsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "TokenPairs"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.erc20.v1.QueryOuterClass.QueryTokenPairsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.erc20.v1.QueryOuterClass.QueryTokenPairsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("TokenPairs"))
              .build();
        }
      }
    }
    return getTokenPairsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<fx.erc20.v1.QueryOuterClass.QueryTokenPairRequest,
      fx.erc20.v1.QueryOuterClass.QueryTokenPairResponse> getTokenPairMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "TokenPair",
      requestType = fx.erc20.v1.QueryOuterClass.QueryTokenPairRequest.class,
      responseType = fx.erc20.v1.QueryOuterClass.QueryTokenPairResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.erc20.v1.QueryOuterClass.QueryTokenPairRequest,
      fx.erc20.v1.QueryOuterClass.QueryTokenPairResponse> getTokenPairMethod() {
    io.grpc.MethodDescriptor<fx.erc20.v1.QueryOuterClass.QueryTokenPairRequest, fx.erc20.v1.QueryOuterClass.QueryTokenPairResponse> getTokenPairMethod;
    if ((getTokenPairMethod = QueryGrpc.getTokenPairMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getTokenPairMethod = QueryGrpc.getTokenPairMethod) == null) {
          QueryGrpc.getTokenPairMethod = getTokenPairMethod =
              io.grpc.MethodDescriptor.<fx.erc20.v1.QueryOuterClass.QueryTokenPairRequest, fx.erc20.v1.QueryOuterClass.QueryTokenPairResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "TokenPair"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.erc20.v1.QueryOuterClass.QueryTokenPairRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.erc20.v1.QueryOuterClass.QueryTokenPairResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("TokenPair"))
              .build();
        }
      }
    }
    return getTokenPairMethod;
  }

  private static volatile io.grpc.MethodDescriptor<fx.erc20.v1.QueryOuterClass.QueryParamsRequest,
      fx.erc20.v1.QueryOuterClass.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = fx.erc20.v1.QueryOuterClass.QueryParamsRequest.class,
      responseType = fx.erc20.v1.QueryOuterClass.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.erc20.v1.QueryOuterClass.QueryParamsRequest,
      fx.erc20.v1.QueryOuterClass.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<fx.erc20.v1.QueryOuterClass.QueryParamsRequest, fx.erc20.v1.QueryOuterClass.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<fx.erc20.v1.QueryOuterClass.QueryParamsRequest, fx.erc20.v1.QueryOuterClass.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.erc20.v1.QueryOuterClass.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.erc20.v1.QueryOuterClass.QueryParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Params"))
              .build();
        }
      }
    }
    return getParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<fx.erc20.v1.QueryOuterClass.QueryDenomAliasesRequest,
      fx.erc20.v1.QueryOuterClass.QueryDenomAliasesResponse> getDenomAliasesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DenomAliases",
      requestType = fx.erc20.v1.QueryOuterClass.QueryDenomAliasesRequest.class,
      responseType = fx.erc20.v1.QueryOuterClass.QueryDenomAliasesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.erc20.v1.QueryOuterClass.QueryDenomAliasesRequest,
      fx.erc20.v1.QueryOuterClass.QueryDenomAliasesResponse> getDenomAliasesMethod() {
    io.grpc.MethodDescriptor<fx.erc20.v1.QueryOuterClass.QueryDenomAliasesRequest, fx.erc20.v1.QueryOuterClass.QueryDenomAliasesResponse> getDenomAliasesMethod;
    if ((getDenomAliasesMethod = QueryGrpc.getDenomAliasesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getDenomAliasesMethod = QueryGrpc.getDenomAliasesMethod) == null) {
          QueryGrpc.getDenomAliasesMethod = getDenomAliasesMethod =
              io.grpc.MethodDescriptor.<fx.erc20.v1.QueryOuterClass.QueryDenomAliasesRequest, fx.erc20.v1.QueryOuterClass.QueryDenomAliasesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DenomAliases"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.erc20.v1.QueryOuterClass.QueryDenomAliasesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.erc20.v1.QueryOuterClass.QueryDenomAliasesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("DenomAliases"))
              .build();
        }
      }
    }
    return getDenomAliasesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<fx.erc20.v1.QueryOuterClass.QueryAliasDenomRequest,
      fx.erc20.v1.QueryOuterClass.QueryAliasDenomResponse> getAliasDenomMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AliasDenom",
      requestType = fx.erc20.v1.QueryOuterClass.QueryAliasDenomRequest.class,
      responseType = fx.erc20.v1.QueryOuterClass.QueryAliasDenomResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.erc20.v1.QueryOuterClass.QueryAliasDenomRequest,
      fx.erc20.v1.QueryOuterClass.QueryAliasDenomResponse> getAliasDenomMethod() {
    io.grpc.MethodDescriptor<fx.erc20.v1.QueryOuterClass.QueryAliasDenomRequest, fx.erc20.v1.QueryOuterClass.QueryAliasDenomResponse> getAliasDenomMethod;
    if ((getAliasDenomMethod = QueryGrpc.getAliasDenomMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAliasDenomMethod = QueryGrpc.getAliasDenomMethod) == null) {
          QueryGrpc.getAliasDenomMethod = getAliasDenomMethod =
              io.grpc.MethodDescriptor.<fx.erc20.v1.QueryOuterClass.QueryAliasDenomRequest, fx.erc20.v1.QueryOuterClass.QueryAliasDenomResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AliasDenom"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.erc20.v1.QueryOuterClass.QueryAliasDenomRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.erc20.v1.QueryOuterClass.QueryAliasDenomResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("AliasDenom"))
              .build();
        }
      }
    }
    return getAliasDenomMethod;
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
   * Query defines the gRPC querier service.
   * </pre>
   */
  public static abstract class QueryImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Retrieves registered token pairs
     * </pre>
     */
    public void tokenPairs(fx.erc20.v1.QueryOuterClass.QueryTokenPairsRequest request,
        io.grpc.stub.StreamObserver<fx.erc20.v1.QueryOuterClass.QueryTokenPairsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getTokenPairsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Retrieves a registered token pair
     * </pre>
     */
    public void tokenPair(fx.erc20.v1.QueryOuterClass.QueryTokenPairRequest request,
        io.grpc.stub.StreamObserver<fx.erc20.v1.QueryOuterClass.QueryTokenPairResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getTokenPairMethod(), responseObserver);
    }

    /**
     * <pre>
     * Params retrieves the erc20 module params
     * </pre>
     */
    public void params(fx.erc20.v1.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<fx.erc20.v1.QueryOuterClass.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Retrieves registered denom aliases
     * </pre>
     */
    public void denomAliases(fx.erc20.v1.QueryOuterClass.QueryDenomAliasesRequest request,
        io.grpc.stub.StreamObserver<fx.erc20.v1.QueryOuterClass.QueryDenomAliasesResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDenomAliasesMethod(), responseObserver);
    }

    /**
     * <pre>
     * Retrieves registered alias denom
     * </pre>
     */
    public void aliasDenom(fx.erc20.v1.QueryOuterClass.QueryAliasDenomRequest request,
        io.grpc.stub.StreamObserver<fx.erc20.v1.QueryOuterClass.QueryAliasDenomResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAliasDenomMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getTokenPairsMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.erc20.v1.QueryOuterClass.QueryTokenPairsRequest,
                fx.erc20.v1.QueryOuterClass.QueryTokenPairsResponse>(
                  this, METHODID_TOKEN_PAIRS)))
          .addMethod(
            getTokenPairMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.erc20.v1.QueryOuterClass.QueryTokenPairRequest,
                fx.erc20.v1.QueryOuterClass.QueryTokenPairResponse>(
                  this, METHODID_TOKEN_PAIR)))
          .addMethod(
            getParamsMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.erc20.v1.QueryOuterClass.QueryParamsRequest,
                fx.erc20.v1.QueryOuterClass.QueryParamsResponse>(
                  this, METHODID_PARAMS)))
          .addMethod(
            getDenomAliasesMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.erc20.v1.QueryOuterClass.QueryDenomAliasesRequest,
                fx.erc20.v1.QueryOuterClass.QueryDenomAliasesResponse>(
                  this, METHODID_DENOM_ALIASES)))
          .addMethod(
            getAliasDenomMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.erc20.v1.QueryOuterClass.QueryAliasDenomRequest,
                fx.erc20.v1.QueryOuterClass.QueryAliasDenomResponse>(
                  this, METHODID_ALIAS_DENOM)))
          .build();
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service.
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
     * Retrieves registered token pairs
     * </pre>
     */
    public void tokenPairs(fx.erc20.v1.QueryOuterClass.QueryTokenPairsRequest request,
        io.grpc.stub.StreamObserver<fx.erc20.v1.QueryOuterClass.QueryTokenPairsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getTokenPairsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Retrieves a registered token pair
     * </pre>
     */
    public void tokenPair(fx.erc20.v1.QueryOuterClass.QueryTokenPairRequest request,
        io.grpc.stub.StreamObserver<fx.erc20.v1.QueryOuterClass.QueryTokenPairResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getTokenPairMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Params retrieves the erc20 module params
     * </pre>
     */
    public void params(fx.erc20.v1.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<fx.erc20.v1.QueryOuterClass.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Retrieves registered denom aliases
     * </pre>
     */
    public void denomAliases(fx.erc20.v1.QueryOuterClass.QueryDenomAliasesRequest request,
        io.grpc.stub.StreamObserver<fx.erc20.v1.QueryOuterClass.QueryDenomAliasesResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDenomAliasesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Retrieves registered alias denom
     * </pre>
     */
    public void aliasDenom(fx.erc20.v1.QueryOuterClass.QueryAliasDenomRequest request,
        io.grpc.stub.StreamObserver<fx.erc20.v1.QueryOuterClass.QueryAliasDenomResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAliasDenomMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service.
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
     * Retrieves registered token pairs
     * </pre>
     */
    public fx.erc20.v1.QueryOuterClass.QueryTokenPairsResponse tokenPairs(fx.erc20.v1.QueryOuterClass.QueryTokenPairsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getTokenPairsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Retrieves a registered token pair
     * </pre>
     */
    public fx.erc20.v1.QueryOuterClass.QueryTokenPairResponse tokenPair(fx.erc20.v1.QueryOuterClass.QueryTokenPairRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getTokenPairMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Params retrieves the erc20 module params
     * </pre>
     */
    public fx.erc20.v1.QueryOuterClass.QueryParamsResponse params(fx.erc20.v1.QueryOuterClass.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Retrieves registered denom aliases
     * </pre>
     */
    public fx.erc20.v1.QueryOuterClass.QueryDenomAliasesResponse denomAliases(fx.erc20.v1.QueryOuterClass.QueryDenomAliasesRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDenomAliasesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Retrieves registered alias denom
     * </pre>
     */
    public fx.erc20.v1.QueryOuterClass.QueryAliasDenomResponse aliasDenom(fx.erc20.v1.QueryOuterClass.QueryAliasDenomRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAliasDenomMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service.
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
     * Retrieves registered token pairs
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<fx.erc20.v1.QueryOuterClass.QueryTokenPairsResponse> tokenPairs(
        fx.erc20.v1.QueryOuterClass.QueryTokenPairsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getTokenPairsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Retrieves a registered token pair
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<fx.erc20.v1.QueryOuterClass.QueryTokenPairResponse> tokenPair(
        fx.erc20.v1.QueryOuterClass.QueryTokenPairRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getTokenPairMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Params retrieves the erc20 module params
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<fx.erc20.v1.QueryOuterClass.QueryParamsResponse> params(
        fx.erc20.v1.QueryOuterClass.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Retrieves registered denom aliases
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<fx.erc20.v1.QueryOuterClass.QueryDenomAliasesResponse> denomAliases(
        fx.erc20.v1.QueryOuterClass.QueryDenomAliasesRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDenomAliasesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Retrieves registered alias denom
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<fx.erc20.v1.QueryOuterClass.QueryAliasDenomResponse> aliasDenom(
        fx.erc20.v1.QueryOuterClass.QueryAliasDenomRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAliasDenomMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_TOKEN_PAIRS = 0;
  private static final int METHODID_TOKEN_PAIR = 1;
  private static final int METHODID_PARAMS = 2;
  private static final int METHODID_DENOM_ALIASES = 3;
  private static final int METHODID_ALIAS_DENOM = 4;

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
        case METHODID_TOKEN_PAIRS:
          serviceImpl.tokenPairs((fx.erc20.v1.QueryOuterClass.QueryTokenPairsRequest) request,
              (io.grpc.stub.StreamObserver<fx.erc20.v1.QueryOuterClass.QueryTokenPairsResponse>) responseObserver);
          break;
        case METHODID_TOKEN_PAIR:
          serviceImpl.tokenPair((fx.erc20.v1.QueryOuterClass.QueryTokenPairRequest) request,
              (io.grpc.stub.StreamObserver<fx.erc20.v1.QueryOuterClass.QueryTokenPairResponse>) responseObserver);
          break;
        case METHODID_PARAMS:
          serviceImpl.params((fx.erc20.v1.QueryOuterClass.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<fx.erc20.v1.QueryOuterClass.QueryParamsResponse>) responseObserver);
          break;
        case METHODID_DENOM_ALIASES:
          serviceImpl.denomAliases((fx.erc20.v1.QueryOuterClass.QueryDenomAliasesRequest) request,
              (io.grpc.stub.StreamObserver<fx.erc20.v1.QueryOuterClass.QueryDenomAliasesResponse>) responseObserver);
          break;
        case METHODID_ALIAS_DENOM:
          serviceImpl.aliasDenom((fx.erc20.v1.QueryOuterClass.QueryAliasDenomRequest) request,
              (io.grpc.stub.StreamObserver<fx.erc20.v1.QueryOuterClass.QueryAliasDenomResponse>) responseObserver);
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
      return fx.erc20.v1.QueryOuterClass.getDescriptor();
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
              .addMethod(getTokenPairsMethod())
              .addMethod(getTokenPairMethod())
              .addMethod(getParamsMethod())
              .addMethod(getDenomAliasesMethod())
              .addMethod(getAliasDenomMethod())
              .build();
        }
      }
    }
    return result;
  }
}
