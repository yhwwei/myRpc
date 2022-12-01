package gkrpc.server;

import gkrpc.Request;
import gkrpc.Response;
import gkrpc.codec.Decoder;
import gkrpc.codec.Encoder;
import gkrpc.commons.util.ReflectionUtil;
import gkrpc.transport.RequestHandle;
import gkrpc.transport.TransportServer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author yhw
 * @version 1.0
 **/
@Slf4j
public class RpcServer {
    private RpcServerConfig config;
    private TransportServer net;
    private Encoder encoder;
    private Decoder decoder;
    private ServiceManager serviceManager;
    private ServiceInvoker serviceInvoker;

    public RpcServer(){
        this(new RpcServerConfig());
    }
    public RpcServer(RpcServerConfig config) {
        this.config = config;
        this.net  = ReflectionUtil.newInstance(config.getTransportClass());
        this.net.init(config.getPort(),handle);
        this.encoder = ReflectionUtil.newInstance(config.getEncoderClass());
        this.decoder = ReflectionUtil.newInstance(config.getDecoderClass());

        serviceManager = new ServiceManager();
        serviceInvoker = new ServiceInvoker();
    }

    public <T> void register(Class<T> tClass,T obj){
        this.serviceManager.register(tClass,obj);
    }

    public void start(){
        this.net.start();
    }
    public void stop(){
        this.net.stop();
    }


    private RequestHandle handle = (receive, toResp) -> {
        Response response = new Response();

        try {
            byte[] bytes = IOUtils.readFully(receive, receive.available());
            Request request = decoder.decode(bytes, Request.class);
            log.info("get request:{}",request);
            ServiceInstance serviceInstance = serviceManager.lookup(request);
            Object ret = serviceInvoker.invoke(serviceInstance, request);

            response.setData(ret);

        } catch (IOException e) {
            log.warn(e.getMessage(),e);
            //异常设置一下响应状态码以及错误信息
            response.setCode(1);
            response.setMessage("RpcServer got error: "
                    +e.getClass().getName()+" :"+e.getMessage());
        }finally {
            //把响应序列化
            byte[] outBytes = encoder.encode(response);
            try {
                toResp.write(outBytes);
                log.info("response back");
            } catch (IOException e) {
                log.warn(e.getMessage(),e);
            }
        }
    };
}
