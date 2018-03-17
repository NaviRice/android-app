// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: response.proto

package navirice.proto;

public final class ResponseOuterClass {
  private ResponseOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }
  public interface ResponseOrBuilder extends
      // @@protoc_insertion_point(interface_extends:navirice.proto.Response)
      com.google.protobuf.MessageLiteOrBuilder {

    /**
     * <code>optional .navirice.proto.Response.Status status = 1;</code>
     */
    int getStatusValue();
    /**
     * <code>optional .navirice.proto.Response.Status status = 1;</code>
     */
    navirice.proto.ResponseOuterClass.Response.Status getStatus();
  }
  /**
   * Protobuf type {@code navirice.proto.Response}
   */
  public  static final class Response extends
      com.google.protobuf.GeneratedMessageLite<
          Response, Response.Builder> implements
      // @@protoc_insertion_point(message_implements:navirice.proto.Response)
      ResponseOrBuilder {
    private Response() {
    }
    /**
     * Protobuf enum {@code navirice.proto.Response.Status}
     */
    public enum Status
        implements com.google.protobuf.Internal.EnumLite {
      /**
       * <code>SUCCESS = 0;</code>
       */
      SUCCESS(0),
      /**
       * <code>BAD_REQUEST = 1;</code>
       */
      BAD_REQUEST(1),
      /**
       * <code>NOT_IMPLEMENTED = 2;</code>
       */
      NOT_IMPLEMENTED(2),
      UNRECOGNIZED(-1),
      ;

      /**
       * <code>SUCCESS = 0;</code>
       */
      public static final int SUCCESS_VALUE = 0;
      /**
       * <code>BAD_REQUEST = 1;</code>
       */
      public static final int BAD_REQUEST_VALUE = 1;
      /**
       * <code>NOT_IMPLEMENTED = 2;</code>
       */
      public static final int NOT_IMPLEMENTED_VALUE = 2;


      public final int getNumber() {
        return value;
      }

      /**
       * @deprecated Use {@link #forNumber(int)} instead.
       */
      @java.lang.Deprecated
      public static Status valueOf(int value) {
        return forNumber(value);
      }

      public static Status forNumber(int value) {
        switch (value) {
          case 0: return SUCCESS;
          case 1: return BAD_REQUEST;
          case 2: return NOT_IMPLEMENTED;
          default: return null;
        }
      }

      public static com.google.protobuf.Internal.EnumLiteMap<Status>
          internalGetValueMap() {
        return internalValueMap;
      }
      private static final com.google.protobuf.Internal.EnumLiteMap<
          Status> internalValueMap =
            new com.google.protobuf.Internal.EnumLiteMap<Status>() {
              public Status findValueByNumber(int number) {
                return Status.forNumber(number);
              }
            };

      private final int value;

      private Status(int value) {
        this.value = value;
      }

      // @@protoc_insertion_point(enum_scope:navirice.proto.Response.Status)
    }

    public static final int STATUS_FIELD_NUMBER = 1;
    private int status_;
    /**
     * <code>optional .navirice.proto.Response.Status status = 1;</code>
     */
    public int getStatusValue() {
      return status_;
    }
    /**
     * <code>optional .navirice.proto.Response.Status status = 1;</code>
     */
    public navirice.proto.ResponseOuterClass.Response.Status getStatus() {
      navirice.proto.ResponseOuterClass.Response.Status result = navirice.proto.ResponseOuterClass.Response.Status.forNumber(status_);
      return result == null ? navirice.proto.ResponseOuterClass.Response.Status.UNRECOGNIZED : result;
    }
    /**
     * <code>optional .navirice.proto.Response.Status status = 1;</code>
     */
    private void setStatusValue(int value) {
        status_ = value;
    }
    /**
     * <code>optional .navirice.proto.Response.Status status = 1;</code>
     */
    private void setStatus(navirice.proto.ResponseOuterClass.Response.Status value) {
      if (value == null) {
        throw new NullPointerException();
      }
      
      status_ = value.getNumber();
    }
    /**
     * <code>optional .navirice.proto.Response.Status status = 1;</code>
     */
    private void clearStatus() {
      
      status_ = 0;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (status_ != navirice.proto.ResponseOuterClass.Response.Status.SUCCESS.getNumber()) {
        output.writeEnum(1, status_);
      }
    }

    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;

      size = 0;
      if (status_ != navirice.proto.ResponseOuterClass.Response.Status.SUCCESS.getNumber()) {
        size += com.google.protobuf.CodedOutputStream
          .computeEnumSize(1, status_);
      }
      memoizedSerializedSize = size;
      return size;
    }

    public static navirice.proto.ResponseOuterClass.Response parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, data);
    }
    public static navirice.proto.ResponseOuterClass.Response parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, data, extensionRegistry);
    }
    public static navirice.proto.ResponseOuterClass.Response parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, data);
    }
    public static navirice.proto.ResponseOuterClass.Response parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, data, extensionRegistry);
    }
    public static navirice.proto.ResponseOuterClass.Response parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, input);
    }
    public static navirice.proto.ResponseOuterClass.Response parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, input, extensionRegistry);
    }
    public static navirice.proto.ResponseOuterClass.Response parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }
    public static navirice.proto.ResponseOuterClass.Response parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }
    public static navirice.proto.ResponseOuterClass.Response parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, input);
    }
    public static navirice.proto.ResponseOuterClass.Response parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(navirice.proto.ResponseOuterClass.Response prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }

    /**
     * Protobuf type {@code navirice.proto.Response}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageLite.Builder<
          navirice.proto.ResponseOuterClass.Response, Builder> implements
        // @@protoc_insertion_point(builder_implements:navirice.proto.Response)
        navirice.proto.ResponseOuterClass.ResponseOrBuilder {
      // Construct using navirice.proto.ResponseOuterClass.Response.newBuilder()
      private Builder() {
        super(DEFAULT_INSTANCE);
      }


      /**
       * <code>optional .navirice.proto.Response.Status status = 1;</code>
       */
      public int getStatusValue() {
        return instance.getStatusValue();
      }
      /**
       * <code>optional .navirice.proto.Response.Status status = 1;</code>
       */
      public Builder setStatusValue(int value) {
        copyOnWrite();
        instance.setStatusValue(value);
        return this;
      }
      /**
       * <code>optional .navirice.proto.Response.Status status = 1;</code>
       */
      public navirice.proto.ResponseOuterClass.Response.Status getStatus() {
        return instance.getStatus();
      }
      /**
       * <code>optional .navirice.proto.Response.Status status = 1;</code>
       */
      public Builder setStatus(navirice.proto.ResponseOuterClass.Response.Status value) {
        copyOnWrite();
        instance.setStatus(value);
        return this;
      }
      /**
       * <code>optional .navirice.proto.Response.Status status = 1;</code>
       */
      public Builder clearStatus() {
        copyOnWrite();
        instance.clearStatus();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:navirice.proto.Response)
    }
    protected final Object dynamicMethod(
        com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
        Object arg0, Object arg1) {
      switch (method) {
        case NEW_MUTABLE_INSTANCE: {
          return new navirice.proto.ResponseOuterClass.Response();
        }
        case IS_INITIALIZED: {
          return DEFAULT_INSTANCE;
        }
        case MAKE_IMMUTABLE: {
          return null;
        }
        case NEW_BUILDER: {
          return new Builder();
        }
        case VISIT: {
          Visitor visitor = (Visitor) arg0;
          navirice.proto.ResponseOuterClass.Response other = (navirice.proto.ResponseOuterClass.Response) arg1;
          status_ = visitor.visitInt(status_ != 0, status_,    other.status_ != 0, other.status_);
          if (visitor == com.google.protobuf.GeneratedMessageLite.MergeFromVisitor
              .INSTANCE) {
          }
          return this;
        }
        case MERGE_FROM_STREAM: {
          com.google.protobuf.CodedInputStream input =
              (com.google.protobuf.CodedInputStream) arg0;
          com.google.protobuf.ExtensionRegistryLite extensionRegistry =
              (com.google.protobuf.ExtensionRegistryLite) arg1;
          try {
            boolean done = false;
            while (!done) {
              int tag = input.readTag();
              switch (tag) {
                case 0:
                  done = true;
                  break;
                default: {
                  if (!input.skipField(tag)) {
                    done = true;
                  }
                  break;
                }
                case 8: {
                  int rawValue = input.readEnum();

                  status_ = rawValue;
                  break;
                }
              }
            }
          } catch (com.google.protobuf.InvalidProtocolBufferException e) {
            throw new RuntimeException(e.setUnfinishedMessage(this));
          } catch (java.io.IOException e) {
            throw new RuntimeException(
                new com.google.protobuf.InvalidProtocolBufferException(
                    e.getMessage()).setUnfinishedMessage(this));
          } finally {
          }
        }
        case GET_DEFAULT_INSTANCE: {
          return DEFAULT_INSTANCE;
        }
        case GET_PARSER: {
          if (PARSER == null) {    synchronized (navirice.proto.ResponseOuterClass.Response.class) {
              if (PARSER == null) {
                PARSER = new DefaultInstanceBasedParser(DEFAULT_INSTANCE);
              }
            }
          }
          return PARSER;
        }
      }
      throw new UnsupportedOperationException();
    }


    // @@protoc_insertion_point(class_scope:navirice.proto.Response)
    private static final navirice.proto.ResponseOuterClass.Response DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new Response();
      DEFAULT_INSTANCE.makeImmutable();
    }

    public static navirice.proto.ResponseOuterClass.Response getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static volatile com.google.protobuf.Parser<Response> PARSER;

    public static com.google.protobuf.Parser<Response> parser() {
      return DEFAULT_INSTANCE.getParserForType();
    }
  }


  static {
  }

  // @@protoc_insertion_point(outer_class_scope)
}
