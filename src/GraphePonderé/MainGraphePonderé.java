package GraphePonderé;

import java.io.File;
import java.io.FileWriter;

public class MainGraphePonderé {
    public static void main(String[] args){
    
        GraphePonderé grapheTest1 = new GraphePonderé(10);
    
        // Connexe avec cycle
        grapheTest1.addArete(0,3,3);
        grapheTest1.addArete(3,1,2);
        grapheTest1.addArete(3,2,1);
        grapheTest1.addArete(1,4,1);
        grapheTest1.addArete(2,4,3);
        grapheTest1.addArete(4,5,3);
        grapheTest1.addArete(4,6,2);
        grapheTest1.addArete(6,7,1);
        grapheTest1.addArete(5,8,3);
        grapheTest1.addArete(8,9,4);
    
        // Connexe sans cycle
        // grapheTest1.addArete(0,3);
        // grapheTest1.addArete(3,1);
        // grapheTest1.addArete(3,2);
        // grapheTest1.addArete(1,4);
    
        // Non connexe
        // grapheTest1.addArete(3,1,3);
        // grapheTest1.addArete(3,2,2);
        // grapheTest1.addArete(1,4,1);
        // grapheTest1.addArete(2,4,1);

        // GraphePonderé grapheTest1_BFS = new GraphePonderé(10);
        
        // grapheTest1_BFS = grapheTest1.Dijkstra(grapheTest1.sommetsPonderés.get(0));
    
        // String output = grapheTest1.toDotComparasion(grapheTest1_BFS);

        String output = grapheTest1.toDot();
    
        File dotFile = new File("/Users/ayoub_cm/Desktop/graphe.txt");
            try {
                if(dotFile.createNewFile())
                System.out.println("File Created.");
            else	
                System.out.println("File already exists.");
            } catch (Exception e) {
                System.out.println("An error occured.");
            }
    
            try {
                FileWriter dotWrite = new FileWriter("/Users/ayoub_cm/Desktop/graphe.txt");
                dotWrite.write(output);
                dotWrite.close();
            } catch (Exception e) {
                System.out.println("An error occured.");
            }
    
        }    
}
