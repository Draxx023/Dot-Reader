package GrapheOriente;

import java.io.File;
import java.io.FileWriter;

public class MainGrapheOriente {
    public static void main(String[] args) {
        GrapheOriente grapheTest1 = new GrapheOriente(10);
    
        // Connexe avec cycle et poids
        // grapheTest1.addArete(0,3,3);
        // grapheTest1.addArete(3,1,2);
        // grapheTest1.addArete(3,2,1);
        // grapheTest1.addArete(1,4,1);
        // grapheTest1.addArete(2,4,3);
        // grapheTest1.addArete(4,5,3);
        // grapheTest1.addArete(4,6,2);
        // grapheTest1.addArete(5,7,1);
        // grapheTest1.addArete(6,8,1);
        // grapheTest1.addArete(7,9,3);

        // Connexe sans poids
        grapheTest1.addArete(0,3,0);
        grapheTest1.addArete(3,1,0);
        grapheTest1.addArete(3,2,0);
        grapheTest1.addArete(1,4,0);
        grapheTest1.addArete(2,4,0);
        grapheTest1.addArete(4,5,0);
        grapheTest1.addArete(4,6,0);
        grapheTest1.addArete(5,7,0);
        grapheTest1.addArete(6,8,0);
        grapheTest1.addArete(7,9,0);

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
