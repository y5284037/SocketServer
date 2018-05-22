package TCPSocket;

import org.apache.log4j.Logger;

import java.io.*;
import java.net.Socket;
import java.util.Properties;

/**
 * @author: Arike
 * @program: SocketServer
 * @description: 服务端多线程列
 * @create: 2018/5/17 0017 15:18
 */

public class ServerThread implements Runnable {
    private Logger logger = Logger.getLogger(ServerThread.class);
    private Socket client = null;
    private Properties properties = null;
    
    public ServerThread(Socket client) {
        this.client = client;
    }
    
    @Override
    public void run() {
        
        //获取Socket的输入流，用来接收从客户端发送过来的数据
        DataInputStream in = null;
        properties = new Properties();
        try {
            properties.load(Object.class.getResourceAsStream("/config.properties"));
            in = new DataInputStream(client.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        boolean flag = true;
        byte[] arr = new byte[new Integer(properties.getProperty("dataSize"))];
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(properties.getProperty("dataLocation"),true)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (flag) {
            //接收从客户端发送过来的数据
            try {
                assert in != null;
                in.read(arr);
                assert bw != null;
                bw.write(new String(arr));
                bw.newLine();
                bw.flush();
                logger.info("Data Recive Success ");
                
            } catch (Exception e) {
                logger.info("客户端断开连接");
                e.printStackTrace();
                try {
                    client.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                break;
            }
        }
    }
}
