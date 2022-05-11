package cosmos.gov.v1beta2;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Query defines the gRPC querier service for gov module
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.44.1)",
    comments = "Source: cosmos/gov/v1beta2/query.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "cosmos.gov.v1beta2.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryProposalRequest,
      cosmos.gov.v1beta2.CosmosGovQueryProto.QueryProposalResponse> getProposalMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Proposal",
      requestType = cosmos.gov.v1beta2.CosmosGovQueryProto.QueryProposalRequest.class,
      responseType = cosmos.gov.v1beta2.CosmosGovQueryProto.QueryProposalResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryProposalRequest,
      cosmos.gov.v1beta2.CosmosGovQueryProto.QueryProposalResponse> getProposalMethod() {
    io.grpc.MethodDescriptor<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryProposalRequest, cosmos.gov.v1beta2.CosmosGovQueryProto.QueryProposalResponse> getProposalMethod;
    if ((getProposalMethod = QueryGrpc.getProposalMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getProposalMethod = QueryGrpc.getProposalMethod) == null) {
          QueryGrpc.getProposalMethod = getProposalMethod =
              io.grpc.MethodDescriptor.<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryProposalRequest, cosmos.gov.v1beta2.CosmosGovQueryProto.QueryProposalResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Proposal"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.gov.v1beta2.CosmosGovQueryProto.QueryProposalRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.gov.v1beta2.CosmosGovQueryProto.QueryProposalResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Proposal"))
              .build();
        }
      }
    }
    return getProposalMethod;
  }

  private static volatile io.grpc.MethodDescriptor<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryProposalsRequest,
      cosmos.gov.v1beta2.CosmosGovQueryProto.QueryProposalsResponse> getProposalsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Proposals",
      requestType = cosmos.gov.v1beta2.CosmosGovQueryProto.QueryProposalsRequest.class,
      responseType = cosmos.gov.v1beta2.CosmosGovQueryProto.QueryProposalsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryProposalsRequest,
      cosmos.gov.v1beta2.CosmosGovQueryProto.QueryProposalsResponse> getProposalsMethod() {
    io.grpc.MethodDescriptor<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryProposalsRequest, cosmos.gov.v1beta2.CosmosGovQueryProto.QueryProposalsResponse> getProposalsMethod;
    if ((getProposalsMethod = QueryGrpc.getProposalsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getProposalsMethod = QueryGrpc.getProposalsMethod) == null) {
          QueryGrpc.getProposalsMethod = getProposalsMethod =
              io.grpc.MethodDescriptor.<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryProposalsRequest, cosmos.gov.v1beta2.CosmosGovQueryProto.QueryProposalsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Proposals"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.gov.v1beta2.CosmosGovQueryProto.QueryProposalsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.gov.v1beta2.CosmosGovQueryProto.QueryProposalsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Proposals"))
              .build();
        }
      }
    }
    return getProposalsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVoteRequest,
      cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVoteResponse> getVoteMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Vote",
      requestType = cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVoteRequest.class,
      responseType = cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVoteResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVoteRequest,
      cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVoteResponse> getVoteMethod() {
    io.grpc.MethodDescriptor<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVoteRequest, cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVoteResponse> getVoteMethod;
    if ((getVoteMethod = QueryGrpc.getVoteMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getVoteMethod = QueryGrpc.getVoteMethod) == null) {
          QueryGrpc.getVoteMethod = getVoteMethod =
              io.grpc.MethodDescriptor.<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVoteRequest, cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVoteResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Vote"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVoteRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVoteResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Vote"))
              .build();
        }
      }
    }
    return getVoteMethod;
  }

  private static volatile io.grpc.MethodDescriptor<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVotesRequest,
      cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVotesResponse> getVotesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Votes",
      requestType = cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVotesRequest.class,
      responseType = cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVotesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVotesRequest,
      cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVotesResponse> getVotesMethod() {
    io.grpc.MethodDescriptor<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVotesRequest, cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVotesResponse> getVotesMethod;
    if ((getVotesMethod = QueryGrpc.getVotesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getVotesMethod = QueryGrpc.getVotesMethod) == null) {
          QueryGrpc.getVotesMethod = getVotesMethod =
              io.grpc.MethodDescriptor.<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVotesRequest, cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVotesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Votes"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVotesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVotesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Votes"))
              .build();
        }
      }
    }
    return getVotesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryParamsRequest,
      cosmos.gov.v1beta2.CosmosGovQueryProto.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = cosmos.gov.v1beta2.CosmosGovQueryProto.QueryParamsRequest.class,
      responseType = cosmos.gov.v1beta2.CosmosGovQueryProto.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryParamsRequest,
      cosmos.gov.v1beta2.CosmosGovQueryProto.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryParamsRequest, cosmos.gov.v1beta2.CosmosGovQueryProto.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryParamsRequest, cosmos.gov.v1beta2.CosmosGovQueryProto.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.gov.v1beta2.CosmosGovQueryProto.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.gov.v1beta2.CosmosGovQueryProto.QueryParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Params"))
              .build();
        }
      }
    }
    return getParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryDepositRequest,
      cosmos.gov.v1beta2.CosmosGovQueryProto.QueryDepositResponse> getDepositMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Deposit",
      requestType = cosmos.gov.v1beta2.CosmosGovQueryProto.QueryDepositRequest.class,
      responseType = cosmos.gov.v1beta2.CosmosGovQueryProto.QueryDepositResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryDepositRequest,
      cosmos.gov.v1beta2.CosmosGovQueryProto.QueryDepositResponse> getDepositMethod() {
    io.grpc.MethodDescriptor<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryDepositRequest, cosmos.gov.v1beta2.CosmosGovQueryProto.QueryDepositResponse> getDepositMethod;
    if ((getDepositMethod = QueryGrpc.getDepositMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getDepositMethod = QueryGrpc.getDepositMethod) == null) {
          QueryGrpc.getDepositMethod = getDepositMethod =
              io.grpc.MethodDescriptor.<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryDepositRequest, cosmos.gov.v1beta2.CosmosGovQueryProto.QueryDepositResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Deposit"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.gov.v1beta2.CosmosGovQueryProto.QueryDepositRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.gov.v1beta2.CosmosGovQueryProto.QueryDepositResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Deposit"))
              .build();
        }
      }
    }
    return getDepositMethod;
  }

  private static volatile io.grpc.MethodDescriptor<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryDepositsRequest,
      cosmos.gov.v1beta2.CosmosGovQueryProto.QueryDepositsResponse> getDepositsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Deposits",
      requestType = cosmos.gov.v1beta2.CosmosGovQueryProto.QueryDepositsRequest.class,
      responseType = cosmos.gov.v1beta2.CosmosGovQueryProto.QueryDepositsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryDepositsRequest,
      cosmos.gov.v1beta2.CosmosGovQueryProto.QueryDepositsResponse> getDepositsMethod() {
    io.grpc.MethodDescriptor<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryDepositsRequest, cosmos.gov.v1beta2.CosmosGovQueryProto.QueryDepositsResponse> getDepositsMethod;
    if ((getDepositsMethod = QueryGrpc.getDepositsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getDepositsMethod = QueryGrpc.getDepositsMethod) == null) {
          QueryGrpc.getDepositsMethod = getDepositsMethod =
              io.grpc.MethodDescriptor.<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryDepositsRequest, cosmos.gov.v1beta2.CosmosGovQueryProto.QueryDepositsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Deposits"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.gov.v1beta2.CosmosGovQueryProto.QueryDepositsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.gov.v1beta2.CosmosGovQueryProto.QueryDepositsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Deposits"))
              .build();
        }
      }
    }
    return getDepositsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryTallyResultRequest,
      cosmos.gov.v1beta2.CosmosGovQueryProto.QueryTallyResultResponse> getTallyResultMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "TallyResult",
      requestType = cosmos.gov.v1beta2.CosmosGovQueryProto.QueryTallyResultRequest.class,
      responseType = cosmos.gov.v1beta2.CosmosGovQueryProto.QueryTallyResultResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryTallyResultRequest,
      cosmos.gov.v1beta2.CosmosGovQueryProto.QueryTallyResultResponse> getTallyResultMethod() {
    io.grpc.MethodDescriptor<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryTallyResultRequest, cosmos.gov.v1beta2.CosmosGovQueryProto.QueryTallyResultResponse> getTallyResultMethod;
    if ((getTallyResultMethod = QueryGrpc.getTallyResultMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getTallyResultMethod = QueryGrpc.getTallyResultMethod) == null) {
          QueryGrpc.getTallyResultMethod = getTallyResultMethod =
              io.grpc.MethodDescriptor.<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryTallyResultRequest, cosmos.gov.v1beta2.CosmosGovQueryProto.QueryTallyResultResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "TallyResult"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.gov.v1beta2.CosmosGovQueryProto.QueryTallyResultRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.gov.v1beta2.CosmosGovQueryProto.QueryTallyResultResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("TallyResult"))
              .build();
        }
      }
    }
    return getTallyResultMethod;
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
   * Query defines the gRPC querier service for gov module
   * </pre>
   */
  public static abstract class QueryImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Proposal queries proposal details based on ProposalID.
     * </pre>
     */
    public void proposal(cosmos.gov.v1beta2.CosmosGovQueryProto.QueryProposalRequest request,
        io.grpc.stub.StreamObserver<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryProposalResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getProposalMethod(), responseObserver);
    }

    /**
     * <pre>
     * Proposals queries all proposals based on given status.
     * </pre>
     */
    public void proposals(cosmos.gov.v1beta2.CosmosGovQueryProto.QueryProposalsRequest request,
        io.grpc.stub.StreamObserver<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryProposalsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getProposalsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Vote queries voted information based on proposalID, voterAddr.
     * </pre>
     */
    public void vote(cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVoteRequest request,
        io.grpc.stub.StreamObserver<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVoteResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getVoteMethod(), responseObserver);
    }

    /**
     * <pre>
     * Votes queries votes of a given proposal.
     * </pre>
     */
    public void votes(cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVotesRequest request,
        io.grpc.stub.StreamObserver<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVotesResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getVotesMethod(), responseObserver);
    }

    /**
     * <pre>
     * Params queries all parameters of the gov module.
     * </pre>
     */
    public void params(cosmos.gov.v1beta2.CosmosGovQueryProto.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Deposit queries single deposit information based proposalID, depositAddr.
     * </pre>
     */
    public void deposit(cosmos.gov.v1beta2.CosmosGovQueryProto.QueryDepositRequest request,
        io.grpc.stub.StreamObserver<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryDepositResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDepositMethod(), responseObserver);
    }

    /**
     * <pre>
     * Deposits queries all deposits of a single proposal.
     * </pre>
     */
    public void deposits(cosmos.gov.v1beta2.CosmosGovQueryProto.QueryDepositsRequest request,
        io.grpc.stub.StreamObserver<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryDepositsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDepositsMethod(), responseObserver);
    }

    /**
     * <pre>
     * TallyResult queries the tally of a proposal vote.
     * </pre>
     */
    public void tallyResult(cosmos.gov.v1beta2.CosmosGovQueryProto.QueryTallyResultRequest request,
        io.grpc.stub.StreamObserver<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryTallyResultResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getTallyResultMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getProposalMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                cosmos.gov.v1beta2.CosmosGovQueryProto.QueryProposalRequest,
                cosmos.gov.v1beta2.CosmosGovQueryProto.QueryProposalResponse>(
                  this, METHODID_PROPOSAL)))
          .addMethod(
            getProposalsMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                cosmos.gov.v1beta2.CosmosGovQueryProto.QueryProposalsRequest,
                cosmos.gov.v1beta2.CosmosGovQueryProto.QueryProposalsResponse>(
                  this, METHODID_PROPOSALS)))
          .addMethod(
            getVoteMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVoteRequest,
                cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVoteResponse>(
                  this, METHODID_VOTE)))
          .addMethod(
            getVotesMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVotesRequest,
                cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVotesResponse>(
                  this, METHODID_VOTES)))
          .addMethod(
            getParamsMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                cosmos.gov.v1beta2.CosmosGovQueryProto.QueryParamsRequest,
                cosmos.gov.v1beta2.CosmosGovQueryProto.QueryParamsResponse>(
                  this, METHODID_PARAMS)))
          .addMethod(
            getDepositMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                cosmos.gov.v1beta2.CosmosGovQueryProto.QueryDepositRequest,
                cosmos.gov.v1beta2.CosmosGovQueryProto.QueryDepositResponse>(
                  this, METHODID_DEPOSIT)))
          .addMethod(
            getDepositsMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                cosmos.gov.v1beta2.CosmosGovQueryProto.QueryDepositsRequest,
                cosmos.gov.v1beta2.CosmosGovQueryProto.QueryDepositsResponse>(
                  this, METHODID_DEPOSITS)))
          .addMethod(
            getTallyResultMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                cosmos.gov.v1beta2.CosmosGovQueryProto.QueryTallyResultRequest,
                cosmos.gov.v1beta2.CosmosGovQueryProto.QueryTallyResultResponse>(
                  this, METHODID_TALLY_RESULT)))
          .build();
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service for gov module
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
     * Proposal queries proposal details based on ProposalID.
     * </pre>
     */
    public void proposal(cosmos.gov.v1beta2.CosmosGovQueryProto.QueryProposalRequest request,
        io.grpc.stub.StreamObserver<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryProposalResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getProposalMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Proposals queries all proposals based on given status.
     * </pre>
     */
    public void proposals(cosmos.gov.v1beta2.CosmosGovQueryProto.QueryProposalsRequest request,
        io.grpc.stub.StreamObserver<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryProposalsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getProposalsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Vote queries voted information based on proposalID, voterAddr.
     * </pre>
     */
    public void vote(cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVoteRequest request,
        io.grpc.stub.StreamObserver<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVoteResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getVoteMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Votes queries votes of a given proposal.
     * </pre>
     */
    public void votes(cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVotesRequest request,
        io.grpc.stub.StreamObserver<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVotesResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getVotesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Params queries all parameters of the gov module.
     * </pre>
     */
    public void params(cosmos.gov.v1beta2.CosmosGovQueryProto.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Deposit queries single deposit information based proposalID, depositAddr.
     * </pre>
     */
    public void deposit(cosmos.gov.v1beta2.CosmosGovQueryProto.QueryDepositRequest request,
        io.grpc.stub.StreamObserver<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryDepositResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDepositMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Deposits queries all deposits of a single proposal.
     * </pre>
     */
    public void deposits(cosmos.gov.v1beta2.CosmosGovQueryProto.QueryDepositsRequest request,
        io.grpc.stub.StreamObserver<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryDepositsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDepositsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * TallyResult queries the tally of a proposal vote.
     * </pre>
     */
    public void tallyResult(cosmos.gov.v1beta2.CosmosGovQueryProto.QueryTallyResultRequest request,
        io.grpc.stub.StreamObserver<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryTallyResultResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getTallyResultMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service for gov module
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
     * Proposal queries proposal details based on ProposalID.
     * </pre>
     */
    public cosmos.gov.v1beta2.CosmosGovQueryProto.QueryProposalResponse proposal(cosmos.gov.v1beta2.CosmosGovQueryProto.QueryProposalRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getProposalMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Proposals queries all proposals based on given status.
     * </pre>
     */
    public cosmos.gov.v1beta2.CosmosGovQueryProto.QueryProposalsResponse proposals(cosmos.gov.v1beta2.CosmosGovQueryProto.QueryProposalsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getProposalsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Vote queries voted information based on proposalID, voterAddr.
     * </pre>
     */
    public cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVoteResponse vote(cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVoteRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getVoteMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Votes queries votes of a given proposal.
     * </pre>
     */
    public cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVotesResponse votes(cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVotesRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getVotesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Params queries all parameters of the gov module.
     * </pre>
     */
    public cosmos.gov.v1beta2.CosmosGovQueryProto.QueryParamsResponse params(cosmos.gov.v1beta2.CosmosGovQueryProto.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Deposit queries single deposit information based proposalID, depositAddr.
     * </pre>
     */
    public cosmos.gov.v1beta2.CosmosGovQueryProto.QueryDepositResponse deposit(cosmos.gov.v1beta2.CosmosGovQueryProto.QueryDepositRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDepositMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Deposits queries all deposits of a single proposal.
     * </pre>
     */
    public cosmos.gov.v1beta2.CosmosGovQueryProto.QueryDepositsResponse deposits(cosmos.gov.v1beta2.CosmosGovQueryProto.QueryDepositsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDepositsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * TallyResult queries the tally of a proposal vote.
     * </pre>
     */
    public cosmos.gov.v1beta2.CosmosGovQueryProto.QueryTallyResultResponse tallyResult(cosmos.gov.v1beta2.CosmosGovQueryProto.QueryTallyResultRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getTallyResultMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service for gov module
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
     * Proposal queries proposal details based on ProposalID.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryProposalResponse> proposal(
        cosmos.gov.v1beta2.CosmosGovQueryProto.QueryProposalRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getProposalMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Proposals queries all proposals based on given status.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryProposalsResponse> proposals(
        cosmos.gov.v1beta2.CosmosGovQueryProto.QueryProposalsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getProposalsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Vote queries voted information based on proposalID, voterAddr.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVoteResponse> vote(
        cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVoteRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getVoteMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Votes queries votes of a given proposal.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVotesResponse> votes(
        cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVotesRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getVotesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Params queries all parameters of the gov module.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryParamsResponse> params(
        cosmos.gov.v1beta2.CosmosGovQueryProto.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Deposit queries single deposit information based proposalID, depositAddr.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryDepositResponse> deposit(
        cosmos.gov.v1beta2.CosmosGovQueryProto.QueryDepositRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDepositMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Deposits queries all deposits of a single proposal.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryDepositsResponse> deposits(
        cosmos.gov.v1beta2.CosmosGovQueryProto.QueryDepositsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDepositsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * TallyResult queries the tally of a proposal vote.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryTallyResultResponse> tallyResult(
        cosmos.gov.v1beta2.CosmosGovQueryProto.QueryTallyResultRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getTallyResultMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PROPOSAL = 0;
  private static final int METHODID_PROPOSALS = 1;
  private static final int METHODID_VOTE = 2;
  private static final int METHODID_VOTES = 3;
  private static final int METHODID_PARAMS = 4;
  private static final int METHODID_DEPOSIT = 5;
  private static final int METHODID_DEPOSITS = 6;
  private static final int METHODID_TALLY_RESULT = 7;

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
        case METHODID_PROPOSAL:
          serviceImpl.proposal((cosmos.gov.v1beta2.CosmosGovQueryProto.QueryProposalRequest) request,
              (io.grpc.stub.StreamObserver<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryProposalResponse>) responseObserver);
          break;
        case METHODID_PROPOSALS:
          serviceImpl.proposals((cosmos.gov.v1beta2.CosmosGovQueryProto.QueryProposalsRequest) request,
              (io.grpc.stub.StreamObserver<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryProposalsResponse>) responseObserver);
          break;
        case METHODID_VOTE:
          serviceImpl.vote((cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVoteRequest) request,
              (io.grpc.stub.StreamObserver<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVoteResponse>) responseObserver);
          break;
        case METHODID_VOTES:
          serviceImpl.votes((cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVotesRequest) request,
              (io.grpc.stub.StreamObserver<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVotesResponse>) responseObserver);
          break;
        case METHODID_PARAMS:
          serviceImpl.params((cosmos.gov.v1beta2.CosmosGovQueryProto.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryParamsResponse>) responseObserver);
          break;
        case METHODID_DEPOSIT:
          serviceImpl.deposit((cosmos.gov.v1beta2.CosmosGovQueryProto.QueryDepositRequest) request,
              (io.grpc.stub.StreamObserver<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryDepositResponse>) responseObserver);
          break;
        case METHODID_DEPOSITS:
          serviceImpl.deposits((cosmos.gov.v1beta2.CosmosGovQueryProto.QueryDepositsRequest) request,
              (io.grpc.stub.StreamObserver<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryDepositsResponse>) responseObserver);
          break;
        case METHODID_TALLY_RESULT:
          serviceImpl.tallyResult((cosmos.gov.v1beta2.CosmosGovQueryProto.QueryTallyResultRequest) request,
              (io.grpc.stub.StreamObserver<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryTallyResultResponse>) responseObserver);
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
      return cosmos.gov.v1beta2.CosmosGovQueryProto.getDescriptor();
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
              .addMethod(getProposalMethod())
              .addMethod(getProposalsMethod())
              .addMethod(getVoteMethod())
              .addMethod(getVotesMethod())
              .addMethod(getParamsMethod())
              .addMethod(getDepositMethod())
              .addMethod(getDepositsMethod())
              .addMethod(getTallyResultMethod())
              .build();
        }
      }
    }
    return result;
  }
}
