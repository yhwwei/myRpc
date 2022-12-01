package gkrpc.transport;

import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * 请求处理接口
 * @author yhw
 * @version 1.0
 **/
public interface RequestHandle {
    void onRequest(InputStream receive, OutputStream toResp);
}
