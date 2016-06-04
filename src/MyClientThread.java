import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class MyClientThread implements Runnable {
	String ip;
	int port;
	public  MyClientThread(String ip,String port) {
		// TODO Auto-generated constructor stub
		this.ip = ip;
		this.port = Integer.parseInt(port);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Socket client = new Socket(ip, port);
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
