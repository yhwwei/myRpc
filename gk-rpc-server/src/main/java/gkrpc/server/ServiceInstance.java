package gkrpc.server;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;

/**
 * @author yhw
 * @version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceInstance {
    private Object target;
    private Method method;
}
