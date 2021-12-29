package GrapheColoré;

import java.util.Vector;

import GraphePonderé.SommetPonderé;

public class SommetColoré extends SommetPonderé{

    public String color;

    Vector<SommetColoré> voisinsColorés = new Vector<SommetColoré>();

    protected SommetColoré(int id, String Color) {
        super(id);
        this.color = Color;
    }

    public void addVoisin(SommetColoré s, int Poids) {
		voisinsColorés.add(s);
		poids.add(Poids);
	}

    public int degree(){
		return voisinsColorés.size();
	}

    public int getPoids(SommetColoré v){
		return poids.get(voisinsColorés.indexOf(v));
	}

    public String toDot(){
		// Cette methode écrit tous les voisins de ce sommet un après l'autre et ajoute le poids de chacun de ces arrets
		String str="";
        if(color!="")
		    str += this.id  + " [color=\"" + this.color + "\"];\n";
		for( int i = 0; i < voisinsColorés.size() ; i++ ){
            if(id<voisinsColorés.get(i).id){
                if(poids.get(i)!=0)
                    str += id + " -- " + voisinsColorés.get(i).id + " [label=\"" + poids.get(i) + "\"];\n";
                else
                str += id + " -- " + voisinsColorés.get(i).id + ";\n";
            }
        }
		return str;
	}
    
}
