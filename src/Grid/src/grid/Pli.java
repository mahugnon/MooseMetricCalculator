package grid;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.StackPane;

public class Pli {
	private ObservableList<StackPane> t=FXCollections.observableArrayList();
	private ObservableList<StackPane> t2=FXCollections.observableArrayList();
	public Pli(StackPane t1,StackPane t2,StackPane t3,StackPane t4) {
		t.addAll(t1,t2,t3,t4);
		seachvide();
	}
	public Pli(StackPane t1,StackPane t2,StackPane t3,StackPane t4,StackPane t5) {
		t.addAll(t1,t2,t3,t4,t5);
		seachvide();
	}
	public StackPane get(int i)
	{
		return t.get(i);
	}
	public int countvide()
	{
		return t2.size();
	}
	public int size()
	{
		return t.size();
	}
	public StackPane getvide(int i)
	{
		if(t2.size()>0)
		{
			return t2.get(i);
		}else
		{
			System.out.println("Rien");
			return null;
		}
	}
	private void seachvide()
	{
		for(int i=0;i<t.size();i++)
		{
			if(t.get(i).getChildren().size()==0)
			{
				t2.add(t.get(i));
			}
		}
	}
	public int i(int i)
	{
		return (int) ((t.get(i).getLayoutY())%32);
	}
	public int j(int j)
	{
		return (int) ((t.get(j).getLayoutX()-5)%32);
	}
}
