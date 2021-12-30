package dotReader;

import GrapheColoré.GrapheColoré;
import GrapheOriente.GrapheOriente;
import GraphePonderé.GraphePonderé;

public class Testing {

    dotReader reader;

    public Testing(dotReader Reader){
        reader = Reader;
    }
    
    public void test(){

        reader.readDot();

        if(reader.flagOriented){
            GrapheOriente resultat = reader.grapheO;
            System.out.println("Instance de graphe en langage dot:");
            System.out.println(resultat.toDot());
            System.out.println("Affichage du contenu du graphe(toString):");
            System.out.println(resultat.toString());
        }
        else if(reader.flagColored && reader.flagWeighted){
            GrapheColoré resultat = reader.grapheC;
            System.out.println("Instance de graphe en langage dot:");
            System.out.println(resultat.toDot());
            System.out.println("Affichage du contenu du graphe(toString):");
            System.out.println(resultat.toString());
        }
        else if(reader.flagColored){
            GrapheColoré resultat = reader.grapheC;
            System.out.println("Instance de graphe en langage dot:");
            System.out.println(resultat.toDot());
            System.out.println("Affichage du contenu du graphe(toString):");
            System.out.println(resultat.toString());
        }
        else if(reader.flagWeighted){
            GraphePonderé resultat = reader.grapheP;
            System.out.println("Instance de graphe en langage dot:");
            System.out.println(resultat.toDot());
            System.out.println("Affichage du contenu du graphe(toString):");
            System.out.println(resultat.toString());
        }
        else{
            GraphePonderé resultat = reader.grapheP;
            System.out.println("Instance de graphe en langage dot:");
            System.out.println(resultat.toDot());
            System.out.println("Affichage du contenu du graphe(toString):");
            System.out.println(resultat.toString());
        }
            
    }
}
