package gkrpc.server;

import gkrpc.codec.Decoder;
import gkrpc.codec.Encoder;
import gkrpc.codec.JSONDecoder;
import gkrpc.codec.JSONEncoder;
import gkrpc.transport.HTTPTransportServer;
import gkrpc.transport.TransportServer;
import lombok.Data;

/**
 * @author yhw
 * @version 1.0
 **/
@Data
public class RpcServerConfig {
    private Class<? extends TransportServer> transportClass = HTTPTransportServer.class;
    private Class<? extends Encoder> encoderClass = JSONEncoder.class;
    private Class<? extends Decoder> decoderClass = JSONDecoder.class;
    private int port = 8999;

}
