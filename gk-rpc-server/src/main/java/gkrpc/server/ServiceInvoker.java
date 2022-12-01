package gkrpc.server;

import gkrpc.Request;
import gkrpc.commons.util.ReflectionUtil;


/**
 * 调用具体服务
 *
 * @author yhw
 * @version 1.0
 **/
public class ServiceInvoker {
    public Object invoke(ServiceInstance serviceInstance, Request request) {
        return ReflectionUtil.invoke(serviceInstance.getTarget(), serviceInstance.getMethod(), request.getParameters());
    }
}
