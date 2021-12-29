package GraphePonderé;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

import GrapheBasique.GrapheBasique;

public class GraphePonderé extends GrapheBasique {

    public Vector<SommetPonderé> sommetsPonderés = new Vector<SommetPonderé>(size);

    public GraphePonderé(int size) {
        super(size);
        sommets.clear();
		for(int id=0;id<size;id++) {
			sommetsPonderés.add(new SommetPonderé(id));
		}
    }

    public void addArete(int a, int b, int poids) { 
        sommetsPonderés.get(a).addVoisin(sommetsPonderés.get(b), poids);
		sommetsPonderés.get(b).addVoisin(sommetsPonderés.get(a), poids);
	}

    public int getPoids(SommetPonderé a, SommetPonderé b){
		if(sommetsPonderés.contains(a) && sommetsPonderés.contains(b)){
			if(a.voisins.contains(b)){
				return a.poids.get(a.voisins.indexOf(b));
			}
			else
				return -1;
		}
		else
			return -1;
	}

	public String toString() {
		String str="";
		for (int i=0; i<size; i++)
			str+=sommetsPonderés.get(i)+" ";

		return str;
	}

    @Override
    public String toDot(){
		String output = "graph mon_graphe{\n";

		for ( int i = 0 ; i < size ; i++ ){
			// pour chaque sommet dans le vecteur sommets, on appelle la methode toDot dans Sommet.java
			output += sommetsPonderés.get(i).toDot();
		}

        return output + "}";
    }

	public String toDotComparaison(GraphePonderé g){
		String output = "graph mon_graphe{\n";
		for ( int i = 0 ; i < size ; i++ ){
			// pour chaque sommet dans le vecteur sommets, on appelle la methode toDot dans Sommet.java
			output += sommetsPonderés.get(i).toDot(g);
		}
		return output+="}";
	}

	public GraphePonderé Dijkstra(SommetPonderé racine) {
		GraphePonderé arbre = new GraphePonderé(size);
		ArrayList<SommetPonderé> S = new ArrayList<SommetPonderé>(); // S pour sommet
		ArrayList<Integer> P = new ArrayList<Integer>(); // P pour poids
		ArrayList<SommetPonderé> O = new ArrayList<SommetPonderé>(); // O pour origine
		// Initialisation des listes
		for (SommetPonderé voisinCourant : racine.voisinsPonderés) {
			S.add(voisinCourant);
			O.add(racine);
			P.add(racine.getPoids(voisinCourant));
		}
		racine.flag=true;
		
		while(!S.isEmpty()) {
			int index=P.indexOf(Collections.min(P));
			SommetPonderé sommetCourant = S.get(index);
			int poidsCourant = P.get(index);
			arbre.addArete(O.get(index).id,sommetCourant.id);
			
			for (SommetPonderé voisinCourant : sommetCourant.voisinsPonderés) {
				if (!S.contains(voisinCourant) && !voisinCourant.flag) {
					S.add(voisinCourant);
					P.add(poidsCourant+sommetCourant.getPoids(voisinCourant));
					O.add(sommetCourant);
				}
				else if(!voisinCourant.flag && poidsCourant+sommetCourant.getPoids(voisinCourant)<P.get(S.indexOf(voisinCourant))) {
					int indexVoisin=S.indexOf(voisinCourant);
					P.set(indexVoisin,poidsCourant+sommetCourant.getPoids(voisinCourant));
					O.set(indexVoisin,sommetCourant);
				}
			}
			
			sommetCourant.flag=true;
			S.remove(index);
			P.remove(index);
			O.remove(index);
		}
		for (SommetPonderé s : sommetsPonderés) s.flag=false;
		return arbre;
		}


    
}
