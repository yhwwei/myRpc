package gkrpc.client;

import gkrpc.Peer;
import gkrpc.commons.util.ReflectionUtil;
import gkrpc.transport.TransportClient;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author yhw
 * @version 1.0
 **/
@Slf4j
public class RandomTransportSelector implements TransportSelector{

    //用list充当池子
    private List<TransportClient> transportClientsPool = new ArrayList<>();

    @Override
    public synchronized void init(List<Peer> peers, int count, Class<? extends TransportClient> tClass) {
            count = Math.max(count,1);
        for (Peer peer : peers) {
            for (int i=0;i<count;i++){
                TransportClient transportClient = ReflectionUtil.newInstance(tClass);
                transportClient.connect(peer);
                transportClientsPool.add(transportClient);
            }
           log.info("pool connect to {} ",peer);
        }
    }

    @Override
    public synchronized TransportClient select() {
        int i = new Random().nextInt(transportClientsPool.size());
        TransportClient transportClient = transportClientsPool.get(i);
        transportClientsPool.remove(i);
        return transportClient;
    }

    @Override
    public synchronized void release(TransportClient transportClient) {
        transportClientsPool.add(transportClient);
    }

    @Override
    public synchronized void close() {
        for (TransportClient transportClient : transportClientsPool) {
            transportClient.close();
        }
        transportClientsPool.clear();
    }
}
