package gkrpc.codec;

/**
 * @author yhw
 * @version 1.0
 **/
public interface Decoder {
    //将二进制数组变为对象
    <T> T decode(byte[] bytes,Class<T> tClass);
}
