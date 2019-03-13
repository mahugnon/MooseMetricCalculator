package grid;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.StackPane;

public class Analyse {
	private ObservableList<Pli> t;
	private ObservableList<Pli> d;
	private Etude p;
	public Analyse() {
		t=FXCollections.observableArrayList();
		d=FXCollections.observableArrayList();
	}
	public void addPli(Pli pli)
	{
		t.add(pli);
	}
	public StackPane get()
	{
		return p.get();
	}
	public boolean isempty()
	{
		boolean b;
		if(t.isEmpty())
		{
			b=false;
		}
		else
		{
			p=new Etude();
			for(int i=0;i<t.size();i++)
			{
				int x=0;
				boolean bool=false;
				while(x<d.size()&&!bool)
				{
					if(t.get(i).equals(d.get(x)))
					{
						bool=true;
					}
					x++;
				}
				if(!bool)
				{
					d.add(t.get(i));
					for(int j=0;j<t.get(i).countvide();j++)
					{
						int c=0;
						for(int k=0;k<t.size();k++)
						{
							for(int y=0;y<t.get(k).countvide();y++)
							{
								if(t.get(i).getvide(j).equals(t.get(k).getvide(y)))
								{
									c++;
								}
							}
						}
						p.addProba(new Proba(t.get(i).getvide(j),c));
					}
				}
			}
			b=true;
		}
		return b;
	}
}
