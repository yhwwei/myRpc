package gkrpc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author yhw
 * @version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Request {
    //服务描述
    private ServiceDescriptor serviceDescriptor;

    //参数
    private Object[] parameters;
}
