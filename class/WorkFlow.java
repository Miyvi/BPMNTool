import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;


public class WorkFlow {
	private ArrayList<Pool> Pools=new ArrayList<Pool>();
	private IdGenerator iter=new IdGenerator();
	int h=0; // hauteur de la fenetre
	int l=0; // largeur de la fenetre
	int nb_obj=0;
	
	public WorkFlow(int hi,int li)
	{
		h=hi;
		l=li;
	}

	
	// ajoute une Pool
	public void addPool(Pool p)
	{
		Pools.add(p);
	}
	
	// ajoute une nouvelle pool avec un label
	public void addNewPool(String s)
	{
		int posy=0;
		
		for(int i=0;i<Pools.size();i++)
		{
		Pool p=Pools.get(i);
		posy+=p.getH();
		}
		
		Pool p=new Pool(s,posy,h-posy, l);
		addPool(p);
	}
	
	// Permet d'ajouter un objet dans une pool /!\ il faut obligatoirement passer par cette méthode
	public void addObject(int pool,Object o)
	{
		o.setId(iter.get_id());
		Pools.get(pool).AddObject(o);
		nb_obj++;
	}
	
	//retourne un objet dont l'id est passé en paramètres
	public Object get_objet(int id)
	{
		for(int i=0;i<Pools.size();i++)
		{
			Pool p=Pools.get(i);
			for(int j=0;j<p.getObjects().size();j++)
			{
				if(p.getObjects().get(j).getId()==id) return p.getObjects().get(j);
			}
		}
		return null;
	}
	
	//retourne une selon l'objet dont l'id est passé en paramètres
		public int get_pool_objet(int id)
		{
			for(int i=0;i<Pools.size();i++)
			{
				Pool p=Pools.get(i);
				for(int j=0;j<p.getObjects().size();j++)
				{
					if(p.getObjects().get(j).getId()==id) return i;
				}
			}
			return 0;
		}
	
	
	//retourne une pool selon son numero
	public Pool get_pool(int id)
	{
		return Pools.get(id);
	}
	
	// créé un lien entre deux objets 
	// source : id1 target : id2
	public void linker(int id1,int id2)
	{
		Object o1=get_objet(id1);
		Object o2=get_objet(id2);
		
		if(o1!=null && o2!=null && o1.linkable_partant() && o2.linkable_arrivant())
		{
			o1.linker_partant(o2);
			o2.linker_arrivant(o1);
		}
		else
		{
		// error
			System.out.println("Problem de linkage");
		}
	}
	
	// retire un lien entre deux objets
	// source : id1 target id2
	public void unlinker(int id1,int id2)
	{
		Object o1=get_objet(id1);
		Object o2=get_objet(id2);
		o1.unlink(o2);
		o2.unlink(o1);
	}
	
	// affichage
	
	public void affiche(Graphics g)
	{
		g.setColor(Color.black);
		for(int i=0;i<Pools.size();i++)
		{
			Pools.get(i).affiche(g);
			ArrayList<Object> Objects=Pools.get(i).getObjects();
			for(int j=0;j<Objects.size();j++)
			{
				Objects.get(j).affiche(g);
			}
		}
		
	}
	
	
	// optimisation de l'emplacement
	// 
	public void optimise()
	{
		
		ArrayList<ArrayList<Object>> Matrice = new ArrayList<ArrayList<Object>>();
		ArrayList<Object> Ligne;// = new ArrayList<Object>();
		Object o;
		for(int i=1;i<=nb_obj;i++)
		{
			o=get_objet(i);
			Boolean trouve=false;
			for(int j=0;j<Matrice.size();j++)
			{	for(int l=0;l<Matrice.get(j).size();l++)
					for(int k=0;k<Matrice.get(j).get(l).links_partant.size();k++)
					{
						if(Matrice.get(j).get(l).links_partant.get(k)==o) 
							{
							trouve=true;
							Matrice.get(j).add(l+1,o);
							}
					}
			}
			if(trouve==false)
			{
				Ligne = new ArrayList<Object>();
				Ligne.add(o);
				Matrice.add(Ligne);
			}
		}
		
		System.out.println(Matrice);
		
	}
	

	
	
	// Accesseur
	
	public ArrayList<Pool> getPools() {
		return Pools;
	}

	public void setPools(ArrayList<Pool> pools) {
		Pools = pools;
	}
}
