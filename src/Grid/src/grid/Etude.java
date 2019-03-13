package grid;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.StackPane;

public class Etude {
	private ObservableList<Proba> t;
	public Etude()
	{
		t=FXCollections.observableArrayList();
	}
	public void addProba(Proba p)
	{
		t.add(p);
	}
	public StackPane get()
	{
		Proba p=t.get(0);
		for(int i=1;i<t.size();i++)
		{
			if(t.get(i).getcount()>=p.getcount())
			{
				p=t.get(i);
			}
		}
		return p.get();
	}
}
