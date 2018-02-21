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
     * <code>optional string resource = 1;</code>
     */
    java.lang.String getResource();
    /**
     * <code>optional string resource = 1;</code>
     */
    com.google.protobuf.ByteString
        getResourceBytes();

    /**
     * <code>optional .navirice.proto.Response.Status status = 2;</code>
     */
    int getStatusValue();
    /**
     * <code>optional .navirice.proto.Response.Status status = 2;</code>
     */
    navirice.proto.ResponseOuterClass.Response.Status getStatus();

    /**
     * <code>optional sint64 time = 5;</code>
     */
    long getTime();

    /**
     * <code>optional .navirice.proto.ContentType contentType = 6;</code>
     */
    int getContentTypeValue();
    /**
     * <code>optional .navirice.proto.ContentType contentType = 6;</code>
     */
    navirice.proto.ContentTypeOuterClass.ContentType getContentType();

    /**
     * <code>optional int32 payloadLength = 7;</code>
     */
    int getPayloadLength();

    /**
     * <code>optional bytes body = 8;</code>
     */
    com.google.protobuf.ByteString getBody();
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
      resource_ = "";
      body_ = com.google.protobuf.ByteString.EMPTY;
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
       * <code>FORBIDDEN = 2;</code>
       */
      FORBIDDEN(2),
      /**
       * <code>NOT_FOUND = 3;</code>
       */
      NOT_FOUND(3),
      /**
       * <code>SERVER_INTERNAL_ERROR = 4;</code>
       */
      SERVER_INTERNAL_ERROR(4),
      /**
       * <code>NOT_IMPLEMENTED = 5;</code>
       */
      NOT_IMPLEMENTED(5),
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
       * <code>FORBIDDEN = 2;</code>
       */
      public static final int FORBIDDEN_VALUE = 2;
      /**
       * <code>NOT_FOUND = 3;</code>
       */
      public static final int NOT_FOUND_VALUE = 3;
      /**
       * <code>SERVER_INTERNAL_ERROR = 4;</code>
       */
      public static final int SERVER_INTERNAL_ERROR_VALUE = 4;
      /**
       * <code>NOT_IMPLEMENTED = 5;</code>
       */
      public static final int NOT_IMPLEMENTED_VALUE = 5;


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
          case 2: return FORBIDDEN;
          case 3: return NOT_FOUND;
          case 4: return SERVER_INTERNAL_ERROR;
          case 5: return NOT_IMPLEMENTED;
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

    public static final int RESOURCE_FIELD_NUMBER = 1;
    private java.lang.String resource_;
    /**
     * <code>optional string resource = 1;</code>
     */
    public java.lang.String getResource() {
      return resource_;
    }
    /**
     * <code>optional string resource = 1;</code>
     */
    public com.google.protobuf.ByteString
        getResourceBytes() {
      return com.google.protobuf.ByteString.copyFromUtf8(resource_);
    }
    /**
     * <code>optional string resource = 1;</code>
     */
    private void setResource(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      resource_ = value;
    }
    /**
     * <code>optional string resource = 1;</code>
     */
    private void clearResource() {
      
      resource_ = getDefaultInstance().getResource();
    }
    /**
     * <code>optional string resource = 1;</code>
     */
    private void setResourceBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      resource_ = value.toStringUtf8();
    }

    public static final int STATUS_FIELD_NUMBER = 2;
    private int status_;
    /**
     * <code>optional .navirice.proto.Response.Status status = 2;</code>
     */
    public int getStatusValue() {
      return status_;
    }
    /**
     * <code>optional .navirice.proto.Response.Status status = 2;</code>
     */
    public navirice.proto.ResponseOuterClass.Response.Status getStatus() {
      navirice.proto.ResponseOuterClass.Response.Status result = navirice.proto.ResponseOuterClass.Response.Status.forNumber(status_);
      return result == null ? navirice.proto.ResponseOuterClass.Response.Status.UNRECOGNIZED : result;
    }
    /**
     * <code>optional .navirice.proto.Response.Status status = 2;</code>
     */
    private void setStatusValue(int value) {
        status_ = value;
    }
    /**
     * <code>optional .navirice.proto.Response.Status status = 2;</code>
     */
    private void setStatus(navirice.proto.ResponseOuterClass.Response.Status value) {
      if (value == null) {
        throw new NullPointerException();
      }
      
      status_ = value.getNumber();
    }
    /**
     * <code>optional .navirice.proto.Response.Status status = 2;</code>
     */
    private void clearStatus() {
      
      status_ = 0;
    }

    public static final int TIME_FIELD_NUMBER = 5;
    private long time_;
    /**
     * <code>optional sint64 time = 5;</code>
     */
    public long getTime() {
      return time_;
    }
    /**
     * <code>optional sint64 time = 5;</code>
     */
    private void setTime(long value) {
      
      time_ = value;
    }
    /**
     * <code>optional sint64 time = 5;</code>
     */
    private void clearTime() {
      
      time_ = 0L;
    }

    public static final int CONTENTTYPE_FIELD_NUMBER = 6;
    private int contentType_;
    /**
     * <code>optional .navirice.proto.ContentType contentType = 6;</code>
     */
    public int getContentTypeValue() {
      return contentType_;
    }
    /**
     * <code>optional .navirice.proto.ContentType contentType = 6;</code>
     */
    public navirice.proto.ContentTypeOuterClass.ContentType getContentType() {
      navirice.proto.ContentTypeOuterClass.ContentType result = navirice.proto.ContentTypeOuterClass.ContentType.forNumber(contentType_);
      return result == null ? navirice.proto.ContentTypeOuterClass.ContentType.UNRECOGNIZED : result;
    }
    /**
     * <code>optional .navirice.proto.ContentType contentType = 6;</code>
     */
    private void setContentTypeValue(int value) {
        contentType_ = value;
    }
    /**
     * <code>optional .navirice.proto.ContentType contentType = 6;</code>
     */
    private void setContentType(navirice.proto.ContentTypeOuterClass.ContentType value) {
      if (value == null) {
        throw new NullPointerException();
      }
      
      contentType_ = value.getNumber();
    }
    /**
     * <code>optional .navirice.proto.ContentType contentType = 6;</code>
     */
    private void clearContentType() {
      
      contentType_ = 0;
    }

    public static final int PAYLOADLENGTH_FIELD_NUMBER = 7;
    private int payloadLength_;
    /**
     * <code>optional int32 payloadLength = 7;</code>
     */
    public int getPayloadLength() {
      return payloadLength_;
    }
    /**
     * <code>optional int32 payloadLength = 7;</code>
     */
    private void setPayloadLength(int value) {
      
      payloadLength_ = value;
    }
    /**
     * <code>optional int32 payloadLength = 7;</code>
     */
    private void clearPayloadLength() {
      
      payloadLength_ = 0;
    }

    public static final int BODY_FIELD_NUMBER = 8;
    private com.google.protobuf.ByteString body_;
    /**
     * <code>optional bytes body = 8;</code>
     */
    public com.google.protobuf.ByteString getBody() {
      return body_;
    }
    /**
     * <code>optional bytes body = 8;</code>
     */
    private void setBody(com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      body_ = value;
    }
    /**
     * <code>optional bytes body = 8;</code>
     */
    private void clearBody() {
      
      body_ = getDefaultInstance().getBody();
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (!resource_.isEmpty()) {
        output.writeString(1, getResource());
      }
      if (status_ != navirice.proto.ResponseOuterClass.Response.Status.SUCCESS.getNumber()) {
        output.writeEnum(2, status_);
      }
      if (time_ != 0L) {
        output.writeSInt64(5, time_);
      }
      if (contentType_ != navirice.proto.ContentTypeOuterClass.ContentType.TEXT_PLAIN.getNumber()) {
        output.writeEnum(6, contentType_);
      }
      if (payloadLength_ != 0) {
        output.writeInt32(7, payloadLength_);
      }
      if (!body_.isEmpty()) {
        output.writeBytes(8, body_);
      }
    }

    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;

      size = 0;
      if (!resource_.isEmpty()) {
        size += com.google.protobuf.CodedOutputStream
          .computeStringSize(1, getResource());
      }
      if (status_ != navirice.proto.ResponseOuterClass.Response.Status.SUCCESS.getNumber()) {
        size += com.google.protobuf.CodedOutputStream
          .computeEnumSize(2, status_);
      }
      if (time_ != 0L) {
        size += com.google.protobuf.CodedOutputStream
          .computeSInt64Size(5, time_);
      }
      if (contentType_ != navirice.proto.ContentTypeOuterClass.ContentType.TEXT_PLAIN.getNumber()) {
        size += com.google.protobuf.CodedOutputStream
          .computeEnumSize(6, contentType_);
      }
      if (payloadLength_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(7, payloadLength_);
      }
      if (!body_.isEmpty()) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(8, body_);
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
       * <code>optional string resource = 1;</code>
       */
      public java.lang.String getResource() {
        return instance.getResource();
      }
      /**
       * <code>optional string resource = 1;</code>
       */
      public com.google.protobuf.ByteString
          getResourceBytes() {
        return instance.getResourceBytes();
      }
      /**
       * <code>optional string resource = 1;</code>
       */
      public Builder setResource(
          java.lang.String value) {
        copyOnWrite();
        instance.setResource(value);
        return this;
      }
      /**
       * <code>optional string resource = 1;</code>
       */
      public Builder clearResource() {
        copyOnWrite();
        instance.clearResource();
        return this;
      }
      /**
       * <code>optional string resource = 1;</code>
       */
      public Builder setResourceBytes(
          com.google.protobuf.ByteString value) {
        copyOnWrite();
        instance.setResourceBytes(value);
        return this;
      }

      /**
       * <code>optional .navirice.proto.Response.Status status = 2;</code>
       */
      public int getStatusValue() {
        return instance.getStatusValue();
      }
      /**
       * <code>optional .navirice.proto.Response.Status status = 2;</code>
       */
      public Builder setStatusValue(int value) {
        copyOnWrite();
        instance.setStatusValue(value);
        return this;
      }
      /**
       * <code>optional .navirice.proto.Response.Status status = 2;</code>
       */
      public navirice.proto.ResponseOuterClass.Response.Status getStatus() {
        return instance.getStatus();
      }
      /**
       * <code>optional .navirice.proto.Response.Status status = 2;</code>
       */
      public Builder setStatus(navirice.proto.ResponseOuterClass.Response.Status value) {
        copyOnWrite();
        instance.setStatus(value);
        return this;
      }
      /**
       * <code>optional .navirice.proto.Response.Status status = 2;</code>
       */
      public Builder clearStatus() {
        copyOnWrite();
        instance.clearStatus();
        return this;
      }

      /**
       * <code>optional sint64 time = 5;</code>
       */
      public long getTime() {
        return instance.getTime();
      }
      /**
       * <code>optional sint64 time = 5;</code>
       */
      public Builder setTime(long value) {
        copyOnWrite();
        instance.setTime(value);
        return this;
      }
      /**
       * <code>optional sint64 time = 5;</code>
       */
      public Builder clearTime() {
        copyOnWrite();
        instance.clearTime();
        return this;
      }

      /**
       * <code>optional .navirice.proto.ContentType contentType = 6;</code>
       */
      public int getContentTypeValue() {
        return instance.getContentTypeValue();
      }
      /**
       * <code>optional .navirice.proto.ContentType contentType = 6;</code>
       */
      public Builder setContentTypeValue(int value) {
        copyOnWrite();
        instance.setContentTypeValue(value);
        return this;
      }
      /**
       * <code>optional .navirice.proto.ContentType contentType = 6;</code>
       */
      public navirice.proto.ContentTypeOuterClass.ContentType getContentType() {
        return instance.getContentType();
      }
      /**
       * <code>optional .navirice.proto.ContentType contentType = 6;</code>
       */
      public Builder setContentType(navirice.proto.ContentTypeOuterClass.ContentType value) {
        copyOnWrite();
        instance.setContentType(value);
        return this;
      }
      /**
       * <code>optional .navirice.proto.ContentType contentType = 6;</code>
       */
      public Builder clearContentType() {
        copyOnWrite();
        instance.clearContentType();
        return this;
      }

      /**
       * <code>optional int32 payloadLength = 7;</code>
       */
      public int getPayloadLength() {
        return instance.getPayloadLength();
      }
      /**
       * <code>optional int32 payloadLength = 7;</code>
       */
      public Builder setPayloadLength(int value) {
        copyOnWrite();
        instance.setPayloadLength(value);
        return this;
      }
      /**
       * <code>optional int32 payloadLength = 7;</code>
       */
      public Builder clearPayloadLength() {
        copyOnWrite();
        instance.clearPayloadLength();
        return this;
      }

      /**
       * <code>optional bytes body = 8;</code>
       */
      public com.google.protobuf.ByteString getBody() {
        return instance.getBody();
      }
      /**
       * <code>optional bytes body = 8;</code>
       */
      public Builder setBody(com.google.protobuf.ByteString value) {
        copyOnWrite();
        instance.setBody(value);
        return this;
      }
      /**
       * <code>optional bytes body = 8;</code>
       */
      public Builder clearBody() {
        copyOnWrite();
        instance.clearBody();
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
          resource_ = visitor.visitString(!resource_.isEmpty(), resource_,
              !other.resource_.isEmpty(), other.resource_);
          status_ = visitor.visitInt(status_ != 0, status_,    other.status_ != 0, other.status_);
          time_ = visitor.visitLong(time_ != 0L, time_,
              other.time_ != 0L, other.time_);
          contentType_ = visitor.visitInt(contentType_ != 0, contentType_,    other.contentType_ != 0, other.contentType_);
          payloadLength_ = visitor.visitInt(payloadLength_ != 0, payloadLength_,
              other.payloadLength_ != 0, other.payloadLength_);
          body_ = visitor.visitByteString(body_ != com.google.protobuf.ByteString.EMPTY, body_,
              other.body_ != com.google.protobuf.ByteString.EMPTY, other.body_);
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
                case 10: {
                  String s = input.readStringRequireUtf8();

                  resource_ = s;
                  break;
                }
                case 16: {
                  int rawValue = input.readEnum();

                  status_ = rawValue;
                  break;
                }
                case 40: {

                  time_ = input.readSInt64();
                  break;
                }
                case 48: {
                  int rawValue = input.readEnum();

                  contentType_ = rawValue;
                  break;
                }
                case 56: {

                  payloadLength_ = input.readInt32();
                  break;
                }
                case 66: {

                  body_ = input.readBytes();
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