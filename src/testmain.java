import java.awt.Button;
import java.awt.Frame;
import java.awt.List;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class testmain   {

	public static void main(String[] args) throws IOException {
	
		boolean flag = true;
		Destop2 destop = Destop2.getInstance();
		destop.initFrame();
		int ServerPort = 7003;
		//监听本机指定的端口号
		ServerSocket server = new ServerSocket(ServerPort);
		while (flag) {
			try {
				//获取请求访问的信息
				Socket apt = server.accept();
				List chatList = destop.getConnectedList();
				
				chatList.add(apt.getInetAddress().toString());
				
				
				//创建线程处理访问信息
				ClientThread client = new ClientThread(apt);
				new Thread(client).start();
			} catch (IOException e) {

			}
		}

	}

}
