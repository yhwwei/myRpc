package gkrpc.server;

import gkrpc.Request;
import gkrpc.ServiceDescriptor;
import gkrpc.commons.util.ReflectionUtil;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

/**
 * @author yhw
 * @version 1.0
 **/
public class ServiceManagerTest {

    ServiceManager sm;

    @Before
    public void init(){
        sm = new ServiceManager();
        ABC abc = new ABC();
        sm.register(ABC.class,abc);
    }

    @Test
    public void testRegister() {
        ABC abc = new ABC();
        sm.register(ABC.class,abc);
    }
    @Test
    public void testLookup() {
        Method publicMethod = ReflectionUtil.getPublicMethods(ABC.class)[0];
        ServiceDescriptor serviceDescriptor = ServiceDescriptor.generateToServiceDescriptor(ABC.class, publicMethod);

        Request  r = new Request();
        r.setServiceDescriptor(serviceDescriptor);

        ServiceInstance lookup = sm.lookup(r);

        assertNotNull(lookup);
    }
}