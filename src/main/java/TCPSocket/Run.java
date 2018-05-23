package TCPSocket;

import java.io.IOException;

/**
 * @author: Arike
 * @program: SocketServer
 * @description: 主程序入口
 * @create: 2018/5/23 0023 16:41
 */

public class Run {
    public static void main(String[] args) {
        Server socketServer = new Server();
        socketServer.startServer();
    }
}
