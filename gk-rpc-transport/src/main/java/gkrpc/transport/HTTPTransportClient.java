package gkrpc.transport;

import gkrpc.Peer;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author yhw
 * @version 1.0
 **/
public class HTTPTransportClient implements TransportClient{
    private String url;
    @Override
    public void connect(Peer peer) {
        url = "http://"+peer.getHost()+":"+peer.getPort();

    }

    @Override
    public InputStream write(InputStream data) {
        try {
            //建立HTTP连接  写数据
            HttpURLConnection httpURLConnection = (HttpURLConnection)new URL(url).openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.connect();
            //将数据复制  outPut  input是相对于连接而言的
            IOUtils.copy(data,httpURLConnection.getOutputStream());
            int responseCode = httpURLConnection.getResponseCode();
            if(responseCode==HttpURLConnection.HTTP_OK){
                return httpURLConnection.getInputStream();
            }
            else{
                return httpURLConnection.getErrorStream();
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }


    //http是短连接  也不需要我们手动close
    @Override
    public void close() {

    }
}
