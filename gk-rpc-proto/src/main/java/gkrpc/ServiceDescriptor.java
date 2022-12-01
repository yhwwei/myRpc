package gkrpc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

/**
 * 服务描述
 * @author yhw
 * @version 1.0
 **/
@AllArgsConstructor
@Data
@NoArgsConstructor
public class ServiceDescriptor {
    //哪个类
    private String clazz;
    //哪个方法
    private String method;

    //返回类型
    private String returnType;

    //方法参数类型
    private String[] parametersType;

    public static ServiceDescriptor generateToServiceDescriptor(Class clazz, Method method){
        ServiceDescriptor serviceDescriptor = new ServiceDescriptor();
        serviceDescriptor.clazz = clazz.getName();
        serviceDescriptor.method = method.getName();
        serviceDescriptor.returnType = method.getReturnType().getName();

        Class<?>[] parameterTypes = method.getParameterTypes();
        serviceDescriptor.parametersType = new String[parameterTypes.length];
        for(int i=0;i<parameterTypes.length;i++){
            serviceDescriptor.parametersType[i] = parameterTypes[i].getTypeName();
        }
        return serviceDescriptor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceDescriptor that = (ServiceDescriptor) o;
        return this.toString().equals(that.toString());
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public String toString() {
        return "ServiceDescriptor{" +
                "clazz='" + clazz + '\'' +
                ", method='" + method + '\'' +
                ", returnType='" + returnType + '\'' +
                ", parametersType=" + Arrays.toString(parametersType) +
                '}';
    }
}
