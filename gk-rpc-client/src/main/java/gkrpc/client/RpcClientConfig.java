package gkrpc.client;

import gkrpc.Peer;
import gkrpc.codec.Decoder;
import gkrpc.codec.Encoder;
import gkrpc.codec.JSONDecoder;
import gkrpc.codec.JSONEncoder;
import gkrpc.transport.HTTPTransportClient;
import gkrpc.transport.TransportClient;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

/**
 *
 * 客户端配置类
 * @author yhw
 * @version 1.0
 **/
@Data
public class RpcClientConfig {
    //哪种网络，默认是HTTP
    private Class<? extends TransportClient> transportClientClass = HTTPTransportClient.class;

    private Class<? extends Encoder> encoderClass = JSONEncoder.class;
    private Class<? extends Decoder> decoderClass = JSONDecoder.class;

    //连接池配置
    private Class<? extends TransportSelector> transportSelectorClass = RandomTransportSelector.class;

    //和每台服务器的连接数
    private int connectCount = 1;
    //服务器ip
    private List<Peer> peers = Arrays.asList(new Peer("127.0.0.1",8999));
}
