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

	public InetAddress getIp() {
		return ip;
	}

	public void setIp(InetAddress ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public long getThreadNum() {
		return ThreadNum;
	}

	public void setThreadNum(long threadNum) {
		ThreadNum = threadNum;
	}

	// response to the client
	public void response(String msg) throws IOException {

		OutputStreamWriter output = new OutputStreamWriter(client.getOutputStream());

		output.write(msg + "\n");
		output.flush();

	}

	boolean connected = true;
	String msg;

	// get information from client
	public String getMsg() {

		InputStreamReader input;
		try {
			input = new InputStreamReader(client.getInputStream());
			BufferedReader reader = new BufferedReader(input);
			msg = reader.readLine();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		System.out.println(msg);
		return (msg);

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		initInfo();

		while (!client.isClosed()) {

			try {
				if (getMsg()==null) {
					System.out.println("socket 关闭");
					client.close();

				} else {
					Destop2.getInstance().textList.add("来自主动连接：" + ip + '\n' + getMsg());

					System.out.println("clientthread run");
					System.out.println("clientThread:-->" + getMsg());
				}
				// response("我是服务器，吃过了吗？");

			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("fail to get msg:" + e.getMessage());
				e.printStackTrace();

			}
		}
		System.out.println("end------");

	}

}
