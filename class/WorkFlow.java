import java.util.ArrayList;


public class WorkFlow {
	private ArrayList<Pool> Pools=new ArrayList<Pool>();

	
	// ajoute une Pool
	public void addPool(Pool p)
	{
		Pools.add(p);
	}
	
	// ajoute une nouvelle pool avec un label
	public void addNewPool(String s)
	{
		Pool p=new Pool(s);
		addPool(p);
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
	
	// créé un lien entre deux objets 
	// source : id1 target : id2
	public void linker(int id1,int id2)
	{
		Object o1=get_objet(id1);
		Object o2=get_objet(id2);
		
		if(o1.linkable_partant() && o2.linkable_arrivant())
		{
			o1.linker_partant(o2);
			o2.linker_partant(o1);
		}
		else
		{
		// error	
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
	
	
	
	// Accesseur
	
	public ArrayList<Pool> getPools() {
		return Pools;
	}

	public void setPools(ArrayList<Pool> pools) {
		Pools = pools;
	}
}
