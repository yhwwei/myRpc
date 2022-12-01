package gkrpc.server;

import gkrpc.Request;
import gkrpc.ServiceDescriptor;
import gkrpc.commons.util.ReflectionUtil;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 管理rpc暴露的服务
 * @author yhw
 * @version 1.0
 **/
@Slf4j
public class ServiceManager {
    private Map<ServiceDescriptor,ServiceInstance> services;
    public ServiceManager(){
        this.services = new ConcurrentHashMap<>();
    }
    public   <T>  void register(Class<T> tClass,T obj){
        for (Method publicMethod : ReflectionUtil.getPublicMethods(tClass)) {
            ServiceDescriptor serviceDescriptor = ServiceDescriptor.generateToServiceDescriptor(tClass,publicMethod);

            //同一个obj  单例模式
            ServiceInstance serviceInstance = new ServiceInstance(obj,publicMethod);
            services.put(serviceDescriptor,serviceInstance);
            log.info("注册了 ："+serviceDescriptor+"   "+serviceInstance);
        }
    }

    public ServiceInstance lookup(Request request){
        ServiceDescriptor serviceDescriptor = request.getServiceDescriptor();
        return services.get(serviceDescriptor);
    }
}
