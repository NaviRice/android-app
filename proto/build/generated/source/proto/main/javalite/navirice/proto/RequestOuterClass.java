// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: request.proto

package navirice.proto;

public final class RequestOuterClass {
  private RequestOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }
  public interface RequestOrBuilder extends
      // @@protoc_insertion_point(interface_extends:navirice.proto.Request)
      com.google.protobuf.MessageLiteOrBuilder {

    /**
     * <code>optional .navirice.proto.Request.Type type = 1;</code>
     */
    int getTypeValue();
    /**
     * <code>optional .navirice.proto.Request.Type type = 1;</code>
     */
    navirice.proto.RequestOuterClass.Request.Type getType();

    /**
     * <code>optional uint32 length = 2;</code>
     */
    int getLength();

    /**
     * <code>optional bytes body = 3;</code>
     */
    com.google.protobuf.ByteString getBody();
  }
  /**
   * Protobuf type {@code navirice.proto.Request}
   */
  public  static final class Request extends
      com.google.protobuf.GeneratedMessageLite<
          Request, Request.Builder> implements
      // @@protoc_insertion_point(message_implements:navirice.proto.Request)
      RequestOrBuilder {
    private Request() {
      body_ = com.google.protobuf.ByteString.EMPTY;
    }
    /**
     * Protobuf enum {@code navirice.proto.Request.Type}
     */
    public enum Type
        implements com.google.protobuf.Internal.EnumLite {
      /**
       * <code>CURRENT_STEP = 0;</code>
       */
      CURRENT_STEP(0),
      /**
       * <code>CURRENT_LOCATION = 1;</code>
       */
      CURRENT_LOCATION(1),
      UNRECOGNIZED(-1),
      ;

      /**
       * <code>CURRENT_STEP = 0;</code>
       */
      public static final int CURRENT_STEP_VALUE = 0;
      /**
       * <code>CURRENT_LOCATION = 1;</code>
       */
      public static final int CURRENT_LOCATION_VALUE = 1;


      public final int getNumber() {
        return value;
      }

      /**
       * @deprecated Use {@link #forNumber(int)} instead.
       */
      @java.lang.Deprecated
      public static Type valueOf(int value) {
        return forNumber(value);
      }

      public static Type forNumber(int value) {
        switch (value) {
          case 0: return CURRENT_STEP;
          case 1: return CURRENT_LOCATION;
          default: return null;
        }
      }

      public static com.google.protobuf.Internal.EnumLiteMap<Type>
          internalGetValueMap() {
        return internalValueMap;
      }
      private static final com.google.protobuf.Internal.EnumLiteMap<
          Type> internalValueMap =
            new com.google.protobuf.Internal.EnumLiteMap<Type>() {
              public Type findValueByNumber(int number) {
                return Type.forNumber(number);
              }
            };

      private final int value;

      private Type(int value) {
        this.value = value;
      }

      // @@protoc_insertion_point(enum_scope:navirice.proto.Request.Type)
    }

    public static final int TYPE_FIELD_NUMBER = 1;
    private int type_;
    /**
     * <code>optional .navirice.proto.Request.Type type = 1;</code>
     */
    public int getTypeValue() {
      return type_;
    }
    /**
     * <code>optional .navirice.proto.Request.Type type = 1;</code>
     */
    public navirice.proto.RequestOuterClass.Request.Type getType() {
      navirice.proto.RequestOuterClass.Request.Type result = navirice.proto.RequestOuterClass.Request.Type.forNumber(type_);
      return result == null ? navirice.proto.RequestOuterClass.Request.Type.UNRECOGNIZED : result;
    }
    /**
     * <code>optional .navirice.proto.Request.Type type = 1;</code>
     */
    private void setTypeValue(int value) {
        type_ = value;
    }
    /**
     * <code>optional .navirice.proto.Request.Type type = 1;</code>
     */
    private void setType(navirice.proto.RequestOuterClass.Request.Type value) {
      if (value == null) {
        throw new NullPointerException();
      }
      
      type_ = value.getNumber();
    }
    /**
     * <code>optional .navirice.proto.Request.Type type = 1;</code>
     */
    private void clearType() {
      
      type_ = 0;
    }

    public static final int LENGTH_FIELD_NUMBER = 2;
    private int length_;
    /**
     * <code>optional uint32 length = 2;</code>
     */
    public int getLength() {
      return length_;
    }
    /**
     * <code>optional uint32 length = 2;</code>
     */
    private void setLength(int value) {
      
      length_ = value;
    }
    /**
     * <code>optional uint32 length = 2;</code>
     */
    private void clearLength() {
      
      length_ = 0;
    }

    public static final int BODY_FIELD_NUMBER = 3;
    private com.google.protobuf.ByteString body_;
    /**
     * <code>optional bytes body = 3;</code>
     */
    public com.google.protobuf.ByteString getBody() {
      return body_;
    }
    /**
     * <code>optional bytes body = 3;</code>
     */
    private void setBody(com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      body_ = value;
    }
    /**
     * <code>optional bytes body = 3;</code>
     */
    private void clearBody() {
      
      body_ = getDefaultInstance().getBody();
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (type_ != navirice.proto.RequestOuterClass.Request.Type.CURRENT_STEP.getNumber()) {
        output.writeEnum(1, type_);
      }
      if (length_ != 0) {
        output.writeUInt32(2, length_);
      }
      if (!body_.isEmpty()) {
        output.writeBytes(3, body_);
      }
    }

    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;

      size = 0;
      if (type_ != navirice.proto.RequestOuterClass.Request.Type.CURRENT_STEP.getNumber()) {
        size += com.google.protobuf.CodedOutputStream
          .computeEnumSize(1, type_);
      }
      if (length_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeUInt32Size(2, length_);
      }
      if (!body_.isEmpty()) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(3, body_);
      }
      memoizedSerializedSize = size;
      return size;
    }

    public static navirice.proto.RequestOuterClass.Request parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, data);
    }
    public static navirice.proto.RequestOuterClass.Request parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, data, extensionRegistry);
    }
    public static navirice.proto.RequestOuterClass.Request parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, data);
    }
    public static navirice.proto.RequestOuterClass.Request parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, data, extensionRegistry);
    }
    public static navirice.proto.RequestOuterClass.Request parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, input);
    }
    public static navirice.proto.RequestOuterClass.Request parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, input, extensionRegistry);
    }
    public static navirice.proto.RequestOuterClass.Request parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }
    public static navirice.proto.RequestOuterClass.Request parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }
    public static navirice.proto.RequestOuterClass.Request parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, input);
    }
    public static navirice.proto.RequestOuterClass.Request parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(navirice.proto.RequestOuterClass.Request prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }

    /**
     * Protobuf type {@code navirice.proto.Request}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageLite.Builder<
          navirice.proto.RequestOuterClass.Request, Builder> implements
        // @@protoc_insertion_point(builder_implements:navirice.proto.Request)
        navirice.proto.RequestOuterClass.RequestOrBuilder {
      // Construct using navirice.proto.RequestOuterClass.Request.newBuilder()
      private Builder() {
        super(DEFAULT_INSTANCE);
      }


      /**
       * <code>optional .navirice.proto.Request.Type type = 1;</code>
       */
      public int getTypeValue() {
        return instance.getTypeValue();
      }
      /**
       * <code>optional .navirice.proto.Request.Type type = 1;</code>
       */
      public Builder setTypeValue(int value) {
        copyOnWrite();
        instance.setTypeValue(value);
        return this;
      }
      /**
       * <code>optional .navirice.proto.Request.Type type = 1;</code>
       */
      public navirice.proto.RequestOuterClass.Request.Type getType() {
        return instance.getType();
      }
      /**
       * <code>optional .navirice.proto.Request.Type type = 1;</code>
       */
      public Builder setType(navirice.proto.RequestOuterClass.Request.Type value) {
        copyOnWrite();
        instance.setType(value);
        return this;
      }
      /**
       * <code>optional .navirice.proto.Request.Type type = 1;</code>
       */
      public Builder clearType() {
        copyOnWrite();
        instance.clearType();
        return this;
      }

      /**
       * <code>optional uint32 length = 2;</code>
       */
      public int getLength() {
        return instance.getLength();
      }
      /**
       * <code>optional uint32 length = 2;</code>
       */
      public Builder setLength(int value) {
        copyOnWrite();
        instance.setLength(value);
        return this;
      }
      /**
       * <code>optional uint32 length = 2;</code>
       */
      public Builder clearLength() {
        copyOnWrite();
        instance.clearLength();
        return this;
      }

      /**
       * <code>optional bytes body = 3;</code>
       */
      public com.google.protobuf.ByteString getBody() {
        return instance.getBody();
      }
      /**
       * <code>optional bytes body = 3;</code>
       */
      public Builder setBody(com.google.protobuf.ByteString value) {
        copyOnWrite();
        instance.setBody(value);
        return this;
      }
      /**
       * <code>optional bytes body = 3;</code>
       */
      public Builder clearBody() {
        copyOnWrite();
        instance.clearBody();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:navirice.proto.Request)
    }
    protected final Object dynamicMethod(
        com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
        Object arg0, Object arg1) {
      switch (method) {
        case NEW_MUTABLE_INSTANCE: {
          return new navirice.proto.RequestOuterClass.Request();
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
          navirice.proto.RequestOuterClass.Request other = (navirice.proto.RequestOuterClass.Request) arg1;
          type_ = visitor.visitInt(type_ != 0, type_,    other.type_ != 0, other.type_);
          length_ = visitor.visitInt(length_ != 0, length_,
              other.length_ != 0, other.length_);
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
                case 8: {
                  int rawValue = input.readEnum();

                  type_ = rawValue;
                  break;
                }
                case 16: {

                  length_ = input.readUInt32();
                  break;
                }
                case 26: {

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
          if (PARSER == null) {    synchronized (navirice.proto.RequestOuterClass.Request.class) {
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


    // @@protoc_insertion_point(class_scope:navirice.proto.Request)
    private static final navirice.proto.RequestOuterClass.Request DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new Request();
      DEFAULT_INSTANCE.makeImmutable();
    }

    public static navirice.proto.RequestOuterClass.Request getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static volatile com.google.protobuf.Parser<Request> PARSER;

    public static com.google.protobuf.Parser<Request> parser() {
      return DEFAULT_INSTANCE.getParserForType();
    }
  }


  static {
  }

  // @@protoc_insertion_point(outer_class_scope)
}
