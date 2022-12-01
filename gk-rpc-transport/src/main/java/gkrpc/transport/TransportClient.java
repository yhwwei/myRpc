package gkrpc.transport;

import gkrpc.Peer;

import java.io.InputStream;
import java.net.URLConnection;

/**
 * 客户端传输接口   方便后面扩展用HTTP、HTTPS等协议
 * @author yhw
 * @version 1.0
 **/
public interface TransportClient {
    void connect(Peer peer);
    InputStream write(InputStream data);
    void close();
}
