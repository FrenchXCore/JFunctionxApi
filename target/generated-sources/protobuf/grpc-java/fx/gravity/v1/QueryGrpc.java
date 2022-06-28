package fx.gravity.v1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Query defines the gRPC querier service
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.44.1)",
    comments = "Source: fx/gravity/v1/query.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "fx.gravity.v1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<fx.gravity.v1.QueryOuterClass.QueryParamsRequest,
      fx.gravity.v1.QueryOuterClass.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = fx.gravity.v1.QueryOuterClass.QueryParamsRequest.class,
      responseType = fx.gravity.v1.QueryOuterClass.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.gravity.v1.QueryOuterClass.QueryParamsRequest,
      fx.gravity.v1.QueryOuterClass.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<fx.gravity.v1.QueryOuterClass.QueryParamsRequest, fx.gravity.v1.QueryOuterClass.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<fx.gravity.v1.QueryOuterClass.QueryParamsRequest, fx.gravity.v1.QueryOuterClass.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.v1.QueryOuterClass.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.v1.QueryOuterClass.QueryParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Params"))
              .build();
        }
      }
    }
    return getParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<fx.gravity.v1.QueryOuterClass.QueryCurrentValsetRequest,
      fx.gravity.v1.QueryOuterClass.QueryCurrentValsetResponse> getCurrentValsetMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CurrentValset",
      requestType = fx.gravity.v1.QueryOuterClass.QueryCurrentValsetRequest.class,
      responseType = fx.gravity.v1.QueryOuterClass.QueryCurrentValsetResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.gravity.v1.QueryOuterClass.QueryCurrentValsetRequest,
      fx.gravity.v1.QueryOuterClass.QueryCurrentValsetResponse> getCurrentValsetMethod() {
    io.grpc.MethodDescriptor<fx.gravity.v1.QueryOuterClass.QueryCurrentValsetRequest, fx.gravity.v1.QueryOuterClass.QueryCurrentValsetResponse> getCurrentValsetMethod;
    if ((getCurrentValsetMethod = QueryGrpc.getCurrentValsetMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getCurrentValsetMethod = QueryGrpc.getCurrentValsetMethod) == null) {
          QueryGrpc.getCurrentValsetMethod = getCurrentValsetMethod =
              io.grpc.MethodDescriptor.<fx.gravity.v1.QueryOuterClass.QueryCurrentValsetRequest, fx.gravity.v1.QueryOuterClass.QueryCurrentValsetResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CurrentValset"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.v1.QueryOuterClass.QueryCurrentValsetRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.v1.QueryOuterClass.QueryCurrentValsetResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("CurrentValset"))
              .build();
        }
      }
    }
    return getCurrentValsetMethod;
  }

  private static volatile io.grpc.MethodDescriptor<fx.gravity.v1.QueryOuterClass.QueryValsetRequestRequest,
      fx.gravity.v1.QueryOuterClass.QueryValsetRequestResponse> getValsetRequestMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ValsetRequest",
      requestType = fx.gravity.v1.QueryOuterClass.QueryValsetRequestRequest.class,
      responseType = fx.gravity.v1.QueryOuterClass.QueryValsetRequestResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.gravity.v1.QueryOuterClass.QueryValsetRequestRequest,
      fx.gravity.v1.QueryOuterClass.QueryValsetRequestResponse> getValsetRequestMethod() {
    io.grpc.MethodDescriptor<fx.gravity.v1.QueryOuterClass.QueryValsetRequestRequest, fx.gravity.v1.QueryOuterClass.QueryValsetRequestResponse> getValsetRequestMethod;
    if ((getValsetRequestMethod = QueryGrpc.getValsetRequestMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getValsetRequestMethod = QueryGrpc.getValsetRequestMethod) == null) {
          QueryGrpc.getValsetRequestMethod = getValsetRequestMethod =
              io.grpc.MethodDescriptor.<fx.gravity.v1.QueryOuterClass.QueryValsetRequestRequest, fx.gravity.v1.QueryOuterClass.QueryValsetRequestResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ValsetRequest"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.v1.QueryOuterClass.QueryValsetRequestRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.v1.QueryOuterClass.QueryValsetRequestResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ValsetRequest"))
              .build();
        }
      }
    }
    return getValsetRequestMethod;
  }

  private static volatile io.grpc.MethodDescriptor<fx.gravity.v1.QueryOuterClass.QueryValsetConfirmRequest,
      fx.gravity.v1.QueryOuterClass.QueryValsetConfirmResponse> getValsetConfirmMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ValsetConfirm",
      requestType = fx.gravity.v1.QueryOuterClass.QueryValsetConfirmRequest.class,
      responseType = fx.gravity.v1.QueryOuterClass.QueryValsetConfirmResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.gravity.v1.QueryOuterClass.QueryValsetConfirmRequest,
      fx.gravity.v1.QueryOuterClass.QueryValsetConfirmResponse> getValsetConfirmMethod() {
    io.grpc.MethodDescriptor<fx.gravity.v1.QueryOuterClass.QueryValsetConfirmRequest, fx.gravity.v1.QueryOuterClass.QueryValsetConfirmResponse> getValsetConfirmMethod;
    if ((getValsetConfirmMethod = QueryGrpc.getValsetConfirmMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getValsetConfirmMethod = QueryGrpc.getValsetConfirmMethod) == null) {
          QueryGrpc.getValsetConfirmMethod = getValsetConfirmMethod =
              io.grpc.MethodDescriptor.<fx.gravity.v1.QueryOuterClass.QueryValsetConfirmRequest, fx.gravity.v1.QueryOuterClass.QueryValsetConfirmResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ValsetConfirm"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.v1.QueryOuterClass.QueryValsetConfirmRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.v1.QueryOuterClass.QueryValsetConfirmResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ValsetConfirm"))
              .build();
        }
      }
    }
    return getValsetConfirmMethod;
  }

  private static volatile io.grpc.MethodDescriptor<fx.gravity.v1.QueryOuterClass.QueryValsetConfirmsByNonceRequest,
      fx.gravity.v1.QueryOuterClass.QueryValsetConfirmsByNonceResponse> getValsetConfirmsByNonceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ValsetConfirmsByNonce",
      requestType = fx.gravity.v1.QueryOuterClass.QueryValsetConfirmsByNonceRequest.class,
      responseType = fx.gravity.v1.QueryOuterClass.QueryValsetConfirmsByNonceResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.gravity.v1.QueryOuterClass.QueryValsetConfirmsByNonceRequest,
      fx.gravity.v1.QueryOuterClass.QueryValsetConfirmsByNonceResponse> getValsetConfirmsByNonceMethod() {
    io.grpc.MethodDescriptor<fx.gravity.v1.QueryOuterClass.QueryValsetConfirmsByNonceRequest, fx.gravity.v1.QueryOuterClass.QueryValsetConfirmsByNonceResponse> getValsetConfirmsByNonceMethod;
    if ((getValsetConfirmsByNonceMethod = QueryGrpc.getValsetConfirmsByNonceMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getValsetConfirmsByNonceMethod = QueryGrpc.getValsetConfirmsByNonceMethod) == null) {
          QueryGrpc.getValsetConfirmsByNonceMethod = getValsetConfirmsByNonceMethod =
              io.grpc.MethodDescriptor.<fx.gravity.v1.QueryOuterClass.QueryValsetConfirmsByNonceRequest, fx.gravity.v1.QueryOuterClass.QueryValsetConfirmsByNonceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ValsetConfirmsByNonce"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.v1.QueryOuterClass.QueryValsetConfirmsByNonceRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.v1.QueryOuterClass.QueryValsetConfirmsByNonceResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ValsetConfirmsByNonce"))
              .build();
        }
      }
    }
    return getValsetConfirmsByNonceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<fx.gravity.v1.QueryOuterClass.QueryLastValsetRequestsRequest,
      fx.gravity.v1.QueryOuterClass.QueryLastValsetRequestsResponse> getLastValsetRequestsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "LastValsetRequests",
      requestType = fx.gravity.v1.QueryOuterClass.QueryLastValsetRequestsRequest.class,
      responseType = fx.gravity.v1.QueryOuterClass.QueryLastValsetRequestsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.gravity.v1.QueryOuterClass.QueryLastValsetRequestsRequest,
      fx.gravity.v1.QueryOuterClass.QueryLastValsetRequestsResponse> getLastValsetRequestsMethod() {
    io.grpc.MethodDescriptor<fx.gravity.v1.QueryOuterClass.QueryLastValsetRequestsRequest, fx.gravity.v1.QueryOuterClass.QueryLastValsetRequestsResponse> getLastValsetRequestsMethod;
    if ((getLastValsetRequestsMethod = QueryGrpc.getLastValsetRequestsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getLastValsetRequestsMethod = QueryGrpc.getLastValsetRequestsMethod) == null) {
          QueryGrpc.getLastValsetRequestsMethod = getLastValsetRequestsMethod =
              io.grpc.MethodDescriptor.<fx.gravity.v1.QueryOuterClass.QueryLastValsetRequestsRequest, fx.gravity.v1.QueryOuterClass.QueryLastValsetRequestsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "LastValsetRequests"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.v1.QueryOuterClass.QueryLastValsetRequestsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.v1.QueryOuterClass.QueryLastValsetRequestsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("LastValsetRequests"))
              .build();
        }
      }
    }
    return getLastValsetRequestsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<fx.gravity.v1.QueryOuterClass.QueryLastPendingValsetRequestByAddrRequest,
      fx.gravity.v1.QueryOuterClass.QueryLastPendingValsetRequestByAddrResponse> getLastPendingValsetRequestByAddrMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "LastPendingValsetRequestByAddr",
      requestType = fx.gravity.v1.QueryOuterClass.QueryLastPendingValsetRequestByAddrRequest.class,
      responseType = fx.gravity.v1.QueryOuterClass.QueryLastPendingValsetRequestByAddrResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.gravity.v1.QueryOuterClass.QueryLastPendingValsetRequestByAddrRequest,
      fx.gravity.v1.QueryOuterClass.QueryLastPendingValsetRequestByAddrResponse> getLastPendingValsetRequestByAddrMethod() {
    io.grpc.MethodDescriptor<fx.gravity.v1.QueryOuterClass.QueryLastPendingValsetRequestByAddrRequest, fx.gravity.v1.QueryOuterClass.QueryLastPendingValsetRequestByAddrResponse> getLastPendingValsetRequestByAddrMethod;
    if ((getLastPendingValsetRequestByAddrMethod = QueryGrpc.getLastPendingValsetRequestByAddrMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getLastPendingValsetRequestByAddrMethod = QueryGrpc.getLastPendingValsetRequestByAddrMethod) == null) {
          QueryGrpc.getLastPendingValsetRequestByAddrMethod = getLastPendingValsetRequestByAddrMethod =
              io.grpc.MethodDescriptor.<fx.gravity.v1.QueryOuterClass.QueryLastPendingValsetRequestByAddrRequest, fx.gravity.v1.QueryOuterClass.QueryLastPendingValsetRequestByAddrResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "LastPendingValsetRequestByAddr"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.v1.QueryOuterClass.QueryLastPendingValsetRequestByAddrRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.v1.QueryOuterClass.QueryLastPendingValsetRequestByAddrResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("LastPendingValsetRequestByAddr"))
              .build();
        }
      }
    }
    return getLastPendingValsetRequestByAddrMethod;
  }

  private static volatile io.grpc.MethodDescriptor<fx.gravity.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrRequest,
      fx.gravity.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrResponse> getLastPendingBatchRequestByAddrMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "LastPendingBatchRequestByAddr",
      requestType = fx.gravity.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrRequest.class,
      responseType = fx.gravity.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.gravity.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrRequest,
      fx.gravity.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrResponse> getLastPendingBatchRequestByAddrMethod() {
    io.grpc.MethodDescriptor<fx.gravity.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrRequest, fx.gravity.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrResponse> getLastPendingBatchRequestByAddrMethod;
    if ((getLastPendingBatchRequestByAddrMethod = QueryGrpc.getLastPendingBatchRequestByAddrMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getLastPendingBatchRequestByAddrMethod = QueryGrpc.getLastPendingBatchRequestByAddrMethod) == null) {
          QueryGrpc.getLastPendingBatchRequestByAddrMethod = getLastPendingBatchRequestByAddrMethod =
              io.grpc.MethodDescriptor.<fx.gravity.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrRequest, fx.gravity.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "LastPendingBatchRequestByAddr"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("LastPendingBatchRequestByAddr"))
              .build();
        }
      }
    }
    return getLastPendingBatchRequestByAddrMethod;
  }

  private static volatile io.grpc.MethodDescriptor<fx.gravity.v1.QueryOuterClass.QueryLastEventNonceByAddrRequest,
      fx.gravity.v1.QueryOuterClass.QueryLastEventNonceByAddrResponse> getLastEventNonceByAddrMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "LastEventNonceByAddr",
      requestType = fx.gravity.v1.QueryOuterClass.QueryLastEventNonceByAddrRequest.class,
      responseType = fx.gravity.v1.QueryOuterClass.QueryLastEventNonceByAddrResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.gravity.v1.QueryOuterClass.QueryLastEventNonceByAddrRequest,
      fx.gravity.v1.QueryOuterClass.QueryLastEventNonceByAddrResponse> getLastEventNonceByAddrMethod() {
    io.grpc.MethodDescriptor<fx.gravity.v1.QueryOuterClass.QueryLastEventNonceByAddrRequest, fx.gravity.v1.QueryOuterClass.QueryLastEventNonceByAddrResponse> getLastEventNonceByAddrMethod;
    if ((getLastEventNonceByAddrMethod = QueryGrpc.getLastEventNonceByAddrMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getLastEventNonceByAddrMethod = QueryGrpc.getLastEventNonceByAddrMethod) == null) {
          QueryGrpc.getLastEventNonceByAddrMethod = getLastEventNonceByAddrMethod =
              io.grpc.MethodDescriptor.<fx.gravity.v1.QueryOuterClass.QueryLastEventNonceByAddrRequest, fx.gravity.v1.QueryOuterClass.QueryLastEventNonceByAddrResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "LastEventNonceByAddr"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.v1.QueryOuterClass.QueryLastEventNonceByAddrRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.v1.QueryOuterClass.QueryLastEventNonceByAddrResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("LastEventNonceByAddr"))
              .build();
        }
      }
    }
    return getLastEventNonceByAddrMethod;
  }

  private static volatile io.grpc.MethodDescriptor<fx.gravity.v1.QueryOuterClass.QueryLastEventBlockHeightByAddrRequest,
      fx.gravity.v1.QueryOuterClass.QueryLastEventBlockHeightByAddrResponse> getLastEventBlockHeightByAddrMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "LastEventBlockHeightByAddr",
      requestType = fx.gravity.v1.QueryOuterClass.QueryLastEventBlockHeightByAddrRequest.class,
      responseType = fx.gravity.v1.QueryOuterClass.QueryLastEventBlockHeightByAddrResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.gravity.v1.QueryOuterClass.QueryLastEventBlockHeightByAddrRequest,
      fx.gravity.v1.QueryOuterClass.QueryLastEventBlockHeightByAddrResponse> getLastEventBlockHeightByAddrMethod() {
    io.grpc.MethodDescriptor<fx.gravity.v1.QueryOuterClass.QueryLastEventBlockHeightByAddrRequest, fx.gravity.v1.QueryOuterClass.QueryLastEventBlockHeightByAddrResponse> getLastEventBlockHeightByAddrMethod;
    if ((getLastEventBlockHeightByAddrMethod = QueryGrpc.getLastEventBlockHeightByAddrMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getLastEventBlockHeightByAddrMethod = QueryGrpc.getLastEventBlockHeightByAddrMethod) == null) {
          QueryGrpc.getLastEventBlockHeightByAddrMethod = getLastEventBlockHeightByAddrMethod =
              io.grpc.MethodDescriptor.<fx.gravity.v1.QueryOuterClass.QueryLastEventBlockHeightByAddrRequest, fx.gravity.v1.QueryOuterClass.QueryLastEventBlockHeightByAddrResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "LastEventBlockHeightByAddr"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.v1.QueryOuterClass.QueryLastEventBlockHeightByAddrRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.v1.QueryOuterClass.QueryLastEventBlockHeightByAddrResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("LastEventBlockHeightByAddr"))
              .build();
        }
      }
    }
    return getLastEventBlockHeightByAddrMethod;
  }

  private static volatile io.grpc.MethodDescriptor<fx.gravity.v1.QueryOuterClass.QueryBatchFeeRequest,
      fx.gravity.v1.QueryOuterClass.QueryBatchFeeResponse> getBatchFeesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BatchFees",
      requestType = fx.gravity.v1.QueryOuterClass.QueryBatchFeeRequest.class,
      responseType = fx.gravity.v1.QueryOuterClass.QueryBatchFeeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.gravity.v1.QueryOuterClass.QueryBatchFeeRequest,
      fx.gravity.v1.QueryOuterClass.QueryBatchFeeResponse> getBatchFeesMethod() {
    io.grpc.MethodDescriptor<fx.gravity.v1.QueryOuterClass.QueryBatchFeeRequest, fx.gravity.v1.QueryOuterClass.QueryBatchFeeResponse> getBatchFeesMethod;
    if ((getBatchFeesMethod = QueryGrpc.getBatchFeesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getBatchFeesMethod = QueryGrpc.getBatchFeesMethod) == null) {
          QueryGrpc.getBatchFeesMethod = getBatchFeesMethod =
              io.grpc.MethodDescriptor.<fx.gravity.v1.QueryOuterClass.QueryBatchFeeRequest, fx.gravity.v1.QueryOuterClass.QueryBatchFeeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BatchFees"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.v1.QueryOuterClass.QueryBatchFeeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.v1.QueryOuterClass.QueryBatchFeeResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("BatchFees"))
              .build();
        }
      }
    }
    return getBatchFeesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<fx.gravity.v1.QueryOuterClass.QueryLastObservedBlockHeightRequest,
      fx.gravity.v1.QueryOuterClass.QueryLastObservedBlockHeightResponse> getLastObservedBlockHeightMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "LastObservedBlockHeight",
      requestType = fx.gravity.v1.QueryOuterClass.QueryLastObservedBlockHeightRequest.class,
      responseType = fx.gravity.v1.QueryOuterClass.QueryLastObservedBlockHeightResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.gravity.v1.QueryOuterClass.QueryLastObservedBlockHeightRequest,
      fx.gravity.v1.QueryOuterClass.QueryLastObservedBlockHeightResponse> getLastObservedBlockHeightMethod() {
    io.grpc.MethodDescriptor<fx.gravity.v1.QueryOuterClass.QueryLastObservedBlockHeightRequest, fx.gravity.v1.QueryOuterClass.QueryLastObservedBlockHeightResponse> getLastObservedBlockHeightMethod;
    if ((getLastObservedBlockHeightMethod = QueryGrpc.getLastObservedBlockHeightMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getLastObservedBlockHeightMethod = QueryGrpc.getLastObservedBlockHeightMethod) == null) {
          QueryGrpc.getLastObservedBlockHeightMethod = getLastObservedBlockHeightMethod =
              io.grpc.MethodDescriptor.<fx.gravity.v1.QueryOuterClass.QueryLastObservedBlockHeightRequest, fx.gravity.v1.QueryOuterClass.QueryLastObservedBlockHeightResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "LastObservedBlockHeight"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.v1.QueryOuterClass.QueryLastObservedBlockHeightRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.v1.QueryOuterClass.QueryLastObservedBlockHeightResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("LastObservedBlockHeight"))
              .build();
        }
      }
    }
    return getLastObservedBlockHeightMethod;
  }

  private static volatile io.grpc.MethodDescriptor<fx.gravity.v1.QueryOuterClass.QueryOutgoingTxBatchesRequest,
      fx.gravity.v1.QueryOuterClass.QueryOutgoingTxBatchesResponse> getOutgoingTxBatchesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "OutgoingTxBatches",
      requestType = fx.gravity.v1.QueryOuterClass.QueryOutgoingTxBatchesRequest.class,
      responseType = fx.gravity.v1.QueryOuterClass.QueryOutgoingTxBatchesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.gravity.v1.QueryOuterClass.QueryOutgoingTxBatchesRequest,
      fx.gravity.v1.QueryOuterClass.QueryOutgoingTxBatchesResponse> getOutgoingTxBatchesMethod() {
    io.grpc.MethodDescriptor<fx.gravity.v1.QueryOuterClass.QueryOutgoingTxBatchesRequest, fx.gravity.v1.QueryOuterClass.QueryOutgoingTxBatchesResponse> getOutgoingTxBatchesMethod;
    if ((getOutgoingTxBatchesMethod = QueryGrpc.getOutgoingTxBatchesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getOutgoingTxBatchesMethod = QueryGrpc.getOutgoingTxBatchesMethod) == null) {
          QueryGrpc.getOutgoingTxBatchesMethod = getOutgoingTxBatchesMethod =
              io.grpc.MethodDescriptor.<fx.gravity.v1.QueryOuterClass.QueryOutgoingTxBatchesRequest, fx.gravity.v1.QueryOuterClass.QueryOutgoingTxBatchesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "OutgoingTxBatches"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.v1.QueryOuterClass.QueryOutgoingTxBatchesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.v1.QueryOuterClass.QueryOutgoingTxBatchesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("OutgoingTxBatches"))
              .build();
        }
      }
    }
    return getOutgoingTxBatchesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<fx.gravity.v1.QueryOuterClass.QueryBatchRequestByNonceRequest,
      fx.gravity.v1.QueryOuterClass.QueryBatchRequestByNonceResponse> getBatchRequestByNonceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BatchRequestByNonce",
      requestType = fx.gravity.v1.QueryOuterClass.QueryBatchRequestByNonceRequest.class,
      responseType = fx.gravity.v1.QueryOuterClass.QueryBatchRequestByNonceResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.gravity.v1.QueryOuterClass.QueryBatchRequestByNonceRequest,
      fx.gravity.v1.QueryOuterClass.QueryBatchRequestByNonceResponse> getBatchRequestByNonceMethod() {
    io.grpc.MethodDescriptor<fx.gravity.v1.QueryOuterClass.QueryBatchRequestByNonceRequest, fx.gravity.v1.QueryOuterClass.QueryBatchRequestByNonceResponse> getBatchRequestByNonceMethod;
    if ((getBatchRequestByNonceMethod = QueryGrpc.getBatchRequestByNonceMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getBatchRequestByNonceMethod = QueryGrpc.getBatchRequestByNonceMethod) == null) {
          QueryGrpc.getBatchRequestByNonceMethod = getBatchRequestByNonceMethod =
              io.grpc.MethodDescriptor.<fx.gravity.v1.QueryOuterClass.QueryBatchRequestByNonceRequest, fx.gravity.v1.QueryOuterClass.QueryBatchRequestByNonceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BatchRequestByNonce"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.v1.QueryOuterClass.QueryBatchRequestByNonceRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.v1.QueryOuterClass.QueryBatchRequestByNonceResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("BatchRequestByNonce"))
              .build();
        }
      }
    }
    return getBatchRequestByNonceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<fx.gravity.v1.QueryOuterClass.QueryBatchConfirmRequest,
      fx.gravity.v1.QueryOuterClass.QueryBatchConfirmResponse> getBatchConfirmMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BatchConfirm",
      requestType = fx.gravity.v1.QueryOuterClass.QueryBatchConfirmRequest.class,
      responseType = fx.gravity.v1.QueryOuterClass.QueryBatchConfirmResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.gravity.v1.QueryOuterClass.QueryBatchConfirmRequest,
      fx.gravity.v1.QueryOuterClass.QueryBatchConfirmResponse> getBatchConfirmMethod() {
    io.grpc.MethodDescriptor<fx.gravity.v1.QueryOuterClass.QueryBatchConfirmRequest, fx.gravity.v1.QueryOuterClass.QueryBatchConfirmResponse> getBatchConfirmMethod;
    if ((getBatchConfirmMethod = QueryGrpc.getBatchConfirmMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getBatchConfirmMethod = QueryGrpc.getBatchConfirmMethod) == null) {
          QueryGrpc.getBatchConfirmMethod = getBatchConfirmMethod =
              io.grpc.MethodDescriptor.<fx.gravity.v1.QueryOuterClass.QueryBatchConfirmRequest, fx.gravity.v1.QueryOuterClass.QueryBatchConfirmResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BatchConfirm"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.v1.QueryOuterClass.QueryBatchConfirmRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.v1.QueryOuterClass.QueryBatchConfirmResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("BatchConfirm"))
              .build();
        }
      }
    }
    return getBatchConfirmMethod;
  }

  private static volatile io.grpc.MethodDescriptor<fx.gravity.v1.QueryOuterClass.QueryBatchConfirmsRequest,
      fx.gravity.v1.QueryOuterClass.QueryBatchConfirmsResponse> getBatchConfirmsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BatchConfirms",
      requestType = fx.gravity.v1.QueryOuterClass.QueryBatchConfirmsRequest.class,
      responseType = fx.gravity.v1.QueryOuterClass.QueryBatchConfirmsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.gravity.v1.QueryOuterClass.QueryBatchConfirmsRequest,
      fx.gravity.v1.QueryOuterClass.QueryBatchConfirmsResponse> getBatchConfirmsMethod() {
    io.grpc.MethodDescriptor<fx.gravity.v1.QueryOuterClass.QueryBatchConfirmsRequest, fx.gravity.v1.QueryOuterClass.QueryBatchConfirmsResponse> getBatchConfirmsMethod;
    if ((getBatchConfirmsMethod = QueryGrpc.getBatchConfirmsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getBatchConfirmsMethod = QueryGrpc.getBatchConfirmsMethod) == null) {
          QueryGrpc.getBatchConfirmsMethod = getBatchConfirmsMethod =
              io.grpc.MethodDescriptor.<fx.gravity.v1.QueryOuterClass.QueryBatchConfirmsRequest, fx.gravity.v1.QueryOuterClass.QueryBatchConfirmsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BatchConfirms"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.v1.QueryOuterClass.QueryBatchConfirmsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.v1.QueryOuterClass.QueryBatchConfirmsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("BatchConfirms"))
              .build();
        }
      }
    }
    return getBatchConfirmsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<fx.gravity.v1.QueryOuterClass.QueryERC20ToDenomRequest,
      fx.gravity.v1.QueryOuterClass.QueryERC20ToDenomResponse> getERC20ToDenomMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ERC20ToDenom",
      requestType = fx.gravity.v1.QueryOuterClass.QueryERC20ToDenomRequest.class,
      responseType = fx.gravity.v1.QueryOuterClass.QueryERC20ToDenomResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.gravity.v1.QueryOuterClass.QueryERC20ToDenomRequest,
      fx.gravity.v1.QueryOuterClass.QueryERC20ToDenomResponse> getERC20ToDenomMethod() {
    io.grpc.MethodDescriptor<fx.gravity.v1.QueryOuterClass.QueryERC20ToDenomRequest, fx.gravity.v1.QueryOuterClass.QueryERC20ToDenomResponse> getERC20ToDenomMethod;
    if ((getERC20ToDenomMethod = QueryGrpc.getERC20ToDenomMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getERC20ToDenomMethod = QueryGrpc.getERC20ToDenomMethod) == null) {
          QueryGrpc.getERC20ToDenomMethod = getERC20ToDenomMethod =
              io.grpc.MethodDescriptor.<fx.gravity.v1.QueryOuterClass.QueryERC20ToDenomRequest, fx.gravity.v1.QueryOuterClass.QueryERC20ToDenomResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ERC20ToDenom"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.v1.QueryOuterClass.QueryERC20ToDenomRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.v1.QueryOuterClass.QueryERC20ToDenomResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ERC20ToDenom"))
              .build();
        }
      }
    }
    return getERC20ToDenomMethod;
  }

  private static volatile io.grpc.MethodDescriptor<fx.gravity.v1.QueryOuterClass.QueryDenomToERC20Request,
      fx.gravity.v1.QueryOuterClass.QueryDenomToERC20Response> getDenomToERC20Method;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DenomToERC20",
      requestType = fx.gravity.v1.QueryOuterClass.QueryDenomToERC20Request.class,
      responseType = fx.gravity.v1.QueryOuterClass.QueryDenomToERC20Response.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.gravity.v1.QueryOuterClass.QueryDenomToERC20Request,
      fx.gravity.v1.QueryOuterClass.QueryDenomToERC20Response> getDenomToERC20Method() {
    io.grpc.MethodDescriptor<fx.gravity.v1.QueryOuterClass.QueryDenomToERC20Request, fx.gravity.v1.QueryOuterClass.QueryDenomToERC20Response> getDenomToERC20Method;
    if ((getDenomToERC20Method = QueryGrpc.getDenomToERC20Method) == null) {
      synchronized (QueryGrpc.class) {
        if ((getDenomToERC20Method = QueryGrpc.getDenomToERC20Method) == null) {
          QueryGrpc.getDenomToERC20Method = getDenomToERC20Method =
              io.grpc.MethodDescriptor.<fx.gravity.v1.QueryOuterClass.QueryDenomToERC20Request, fx.gravity.v1.QueryOuterClass.QueryDenomToERC20Response>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DenomToERC20"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.v1.QueryOuterClass.QueryDenomToERC20Request.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.v1.QueryOuterClass.QueryDenomToERC20Response.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("DenomToERC20"))
              .build();
        }
      }
    }
    return getDenomToERC20Method;
  }

  private static volatile io.grpc.MethodDescriptor<fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByValidatorRequest,
      fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByValidatorResponse> getGetDelegateKeyByValidatorMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetDelegateKeyByValidator",
      requestType = fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByValidatorRequest.class,
      responseType = fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByValidatorResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByValidatorRequest,
      fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByValidatorResponse> getGetDelegateKeyByValidatorMethod() {
    io.grpc.MethodDescriptor<fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByValidatorRequest, fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByValidatorResponse> getGetDelegateKeyByValidatorMethod;
    if ((getGetDelegateKeyByValidatorMethod = QueryGrpc.getGetDelegateKeyByValidatorMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getGetDelegateKeyByValidatorMethod = QueryGrpc.getGetDelegateKeyByValidatorMethod) == null) {
          QueryGrpc.getGetDelegateKeyByValidatorMethod = getGetDelegateKeyByValidatorMethod =
              io.grpc.MethodDescriptor.<fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByValidatorRequest, fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByValidatorResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetDelegateKeyByValidator"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByValidatorRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByValidatorResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("GetDelegateKeyByValidator"))
              .build();
        }
      }
    }
    return getGetDelegateKeyByValidatorMethod;
  }

  private static volatile io.grpc.MethodDescriptor<fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByEthRequest,
      fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByEthResponse> getGetDelegateKeyByEthMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetDelegateKeyByEth",
      requestType = fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByEthRequest.class,
      responseType = fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByEthResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByEthRequest,
      fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByEthResponse> getGetDelegateKeyByEthMethod() {
    io.grpc.MethodDescriptor<fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByEthRequest, fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByEthResponse> getGetDelegateKeyByEthMethod;
    if ((getGetDelegateKeyByEthMethod = QueryGrpc.getGetDelegateKeyByEthMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getGetDelegateKeyByEthMethod = QueryGrpc.getGetDelegateKeyByEthMethod) == null) {
          QueryGrpc.getGetDelegateKeyByEthMethod = getGetDelegateKeyByEthMethod =
              io.grpc.MethodDescriptor.<fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByEthRequest, fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByEthResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetDelegateKeyByEth"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByEthRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByEthResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("GetDelegateKeyByEth"))
              .build();
        }
      }
    }
    return getGetDelegateKeyByEthMethod;
  }

  private static volatile io.grpc.MethodDescriptor<fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByOrchestratorRequest,
      fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByOrchestratorResponse> getGetDelegateKeyByOrchestratorMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetDelegateKeyByOrchestrator",
      requestType = fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByOrchestratorRequest.class,
      responseType = fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByOrchestratorResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByOrchestratorRequest,
      fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByOrchestratorResponse> getGetDelegateKeyByOrchestratorMethod() {
    io.grpc.MethodDescriptor<fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByOrchestratorRequest, fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByOrchestratorResponse> getGetDelegateKeyByOrchestratorMethod;
    if ((getGetDelegateKeyByOrchestratorMethod = QueryGrpc.getGetDelegateKeyByOrchestratorMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getGetDelegateKeyByOrchestratorMethod = QueryGrpc.getGetDelegateKeyByOrchestratorMethod) == null) {
          QueryGrpc.getGetDelegateKeyByOrchestratorMethod = getGetDelegateKeyByOrchestratorMethod =
              io.grpc.MethodDescriptor.<fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByOrchestratorRequest, fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByOrchestratorResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetDelegateKeyByOrchestrator"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByOrchestratorRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByOrchestratorResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("GetDelegateKeyByOrchestrator"))
              .build();
        }
      }
    }
    return getGetDelegateKeyByOrchestratorMethod;
  }

  private static volatile io.grpc.MethodDescriptor<fx.gravity.v1.QueryOuterClass.QueryPendingSendToEthRequest,
      fx.gravity.v1.QueryOuterClass.QueryPendingSendToEthResponse> getGetPendingSendToEthMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetPendingSendToEth",
      requestType = fx.gravity.v1.QueryOuterClass.QueryPendingSendToEthRequest.class,
      responseType = fx.gravity.v1.QueryOuterClass.QueryPendingSendToEthResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.gravity.v1.QueryOuterClass.QueryPendingSendToEthRequest,
      fx.gravity.v1.QueryOuterClass.QueryPendingSendToEthResponse> getGetPendingSendToEthMethod() {
    io.grpc.MethodDescriptor<fx.gravity.v1.QueryOuterClass.QueryPendingSendToEthRequest, fx.gravity.v1.QueryOuterClass.QueryPendingSendToEthResponse> getGetPendingSendToEthMethod;
    if ((getGetPendingSendToEthMethod = QueryGrpc.getGetPendingSendToEthMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getGetPendingSendToEthMethod = QueryGrpc.getGetPendingSendToEthMethod) == null) {
          QueryGrpc.getGetPendingSendToEthMethod = getGetPendingSendToEthMethod =
              io.grpc.MethodDescriptor.<fx.gravity.v1.QueryOuterClass.QueryPendingSendToEthRequest, fx.gravity.v1.QueryOuterClass.QueryPendingSendToEthResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetPendingSendToEth"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.v1.QueryOuterClass.QueryPendingSendToEthRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.v1.QueryOuterClass.QueryPendingSendToEthResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("GetPendingSendToEth"))
              .build();
        }
      }
    }
    return getGetPendingSendToEthMethod;
  }

  private static volatile io.grpc.MethodDescriptor<fx.gravity.v1.QueryOuterClass.QueryIbcSequenceHeightRequest,
      fx.gravity.v1.QueryOuterClass.QueryIbcSequenceHeightResponse> getGetIbcSequenceHeightByChannelMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetIbcSequenceHeightByChannel",
      requestType = fx.gravity.v1.QueryOuterClass.QueryIbcSequenceHeightRequest.class,
      responseType = fx.gravity.v1.QueryOuterClass.QueryIbcSequenceHeightResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.gravity.v1.QueryOuterClass.QueryIbcSequenceHeightRequest,
      fx.gravity.v1.QueryOuterClass.QueryIbcSequenceHeightResponse> getGetIbcSequenceHeightByChannelMethod() {
    io.grpc.MethodDescriptor<fx.gravity.v1.QueryOuterClass.QueryIbcSequenceHeightRequest, fx.gravity.v1.QueryOuterClass.QueryIbcSequenceHeightResponse> getGetIbcSequenceHeightByChannelMethod;
    if ((getGetIbcSequenceHeightByChannelMethod = QueryGrpc.getGetIbcSequenceHeightByChannelMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getGetIbcSequenceHeightByChannelMethod = QueryGrpc.getGetIbcSequenceHeightByChannelMethod) == null) {
          QueryGrpc.getGetIbcSequenceHeightByChannelMethod = getGetIbcSequenceHeightByChannelMethod =
              io.grpc.MethodDescriptor.<fx.gravity.v1.QueryOuterClass.QueryIbcSequenceHeightRequest, fx.gravity.v1.QueryOuterClass.QueryIbcSequenceHeightResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetIbcSequenceHeightByChannel"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.v1.QueryOuterClass.QueryIbcSequenceHeightRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.v1.QueryOuterClass.QueryIbcSequenceHeightResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("GetIbcSequenceHeightByChannel"))
              .build();
        }
      }
    }
    return getGetIbcSequenceHeightByChannelMethod;
  }

  private static volatile io.grpc.MethodDescriptor<fx.gravity.v1.QueryOuterClass.QueryProjectedBatchTimeoutHeightRequest,
      fx.gravity.v1.QueryOuterClass.QueryProjectedBatchTimeoutHeightResponse> getProjectedBatchTimeoutHeightMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ProjectedBatchTimeoutHeight",
      requestType = fx.gravity.v1.QueryOuterClass.QueryProjectedBatchTimeoutHeightRequest.class,
      responseType = fx.gravity.v1.QueryOuterClass.QueryProjectedBatchTimeoutHeightResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.gravity.v1.QueryOuterClass.QueryProjectedBatchTimeoutHeightRequest,
      fx.gravity.v1.QueryOuterClass.QueryProjectedBatchTimeoutHeightResponse> getProjectedBatchTimeoutHeightMethod() {
    io.grpc.MethodDescriptor<fx.gravity.v1.QueryOuterClass.QueryProjectedBatchTimeoutHeightRequest, fx.gravity.v1.QueryOuterClass.QueryProjectedBatchTimeoutHeightResponse> getProjectedBatchTimeoutHeightMethod;
    if ((getProjectedBatchTimeoutHeightMethod = QueryGrpc.getProjectedBatchTimeoutHeightMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getProjectedBatchTimeoutHeightMethod = QueryGrpc.getProjectedBatchTimeoutHeightMethod) == null) {
          QueryGrpc.getProjectedBatchTimeoutHeightMethod = getProjectedBatchTimeoutHeightMethod =
              io.grpc.MethodDescriptor.<fx.gravity.v1.QueryOuterClass.QueryProjectedBatchTimeoutHeightRequest, fx.gravity.v1.QueryOuterClass.QueryProjectedBatchTimeoutHeightResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ProjectedBatchTimeoutHeight"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.v1.QueryOuterClass.QueryProjectedBatchTimeoutHeightRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.v1.QueryOuterClass.QueryProjectedBatchTimeoutHeightResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ProjectedBatchTimeoutHeight"))
              .build();
        }
      }
    }
    return getProjectedBatchTimeoutHeightMethod;
  }

  private static volatile io.grpc.MethodDescriptor<fx.gravity.v1.QueryOuterClass.QueryBridgeTokensRequest,
      fx.gravity.v1.QueryOuterClass.QueryBridgeTokensResponse> getBridgeTokensMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BridgeTokens",
      requestType = fx.gravity.v1.QueryOuterClass.QueryBridgeTokensRequest.class,
      responseType = fx.gravity.v1.QueryOuterClass.QueryBridgeTokensResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.gravity.v1.QueryOuterClass.QueryBridgeTokensRequest,
      fx.gravity.v1.QueryOuterClass.QueryBridgeTokensResponse> getBridgeTokensMethod() {
    io.grpc.MethodDescriptor<fx.gravity.v1.QueryOuterClass.QueryBridgeTokensRequest, fx.gravity.v1.QueryOuterClass.QueryBridgeTokensResponse> getBridgeTokensMethod;
    if ((getBridgeTokensMethod = QueryGrpc.getBridgeTokensMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getBridgeTokensMethod = QueryGrpc.getBridgeTokensMethod) == null) {
          QueryGrpc.getBridgeTokensMethod = getBridgeTokensMethod =
              io.grpc.MethodDescriptor.<fx.gravity.v1.QueryOuterClass.QueryBridgeTokensRequest, fx.gravity.v1.QueryOuterClass.QueryBridgeTokensResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BridgeTokens"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.v1.QueryOuterClass.QueryBridgeTokensRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.v1.QueryOuterClass.QueryBridgeTokensResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("BridgeTokens"))
              .build();
        }
      }
    }
    return getBridgeTokensMethod;
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
   * Query defines the gRPC querier service
   * </pre>
   */
  public static abstract class QueryImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Deployments queries deployments
     * </pre>
     */
    public void params(fx.gravity.v1.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.v1.QueryOuterClass.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }

    /**
     */
    public void currentValset(fx.gravity.v1.QueryOuterClass.QueryCurrentValsetRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.v1.QueryOuterClass.QueryCurrentValsetResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCurrentValsetMethod(), responseObserver);
    }

    /**
     */
    public void valsetRequest(fx.gravity.v1.QueryOuterClass.QueryValsetRequestRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.v1.QueryOuterClass.QueryValsetRequestResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getValsetRequestMethod(), responseObserver);
    }

    /**
     */
    public void valsetConfirm(fx.gravity.v1.QueryOuterClass.QueryValsetConfirmRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.v1.QueryOuterClass.QueryValsetConfirmResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getValsetConfirmMethod(), responseObserver);
    }

    /**
     */
    public void valsetConfirmsByNonce(fx.gravity.v1.QueryOuterClass.QueryValsetConfirmsByNonceRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.v1.QueryOuterClass.QueryValsetConfirmsByNonceResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getValsetConfirmsByNonceMethod(), responseObserver);
    }

    /**
     */
    public void lastValsetRequests(fx.gravity.v1.QueryOuterClass.QueryLastValsetRequestsRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.v1.QueryOuterClass.QueryLastValsetRequestsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getLastValsetRequestsMethod(), responseObserver);
    }

    /**
     */
    public void lastPendingValsetRequestByAddr(fx.gravity.v1.QueryOuterClass.QueryLastPendingValsetRequestByAddrRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.v1.QueryOuterClass.QueryLastPendingValsetRequestByAddrResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getLastPendingValsetRequestByAddrMethod(), responseObserver);
    }

    /**
     */
    public void lastPendingBatchRequestByAddr(fx.gravity.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getLastPendingBatchRequestByAddrMethod(), responseObserver);
    }

    /**
     */
    public void lastEventNonceByAddr(fx.gravity.v1.QueryOuterClass.QueryLastEventNonceByAddrRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.v1.QueryOuterClass.QueryLastEventNonceByAddrResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getLastEventNonceByAddrMethod(), responseObserver);
    }

    /**
     */
    public void lastEventBlockHeightByAddr(fx.gravity.v1.QueryOuterClass.QueryLastEventBlockHeightByAddrRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.v1.QueryOuterClass.QueryLastEventBlockHeightByAddrResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getLastEventBlockHeightByAddrMethod(), responseObserver);
    }

    /**
     */
    public void batchFees(fx.gravity.v1.QueryOuterClass.QueryBatchFeeRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.v1.QueryOuterClass.QueryBatchFeeResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getBatchFeesMethod(), responseObserver);
    }

    /**
     */
    public void lastObservedBlockHeight(fx.gravity.v1.QueryOuterClass.QueryLastObservedBlockHeightRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.v1.QueryOuterClass.QueryLastObservedBlockHeightResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getLastObservedBlockHeightMethod(), responseObserver);
    }

    /**
     */
    public void outgoingTxBatches(fx.gravity.v1.QueryOuterClass.QueryOutgoingTxBatchesRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.v1.QueryOuterClass.QueryOutgoingTxBatchesResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getOutgoingTxBatchesMethod(), responseObserver);
    }

    /**
     */
    public void batchRequestByNonce(fx.gravity.v1.QueryOuterClass.QueryBatchRequestByNonceRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.v1.QueryOuterClass.QueryBatchRequestByNonceResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getBatchRequestByNonceMethod(), responseObserver);
    }

    /**
     */
    public void batchConfirm(fx.gravity.v1.QueryOuterClass.QueryBatchConfirmRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.v1.QueryOuterClass.QueryBatchConfirmResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getBatchConfirmMethod(), responseObserver);
    }

    /**
     */
    public void batchConfirms(fx.gravity.v1.QueryOuterClass.QueryBatchConfirmsRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.v1.QueryOuterClass.QueryBatchConfirmsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getBatchConfirmsMethod(), responseObserver);
    }

    /**
     */
    public void eRC20ToDenom(fx.gravity.v1.QueryOuterClass.QueryERC20ToDenomRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.v1.QueryOuterClass.QueryERC20ToDenomResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getERC20ToDenomMethod(), responseObserver);
    }

    /**
     */
    public void denomToERC20(fx.gravity.v1.QueryOuterClass.QueryDenomToERC20Request request,
        io.grpc.stub.StreamObserver<fx.gravity.v1.QueryOuterClass.QueryDenomToERC20Response> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDenomToERC20Method(), responseObserver);
    }

    /**
     */
    public void getDelegateKeyByValidator(fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByValidatorRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByValidatorResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetDelegateKeyByValidatorMethod(), responseObserver);
    }

    /**
     */
    public void getDelegateKeyByEth(fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByEthRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByEthResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetDelegateKeyByEthMethod(), responseObserver);
    }

    /**
     */
    public void getDelegateKeyByOrchestrator(fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByOrchestratorRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByOrchestratorResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetDelegateKeyByOrchestratorMethod(), responseObserver);
    }

    /**
     */
    public void getPendingSendToEth(fx.gravity.v1.QueryOuterClass.QueryPendingSendToEthRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.v1.QueryOuterClass.QueryPendingSendToEthResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetPendingSendToEthMethod(), responseObserver);
    }

    /**
     */
    public void getIbcSequenceHeightByChannel(fx.gravity.v1.QueryOuterClass.QueryIbcSequenceHeightRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.v1.QueryOuterClass.QueryIbcSequenceHeightResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetIbcSequenceHeightByChannelMethod(), responseObserver);
    }

    /**
     */
    public void projectedBatchTimeoutHeight(fx.gravity.v1.QueryOuterClass.QueryProjectedBatchTimeoutHeightRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.v1.QueryOuterClass.QueryProjectedBatchTimeoutHeightResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getProjectedBatchTimeoutHeightMethod(), responseObserver);
    }

    /**
     */
    public void bridgeTokens(fx.gravity.v1.QueryOuterClass.QueryBridgeTokensRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.v1.QueryOuterClass.QueryBridgeTokensResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getBridgeTokensMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getParamsMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.gravity.v1.QueryOuterClass.QueryParamsRequest,
                fx.gravity.v1.QueryOuterClass.QueryParamsResponse>(
                  this, METHODID_PARAMS)))
          .addMethod(
            getCurrentValsetMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.gravity.v1.QueryOuterClass.QueryCurrentValsetRequest,
                fx.gravity.v1.QueryOuterClass.QueryCurrentValsetResponse>(
                  this, METHODID_CURRENT_VALSET)))
          .addMethod(
            getValsetRequestMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.gravity.v1.QueryOuterClass.QueryValsetRequestRequest,
                fx.gravity.v1.QueryOuterClass.QueryValsetRequestResponse>(
                  this, METHODID_VALSET_REQUEST)))
          .addMethod(
            getValsetConfirmMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.gravity.v1.QueryOuterClass.QueryValsetConfirmRequest,
                fx.gravity.v1.QueryOuterClass.QueryValsetConfirmResponse>(
                  this, METHODID_VALSET_CONFIRM)))
          .addMethod(
            getValsetConfirmsByNonceMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.gravity.v1.QueryOuterClass.QueryValsetConfirmsByNonceRequest,
                fx.gravity.v1.QueryOuterClass.QueryValsetConfirmsByNonceResponse>(
                  this, METHODID_VALSET_CONFIRMS_BY_NONCE)))
          .addMethod(
            getLastValsetRequestsMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.gravity.v1.QueryOuterClass.QueryLastValsetRequestsRequest,
                fx.gravity.v1.QueryOuterClass.QueryLastValsetRequestsResponse>(
                  this, METHODID_LAST_VALSET_REQUESTS)))
          .addMethod(
            getLastPendingValsetRequestByAddrMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.gravity.v1.QueryOuterClass.QueryLastPendingValsetRequestByAddrRequest,
                fx.gravity.v1.QueryOuterClass.QueryLastPendingValsetRequestByAddrResponse>(
                  this, METHODID_LAST_PENDING_VALSET_REQUEST_BY_ADDR)))
          .addMethod(
            getLastPendingBatchRequestByAddrMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.gravity.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrRequest,
                fx.gravity.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrResponse>(
                  this, METHODID_LAST_PENDING_BATCH_REQUEST_BY_ADDR)))
          .addMethod(
            getLastEventNonceByAddrMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.gravity.v1.QueryOuterClass.QueryLastEventNonceByAddrRequest,
                fx.gravity.v1.QueryOuterClass.QueryLastEventNonceByAddrResponse>(
                  this, METHODID_LAST_EVENT_NONCE_BY_ADDR)))
          .addMethod(
            getLastEventBlockHeightByAddrMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.gravity.v1.QueryOuterClass.QueryLastEventBlockHeightByAddrRequest,
                fx.gravity.v1.QueryOuterClass.QueryLastEventBlockHeightByAddrResponse>(
                  this, METHODID_LAST_EVENT_BLOCK_HEIGHT_BY_ADDR)))
          .addMethod(
            getBatchFeesMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.gravity.v1.QueryOuterClass.QueryBatchFeeRequest,
                fx.gravity.v1.QueryOuterClass.QueryBatchFeeResponse>(
                  this, METHODID_BATCH_FEES)))
          .addMethod(
            getLastObservedBlockHeightMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.gravity.v1.QueryOuterClass.QueryLastObservedBlockHeightRequest,
                fx.gravity.v1.QueryOuterClass.QueryLastObservedBlockHeightResponse>(
                  this, METHODID_LAST_OBSERVED_BLOCK_HEIGHT)))
          .addMethod(
            getOutgoingTxBatchesMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.gravity.v1.QueryOuterClass.QueryOutgoingTxBatchesRequest,
                fx.gravity.v1.QueryOuterClass.QueryOutgoingTxBatchesResponse>(
                  this, METHODID_OUTGOING_TX_BATCHES)))
          .addMethod(
            getBatchRequestByNonceMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.gravity.v1.QueryOuterClass.QueryBatchRequestByNonceRequest,
                fx.gravity.v1.QueryOuterClass.QueryBatchRequestByNonceResponse>(
                  this, METHODID_BATCH_REQUEST_BY_NONCE)))
          .addMethod(
            getBatchConfirmMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.gravity.v1.QueryOuterClass.QueryBatchConfirmRequest,
                fx.gravity.v1.QueryOuterClass.QueryBatchConfirmResponse>(
                  this, METHODID_BATCH_CONFIRM)))
          .addMethod(
            getBatchConfirmsMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.gravity.v1.QueryOuterClass.QueryBatchConfirmsRequest,
                fx.gravity.v1.QueryOuterClass.QueryBatchConfirmsResponse>(
                  this, METHODID_BATCH_CONFIRMS)))
          .addMethod(
            getERC20ToDenomMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.gravity.v1.QueryOuterClass.QueryERC20ToDenomRequest,
                fx.gravity.v1.QueryOuterClass.QueryERC20ToDenomResponse>(
                  this, METHODID_ERC20TO_DENOM)))
          .addMethod(
            getDenomToERC20Method(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.gravity.v1.QueryOuterClass.QueryDenomToERC20Request,
                fx.gravity.v1.QueryOuterClass.QueryDenomToERC20Response>(
                  this, METHODID_DENOM_TO_ERC20)))
          .addMethod(
            getGetDelegateKeyByValidatorMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByValidatorRequest,
                fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByValidatorResponse>(
                  this, METHODID_GET_DELEGATE_KEY_BY_VALIDATOR)))
          .addMethod(
            getGetDelegateKeyByEthMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByEthRequest,
                fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByEthResponse>(
                  this, METHODID_GET_DELEGATE_KEY_BY_ETH)))
          .addMethod(
            getGetDelegateKeyByOrchestratorMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByOrchestratorRequest,
                fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByOrchestratorResponse>(
                  this, METHODID_GET_DELEGATE_KEY_BY_ORCHESTRATOR)))
          .addMethod(
            getGetPendingSendToEthMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.gravity.v1.QueryOuterClass.QueryPendingSendToEthRequest,
                fx.gravity.v1.QueryOuterClass.QueryPendingSendToEthResponse>(
                  this, METHODID_GET_PENDING_SEND_TO_ETH)))
          .addMethod(
            getGetIbcSequenceHeightByChannelMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.gravity.v1.QueryOuterClass.QueryIbcSequenceHeightRequest,
                fx.gravity.v1.QueryOuterClass.QueryIbcSequenceHeightResponse>(
                  this, METHODID_GET_IBC_SEQUENCE_HEIGHT_BY_CHANNEL)))
          .addMethod(
            getProjectedBatchTimeoutHeightMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.gravity.v1.QueryOuterClass.QueryProjectedBatchTimeoutHeightRequest,
                fx.gravity.v1.QueryOuterClass.QueryProjectedBatchTimeoutHeightResponse>(
                  this, METHODID_PROJECTED_BATCH_TIMEOUT_HEIGHT)))
          .addMethod(
            getBridgeTokensMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.gravity.v1.QueryOuterClass.QueryBridgeTokensRequest,
                fx.gravity.v1.QueryOuterClass.QueryBridgeTokensResponse>(
                  this, METHODID_BRIDGE_TOKENS)))
          .build();
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service
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
     * Deployments queries deployments
     * </pre>
     */
    public void params(fx.gravity.v1.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.v1.QueryOuterClass.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void currentValset(fx.gravity.v1.QueryOuterClass.QueryCurrentValsetRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.v1.QueryOuterClass.QueryCurrentValsetResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCurrentValsetMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void valsetRequest(fx.gravity.v1.QueryOuterClass.QueryValsetRequestRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.v1.QueryOuterClass.QueryValsetRequestResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getValsetRequestMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void valsetConfirm(fx.gravity.v1.QueryOuterClass.QueryValsetConfirmRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.v1.QueryOuterClass.QueryValsetConfirmResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getValsetConfirmMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void valsetConfirmsByNonce(fx.gravity.v1.QueryOuterClass.QueryValsetConfirmsByNonceRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.v1.QueryOuterClass.QueryValsetConfirmsByNonceResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getValsetConfirmsByNonceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void lastValsetRequests(fx.gravity.v1.QueryOuterClass.QueryLastValsetRequestsRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.v1.QueryOuterClass.QueryLastValsetRequestsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getLastValsetRequestsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void lastPendingValsetRequestByAddr(fx.gravity.v1.QueryOuterClass.QueryLastPendingValsetRequestByAddrRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.v1.QueryOuterClass.QueryLastPendingValsetRequestByAddrResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getLastPendingValsetRequestByAddrMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void lastPendingBatchRequestByAddr(fx.gravity.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getLastPendingBatchRequestByAddrMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void lastEventNonceByAddr(fx.gravity.v1.QueryOuterClass.QueryLastEventNonceByAddrRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.v1.QueryOuterClass.QueryLastEventNonceByAddrResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getLastEventNonceByAddrMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void lastEventBlockHeightByAddr(fx.gravity.v1.QueryOuterClass.QueryLastEventBlockHeightByAddrRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.v1.QueryOuterClass.QueryLastEventBlockHeightByAddrResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getLastEventBlockHeightByAddrMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void batchFees(fx.gravity.v1.QueryOuterClass.QueryBatchFeeRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.v1.QueryOuterClass.QueryBatchFeeResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getBatchFeesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void lastObservedBlockHeight(fx.gravity.v1.QueryOuterClass.QueryLastObservedBlockHeightRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.v1.QueryOuterClass.QueryLastObservedBlockHeightResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getLastObservedBlockHeightMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void outgoingTxBatches(fx.gravity.v1.QueryOuterClass.QueryOutgoingTxBatchesRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.v1.QueryOuterClass.QueryOutgoingTxBatchesResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getOutgoingTxBatchesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void batchRequestByNonce(fx.gravity.v1.QueryOuterClass.QueryBatchRequestByNonceRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.v1.QueryOuterClass.QueryBatchRequestByNonceResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getBatchRequestByNonceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void batchConfirm(fx.gravity.v1.QueryOuterClass.QueryBatchConfirmRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.v1.QueryOuterClass.QueryBatchConfirmResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getBatchConfirmMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void batchConfirms(fx.gravity.v1.QueryOuterClass.QueryBatchConfirmsRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.v1.QueryOuterClass.QueryBatchConfirmsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getBatchConfirmsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void eRC20ToDenom(fx.gravity.v1.QueryOuterClass.QueryERC20ToDenomRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.v1.QueryOuterClass.QueryERC20ToDenomResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getERC20ToDenomMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void denomToERC20(fx.gravity.v1.QueryOuterClass.QueryDenomToERC20Request request,
        io.grpc.stub.StreamObserver<fx.gravity.v1.QueryOuterClass.QueryDenomToERC20Response> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDenomToERC20Method(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getDelegateKeyByValidator(fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByValidatorRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByValidatorResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetDelegateKeyByValidatorMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getDelegateKeyByEth(fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByEthRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByEthResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetDelegateKeyByEthMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getDelegateKeyByOrchestrator(fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByOrchestratorRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByOrchestratorResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetDelegateKeyByOrchestratorMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getPendingSendToEth(fx.gravity.v1.QueryOuterClass.QueryPendingSendToEthRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.v1.QueryOuterClass.QueryPendingSendToEthResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetPendingSendToEthMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getIbcSequenceHeightByChannel(fx.gravity.v1.QueryOuterClass.QueryIbcSequenceHeightRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.v1.QueryOuterClass.QueryIbcSequenceHeightResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetIbcSequenceHeightByChannelMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void projectedBatchTimeoutHeight(fx.gravity.v1.QueryOuterClass.QueryProjectedBatchTimeoutHeightRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.v1.QueryOuterClass.QueryProjectedBatchTimeoutHeightResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getProjectedBatchTimeoutHeightMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void bridgeTokens(fx.gravity.v1.QueryOuterClass.QueryBridgeTokensRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.v1.QueryOuterClass.QueryBridgeTokensResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getBridgeTokensMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service
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
     * Deployments queries deployments
     * </pre>
     */
    public fx.gravity.v1.QueryOuterClass.QueryParamsResponse params(fx.gravity.v1.QueryOuterClass.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
    }

    /**
     */
    public fx.gravity.v1.QueryOuterClass.QueryCurrentValsetResponse currentValset(fx.gravity.v1.QueryOuterClass.QueryCurrentValsetRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCurrentValsetMethod(), getCallOptions(), request);
    }

    /**
     */
    public fx.gravity.v1.QueryOuterClass.QueryValsetRequestResponse valsetRequest(fx.gravity.v1.QueryOuterClass.QueryValsetRequestRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getValsetRequestMethod(), getCallOptions(), request);
    }

    /**
     */
    public fx.gravity.v1.QueryOuterClass.QueryValsetConfirmResponse valsetConfirm(fx.gravity.v1.QueryOuterClass.QueryValsetConfirmRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getValsetConfirmMethod(), getCallOptions(), request);
    }

    /**
     */
    public fx.gravity.v1.QueryOuterClass.QueryValsetConfirmsByNonceResponse valsetConfirmsByNonce(fx.gravity.v1.QueryOuterClass.QueryValsetConfirmsByNonceRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getValsetConfirmsByNonceMethod(), getCallOptions(), request);
    }

    /**
     */
    public fx.gravity.v1.QueryOuterClass.QueryLastValsetRequestsResponse lastValsetRequests(fx.gravity.v1.QueryOuterClass.QueryLastValsetRequestsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getLastValsetRequestsMethod(), getCallOptions(), request);
    }

    /**
     */
    public fx.gravity.v1.QueryOuterClass.QueryLastPendingValsetRequestByAddrResponse lastPendingValsetRequestByAddr(fx.gravity.v1.QueryOuterClass.QueryLastPendingValsetRequestByAddrRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getLastPendingValsetRequestByAddrMethod(), getCallOptions(), request);
    }

    /**
     */
    public fx.gravity.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrResponse lastPendingBatchRequestByAddr(fx.gravity.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getLastPendingBatchRequestByAddrMethod(), getCallOptions(), request);
    }

    /**
     */
    public fx.gravity.v1.QueryOuterClass.QueryLastEventNonceByAddrResponse lastEventNonceByAddr(fx.gravity.v1.QueryOuterClass.QueryLastEventNonceByAddrRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getLastEventNonceByAddrMethod(), getCallOptions(), request);
    }

    /**
     */
    public fx.gravity.v1.QueryOuterClass.QueryLastEventBlockHeightByAddrResponse lastEventBlockHeightByAddr(fx.gravity.v1.QueryOuterClass.QueryLastEventBlockHeightByAddrRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getLastEventBlockHeightByAddrMethod(), getCallOptions(), request);
    }

    /**
     */
    public fx.gravity.v1.QueryOuterClass.QueryBatchFeeResponse batchFees(fx.gravity.v1.QueryOuterClass.QueryBatchFeeRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getBatchFeesMethod(), getCallOptions(), request);
    }

    /**
     */
    public fx.gravity.v1.QueryOuterClass.QueryLastObservedBlockHeightResponse lastObservedBlockHeight(fx.gravity.v1.QueryOuterClass.QueryLastObservedBlockHeightRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getLastObservedBlockHeightMethod(), getCallOptions(), request);
    }

    /**
     */
    public fx.gravity.v1.QueryOuterClass.QueryOutgoingTxBatchesResponse outgoingTxBatches(fx.gravity.v1.QueryOuterClass.QueryOutgoingTxBatchesRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getOutgoingTxBatchesMethod(), getCallOptions(), request);
    }

    /**
     */
    public fx.gravity.v1.QueryOuterClass.QueryBatchRequestByNonceResponse batchRequestByNonce(fx.gravity.v1.QueryOuterClass.QueryBatchRequestByNonceRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getBatchRequestByNonceMethod(), getCallOptions(), request);
    }

    /**
     */
    public fx.gravity.v1.QueryOuterClass.QueryBatchConfirmResponse batchConfirm(fx.gravity.v1.QueryOuterClass.QueryBatchConfirmRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getBatchConfirmMethod(), getCallOptions(), request);
    }

    /**
     */
    public fx.gravity.v1.QueryOuterClass.QueryBatchConfirmsResponse batchConfirms(fx.gravity.v1.QueryOuterClass.QueryBatchConfirmsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getBatchConfirmsMethod(), getCallOptions(), request);
    }

    /**
     */
    public fx.gravity.v1.QueryOuterClass.QueryERC20ToDenomResponse eRC20ToDenom(fx.gravity.v1.QueryOuterClass.QueryERC20ToDenomRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getERC20ToDenomMethod(), getCallOptions(), request);
    }

    /**
     */
    public fx.gravity.v1.QueryOuterClass.QueryDenomToERC20Response denomToERC20(fx.gravity.v1.QueryOuterClass.QueryDenomToERC20Request request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDenomToERC20Method(), getCallOptions(), request);
    }

    /**
     */
    public fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByValidatorResponse getDelegateKeyByValidator(fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByValidatorRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetDelegateKeyByValidatorMethod(), getCallOptions(), request);
    }

    /**
     */
    public fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByEthResponse getDelegateKeyByEth(fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByEthRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetDelegateKeyByEthMethod(), getCallOptions(), request);
    }

    /**
     */
    public fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByOrchestratorResponse getDelegateKeyByOrchestrator(fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByOrchestratorRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetDelegateKeyByOrchestratorMethod(), getCallOptions(), request);
    }

    /**
     */
    public fx.gravity.v1.QueryOuterClass.QueryPendingSendToEthResponse getPendingSendToEth(fx.gravity.v1.QueryOuterClass.QueryPendingSendToEthRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetPendingSendToEthMethod(), getCallOptions(), request);
    }

    /**
     */
    public fx.gravity.v1.QueryOuterClass.QueryIbcSequenceHeightResponse getIbcSequenceHeightByChannel(fx.gravity.v1.QueryOuterClass.QueryIbcSequenceHeightRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetIbcSequenceHeightByChannelMethod(), getCallOptions(), request);
    }

    /**
     */
    public fx.gravity.v1.QueryOuterClass.QueryProjectedBatchTimeoutHeightResponse projectedBatchTimeoutHeight(fx.gravity.v1.QueryOuterClass.QueryProjectedBatchTimeoutHeightRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getProjectedBatchTimeoutHeightMethod(), getCallOptions(), request);
    }

    /**
     */
    public fx.gravity.v1.QueryOuterClass.QueryBridgeTokensResponse bridgeTokens(fx.gravity.v1.QueryOuterClass.QueryBridgeTokensRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getBridgeTokensMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service
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
     * Deployments queries deployments
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryParamsResponse> params(
        fx.gravity.v1.QueryOuterClass.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryCurrentValsetResponse> currentValset(
        fx.gravity.v1.QueryOuterClass.QueryCurrentValsetRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCurrentValsetMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryValsetRequestResponse> valsetRequest(
        fx.gravity.v1.QueryOuterClass.QueryValsetRequestRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getValsetRequestMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryValsetConfirmResponse> valsetConfirm(
        fx.gravity.v1.QueryOuterClass.QueryValsetConfirmRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getValsetConfirmMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryValsetConfirmsByNonceResponse> valsetConfirmsByNonce(
        fx.gravity.v1.QueryOuterClass.QueryValsetConfirmsByNonceRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getValsetConfirmsByNonceMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryLastValsetRequestsResponse> lastValsetRequests(
        fx.gravity.v1.QueryOuterClass.QueryLastValsetRequestsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getLastValsetRequestsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryLastPendingValsetRequestByAddrResponse> lastPendingValsetRequestByAddr(
        fx.gravity.v1.QueryOuterClass.QueryLastPendingValsetRequestByAddrRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getLastPendingValsetRequestByAddrMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrResponse> lastPendingBatchRequestByAddr(
        fx.gravity.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getLastPendingBatchRequestByAddrMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryLastEventNonceByAddrResponse> lastEventNonceByAddr(
        fx.gravity.v1.QueryOuterClass.QueryLastEventNonceByAddrRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getLastEventNonceByAddrMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryLastEventBlockHeightByAddrResponse> lastEventBlockHeightByAddr(
        fx.gravity.v1.QueryOuterClass.QueryLastEventBlockHeightByAddrRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getLastEventBlockHeightByAddrMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryBatchFeeResponse> batchFees(
        fx.gravity.v1.QueryOuterClass.QueryBatchFeeRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getBatchFeesMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryLastObservedBlockHeightResponse> lastObservedBlockHeight(
        fx.gravity.v1.QueryOuterClass.QueryLastObservedBlockHeightRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getLastObservedBlockHeightMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryOutgoingTxBatchesResponse> outgoingTxBatches(
        fx.gravity.v1.QueryOuterClass.QueryOutgoingTxBatchesRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getOutgoingTxBatchesMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryBatchRequestByNonceResponse> batchRequestByNonce(
        fx.gravity.v1.QueryOuterClass.QueryBatchRequestByNonceRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getBatchRequestByNonceMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryBatchConfirmResponse> batchConfirm(
        fx.gravity.v1.QueryOuterClass.QueryBatchConfirmRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getBatchConfirmMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryBatchConfirmsResponse> batchConfirms(
        fx.gravity.v1.QueryOuterClass.QueryBatchConfirmsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getBatchConfirmsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryERC20ToDenomResponse> eRC20ToDenom(
        fx.gravity.v1.QueryOuterClass.QueryERC20ToDenomRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getERC20ToDenomMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryDenomToERC20Response> denomToERC20(
        fx.gravity.v1.QueryOuterClass.QueryDenomToERC20Request request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDenomToERC20Method(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByValidatorResponse> getDelegateKeyByValidator(
        fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByValidatorRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetDelegateKeyByValidatorMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByEthResponse> getDelegateKeyByEth(
        fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByEthRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetDelegateKeyByEthMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByOrchestratorResponse> getDelegateKeyByOrchestrator(
        fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByOrchestratorRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetDelegateKeyByOrchestratorMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryPendingSendToEthResponse> getPendingSendToEth(
        fx.gravity.v1.QueryOuterClass.QueryPendingSendToEthRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetPendingSendToEthMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryIbcSequenceHeightResponse> getIbcSequenceHeightByChannel(
        fx.gravity.v1.QueryOuterClass.QueryIbcSequenceHeightRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetIbcSequenceHeightByChannelMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryProjectedBatchTimeoutHeightResponse> projectedBatchTimeoutHeight(
        fx.gravity.v1.QueryOuterClass.QueryProjectedBatchTimeoutHeightRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getProjectedBatchTimeoutHeightMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryBridgeTokensResponse> bridgeTokens(
        fx.gravity.v1.QueryOuterClass.QueryBridgeTokensRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getBridgeTokensMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PARAMS = 0;
  private static final int METHODID_CURRENT_VALSET = 1;
  private static final int METHODID_VALSET_REQUEST = 2;
  private static final int METHODID_VALSET_CONFIRM = 3;
  private static final int METHODID_VALSET_CONFIRMS_BY_NONCE = 4;
  private static final int METHODID_LAST_VALSET_REQUESTS = 5;
  private static final int METHODID_LAST_PENDING_VALSET_REQUEST_BY_ADDR = 6;
  private static final int METHODID_LAST_PENDING_BATCH_REQUEST_BY_ADDR = 7;
  private static final int METHODID_LAST_EVENT_NONCE_BY_ADDR = 8;
  private static final int METHODID_LAST_EVENT_BLOCK_HEIGHT_BY_ADDR = 9;
  private static final int METHODID_BATCH_FEES = 10;
  private static final int METHODID_LAST_OBSERVED_BLOCK_HEIGHT = 11;
  private static final int METHODID_OUTGOING_TX_BATCHES = 12;
  private static final int METHODID_BATCH_REQUEST_BY_NONCE = 13;
  private static final int METHODID_BATCH_CONFIRM = 14;
  private static final int METHODID_BATCH_CONFIRMS = 15;
  private static final int METHODID_ERC20TO_DENOM = 16;
  private static final int METHODID_DENOM_TO_ERC20 = 17;
  private static final int METHODID_GET_DELEGATE_KEY_BY_VALIDATOR = 18;
  private static final int METHODID_GET_DELEGATE_KEY_BY_ETH = 19;
  private static final int METHODID_GET_DELEGATE_KEY_BY_ORCHESTRATOR = 20;
  private static final int METHODID_GET_PENDING_SEND_TO_ETH = 21;
  private static final int METHODID_GET_IBC_SEQUENCE_HEIGHT_BY_CHANNEL = 22;
  private static final int METHODID_PROJECTED_BATCH_TIMEOUT_HEIGHT = 23;
  private static final int METHODID_BRIDGE_TOKENS = 24;

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
        case METHODID_PARAMS:
          serviceImpl.params((fx.gravity.v1.QueryOuterClass.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<fx.gravity.v1.QueryOuterClass.QueryParamsResponse>) responseObserver);
          break;
        case METHODID_CURRENT_VALSET:
          serviceImpl.currentValset((fx.gravity.v1.QueryOuterClass.QueryCurrentValsetRequest) request,
              (io.grpc.stub.StreamObserver<fx.gravity.v1.QueryOuterClass.QueryCurrentValsetResponse>) responseObserver);
          break;
        case METHODID_VALSET_REQUEST:
          serviceImpl.valsetRequest((fx.gravity.v1.QueryOuterClass.QueryValsetRequestRequest) request,
              (io.grpc.stub.StreamObserver<fx.gravity.v1.QueryOuterClass.QueryValsetRequestResponse>) responseObserver);
          break;
        case METHODID_VALSET_CONFIRM:
          serviceImpl.valsetConfirm((fx.gravity.v1.QueryOuterClass.QueryValsetConfirmRequest) request,
              (io.grpc.stub.StreamObserver<fx.gravity.v1.QueryOuterClass.QueryValsetConfirmResponse>) responseObserver);
          break;
        case METHODID_VALSET_CONFIRMS_BY_NONCE:
          serviceImpl.valsetConfirmsByNonce((fx.gravity.v1.QueryOuterClass.QueryValsetConfirmsByNonceRequest) request,
              (io.grpc.stub.StreamObserver<fx.gravity.v1.QueryOuterClass.QueryValsetConfirmsByNonceResponse>) responseObserver);
          break;
        case METHODID_LAST_VALSET_REQUESTS:
          serviceImpl.lastValsetRequests((fx.gravity.v1.QueryOuterClass.QueryLastValsetRequestsRequest) request,
              (io.grpc.stub.StreamObserver<fx.gravity.v1.QueryOuterClass.QueryLastValsetRequestsResponse>) responseObserver);
          break;
        case METHODID_LAST_PENDING_VALSET_REQUEST_BY_ADDR:
          serviceImpl.lastPendingValsetRequestByAddr((fx.gravity.v1.QueryOuterClass.QueryLastPendingValsetRequestByAddrRequest) request,
              (io.grpc.stub.StreamObserver<fx.gravity.v1.QueryOuterClass.QueryLastPendingValsetRequestByAddrResponse>) responseObserver);
          break;
        case METHODID_LAST_PENDING_BATCH_REQUEST_BY_ADDR:
          serviceImpl.lastPendingBatchRequestByAddr((fx.gravity.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrRequest) request,
              (io.grpc.stub.StreamObserver<fx.gravity.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrResponse>) responseObserver);
          break;
        case METHODID_LAST_EVENT_NONCE_BY_ADDR:
          serviceImpl.lastEventNonceByAddr((fx.gravity.v1.QueryOuterClass.QueryLastEventNonceByAddrRequest) request,
              (io.grpc.stub.StreamObserver<fx.gravity.v1.QueryOuterClass.QueryLastEventNonceByAddrResponse>) responseObserver);
          break;
        case METHODID_LAST_EVENT_BLOCK_HEIGHT_BY_ADDR:
          serviceImpl.lastEventBlockHeightByAddr((fx.gravity.v1.QueryOuterClass.QueryLastEventBlockHeightByAddrRequest) request,
              (io.grpc.stub.StreamObserver<fx.gravity.v1.QueryOuterClass.QueryLastEventBlockHeightByAddrResponse>) responseObserver);
          break;
        case METHODID_BATCH_FEES:
          serviceImpl.batchFees((fx.gravity.v1.QueryOuterClass.QueryBatchFeeRequest) request,
              (io.grpc.stub.StreamObserver<fx.gravity.v1.QueryOuterClass.QueryBatchFeeResponse>) responseObserver);
          break;
        case METHODID_LAST_OBSERVED_BLOCK_HEIGHT:
          serviceImpl.lastObservedBlockHeight((fx.gravity.v1.QueryOuterClass.QueryLastObservedBlockHeightRequest) request,
              (io.grpc.stub.StreamObserver<fx.gravity.v1.QueryOuterClass.QueryLastObservedBlockHeightResponse>) responseObserver);
          break;
        case METHODID_OUTGOING_TX_BATCHES:
          serviceImpl.outgoingTxBatches((fx.gravity.v1.QueryOuterClass.QueryOutgoingTxBatchesRequest) request,
              (io.grpc.stub.StreamObserver<fx.gravity.v1.QueryOuterClass.QueryOutgoingTxBatchesResponse>) responseObserver);
          break;
        case METHODID_BATCH_REQUEST_BY_NONCE:
          serviceImpl.batchRequestByNonce((fx.gravity.v1.QueryOuterClass.QueryBatchRequestByNonceRequest) request,
              (io.grpc.stub.StreamObserver<fx.gravity.v1.QueryOuterClass.QueryBatchRequestByNonceResponse>) responseObserver);
          break;
        case METHODID_BATCH_CONFIRM:
          serviceImpl.batchConfirm((fx.gravity.v1.QueryOuterClass.QueryBatchConfirmRequest) request,
              (io.grpc.stub.StreamObserver<fx.gravity.v1.QueryOuterClass.QueryBatchConfirmResponse>) responseObserver);
          break;
        case METHODID_BATCH_CONFIRMS:
          serviceImpl.batchConfirms((fx.gravity.v1.QueryOuterClass.QueryBatchConfirmsRequest) request,
              (io.grpc.stub.StreamObserver<fx.gravity.v1.QueryOuterClass.QueryBatchConfirmsResponse>) responseObserver);
          break;
        case METHODID_ERC20TO_DENOM:
          serviceImpl.eRC20ToDenom((fx.gravity.v1.QueryOuterClass.QueryERC20ToDenomRequest) request,
              (io.grpc.stub.StreamObserver<fx.gravity.v1.QueryOuterClass.QueryERC20ToDenomResponse>) responseObserver);
          break;
        case METHODID_DENOM_TO_ERC20:
          serviceImpl.denomToERC20((fx.gravity.v1.QueryOuterClass.QueryDenomToERC20Request) request,
              (io.grpc.stub.StreamObserver<fx.gravity.v1.QueryOuterClass.QueryDenomToERC20Response>) responseObserver);
          break;
        case METHODID_GET_DELEGATE_KEY_BY_VALIDATOR:
          serviceImpl.getDelegateKeyByValidator((fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByValidatorRequest) request,
              (io.grpc.stub.StreamObserver<fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByValidatorResponse>) responseObserver);
          break;
        case METHODID_GET_DELEGATE_KEY_BY_ETH:
          serviceImpl.getDelegateKeyByEth((fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByEthRequest) request,
              (io.grpc.stub.StreamObserver<fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByEthResponse>) responseObserver);
          break;
        case METHODID_GET_DELEGATE_KEY_BY_ORCHESTRATOR:
          serviceImpl.getDelegateKeyByOrchestrator((fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByOrchestratorRequest) request,
              (io.grpc.stub.StreamObserver<fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByOrchestratorResponse>) responseObserver);
          break;
        case METHODID_GET_PENDING_SEND_TO_ETH:
          serviceImpl.getPendingSendToEth((fx.gravity.v1.QueryOuterClass.QueryPendingSendToEthRequest) request,
              (io.grpc.stub.StreamObserver<fx.gravity.v1.QueryOuterClass.QueryPendingSendToEthResponse>) responseObserver);
          break;
        case METHODID_GET_IBC_SEQUENCE_HEIGHT_BY_CHANNEL:
          serviceImpl.getIbcSequenceHeightByChannel((fx.gravity.v1.QueryOuterClass.QueryIbcSequenceHeightRequest) request,
              (io.grpc.stub.StreamObserver<fx.gravity.v1.QueryOuterClass.QueryIbcSequenceHeightResponse>) responseObserver);
          break;
        case METHODID_PROJECTED_BATCH_TIMEOUT_HEIGHT:
          serviceImpl.projectedBatchTimeoutHeight((fx.gravity.v1.QueryOuterClass.QueryProjectedBatchTimeoutHeightRequest) request,
              (io.grpc.stub.StreamObserver<fx.gravity.v1.QueryOuterClass.QueryProjectedBatchTimeoutHeightResponse>) responseObserver);
          break;
        case METHODID_BRIDGE_TOKENS:
          serviceImpl.bridgeTokens((fx.gravity.v1.QueryOuterClass.QueryBridgeTokensRequest) request,
              (io.grpc.stub.StreamObserver<fx.gravity.v1.QueryOuterClass.QueryBridgeTokensResponse>) responseObserver);
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
      return fx.gravity.v1.QueryOuterClass.getDescriptor();
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
              .addMethod(getParamsMethod())
              .addMethod(getCurrentValsetMethod())
              .addMethod(getValsetRequestMethod())
              .addMethod(getValsetConfirmMethod())
              .addMethod(getValsetConfirmsByNonceMethod())
              .addMethod(getLastValsetRequestsMethod())
              .addMethod(getLastPendingValsetRequestByAddrMethod())
              .addMethod(getLastPendingBatchRequestByAddrMethod())
              .addMethod(getLastEventNonceByAddrMethod())
              .addMethod(getLastEventBlockHeightByAddrMethod())
              .addMethod(getBatchFeesMethod())
              .addMethod(getLastObservedBlockHeightMethod())
              .addMethod(getOutgoingTxBatchesMethod())
              .addMethod(getBatchRequestByNonceMethod())
              .addMethod(getBatchConfirmMethod())
              .addMethod(getBatchConfirmsMethod())
              .addMethod(getERC20ToDenomMethod())
              .addMethod(getDenomToERC20Method())
              .addMethod(getGetDelegateKeyByValidatorMethod())
              .addMethod(getGetDelegateKeyByEthMethod())
              .addMethod(getGetDelegateKeyByOrchestratorMethod())
              .addMethod(getGetPendingSendToEthMethod())
              .addMethod(getGetIbcSequenceHeightByChannelMethod())
              .addMethod(getProjectedBatchTimeoutHeightMethod())
              .addMethod(getBridgeTokensMethod())
              .build();
        }
      }
    }
    return result;
  }
}
