import gkrpc.client.RpcClient;

/**
 * @author yhw
 * @version 1.0
 **/
public class Client {
    public static void main(String[] args) {
        RpcClient rpcClient = new RpcClient();
        CalcService proxy = rpcClient.getProxy(CalcService.class);
        int add = proxy.add(1, 2);
        int sub = proxy.sub(2, 3);
        System.out.println(add+"   "+sub);
    }
}
