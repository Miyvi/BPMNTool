import java.util.ArrayList;


public class Pool {
private String label;
private ArrayList<Object> Objects=new ArrayList<Object>();

public Pool(String lbl)
{
	setLabel(lbl);
}

public void AddObject(Object o)
{
	Objects.add(o);
}


// Accesseurs

public String getLabel() {
	return label;
}

public void setLabel(String label) {
	this.label = label;
}

public ArrayList<Object> getObjects() {
	return Objects;
}

public void setObjects(ArrayList<Object> objects) {
	Objects = objects;
}


}
