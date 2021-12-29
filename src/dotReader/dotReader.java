package dotReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Vector;
import java.util.Map.Entry;
import java.util.regex.*;

import GrapheColoré.*;
import GrapheOriente.*;
import GraphePonderé.*;

public class dotReader {

	GraphePonderé grapheP;

	GrapheColoré grapheC;

	GrapheOriente grapheO;

	Boolean flagWeighted = false;
	Boolean flagColored = false;
	Boolean flagOriented = false;

    String path;

    public dotReader(String Path){
        path=Path;
    }

	public int orientedOrNot(String header){
		if(header.matches("graph [a-zA-Z].*\\{"))
			return 0;
		
		else if(header.matches("digraph [a-zA-Z].*\\{"))
			return 1;
		else
			return -1;
	}

	public boolean isWeightedNode(String line){
		if(line.matches("([0-9]{1,}) -[->] ([0-9]{1,}) \\[label=\"([0-9]{1,})\"\\];"))
			return true;
		else 
			return false;
	}

	public boolean isColoredNode(String line){
		if(line.matches("[0-9]{1,} \\[color=\".*\"\\];"))
			return true;
		else 
			return false;
	}

	public boolean isNotWeighted(String line){
		if(line.matches("([0-9]{1,}) -[->] ([0-9]{1,});"))
			return true;
		else 
			return false;
	}
	
	public Vector<Integer> findNodes(String line){
		// regex pour trouver un nombre
		Pattern p1 = Pattern.compile("([0-9]{1,}) -[->] ([0-9]{1,});", Pattern.MULTILINE);
		Matcher matcher = p1.matcher(line);

		// ce tableau contient au max 4 valeurs: sommetDépart, sommetArrivé, poids, sommetMaximal
		Vector<Integer> données = new Vector<Integer>(4);
		if(matcher.matches()){
			int sommetDépart = Integer.parseInt(matcher.group(1));
			int sommetArrivé = Integer.parseInt(matcher.group(2));
			int max = Math.max(sommetDépart, sommetArrivé);
			données.add(sommetDépart);
			données.add(sommetArrivé);
			données.add(0);
			données.add(max);
			return données;
		}
		return null;
	}
	public Vector<Integer> findNodesWeighted(String line){
		// regex pour trouver un nombre
		Pattern p1 = Pattern.compile("([0-9]{1,}) -[->] ([0-9]{1,}) \\[label=\"([0-9]{1,})\"\\];", Pattern.MULTILINE);
		Matcher matcher = p1.matcher(line);

		// ce tableau contient au max 4 valeurs: sommetDépart, sommetArrivé, poids, sommetMaximal
		Vector<Integer> données = new Vector<Integer>(4);
		if(matcher.matches()){
			int sommetDépart = Integer.parseInt(matcher.group(1));
			int sommetArrivé = Integer.parseInt(matcher.group(2));
			int poids = Integer.parseInt(matcher.group(3));
			int max = Math.max(sommetDépart, sommetArrivé);
			données.add(sommetDépart);
			données.add(sommetArrivé);
			données.add(poids);
			données.add(max);
			return données;
		}
		return null;
	}

	// map.entry<int,str> a la meme fonction que pair<int,string>

	public Map.Entry<Integer,String> findColor(String line){
		Pattern p1 = Pattern.compile("([0-9]{1,}) \\[color=\"(.*)\"\\];", Pattern.MULTILINE);
		Matcher matcher = p1.matcher(line);
		if(matcher.matches()){
			int sommet = Integer.parseInt(matcher.group(1));
			String color = matcher.group(2);
			Map.Entry<Integer,String> node = new AbstractMap.SimpleEntry<Integer,String>(sommet,color);
			return node;
		}
		return null;
	}

	public void getNodes(Vector<Vector<Integer>> nodes, String ligne){
		if(isWeightedNode(ligne)){
			flagWeighted=true;
			nodes.add(findNodesWeighted(ligne));
		}
		if(isNotWeighted(ligne)){
			nodes.add(findNodes(ligne));
		}
	}

	public void getColors(HashMap<Integer,String> colors, String ligne){
		if(isColoredNode(ligne)){
			flagColored=true;
			Map.Entry<Integer,String> resultat = findColor(ligne);
			colors.put(resultat.getKey(), resultat.getValue());
		}
	}

	public int findGraphSize(Vector<Vector<Integer>> nodes){
		int nombreSommets=0;
		for(Vector<Integer> node: nodes){
			if(node.get(3)>nombreSommets){
				nombreSommets=node.get(3);
			}
		}
		return nombreSommets;
	}
    
    public void readDot(){
		try {
			// Access file -> Apply Scanner -> Extract the first line's words to verify the correct syntax.
			File myObj = new File(path);
			Scanner myReader = new Scanner(myObj);
			String header = myReader.nextLine();

			// si le fichier a un entete correcte: graph + un nom commençant par une lettre + {
			// Ce cas analyse les graphes non orientés, ponderés ou non
			if(orientedOrNot(header)==0){
				// Si la syntaxe est juste, on compte le nombre de sommets, tout en enregistrant les lignes dans un vecteur
				Vector<Vector<Integer>> nodes = new Vector<Vector<Integer>>();
				HashMap<Integer,String> colors = new HashMap<Integer,String>();
				String ligne;

				// On lit ligne par ligne et on extrait tous les sommets ou la couleur lorsque la ligne a une syntaxe correcte
				while (myReader.hasNextLine()) {
					ligne = myReader.nextLine();
					getNodes(nodes, ligne);
					getColors(colors, ligne);
				}

				int nombreSommets = findGraphSize(nodes);

				// On crée un nouveau graphe pour y ajouter les arrets en utilisant le vecteur crée avant
				
				if(flagColored){
					grapheC = new GrapheColoré(nombreSommets+1);
					for(Vector<Integer> node: nodes){
						if(node.get(1)>=0){
							grapheC.addArete(node.get(0), node.get(1), node.get(2));
						}
					}
					Set entrySet = colors.entrySet();
					Iterator it = entrySet.iterator();
					while(it.hasNext()){
						Map.Entry<Integer,String> sommetEtCouleur = (Entry<Integer, String>) it.next();
						grapheC.sommetsColorés.get(sommetEtCouleur.getKey()).color=sommetEtCouleur.getValue();
					}
				}
				else{
					grapheP = new GraphePonderé(nombreSommets+1);
					for(Vector<Integer> node: nodes){
						grapheP.addArete(node.get(0), node.get(1), node.get(2));
					}
				}

			}

			// graphe orienté
			else if(orientedOrNot(header)==1){

				flagOriented=true;
				Vector<Vector<Integer>> nodes = new Vector<Vector<Integer>>();
				HashMap<Integer,String> colors = new HashMap<Integer,String>();
				String ligne;

				// On lit ligne par ligne et on extrait tous les nombres lorsque la ligne a une syntaxe correcte
				while (myReader.hasNextLine()) {
					ligne = myReader.nextLine();
					getNodes(nodes, ligne);
					getColors(colors, ligne);
				}
				
				int nombreSommets = findGraphSize(nodes);

				grapheO = new GrapheOriente(nombreSommets+1);

				// creation du graphe
				// Graphe avec couleurs
				if(flagColored){
					Set entrySet = colors.entrySet();
					Iterator it = entrySet.iterator();
					while(it.hasNext()){
						Map.Entry<Integer,String> sommetEtCouleur = (Entry<Integer, String>) it.next();
						grapheO.sommetsOrientés.get(sommetEtCouleur.getKey()).color=sommetEtCouleur.getValue();
					}
				}
				
				// Graphe orienté avec poids ou sans poids
				for(Vector<Integer> node: nodes)
					grapheO.addArete(node.get(0), node.get(1), node.get(2));

			}

			else{
				System.out.println("Wrong header syntax: either graph graph_name{ or digraph graph_name{.\n");
			}
			
			myReader.close();
		}

		catch (FileNotFoundException e) {
			System.out.println("An error occurred." + e);
			e.printStackTrace();
		}
    }
}
