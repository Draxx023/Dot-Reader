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
            System.out.println(resultat.toDot());
        }
        else if(reader.flagColored && reader.flagWeighted){
            GrapheColoré resultat = reader.grapheC;
            System.out.println(resultat.toDot());
        }
        else if(reader.flagColored){
            GrapheColoré resultat = reader.grapheC;
            System.out.println(resultat.toDot());
        }
        else if(reader.flagWeighted){
            GraphePonderé resultat = reader.grapheP;
            System.out.println(resultat.toDot());
        }
        else{
            GraphePonderé resultat = reader.grapheP;
            System.out.println(resultat.toDot());
        }
            
    }
}
