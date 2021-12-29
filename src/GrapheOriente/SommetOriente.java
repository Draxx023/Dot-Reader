package GrapheOriente;

import java.util.Vector;

import GrapheColoré.SommetColoré;

public class SommetOriente extends SommetColoré{

	Vector<SommetOriente> voisinsOrientes = new Vector<SommetOriente>();

    SommetOriente(int id, String color) {
        super(id, color);
    }

	public int degree(){
		return voisinsOrientes.size();
	}

	public void addVoisin(SommetOriente s, int Poids) {
		voisinsOrientes.add(s);
		poids.add(Poids);
	}

	public int getPoids(SommetOriente v){
		return poids.get(voisinsOrientes.indexOf(v));
	}

    public String toString() {
		String str = id + "";
		if(color != null){
			str += "[color=\"" + this.color + "\"]";
		}
		str= " -> (";
		for (int i=0; i<voisinsOrientes.size()-1; i++)
			str+=voisinsOrientes.get(i).id + "(" + poids.get(i) + "), ";
		if(voisinsOrientes.size()>0){
        	int end = voisinsOrientes.size()-1; 
			str += voisinsOrientes.get(end).id + poids.get(end) + ")\n";
		}
		return str;
	}

    @Override
    public String toDot(){
		// Cette methode écrit tous les voisins de ce sommet un après l'autre et ajoute le poids de chacun de ces arrets
		String str = "";
		if(color != ""){
			str += id + " [color=\"" + this.color + "\"];\n";
		}
		for( int i = 0; i < voisinsOrientes.size() ; i++ )
			if(poids.get(i)!=0)
				str += id + " -> " + voisinsOrientes.get(i).id + " [label=\"" + poids.get(i) + "\"];\n";
			else
				str += id + " -> " + voisinsOrientes.get(i).id + ";\n";		
		return str;
	}

    public String toDot(GrapheOriente arbre){
		// Cette methode est de meme que la précedente sauf que si l'arete se trouve aussi dans le graphe donné en paramètre, il le colore en rouge
		String str="";
		for( int i = 0; i < voisinsOrientes.size() ; i++ ){
			if(arbre.sontVoisins(this.id, voisinsOrientes.get(i).id))
				str += id + " -> " + voisinsOrientes.get(i).id + " [label= \"" + poids.get(i) + "\", color=red];\n";
			else
			str += id + " -> " + voisinsOrientes.get(i).id;
		}
		return str;
	}
    
}
