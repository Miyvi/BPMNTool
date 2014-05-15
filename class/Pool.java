import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Pool {
private String label;
private int y=0,h=0,l=0;
private ArrayList<Object> Objects=new ArrayList<Object>();

public Pool(String lbl,int yi,int hi,int li)
{
	setLabel(lbl);
	y=yi;
	h=hi;
	l=li;
}



public void AddObject(Object o)
{
	Objects.add(o);
}

public void affiche(Graphics g)
{
	g.drawRect(0,y,l,h+y);
	g.drawRect(0,y,20,h+y);
	int posy=y+h/2-label.length()/2;
	for(int i=0;i<label.length();i++)  g.drawString(""+label.charAt(i),3,posy+i*12);
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

public int getY() {
	return y;
}

public void setY(int y) {
	this.y = y;
}

public int getH() {
	return h;
}

public void setH(int h) {
	this.h = h;
}

public int getL() {
	return l;
}

public void setL(int l) {
	this.l = l;
}




}
