import java.util.ArrayList;


abstract class Object {
protected int id;
protected int x,y;
protected ArrayList<Object> links_partant=new ArrayList<Object>(); // liens partant
protected ArrayList<Object> links_arrivant=new ArrayList<Object>(); // liens arrivant
int max_link_partant,max_link_arrivant; // nombre de liens max définis pour chaque type d'objet hérité

public Object()
{
	x=0;
	y=0;
	id=IdGenerator.get_id();
}


//link un objet à l'objet actuel en partant de l'objet actuel
	public void linker_partant(Object o)
	{
		links_partant.add(o);
	}
	
//link un objet à l'objet actuel en partant de cet objet
	public void linker_arrivant(Object o)
	{
		links_arrivant.add(o);
	}
	
	
	
	// permet de savoir s'il est possible de linker un objet partant de l'objet actuel
		public boolean linkable_partant()
		{	if(max_link_partant==-1) return true;
			if(this.links_partant.size()<max_link_partant) return true;
			else return false;
		}
		
	// permet de savoir s'il est possible de linker un objet partant d'un autre objet
		public boolean linkable_arrivant()
		{
			if(max_link_arrivant==-1) return true;
			if(this.links_arrivant.size()<max_link_arrivant) return true;
			else return false;
		}
		
		
	// permet de supprimer le lien vers un objet
		public void unlink(Object o)
		{
			links_partant.remove(o);
			links_arrivant.remove(o);
		}


///////////////
// Accesseurs
//////////////

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public int getX() {
	return x;
}

public void setX(int x) {
	this.x = x;
}

public int getY() {
	return y;
}

public void setY(int y) {
	this.y = y;
}




public ArrayList<Object> getLinks_partant() {
	return links_partant;
}


public void setLinks_partant(ArrayList<Object> links_partant) {
	this.links_partant = links_partant;
}


public ArrayList<Object> getLinks_arrivant() {
	return links_arrivant;
}


public void setLinks_arrivant(ArrayList<Object> links_arrivant) {
	this.links_arrivant = links_arrivant;
}


}
