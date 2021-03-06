package fx.migrate.v1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Query provides defines the gRPC querier service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.44.1)",
    comments = "Source: fx/migrate/v1/query.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "fx.migrate.v1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<fx.migrate.v1.QueryOuterClass.QueryMigrateRecordRequest,
      fx.migrate.v1.QueryOuterClass.QueryMigrateRecordResponse> getMigrateRecordMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "MigrateRecord",
      requestType = fx.migrate.v1.QueryOuterClass.QueryMigrateRecordRequest.class,
      responseType = fx.migrate.v1.QueryOuterClass.QueryMigrateRecordResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.migrate.v1.QueryOuterClass.QueryMigrateRecordRequest,
      fx.migrate.v1.QueryOuterClass.QueryMigrateRecordResponse> getMigrateRecordMethod() {
    io.grpc.MethodDescriptor<fx.migrate.v1.QueryOuterClass.QueryMigrateRecordRequest, fx.migrate.v1.QueryOuterClass.QueryMigrateRecordResponse> getMigrateRecordMethod;
    if ((getMigrateRecordMethod = QueryGrpc.getMigrateRecordMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getMigrateRecordMethod = QueryGrpc.getMigrateRecordMethod) == null) {
          QueryGrpc.getMigrateRecordMethod = getMigrateRecordMethod =
              io.grpc.MethodDescriptor.<fx.migrate.v1.QueryOuterClass.QueryMigrateRecordRequest, fx.migrate.v1.QueryOuterClass.QueryMigrateRecordResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "MigrateRecord"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.migrate.v1.QueryOuterClass.QueryMigrateRecordRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.migrate.v1.QueryOuterClass.QueryMigrateRecordResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("MigrateRecord"))
              .build();
        }
      }
    }
    return getMigrateRecordMethod;
  }

  private static volatile io.grpc.MethodDescriptor<fx.migrate.v1.QueryOuterClass.QueryMigrateCheckAccountRequest,
      fx.migrate.v1.QueryOuterClass.QueryMigrateCheckAccountResponse> getMigrateCheckAccountMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "MigrateCheckAccount",
      requestType = fx.migrate.v1.QueryOuterClass.QueryMigrateCheckAccountRequest.class,
      responseType = fx.migrate.v1.QueryOuterClass.QueryMigrateCheckAccountResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fx.migrate.v1.QueryOuterClass.QueryMigrateCheckAccountRequest,
      fx.migrate.v1.QueryOuterClass.QueryMigrateCheckAccountResponse> getMigrateCheckAccountMethod() {
    io.grpc.MethodDescriptor<fx.migrate.v1.QueryOuterClass.QueryMigrateCheckAccountRequest, fx.migrate.v1.QueryOuterClass.QueryMigrateCheckAccountResponse> getMigrateCheckAccountMethod;
    if ((getMigrateCheckAccountMethod = QueryGrpc.getMigrateCheckAccountMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getMigrateCheckAccountMethod = QueryGrpc.getMigrateCheckAccountMethod) == null) {
          QueryGrpc.getMigrateCheckAccountMethod = getMigrateCheckAccountMethod =
              io.grpc.MethodDescriptor.<fx.migrate.v1.QueryOuterClass.QueryMigrateCheckAccountRequest, fx.migrate.v1.QueryOuterClass.QueryMigrateCheckAccountResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "MigrateCheckAccount"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.migrate.v1.QueryOuterClass.QueryMigrateCheckAccountRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fx.migrate.v1.QueryOuterClass.QueryMigrateCheckAccountResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("MigrateCheckAccount"))
              .build();
        }
      }
    }
    return getMigrateCheckAccountMethod;
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
    public void migrateRecord(fx.migrate.v1.QueryOuterClass.QueryMigrateRecordRequest request,
        io.grpc.stub.StreamObserver<fx.migrate.v1.QueryOuterClass.QueryMigrateRecordResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getMigrateRecordMethod(), responseObserver);
    }

    /**
     */
    public void migrateCheckAccount(fx.migrate.v1.QueryOuterClass.QueryMigrateCheckAccountRequest request,
        io.grpc.stub.StreamObserver<fx.migrate.v1.QueryOuterClass.QueryMigrateCheckAccountResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getMigrateCheckAccountMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getMigrateRecordMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.migrate.v1.QueryOuterClass.QueryMigrateRecordRequest,
                fx.migrate.v1.QueryOuterClass.QueryMigrateRecordResponse>(
                  this, METHODID_MIGRATE_RECORD)))
          .addMethod(
            getMigrateCheckAccountMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                fx.migrate.v1.QueryOuterClass.QueryMigrateCheckAccountRequest,
                fx.migrate.v1.QueryOuterClass.QueryMigrateCheckAccountResponse>(
                  this, METHODID_MIGRATE_CHECK_ACCOUNT)))
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
    public void migrateRecord(fx.migrate.v1.QueryOuterClass.QueryMigrateRecordRequest request,
        io.grpc.stub.StreamObserver<fx.migrate.v1.QueryOuterClass.QueryMigrateRecordResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getMigrateRecordMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void migrateCheckAccount(fx.migrate.v1.QueryOuterClass.QueryMigrateCheckAccountRequest request,
        io.grpc.stub.StreamObserver<fx.migrate.v1.QueryOuterClass.QueryMigrateCheckAccountResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getMigrateCheckAccountMethod(), getCallOptions()), request, responseObserver);
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
    public fx.migrate.v1.QueryOuterClass.QueryMigrateRecordResponse migrateRecord(fx.migrate.v1.QueryOuterClass.QueryMigrateRecordRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getMigrateRecordMethod(), getCallOptions(), request);
    }

    /**
     */
    public fx.migrate.v1.QueryOuterClass.QueryMigrateCheckAccountResponse migrateCheckAccount(fx.migrate.v1.QueryOuterClass.QueryMigrateCheckAccountRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getMigrateCheckAccountMethod(), getCallOptions(), request);
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
    public com.google.common.util.concurrent.ListenableFuture<fx.migrate.v1.QueryOuterClass.QueryMigrateRecordResponse> migrateRecord(
        fx.migrate.v1.QueryOuterClass.QueryMigrateRecordRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getMigrateRecordMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<fx.migrate.v1.QueryOuterClass.QueryMigrateCheckAccountResponse> migrateCheckAccount(
        fx.migrate.v1.QueryOuterClass.QueryMigrateCheckAccountRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getMigrateCheckAccountMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_MIGRATE_RECORD = 0;
  private static final int METHODID_MIGRATE_CHECK_ACCOUNT = 1;

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
        case METHODID_MIGRATE_RECORD:
          serviceImpl.migrateRecord((fx.migrate.v1.QueryOuterClass.QueryMigrateRecordRequest) request,
              (io.grpc.stub.StreamObserver<fx.migrate.v1.QueryOuterClass.QueryMigrateRecordResponse>) responseObserver);
          break;
        case METHODID_MIGRATE_CHECK_ACCOUNT:
          serviceImpl.migrateCheckAccount((fx.migrate.v1.QueryOuterClass.QueryMigrateCheckAccountRequest) request,
              (io.grpc.stub.StreamObserver<fx.migrate.v1.QueryOuterClass.QueryMigrateCheckAccountResponse>) responseObserver);
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
      return fx.migrate.v1.QueryOuterClass.getDescriptor();
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
              .addMethod(getMigrateRecordMethod())
              .addMethod(getMigrateCheckAccountMethod())
              .build();
        }
      }
    }
    return result;
  }
}
