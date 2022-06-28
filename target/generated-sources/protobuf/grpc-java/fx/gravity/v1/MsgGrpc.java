package fx.gravity.v1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Msg defines the state transitions possible within gravity
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.44.1)",
    comments = "Source: fx/gravity/v1/tx.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "fx.gravity.v1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<fx.gravity.v1.Tx.MsgValsetConfirm,
      fx.gravity.v1.Tx.MsgValsetConfirmResponse> getValsetConfirmMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ValsetConfirm",
      requestType = fx.gravity.v1.Tx.MsgValsetConfirm.class,
      responseType = fx.gravity.v1.Tx.MsgValsetConfirmResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.gravity.v1.Tx.MsgValsetConfirm,
      fx.gravity.v1.Tx.MsgValsetConfirmResponse> getValsetConfirmMethod() {
    io.grpc.MethodDescriptor<fx.gravity.v1.Tx.MsgValsetConfirm, fx.gravity.v1.Tx.MsgValsetConfirmResponse> getValsetConfirmMethod;
    if ((getValsetConfirmMethod = MsgGrpc.getValsetConfirmMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getValsetConfirmMethod = MsgGrpc.getValsetConfirmMethod) == null) {
          MsgGrpc.getValsetConfirmMethod = getValsetConfirmMethod =
              io.grpc.MethodDescriptor.<fx.gravity.v1.Tx.MsgValsetConfirm, fx.gravity.v1.Tx.MsgValsetConfirmResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ValsetConfirm"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.v1.Tx.MsgValsetConfirm.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.v1.Tx.MsgValsetConfirmResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("ValsetConfirm"))
              .build();
        }
      }
    }
    return getValsetConfirmMethod;
  }

  private static volatile io.grpc.MethodDescriptor<fx.gravity.v1.Tx.MsgSendToEth,
      fx.gravity.v1.Tx.MsgSendToEthResponse> getSendToEthMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SendToEth",
      requestType = fx.gravity.v1.Tx.MsgSendToEth.class,
      responseType = fx.gravity.v1.Tx.MsgSendToEthResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.gravity.v1.Tx.MsgSendToEth,
      fx.gravity.v1.Tx.MsgSendToEthResponse> getSendToEthMethod() {
    io.grpc.MethodDescriptor<fx.gravity.v1.Tx.MsgSendToEth, fx.gravity.v1.Tx.MsgSendToEthResponse> getSendToEthMethod;
    if ((getSendToEthMethod = MsgGrpc.getSendToEthMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getSendToEthMethod = MsgGrpc.getSendToEthMethod) == null) {
          MsgGrpc.getSendToEthMethod = getSendToEthMethod =
              io.grpc.MethodDescriptor.<fx.gravity.v1.Tx.MsgSendToEth, fx.gravity.v1.Tx.MsgSendToEthResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SendToEth"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.v1.Tx.MsgSendToEth.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.v1.Tx.MsgSendToEthResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("SendToEth"))
              .build();
        }
      }
    }
    return getSendToEthMethod;
  }

  private static volatile io.grpc.MethodDescriptor<fx.gravity.v1.Tx.MsgRequestBatch,
      fx.gravity.v1.Tx.MsgRequestBatchResponse> getRequestBatchMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RequestBatch",
      requestType = fx.gravity.v1.Tx.MsgRequestBatch.class,
      responseType = fx.gravity.v1.Tx.MsgRequestBatchResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.gravity.v1.Tx.MsgRequestBatch,
      fx.gravity.v1.Tx.MsgRequestBatchResponse> getRequestBatchMethod() {
    io.grpc.MethodDescriptor<fx.gravity.v1.Tx.MsgRequestBatch, fx.gravity.v1.Tx.MsgRequestBatchResponse> getRequestBatchMethod;
    if ((getRequestBatchMethod = MsgGrpc.getRequestBatchMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getRequestBatchMethod = MsgGrpc.getRequestBatchMethod) == null) {
          MsgGrpc.getRequestBatchMethod = getRequestBatchMethod =
              io.grpc.MethodDescriptor.<fx.gravity.v1.Tx.MsgRequestBatch, fx.gravity.v1.Tx.MsgRequestBatchResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RequestBatch"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.v1.Tx.MsgRequestBatch.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.v1.Tx.MsgRequestBatchResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("RequestBatch"))
              .build();
        }
      }
    }
    return getRequestBatchMethod;
  }

  private static volatile io.grpc.MethodDescriptor<fx.gravity.v1.Tx.MsgConfirmBatch,
      fx.gravity.v1.Tx.MsgConfirmBatchResponse> getConfirmBatchMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ConfirmBatch",
      requestType = fx.gravity.v1.Tx.MsgConfirmBatch.class,
      responseType = fx.gravity.v1.Tx.MsgConfirmBatchResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.gravity.v1.Tx.MsgConfirmBatch,
      fx.gravity.v1.Tx.MsgConfirmBatchResponse> getConfirmBatchMethod() {
    io.grpc.MethodDescriptor<fx.gravity.v1.Tx.MsgConfirmBatch, fx.gravity.v1.Tx.MsgConfirmBatchResponse> getConfirmBatchMethod;
    if ((getConfirmBatchMethod = MsgGrpc.getConfirmBatchMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getConfirmBatchMethod = MsgGrpc.getConfirmBatchMethod) == null) {
          MsgGrpc.getConfirmBatchMethod = getConfirmBatchMethod =
              io.grpc.MethodDescriptor.<fx.gravity.v1.Tx.MsgConfirmBatch, fx.gravity.v1.Tx.MsgConfirmBatchResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ConfirmBatch"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.v1.Tx.MsgConfirmBatch.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.v1.Tx.MsgConfirmBatchResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("ConfirmBatch"))
              .build();
        }
      }
    }
    return getConfirmBatchMethod;
  }

  private static volatile io.grpc.MethodDescriptor<fx.gravity.v1.Tx.MsgDepositClaim,
      fx.gravity.v1.Tx.MsgDepositClaimResponse> getDepositClaimMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DepositClaim",
      requestType = fx.gravity.v1.Tx.MsgDepositClaim.class,
      responseType = fx.gravity.v1.Tx.MsgDepositClaimResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.gravity.v1.Tx.MsgDepositClaim,
      fx.gravity.v1.Tx.MsgDepositClaimResponse> getDepositClaimMethod() {
    io.grpc.MethodDescriptor<fx.gravity.v1.Tx.MsgDepositClaim, fx.gravity.v1.Tx.MsgDepositClaimResponse> getDepositClaimMethod;
    if ((getDepositClaimMethod = MsgGrpc.getDepositClaimMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getDepositClaimMethod = MsgGrpc.getDepositClaimMethod) == null) {
          MsgGrpc.getDepositClaimMethod = getDepositClaimMethod =
              io.grpc.MethodDescriptor.<fx.gravity.v1.Tx.MsgDepositClaim, fx.gravity.v1.Tx.MsgDepositClaimResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DepositClaim"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.v1.Tx.MsgDepositClaim.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.v1.Tx.MsgDepositClaimResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("DepositClaim"))
              .build();
        }
      }
    }
    return getDepositClaimMethod;
  }

  private static volatile io.grpc.MethodDescriptor<fx.gravity.v1.Tx.MsgWithdrawClaim,
      fx.gravity.v1.Tx.MsgWithdrawClaimResponse> getWithdrawClaimMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "WithdrawClaim",
      requestType = fx.gravity.v1.Tx.MsgWithdrawClaim.class,
      responseType = fx.gravity.v1.Tx.MsgWithdrawClaimResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.gravity.v1.Tx.MsgWithdrawClaim,
      fx.gravity.v1.Tx.MsgWithdrawClaimResponse> getWithdrawClaimMethod() {
    io.grpc.MethodDescriptor<fx.gravity.v1.Tx.MsgWithdrawClaim, fx.gravity.v1.Tx.MsgWithdrawClaimResponse> getWithdrawClaimMethod;
    if ((getWithdrawClaimMethod = MsgGrpc.getWithdrawClaimMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getWithdrawClaimMethod = MsgGrpc.getWithdrawClaimMethod) == null) {
          MsgGrpc.getWithdrawClaimMethod = getWithdrawClaimMethod =
              io.grpc.MethodDescriptor.<fx.gravity.v1.Tx.MsgWithdrawClaim, fx.gravity.v1.Tx.MsgWithdrawClaimResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "WithdrawClaim"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.v1.Tx.MsgWithdrawClaim.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.v1.Tx.MsgWithdrawClaimResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("WithdrawClaim"))
              .build();
        }
      }
    }
    return getWithdrawClaimMethod;
  }

  private static volatile io.grpc.MethodDescriptor<fx.gravity.v1.Tx.MsgValsetUpdatedClaim,
      fx.gravity.v1.Tx.MsgValsetUpdatedClaimResponse> getValsetUpdateClaimMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ValsetUpdateClaim",
      requestType = fx.gravity.v1.Tx.MsgValsetUpdatedClaim.class,
      responseType = fx.gravity.v1.Tx.MsgValsetUpdatedClaimResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.gravity.v1.Tx.MsgValsetUpdatedClaim,
      fx.gravity.v1.Tx.MsgValsetUpdatedClaimResponse> getValsetUpdateClaimMethod() {
    io.grpc.MethodDescriptor<fx.gravity.v1.Tx.MsgValsetUpdatedClaim, fx.gravity.v1.Tx.MsgValsetUpdatedClaimResponse> getValsetUpdateClaimMethod;
    if ((getValsetUpdateClaimMethod = MsgGrpc.getValsetUpdateClaimMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getValsetUpdateClaimMethod = MsgGrpc.getValsetUpdateClaimMethod) == null) {
          MsgGrpc.getValsetUpdateClaimMethod = getValsetUpdateClaimMethod =
              io.grpc.MethodDescriptor.<fx.gravity.v1.Tx.MsgValsetUpdatedClaim, fx.gravity.v1.Tx.MsgValsetUpdatedClaimResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ValsetUpdateClaim"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.v1.Tx.MsgValsetUpdatedClaim.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.v1.Tx.MsgValsetUpdatedClaimResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("ValsetUpdateClaim"))
              .build();
        }
      }
    }
    return getValsetUpdateClaimMethod;
  }

  private static volatile io.grpc.MethodDescriptor<fx.gravity.v1.Tx.MsgSetOrchestratorAddress,
      fx.gravity.v1.Tx.MsgSetOrchestratorAddressResponse> getSetOrchestratorAddressMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SetOrchestratorAddress",
      requestType = fx.gravity.v1.Tx.MsgSetOrchestratorAddress.class,
      responseType = fx.gravity.v1.Tx.MsgSetOrchestratorAddressResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.gravity.v1.Tx.MsgSetOrchestratorAddress,
      fx.gravity.v1.Tx.MsgSetOrchestratorAddressResponse> getSetOrchestratorAddressMethod() {
    io.grpc.MethodDescriptor<fx.gravity.v1.Tx.MsgSetOrchestratorAddress, fx.gravity.v1.Tx.MsgSetOrchestratorAddressResponse> getSetOrchestratorAddressMethod;
    if ((getSetOrchestratorAddressMethod = MsgGrpc.getSetOrchestratorAddressMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getSetOrchestratorAddressMethod = MsgGrpc.getSetOrchestratorAddressMethod) == null) {
          MsgGrpc.getSetOrchestratorAddressMethod = getSetOrchestratorAddressMethod =
              io.grpc.MethodDescriptor.<fx.gravity.v1.Tx.MsgSetOrchestratorAddress, fx.gravity.v1.Tx.MsgSetOrchestratorAddressResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SetOrchestratorAddress"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.v1.Tx.MsgSetOrchestratorAddress.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.v1.Tx.MsgSetOrchestratorAddressResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("SetOrchestratorAddress"))
              .build();
        }
      }
    }
    return getSetOrchestratorAddressMethod;
  }

  private static volatile io.grpc.MethodDescriptor<fx.gravity.v1.Tx.MsgCancelSendToEth,
      fx.gravity.v1.Tx.MsgCancelSendToEthResponse> getCancelSendToEthMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CancelSendToEth",
      requestType = fx.gravity.v1.Tx.MsgCancelSendToEth.class,
      responseType = fx.gravity.v1.Tx.MsgCancelSendToEthResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.gravity.v1.Tx.MsgCancelSendToEth,
      fx.gravity.v1.Tx.MsgCancelSendToEthResponse> getCancelSendToEthMethod() {
    io.grpc.MethodDescriptor<fx.gravity.v1.Tx.MsgCancelSendToEth, fx.gravity.v1.Tx.MsgCancelSendToEthResponse> getCancelSendToEthMethod;
    if ((getCancelSendToEthMethod = MsgGrpc.getCancelSendToEthMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCancelSendToEthMethod = MsgGrpc.getCancelSendToEthMethod) == null) {
          MsgGrpc.getCancelSendToEthMethod = getCancelSendToEthMethod =
              io.grpc.MethodDescriptor.<fx.gravity.v1.Tx.MsgCancelSendToEth, fx.gravity.v1.Tx.MsgCancelSendToEthResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CancelSendToEth"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.v1.Tx.MsgCancelSendToEth.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.v1.Tx.MsgCancelSendToEthResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CancelSendToEth"))
              .build();
        }
      }
    }
    return getCancelSendToEthMethod;
  }

  private static volatile io.grpc.MethodDescriptor<fx.gravity.v1.Tx.MsgFxOriginatedTokenClaim,
      fx.gravity.v1.Tx.MsgFxOriginatedTokenClaimResponse> getFxOriginatedTokenClaimMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "FxOriginatedTokenClaim",
      requestType = fx.gravity.v1.Tx.MsgFxOriginatedTokenClaim.class,
      responseType = fx.gravity.v1.Tx.MsgFxOriginatedTokenClaimResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.gravity.v1.Tx.MsgFxOriginatedTokenClaim,
      fx.gravity.v1.Tx.MsgFxOriginatedTokenClaimResponse> getFxOriginatedTokenClaimMethod() {
    io.grpc.MethodDescriptor<fx.gravity.v1.Tx.MsgFxOriginatedTokenClaim, fx.gravity.v1.Tx.MsgFxOriginatedTokenClaimResponse> getFxOriginatedTokenClaimMethod;
    if ((getFxOriginatedTokenClaimMethod = MsgGrpc.getFxOriginatedTokenClaimMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getFxOriginatedTokenClaimMethod = MsgGrpc.getFxOriginatedTokenClaimMethod) == null) {
          MsgGrpc.getFxOriginatedTokenClaimMethod = getFxOriginatedTokenClaimMethod =
              io.grpc.MethodDescriptor.<fx.gravity.v1.Tx.MsgFxOriginatedTokenClaim, fx.gravity.v1.Tx.MsgFxOriginatedTokenClaimResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "FxOriginatedTokenClaim"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.v1.Tx.MsgFxOriginatedTokenClaim.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.v1.Tx.MsgFxOriginatedTokenClaimResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("FxOriginatedTokenClaim"))
              .build();
        }
      }
    }
    return getFxOriginatedTokenClaimMethod;
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
   * Msg defines the state transitions possible within gravity
   * </pre>
   */
  public static abstract class MsgImplBase implements io.grpc.BindableService {

    /**
     */
    public void valsetConfirm(fx.gravity.v1.Tx.MsgValsetConfirm request,
        io.grpc.stub.StreamObserver<fx.gravity.v1.Tx.MsgValsetConfirmResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getValsetConfirmMethod(), responseObserver);
    }

    /**
     */
    public void sendToEth(fx.gravity.v1.Tx.MsgSendToEth request,
        io.grpc.stub.StreamObserver<fx.gravity.v1.Tx.MsgSendToEthResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSendToEthMethod(), responseObserver);
    }

    /**
     */
    public void requestBatch(fx.gravity.v1.Tx.MsgRequestBatch request,
        io.grpc.stub.StreamObserver<fx.gravity.v1.Tx.MsgRequestBatchResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRequestBatchMethod(), responseObserver);
    }

    /**
     */
    public void confirmBatch(fx.gravity.v1.Tx.MsgConfirmBatch request,
        io.grpc.stub.StreamObserver<fx.gravity.v1.Tx.MsgConfirmBatchResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getConfirmBatchMethod(), responseObserver);
    }

    /**
     */
    public void depositClaim(fx.gravity.v1.Tx.MsgDepositClaim request,
        io.grpc.stub.StreamObserver<fx.gravity.v1.Tx.MsgDepositClaimResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDepositClaimMethod(), responseObserver);
    }

    /**
     */
    public void withdrawClaim(fx.gravity.v1.Tx.MsgWithdrawClaim request,
        io.grpc.stub.StreamObserver<fx.gravity.v1.Tx.MsgWithdrawClaimResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getWithdrawClaimMethod(), responseObserver);
    }

    /**
     */
    public void valsetUpdateClaim(fx.gravity.v1.Tx.MsgValsetUpdatedClaim request,
        io.grpc.stub.StreamObserver<fx.gravity.v1.Tx.MsgValsetUpdatedClaimResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getValsetUpdateClaimMethod(), responseObserver);
    }

    /**
     */
    public void setOrchestratorAddress(fx.gravity.v1.Tx.MsgSetOrchestratorAddress request,
        io.grpc.stub.StreamObserver<fx.gravity.v1.Tx.MsgSetOrchestratorAddressResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSetOrchestratorAddressMethod(), responseObserver);
    }

    /**
     */
    public void cancelSendToEth(fx.gravity.v1.Tx.MsgCancelSendToEth request,
        io.grpc.stub.StreamObserver<fx.gravity.v1.Tx.MsgCancelSendToEthResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCancelSendToEthMethod(), responseObserver);
    }

    /**
     */
    public void fxOriginatedTokenClaim(fx.gravity.v1.Tx.MsgFxOriginatedTokenClaim request,
        io.grpc.stub.StreamObserver<fx.gravity.v1.Tx.MsgFxOriginatedTokenClaimResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getFxOriginatedTokenClaimMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getValsetConfirmMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.gravity.v1.Tx.MsgValsetConfirm,
                fx.gravity.v1.Tx.MsgValsetConfirmResponse>(
                  this, METHODID_VALSET_CONFIRM)))
          .addMethod(
            getSendToEthMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.gravity.v1.Tx.MsgSendToEth,
                fx.gravity.v1.Tx.MsgSendToEthResponse>(
                  this, METHODID_SEND_TO_ETH)))
          .addMethod(
            getRequestBatchMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.gravity.v1.Tx.MsgRequestBatch,
                fx.gravity.v1.Tx.MsgRequestBatchResponse>(
                  this, METHODID_REQUEST_BATCH)))
          .addMethod(
            getConfirmBatchMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.gravity.v1.Tx.MsgConfirmBatch,
                fx.gravity.v1.Tx.MsgConfirmBatchResponse>(
                  this, METHODID_CONFIRM_BATCH)))
          .addMethod(
            getDepositClaimMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.gravity.v1.Tx.MsgDepositClaim,
                fx.gravity.v1.Tx.MsgDepositClaimResponse>(
                  this, METHODID_DEPOSIT_CLAIM)))
          .addMethod(
            getWithdrawClaimMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.gravity.v1.Tx.MsgWithdrawClaim,
                fx.gravity.v1.Tx.MsgWithdrawClaimResponse>(
                  this, METHODID_WITHDRAW_CLAIM)))
          .addMethod(
            getValsetUpdateClaimMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.gravity.v1.Tx.MsgValsetUpdatedClaim,
                fx.gravity.v1.Tx.MsgValsetUpdatedClaimResponse>(
                  this, METHODID_VALSET_UPDATE_CLAIM)))
          .addMethod(
            getSetOrchestratorAddressMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.gravity.v1.Tx.MsgSetOrchestratorAddress,
                fx.gravity.v1.Tx.MsgSetOrchestratorAddressResponse>(
                  this, METHODID_SET_ORCHESTRATOR_ADDRESS)))
          .addMethod(
            getCancelSendToEthMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.gravity.v1.Tx.MsgCancelSendToEth,
                fx.gravity.v1.Tx.MsgCancelSendToEthResponse>(
                  this, METHODID_CANCEL_SEND_TO_ETH)))
          .addMethod(
            getFxOriginatedTokenClaimMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.gravity.v1.Tx.MsgFxOriginatedTokenClaim,
                fx.gravity.v1.Tx.MsgFxOriginatedTokenClaimResponse>(
                  this, METHODID_FX_ORIGINATED_TOKEN_CLAIM)))
          .build();
    }
  }

  /**
   * <pre>
   * Msg defines the state transitions possible within gravity
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
     */
    public void valsetConfirm(fx.gravity.v1.Tx.MsgValsetConfirm request,
        io.grpc.stub.StreamObserver<fx.gravity.v1.Tx.MsgValsetConfirmResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getValsetConfirmMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void sendToEth(fx.gravity.v1.Tx.MsgSendToEth request,
        io.grpc.stub.StreamObserver<fx.gravity.v1.Tx.MsgSendToEthResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSendToEthMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void requestBatch(fx.gravity.v1.Tx.MsgRequestBatch request,
        io.grpc.stub.StreamObserver<fx.gravity.v1.Tx.MsgRequestBatchResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRequestBatchMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void confirmBatch(fx.gravity.v1.Tx.MsgConfirmBatch request,
        io.grpc.stub.StreamObserver<fx.gravity.v1.Tx.MsgConfirmBatchResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getConfirmBatchMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void depositClaim(fx.gravity.v1.Tx.MsgDepositClaim request,
        io.grpc.stub.StreamObserver<fx.gravity.v1.Tx.MsgDepositClaimResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDepositClaimMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void withdrawClaim(fx.gravity.v1.Tx.MsgWithdrawClaim request,
        io.grpc.stub.StreamObserver<fx.gravity.v1.Tx.MsgWithdrawClaimResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getWithdrawClaimMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void valsetUpdateClaim(fx.gravity.v1.Tx.MsgValsetUpdatedClaim request,
        io.grpc.stub.StreamObserver<fx.gravity.v1.Tx.MsgValsetUpdatedClaimResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getValsetUpdateClaimMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void setOrchestratorAddress(fx.gravity.v1.Tx.MsgSetOrchestratorAddress request,
        io.grpc.stub.StreamObserver<fx.gravity.v1.Tx.MsgSetOrchestratorAddressResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSetOrchestratorAddressMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void cancelSendToEth(fx.gravity.v1.Tx.MsgCancelSendToEth request,
        io.grpc.stub.StreamObserver<fx.gravity.v1.Tx.MsgCancelSendToEthResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCancelSendToEthMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void fxOriginatedTokenClaim(fx.gravity.v1.Tx.MsgFxOriginatedTokenClaim request,
        io.grpc.stub.StreamObserver<fx.gravity.v1.Tx.MsgFxOriginatedTokenClaimResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getFxOriginatedTokenClaimMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Msg defines the state transitions possible within gravity
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
     */
    public fx.gravity.v1.Tx.MsgValsetConfirmResponse valsetConfirm(fx.gravity.v1.Tx.MsgValsetConfirm request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getValsetConfirmMethod(), getCallOptions(), request);
    }

    /**
     */
    public fx.gravity.v1.Tx.MsgSendToEthResponse sendToEth(fx.gravity.v1.Tx.MsgSendToEth request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSendToEthMethod(), getCallOptions(), request);
    }

    /**
     */
    public fx.gravity.v1.Tx.MsgRequestBatchResponse requestBatch(fx.gravity.v1.Tx.MsgRequestBatch request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRequestBatchMethod(), getCallOptions(), request);
    }

    /**
     */
    public fx.gravity.v1.Tx.MsgConfirmBatchResponse confirmBatch(fx.gravity.v1.Tx.MsgConfirmBatch request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getConfirmBatchMethod(), getCallOptions(), request);
    }

    /**
     */
    public fx.gravity.v1.Tx.MsgDepositClaimResponse depositClaim(fx.gravity.v1.Tx.MsgDepositClaim request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDepositClaimMethod(), getCallOptions(), request);
    }

    /**
     */
    public fx.gravity.v1.Tx.MsgWithdrawClaimResponse withdrawClaim(fx.gravity.v1.Tx.MsgWithdrawClaim request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getWithdrawClaimMethod(), getCallOptions(), request);
    }

    /**
     */
    public fx.gravity.v1.Tx.MsgValsetUpdatedClaimResponse valsetUpdateClaim(fx.gravity.v1.Tx.MsgValsetUpdatedClaim request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getValsetUpdateClaimMethod(), getCallOptions(), request);
    }

    /**
     */
    public fx.gravity.v1.Tx.MsgSetOrchestratorAddressResponse setOrchestratorAddress(fx.gravity.v1.Tx.MsgSetOrchestratorAddress request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSetOrchestratorAddressMethod(), getCallOptions(), request);
    }

    /**
     */
    public fx.gravity.v1.Tx.MsgCancelSendToEthResponse cancelSendToEth(fx.gravity.v1.Tx.MsgCancelSendToEth request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCancelSendToEthMethod(), getCallOptions(), request);
    }

    /**
     */
    public fx.gravity.v1.Tx.MsgFxOriginatedTokenClaimResponse fxOriginatedTokenClaim(fx.gravity.v1.Tx.MsgFxOriginatedTokenClaim request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getFxOriginatedTokenClaimMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Msg defines the state transitions possible within gravity
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
     */
    public com.google.common.util.concurrent.ListenableFuture<fx.gravity.v1.Tx.MsgValsetConfirmResponse> valsetConfirm(
        fx.gravity.v1.Tx.MsgValsetConfirm request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getValsetConfirmMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<fx.gravity.v1.Tx.MsgSendToEthResponse> sendToEth(
        fx.gravity.v1.Tx.MsgSendToEth request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSendToEthMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<fx.gravity.v1.Tx.MsgRequestBatchResponse> requestBatch(
        fx.gravity.v1.Tx.MsgRequestBatch request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRequestBatchMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<fx.gravity.v1.Tx.MsgConfirmBatchResponse> confirmBatch(
        fx.gravity.v1.Tx.MsgConfirmBatch request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getConfirmBatchMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<fx.gravity.v1.Tx.MsgDepositClaimResponse> depositClaim(
        fx.gravity.v1.Tx.MsgDepositClaim request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDepositClaimMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<fx.gravity.v1.Tx.MsgWithdrawClaimResponse> withdrawClaim(
        fx.gravity.v1.Tx.MsgWithdrawClaim request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getWithdrawClaimMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<fx.gravity.v1.Tx.MsgValsetUpdatedClaimResponse> valsetUpdateClaim(
        fx.gravity.v1.Tx.MsgValsetUpdatedClaim request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getValsetUpdateClaimMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<fx.gravity.v1.Tx.MsgSetOrchestratorAddressResponse> setOrchestratorAddress(
        fx.gravity.v1.Tx.MsgSetOrchestratorAddress request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSetOrchestratorAddressMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<fx.gravity.v1.Tx.MsgCancelSendToEthResponse> cancelSendToEth(
        fx.gravity.v1.Tx.MsgCancelSendToEth request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCancelSendToEthMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<fx.gravity.v1.Tx.MsgFxOriginatedTokenClaimResponse> fxOriginatedTokenClaim(
        fx.gravity.v1.Tx.MsgFxOriginatedTokenClaim request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getFxOriginatedTokenClaimMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_VALSET_CONFIRM = 0;
  private static final int METHODID_SEND_TO_ETH = 1;
  private static final int METHODID_REQUEST_BATCH = 2;
  private static final int METHODID_CONFIRM_BATCH = 3;
  private static final int METHODID_DEPOSIT_CLAIM = 4;
  private static final int METHODID_WITHDRAW_CLAIM = 5;
  private static final int METHODID_VALSET_UPDATE_CLAIM = 6;
  private static final int METHODID_SET_ORCHESTRATOR_ADDRESS = 7;
  private static final int METHODID_CANCEL_SEND_TO_ETH = 8;
  private static final int METHODID_FX_ORIGINATED_TOKEN_CLAIM = 9;

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
        case METHODID_VALSET_CONFIRM:
          serviceImpl.valsetConfirm((fx.gravity.v1.Tx.MsgValsetConfirm) request,
              (io.grpc.stub.StreamObserver<fx.gravity.v1.Tx.MsgValsetConfirmResponse>) responseObserver);
          break;
        case METHODID_SEND_TO_ETH:
          serviceImpl.sendToEth((fx.gravity.v1.Tx.MsgSendToEth) request,
              (io.grpc.stub.StreamObserver<fx.gravity.v1.Tx.MsgSendToEthResponse>) responseObserver);
          break;
        case METHODID_REQUEST_BATCH:
          serviceImpl.requestBatch((fx.gravity.v1.Tx.MsgRequestBatch) request,
              (io.grpc.stub.StreamObserver<fx.gravity.v1.Tx.MsgRequestBatchResponse>) responseObserver);
          break;
        case METHODID_CONFIRM_BATCH:
          serviceImpl.confirmBatch((fx.gravity.v1.Tx.MsgConfirmBatch) request,
              (io.grpc.stub.StreamObserver<fx.gravity.v1.Tx.MsgConfirmBatchResponse>) responseObserver);
          break;
        case METHODID_DEPOSIT_CLAIM:
          serviceImpl.depositClaim((fx.gravity.v1.Tx.MsgDepositClaim) request,
              (io.grpc.stub.StreamObserver<fx.gravity.v1.Tx.MsgDepositClaimResponse>) responseObserver);
          break;
        case METHODID_WITHDRAW_CLAIM:
          serviceImpl.withdrawClaim((fx.gravity.v1.Tx.MsgWithdrawClaim) request,
              (io.grpc.stub.StreamObserver<fx.gravity.v1.Tx.MsgWithdrawClaimResponse>) responseObserver);
          break;
        case METHODID_VALSET_UPDATE_CLAIM:
          serviceImpl.valsetUpdateClaim((fx.gravity.v1.Tx.MsgValsetUpdatedClaim) request,
              (io.grpc.stub.StreamObserver<fx.gravity.v1.Tx.MsgValsetUpdatedClaimResponse>) responseObserver);
          break;
        case METHODID_SET_ORCHESTRATOR_ADDRESS:
          serviceImpl.setOrchestratorAddress((fx.gravity.v1.Tx.MsgSetOrchestratorAddress) request,
              (io.grpc.stub.StreamObserver<fx.gravity.v1.Tx.MsgSetOrchestratorAddressResponse>) responseObserver);
          break;
        case METHODID_CANCEL_SEND_TO_ETH:
          serviceImpl.cancelSendToEth((fx.gravity.v1.Tx.MsgCancelSendToEth) request,
              (io.grpc.stub.StreamObserver<fx.gravity.v1.Tx.MsgCancelSendToEthResponse>) responseObserver);
          break;
        case METHODID_FX_ORIGINATED_TOKEN_CLAIM:
          serviceImpl.fxOriginatedTokenClaim((fx.gravity.v1.Tx.MsgFxOriginatedTokenClaim) request,
              (io.grpc.stub.StreamObserver<fx.gravity.v1.Tx.MsgFxOriginatedTokenClaimResponse>) responseObserver);
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
      return fx.gravity.v1.Tx.getDescriptor();
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
              .addMethod(getValsetConfirmMethod())
              .addMethod(getSendToEthMethod())
              .addMethod(getRequestBatchMethod())
              .addMethod(getConfirmBatchMethod())
              .addMethod(getDepositClaimMethod())
              .addMethod(getWithdrawClaimMethod())
              .addMethod(getValsetUpdateClaimMethod())
              .addMethod(getSetOrchestratorAddressMethod())
              .addMethod(getCancelSendToEthMethod())
              .addMethod(getFxOriginatedTokenClaimMethod())
              .build();
        }
      }
    }
    return result;
  }
}
