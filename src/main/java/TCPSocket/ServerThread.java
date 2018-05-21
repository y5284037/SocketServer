package TCPSocket;

import java.io.*;
import java.net.Socket;

/**
 * @author: Arike
 * @program: SocketServer
 * @description: 服务端多线程列
 * @create: 2018/5/17 0017 15:18
 */

public class ServerThread implements Runnable {
    
    private Socket client = null;
    
    public ServerThread(Socket client) {
        this.client = client;
    }
    
    @Override
    public void run() {
        
        //获取Socket的输入流，用来接收从客户端发送过来的数据
        DataInputStream in = null;
        try {
            in = new DataInputStream(client.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        boolean flag = true;
        byte[] arr = new byte[512];
        while (flag) {
            //接收从客户端发送过来的数据
            try {
                assert in != null;
                in.read(arr);
                System.out.println(new String(arr));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
