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
		String str = "Sommet n°" + id;
		if(color != ""){
			str += " de couleur " + this.color;
		}
		if(voisinsOrientes.isEmpty()){
			str += " n'a aucun voisin.\n";
		}
		else{
			str += " a les voisins suivants:\n\t-> (";
			for (int i=0; i<voisinsOrientes.size()-1; i++){
				if(poids.get(i)!=0)
					str+=voisinsOrientes.get(i).id + "(poids arête: " + poids.get(i) + ", ";
				else
					str+=voisinsOrientes.get(i).id + ", ";
			}
			if(voisinsOrientes.size()>0){
				int end = voisinsOrientes.size()-1; 
				if(poids.get(end)!=0)
					str += voisinsOrientes.get(end).id + "(poids arête: " + poids.get(end) + "));\n";
				else
					str+=voisinsOrientes.get(end).id + ");\n";
			}
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
