package gkrpc.codec;

/**
 * @author yhw
 * @version 1.0
 **/
public interface Encoder {
    //将对象变为二进制数组
    byte[] encode(Object obj);
}
