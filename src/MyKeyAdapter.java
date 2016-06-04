import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MyKeyAdapter extends KeyAdapter {
	private int type;
	//表示用于输入ip地址
	public static int INPUT_IP = 0;
	//表示用于输入端口
	public static int INPUT_PORT = 1;

	public MyKeyAdapter(int type) {
		this.type = type;

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		int keyChar = e.getKeyChar();
		switch (type) {
		case 0: {
			if ((keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9) || KeyEvent.VK_PERIOD == keyChar) {
				

			} else {

				e.consume(); // 关键，屏蔽掉非法输入
			}

		}
			break;
		case 1: {
			if (keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9) {

			} else {
				e.consume();
			}
		}break;
		default:
			break;

		}

	}

}
