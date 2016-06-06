import java.awt.List;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class MyItemListener implements ItemListener {
	

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		String item = e.getItem().toString();
		int stateChange = e.getStateChange();
		if (stateChange == ItemEvent.SELECTED) {
			System.out.println("此次事件由选中“" + item + "”触发！");
			
		} else if (stateChange == ItemEvent.DESELECTED) {
			System.out.println("此次事件由取消选中“" + item + "”触发！");
		} else {
			System.out.println("此次事件由其它原因触发！");
		}

	}

}
