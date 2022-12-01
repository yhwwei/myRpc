import gkrpc.server.RpcServer;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yhw
 * @version 1.0
 **/
@Slf4j
public class Server {
    public static void main(String[] args) {
        RpcServer rpcServer = new RpcServer();
        rpcServer.register(CalcService.class,new CalcServiceImpl());
        rpcServer.start();
    }
}
