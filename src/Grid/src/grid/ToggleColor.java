package grid;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class ToggleColor {
	private Colorlist l=new Colorlist();
	private ColorGroup g1=new ColorGroup();
	private ColorGroup g2=new ColorGroup();
	private ColorGroup g3=new ColorGroup();
	private ColorGroup g4=new ColorGroup();
	private ColorGroup g0=new ColorGroup();
	private HBox[] t=new HBox[l.size()];
	public ToggleColor() {}
	public HBox getToggles(int i)
	{
		RadioButton t0=g0.get(i);
		t0.setPadding(new Insets(0,0,0,25));
		RadioButton t1=g1.get(i);
		t1.setPadding(new Insets(0,0,0,28));
		RadioButton t2=g2.get(i);
		t2.setPadding(new Insets(0,0,0,17));
		RadioButton t3=g3.get(i);
		t3.setPadding(new Insets(0,0,0,22));
		RadioButton t4=g4.get(i);
		t4.setPadding(new Insets(0,0,0,32));
		Label name=new Label(l.get(i).getName());
		name.setPadding(new Insets(0,0,0,18));
		name.setFont(new Font(14.4));
		t[i]=new HBox();
		t[i].setStyle("-fx-background-color:#"+l.get(i).getString());
		t[i].getChildren().addAll(t0,t1,t2,t3,t4,name);
		return t[i];
	}
}
