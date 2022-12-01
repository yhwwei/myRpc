package gkrpc.client;

import gkrpc.codec.Decoder;
import gkrpc.codec.Encoder;
import gkrpc.commons.util.ReflectionUtil;

import java.lang.reflect.Proxy;

/**
 * @author yhw
 * @version 1.0
 **/
public class RpcClient {
    private RpcClientConfig config;
    private Encoder encoder;
    private Decoder decoder;
    private TransportSelector transportSelector;

    public RpcClient() {
        //使用默认配置
        this(new RpcClientConfig());
    }

    public RpcClient(RpcClientConfig config) {
        this.config = config;
        this.encoder = ReflectionUtil.newInstance(config.getEncoderClass());
        this.decoder = ReflectionUtil.newInstance(config.getDecoderClass());
        this.transportSelector = ReflectionUtil.newInstance(config.getTransportSelectorClass());
        this.transportSelector.init(config.getPeers(),config.getConnectCount(), config.getTransportClientClass());
    }

    public<T> T getProxy(Class<T> clazz){
        return (T) Proxy.newProxyInstance(getClass().getClassLoader(),new Class[]{clazz},new RemoteInvoke(clazz,encoder,decoder,transportSelector));
    }
}
