import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BoxLayout;
import javax.swing.text.View;

public class Destop implements ActionListener {
	private static Destop destop;
	Button send = new Button("send");

	//single instance
	public static Destop getInstance() {
		if (destop == null) {
			destop = new Destop();
		}
		return destop;

	}

	private Frame mainView;
	private Panel panel1, panel2;

	public Frame initFrame() {
		mainView = new Frame("chat");

		mainView.setBounds(50, 50, 500, 650);
		mainView.setLayout(new BoxLayout(mainView, BoxLayout.Y_AXIS));
		Changestat(mainView);
		panel1 = new Panel();
		

		
		panel1.setBackground(Color.gray);
		panel1.setLayout(new FlowLayout(FlowLayout.LEFT));

		Label lableIp = new Label("对方ip地址:");
		lableIp.setAlignment(Label.LEFT);
		panel1.add(lableIp);
		TextField ipText = new TextField();
		ipText.setEditable(true);
		ipText.setPreferredSize(new Dimension(140, 20));
		ipText.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				int keyChar = e.getKeyChar();
				if ((keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9) || KeyEvent.VK_PERIOD == keyChar) {

				} else {

					e.consume(); // 关键，屏蔽掉非法输入
				}
			}
		});
		Label labelport = new Label("端口号:");
		labelport.setAlignment(Label.RIGHT);
		TextField portText = new TextField();
		portText.setEditable(true);
		portText.setPreferredSize(new Dimension(60, 20));
		portText.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				int keyChar = e.getKeyChar();
				if ((keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9)) {

				} else {

					e.consume(); // 关键，屏蔽掉非法输入
				}
			
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		

		panel1.add(ipText);
		panel1.add(labelport);
		panel1.add(portText);

		/*
		 * TextArea edittext= new TextArea(); edittext.setEditable(true);
		 * edittext.setPreferredSize(new Dimension(300, 200));
		 * 
		 * send.addActionListener(this); send.setBounds(50, 50, 30, 30);
		 * send.setActionCommand("send"); panel1.add(lableIp);
		 * panel1.add(edittext); panel1.add(send);
		 */

		mainView.add(panel1);

		mainView.setResizable(false);

		mainView.setVisible(true);

		return mainView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch (e.getActionCommand()) {
		case "send":
			break;

		}

	}

	public void Changestat(Frame frame) {
		frame.addWindowListener(new WindowListener() {

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
	}

}
