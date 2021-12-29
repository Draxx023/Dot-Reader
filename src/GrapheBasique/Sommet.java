package GrapheBasique;

import java.util.Vector;

public class Sommet{

    public int id;
	public Vector<Sommet> voisins = new Vector<Sommet>();
    public boolean flag=false;
    
	/**
	 * Constructeur utilisant un identifiant pour le sommet.
	 * @param id La valeur de l'identifiant du sommet.
	 */
	protected Sommet(int id) {
		this.id=id;
	}

	public int degree(){
		return voisins.size();
	}
	
	/**
	 * Ajoute un sommet dans la lsite des voisins du sommet.
	 * @param s Le sommet à ajouter.
	 */

	public void addVoisin(Sommet s) {
		voisins.add(s);
	}
	
	/**
	 * Vérifie si un sommet est bien voisin du sommet de l'instence courante.
	 * @param s Le voisin potentiel.
	 * @return true or false.
	 */
	public boolean isVoisin(Sommet s) {
		return voisins.contains(s);
	}
	
	@Override
	public String toString() {
		String str = id+":(";
		for (int i=0; i<voisins.size()-1; i++)
			str += voisins.get(i).id + ",";
		return str + voisins.get(voisins.size()-1).id + ")";
	}

	public String toDot(){
		// Cette methode écrit tous les voisins de ce sommet un après l'autre
		String str = "";

		for( int i = 0; i < voisins.size() ; i++ )
			if(id<voisins.get(i).id)
				str += id + " -- " + voisins.get(i).id + ";\n";
        
		return str;
	}

	public String toDot(GrapheBasique arbre){
		// Cette methode est de meme que la précedente sauf que si l'arete se trouve aussi dans le graphe donné en paramètre, il le colore en rouge
		String str="";

		if(voisins.isEmpty()){
			str += id + " ;\n";
			return str;
		}

		for( int i = 0; i < voisins.size() ; i++ )
			if(id<voisins.get(i).id){
				if( arbre.sontVoisins(this.id, voisins.get(i).id))
					str += id + " -- " + voisins.get(i).id + " [color=\"red\"];\n";
				else
				    str += id + " -- " + voisins.get(i).id + ";\n";
            }
		return str;
	}

}
