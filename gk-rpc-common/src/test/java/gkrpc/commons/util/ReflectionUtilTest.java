package gkrpc.commons.util;

import junit.framework.TestCase;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @author yhw
 * @version 1.0
 **/
public class ReflectionUtilTest extends TestCase {

    @Test
    public void testNewInstance() {
        String str =  ReflectionUtil.newInstance(String.class);
        assertNotNull(str);
    }

    public void testGetPublicMethods() {
        for (Method publicMethod : ReflectionUtil.getPublicMethods(String.class)) {
            System.out.println(publicMethod);
        }

    }

    public void testInvoke() {
    }
}