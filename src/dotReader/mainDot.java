package dotReader;
import java.util.Scanner;

import GrapheColoré.GrapheColoré;
import GrapheOriente.GrapheOriente;
import GraphePonderé.GraphePonderé;

public class mainDot {
    public static void main(String[] args) {
        System.out.println(
        "\n----------------------------------------------\n" +
        "Lecteur de fichier DOT par ligne de Commande\n" +
        "----------------------------------------------\n" +
        "Vous pouvez entrer le parcours de votre propre fichier.txt contenant un graphe en langage dot" +
        " ou vous pouvez utiliser les fichiers de test suivants.\n" + 
        "Entrez 1 pour choisir votre fichier ou 2 pour utiliser les fichiers de test:"); 

        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

        if(choice == 1){
            System.out.println("Vous avez choisi d'utiliser votre fichier.\nEntrez le chemin de votre fichier dot:");
            String path = sc.nextLine();
            dotReader reader = new dotReader(path);

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

        else if(choice== 2){

            System.out.println("Vous avez choisi d'utiliser les fichiers de test du programme.\n" +
                "Ce programme contient 6 fichiers de test dans deux catégories: graphe orienté ou pas.\n" +
                "Chaque catégorie supporte le poids des arets et le couleurs des sommets."
            );

            System.out.println("Ce test teste un graphe basique (non orienté sans poids et sans couleur):");
            String pathBasique = "src/dotReader/testingFiles/NonOrienté/grapheBasique.txt";
            dotReader readerBasique = new dotReader(pathBasique);
            Testing testBasique = new Testing(readerBasique);
            testBasique.test();

            System.out.println("Ce test teste un graphe pondéré non orienté sans couleur:");
            String pathPonderé = "src/dotReader/testingFiles/NonOrienté/graphePonderé.txt";
            dotReader readerPonderé = new dotReader(pathPonderé);
            Testing testPonderé = new Testing(readerPonderé);
            testPonderé.test();

            System.out.println("Ce test teste un graphe non orienté avec poids et couleur:");
            String pathMixte = "src/dotReader/testingFiles/NonOrienté/grapheMixte.txt";
            dotReader readerMixte = new dotReader(pathMixte);
            Testing testMixte = new Testing(readerMixte);
            testMixte.test();

            System.out.println("Ce test teste un graphe basique orienté sans poids et sans couleur:");
            String pathOriente = "src/dotReader/testingFiles/Orienté/grapheOrienté.txt";
            dotReader readerOriente = new dotReader(pathOriente);
            Testing testOriente = new Testing(readerOriente);
            testOriente.test();

            System.out.println("Ce test teste un graphe orienté avec poids et sans couleur:");
            String pathOrientePondere = "src/dotReader/testingFiles/Orienté/orientéPonderé.txt";
            dotReader readerOrientePondere = new dotReader(pathOrientePondere);
            Testing testOrientePondere = new Testing(readerOrientePondere);
            testOrientePondere.test();

            System.out.println("Ce test teste un graphe orienté avec poids et couleurs:");
            String pathMixteOrienté = "src/dotReader/testingFiles/Orienté/grapheMixte.txt";
            dotReader readerMixteOriente = new dotReader(pathMixteOrienté);
            Testing testMixteOriente = new Testing(readerMixteOriente);
            testMixteOriente.test();

        }
        else {
            System.out.println("Mauvais Choix. Réessayez s'il vous plait.\n");
        }
        
        sc.close();
    }
}
