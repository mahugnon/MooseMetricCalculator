package grid;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Win {
	private ObservableList<Pli> t;
	private Pli p;
	private boolean bool;
	public Win(boolean bool)
	{
		this.bool=bool;
		t=FXCollections.observableArrayList();
	}
	public boolean addPli(Pli p)
	{
		this.p=p;
		boolean b=false;
		if(p.size()==4)
		{
			if((!isWinable(0,1))&&(!isWinable(2,3)))
			{
				t.add(p);
				b=true;
			}
		}else
		{
			if((!isWinable(0,1))&&(!isWinable(1,2))&&(!isWinable(3,4)))
			{
				t.add(p);
				b=true;
			}
		}
		return b;
	}
	private boolean isWinable(int i,int j)
	{
		boolean b=false;
		for(int k=0;k<t.size();k++)
		{
			if(bool)
			{
				if(isIn(i,k)&&isIn(j,k))
				{
					b=true;
				}
			}else
			{
				if(isIn(i,k)||isIn(j,k))
				{
					b=true;
				}
			}
		}
		return b;
	}
	private boolean isIn(int i,int k)
	{
		boolean b=false;
		for(int z=0;z<p.size();z++)
		{
			if(t.get(k).get(z).equals(p.get(i)))
			{
				b=true;
			}
		}
		return b;
	}
	public boolean getVerifPion(Pli p)
	{
		boolean b=false;
		this.p=p;
		if(p.size()==4)
		{
			if((!isWinable(0,1))&&(!isWinable(2,3)))
			{
				b=true;
			}
		}else
		{
			if((!isWinable(0,1))&&(!isWinable(1,2))&&(!isWinable(3,4)))
			{
				b=true;
			}
		}
		return b;
	}
	public boolean atravers(Pli p)
	{
		boolean b=false;
		this.p=p;
		for(int i=0;i<p.size()-1;i++)
		{
			for(int j=0;j<t.size();j++)
			{
				for(int k=0;k<p.size()-1;k++)
				{
					if(entre(i,j,k))
					{
						b=true;
					}
				}
			}
		}
		return b;
	}
	private boolean entre(int i,int j,int k)
	{
		boolean b=false;
		if(p.get(0).getLayoutY()<p.get(1).getLayoutY())
		{
			if((p.get(i).getLayoutX()==t.get(j).get(k).getLayoutX())&&((p.get(i).getLayoutY()+1)==t.get(j).get(k).getLayoutY())&&((p.get(i).getLayoutX()+1)==t.get(j).get(k+1).getLayoutX())&&(p.get(i).getLayoutY()==t.get(j).get(k+1).getLayoutY()))
			{
				b=true;
			}
		}else
		{
			if((p.get(i).getLayoutX()==t.get(j).get(k).getLayoutX())&&((p.get(i).getLayoutY()-1)==t.get(j).get(k).getLayoutY())&&((p.get(i).getLayoutX()+1)==t.get(j).get(k+1).getLayoutX())&&(p.get(i).getLayoutY()==t.get(j).get(k+1).getLayoutY()))
			{
				b=true;
			}
		}
		return b;
	}
	public  ObservableList<Pli> getwin()
	{
		return t;
	}
	public int size()
	{
		return t.size();
	}
}
