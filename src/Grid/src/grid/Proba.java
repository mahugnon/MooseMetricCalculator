package grid;

import javafx.scene.layout.StackPane;

public class Proba {
	private StackPane p;
	private int i;
	public Proba(StackPane p,int i)
	{
		this.p=p;
		this.i=i;
	}
	public int getcount()
	{
		return i;
	}
	public StackPane get()
	{
		return p;
	}
}
