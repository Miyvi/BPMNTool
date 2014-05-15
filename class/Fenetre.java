
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
 
public class Fenetre extends JFrame {
  public Fenetre(){                
    this.setTitle("Ma première fenêtre Java");
    this.setSize(500, 500);
    this.setLocationRelativeTo(null);               
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    
    WorkFlow wf=new WorkFlow(500,500);
    
    Task sp= new Task("Je suis tristan et je test un truc");
    Task sp2= new Task("Test2");
    Task sp3= new Task("Test3");
    Start st= new Start();
    End nd=new End();
    JoinGateway jn=new JoinGateway();
    SplitGateway spl=new SplitGateway();
    st.setX(30);
    st.setY(30);
    
    jn.setX(250);
    jn.setY(50);
    sp2.setX(400);
    sp2.setY(50);
    sp.setX(130);
    sp.setY(20);
    sp3.setX(160);
    sp3.setY(150);
    nd.setX(300);
    nd.setY(300);
    spl.setX(400);spl.setY(400);
    wf.addNewPool("ok");
    wf.get_pool(0).setH(200);
    wf.addNewPool("ok2");
    wf.get_pool(1).setH(200);
    wf.get_pool(1).setY(250);
    wf.addObject(0, st);
	wf.addObject(0, sp);
	wf.addObject(0, sp2);
	
	wf.addObject(1, nd);
	wf.addObject(0, jn);
	wf.addObject(0, spl);
	wf.addObject(0, sp3);
	
	
	Start stt=new Start();
	
	wf.addObject(1, stt);
	
	
	wf.linker(1, 2);
	wf.linker(2, 5);
	wf.linker(5, 3);
	wf.linker(3, 4);
	
    
    Panneau pan=new Panneau(wf);
    this.setContentPane(pan);

    wf.optimise();
    
    this.setVisible(true);
  }     
}