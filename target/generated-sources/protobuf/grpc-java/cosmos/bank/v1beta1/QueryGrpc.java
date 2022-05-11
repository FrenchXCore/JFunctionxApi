package cosmos.bank.v1beta1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Query defines the gRPC querier service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.44.1)",
    comments = "Source: cosmos/bank/v1beta1/query.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "cosmos.bank.v1beta1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<cosmos.bank.v1beta1.QueryBalanceRequest,
      cosmos.bank.v1beta1.QueryBalanceResponse> getBalanceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Balance",
      requestType = cosmos.bank.v1beta1.QueryBalanceRequest.class,
      responseType = cosmos.bank.v1beta1.QueryBalanceResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<cosmos.bank.v1beta1.QueryBalanceRequest,
      cosmos.bank.v1beta1.QueryBalanceResponse> getBalanceMethod() {
    io.grpc.MethodDescriptor<cosmos.bank.v1beta1.QueryBalanceRequest, cosmos.bank.v1beta1.QueryBalanceResponse> getBalanceMethod;
    if ((getBalanceMethod = QueryGrpc.getBalanceMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getBalanceMethod = QueryGrpc.getBalanceMethod) == null) {
          QueryGrpc.getBalanceMethod = getBalanceMethod =
              io.grpc.MethodDescriptor.<cosmos.bank.v1beta1.QueryBalanceRequest, cosmos.bank.v1beta1.QueryBalanceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Balance"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.bank.v1beta1.QueryBalanceRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.bank.v1beta1.QueryBalanceResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Balance"))
              .build();
        }
      }
    }
    return getBalanceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<cosmos.bank.v1beta1.QueryAllBalancesRequest,
      cosmos.bank.v1beta1.QueryAllBalancesResponse> getAllBalancesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AllBalances",
      requestType = cosmos.bank.v1beta1.QueryAllBalancesRequest.class,
      responseType = cosmos.bank.v1beta1.QueryAllBalancesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<cosmos.bank.v1beta1.QueryAllBalancesRequest,
      cosmos.bank.v1beta1.QueryAllBalancesResponse> getAllBalancesMethod() {
    io.grpc.MethodDescriptor<cosmos.bank.v1beta1.QueryAllBalancesRequest, cosmos.bank.v1beta1.QueryAllBalancesResponse> getAllBalancesMethod;
    if ((getAllBalancesMethod = QueryGrpc.getAllBalancesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAllBalancesMethod = QueryGrpc.getAllBalancesMethod) == null) {
          QueryGrpc.getAllBalancesMethod = getAllBalancesMethod =
              io.grpc.MethodDescriptor.<cosmos.bank.v1beta1.QueryAllBalancesRequest, cosmos.bank.v1beta1.QueryAllBalancesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AllBalances"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.bank.v1beta1.QueryAllBalancesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.bank.v1beta1.QueryAllBalancesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("AllBalances"))
              .build();
        }
      }
    }
    return getAllBalancesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<cosmos.bank.v1beta1.QueryTotalSupplyRequest,
      cosmos.bank.v1beta1.QueryTotalSupplyResponse> getTotalSupplyMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "TotalSupply",
      requestType = cosmos.bank.v1beta1.QueryTotalSupplyRequest.class,
      responseType = cosmos.bank.v1beta1.QueryTotalSupplyResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<cosmos.bank.v1beta1.QueryTotalSupplyRequest,
      cosmos.bank.v1beta1.QueryTotalSupplyResponse> getTotalSupplyMethod() {
    io.grpc.MethodDescriptor<cosmos.bank.v1beta1.QueryTotalSupplyRequest, cosmos.bank.v1beta1.QueryTotalSupplyResponse> getTotalSupplyMethod;
    if ((getTotalSupplyMethod = QueryGrpc.getTotalSupplyMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getTotalSupplyMethod = QueryGrpc.getTotalSupplyMethod) == null) {
          QueryGrpc.getTotalSupplyMethod = getTotalSupplyMethod =
              io.grpc.MethodDescriptor.<cosmos.bank.v1beta1.QueryTotalSupplyRequest, cosmos.bank.v1beta1.QueryTotalSupplyResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "TotalSupply"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.bank.v1beta1.QueryTotalSupplyRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.bank.v1beta1.QueryTotalSupplyResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("TotalSupply"))
              .build();
        }
      }
    }
    return getTotalSupplyMethod;
  }

  private static volatile io.grpc.MethodDescriptor<cosmos.bank.v1beta1.QuerySupplyOfRequest,
      cosmos.bank.v1beta1.QuerySupplyOfResponse> getSupplyOfMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SupplyOf",
      requestType = cosmos.bank.v1beta1.QuerySupplyOfRequest.class,
      responseType = cosmos.bank.v1beta1.QuerySupplyOfResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<cosmos.bank.v1beta1.QuerySupplyOfRequest,
      cosmos.bank.v1beta1.QuerySupplyOfResponse> getSupplyOfMethod() {
    io.grpc.MethodDescriptor<cosmos.bank.v1beta1.QuerySupplyOfRequest, cosmos.bank.v1beta1.QuerySupplyOfResponse> getSupplyOfMethod;
    if ((getSupplyOfMethod = QueryGrpc.getSupplyOfMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getSupplyOfMethod = QueryGrpc.getSupplyOfMethod) == null) {
          QueryGrpc.getSupplyOfMethod = getSupplyOfMethod =
              io.grpc.MethodDescriptor.<cosmos.bank.v1beta1.QuerySupplyOfRequest, cosmos.bank.v1beta1.QuerySupplyOfResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SupplyOf"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.bank.v1beta1.QuerySupplyOfRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.bank.v1beta1.QuerySupplyOfResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("SupplyOf"))
              .build();
        }
      }
    }
    return getSupplyOfMethod;
  }

  private static volatile io.grpc.MethodDescriptor<cosmos.bank.v1beta1.QueryParamsRequest,
      cosmos.bank.v1beta1.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = cosmos.bank.v1beta1.QueryParamsRequest.class,
      responseType = cosmos.bank.v1beta1.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<cosmos.bank.v1beta1.QueryParamsRequest,
      cosmos.bank.v1beta1.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<cosmos.bank.v1beta1.QueryParamsRequest, cosmos.bank.v1beta1.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<cosmos.bank.v1beta1.QueryParamsRequest, cosmos.bank.v1beta1.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.bank.v1beta1.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.bank.v1beta1.QueryParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Params"))
              .build();
        }
      }
    }
    return getParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<cosmos.bank.v1beta1.QueryDenomMetadataRequest,
      cosmos.bank.v1beta1.QueryDenomMetadataResponse> getDenomMetadataMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DenomMetadata",
      requestType = cosmos.bank.v1beta1.QueryDenomMetadataRequest.class,
      responseType = cosmos.bank.v1beta1.QueryDenomMetadataResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<cosmos.bank.v1beta1.QueryDenomMetadataRequest,
      cosmos.bank.v1beta1.QueryDenomMetadataResponse> getDenomMetadataMethod() {
    io.grpc.MethodDescriptor<cosmos.bank.v1beta1.QueryDenomMetadataRequest, cosmos.bank.v1beta1.QueryDenomMetadataResponse> getDenomMetadataMethod;
    if ((getDenomMetadataMethod = QueryGrpc.getDenomMetadataMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getDenomMetadataMethod = QueryGrpc.getDenomMetadataMethod) == null) {
          QueryGrpc.getDenomMetadataMethod = getDenomMetadataMethod =
              io.grpc.MethodDescriptor.<cosmos.bank.v1beta1.QueryDenomMetadataRequest, cosmos.bank.v1beta1.QueryDenomMetadataResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DenomMetadata"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.bank.v1beta1.QueryDenomMetadataRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.bank.v1beta1.QueryDenomMetadataResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("DenomMetadata"))
              .build();
        }
      }
    }
    return getDenomMetadataMethod;
  }

  private static volatile io.grpc.MethodDescriptor<cosmos.bank.v1beta1.QueryDenomsMetadataRequest,
      cosmos.bank.v1beta1.QueryDenomsMetadataResponse> getDenomsMetadataMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DenomsMetadata",
      requestType = cosmos.bank.v1beta1.QueryDenomsMetadataRequest.class,
      responseType = cosmos.bank.v1beta1.QueryDenomsMetadataResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<cosmos.bank.v1beta1.QueryDenomsMetadataRequest,
      cosmos.bank.v1beta1.QueryDenomsMetadataResponse> getDenomsMetadataMethod() {
    io.grpc.MethodDescriptor<cosmos.bank.v1beta1.QueryDenomsMetadataRequest, cosmos.bank.v1beta1.QueryDenomsMetadataResponse> getDenomsMetadataMethod;
    if ((getDenomsMetadataMethod = QueryGrpc.getDenomsMetadataMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getDenomsMetadataMethod = QueryGrpc.getDenomsMetadataMethod) == null) {
          QueryGrpc.getDenomsMetadataMethod = getDenomsMetadataMethod =
              io.grpc.MethodDescriptor.<cosmos.bank.v1beta1.QueryDenomsMetadataRequest, cosmos.bank.v1beta1.QueryDenomsMetadataResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DenomsMetadata"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.bank.v1beta1.QueryDenomsMetadataRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.bank.v1beta1.QueryDenomsMetadataResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("DenomsMetadata"))
              .build();
        }
      }
    }
    return getDenomsMetadataMethod;
  }

  private static volatile io.grpc.MethodDescriptor<cosmos.bank.v1beta1.QueryDenomOwnersRequest,
      cosmos.bank.v1beta1.QueryDenomOwnersResponse> getDenomOwnersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DenomOwners",
      requestType = cosmos.bank.v1beta1.QueryDenomOwnersRequest.class,
      responseType = cosmos.bank.v1beta1.QueryDenomOwnersResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<cosmos.bank.v1beta1.QueryDenomOwnersRequest,
      cosmos.bank.v1beta1.QueryDenomOwnersResponse> getDenomOwnersMethod() {
    io.grpc.MethodDescriptor<cosmos.bank.v1beta1.QueryDenomOwnersRequest, cosmos.bank.v1beta1.QueryDenomOwnersResponse> getDenomOwnersMethod;
    if ((getDenomOwnersMethod = QueryGrpc.getDenomOwnersMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getDenomOwnersMethod = QueryGrpc.getDenomOwnersMethod) == null) {
          QueryGrpc.getDenomOwnersMethod = getDenomOwnersMethod =
              io.grpc.MethodDescriptor.<cosmos.bank.v1beta1.QueryDenomOwnersRequest, cosmos.bank.v1beta1.QueryDenomOwnersResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DenomOwners"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.bank.v1beta1.QueryDenomOwnersRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.bank.v1beta1.QueryDenomOwnersResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("DenomOwners"))
              .build();
        }
      }
    }
    return getDenomOwnersMethod;
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
     * Balance queries the balance of a single coin for a single account.
     * </pre>
     */
    public void balance(cosmos.bank.v1beta1.QueryBalanceRequest request,
        io.grpc.stub.StreamObserver<cosmos.bank.v1beta1.QueryBalanceResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getBalanceMethod(), responseObserver);
    }

    /**
     * <pre>
     * AllBalances queries the balance of all coins for a single account.
     * </pre>
     */
    public void allBalances(cosmos.bank.v1beta1.QueryAllBalancesRequest request,
        io.grpc.stub.StreamObserver<cosmos.bank.v1beta1.QueryAllBalancesResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAllBalancesMethod(), responseObserver);
    }

    /**
     * <pre>
     * TotalSupply queries the total supply of all coins.
     * </pre>
     */
    public void totalSupply(cosmos.bank.v1beta1.QueryTotalSupplyRequest request,
        io.grpc.stub.StreamObserver<cosmos.bank.v1beta1.QueryTotalSupplyResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getTotalSupplyMethod(), responseObserver);
    }

    /**
     * <pre>
     * SupplyOf queries the supply of a single coin.
     * </pre>
     */
    public void supplyOf(cosmos.bank.v1beta1.QuerySupplyOfRequest request,
        io.grpc.stub.StreamObserver<cosmos.bank.v1beta1.QuerySupplyOfResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSupplyOfMethod(), responseObserver);
    }

    /**
     * <pre>
     * Params queries the parameters of x/bank module.
     * </pre>
     */
    public void params(cosmos.bank.v1beta1.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<cosmos.bank.v1beta1.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }

    /**
     * <pre>
     * DenomsMetadata queries the client metadata of a given coin denomination.
     * </pre>
     */
    public void denomMetadata(cosmos.bank.v1beta1.QueryDenomMetadataRequest request,
        io.grpc.stub.StreamObserver<cosmos.bank.v1beta1.QueryDenomMetadataResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDenomMetadataMethod(), responseObserver);
    }

    /**
     * <pre>
     * DenomsMetadata queries the client metadata for all registered coin
     * denominations.
     * </pre>
     */
    public void denomsMetadata(cosmos.bank.v1beta1.QueryDenomsMetadataRequest request,
        io.grpc.stub.StreamObserver<cosmos.bank.v1beta1.QueryDenomsMetadataResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDenomsMetadataMethod(), responseObserver);
    }

    /**
     * <pre>
     * DenomOwners queries for all account addresses that own a particular token
     * denomination.
     * </pre>
     */
    public void denomOwners(cosmos.bank.v1beta1.QueryDenomOwnersRequest request,
        io.grpc.stub.StreamObserver<cosmos.bank.v1beta1.QueryDenomOwnersResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDenomOwnersMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getBalanceMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                cosmos.bank.v1beta1.QueryBalanceRequest,
                cosmos.bank.v1beta1.QueryBalanceResponse>(
                  this, METHODID_BALANCE)))
          .addMethod(
            getAllBalancesMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                cosmos.bank.v1beta1.QueryAllBalancesRequest,
                cosmos.bank.v1beta1.QueryAllBalancesResponse>(
                  this, METHODID_ALL_BALANCES)))
          .addMethod(
            getTotalSupplyMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                cosmos.bank.v1beta1.QueryTotalSupplyRequest,
                cosmos.bank.v1beta1.QueryTotalSupplyResponse>(
                  this, METHODID_TOTAL_SUPPLY)))
          .addMethod(
            getSupplyOfMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                cosmos.bank.v1beta1.QuerySupplyOfRequest,
                cosmos.bank.v1beta1.QuerySupplyOfResponse>(
                  this, METHODID_SUPPLY_OF)))
          .addMethod(
            getParamsMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                cosmos.bank.v1beta1.QueryParamsRequest,
                cosmos.bank.v1beta1.QueryParamsResponse>(
                  this, METHODID_PARAMS)))
          .addMethod(
            getDenomMetadataMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                cosmos.bank.v1beta1.QueryDenomMetadataRequest,
                cosmos.bank.v1beta1.QueryDenomMetadataResponse>(
                  this, METHODID_DENOM_METADATA)))
          .addMethod(
            getDenomsMetadataMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                cosmos.bank.v1beta1.QueryDenomsMetadataRequest,
                cosmos.bank.v1beta1.QueryDenomsMetadataResponse>(
                  this, METHODID_DENOMS_METADATA)))
          .addMethod(
            getDenomOwnersMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                cosmos.bank.v1beta1.QueryDenomOwnersRequest,
                cosmos.bank.v1beta1.QueryDenomOwnersResponse>(
                  this, METHODID_DENOM_OWNERS)))
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
     * Balance queries the balance of a single coin for a single account.
     * </pre>
     */
    public void balance(cosmos.bank.v1beta1.QueryBalanceRequest request,
        io.grpc.stub.StreamObserver<cosmos.bank.v1beta1.QueryBalanceResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getBalanceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * AllBalances queries the balance of all coins for a single account.
     * </pre>
     */
    public void allBalances(cosmos.bank.v1beta1.QueryAllBalancesRequest request,
        io.grpc.stub.StreamObserver<cosmos.bank.v1beta1.QueryAllBalancesResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAllBalancesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * TotalSupply queries the total supply of all coins.
     * </pre>
     */
    public void totalSupply(cosmos.bank.v1beta1.QueryTotalSupplyRequest request,
        io.grpc.stub.StreamObserver<cosmos.bank.v1beta1.QueryTotalSupplyResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getTotalSupplyMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * SupplyOf queries the supply of a single coin.
     * </pre>
     */
    public void supplyOf(cosmos.bank.v1beta1.QuerySupplyOfRequest request,
        io.grpc.stub.StreamObserver<cosmos.bank.v1beta1.QuerySupplyOfResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSupplyOfMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Params queries the parameters of x/bank module.
     * </pre>
     */
    public void params(cosmos.bank.v1beta1.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<cosmos.bank.v1beta1.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * DenomsMetadata queries the client metadata of a given coin denomination.
     * </pre>
     */
    public void denomMetadata(cosmos.bank.v1beta1.QueryDenomMetadataRequest request,
        io.grpc.stub.StreamObserver<cosmos.bank.v1beta1.QueryDenomMetadataResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDenomMetadataMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * DenomsMetadata queries the client metadata for all registered coin
     * denominations.
     * </pre>
     */
    public void denomsMetadata(cosmos.bank.v1beta1.QueryDenomsMetadataRequest request,
        io.grpc.stub.StreamObserver<cosmos.bank.v1beta1.QueryDenomsMetadataResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDenomsMetadataMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * DenomOwners queries for all account addresses that own a particular token
     * denomination.
     * </pre>
     */
    public void denomOwners(cosmos.bank.v1beta1.QueryDenomOwnersRequest request,
        io.grpc.stub.StreamObserver<cosmos.bank.v1beta1.QueryDenomOwnersResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDenomOwnersMethod(), getCallOptions()), request, responseObserver);
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
     * Balance queries the balance of a single coin for a single account.
     * </pre>
     */
    public cosmos.bank.v1beta1.QueryBalanceResponse balance(cosmos.bank.v1beta1.QueryBalanceRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getBalanceMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * AllBalances queries the balance of all coins for a single account.
     * </pre>
     */
    public cosmos.bank.v1beta1.QueryAllBalancesResponse allBalances(cosmos.bank.v1beta1.QueryAllBalancesRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAllBalancesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * TotalSupply queries the total supply of all coins.
     * </pre>
     */
    public cosmos.bank.v1beta1.QueryTotalSupplyResponse totalSupply(cosmos.bank.v1beta1.QueryTotalSupplyRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getTotalSupplyMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * SupplyOf queries the supply of a single coin.
     * </pre>
     */
    public cosmos.bank.v1beta1.QuerySupplyOfResponse supplyOf(cosmos.bank.v1beta1.QuerySupplyOfRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSupplyOfMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Params queries the parameters of x/bank module.
     * </pre>
     */
    public cosmos.bank.v1beta1.QueryParamsResponse params(cosmos.bank.v1beta1.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * DenomsMetadata queries the client metadata of a given coin denomination.
     * </pre>
     */
    public cosmos.bank.v1beta1.QueryDenomMetadataResponse denomMetadata(cosmos.bank.v1beta1.QueryDenomMetadataRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDenomMetadataMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * DenomsMetadata queries the client metadata for all registered coin
     * denominations.
     * </pre>
     */
    public cosmos.bank.v1beta1.QueryDenomsMetadataResponse denomsMetadata(cosmos.bank.v1beta1.QueryDenomsMetadataRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDenomsMetadataMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * DenomOwners queries for all account addresses that own a particular token
     * denomination.
     * </pre>
     */
    public cosmos.bank.v1beta1.QueryDenomOwnersResponse denomOwners(cosmos.bank.v1beta1.QueryDenomOwnersRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDenomOwnersMethod(), getCallOptions(), request);
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
     * Balance queries the balance of a single coin for a single account.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<cosmos.bank.v1beta1.QueryBalanceResponse> balance(
        cosmos.bank.v1beta1.QueryBalanceRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getBalanceMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * AllBalances queries the balance of all coins for a single account.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<cosmos.bank.v1beta1.QueryAllBalancesResponse> allBalances(
        cosmos.bank.v1beta1.QueryAllBalancesRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAllBalancesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * TotalSupply queries the total supply of all coins.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<cosmos.bank.v1beta1.QueryTotalSupplyResponse> totalSupply(
        cosmos.bank.v1beta1.QueryTotalSupplyRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getTotalSupplyMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * SupplyOf queries the supply of a single coin.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<cosmos.bank.v1beta1.QuerySupplyOfResponse> supplyOf(
        cosmos.bank.v1beta1.QuerySupplyOfRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSupplyOfMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Params queries the parameters of x/bank module.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<cosmos.bank.v1beta1.QueryParamsResponse> params(
        cosmos.bank.v1beta1.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * DenomsMetadata queries the client metadata of a given coin denomination.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<cosmos.bank.v1beta1.QueryDenomMetadataResponse> denomMetadata(
        cosmos.bank.v1beta1.QueryDenomMetadataRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDenomMetadataMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * DenomsMetadata queries the client metadata for all registered coin
     * denominations.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<cosmos.bank.v1beta1.QueryDenomsMetadataResponse> denomsMetadata(
        cosmos.bank.v1beta1.QueryDenomsMetadataRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDenomsMetadataMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * DenomOwners queries for all account addresses that own a particular token
     * denomination.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<cosmos.bank.v1beta1.QueryDenomOwnersResponse> denomOwners(
        cosmos.bank.v1beta1.QueryDenomOwnersRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDenomOwnersMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_BALANCE = 0;
  private static final int METHODID_ALL_BALANCES = 1;
  private static final int METHODID_TOTAL_SUPPLY = 2;
  private static final int METHODID_SUPPLY_OF = 3;
  private static final int METHODID_PARAMS = 4;
  private static final int METHODID_DENOM_METADATA = 5;
  private static final int METHODID_DENOMS_METADATA = 6;
  private static final int METHODID_DENOM_OWNERS = 7;

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
        case METHODID_BALANCE:
          serviceImpl.balance((cosmos.bank.v1beta1.QueryBalanceRequest) request,
              (io.grpc.stub.StreamObserver<cosmos.bank.v1beta1.QueryBalanceResponse>) responseObserver);
          break;
        case METHODID_ALL_BALANCES:
          serviceImpl.allBalances((cosmos.bank.v1beta1.QueryAllBalancesRequest) request,
              (io.grpc.stub.StreamObserver<cosmos.bank.v1beta1.QueryAllBalancesResponse>) responseObserver);
          break;
        case METHODID_TOTAL_SUPPLY:
          serviceImpl.totalSupply((cosmos.bank.v1beta1.QueryTotalSupplyRequest) request,
              (io.grpc.stub.StreamObserver<cosmos.bank.v1beta1.QueryTotalSupplyResponse>) responseObserver);
          break;
        case METHODID_SUPPLY_OF:
          serviceImpl.supplyOf((cosmos.bank.v1beta1.QuerySupplyOfRequest) request,
              (io.grpc.stub.StreamObserver<cosmos.bank.v1beta1.QuerySupplyOfResponse>) responseObserver);
          break;
        case METHODID_PARAMS:
          serviceImpl.params((cosmos.bank.v1beta1.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<cosmos.bank.v1beta1.QueryParamsResponse>) responseObserver);
          break;
        case METHODID_DENOM_METADATA:
          serviceImpl.denomMetadata((cosmos.bank.v1beta1.QueryDenomMetadataRequest) request,
              (io.grpc.stub.StreamObserver<cosmos.bank.v1beta1.QueryDenomMetadataResponse>) responseObserver);
          break;
        case METHODID_DENOMS_METADATA:
          serviceImpl.denomsMetadata((cosmos.bank.v1beta1.QueryDenomsMetadataRequest) request,
              (io.grpc.stub.StreamObserver<cosmos.bank.v1beta1.QueryDenomsMetadataResponse>) responseObserver);
          break;
        case METHODID_DENOM_OWNERS:
          serviceImpl.denomOwners((cosmos.bank.v1beta1.QueryDenomOwnersRequest) request,
              (io.grpc.stub.StreamObserver<cosmos.bank.v1beta1.QueryDenomOwnersResponse>) responseObserver);
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
      return cosmos.bank.v1beta1.CosmosBankQueryProto.getDescriptor();
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
              .addMethod(getBalanceMethod())
              .addMethod(getAllBalancesMethod())
              .addMethod(getTotalSupplyMethod())
              .addMethod(getSupplyOfMethod())
              .addMethod(getParamsMethod())
              .addMethod(getDenomMetadataMethod())
              .addMethod(getDenomsMetadataMethod())
              .addMethod(getDenomOwnersMethod())
              .build();
        }
      }
    }
    return result;
  }
}
