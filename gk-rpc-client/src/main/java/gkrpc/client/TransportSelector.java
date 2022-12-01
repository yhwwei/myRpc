package gkrpc.client;

import gkrpc.Peer;
import gkrpc.transport.TransportClient;

import java.util.List;

/**
 * 表示选择哪个server去连接
 *
 * @author yhw
 * @version 1.0
 **/


//这个类的作用是相对于和多服务器建立连接    连接池作用
//然后用的时候从连接池里面取出连接，用完放回去
public interface TransportSelector {

    /**
     * 初始化selector  （初始化连接池）
     *
     * @param peers 可以连接的server端点信息  因为服务器可以是多台的
     * @param count  client和server 建立多少个连接
     * @param tClass
     */
    void init(List<Peer> peers,int count,Class<? extends TransportClient> tClass);
    /**
     * 从连接池 选择一个TransportClient和服务器交互
     * @return 网络client
     */
    TransportClient select();

    /**
     * 释放用完的TransportClient，放回连接池
     * @param transportClient
     */
    void release(TransportClient transportClient);

    //关闭整个连接池
    void close();
}
