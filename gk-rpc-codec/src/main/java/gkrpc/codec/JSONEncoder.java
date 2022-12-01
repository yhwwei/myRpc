package gkrpc.codec;

import com.alibaba.fastjson.JSON;

/**
 * @author yhw
 * @version 1.0
 **/
public class JSONEncoder implements Encoder{
    @Override
    public byte[] encode(Object obj) {
        return JSON.toJSONBytes(obj);
    }
}
