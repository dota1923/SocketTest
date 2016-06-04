import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
	public static void main(String[] args) throws IOException {
		boolean flag = true;
		int Listenport = 5598;
		ServerSocket server = new ServerSocket(Listenport);

		// client thread
		new Thread(new Runnable() {
			InetAddress ip = InetAddress.getLocalHost();
			Socket client = new Socket(ip, Listenport);

			@Override
			public void run() {
				// TODO Auto-generated method stub

				if (client.isConnected()) {
					System.out.println("connected ");
				} else {
					System.out.println("fail to connected ");
				}
				try {
					
					PrintWriter writer = new PrintWriter(client.getOutputStream());
					//"/n" is the mark for readline() to recognize the end of a line
					writer.write("this is client"+"\n");
					writer.flush();
					
					
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				while (client.isConnected()) {
					System.out.println("is socket connected"+client.isConnected());
					InputStreamReader reader;
					try {
						
						reader = new InputStreamReader(client.getInputStream());
						BufferedReader buf = new BufferedReader(reader);
						String msg = buf.readLine();
						if(msg!=null){
							System.out.println("server:" + msg.toString());
						}

						
					    

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("这是会话循环体");

				}
			}

		}).start();
		while (flag) {
			try {
				System.out.println("server:waiting for connected");

				Socket apt = server.accept();
				ClientThread a = new ClientThread(apt);
				new Thread(a).start();
			}

			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		// }

	}

}
