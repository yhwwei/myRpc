package gkrpc.transport;

/**
 * @author yhw
 * @version 1.0
 **/
public interface TransportServer {
    //对于不同的请求 交由不同的请求处理器
    void init(int port,RequestHandle requestHandle);

    //网络监听
    void start();

    //网络关闭
    void stop();
}
