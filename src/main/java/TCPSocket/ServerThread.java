package TCPSocket;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Properties;

/**
 * @author: Arike
 * @program: SocketServer
 * @description: 服务端多线程列
 * @create: 2018/5/17 0017 15:18
 */

public class ServerThread implements Runnable {
    private Properties config;
    private int dataSize;
    private Socket socketClient;
    private DataInputStream socketDataIn;
    
    public ServerThread(Socket socketClient) {
        this.socketClient = socketClient;
    }
    
    @Override
    public void run() {
        try {
            init();
            socketDataIn = new DataInputStream(socketClient.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
        byte[] arr = new byte[dataSize];
        Log.buildLogsFile();
        Log.runtimeInfo("客户端成功接入.");
        while (true) {
            try {
                socketDataIn.read(arr);
                Log.dataInfo(arr);
                Log.runtimeInfo("Data Recive Success");
            } catch (IOException e) {
                try {
                    Log.runtimeInfo("客户端断开连接,等待客户端重连.");
                    Log.close();
                    socketClient.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                break;
            }
        }
    }
    
    private void init() throws Exception {
        config = new Properties();
        config.load(new FileInputStream(System.getProperty("user.dir") + "/config.properties"));
//        config.load(Object.class.getResourceAsStream("/config.properties"));
        dataSize = Integer.valueOf(config.getProperty("dataSize"));
    }
    
    
}


