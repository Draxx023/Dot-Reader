package GrapheBasique;

import java.util.*;


public class GrapheBasique{

	protected Vector<Sommet> sommets = new Vector<Sommet>();
	protected int size;
	
	public GrapheBasique(int size){
		this.size=size;
		for(int id=0;id<size;id++) {
			sommets.add(new Sommet(id));
		}
	}

	public GrapheBasique(GrapheBasique G){
		this.sommets = G.sommets;
		this.size = G.size;
	}

	public void addArete(int a, int b) {
    	sommets.get(a).addVoisin(sommets.get(b));
        sommets.get(b).addVoisin(sommets.get(a));
	}
		
	public boolean sontVoisins(int a, int b) {
		return sommets.get(a).isVoisin(sommets.get(b));
	}

	@Override
	public String toString() {
		String str="";
		for (int i=0; i<size; i++)
			str+=sommets.get(i)+" ";

		return str;
	}

	public String toDot(){
		String output = "graph mon_graphe{\n";
		for ( int i = 0 ; i < size ; i++ ){
			// pour chaque sommet dans le vecteur sommets, on appelle la methode toDot dans Sommet.java
			output += sommets.get(i).toDot();
		}
		return output+="}";
		
	}

	public String toDotComparasion(GrapheBasique g){
		String output = "graph mon_graphe{\n";
		for ( int i = 0 ; i < size ; i++ ){
			// pour chaque sommet dans le vecteur sommets, on appelle la methode toDot dans Sommet.java
			output += sommets.get(i).toDot(g);
		}
		return output+="}";
	}

	public GrapheBasique BFS(Sommet racine){
        // On crée un nouveau arbre de la meme taille que l'original
		GrapheBasique Arbre = new GrapheBasique(size);
        
        // On utilise une linked list pour la queue
		LinkedList<Sommet> Queue = new LinkedList<Sommet>(); 

        // On ajoute la racine comme premier element
		Queue.add(sommets.get(racine.id));
        
        // On marque cette racine comme déja existant dans la liste d'attente
		sommets.get(racine.id).flag=true; 

		while(!Queue.isEmpty()){

			// L'algorithme va parcourir tous les voisins du premier sommet dans la liste d'attente
			Sommet sommetCourant = Queue.getFirst();

			for(Sommet voisinCourant : sommetCourant.voisins){
			// En suite il va les ajouter à la liste d'attente si ils ne sont pas encore dans la queue
				if(voisinCourant.flag==false){
					Queue.add(voisinCourant);

			// Si on les ajoute à la queue, on change leur flag
					voisinCourant.flag=true;

			// On ajoute le premier arret qu'on trouve entre le sommet dans la liste d'attente et le nouveau element ajouté
					Arbre.addArete(sommetCourant.id, voisinCourant.id);
				}
			}

			// Une fois qu'on a parcouru tous les voisins du premier sommet dans la queue, et qu'on a ajouté tous les 
			// liasons possibles non encore existantes dans notre nouveau arbre on supprime ce sommet de la queue et on est
			// surs qu'il ne va pas retourner dans la queue car son flag est true
			Queue.removeFirst();
		}	

		// Une fois le BFS est trouvé, il faut les remttre tous à false pour garder l'integrité 
		// de l'arbre d'origine
		for(Sommet s: sommets) s.flag=false;
		return Arbre;
	}

}

