package GrapheOriente;

import java.util.Vector;

import GraphePonderé.GraphePonderé;

public class GrapheOriente extends GraphePonderé{

    public Vector<SommetOriente> sommetsOrientés = new Vector<SommetOriente>(size);

    public GrapheOriente(int size) {
        super(size);
		for(int id=0;id<size;id++) {
			sommetsOrientés.add(new SommetOriente(id,""));
		}
    }

    public void addArete(int a, int b, int poids) { 
        sommetsOrientés.get(a).addVoisin(sommetsOrientés.get(b), poids);
	}

	public String toString() {
		String str="";
		for (int i=0; i<size; i++)
			str+=sommetsOrientés.get(i).toString()+"\n";

		return str;
	}

    public String toDot(){
		String output = "digraph mon_graphe{\n";

		for ( int i = 0 ; i < size ; i++ ){
			// pour chaque sommet dans le vecteur sommets, on appelle la methode toDot dans Sommet.java
			output += sommetsOrientés.get(i).toDot();
		}

        return output + "}";
    }

	public String toDotComparaison(GraphePonderé g){
		String output = "graph mon_graphe{\n";
		for ( int i = 0 ; i < size ; i++ ){
			// pour chaque sommet dans le vecteur sommets, on appelle la methode toDot dans Sommet.java
			output += sommetsOrientés.get(i).toDot(g);
		}
		return output+="}";
	}

    
}
