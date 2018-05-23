package TCPSocket;

import java.io.FileInputStream;
import java.io.IOException;
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
    private  Properties config;
    private  int hostPort;
    private  ServerSocket socketServer;
    private  Socket socketClient;
    
    
    /**
     * 启动服务
     * @throws IOException
     */
    public void startServer() {
        try {
            init();
        } catch (Exception e) {
            System.out.println("程序化初始化失败,请确认config.properties在当前目录下,并重新运行程序.");
            return;
        }
        
        try {
            socketServer = new ServerSocket(hostPort);
        } catch (IOException e) {
            System.out.println("服务启动失败,请重新启动程序.");
            return;
        }
        
        System.out.println("服务端口启动成功，等待客户端接入...");
        
        while (true) {
            try {
                socketClient = socketServer.accept();
                new Thread(new ServerThread(socketClient)).start();//为每个客户端连接开启一个线程
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * 初始化程序,加载配置文件.
     * @throws Exception
     */
    private  void init() throws Exception {
        config = new Properties();
        config.load(new FileInputStream(System.getProperty("user.dir")+"/config.properties"));
//        config.load(Object.class.getResourceAsStream("/config.properties"));
        hostPort = Integer.valueOf(config.getProperty("hostPort"));
    }
}

