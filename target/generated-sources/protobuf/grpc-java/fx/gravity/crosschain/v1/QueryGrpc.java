package fx.gravity.crosschain.v1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Query defines the gRPC querier service
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.44.1)",
    comments = "Source: fx/crosschain/v1/query.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "fx.gravity.crosschain.v1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.QueryOuterClass.QueryParamsRequest,
      fx.gravity.crosschain.v1.QueryOuterClass.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = fx.gravity.crosschain.v1.QueryOuterClass.QueryParamsRequest.class,
      responseType = fx.gravity.crosschain.v1.QueryOuterClass.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.QueryOuterClass.QueryParamsRequest,
      fx.gravity.crosschain.v1.QueryOuterClass.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.QueryOuterClass.QueryParamsRequest, fx.gravity.crosschain.v1.QueryOuterClass.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<fx.gravity.crosschain.v1.QueryOuterClass.QueryParamsRequest, fx.gravity.crosschain.v1.QueryOuterClass.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.crosschain.v1.QueryOuterClass.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.crosschain.v1.QueryOuterClass.QueryParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Params"))
              .build();
        }
      }
    }
    return getParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.QueryOuterClass.QueryCurrentOracleSetRequest,
      fx.gravity.crosschain.v1.QueryOuterClass.QueryCurrentOracleSetResponse> getCurrentOracleSetMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CurrentOracleSet",
      requestType = fx.gravity.crosschain.v1.QueryOuterClass.QueryCurrentOracleSetRequest.class,
      responseType = fx.gravity.crosschain.v1.QueryOuterClass.QueryCurrentOracleSetResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.QueryOuterClass.QueryCurrentOracleSetRequest,
      fx.gravity.crosschain.v1.QueryOuterClass.QueryCurrentOracleSetResponse> getCurrentOracleSetMethod() {
    io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.QueryOuterClass.QueryCurrentOracleSetRequest, fx.gravity.crosschain.v1.QueryOuterClass.QueryCurrentOracleSetResponse> getCurrentOracleSetMethod;
    if ((getCurrentOracleSetMethod = QueryGrpc.getCurrentOracleSetMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getCurrentOracleSetMethod = QueryGrpc.getCurrentOracleSetMethod) == null) {
          QueryGrpc.getCurrentOracleSetMethod = getCurrentOracleSetMethod =
              io.grpc.MethodDescriptor.<fx.gravity.crosschain.v1.QueryOuterClass.QueryCurrentOracleSetRequest, fx.gravity.crosschain.v1.QueryOuterClass.QueryCurrentOracleSetResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CurrentOracleSet"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.crosschain.v1.QueryOuterClass.QueryCurrentOracleSetRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.crosschain.v1.QueryOuterClass.QueryCurrentOracleSetResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("CurrentOracleSet"))
              .build();
        }
      }
    }
    return getCurrentOracleSetMethod;
  }

  private static volatile io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetRequestRequest,
      fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetRequestResponse> getOracleSetRequestMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "OracleSetRequest",
      requestType = fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetRequestRequest.class,
      responseType = fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetRequestResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetRequestRequest,
      fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetRequestResponse> getOracleSetRequestMethod() {
    io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetRequestRequest, fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetRequestResponse> getOracleSetRequestMethod;
    if ((getOracleSetRequestMethod = QueryGrpc.getOracleSetRequestMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getOracleSetRequestMethod = QueryGrpc.getOracleSetRequestMethod) == null) {
          QueryGrpc.getOracleSetRequestMethod = getOracleSetRequestMethod =
              io.grpc.MethodDescriptor.<fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetRequestRequest, fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetRequestResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "OracleSetRequest"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetRequestRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetRequestResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("OracleSetRequest"))
              .build();
        }
      }
    }
    return getOracleSetRequestMethod;
  }

  private static volatile io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetConfirmRequest,
      fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetConfirmResponse> getOracleSetConfirmMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "OracleSetConfirm",
      requestType = fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetConfirmRequest.class,
      responseType = fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetConfirmResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetConfirmRequest,
      fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetConfirmResponse> getOracleSetConfirmMethod() {
    io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetConfirmRequest, fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetConfirmResponse> getOracleSetConfirmMethod;
    if ((getOracleSetConfirmMethod = QueryGrpc.getOracleSetConfirmMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getOracleSetConfirmMethod = QueryGrpc.getOracleSetConfirmMethod) == null) {
          QueryGrpc.getOracleSetConfirmMethod = getOracleSetConfirmMethod =
              io.grpc.MethodDescriptor.<fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetConfirmRequest, fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetConfirmResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "OracleSetConfirm"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetConfirmRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetConfirmResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("OracleSetConfirm"))
              .build();
        }
      }
    }
    return getOracleSetConfirmMethod;
  }

  private static volatile io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetConfirmsByNonceRequest,
      fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetConfirmsByNonceResponse> getOracleSetConfirmsByNonceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "OracleSetConfirmsByNonce",
      requestType = fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetConfirmsByNonceRequest.class,
      responseType = fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetConfirmsByNonceResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetConfirmsByNonceRequest,
      fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetConfirmsByNonceResponse> getOracleSetConfirmsByNonceMethod() {
    io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetConfirmsByNonceRequest, fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetConfirmsByNonceResponse> getOracleSetConfirmsByNonceMethod;
    if ((getOracleSetConfirmsByNonceMethod = QueryGrpc.getOracleSetConfirmsByNonceMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getOracleSetConfirmsByNonceMethod = QueryGrpc.getOracleSetConfirmsByNonceMethod) == null) {
          QueryGrpc.getOracleSetConfirmsByNonceMethod = getOracleSetConfirmsByNonceMethod =
              io.grpc.MethodDescriptor.<fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetConfirmsByNonceRequest, fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetConfirmsByNonceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "OracleSetConfirmsByNonce"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetConfirmsByNonceRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetConfirmsByNonceResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("OracleSetConfirmsByNonce"))
              .build();
        }
      }
    }
    return getOracleSetConfirmsByNonceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.QueryOuterClass.QueryLastOracleSetRequestsRequest,
      fx.gravity.crosschain.v1.QueryOuterClass.QueryLastOracleSetRequestsResponse> getLastOracleSetRequestsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "LastOracleSetRequests",
      requestType = fx.gravity.crosschain.v1.QueryOuterClass.QueryLastOracleSetRequestsRequest.class,
      responseType = fx.gravity.crosschain.v1.QueryOuterClass.QueryLastOracleSetRequestsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.QueryOuterClass.QueryLastOracleSetRequestsRequest,
      fx.gravity.crosschain.v1.QueryOuterClass.QueryLastOracleSetRequestsResponse> getLastOracleSetRequestsMethod() {
    io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.QueryOuterClass.QueryLastOracleSetRequestsRequest, fx.gravity.crosschain.v1.QueryOuterClass.QueryLastOracleSetRequestsResponse> getLastOracleSetRequestsMethod;
    if ((getLastOracleSetRequestsMethod = QueryGrpc.getLastOracleSetRequestsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getLastOracleSetRequestsMethod = QueryGrpc.getLastOracleSetRequestsMethod) == null) {
          QueryGrpc.getLastOracleSetRequestsMethod = getLastOracleSetRequestsMethod =
              io.grpc.MethodDescriptor.<fx.gravity.crosschain.v1.QueryOuterClass.QueryLastOracleSetRequestsRequest, fx.gravity.crosschain.v1.QueryOuterClass.QueryLastOracleSetRequestsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "LastOracleSetRequests"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.crosschain.v1.QueryOuterClass.QueryLastOracleSetRequestsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.crosschain.v1.QueryOuterClass.QueryLastOracleSetRequestsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("LastOracleSetRequests"))
              .build();
        }
      }
    }
    return getLastOracleSetRequestsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.QueryOuterClass.QueryLastPendingOracleSetRequestByAddrRequest,
      fx.gravity.crosschain.v1.QueryOuterClass.QueryLastPendingOracleSetRequestByAddrResponse> getLastPendingOracleSetRequestByAddrMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "LastPendingOracleSetRequestByAddr",
      requestType = fx.gravity.crosschain.v1.QueryOuterClass.QueryLastPendingOracleSetRequestByAddrRequest.class,
      responseType = fx.gravity.crosschain.v1.QueryOuterClass.QueryLastPendingOracleSetRequestByAddrResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.QueryOuterClass.QueryLastPendingOracleSetRequestByAddrRequest,
      fx.gravity.crosschain.v1.QueryOuterClass.QueryLastPendingOracleSetRequestByAddrResponse> getLastPendingOracleSetRequestByAddrMethod() {
    io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.QueryOuterClass.QueryLastPendingOracleSetRequestByAddrRequest, fx.gravity.crosschain.v1.QueryOuterClass.QueryLastPendingOracleSetRequestByAddrResponse> getLastPendingOracleSetRequestByAddrMethod;
    if ((getLastPendingOracleSetRequestByAddrMethod = QueryGrpc.getLastPendingOracleSetRequestByAddrMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getLastPendingOracleSetRequestByAddrMethod = QueryGrpc.getLastPendingOracleSetRequestByAddrMethod) == null) {
          QueryGrpc.getLastPendingOracleSetRequestByAddrMethod = getLastPendingOracleSetRequestByAddrMethod =
              io.grpc.MethodDescriptor.<fx.gravity.crosschain.v1.QueryOuterClass.QueryLastPendingOracleSetRequestByAddrRequest, fx.gravity.crosschain.v1.QueryOuterClass.QueryLastPendingOracleSetRequestByAddrResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "LastPendingOracleSetRequestByAddr"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.crosschain.v1.QueryOuterClass.QueryLastPendingOracleSetRequestByAddrRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.crosschain.v1.QueryOuterClass.QueryLastPendingOracleSetRequestByAddrResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("LastPendingOracleSetRequestByAddr"))
              .build();
        }
      }
    }
    return getLastPendingOracleSetRequestByAddrMethod;
  }

  private static volatile io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrRequest,
      fx.gravity.crosschain.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrResponse> getLastPendingBatchRequestByAddrMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "LastPendingBatchRequestByAddr",
      requestType = fx.gravity.crosschain.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrRequest.class,
      responseType = fx.gravity.crosschain.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrRequest,
      fx.gravity.crosschain.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrResponse> getLastPendingBatchRequestByAddrMethod() {
    io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrRequest, fx.gravity.crosschain.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrResponse> getLastPendingBatchRequestByAddrMethod;
    if ((getLastPendingBatchRequestByAddrMethod = QueryGrpc.getLastPendingBatchRequestByAddrMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getLastPendingBatchRequestByAddrMethod = QueryGrpc.getLastPendingBatchRequestByAddrMethod) == null) {
          QueryGrpc.getLastPendingBatchRequestByAddrMethod = getLastPendingBatchRequestByAddrMethod =
              io.grpc.MethodDescriptor.<fx.gravity.crosschain.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrRequest, fx.gravity.crosschain.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "LastPendingBatchRequestByAddr"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.crosschain.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.crosschain.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("LastPendingBatchRequestByAddr"))
              .build();
        }
      }
    }
    return getLastPendingBatchRequestByAddrMethod;
  }

  private static volatile io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.QueryOuterClass.QueryLastEventNonceByAddrRequest,
      fx.gravity.crosschain.v1.QueryOuterClass.QueryLastEventNonceByAddrResponse> getLastEventNonceByAddrMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "LastEventNonceByAddr",
      requestType = fx.gravity.crosschain.v1.QueryOuterClass.QueryLastEventNonceByAddrRequest.class,
      responseType = fx.gravity.crosschain.v1.QueryOuterClass.QueryLastEventNonceByAddrResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.QueryOuterClass.QueryLastEventNonceByAddrRequest,
      fx.gravity.crosschain.v1.QueryOuterClass.QueryLastEventNonceByAddrResponse> getLastEventNonceByAddrMethod() {
    io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.QueryOuterClass.QueryLastEventNonceByAddrRequest, fx.gravity.crosschain.v1.QueryOuterClass.QueryLastEventNonceByAddrResponse> getLastEventNonceByAddrMethod;
    if ((getLastEventNonceByAddrMethod = QueryGrpc.getLastEventNonceByAddrMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getLastEventNonceByAddrMethod = QueryGrpc.getLastEventNonceByAddrMethod) == null) {
          QueryGrpc.getLastEventNonceByAddrMethod = getLastEventNonceByAddrMethod =
              io.grpc.MethodDescriptor.<fx.gravity.crosschain.v1.QueryOuterClass.QueryLastEventNonceByAddrRequest, fx.gravity.crosschain.v1.QueryOuterClass.QueryLastEventNonceByAddrResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "LastEventNonceByAddr"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.crosschain.v1.QueryOuterClass.QueryLastEventNonceByAddrRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.crosschain.v1.QueryOuterClass.QueryLastEventNonceByAddrResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("LastEventNonceByAddr"))
              .build();
        }
      }
    }
    return getLastEventNonceByAddrMethod;
  }

  private static volatile io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.QueryOuterClass.QueryLastEventBlockHeightByAddrRequest,
      fx.gravity.crosschain.v1.QueryOuterClass.QueryLastEventBlockHeightByAddrResponse> getLastEventBlockHeightByAddrMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "LastEventBlockHeightByAddr",
      requestType = fx.gravity.crosschain.v1.QueryOuterClass.QueryLastEventBlockHeightByAddrRequest.class,
      responseType = fx.gravity.crosschain.v1.QueryOuterClass.QueryLastEventBlockHeightByAddrResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.QueryOuterClass.QueryLastEventBlockHeightByAddrRequest,
      fx.gravity.crosschain.v1.QueryOuterClass.QueryLastEventBlockHeightByAddrResponse> getLastEventBlockHeightByAddrMethod() {
    io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.QueryOuterClass.QueryLastEventBlockHeightByAddrRequest, fx.gravity.crosschain.v1.QueryOuterClass.QueryLastEventBlockHeightByAddrResponse> getLastEventBlockHeightByAddrMethod;
    if ((getLastEventBlockHeightByAddrMethod = QueryGrpc.getLastEventBlockHeightByAddrMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getLastEventBlockHeightByAddrMethod = QueryGrpc.getLastEventBlockHeightByAddrMethod) == null) {
          QueryGrpc.getLastEventBlockHeightByAddrMethod = getLastEventBlockHeightByAddrMethod =
              io.grpc.MethodDescriptor.<fx.gravity.crosschain.v1.QueryOuterClass.QueryLastEventBlockHeightByAddrRequest, fx.gravity.crosschain.v1.QueryOuterClass.QueryLastEventBlockHeightByAddrResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "LastEventBlockHeightByAddr"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.crosschain.v1.QueryOuterClass.QueryLastEventBlockHeightByAddrRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.crosschain.v1.QueryOuterClass.QueryLastEventBlockHeightByAddrResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("LastEventBlockHeightByAddr"))
              .build();
        }
      }
    }
    return getLastEventBlockHeightByAddrMethod;
  }

  private static volatile io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchFeeRequest,
      fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchFeeResponse> getBatchFeesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BatchFees",
      requestType = fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchFeeRequest.class,
      responseType = fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchFeeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchFeeRequest,
      fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchFeeResponse> getBatchFeesMethod() {
    io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchFeeRequest, fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchFeeResponse> getBatchFeesMethod;
    if ((getBatchFeesMethod = QueryGrpc.getBatchFeesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getBatchFeesMethod = QueryGrpc.getBatchFeesMethod) == null) {
          QueryGrpc.getBatchFeesMethod = getBatchFeesMethod =
              io.grpc.MethodDescriptor.<fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchFeeRequest, fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchFeeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BatchFees"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchFeeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchFeeResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("BatchFees"))
              .build();
        }
      }
    }
    return getBatchFeesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.QueryOuterClass.QueryLastObservedBlockHeightRequest,
      fx.gravity.crosschain.v1.QueryOuterClass.QueryLastObservedBlockHeightResponse> getLastObservedBlockHeightMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "LastObservedBlockHeight",
      requestType = fx.gravity.crosschain.v1.QueryOuterClass.QueryLastObservedBlockHeightRequest.class,
      responseType = fx.gravity.crosschain.v1.QueryOuterClass.QueryLastObservedBlockHeightResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.QueryOuterClass.QueryLastObservedBlockHeightRequest,
      fx.gravity.crosschain.v1.QueryOuterClass.QueryLastObservedBlockHeightResponse> getLastObservedBlockHeightMethod() {
    io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.QueryOuterClass.QueryLastObservedBlockHeightRequest, fx.gravity.crosschain.v1.QueryOuterClass.QueryLastObservedBlockHeightResponse> getLastObservedBlockHeightMethod;
    if ((getLastObservedBlockHeightMethod = QueryGrpc.getLastObservedBlockHeightMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getLastObservedBlockHeightMethod = QueryGrpc.getLastObservedBlockHeightMethod) == null) {
          QueryGrpc.getLastObservedBlockHeightMethod = getLastObservedBlockHeightMethod =
              io.grpc.MethodDescriptor.<fx.gravity.crosschain.v1.QueryOuterClass.QueryLastObservedBlockHeightRequest, fx.gravity.crosschain.v1.QueryOuterClass.QueryLastObservedBlockHeightResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "LastObservedBlockHeight"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.crosschain.v1.QueryOuterClass.QueryLastObservedBlockHeightRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.crosschain.v1.QueryOuterClass.QueryLastObservedBlockHeightResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("LastObservedBlockHeight"))
              .build();
        }
      }
    }
    return getLastObservedBlockHeightMethod;
  }

  private static volatile io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.QueryOuterClass.QueryOutgoingTxBatchesRequest,
      fx.gravity.crosschain.v1.QueryOuterClass.QueryOutgoingTxBatchesResponse> getOutgoingTxBatchesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "OutgoingTxBatches",
      requestType = fx.gravity.crosschain.v1.QueryOuterClass.QueryOutgoingTxBatchesRequest.class,
      responseType = fx.gravity.crosschain.v1.QueryOuterClass.QueryOutgoingTxBatchesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.QueryOuterClass.QueryOutgoingTxBatchesRequest,
      fx.gravity.crosschain.v1.QueryOuterClass.QueryOutgoingTxBatchesResponse> getOutgoingTxBatchesMethod() {
    io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.QueryOuterClass.QueryOutgoingTxBatchesRequest, fx.gravity.crosschain.v1.QueryOuterClass.QueryOutgoingTxBatchesResponse> getOutgoingTxBatchesMethod;
    if ((getOutgoingTxBatchesMethod = QueryGrpc.getOutgoingTxBatchesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getOutgoingTxBatchesMethod = QueryGrpc.getOutgoingTxBatchesMethod) == null) {
          QueryGrpc.getOutgoingTxBatchesMethod = getOutgoingTxBatchesMethod =
              io.grpc.MethodDescriptor.<fx.gravity.crosschain.v1.QueryOuterClass.QueryOutgoingTxBatchesRequest, fx.gravity.crosschain.v1.QueryOuterClass.QueryOutgoingTxBatchesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "OutgoingTxBatches"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.crosschain.v1.QueryOuterClass.QueryOutgoingTxBatchesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.crosschain.v1.QueryOuterClass.QueryOutgoingTxBatchesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("OutgoingTxBatches"))
              .build();
        }
      }
    }
    return getOutgoingTxBatchesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchRequestByNonceRequest,
      fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchRequestByNonceResponse> getBatchRequestByNonceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BatchRequestByNonce",
      requestType = fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchRequestByNonceRequest.class,
      responseType = fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchRequestByNonceResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchRequestByNonceRequest,
      fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchRequestByNonceResponse> getBatchRequestByNonceMethod() {
    io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchRequestByNonceRequest, fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchRequestByNonceResponse> getBatchRequestByNonceMethod;
    if ((getBatchRequestByNonceMethod = QueryGrpc.getBatchRequestByNonceMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getBatchRequestByNonceMethod = QueryGrpc.getBatchRequestByNonceMethod) == null) {
          QueryGrpc.getBatchRequestByNonceMethod = getBatchRequestByNonceMethod =
              io.grpc.MethodDescriptor.<fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchRequestByNonceRequest, fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchRequestByNonceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BatchRequestByNonce"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchRequestByNonceRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchRequestByNonceResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("BatchRequestByNonce"))
              .build();
        }
      }
    }
    return getBatchRequestByNonceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchConfirmRequest,
      fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchConfirmResponse> getBatchConfirmMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BatchConfirm",
      requestType = fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchConfirmRequest.class,
      responseType = fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchConfirmResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchConfirmRequest,
      fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchConfirmResponse> getBatchConfirmMethod() {
    io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchConfirmRequest, fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchConfirmResponse> getBatchConfirmMethod;
    if ((getBatchConfirmMethod = QueryGrpc.getBatchConfirmMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getBatchConfirmMethod = QueryGrpc.getBatchConfirmMethod) == null) {
          QueryGrpc.getBatchConfirmMethod = getBatchConfirmMethod =
              io.grpc.MethodDescriptor.<fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchConfirmRequest, fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchConfirmResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BatchConfirm"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchConfirmRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchConfirmResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("BatchConfirm"))
              .build();
        }
      }
    }
    return getBatchConfirmMethod;
  }

  private static volatile io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchConfirmsRequest,
      fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchConfirmsResponse> getBatchConfirmsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BatchConfirms",
      requestType = fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchConfirmsRequest.class,
      responseType = fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchConfirmsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchConfirmsRequest,
      fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchConfirmsResponse> getBatchConfirmsMethod() {
    io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchConfirmsRequest, fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchConfirmsResponse> getBatchConfirmsMethod;
    if ((getBatchConfirmsMethod = QueryGrpc.getBatchConfirmsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getBatchConfirmsMethod = QueryGrpc.getBatchConfirmsMethod) == null) {
          QueryGrpc.getBatchConfirmsMethod = getBatchConfirmsMethod =
              io.grpc.MethodDescriptor.<fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchConfirmsRequest, fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchConfirmsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BatchConfirms"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchConfirmsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchConfirmsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("BatchConfirms"))
              .build();
        }
      }
    }
    return getBatchConfirmsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.QueryOuterClass.QueryTokenToDenomRequest,
      fx.gravity.crosschain.v1.QueryOuterClass.QueryTokenToDenomResponse> getTokenToDenomMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "TokenToDenom",
      requestType = fx.gravity.crosschain.v1.QueryOuterClass.QueryTokenToDenomRequest.class,
      responseType = fx.gravity.crosschain.v1.QueryOuterClass.QueryTokenToDenomResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.QueryOuterClass.QueryTokenToDenomRequest,
      fx.gravity.crosschain.v1.QueryOuterClass.QueryTokenToDenomResponse> getTokenToDenomMethod() {
    io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.QueryOuterClass.QueryTokenToDenomRequest, fx.gravity.crosschain.v1.QueryOuterClass.QueryTokenToDenomResponse> getTokenToDenomMethod;
    if ((getTokenToDenomMethod = QueryGrpc.getTokenToDenomMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getTokenToDenomMethod = QueryGrpc.getTokenToDenomMethod) == null) {
          QueryGrpc.getTokenToDenomMethod = getTokenToDenomMethod =
              io.grpc.MethodDescriptor.<fx.gravity.crosschain.v1.QueryOuterClass.QueryTokenToDenomRequest, fx.gravity.crosschain.v1.QueryOuterClass.QueryTokenToDenomResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "TokenToDenom"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.crosschain.v1.QueryOuterClass.QueryTokenToDenomRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.crosschain.v1.QueryOuterClass.QueryTokenToDenomResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("TokenToDenom"))
              .build();
        }
      }
    }
    return getTokenToDenomMethod;
  }

  private static volatile io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.QueryOuterClass.QueryDenomToTokenRequest,
      fx.gravity.crosschain.v1.QueryOuterClass.QueryDenomToTokenResponse> getDenomToTokenMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DenomToToken",
      requestType = fx.gravity.crosschain.v1.QueryOuterClass.QueryDenomToTokenRequest.class,
      responseType = fx.gravity.crosschain.v1.QueryOuterClass.QueryDenomToTokenResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.QueryOuterClass.QueryDenomToTokenRequest,
      fx.gravity.crosschain.v1.QueryOuterClass.QueryDenomToTokenResponse> getDenomToTokenMethod() {
    io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.QueryOuterClass.QueryDenomToTokenRequest, fx.gravity.crosschain.v1.QueryOuterClass.QueryDenomToTokenResponse> getDenomToTokenMethod;
    if ((getDenomToTokenMethod = QueryGrpc.getDenomToTokenMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getDenomToTokenMethod = QueryGrpc.getDenomToTokenMethod) == null) {
          QueryGrpc.getDenomToTokenMethod = getDenomToTokenMethod =
              io.grpc.MethodDescriptor.<fx.gravity.crosschain.v1.QueryOuterClass.QueryDenomToTokenRequest, fx.gravity.crosschain.v1.QueryOuterClass.QueryDenomToTokenResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DenomToToken"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.crosschain.v1.QueryOuterClass.QueryDenomToTokenRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.crosschain.v1.QueryOuterClass.QueryDenomToTokenResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("DenomToToken"))
              .build();
        }
      }
    }
    return getDenomToTokenMethod;
  }

  private static volatile io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleByAddrRequest,
      fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleResponse> getGetOracleByAddrMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetOracleByAddr",
      requestType = fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleByAddrRequest.class,
      responseType = fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleByAddrRequest,
      fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleResponse> getGetOracleByAddrMethod() {
    io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleByAddrRequest, fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleResponse> getGetOracleByAddrMethod;
    if ((getGetOracleByAddrMethod = QueryGrpc.getGetOracleByAddrMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getGetOracleByAddrMethod = QueryGrpc.getGetOracleByAddrMethod) == null) {
          QueryGrpc.getGetOracleByAddrMethod = getGetOracleByAddrMethod =
              io.grpc.MethodDescriptor.<fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleByAddrRequest, fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetOracleByAddr"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleByAddrRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("GetOracleByAddr"))
              .build();
        }
      }
    }
    return getGetOracleByAddrMethod;
  }

  private static volatile io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleByExternalAddrRequest,
      fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleResponse> getGetOracleByExternalAddrMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetOracleByExternalAddr",
      requestType = fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleByExternalAddrRequest.class,
      responseType = fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleByExternalAddrRequest,
      fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleResponse> getGetOracleByExternalAddrMethod() {
    io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleByExternalAddrRequest, fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleResponse> getGetOracleByExternalAddrMethod;
    if ((getGetOracleByExternalAddrMethod = QueryGrpc.getGetOracleByExternalAddrMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getGetOracleByExternalAddrMethod = QueryGrpc.getGetOracleByExternalAddrMethod) == null) {
          QueryGrpc.getGetOracleByExternalAddrMethod = getGetOracleByExternalAddrMethod =
              io.grpc.MethodDescriptor.<fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleByExternalAddrRequest, fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetOracleByExternalAddr"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleByExternalAddrRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("GetOracleByExternalAddr"))
              .build();
        }
      }
    }
    return getGetOracleByExternalAddrMethod;
  }

  private static volatile io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleByBridgerAddrRequest,
      fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleResponse> getGetOracleByBridgerAddrMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetOracleByBridgerAddr",
      requestType = fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleByBridgerAddrRequest.class,
      responseType = fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleByBridgerAddrRequest,
      fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleResponse> getGetOracleByBridgerAddrMethod() {
    io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleByBridgerAddrRequest, fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleResponse> getGetOracleByBridgerAddrMethod;
    if ((getGetOracleByBridgerAddrMethod = QueryGrpc.getGetOracleByBridgerAddrMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getGetOracleByBridgerAddrMethod = QueryGrpc.getGetOracleByBridgerAddrMethod) == null) {
          QueryGrpc.getGetOracleByBridgerAddrMethod = getGetOracleByBridgerAddrMethod =
              io.grpc.MethodDescriptor.<fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleByBridgerAddrRequest, fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetOracleByBridgerAddr"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleByBridgerAddrRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("GetOracleByBridgerAddr"))
              .build();
        }
      }
    }
    return getGetOracleByBridgerAddrMethod;
  }

  private static volatile io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.QueryOuterClass.QueryPendingSendToExternalRequest,
      fx.gravity.crosschain.v1.QueryOuterClass.QueryPendingSendToExternalResponse> getGetPendingSendToExternalMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetPendingSendToExternal",
      requestType = fx.gravity.crosschain.v1.QueryOuterClass.QueryPendingSendToExternalRequest.class,
      responseType = fx.gravity.crosschain.v1.QueryOuterClass.QueryPendingSendToExternalResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.QueryOuterClass.QueryPendingSendToExternalRequest,
      fx.gravity.crosschain.v1.QueryOuterClass.QueryPendingSendToExternalResponse> getGetPendingSendToExternalMethod() {
    io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.QueryOuterClass.QueryPendingSendToExternalRequest, fx.gravity.crosschain.v1.QueryOuterClass.QueryPendingSendToExternalResponse> getGetPendingSendToExternalMethod;
    if ((getGetPendingSendToExternalMethod = QueryGrpc.getGetPendingSendToExternalMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getGetPendingSendToExternalMethod = QueryGrpc.getGetPendingSendToExternalMethod) == null) {
          QueryGrpc.getGetPendingSendToExternalMethod = getGetPendingSendToExternalMethod =
              io.grpc.MethodDescriptor.<fx.gravity.crosschain.v1.QueryOuterClass.QueryPendingSendToExternalRequest, fx.gravity.crosschain.v1.QueryOuterClass.QueryPendingSendToExternalResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetPendingSendToExternal"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.crosschain.v1.QueryOuterClass.QueryPendingSendToExternalRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.crosschain.v1.QueryOuterClass.QueryPendingSendToExternalResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("GetPendingSendToExternal"))
              .build();
        }
      }
    }
    return getGetPendingSendToExternalMethod;
  }

  private static volatile io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.QueryOuterClass.QueryOraclesRequest,
      fx.gravity.crosschain.v1.QueryOuterClass.QueryOraclesResponse> getOraclesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Oracles",
      requestType = fx.gravity.crosschain.v1.QueryOuterClass.QueryOraclesRequest.class,
      responseType = fx.gravity.crosschain.v1.QueryOuterClass.QueryOraclesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.QueryOuterClass.QueryOraclesRequest,
      fx.gravity.crosschain.v1.QueryOuterClass.QueryOraclesResponse> getOraclesMethod() {
    io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.QueryOuterClass.QueryOraclesRequest, fx.gravity.crosschain.v1.QueryOuterClass.QueryOraclesResponse> getOraclesMethod;
    if ((getOraclesMethod = QueryGrpc.getOraclesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getOraclesMethod = QueryGrpc.getOraclesMethod) == null) {
          QueryGrpc.getOraclesMethod = getOraclesMethod =
              io.grpc.MethodDescriptor.<fx.gravity.crosschain.v1.QueryOuterClass.QueryOraclesRequest, fx.gravity.crosschain.v1.QueryOuterClass.QueryOraclesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Oracles"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.crosschain.v1.QueryOuterClass.QueryOraclesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.crosschain.v1.QueryOuterClass.QueryOraclesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Oracles"))
              .build();
        }
      }
    }
    return getOraclesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.QueryOuterClass.QueryProjectedBatchTimeoutHeightRequest,
      fx.gravity.crosschain.v1.QueryOuterClass.QueryProjectedBatchTimeoutHeightResponse> getProjectedBatchTimeoutHeightMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ProjectedBatchTimeoutHeight",
      requestType = fx.gravity.crosschain.v1.QueryOuterClass.QueryProjectedBatchTimeoutHeightRequest.class,
      responseType = fx.gravity.crosschain.v1.QueryOuterClass.QueryProjectedBatchTimeoutHeightResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.QueryOuterClass.QueryProjectedBatchTimeoutHeightRequest,
      fx.gravity.crosschain.v1.QueryOuterClass.QueryProjectedBatchTimeoutHeightResponse> getProjectedBatchTimeoutHeightMethod() {
    io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.QueryOuterClass.QueryProjectedBatchTimeoutHeightRequest, fx.gravity.crosschain.v1.QueryOuterClass.QueryProjectedBatchTimeoutHeightResponse> getProjectedBatchTimeoutHeightMethod;
    if ((getProjectedBatchTimeoutHeightMethod = QueryGrpc.getProjectedBatchTimeoutHeightMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getProjectedBatchTimeoutHeightMethod = QueryGrpc.getProjectedBatchTimeoutHeightMethod) == null) {
          QueryGrpc.getProjectedBatchTimeoutHeightMethod = getProjectedBatchTimeoutHeightMethod =
              io.grpc.MethodDescriptor.<fx.gravity.crosschain.v1.QueryOuterClass.QueryProjectedBatchTimeoutHeightRequest, fx.gravity.crosschain.v1.QueryOuterClass.QueryProjectedBatchTimeoutHeightResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ProjectedBatchTimeoutHeight"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.crosschain.v1.QueryOuterClass.QueryProjectedBatchTimeoutHeightRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.crosschain.v1.QueryOuterClass.QueryProjectedBatchTimeoutHeightResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ProjectedBatchTimeoutHeight"))
              .build();
        }
      }
    }
    return getProjectedBatchTimeoutHeightMethod;
  }

  private static volatile io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.QueryOuterClass.QueryBridgeTokensRequest,
      fx.gravity.crosschain.v1.QueryOuterClass.QueryBridgeTokensResponse> getBridgeTokensMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BridgeTokens",
      requestType = fx.gravity.crosschain.v1.QueryOuterClass.QueryBridgeTokensRequest.class,
      responseType = fx.gravity.crosschain.v1.QueryOuterClass.QueryBridgeTokensResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.QueryOuterClass.QueryBridgeTokensRequest,
      fx.gravity.crosschain.v1.QueryOuterClass.QueryBridgeTokensResponse> getBridgeTokensMethod() {
    io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.QueryOuterClass.QueryBridgeTokensRequest, fx.gravity.crosschain.v1.QueryOuterClass.QueryBridgeTokensResponse> getBridgeTokensMethod;
    if ((getBridgeTokensMethod = QueryGrpc.getBridgeTokensMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getBridgeTokensMethod = QueryGrpc.getBridgeTokensMethod) == null) {
          QueryGrpc.getBridgeTokensMethod = getBridgeTokensMethod =
              io.grpc.MethodDescriptor.<fx.gravity.crosschain.v1.QueryOuterClass.QueryBridgeTokensRequest, fx.gravity.crosschain.v1.QueryOuterClass.QueryBridgeTokensResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BridgeTokens"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.crosschain.v1.QueryOuterClass.QueryBridgeTokensRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.crosschain.v1.QueryOuterClass.QueryBridgeTokensResponse.getDefaultInstance()))
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
    public void params(fx.gravity.crosschain.v1.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.QueryOuterClass.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }

    /**
     */
    public void currentOracleSet(fx.gravity.crosschain.v1.QueryOuterClass.QueryCurrentOracleSetRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.QueryOuterClass.QueryCurrentOracleSetResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCurrentOracleSetMethod(), responseObserver);
    }

    /**
     */
    public void oracleSetRequest(fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetRequestRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetRequestResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getOracleSetRequestMethod(), responseObserver);
    }

    /**
     */
    public void oracleSetConfirm(fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetConfirmRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetConfirmResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getOracleSetConfirmMethod(), responseObserver);
    }

    /**
     */
    public void oracleSetConfirmsByNonce(fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetConfirmsByNonceRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetConfirmsByNonceResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getOracleSetConfirmsByNonceMethod(), responseObserver);
    }

    /**
     */
    public void lastOracleSetRequests(fx.gravity.crosschain.v1.QueryOuterClass.QueryLastOracleSetRequestsRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.QueryOuterClass.QueryLastOracleSetRequestsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getLastOracleSetRequestsMethod(), responseObserver);
    }

    /**
     */
    public void lastPendingOracleSetRequestByAddr(fx.gravity.crosschain.v1.QueryOuterClass.QueryLastPendingOracleSetRequestByAddrRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.QueryOuterClass.QueryLastPendingOracleSetRequestByAddrResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getLastPendingOracleSetRequestByAddrMethod(), responseObserver);
    }

    /**
     */
    public void lastPendingBatchRequestByAddr(fx.gravity.crosschain.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getLastPendingBatchRequestByAddrMethod(), responseObserver);
    }

    /**
     */
    public void lastEventNonceByAddr(fx.gravity.crosschain.v1.QueryOuterClass.QueryLastEventNonceByAddrRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.QueryOuterClass.QueryLastEventNonceByAddrResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getLastEventNonceByAddrMethod(), responseObserver);
    }

    /**
     */
    public void lastEventBlockHeightByAddr(fx.gravity.crosschain.v1.QueryOuterClass.QueryLastEventBlockHeightByAddrRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.QueryOuterClass.QueryLastEventBlockHeightByAddrResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getLastEventBlockHeightByAddrMethod(), responseObserver);
    }

    /**
     */
    public void batchFees(fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchFeeRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchFeeResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getBatchFeesMethod(), responseObserver);
    }

    /**
     */
    public void lastObservedBlockHeight(fx.gravity.crosschain.v1.QueryOuterClass.QueryLastObservedBlockHeightRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.QueryOuterClass.QueryLastObservedBlockHeightResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getLastObservedBlockHeightMethod(), responseObserver);
    }

    /**
     */
    public void outgoingTxBatches(fx.gravity.crosschain.v1.QueryOuterClass.QueryOutgoingTxBatchesRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.QueryOuterClass.QueryOutgoingTxBatchesResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getOutgoingTxBatchesMethod(), responseObserver);
    }

    /**
     */
    public void batchRequestByNonce(fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchRequestByNonceRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchRequestByNonceResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getBatchRequestByNonceMethod(), responseObserver);
    }

    /**
     */
    public void batchConfirm(fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchConfirmRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchConfirmResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getBatchConfirmMethod(), responseObserver);
    }

    /**
     */
    public void batchConfirms(fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchConfirmsRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchConfirmsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getBatchConfirmsMethod(), responseObserver);
    }

    /**
     */
    public void tokenToDenom(fx.gravity.crosschain.v1.QueryOuterClass.QueryTokenToDenomRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.QueryOuterClass.QueryTokenToDenomResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getTokenToDenomMethod(), responseObserver);
    }

    /**
     */
    public void denomToToken(fx.gravity.crosschain.v1.QueryOuterClass.QueryDenomToTokenRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.QueryOuterClass.QueryDenomToTokenResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDenomToTokenMethod(), responseObserver);
    }

    /**
     */
    public void getOracleByAddr(fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleByAddrRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetOracleByAddrMethod(), responseObserver);
    }

    /**
     */
    public void getOracleByExternalAddr(fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleByExternalAddrRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetOracleByExternalAddrMethod(), responseObserver);
    }

    /**
     */
    public void getOracleByBridgerAddr(fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleByBridgerAddrRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetOracleByBridgerAddrMethod(), responseObserver);
    }

    /**
     */
    public void getPendingSendToExternal(fx.gravity.crosschain.v1.QueryOuterClass.QueryPendingSendToExternalRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.QueryOuterClass.QueryPendingSendToExternalResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetPendingSendToExternalMethod(), responseObserver);
    }

    /**
     * <pre>
     * Validators queries all oracle that match the given status.
     * </pre>
     */
    public void oracles(fx.gravity.crosschain.v1.QueryOuterClass.QueryOraclesRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.QueryOuterClass.QueryOraclesResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getOraclesMethod(), responseObserver);
    }

    /**
     */
    public void projectedBatchTimeoutHeight(fx.gravity.crosschain.v1.QueryOuterClass.QueryProjectedBatchTimeoutHeightRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.QueryOuterClass.QueryProjectedBatchTimeoutHeightResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getProjectedBatchTimeoutHeightMethod(), responseObserver);
    }

    /**
     */
    public void bridgeTokens(fx.gravity.crosschain.v1.QueryOuterClass.QueryBridgeTokensRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.QueryOuterClass.QueryBridgeTokensResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getBridgeTokensMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getParamsMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.gravity.crosschain.v1.QueryOuterClass.QueryParamsRequest,
                fx.gravity.crosschain.v1.QueryOuterClass.QueryParamsResponse>(
                  this, METHODID_PARAMS)))
          .addMethod(
            getCurrentOracleSetMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.gravity.crosschain.v1.QueryOuterClass.QueryCurrentOracleSetRequest,
                fx.gravity.crosschain.v1.QueryOuterClass.QueryCurrentOracleSetResponse>(
                  this, METHODID_CURRENT_ORACLE_SET)))
          .addMethod(
            getOracleSetRequestMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetRequestRequest,
                fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetRequestResponse>(
                  this, METHODID_ORACLE_SET_REQUEST)))
          .addMethod(
            getOracleSetConfirmMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetConfirmRequest,
                fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetConfirmResponse>(
                  this, METHODID_ORACLE_SET_CONFIRM)))
          .addMethod(
            getOracleSetConfirmsByNonceMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetConfirmsByNonceRequest,
                fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetConfirmsByNonceResponse>(
                  this, METHODID_ORACLE_SET_CONFIRMS_BY_NONCE)))
          .addMethod(
            getLastOracleSetRequestsMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.gravity.crosschain.v1.QueryOuterClass.QueryLastOracleSetRequestsRequest,
                fx.gravity.crosschain.v1.QueryOuterClass.QueryLastOracleSetRequestsResponse>(
                  this, METHODID_LAST_ORACLE_SET_REQUESTS)))
          .addMethod(
            getLastPendingOracleSetRequestByAddrMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.gravity.crosschain.v1.QueryOuterClass.QueryLastPendingOracleSetRequestByAddrRequest,
                fx.gravity.crosschain.v1.QueryOuterClass.QueryLastPendingOracleSetRequestByAddrResponse>(
                  this, METHODID_LAST_PENDING_ORACLE_SET_REQUEST_BY_ADDR)))
          .addMethod(
            getLastPendingBatchRequestByAddrMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.gravity.crosschain.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrRequest,
                fx.gravity.crosschain.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrResponse>(
                  this, METHODID_LAST_PENDING_BATCH_REQUEST_BY_ADDR)))
          .addMethod(
            getLastEventNonceByAddrMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.gravity.crosschain.v1.QueryOuterClass.QueryLastEventNonceByAddrRequest,
                fx.gravity.crosschain.v1.QueryOuterClass.QueryLastEventNonceByAddrResponse>(
                  this, METHODID_LAST_EVENT_NONCE_BY_ADDR)))
          .addMethod(
            getLastEventBlockHeightByAddrMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.gravity.crosschain.v1.QueryOuterClass.QueryLastEventBlockHeightByAddrRequest,
                fx.gravity.crosschain.v1.QueryOuterClass.QueryLastEventBlockHeightByAddrResponse>(
                  this, METHODID_LAST_EVENT_BLOCK_HEIGHT_BY_ADDR)))
          .addMethod(
            getBatchFeesMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchFeeRequest,
                fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchFeeResponse>(
                  this, METHODID_BATCH_FEES)))
          .addMethod(
            getLastObservedBlockHeightMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.gravity.crosschain.v1.QueryOuterClass.QueryLastObservedBlockHeightRequest,
                fx.gravity.crosschain.v1.QueryOuterClass.QueryLastObservedBlockHeightResponse>(
                  this, METHODID_LAST_OBSERVED_BLOCK_HEIGHT)))
          .addMethod(
            getOutgoingTxBatchesMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.gravity.crosschain.v1.QueryOuterClass.QueryOutgoingTxBatchesRequest,
                fx.gravity.crosschain.v1.QueryOuterClass.QueryOutgoingTxBatchesResponse>(
                  this, METHODID_OUTGOING_TX_BATCHES)))
          .addMethod(
            getBatchRequestByNonceMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchRequestByNonceRequest,
                fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchRequestByNonceResponse>(
                  this, METHODID_BATCH_REQUEST_BY_NONCE)))
          .addMethod(
            getBatchConfirmMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchConfirmRequest,
                fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchConfirmResponse>(
                  this, METHODID_BATCH_CONFIRM)))
          .addMethod(
            getBatchConfirmsMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchConfirmsRequest,
                fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchConfirmsResponse>(
                  this, METHODID_BATCH_CONFIRMS)))
          .addMethod(
            getTokenToDenomMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.gravity.crosschain.v1.QueryOuterClass.QueryTokenToDenomRequest,
                fx.gravity.crosschain.v1.QueryOuterClass.QueryTokenToDenomResponse>(
                  this, METHODID_TOKEN_TO_DENOM)))
          .addMethod(
            getDenomToTokenMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.gravity.crosschain.v1.QueryOuterClass.QueryDenomToTokenRequest,
                fx.gravity.crosschain.v1.QueryOuterClass.QueryDenomToTokenResponse>(
                  this, METHODID_DENOM_TO_TOKEN)))
          .addMethod(
            getGetOracleByAddrMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleByAddrRequest,
                fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleResponse>(
                  this, METHODID_GET_ORACLE_BY_ADDR)))
          .addMethod(
            getGetOracleByExternalAddrMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleByExternalAddrRequest,
                fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleResponse>(
                  this, METHODID_GET_ORACLE_BY_EXTERNAL_ADDR)))
          .addMethod(
            getGetOracleByBridgerAddrMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleByBridgerAddrRequest,
                fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleResponse>(
                  this, METHODID_GET_ORACLE_BY_BRIDGER_ADDR)))
          .addMethod(
            getGetPendingSendToExternalMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.gravity.crosschain.v1.QueryOuterClass.QueryPendingSendToExternalRequest,
                fx.gravity.crosschain.v1.QueryOuterClass.QueryPendingSendToExternalResponse>(
                  this, METHODID_GET_PENDING_SEND_TO_EXTERNAL)))
          .addMethod(
            getOraclesMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.gravity.crosschain.v1.QueryOuterClass.QueryOraclesRequest,
                fx.gravity.crosschain.v1.QueryOuterClass.QueryOraclesResponse>(
                  this, METHODID_ORACLES)))
          .addMethod(
            getProjectedBatchTimeoutHeightMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.gravity.crosschain.v1.QueryOuterClass.QueryProjectedBatchTimeoutHeightRequest,
                fx.gravity.crosschain.v1.QueryOuterClass.QueryProjectedBatchTimeoutHeightResponse>(
                  this, METHODID_PROJECTED_BATCH_TIMEOUT_HEIGHT)))
          .addMethod(
            getBridgeTokensMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.gravity.crosschain.v1.QueryOuterClass.QueryBridgeTokensRequest,
                fx.gravity.crosschain.v1.QueryOuterClass.QueryBridgeTokensResponse>(
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
    public void params(fx.gravity.crosschain.v1.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.QueryOuterClass.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void currentOracleSet(fx.gravity.crosschain.v1.QueryOuterClass.QueryCurrentOracleSetRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.QueryOuterClass.QueryCurrentOracleSetResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCurrentOracleSetMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void oracleSetRequest(fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetRequestRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetRequestResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getOracleSetRequestMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void oracleSetConfirm(fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetConfirmRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetConfirmResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getOracleSetConfirmMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void oracleSetConfirmsByNonce(fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetConfirmsByNonceRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetConfirmsByNonceResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getOracleSetConfirmsByNonceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void lastOracleSetRequests(fx.gravity.crosschain.v1.QueryOuterClass.QueryLastOracleSetRequestsRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.QueryOuterClass.QueryLastOracleSetRequestsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getLastOracleSetRequestsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void lastPendingOracleSetRequestByAddr(fx.gravity.crosschain.v1.QueryOuterClass.QueryLastPendingOracleSetRequestByAddrRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.QueryOuterClass.QueryLastPendingOracleSetRequestByAddrResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getLastPendingOracleSetRequestByAddrMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void lastPendingBatchRequestByAddr(fx.gravity.crosschain.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getLastPendingBatchRequestByAddrMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void lastEventNonceByAddr(fx.gravity.crosschain.v1.QueryOuterClass.QueryLastEventNonceByAddrRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.QueryOuterClass.QueryLastEventNonceByAddrResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getLastEventNonceByAddrMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void lastEventBlockHeightByAddr(fx.gravity.crosschain.v1.QueryOuterClass.QueryLastEventBlockHeightByAddrRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.QueryOuterClass.QueryLastEventBlockHeightByAddrResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getLastEventBlockHeightByAddrMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void batchFees(fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchFeeRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchFeeResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getBatchFeesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void lastObservedBlockHeight(fx.gravity.crosschain.v1.QueryOuterClass.QueryLastObservedBlockHeightRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.QueryOuterClass.QueryLastObservedBlockHeightResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getLastObservedBlockHeightMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void outgoingTxBatches(fx.gravity.crosschain.v1.QueryOuterClass.QueryOutgoingTxBatchesRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.QueryOuterClass.QueryOutgoingTxBatchesResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getOutgoingTxBatchesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void batchRequestByNonce(fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchRequestByNonceRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchRequestByNonceResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getBatchRequestByNonceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void batchConfirm(fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchConfirmRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchConfirmResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getBatchConfirmMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void batchConfirms(fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchConfirmsRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchConfirmsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getBatchConfirmsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void tokenToDenom(fx.gravity.crosschain.v1.QueryOuterClass.QueryTokenToDenomRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.QueryOuterClass.QueryTokenToDenomResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getTokenToDenomMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void denomToToken(fx.gravity.crosschain.v1.QueryOuterClass.QueryDenomToTokenRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.QueryOuterClass.QueryDenomToTokenResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDenomToTokenMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getOracleByAddr(fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleByAddrRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetOracleByAddrMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getOracleByExternalAddr(fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleByExternalAddrRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetOracleByExternalAddrMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getOracleByBridgerAddr(fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleByBridgerAddrRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetOracleByBridgerAddrMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getPendingSendToExternal(fx.gravity.crosschain.v1.QueryOuterClass.QueryPendingSendToExternalRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.QueryOuterClass.QueryPendingSendToExternalResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetPendingSendToExternalMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Validators queries all oracle that match the given status.
     * </pre>
     */
    public void oracles(fx.gravity.crosschain.v1.QueryOuterClass.QueryOraclesRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.QueryOuterClass.QueryOraclesResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getOraclesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void projectedBatchTimeoutHeight(fx.gravity.crosschain.v1.QueryOuterClass.QueryProjectedBatchTimeoutHeightRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.QueryOuterClass.QueryProjectedBatchTimeoutHeightResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getProjectedBatchTimeoutHeightMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void bridgeTokens(fx.gravity.crosschain.v1.QueryOuterClass.QueryBridgeTokensRequest request,
        io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.QueryOuterClass.QueryBridgeTokensResponse> responseObserver) {
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
    public fx.gravity.crosschain.v1.QueryOuterClass.QueryParamsResponse params(fx.gravity.crosschain.v1.QueryOuterClass.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
    }

    /**
     */
    public fx.gravity.crosschain.v1.QueryOuterClass.QueryCurrentOracleSetResponse currentOracleSet(fx.gravity.crosschain.v1.QueryOuterClass.QueryCurrentOracleSetRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCurrentOracleSetMethod(), getCallOptions(), request);
    }

    /**
     */
    public fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetRequestResponse oracleSetRequest(fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetRequestRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getOracleSetRequestMethod(), getCallOptions(), request);
    }

    /**
     */
    public fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetConfirmResponse oracleSetConfirm(fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetConfirmRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getOracleSetConfirmMethod(), getCallOptions(), request);
    }

    /**
     */
    public fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetConfirmsByNonceResponse oracleSetConfirmsByNonce(fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetConfirmsByNonceRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getOracleSetConfirmsByNonceMethod(), getCallOptions(), request);
    }

    /**
     */
    public fx.gravity.crosschain.v1.QueryOuterClass.QueryLastOracleSetRequestsResponse lastOracleSetRequests(fx.gravity.crosschain.v1.QueryOuterClass.QueryLastOracleSetRequestsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getLastOracleSetRequestsMethod(), getCallOptions(), request);
    }

    /**
     */
    public fx.gravity.crosschain.v1.QueryOuterClass.QueryLastPendingOracleSetRequestByAddrResponse lastPendingOracleSetRequestByAddr(fx.gravity.crosschain.v1.QueryOuterClass.QueryLastPendingOracleSetRequestByAddrRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getLastPendingOracleSetRequestByAddrMethod(), getCallOptions(), request);
    }

    /**
     */
    public fx.gravity.crosschain.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrResponse lastPendingBatchRequestByAddr(fx.gravity.crosschain.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getLastPendingBatchRequestByAddrMethod(), getCallOptions(), request);
    }

    /**
     */
    public fx.gravity.crosschain.v1.QueryOuterClass.QueryLastEventNonceByAddrResponse lastEventNonceByAddr(fx.gravity.crosschain.v1.QueryOuterClass.QueryLastEventNonceByAddrRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getLastEventNonceByAddrMethod(), getCallOptions(), request);
    }

    /**
     */
    public fx.gravity.crosschain.v1.QueryOuterClass.QueryLastEventBlockHeightByAddrResponse lastEventBlockHeightByAddr(fx.gravity.crosschain.v1.QueryOuterClass.QueryLastEventBlockHeightByAddrRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getLastEventBlockHeightByAddrMethod(), getCallOptions(), request);
    }

    /**
     */
    public fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchFeeResponse batchFees(fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchFeeRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getBatchFeesMethod(), getCallOptions(), request);
    }

    /**
     */
    public fx.gravity.crosschain.v1.QueryOuterClass.QueryLastObservedBlockHeightResponse lastObservedBlockHeight(fx.gravity.crosschain.v1.QueryOuterClass.QueryLastObservedBlockHeightRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getLastObservedBlockHeightMethod(), getCallOptions(), request);
    }

    /**
     */
    public fx.gravity.crosschain.v1.QueryOuterClass.QueryOutgoingTxBatchesResponse outgoingTxBatches(fx.gravity.crosschain.v1.QueryOuterClass.QueryOutgoingTxBatchesRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getOutgoingTxBatchesMethod(), getCallOptions(), request);
    }

    /**
     */
    public fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchRequestByNonceResponse batchRequestByNonce(fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchRequestByNonceRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getBatchRequestByNonceMethod(), getCallOptions(), request);
    }

    /**
     */
    public fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchConfirmResponse batchConfirm(fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchConfirmRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getBatchConfirmMethod(), getCallOptions(), request);
    }

    /**
     */
    public fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchConfirmsResponse batchConfirms(fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchConfirmsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getBatchConfirmsMethod(), getCallOptions(), request);
    }

    /**
     */
    public fx.gravity.crosschain.v1.QueryOuterClass.QueryTokenToDenomResponse tokenToDenom(fx.gravity.crosschain.v1.QueryOuterClass.QueryTokenToDenomRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getTokenToDenomMethod(), getCallOptions(), request);
    }

    /**
     */
    public fx.gravity.crosschain.v1.QueryOuterClass.QueryDenomToTokenResponse denomToToken(fx.gravity.crosschain.v1.QueryOuterClass.QueryDenomToTokenRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDenomToTokenMethod(), getCallOptions(), request);
    }

    /**
     */
    public fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleResponse getOracleByAddr(fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleByAddrRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetOracleByAddrMethod(), getCallOptions(), request);
    }

    /**
     */
    public fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleResponse getOracleByExternalAddr(fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleByExternalAddrRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetOracleByExternalAddrMethod(), getCallOptions(), request);
    }

    /**
     */
    public fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleResponse getOracleByBridgerAddr(fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleByBridgerAddrRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetOracleByBridgerAddrMethod(), getCallOptions(), request);
    }

    /**
     */
    public fx.gravity.crosschain.v1.QueryOuterClass.QueryPendingSendToExternalResponse getPendingSendToExternal(fx.gravity.crosschain.v1.QueryOuterClass.QueryPendingSendToExternalRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetPendingSendToExternalMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Validators queries all oracle that match the given status.
     * </pre>
     */
    public fx.gravity.crosschain.v1.QueryOuterClass.QueryOraclesResponse oracles(fx.gravity.crosschain.v1.QueryOuterClass.QueryOraclesRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getOraclesMethod(), getCallOptions(), request);
    }

    /**
     */
    public fx.gravity.crosschain.v1.QueryOuterClass.QueryProjectedBatchTimeoutHeightResponse projectedBatchTimeoutHeight(fx.gravity.crosschain.v1.QueryOuterClass.QueryProjectedBatchTimeoutHeightRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getProjectedBatchTimeoutHeightMethod(), getCallOptions(), request);
    }

    /**
     */
    public fx.gravity.crosschain.v1.QueryOuterClass.QueryBridgeTokensResponse bridgeTokens(fx.gravity.crosschain.v1.QueryOuterClass.QueryBridgeTokensRequest request) {
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
    public com.google.common.util.concurrent.ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryParamsResponse> params(
        fx.gravity.crosschain.v1.QueryOuterClass.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryCurrentOracleSetResponse> currentOracleSet(
        fx.gravity.crosschain.v1.QueryOuterClass.QueryCurrentOracleSetRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCurrentOracleSetMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetRequestResponse> oracleSetRequest(
        fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetRequestRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getOracleSetRequestMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetConfirmResponse> oracleSetConfirm(
        fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetConfirmRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getOracleSetConfirmMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetConfirmsByNonceResponse> oracleSetConfirmsByNonce(
        fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetConfirmsByNonceRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getOracleSetConfirmsByNonceMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryLastOracleSetRequestsResponse> lastOracleSetRequests(
        fx.gravity.crosschain.v1.QueryOuterClass.QueryLastOracleSetRequestsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getLastOracleSetRequestsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryLastPendingOracleSetRequestByAddrResponse> lastPendingOracleSetRequestByAddr(
        fx.gravity.crosschain.v1.QueryOuterClass.QueryLastPendingOracleSetRequestByAddrRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getLastPendingOracleSetRequestByAddrMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrResponse> lastPendingBatchRequestByAddr(
        fx.gravity.crosschain.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getLastPendingBatchRequestByAddrMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryLastEventNonceByAddrResponse> lastEventNonceByAddr(
        fx.gravity.crosschain.v1.QueryOuterClass.QueryLastEventNonceByAddrRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getLastEventNonceByAddrMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryLastEventBlockHeightByAddrResponse> lastEventBlockHeightByAddr(
        fx.gravity.crosschain.v1.QueryOuterClass.QueryLastEventBlockHeightByAddrRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getLastEventBlockHeightByAddrMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchFeeResponse> batchFees(
        fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchFeeRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getBatchFeesMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryLastObservedBlockHeightResponse> lastObservedBlockHeight(
        fx.gravity.crosschain.v1.QueryOuterClass.QueryLastObservedBlockHeightRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getLastObservedBlockHeightMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryOutgoingTxBatchesResponse> outgoingTxBatches(
        fx.gravity.crosschain.v1.QueryOuterClass.QueryOutgoingTxBatchesRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getOutgoingTxBatchesMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchRequestByNonceResponse> batchRequestByNonce(
        fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchRequestByNonceRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getBatchRequestByNonceMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchConfirmResponse> batchConfirm(
        fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchConfirmRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getBatchConfirmMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchConfirmsResponse> batchConfirms(
        fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchConfirmsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getBatchConfirmsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryTokenToDenomResponse> tokenToDenom(
        fx.gravity.crosschain.v1.QueryOuterClass.QueryTokenToDenomRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getTokenToDenomMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryDenomToTokenResponse> denomToToken(
        fx.gravity.crosschain.v1.QueryOuterClass.QueryDenomToTokenRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDenomToTokenMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleResponse> getOracleByAddr(
        fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleByAddrRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetOracleByAddrMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleResponse> getOracleByExternalAddr(
        fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleByExternalAddrRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetOracleByExternalAddrMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleResponse> getOracleByBridgerAddr(
        fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleByBridgerAddrRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetOracleByBridgerAddrMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryPendingSendToExternalResponse> getPendingSendToExternal(
        fx.gravity.crosschain.v1.QueryOuterClass.QueryPendingSendToExternalRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetPendingSendToExternalMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Validators queries all oracle that match the given status.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryOraclesResponse> oracles(
        fx.gravity.crosschain.v1.QueryOuterClass.QueryOraclesRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getOraclesMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryProjectedBatchTimeoutHeightResponse> projectedBatchTimeoutHeight(
        fx.gravity.crosschain.v1.QueryOuterClass.QueryProjectedBatchTimeoutHeightRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getProjectedBatchTimeoutHeightMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryBridgeTokensResponse> bridgeTokens(
        fx.gravity.crosschain.v1.QueryOuterClass.QueryBridgeTokensRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getBridgeTokensMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PARAMS = 0;
  private static final int METHODID_CURRENT_ORACLE_SET = 1;
  private static final int METHODID_ORACLE_SET_REQUEST = 2;
  private static final int METHODID_ORACLE_SET_CONFIRM = 3;
  private static final int METHODID_ORACLE_SET_CONFIRMS_BY_NONCE = 4;
  private static final int METHODID_LAST_ORACLE_SET_REQUESTS = 5;
  private static final int METHODID_LAST_PENDING_ORACLE_SET_REQUEST_BY_ADDR = 6;
  private static final int METHODID_LAST_PENDING_BATCH_REQUEST_BY_ADDR = 7;
  private static final int METHODID_LAST_EVENT_NONCE_BY_ADDR = 8;
  private static final int METHODID_LAST_EVENT_BLOCK_HEIGHT_BY_ADDR = 9;
  private static final int METHODID_BATCH_FEES = 10;
  private static final int METHODID_LAST_OBSERVED_BLOCK_HEIGHT = 11;
  private static final int METHODID_OUTGOING_TX_BATCHES = 12;
  private static final int METHODID_BATCH_REQUEST_BY_NONCE = 13;
  private static final int METHODID_BATCH_CONFIRM = 14;
  private static final int METHODID_BATCH_CONFIRMS = 15;
  private static final int METHODID_TOKEN_TO_DENOM = 16;
  private static final int METHODID_DENOM_TO_TOKEN = 17;
  private static final int METHODID_GET_ORACLE_BY_ADDR = 18;
  private static final int METHODID_GET_ORACLE_BY_EXTERNAL_ADDR = 19;
  private static final int METHODID_GET_ORACLE_BY_BRIDGER_ADDR = 20;
  private static final int METHODID_GET_PENDING_SEND_TO_EXTERNAL = 21;
  private static final int METHODID_ORACLES = 22;
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
          serviceImpl.params((fx.gravity.crosschain.v1.QueryOuterClass.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.QueryOuterClass.QueryParamsResponse>) responseObserver);
          break;
        case METHODID_CURRENT_ORACLE_SET:
          serviceImpl.currentOracleSet((fx.gravity.crosschain.v1.QueryOuterClass.QueryCurrentOracleSetRequest) request,
              (io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.QueryOuterClass.QueryCurrentOracleSetResponse>) responseObserver);
          break;
        case METHODID_ORACLE_SET_REQUEST:
          serviceImpl.oracleSetRequest((fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetRequestRequest) request,
              (io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetRequestResponse>) responseObserver);
          break;
        case METHODID_ORACLE_SET_CONFIRM:
          serviceImpl.oracleSetConfirm((fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetConfirmRequest) request,
              (io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetConfirmResponse>) responseObserver);
          break;
        case METHODID_ORACLE_SET_CONFIRMS_BY_NONCE:
          serviceImpl.oracleSetConfirmsByNonce((fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetConfirmsByNonceRequest) request,
              (io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetConfirmsByNonceResponse>) responseObserver);
          break;
        case METHODID_LAST_ORACLE_SET_REQUESTS:
          serviceImpl.lastOracleSetRequests((fx.gravity.crosschain.v1.QueryOuterClass.QueryLastOracleSetRequestsRequest) request,
              (io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.QueryOuterClass.QueryLastOracleSetRequestsResponse>) responseObserver);
          break;
        case METHODID_LAST_PENDING_ORACLE_SET_REQUEST_BY_ADDR:
          serviceImpl.lastPendingOracleSetRequestByAddr((fx.gravity.crosschain.v1.QueryOuterClass.QueryLastPendingOracleSetRequestByAddrRequest) request,
              (io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.QueryOuterClass.QueryLastPendingOracleSetRequestByAddrResponse>) responseObserver);
          break;
        case METHODID_LAST_PENDING_BATCH_REQUEST_BY_ADDR:
          serviceImpl.lastPendingBatchRequestByAddr((fx.gravity.crosschain.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrRequest) request,
              (io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrResponse>) responseObserver);
          break;
        case METHODID_LAST_EVENT_NONCE_BY_ADDR:
          serviceImpl.lastEventNonceByAddr((fx.gravity.crosschain.v1.QueryOuterClass.QueryLastEventNonceByAddrRequest) request,
              (io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.QueryOuterClass.QueryLastEventNonceByAddrResponse>) responseObserver);
          break;
        case METHODID_LAST_EVENT_BLOCK_HEIGHT_BY_ADDR:
          serviceImpl.lastEventBlockHeightByAddr((fx.gravity.crosschain.v1.QueryOuterClass.QueryLastEventBlockHeightByAddrRequest) request,
              (io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.QueryOuterClass.QueryLastEventBlockHeightByAddrResponse>) responseObserver);
          break;
        case METHODID_BATCH_FEES:
          serviceImpl.batchFees((fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchFeeRequest) request,
              (io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchFeeResponse>) responseObserver);
          break;
        case METHODID_LAST_OBSERVED_BLOCK_HEIGHT:
          serviceImpl.lastObservedBlockHeight((fx.gravity.crosschain.v1.QueryOuterClass.QueryLastObservedBlockHeightRequest) request,
              (io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.QueryOuterClass.QueryLastObservedBlockHeightResponse>) responseObserver);
          break;
        case METHODID_OUTGOING_TX_BATCHES:
          serviceImpl.outgoingTxBatches((fx.gravity.crosschain.v1.QueryOuterClass.QueryOutgoingTxBatchesRequest) request,
              (io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.QueryOuterClass.QueryOutgoingTxBatchesResponse>) responseObserver);
          break;
        case METHODID_BATCH_REQUEST_BY_NONCE:
          serviceImpl.batchRequestByNonce((fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchRequestByNonceRequest) request,
              (io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchRequestByNonceResponse>) responseObserver);
          break;
        case METHODID_BATCH_CONFIRM:
          serviceImpl.batchConfirm((fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchConfirmRequest) request,
              (io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchConfirmResponse>) responseObserver);
          break;
        case METHODID_BATCH_CONFIRMS:
          serviceImpl.batchConfirms((fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchConfirmsRequest) request,
              (io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchConfirmsResponse>) responseObserver);
          break;
        case METHODID_TOKEN_TO_DENOM:
          serviceImpl.tokenToDenom((fx.gravity.crosschain.v1.QueryOuterClass.QueryTokenToDenomRequest) request,
              (io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.QueryOuterClass.QueryTokenToDenomResponse>) responseObserver);
          break;
        case METHODID_DENOM_TO_TOKEN:
          serviceImpl.denomToToken((fx.gravity.crosschain.v1.QueryOuterClass.QueryDenomToTokenRequest) request,
              (io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.QueryOuterClass.QueryDenomToTokenResponse>) responseObserver);
          break;
        case METHODID_GET_ORACLE_BY_ADDR:
          serviceImpl.getOracleByAddr((fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleByAddrRequest) request,
              (io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleResponse>) responseObserver);
          break;
        case METHODID_GET_ORACLE_BY_EXTERNAL_ADDR:
          serviceImpl.getOracleByExternalAddr((fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleByExternalAddrRequest) request,
              (io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleResponse>) responseObserver);
          break;
        case METHODID_GET_ORACLE_BY_BRIDGER_ADDR:
          serviceImpl.getOracleByBridgerAddr((fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleByBridgerAddrRequest) request,
              (io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleResponse>) responseObserver);
          break;
        case METHODID_GET_PENDING_SEND_TO_EXTERNAL:
          serviceImpl.getPendingSendToExternal((fx.gravity.crosschain.v1.QueryOuterClass.QueryPendingSendToExternalRequest) request,
              (io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.QueryOuterClass.QueryPendingSendToExternalResponse>) responseObserver);
          break;
        case METHODID_ORACLES:
          serviceImpl.oracles((fx.gravity.crosschain.v1.QueryOuterClass.QueryOraclesRequest) request,
              (io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.QueryOuterClass.QueryOraclesResponse>) responseObserver);
          break;
        case METHODID_PROJECTED_BATCH_TIMEOUT_HEIGHT:
          serviceImpl.projectedBatchTimeoutHeight((fx.gravity.crosschain.v1.QueryOuterClass.QueryProjectedBatchTimeoutHeightRequest) request,
              (io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.QueryOuterClass.QueryProjectedBatchTimeoutHeightResponse>) responseObserver);
          break;
        case METHODID_BRIDGE_TOKENS:
          serviceImpl.bridgeTokens((fx.gravity.crosschain.v1.QueryOuterClass.QueryBridgeTokensRequest) request,
              (io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.QueryOuterClass.QueryBridgeTokensResponse>) responseObserver);
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
      return fx.gravity.crosschain.v1.QueryOuterClass.getDescriptor();
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
              .addMethod(getCurrentOracleSetMethod())
              .addMethod(getOracleSetRequestMethod())
              .addMethod(getOracleSetConfirmMethod())
              .addMethod(getOracleSetConfirmsByNonceMethod())
              .addMethod(getLastOracleSetRequestsMethod())
              .addMethod(getLastPendingOracleSetRequestByAddrMethod())
              .addMethod(getLastPendingBatchRequestByAddrMethod())
              .addMethod(getLastEventNonceByAddrMethod())
              .addMethod(getLastEventBlockHeightByAddrMethod())
              .addMethod(getBatchFeesMethod())
              .addMethod(getLastObservedBlockHeightMethod())
              .addMethod(getOutgoingTxBatchesMethod())
              .addMethod(getBatchRequestByNonceMethod())
              .addMethod(getBatchConfirmMethod())
              .addMethod(getBatchConfirmsMethod())
              .addMethod(getTokenToDenomMethod())
              .addMethod(getDenomToTokenMethod())
              .addMethod(getGetOracleByAddrMethod())
              .addMethod(getGetOracleByExternalAddrMethod())
              .addMethod(getGetOracleByBridgerAddrMethod())
              .addMethod(getGetPendingSendToExternalMethod())
              .addMethod(getOraclesMethod())
              .addMethod(getProjectedBatchTimeoutHeightMethod())
              .addMethod(getBridgeTokensMethod())
              .build();
        }
      }
    }
    return result;
  }
}
