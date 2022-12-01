package gkrpc.codec;

import com.alibaba.fastjson.JSON;

/**
 * @author yhw
 * @version 1.0
 **/
public class JSONDecoder implements Decoder{
    @Override
    public <T> T decode(byte[] bytes, Class<T> tClass) {
        return JSON.parseObject(bytes,tClass);
    }
}
