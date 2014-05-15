import java.awt.Color;
import java.awt.Graphics;


public class End extends Object{

	public End(){
		super();
		max_link_partant=0;
		max_link_arrivant=1;
		h=30;
		l=30;
	}
	
	public void affiche(Graphics g)
	{
		// choix de la couleur
	    float[] hsbvals = null;
	    hsbvals= Color.RGBtoHSB(255, 85, 85, hsbvals);
	    g.setColor(Color.getHSBColor(hsbvals[0], hsbvals[1], hsbvals[2]));
	    g.fillOval(x, y, h,l);
		
	    g.setColor(Color.black);
		g.drawOval(x, y, h,l);
		
		String s="End";
		g.drawString(s, x+5,y+h+12); // positionne le texte centré
		super.affiche(g);
	}
}