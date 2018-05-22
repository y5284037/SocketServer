package TCPSocket;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

/**
 * @author: Arike
 * @program: SocketServer
 * @description: Socket Server
 * @create: 2018/5/17 0017 14:56
 */

public class Server {
    private static Logger logger = Logger.getLogger(Server.class);
    private static Properties properties = new Properties();
    public static void main(String[] args) throws Exception{
        properties.load(Object.class.getResourceAsStream("/config.properties"));
        //服务端在20006端口监听客户端请求的TCP连接
        ServerSocket server = new ServerSocket(new Integer(properties.getProperty("hostPort")));
        Socket client = null;
        boolean f = true;
        while (true) {
    
            //等待客户端的连接，如果没有获取连接
            client = server.accept();
            logger.info("客户端连接成功");
            //为每个客户端连接开启一个线程
            new Thread(new ServerThread(client)).start();
        }
        
        
    }
}

