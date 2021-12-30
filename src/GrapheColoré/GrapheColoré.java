package GrapheColoré;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import GraphePonderé.GraphePonderé;

public class GrapheColoré extends GraphePonderé{

    public Vector<SommetColoré> sommetsColorés = new Vector<SommetColoré>();

    public GrapheColoré(int size) {
        super(size);
        for(int id=0;id<size;id++) {
			sommetsColorés.add(new SommetColoré(id,""));
		}
    }

	public void addArete(int a, int b, int poids) { 
        sommetsColorés.get(a).addVoisin(sommetsColorés.get(b), poids);
		sommetsColorés.get(b).addVoisin(sommetsColorés.get(a), poids);
	}

	public String toString() {
		String str="";
		for (int i=0; i<size; i++)
			str+=sommetsColorés.get(i);

		return str;
	}

    @Override
    public String toDot(){
		String output = "graph mon_graphe{\n";

		for ( int i = 0 ; i < size ; i++ ){
			// pour chaque sommet dans le vecteur sommets, on appelle la methode toDot dans Sommet.java
			output += sommetsColorés.get(i).toDot();
		}

        return output + "}";
    }

	public String toDotComparaison(GrapheColoré g){
		String output = "graph mon_graphe{\n";
		for ( int i = 0 ; i < size ; i++ ){
			// pour chaque sommet dans le vecteur sommets, on appelle la methode toDot dans Sommet.java
			output += sommetsColorés.get(i).toDot(g);
		}
		return output+="}";
	}

    public int maxDegree(){
		int d=0;
		for(SommetColoré s: sommetsColorés){
			int deg = s.degree();
			d= (deg > d)? deg : d;
		}
		return d;
	}

    public void colorTheGraph(){
		Vector<String> couleurs = new Vector<String>(Arrays.asList("red","green","blue","yellow","gray","orange","pink","purple","khaki","cyan","darkolivegreen","darkorchid","darkorange","forestgreen","gold","deeppink","darksalmon"));
		List<String> maxNumberOfColors = couleurs.subList(0, this.maxDegree());
		Vector<String> possibleColors = new Vector<String>(maxNumberOfColors);
		for(SommetColoré s: sommetsColorés){
			possibleColors = new Vector<String>(maxNumberOfColors);
			for(SommetColoré v: s.voisinsColorés){
				if(possibleColors.contains(v.color)){
					possibleColors.remove(v.color);
				}
			}
			s.color=possibleColors.get(0);
		}
	}


    
}
