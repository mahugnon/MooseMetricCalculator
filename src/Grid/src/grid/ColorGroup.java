package grid;

import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class ColorGroup extends ToggleGroup{
	private Colorlist l=new Colorlist();
	public ColorGroup() {
		for(int i=0;i<l.size();i++)
		{
			this.getToggles().add(new RadioButton());
		}
	}
	public RadioButton get(int i)
	{
		return (RadioButton) this.getToggles().get(i);
	}
}
