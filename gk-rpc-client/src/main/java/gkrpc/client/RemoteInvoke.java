package gkrpc.client;

import gkrpc.Request;
import gkrpc.Response;
import gkrpc.ServiceDescriptor;
import gkrpc.codec.Decoder;
import gkrpc.codec.Encoder;
import gkrpc.transport.TransportClient;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 调用远程服务代理类
 *
 * @author yhw
 * @version 1.0
 **/
public class RemoteInvoke implements InvocationHandler {
    private Class clazz;
    private Encoder encoder;
    private Decoder decoder;
    private TransportSelector transportSelector;
    RemoteInvoke(Class clazz, Encoder encoder, Decoder decoder,TransportSelector transportSelector){
        this.clazz = clazz;
        this.encoder = encoder;
        this.decoder = decoder;
        this.transportSelector = transportSelector;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Request request = new Request();
        request.setServiceDescriptor(ServiceDescriptor.generateToServiceDescriptor(clazz,method));
        request.setParameters(args);


        Response response = invokeRemote(request);
        if (response==null||response.getCode()!=0)
        {
            throw new IllegalStateException("failed to invoke remote:" +response);
        }
        return response.getData();
    }

    private Response invokeRemote(Request request) {
        TransportClient transportClient = null;
        Response response = null;
        try {
            transportClient = transportSelector.select();
            byte[] bytes = encoder.encode(request);
            InputStream respStream = transportClient.write(new ByteArrayInputStream(bytes));
            byte[] respBytes = IOUtils.readFully(respStream, respStream.available());

            response = decoder.decode(respBytes, Response.class);
            return response;
        } catch (IOException e) {
            e.printStackTrace();
            response = new Response();
            response.setCode(1);
            response.setMessage("RpcClient got error:"+e.getClass()+" : "+e.getMessage());
            return response;
        } finally {
            transportSelector.release(transportClient);
        }

    }
}
