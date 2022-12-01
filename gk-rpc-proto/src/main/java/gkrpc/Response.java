package gkrpc;

import lombok.Data;

/**
 * @author yhw
 * @version 1.0
 **/
@Data

public class Response {
    //响应状态码  0成功 非0失败
    private int code;

    //具体的错误信息
    private String message="ok";

    //返回的数据
    private Object data;
}
