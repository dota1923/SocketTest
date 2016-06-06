import java.awt.Button;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.List;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;
import javax.swing.text.html.ListView;

import org.omg.CORBA.Current;

public class Destop2 implements ActionListener {
	private static Destop2 destop;

	// single instance
	public static Destop2 getInstance() {
		if (destop == null) {
			destop = new Destop2();
		}
		return destop;
	}

	private Frame mainframe;
	private Panel head, body, bottom;
	private TextField textIP, textPort, textGroup;
	private Button chat, stop, clear, send;
	private JTextArea sendContext;
	public List textList, Connectedlist;

	// 连接服务器的socket
	private Socket client;
	private Vector<Socket> socketList;

	public void initFrame() {
		mainframe = new Frame(StringValue.chat);
		mainframe.setBounds(100, 30, 600, 730);
		mainframe.setResizable(false);
		mainframe.setLayout(null);
		mainframe.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);

			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub

			}
		});
		initHead();
		initBody();
		initBottom();

		mainframe.add(head);
		mainframe.add(body);
		mainframe.add(bottom);

		mainframe.setVisible(true);

	}

	public List getConnectedList() {
		return Connectedlist;
	}

	// 修改聊天和断开按钮的状态
	private void ChangeButtonSta(boolean chat, boolean stop) {
		this.chat.setEnabled(chat);
		this.stop.setEnabled(stop);

	}

	private void initHead() {
		head = new Panel();
		head.setBounds(0, 0, 600, 100);

		head.setLayout(null);
		Label labelIp = new Label("对方的IP地址");
		// labelIp.setBackground(Color.orange);
		labelIp.setBounds(20, 40, 100, 20);
		textIP = new TextField();
		textIP.setEditable(true);
		textIP.addKeyListener(new MyKeyAdapter(MyKeyAdapter.INPUT_IP));

		textIP.setBounds(120, 40, 140, 20);
		Label labelPort = new Label("端口号");
		labelPort.setBounds(270, 40, 50, 20);
		textPort = new TextField();
		textPort.setEditable(true);
		textPort.addKeyListener(new MyKeyAdapter(MyKeyAdapter.INPUT_PORT));
		textPort.setBounds(320, 40, 80, 20);

		Label labelGroup = new Label("组播地址");
		labelGroup.setBounds(43, 70, 78, 20);
		textGroup = new TextField();
		textGroup.setEditable(true);
		textGroup.setBounds(121, 70, 140, 20);

		chat = new Button("开始聊天");
		chat.setBounds(301, 70, 80, 20);
		chat.addActionListener(this);

		stop = new Button("断开");
		stop.setBounds(401, 70, 60, 20);
		stop.addActionListener(this);

		ChangeButtonSta(true, false);

		head.add(labelIp);
		head.add(textIP);
		head.add(labelPort);
		head.add(textPort);
		head.add(labelGroup);
		head.add(textGroup);
		head.add(chat);
		head.add(stop);

	}

	private void initBody() {
		body = new Panel();
		body.setLayout(null);
		body.setBounds(0, 100, 600, 350);
		// body.setBackground(Color.BLUE);
		Label labelContext = new Label("接收消息");
		labelContext.setBounds(20, 10, 60, 20);

		// TextField textContext = new TextField();
		textList = new List();
		textList.setBounds(20, 35, 450, 300);

		Label chatList = new Label("私聊列表");
		chatList.setBounds(510, 10, 60, 20);

		Connectedlist = new List();
		Connectedlist.setBounds(500, 35, 80, 240);
		Connectedlist.setVisible(true);

		clear = new Button("清屏");
		clear.setBounds(510, 310, 60, 20);
		clear.addActionListener(this);

		body.add(labelContext);
		body.add(textList);
		body.add(chatList);
		body.add(Connectedlist);
		body.add(clear);

	}

	private void initBottom() {

		bottom = new Panel();
		bottom.setLayout(null);
		bottom.setBounds(0, 450, 600, 300);
		// bottom.setBackground(Color.red);

		Label sendMsg = new Label("发送消息");
		sendMsg.setBounds(20, 10, 60, 20);

		// TextField sendContext = new TextField();
		sendContext = new JTextArea();
		sendContext.setLineWrap(true);

		sendContext.setEditable(true);

		sendContext.setBounds(20, 35, 450, 200);

		send = new Button("发送");
		send.setBounds(510, 215, 60, 20);
		send.addActionListener(this);

		bottom.add(sendMsg);
		bottom.add(sendContext);
		bottom.add(send);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		String name = e.getActionCommand();
		switch (name) {
		case "开始聊天":
			if (!(Boolean.logicalXor((textIP.getText().equals("")), (textGroup.getText().equals(""))))
					|| (textPort.getText().equals(""))) {
				JOptionPane.showMessageDialog(null, "地址或端口不能为空");
			} else if (textGroup.getText().equals("")) {
				connectedToSocket();
			} else if (textIP.getText().equals("")) {

			}

			break;
		case "断开":
			try {
				// sendMsg("123");
				client.close();
				ChangeButtonSta(true, false);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			break;
		case "清屏":
			textList.removeAll();
			break;
		case "发送":
			if (chat.isEnabled() || client.isClosed()) {

				JOptionPane.showMessageDialog(null, "还没连接，发送消息失败");

			} else {
				sendMsg(sendContext.getText());
			}
			break;
		default:
			System.out.println("--------3------");
			break;

		}

	}

	// 连接到一个serversockket
	private void connectedToSocket() {
		String ip = textIP.getText();
		int port = Integer.parseInt(textPort.getText());

		try {
			client = new Socket(InetAddress.getLocalHost(), port);
			ChangeButtonSta(false, true);

		} catch (Exception e1) {
			System.out.println("........chen8");
		}

		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (!client.isClosed()) {
					System.out.println("is socket connected" + client.isConnected());
					InputStreamReader reader;
					try {

						reader = new InputStreamReader(client.getInputStream());
						BufferedReader buf = new BufferedReader(reader);
						String msg = buf.readLine();
						if (msg != null) {
							System.out.println("server:" + msg.toString());
							String ContextMsg = "来自被连接的回复:" + client.getInetAddress() + "\n" + msg.toString();
							textList.add(ContextMsg);

						}

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			}
		}).start();

	}

	private void sendMsg(String msg) {
		// 发送信息
		try {

			PrintWriter writer = new PrintWriter(client.getOutputStream());
			writer.write(msg + "\n");
			writer.flush();

		} catch (Exception e2) {
			// TODO Auto-generated catch block
			System.out.println("写入失败");
			e2.printStackTrace();
		}
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd HH:mm");
		String dateString = formatter.format(currentTime);
		String ContextMsg = "本机:" + dateString + "\n" + sendContext.getText();
		System.out.println(ContextMsg);
		textList.add(ContextMsg);
		sendContext.setText(null);

	}

	private void connectedToGroup() {

	}

}
