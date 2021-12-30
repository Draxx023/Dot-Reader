package GraphePonderé;

import java.util.Vector;

import GrapheBasique.Sommet;

public class SommetPonderé extends Sommet{

    protected Vector<SommetPonderé> voisinsPonderés = new Vector<SommetPonderé>();
    public Vector<Integer> poids = new Vector<Integer>();

    public void addVoisin(SommetPonderé s, int Poids) {
		voisinsPonderés.add(s);
		poids.add(Poids);
	}

    public SommetPonderé(int id) {
        super(id);
    }

	public int degree(){
		return voisinsPonderés.size();
	}

	public int getPoids(SommetPonderé v){
		return poids.get(voisinsPonderés.indexOf(v));
	}

    @Override
	public String toString() {
		String str = "Sommet n°" + id;
		if(voisinsPonderés.isEmpty()){
			str += " n'a aucun voisin.\n";
		}
		else{
			str += " a les voisins suivants:\n\t(";
			for (int i=0; i<voisinsPonderés.size()-1; i++){
				if(poids.get(i)!=0)
					str+=voisinsPonderés.get(i).id + " (poids arête:" + poids.get(i) + "), ";
				else
					str+=voisinsPonderés.get(i).id + ", ";
			}
			int end = voisinsPonderés.size()-1; 
			if(poids.get(end)!=0)
				str += voisinsPonderés.get(end).id + " (poids arête: " + poids.get(end) + "));\n";
			else
				str += voisinsPonderés.get(end).id + ");\n";
		}
		return str;
	}

    @Override
    public String toDot(){
		// Cette methode écrit tous les voisins de ce sommet un après l'autre et ajoute le poids de chacun de ces arrets
		String str="";
		// str += this.id + "[color=\"" + this.couleur + "\"];\n";
		for( int i = 0; i < voisinsPonderés.size() ; i++ )
			if(id<voisinsPonderés.get(i).id){
				if(poids.get(i)!=0)
					str += id + " -- " + voisinsPonderés.get(i).id + " [label=\"" + poids.get(i) + "\"];\n";
				else
				str += id + " -- " + voisinsPonderés.get(i).id + ";\n";
			}
		return str;
	}

    public String toDot(GraphePonderé arbre){
		// Cette methode est de meme que la précedente sauf que si l'arete se trouve aussi dans le graphe donné en paramètre, il le colore en rouge
		String str="";
		for( int i = 0; i < voisinsPonderés.size() ; i++ )
			if(id<voisinsPonderés.get(i).id)
				if(arbre.sontVoisins(this.id, voisinsPonderés.get(i).id))
					str += id + " -- " + voisinsPonderés.get(i).id + " [label= \"" + poids.get(i) + "\", color=red];\n";
				else
				str += id + " -- " + voisinsPonderés.get(i).id;
		return str;
	}


    
}
