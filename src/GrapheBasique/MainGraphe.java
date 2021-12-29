package GrapheBasique;
import java.io.File;
import java.io.FileWriter;

public class MainGraphe {
	public static void main(String[] args){
    
	GrapheBasique grapheTest1 = new GrapheBasique(10);

	// Connexe avec cycle
	grapheTest1.addArete(0,3);
	grapheTest1.addArete(3,1);
	grapheTest1.addArete(3,2);
	grapheTest1.addArete(1,4);
	grapheTest1.addArete(2,4);
	grapheTest1.addArete(4,5);
	grapheTest1.addArete(4,6);
	grapheTest1.addArete(6,7);
	grapheTest1.addArete(5,8);
	grapheTest1.addArete(8,9);

	// Connexe sans cycle
	// grapheTest1.addArete(0,3);
	// grapheTest1.addArete(3,1);
	// grapheTest1.addArete(3,2);
	// grapheTest1.addArete(1,4);

	// Non connexe
	// grapheTest1.addArete(3,1);
	// grapheTest1.addArete(3,2);
	// grapheTest1.addArete(1,4);
	// grapheTest1.addArete(2,4);

	// GrapheBasique grapheTest1_BFS = new GrapheBasique(5);
	
	// grapheTest1_BFS = grapheTest1.BFS(grapheTest1.sommets.get(0));

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
