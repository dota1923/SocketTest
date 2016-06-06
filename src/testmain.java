import java.awt.Button;
import java.awt.Frame;
import java.awt.List;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Vector;

public class testmain {

	public static void main(String[] args) throws IOException {

		boolean flag = true;
		Destop2 destop = Destop2.getInstance();
		destop.initFrame();
		int ServerPort = 6011;

		// 监听本机指定的端口号
		ServerSocket server = new ServerSocket(ServerPort);
		while (flag) {
			try {
				// 获取请求访问的信息
				Socket apt = server.accept();
				List chatList = destop.getConnectedList();

				chatList.add(apt.getInetAddress().toString());
				

				// 创建线程处理访问信息
				ClientThread client = new ClientThread(apt);
				Thread temp = new Thread(client);

				temp.start();
			} catch (IOException e) {

			}
		}

	}

}
