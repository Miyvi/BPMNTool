
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JPanel;
 
public class Panneau extends JPanel { 
	WorkFlow work;
	
  public Panneau(WorkFlow w)
    {
        work=w; 
    }
	
	
  public void paintComponent(Graphics g){
    work.affiche(g);
  }               
}