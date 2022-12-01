package gkrpc.transport;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author yhw
 * @version 1.0
 **/
@Slf4j
public class HTTPTransportServer implements TransportServer{
    private RequestHandle handle;

    //可以看作是一个webapp  对象
    private Server server;

    @Override
    public void init(int port, RequestHandle requestHandle) {
        this.handle = requestHandle;

        //用jetty 绑定这个端口
        this.server = new Server(port);

        //一般来说好像使用XML或者注释来配置服务器
        //这里就在java代码中设置
        //每个WebApp都有一个context，ServletContextHandler起到的作用跟web.xml类似
        //跟tomcat在web.xml配置servlet处理哪个请求类似吧？
        ServletContextHandler servletContextHandler = new ServletContextHandler();
        

        server.setHandler(servletContextHandler);

        ServletHolder holder = new ServletHolder(new RequestServlet());

        //加入  该servlet处理哪个path请求
        servletContextHandler.addServlet(holder,"/*");
    }

    @Override
    public void start() {
        try {
            server.start();
            server.join();
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
    }

    @Override
    public void stop() {
        try {
            server.stop();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    class RequestServlet extends HttpServlet{
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            doPost(req,resp);
        }

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            log.info("request arrived");
            InputStream inputStream = req.getInputStream();
            OutputStream outputStream = resp.getOutputStream();

            if(handle!=null){
                handle.onRequest(inputStream,outputStream);
            }
            outputStream.flush();
        }
    }
}
