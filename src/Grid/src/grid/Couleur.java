package grid;

import javafx.scene.paint.Color;

public class Couleur{
	private String s;
	private Color c;
	public Couleur(Color c,String s) {
		this.c=c;
		this.s=s;
	}
	public Color getColor()
	{
		return c;
	}
	public String getName()
	{
		return s.toLowerCase();
	}
	public Color getWeb()
	{
		return Color.web(c.toString().substring(2,8));
	}
	public String getString()
	{
		return c.toString().substring(2,8);
	}
}
