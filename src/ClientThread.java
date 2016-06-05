import java.awt.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

public class ClientThread implements Runnable {
	private Socket client;
	private InetAddress ip;
	private int port;
	private long ThreadNum;

	public ClientThread(Socket client) {
		// TODO Auto-generated constructor stub
		this.client = client;

	}

	// init info of the thread
	public void initInfo() {
		ip = client.getInetAddress();
		port = client.getPort();
		ThreadNum = Thread.currentThread().getId();

	}

	// get information from client
	public String getMsg() throws IOException {
		if (client.isClosed()) {
			return null;

		} else {
			InputStreamReader input = new InputStreamReader(client.getInputStream());

			BufferedReader reader = new BufferedReader(input);
			String msg = reader.readLine();
			/*
			 * StringBuilder msg = new StringBuilder(); String temp; int index;
			 * while ((temp = reader.readLine()) != null) { msg.append(temp); }
			 */

			return (msg.toString());

		}

	}

	// response to the client
	public void response(String msg) throws IOException {

		OutputStreamWriter output = new OutputStreamWriter(client.getOutputStream());

		output.write(msg + "\n");
		output.flush();

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		initInfo();
		while(client.isClosed()) {

			try {
				Destop2.getInstance().textList.add(getMsg());

				System.out.println("clientthread run");
				System.out.println("clientThread:-->" + getMsg());
				response("我是服务器，吃过了吗？");

			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("fail to get msg:" + e.getMessage());
				e.printStackTrace();

			}
		}

	}

}
