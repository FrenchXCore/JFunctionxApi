package fx.gravity.crosschain.v1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Msg defines the state transitions possible within gravity
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.44.1)",
    comments = "Source: fx/crosschain/v1/tx.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "fx.gravity.crosschain.v1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.Tx.MsgBondedOracle,
      fx.gravity.crosschain.v1.Tx.MsgBondedOracleResponse> getBondedOracleMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BondedOracle",
      requestType = fx.gravity.crosschain.v1.Tx.MsgBondedOracle.class,
      responseType = fx.gravity.crosschain.v1.Tx.MsgBondedOracleResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.Tx.MsgBondedOracle,
      fx.gravity.crosschain.v1.Tx.MsgBondedOracleResponse> getBondedOracleMethod() {
    io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.Tx.MsgBondedOracle, fx.gravity.crosschain.v1.Tx.MsgBondedOracleResponse> getBondedOracleMethod;
    if ((getBondedOracleMethod = MsgGrpc.getBondedOracleMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getBondedOracleMethod = MsgGrpc.getBondedOracleMethod) == null) {
          MsgGrpc.getBondedOracleMethod = getBondedOracleMethod =
              io.grpc.MethodDescriptor.<fx.gravity.crosschain.v1.Tx.MsgBondedOracle, fx.gravity.crosschain.v1.Tx.MsgBondedOracleResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BondedOracle"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.crosschain.v1.Tx.MsgBondedOracle.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.crosschain.v1.Tx.MsgBondedOracleResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("BondedOracle"))
              .build();
        }
      }
    }
    return getBondedOracleMethod;
  }

  private static volatile io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.Tx.MsgAddDelegate,
      fx.gravity.crosschain.v1.Tx.MsgAddDelegateResponse> getAddDelegateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AddDelegate",
      requestType = fx.gravity.crosschain.v1.Tx.MsgAddDelegate.class,
      responseType = fx.gravity.crosschain.v1.Tx.MsgAddDelegateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.Tx.MsgAddDelegate,
      fx.gravity.crosschain.v1.Tx.MsgAddDelegateResponse> getAddDelegateMethod() {
    io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.Tx.MsgAddDelegate, fx.gravity.crosschain.v1.Tx.MsgAddDelegateResponse> getAddDelegateMethod;
    if ((getAddDelegateMethod = MsgGrpc.getAddDelegateMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getAddDelegateMethod = MsgGrpc.getAddDelegateMethod) == null) {
          MsgGrpc.getAddDelegateMethod = getAddDelegateMethod =
              io.grpc.MethodDescriptor.<fx.gravity.crosschain.v1.Tx.MsgAddDelegate, fx.gravity.crosschain.v1.Tx.MsgAddDelegateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AddDelegate"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.crosschain.v1.Tx.MsgAddDelegate.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.crosschain.v1.Tx.MsgAddDelegateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("AddDelegate"))
              .build();
        }
      }
    }
    return getAddDelegateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.Tx.MsgEditOracle,
      fx.gravity.crosschain.v1.Tx.MsgEditOracleResponse> getEditOracleMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "EditOracle",
      requestType = fx.gravity.crosschain.v1.Tx.MsgEditOracle.class,
      responseType = fx.gravity.crosschain.v1.Tx.MsgEditOracleResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.Tx.MsgEditOracle,
      fx.gravity.crosschain.v1.Tx.MsgEditOracleResponse> getEditOracleMethod() {
    io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.Tx.MsgEditOracle, fx.gravity.crosschain.v1.Tx.MsgEditOracleResponse> getEditOracleMethod;
    if ((getEditOracleMethod = MsgGrpc.getEditOracleMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getEditOracleMethod = MsgGrpc.getEditOracleMethod) == null) {
          MsgGrpc.getEditOracleMethod = getEditOracleMethod =
              io.grpc.MethodDescriptor.<fx.gravity.crosschain.v1.Tx.MsgEditOracle, fx.gravity.crosschain.v1.Tx.MsgEditOracleResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "EditOracle"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.crosschain.v1.Tx.MsgEditOracle.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.crosschain.v1.Tx.MsgEditOracleResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("EditOracle"))
              .build();
        }
      }
    }
    return getEditOracleMethod;
  }

  private static volatile io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.Tx.MsgWithdrawReward,
      fx.gravity.crosschain.v1.Tx.MsgWithdrawRewardResponse> getWithdrawRewardMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "WithdrawReward",
      requestType = fx.gravity.crosschain.v1.Tx.MsgWithdrawReward.class,
      responseType = fx.gravity.crosschain.v1.Tx.MsgWithdrawRewardResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.Tx.MsgWithdrawReward,
      fx.gravity.crosschain.v1.Tx.MsgWithdrawRewardResponse> getWithdrawRewardMethod() {
    io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.Tx.MsgWithdrawReward, fx.gravity.crosschain.v1.Tx.MsgWithdrawRewardResponse> getWithdrawRewardMethod;
    if ((getWithdrawRewardMethod = MsgGrpc.getWithdrawRewardMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getWithdrawRewardMethod = MsgGrpc.getWithdrawRewardMethod) == null) {
          MsgGrpc.getWithdrawRewardMethod = getWithdrawRewardMethod =
              io.grpc.MethodDescriptor.<fx.gravity.crosschain.v1.Tx.MsgWithdrawReward, fx.gravity.crosschain.v1.Tx.MsgWithdrawRewardResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "WithdrawReward"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.crosschain.v1.Tx.MsgWithdrawReward.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.crosschain.v1.Tx.MsgWithdrawRewardResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("WithdrawReward"))
              .build();
        }
      }
    }
    return getWithdrawRewardMethod;
  }

  private static volatile io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.Tx.MsgUnbondedOracle,
      fx.gravity.crosschain.v1.Tx.MsgUnbondedOracleResponse> getUnbondedOracleMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UnbondedOracle",
      requestType = fx.gravity.crosschain.v1.Tx.MsgUnbondedOracle.class,
      responseType = fx.gravity.crosschain.v1.Tx.MsgUnbondedOracleResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.Tx.MsgUnbondedOracle,
      fx.gravity.crosschain.v1.Tx.MsgUnbondedOracleResponse> getUnbondedOracleMethod() {
    io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.Tx.MsgUnbondedOracle, fx.gravity.crosschain.v1.Tx.MsgUnbondedOracleResponse> getUnbondedOracleMethod;
    if ((getUnbondedOracleMethod = MsgGrpc.getUnbondedOracleMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getUnbondedOracleMethod = MsgGrpc.getUnbondedOracleMethod) == null) {
          MsgGrpc.getUnbondedOracleMethod = getUnbondedOracleMethod =
              io.grpc.MethodDescriptor.<fx.gravity.crosschain.v1.Tx.MsgUnbondedOracle, fx.gravity.crosschain.v1.Tx.MsgUnbondedOracleResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UnbondedOracle"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.crosschain.v1.Tx.MsgUnbondedOracle.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.crosschain.v1.Tx.MsgUnbondedOracleResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("UnbondedOracle"))
              .build();
        }
      }
    }
    return getUnbondedOracleMethod;
  }

  private static volatile io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.Tx.MsgOracleSetConfirm,
      fx.gravity.crosschain.v1.Tx.MsgOracleSetConfirmResponse> getOracleSetConfirmMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "OracleSetConfirm",
      requestType = fx.gravity.crosschain.v1.Tx.MsgOracleSetConfirm.class,
      responseType = fx.gravity.crosschain.v1.Tx.MsgOracleSetConfirmResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.Tx.MsgOracleSetConfirm,
      fx.gravity.crosschain.v1.Tx.MsgOracleSetConfirmResponse> getOracleSetConfirmMethod() {
    io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.Tx.MsgOracleSetConfirm, fx.gravity.crosschain.v1.Tx.MsgOracleSetConfirmResponse> getOracleSetConfirmMethod;
    if ((getOracleSetConfirmMethod = MsgGrpc.getOracleSetConfirmMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getOracleSetConfirmMethod = MsgGrpc.getOracleSetConfirmMethod) == null) {
          MsgGrpc.getOracleSetConfirmMethod = getOracleSetConfirmMethod =
              io.grpc.MethodDescriptor.<fx.gravity.crosschain.v1.Tx.MsgOracleSetConfirm, fx.gravity.crosschain.v1.Tx.MsgOracleSetConfirmResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "OracleSetConfirm"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.crosschain.v1.Tx.MsgOracleSetConfirm.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.crosschain.v1.Tx.MsgOracleSetConfirmResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("OracleSetConfirm"))
              .build();
        }
      }
    }
    return getOracleSetConfirmMethod;
  }

  private static volatile io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.Tx.MsgOracleSetUpdatedClaim,
      fx.gravity.crosschain.v1.Tx.MsgOracleSetUpdatedClaimResponse> getOracleSetUpdateClaimMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "OracleSetUpdateClaim",
      requestType = fx.gravity.crosschain.v1.Tx.MsgOracleSetUpdatedClaim.class,
      responseType = fx.gravity.crosschain.v1.Tx.MsgOracleSetUpdatedClaimResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.Tx.MsgOracleSetUpdatedClaim,
      fx.gravity.crosschain.v1.Tx.MsgOracleSetUpdatedClaimResponse> getOracleSetUpdateClaimMethod() {
    io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.Tx.MsgOracleSetUpdatedClaim, fx.gravity.crosschain.v1.Tx.MsgOracleSetUpdatedClaimResponse> getOracleSetUpdateClaimMethod;
    if ((getOracleSetUpdateClaimMethod = MsgGrpc.getOracleSetUpdateClaimMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getOracleSetUpdateClaimMethod = MsgGrpc.getOracleSetUpdateClaimMethod) == null) {
          MsgGrpc.getOracleSetUpdateClaimMethod = getOracleSetUpdateClaimMethod =
              io.grpc.MethodDescriptor.<fx.gravity.crosschain.v1.Tx.MsgOracleSetUpdatedClaim, fx.gravity.crosschain.v1.Tx.MsgOracleSetUpdatedClaimResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "OracleSetUpdateClaim"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.crosschain.v1.Tx.MsgOracleSetUpdatedClaim.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.crosschain.v1.Tx.MsgOracleSetUpdatedClaimResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("OracleSetUpdateClaim"))
              .build();
        }
      }
    }
    return getOracleSetUpdateClaimMethod;
  }

  private static volatile io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.Tx.MsgBridgeTokenClaim,
      fx.gravity.crosschain.v1.Tx.MsgBridgeTokenClaimResponse> getBridgeTokenClaimMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BridgeTokenClaim",
      requestType = fx.gravity.crosschain.v1.Tx.MsgBridgeTokenClaim.class,
      responseType = fx.gravity.crosschain.v1.Tx.MsgBridgeTokenClaimResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.Tx.MsgBridgeTokenClaim,
      fx.gravity.crosschain.v1.Tx.MsgBridgeTokenClaimResponse> getBridgeTokenClaimMethod() {
    io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.Tx.MsgBridgeTokenClaim, fx.gravity.crosschain.v1.Tx.MsgBridgeTokenClaimResponse> getBridgeTokenClaimMethod;
    if ((getBridgeTokenClaimMethod = MsgGrpc.getBridgeTokenClaimMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getBridgeTokenClaimMethod = MsgGrpc.getBridgeTokenClaimMethod) == null) {
          MsgGrpc.getBridgeTokenClaimMethod = getBridgeTokenClaimMethod =
              io.grpc.MethodDescriptor.<fx.gravity.crosschain.v1.Tx.MsgBridgeTokenClaim, fx.gravity.crosschain.v1.Tx.MsgBridgeTokenClaimResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BridgeTokenClaim"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.crosschain.v1.Tx.MsgBridgeTokenClaim.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.crosschain.v1.Tx.MsgBridgeTokenClaimResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("BridgeTokenClaim"))
              .build();
        }
      }
    }
    return getBridgeTokenClaimMethod;
  }

  private static volatile io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.Tx.MsgSendToFxClaim,
      fx.gravity.crosschain.v1.Tx.MsgSendToFxClaimResponse> getSendToFxClaimMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SendToFxClaim",
      requestType = fx.gravity.crosschain.v1.Tx.MsgSendToFxClaim.class,
      responseType = fx.gravity.crosschain.v1.Tx.MsgSendToFxClaimResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.Tx.MsgSendToFxClaim,
      fx.gravity.crosschain.v1.Tx.MsgSendToFxClaimResponse> getSendToFxClaimMethod() {
    io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.Tx.MsgSendToFxClaim, fx.gravity.crosschain.v1.Tx.MsgSendToFxClaimResponse> getSendToFxClaimMethod;
    if ((getSendToFxClaimMethod = MsgGrpc.getSendToFxClaimMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getSendToFxClaimMethod = MsgGrpc.getSendToFxClaimMethod) == null) {
          MsgGrpc.getSendToFxClaimMethod = getSendToFxClaimMethod =
              io.grpc.MethodDescriptor.<fx.gravity.crosschain.v1.Tx.MsgSendToFxClaim, fx.gravity.crosschain.v1.Tx.MsgSendToFxClaimResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SendToFxClaim"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.crosschain.v1.Tx.MsgSendToFxClaim.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.crosschain.v1.Tx.MsgSendToFxClaimResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("SendToFxClaim"))
              .build();
        }
      }
    }
    return getSendToFxClaimMethod;
  }

  private static volatile io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.Tx.MsgSendToExternal,
      fx.gravity.crosschain.v1.Tx.MsgSendToExternalResponse> getSendToExternalMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SendToExternal",
      requestType = fx.gravity.crosschain.v1.Tx.MsgSendToExternal.class,
      responseType = fx.gravity.crosschain.v1.Tx.MsgSendToExternalResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.Tx.MsgSendToExternal,
      fx.gravity.crosschain.v1.Tx.MsgSendToExternalResponse> getSendToExternalMethod() {
    io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.Tx.MsgSendToExternal, fx.gravity.crosschain.v1.Tx.MsgSendToExternalResponse> getSendToExternalMethod;
    if ((getSendToExternalMethod = MsgGrpc.getSendToExternalMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getSendToExternalMethod = MsgGrpc.getSendToExternalMethod) == null) {
          MsgGrpc.getSendToExternalMethod = getSendToExternalMethod =
              io.grpc.MethodDescriptor.<fx.gravity.crosschain.v1.Tx.MsgSendToExternal, fx.gravity.crosschain.v1.Tx.MsgSendToExternalResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SendToExternal"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.crosschain.v1.Tx.MsgSendToExternal.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.crosschain.v1.Tx.MsgSendToExternalResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("SendToExternal"))
              .build();
        }
      }
    }
    return getSendToExternalMethod;
  }

  private static volatile io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.Tx.MsgCancelSendToExternal,
      fx.gravity.crosschain.v1.Tx.MsgCancelSendToExternalResponse> getCancelSendToExternalMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CancelSendToExternal",
      requestType = fx.gravity.crosschain.v1.Tx.MsgCancelSendToExternal.class,
      responseType = fx.gravity.crosschain.v1.Tx.MsgCancelSendToExternalResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.Tx.MsgCancelSendToExternal,
      fx.gravity.crosschain.v1.Tx.MsgCancelSendToExternalResponse> getCancelSendToExternalMethod() {
    io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.Tx.MsgCancelSendToExternal, fx.gravity.crosschain.v1.Tx.MsgCancelSendToExternalResponse> getCancelSendToExternalMethod;
    if ((getCancelSendToExternalMethod = MsgGrpc.getCancelSendToExternalMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCancelSendToExternalMethod = MsgGrpc.getCancelSendToExternalMethod) == null) {
          MsgGrpc.getCancelSendToExternalMethod = getCancelSendToExternalMethod =
              io.grpc.MethodDescriptor.<fx.gravity.crosschain.v1.Tx.MsgCancelSendToExternal, fx.gravity.crosschain.v1.Tx.MsgCancelSendToExternalResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CancelSendToExternal"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.crosschain.v1.Tx.MsgCancelSendToExternal.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.crosschain.v1.Tx.MsgCancelSendToExternalResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CancelSendToExternal"))
              .build();
        }
      }
    }
    return getCancelSendToExternalMethod;
  }

  private static volatile io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.Tx.MsgSendToExternalClaim,
      fx.gravity.crosschain.v1.Tx.MsgSendToExternalClaimResponse> getSendToExternalClaimMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SendToExternalClaim",
      requestType = fx.gravity.crosschain.v1.Tx.MsgSendToExternalClaim.class,
      responseType = fx.gravity.crosschain.v1.Tx.MsgSendToExternalClaimResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.Tx.MsgSendToExternalClaim,
      fx.gravity.crosschain.v1.Tx.MsgSendToExternalClaimResponse> getSendToExternalClaimMethod() {
    io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.Tx.MsgSendToExternalClaim, fx.gravity.crosschain.v1.Tx.MsgSendToExternalClaimResponse> getSendToExternalClaimMethod;
    if ((getSendToExternalClaimMethod = MsgGrpc.getSendToExternalClaimMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getSendToExternalClaimMethod = MsgGrpc.getSendToExternalClaimMethod) == null) {
          MsgGrpc.getSendToExternalClaimMethod = getSendToExternalClaimMethod =
              io.grpc.MethodDescriptor.<fx.gravity.crosschain.v1.Tx.MsgSendToExternalClaim, fx.gravity.crosschain.v1.Tx.MsgSendToExternalClaimResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SendToExternalClaim"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.crosschain.v1.Tx.MsgSendToExternalClaim.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.crosschain.v1.Tx.MsgSendToExternalClaimResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("SendToExternalClaim"))
              .build();
        }
      }
    }
    return getSendToExternalClaimMethod;
  }

  private static volatile io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.Tx.MsgRequestBatch,
      fx.gravity.crosschain.v1.Tx.MsgRequestBatchResponse> getRequestBatchMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RequestBatch",
      requestType = fx.gravity.crosschain.v1.Tx.MsgRequestBatch.class,
      responseType = fx.gravity.crosschain.v1.Tx.MsgRequestBatchResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.Tx.MsgRequestBatch,
      fx.gravity.crosschain.v1.Tx.MsgRequestBatchResponse> getRequestBatchMethod() {
    io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.Tx.MsgRequestBatch, fx.gravity.crosschain.v1.Tx.MsgRequestBatchResponse> getRequestBatchMethod;
    if ((getRequestBatchMethod = MsgGrpc.getRequestBatchMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getRequestBatchMethod = MsgGrpc.getRequestBatchMethod) == null) {
          MsgGrpc.getRequestBatchMethod = getRequestBatchMethod =
              io.grpc.MethodDescriptor.<fx.gravity.crosschain.v1.Tx.MsgRequestBatch, fx.gravity.crosschain.v1.Tx.MsgRequestBatchResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RequestBatch"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.crosschain.v1.Tx.MsgRequestBatch.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.crosschain.v1.Tx.MsgRequestBatchResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("RequestBatch"))
              .build();
        }
      }
    }
    return getRequestBatchMethod;
  }

  private static volatile io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.Tx.MsgConfirmBatch,
      fx.gravity.crosschain.v1.Tx.MsgConfirmBatchResponse> getConfirmBatchMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ConfirmBatch",
      requestType = fx.gravity.crosschain.v1.Tx.MsgConfirmBatch.class,
      responseType = fx.gravity.crosschain.v1.Tx.MsgConfirmBatchResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.Tx.MsgConfirmBatch,
      fx.gravity.crosschain.v1.Tx.MsgConfirmBatchResponse> getConfirmBatchMethod() {
    io.grpc.MethodDescriptor<fx.gravity.crosschain.v1.Tx.MsgConfirmBatch, fx.gravity.crosschain.v1.Tx.MsgConfirmBatchResponse> getConfirmBatchMethod;
    if ((getConfirmBatchMethod = MsgGrpc.getConfirmBatchMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getConfirmBatchMethod = MsgGrpc.getConfirmBatchMethod) == null) {
          MsgGrpc.getConfirmBatchMethod = getConfirmBatchMethod =
              io.grpc.MethodDescriptor.<fx.gravity.crosschain.v1.Tx.MsgConfirmBatch, fx.gravity.crosschain.v1.Tx.MsgConfirmBatchResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ConfirmBatch"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.crosschain.v1.Tx.MsgConfirmBatch.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.gravity.crosschain.v1.Tx.MsgConfirmBatchResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("ConfirmBatch"))
              .build();
        }
      }
    }
    return getConfirmBatchMethod;
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
    public void bondedOracle(fx.gravity.crosschain.v1.Tx.MsgBondedOracle request,
        io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.Tx.MsgBondedOracleResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getBondedOracleMethod(), responseObserver);
    }

    /**
     */
    public void addDelegate(fx.gravity.crosschain.v1.Tx.MsgAddDelegate request,
        io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.Tx.MsgAddDelegateResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAddDelegateMethod(), responseObserver);
    }

    /**
     */
    public void editOracle(fx.gravity.crosschain.v1.Tx.MsgEditOracle request,
        io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.Tx.MsgEditOracleResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getEditOracleMethod(), responseObserver);
    }

    /**
     */
    public void withdrawReward(fx.gravity.crosschain.v1.Tx.MsgWithdrawReward request,
        io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.Tx.MsgWithdrawRewardResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getWithdrawRewardMethod(), responseObserver);
    }

    /**
     */
    public void unbondedOracle(fx.gravity.crosschain.v1.Tx.MsgUnbondedOracle request,
        io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.Tx.MsgUnbondedOracleResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUnbondedOracleMethod(), responseObserver);
    }

    /**
     */
    public void oracleSetConfirm(fx.gravity.crosschain.v1.Tx.MsgOracleSetConfirm request,
        io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.Tx.MsgOracleSetConfirmResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getOracleSetConfirmMethod(), responseObserver);
    }

    /**
     */
    public void oracleSetUpdateClaim(fx.gravity.crosschain.v1.Tx.MsgOracleSetUpdatedClaim request,
        io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.Tx.MsgOracleSetUpdatedClaimResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getOracleSetUpdateClaimMethod(), responseObserver);
    }

    /**
     */
    public void bridgeTokenClaim(fx.gravity.crosschain.v1.Tx.MsgBridgeTokenClaim request,
        io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.Tx.MsgBridgeTokenClaimResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getBridgeTokenClaimMethod(), responseObserver);
    }

    /**
     */
    public void sendToFxClaim(fx.gravity.crosschain.v1.Tx.MsgSendToFxClaim request,
        io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.Tx.MsgSendToFxClaimResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSendToFxClaimMethod(), responseObserver);
    }

    /**
     */
    public void sendToExternal(fx.gravity.crosschain.v1.Tx.MsgSendToExternal request,
        io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.Tx.MsgSendToExternalResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSendToExternalMethod(), responseObserver);
    }

    /**
     */
    public void cancelSendToExternal(fx.gravity.crosschain.v1.Tx.MsgCancelSendToExternal request,
        io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.Tx.MsgCancelSendToExternalResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCancelSendToExternalMethod(), responseObserver);
    }

    /**
     */
    public void sendToExternalClaim(fx.gravity.crosschain.v1.Tx.MsgSendToExternalClaim request,
        io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.Tx.MsgSendToExternalClaimResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSendToExternalClaimMethod(), responseObserver);
    }

    /**
     */
    public void requestBatch(fx.gravity.crosschain.v1.Tx.MsgRequestBatch request,
        io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.Tx.MsgRequestBatchResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRequestBatchMethod(), responseObserver);
    }

    /**
     */
    public void confirmBatch(fx.gravity.crosschain.v1.Tx.MsgConfirmBatch request,
        io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.Tx.MsgConfirmBatchResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getConfirmBatchMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getBondedOracleMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.gravity.crosschain.v1.Tx.MsgBondedOracle,
                fx.gravity.crosschain.v1.Tx.MsgBondedOracleResponse>(
                  this, METHODID_BONDED_ORACLE)))
          .addMethod(
            getAddDelegateMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.gravity.crosschain.v1.Tx.MsgAddDelegate,
                fx.gravity.crosschain.v1.Tx.MsgAddDelegateResponse>(
                  this, METHODID_ADD_DELEGATE)))
          .addMethod(
            getEditOracleMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.gravity.crosschain.v1.Tx.MsgEditOracle,
                fx.gravity.crosschain.v1.Tx.MsgEditOracleResponse>(
                  this, METHODID_EDIT_ORACLE)))
          .addMethod(
            getWithdrawRewardMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.gravity.crosschain.v1.Tx.MsgWithdrawReward,
                fx.gravity.crosschain.v1.Tx.MsgWithdrawRewardResponse>(
                  this, METHODID_WITHDRAW_REWARD)))
          .addMethod(
            getUnbondedOracleMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.gravity.crosschain.v1.Tx.MsgUnbondedOracle,
                fx.gravity.crosschain.v1.Tx.MsgUnbondedOracleResponse>(
                  this, METHODID_UNBONDED_ORACLE)))
          .addMethod(
            getOracleSetConfirmMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.gravity.crosschain.v1.Tx.MsgOracleSetConfirm,
                fx.gravity.crosschain.v1.Tx.MsgOracleSetConfirmResponse>(
                  this, METHODID_ORACLE_SET_CONFIRM)))
          .addMethod(
            getOracleSetUpdateClaimMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.gravity.crosschain.v1.Tx.MsgOracleSetUpdatedClaim,
                fx.gravity.crosschain.v1.Tx.MsgOracleSetUpdatedClaimResponse>(
                  this, METHODID_ORACLE_SET_UPDATE_CLAIM)))
          .addMethod(
            getBridgeTokenClaimMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.gravity.crosschain.v1.Tx.MsgBridgeTokenClaim,
                fx.gravity.crosschain.v1.Tx.MsgBridgeTokenClaimResponse>(
                  this, METHODID_BRIDGE_TOKEN_CLAIM)))
          .addMethod(
            getSendToFxClaimMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.gravity.crosschain.v1.Tx.MsgSendToFxClaim,
                fx.gravity.crosschain.v1.Tx.MsgSendToFxClaimResponse>(
                  this, METHODID_SEND_TO_FX_CLAIM)))
          .addMethod(
            getSendToExternalMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.gravity.crosschain.v1.Tx.MsgSendToExternal,
                fx.gravity.crosschain.v1.Tx.MsgSendToExternalResponse>(
                  this, METHODID_SEND_TO_EXTERNAL)))
          .addMethod(
            getCancelSendToExternalMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.gravity.crosschain.v1.Tx.MsgCancelSendToExternal,
                fx.gravity.crosschain.v1.Tx.MsgCancelSendToExternalResponse>(
                  this, METHODID_CANCEL_SEND_TO_EXTERNAL)))
          .addMethod(
            getSendToExternalClaimMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.gravity.crosschain.v1.Tx.MsgSendToExternalClaim,
                fx.gravity.crosschain.v1.Tx.MsgSendToExternalClaimResponse>(
                  this, METHODID_SEND_TO_EXTERNAL_CLAIM)))
          .addMethod(
            getRequestBatchMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.gravity.crosschain.v1.Tx.MsgRequestBatch,
                fx.gravity.crosschain.v1.Tx.MsgRequestBatchResponse>(
                  this, METHODID_REQUEST_BATCH)))
          .addMethod(
            getConfirmBatchMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.gravity.crosschain.v1.Tx.MsgConfirmBatch,
                fx.gravity.crosschain.v1.Tx.MsgConfirmBatchResponse>(
                  this, METHODID_CONFIRM_BATCH)))
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
    public void bondedOracle(fx.gravity.crosschain.v1.Tx.MsgBondedOracle request,
        io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.Tx.MsgBondedOracleResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getBondedOracleMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void addDelegate(fx.gravity.crosschain.v1.Tx.MsgAddDelegate request,
        io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.Tx.MsgAddDelegateResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAddDelegateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void editOracle(fx.gravity.crosschain.v1.Tx.MsgEditOracle request,
        io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.Tx.MsgEditOracleResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getEditOracleMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void withdrawReward(fx.gravity.crosschain.v1.Tx.MsgWithdrawReward request,
        io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.Tx.MsgWithdrawRewardResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getWithdrawRewardMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void unbondedOracle(fx.gravity.crosschain.v1.Tx.MsgUnbondedOracle request,
        io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.Tx.MsgUnbondedOracleResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUnbondedOracleMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void oracleSetConfirm(fx.gravity.crosschain.v1.Tx.MsgOracleSetConfirm request,
        io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.Tx.MsgOracleSetConfirmResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getOracleSetConfirmMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void oracleSetUpdateClaim(fx.gravity.crosschain.v1.Tx.MsgOracleSetUpdatedClaim request,
        io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.Tx.MsgOracleSetUpdatedClaimResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getOracleSetUpdateClaimMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void bridgeTokenClaim(fx.gravity.crosschain.v1.Tx.MsgBridgeTokenClaim request,
        io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.Tx.MsgBridgeTokenClaimResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getBridgeTokenClaimMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void sendToFxClaim(fx.gravity.crosschain.v1.Tx.MsgSendToFxClaim request,
        io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.Tx.MsgSendToFxClaimResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSendToFxClaimMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void sendToExternal(fx.gravity.crosschain.v1.Tx.MsgSendToExternal request,
        io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.Tx.MsgSendToExternalResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSendToExternalMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void cancelSendToExternal(fx.gravity.crosschain.v1.Tx.MsgCancelSendToExternal request,
        io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.Tx.MsgCancelSendToExternalResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCancelSendToExternalMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void sendToExternalClaim(fx.gravity.crosschain.v1.Tx.MsgSendToExternalClaim request,
        io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.Tx.MsgSendToExternalClaimResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSendToExternalClaimMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void requestBatch(fx.gravity.crosschain.v1.Tx.MsgRequestBatch request,
        io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.Tx.MsgRequestBatchResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRequestBatchMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void confirmBatch(fx.gravity.crosschain.v1.Tx.MsgConfirmBatch request,
        io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.Tx.MsgConfirmBatchResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getConfirmBatchMethod(), getCallOptions()), request, responseObserver);
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
    public fx.gravity.crosschain.v1.Tx.MsgBondedOracleResponse bondedOracle(fx.gravity.crosschain.v1.Tx.MsgBondedOracle request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getBondedOracleMethod(), getCallOptions(), request);
    }

    /**
     */
    public fx.gravity.crosschain.v1.Tx.MsgAddDelegateResponse addDelegate(fx.gravity.crosschain.v1.Tx.MsgAddDelegate request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAddDelegateMethod(), getCallOptions(), request);
    }

    /**
     */
    public fx.gravity.crosschain.v1.Tx.MsgEditOracleResponse editOracle(fx.gravity.crosschain.v1.Tx.MsgEditOracle request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getEditOracleMethod(), getCallOptions(), request);
    }

    /**
     */
    public fx.gravity.crosschain.v1.Tx.MsgWithdrawRewardResponse withdrawReward(fx.gravity.crosschain.v1.Tx.MsgWithdrawReward request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getWithdrawRewardMethod(), getCallOptions(), request);
    }

    /**
     */
    public fx.gravity.crosschain.v1.Tx.MsgUnbondedOracleResponse unbondedOracle(fx.gravity.crosschain.v1.Tx.MsgUnbondedOracle request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUnbondedOracleMethod(), getCallOptions(), request);
    }

    /**
     */
    public fx.gravity.crosschain.v1.Tx.MsgOracleSetConfirmResponse oracleSetConfirm(fx.gravity.crosschain.v1.Tx.MsgOracleSetConfirm request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getOracleSetConfirmMethod(), getCallOptions(), request);
    }

    /**
     */
    public fx.gravity.crosschain.v1.Tx.MsgOracleSetUpdatedClaimResponse oracleSetUpdateClaim(fx.gravity.crosschain.v1.Tx.MsgOracleSetUpdatedClaim request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getOracleSetUpdateClaimMethod(), getCallOptions(), request);
    }

    /**
     */
    public fx.gravity.crosschain.v1.Tx.MsgBridgeTokenClaimResponse bridgeTokenClaim(fx.gravity.crosschain.v1.Tx.MsgBridgeTokenClaim request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getBridgeTokenClaimMethod(), getCallOptions(), request);
    }

    /**
     */
    public fx.gravity.crosschain.v1.Tx.MsgSendToFxClaimResponse sendToFxClaim(fx.gravity.crosschain.v1.Tx.MsgSendToFxClaim request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSendToFxClaimMethod(), getCallOptions(), request);
    }

    /**
     */
    public fx.gravity.crosschain.v1.Tx.MsgSendToExternalResponse sendToExternal(fx.gravity.crosschain.v1.Tx.MsgSendToExternal request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSendToExternalMethod(), getCallOptions(), request);
    }

    /**
     */
    public fx.gravity.crosschain.v1.Tx.MsgCancelSendToExternalResponse cancelSendToExternal(fx.gravity.crosschain.v1.Tx.MsgCancelSendToExternal request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCancelSendToExternalMethod(), getCallOptions(), request);
    }

    /**
     */
    public fx.gravity.crosschain.v1.Tx.MsgSendToExternalClaimResponse sendToExternalClaim(fx.gravity.crosschain.v1.Tx.MsgSendToExternalClaim request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSendToExternalClaimMethod(), getCallOptions(), request);
    }

    /**
     */
    public fx.gravity.crosschain.v1.Tx.MsgRequestBatchResponse requestBatch(fx.gravity.crosschain.v1.Tx.MsgRequestBatch request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRequestBatchMethod(), getCallOptions(), request);
    }

    /**
     */
    public fx.gravity.crosschain.v1.Tx.MsgConfirmBatchResponse confirmBatch(fx.gravity.crosschain.v1.Tx.MsgConfirmBatch request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getConfirmBatchMethod(), getCallOptions(), request);
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
    public com.google.common.util.concurrent.ListenableFuture<fx.gravity.crosschain.v1.Tx.MsgBondedOracleResponse> bondedOracle(
        fx.gravity.crosschain.v1.Tx.MsgBondedOracle request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getBondedOracleMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<fx.gravity.crosschain.v1.Tx.MsgAddDelegateResponse> addDelegate(
        fx.gravity.crosschain.v1.Tx.MsgAddDelegate request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAddDelegateMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<fx.gravity.crosschain.v1.Tx.MsgEditOracleResponse> editOracle(
        fx.gravity.crosschain.v1.Tx.MsgEditOracle request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getEditOracleMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<fx.gravity.crosschain.v1.Tx.MsgWithdrawRewardResponse> withdrawReward(
        fx.gravity.crosschain.v1.Tx.MsgWithdrawReward request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getWithdrawRewardMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<fx.gravity.crosschain.v1.Tx.MsgUnbondedOracleResponse> unbondedOracle(
        fx.gravity.crosschain.v1.Tx.MsgUnbondedOracle request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUnbondedOracleMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<fx.gravity.crosschain.v1.Tx.MsgOracleSetConfirmResponse> oracleSetConfirm(
        fx.gravity.crosschain.v1.Tx.MsgOracleSetConfirm request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getOracleSetConfirmMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<fx.gravity.crosschain.v1.Tx.MsgOracleSetUpdatedClaimResponse> oracleSetUpdateClaim(
        fx.gravity.crosschain.v1.Tx.MsgOracleSetUpdatedClaim request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getOracleSetUpdateClaimMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<fx.gravity.crosschain.v1.Tx.MsgBridgeTokenClaimResponse> bridgeTokenClaim(
        fx.gravity.crosschain.v1.Tx.MsgBridgeTokenClaim request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getBridgeTokenClaimMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<fx.gravity.crosschain.v1.Tx.MsgSendToFxClaimResponse> sendToFxClaim(
        fx.gravity.crosschain.v1.Tx.MsgSendToFxClaim request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSendToFxClaimMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<fx.gravity.crosschain.v1.Tx.MsgSendToExternalResponse> sendToExternal(
        fx.gravity.crosschain.v1.Tx.MsgSendToExternal request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSendToExternalMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<fx.gravity.crosschain.v1.Tx.MsgCancelSendToExternalResponse> cancelSendToExternal(
        fx.gravity.crosschain.v1.Tx.MsgCancelSendToExternal request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCancelSendToExternalMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<fx.gravity.crosschain.v1.Tx.MsgSendToExternalClaimResponse> sendToExternalClaim(
        fx.gravity.crosschain.v1.Tx.MsgSendToExternalClaim request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSendToExternalClaimMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<fx.gravity.crosschain.v1.Tx.MsgRequestBatchResponse> requestBatch(
        fx.gravity.crosschain.v1.Tx.MsgRequestBatch request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRequestBatchMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<fx.gravity.crosschain.v1.Tx.MsgConfirmBatchResponse> confirmBatch(
        fx.gravity.crosschain.v1.Tx.MsgConfirmBatch request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getConfirmBatchMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_BONDED_ORACLE = 0;
  private static final int METHODID_ADD_DELEGATE = 1;
  private static final int METHODID_EDIT_ORACLE = 2;
  private static final int METHODID_WITHDRAW_REWARD = 3;
  private static final int METHODID_UNBONDED_ORACLE = 4;
  private static final int METHODID_ORACLE_SET_CONFIRM = 5;
  private static final int METHODID_ORACLE_SET_UPDATE_CLAIM = 6;
  private static final int METHODID_BRIDGE_TOKEN_CLAIM = 7;
  private static final int METHODID_SEND_TO_FX_CLAIM = 8;
  private static final int METHODID_SEND_TO_EXTERNAL = 9;
  private static final int METHODID_CANCEL_SEND_TO_EXTERNAL = 10;
  private static final int METHODID_SEND_TO_EXTERNAL_CLAIM = 11;
  private static final int METHODID_REQUEST_BATCH = 12;
  private static final int METHODID_CONFIRM_BATCH = 13;

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
        case METHODID_BONDED_ORACLE:
          serviceImpl.bondedOracle((fx.gravity.crosschain.v1.Tx.MsgBondedOracle) request,
              (io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.Tx.MsgBondedOracleResponse>) responseObserver);
          break;
        case METHODID_ADD_DELEGATE:
          serviceImpl.addDelegate((fx.gravity.crosschain.v1.Tx.MsgAddDelegate) request,
              (io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.Tx.MsgAddDelegateResponse>) responseObserver);
          break;
        case METHODID_EDIT_ORACLE:
          serviceImpl.editOracle((fx.gravity.crosschain.v1.Tx.MsgEditOracle) request,
              (io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.Tx.MsgEditOracleResponse>) responseObserver);
          break;
        case METHODID_WITHDRAW_REWARD:
          serviceImpl.withdrawReward((fx.gravity.crosschain.v1.Tx.MsgWithdrawReward) request,
              (io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.Tx.MsgWithdrawRewardResponse>) responseObserver);
          break;
        case METHODID_UNBONDED_ORACLE:
          serviceImpl.unbondedOracle((fx.gravity.crosschain.v1.Tx.MsgUnbondedOracle) request,
              (io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.Tx.MsgUnbondedOracleResponse>) responseObserver);
          break;
        case METHODID_ORACLE_SET_CONFIRM:
          serviceImpl.oracleSetConfirm((fx.gravity.crosschain.v1.Tx.MsgOracleSetConfirm) request,
              (io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.Tx.MsgOracleSetConfirmResponse>) responseObserver);
          break;
        case METHODID_ORACLE_SET_UPDATE_CLAIM:
          serviceImpl.oracleSetUpdateClaim((fx.gravity.crosschain.v1.Tx.MsgOracleSetUpdatedClaim) request,
              (io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.Tx.MsgOracleSetUpdatedClaimResponse>) responseObserver);
          break;
        case METHODID_BRIDGE_TOKEN_CLAIM:
          serviceImpl.bridgeTokenClaim((fx.gravity.crosschain.v1.Tx.MsgBridgeTokenClaim) request,
              (io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.Tx.MsgBridgeTokenClaimResponse>) responseObserver);
          break;
        case METHODID_SEND_TO_FX_CLAIM:
          serviceImpl.sendToFxClaim((fx.gravity.crosschain.v1.Tx.MsgSendToFxClaim) request,
              (io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.Tx.MsgSendToFxClaimResponse>) responseObserver);
          break;
        case METHODID_SEND_TO_EXTERNAL:
          serviceImpl.sendToExternal((fx.gravity.crosschain.v1.Tx.MsgSendToExternal) request,
              (io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.Tx.MsgSendToExternalResponse>) responseObserver);
          break;
        case METHODID_CANCEL_SEND_TO_EXTERNAL:
          serviceImpl.cancelSendToExternal((fx.gravity.crosschain.v1.Tx.MsgCancelSendToExternal) request,
              (io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.Tx.MsgCancelSendToExternalResponse>) responseObserver);
          break;
        case METHODID_SEND_TO_EXTERNAL_CLAIM:
          serviceImpl.sendToExternalClaim((fx.gravity.crosschain.v1.Tx.MsgSendToExternalClaim) request,
              (io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.Tx.MsgSendToExternalClaimResponse>) responseObserver);
          break;
        case METHODID_REQUEST_BATCH:
          serviceImpl.requestBatch((fx.gravity.crosschain.v1.Tx.MsgRequestBatch) request,
              (io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.Tx.MsgRequestBatchResponse>) responseObserver);
          break;
        case METHODID_CONFIRM_BATCH:
          serviceImpl.confirmBatch((fx.gravity.crosschain.v1.Tx.MsgConfirmBatch) request,
              (io.grpc.stub.StreamObserver<fx.gravity.crosschain.v1.Tx.MsgConfirmBatchResponse>) responseObserver);
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
      return fx.gravity.crosschain.v1.Tx.getDescriptor();
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
              .addMethod(getBondedOracleMethod())
              .addMethod(getAddDelegateMethod())
              .addMethod(getEditOracleMethod())
              .addMethod(getWithdrawRewardMethod())
              .addMethod(getUnbondedOracleMethod())
              .addMethod(getOracleSetConfirmMethod())
              .addMethod(getOracleSetUpdateClaimMethod())
              .addMethod(getBridgeTokenClaimMethod())
              .addMethod(getSendToFxClaimMethod())
              .addMethod(getSendToExternalMethod())
              .addMethod(getCancelSendToExternalMethod())
              .addMethod(getSendToExternalClaimMethod())
              .addMethod(getRequestBatchMethod())
              .addMethod(getConfirmBatchMethod())
              .build();
        }
      }
    }
    return result;
  }
}
