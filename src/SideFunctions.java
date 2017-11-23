import java.util.ArrayList;
import java.util.Collections;

public class SideFunctions {
	
	
	public static MatObj fitnessFunction(int [] ord, double [][] sim) {
		int v = ord.length;
		double cost = 0;
		double [][] changed = new double [v][v];
		
		for(int t=1;t<v;t++) 
			if(true) 
				for(int i=0;i<=t;i++) 
					for(int j=0;j<=t;j++) 
						changed[i][j] = sim[ord[i]][ord[j]];
		
		for(int i=0;i<v;i++) 
			for(int j=i+1;j<v;j++) 
				cost += changed[i][j]* (Math.abs(i-j));
		
		MatObj Candidate = new MatObj(changed, cost, ord, -1);
		
	
		return Candidate;
	}
	

	public static int [][] tournoment (int tournoment, int [][] populations, int population, double [][] sim) {
		
		int v = populations[0].length;
		int [][] filtered = new int [population][v];
		
		for(int i=0;i<population;i++) {
			
			ArrayList <Integer> tor = new ArrayList<Integer> ();
			double cost = v*v*v*v*v;
			int selected = -1;
			for(int t=0;t<tournoment;t++) {
				int index = (int)(Math.random() * population);
				
				if(tor.contains(index))
					t--;
				else
				{
					tor.add(index);
					double tempCOST = fitnessFunction(populations[index], sim).getCost(); 
				
					if( tempCOST < cost) {
						cost = tempCOST;
						selected = index;
					}
				}
			}
			
			for(int j=0;j<v;j++) {
				filtered[i][j] = populations[selected][j];
			}
		}
		
		return filtered;
	}


	public static int [][] elicit (int tournoment, int [][] populations, int population, double [][] sim) {
		int v = populations[0].length;
		int [][] filtered = new int [population][v];
		ArrayList<Integer> ords = new ArrayList<Integer> ();
		ArrayList<int []> Listpopulations = new ArrayList<int []>();
		
		for(int i=0;i<population;i++) { 
			ords.add(i);
			Listpopulations.add(populations[i]);
		}
		
		int sec = population/3;
		for(int i=0;i<sec*2;i++) {
			MatObj bbest = bestOrdering(Listpopulations, sim);
			filtered[i] = bbest.getOrdering();
			filtered[i+1] = bbest.getOrdering();
			Listpopulations.remove(bbest.getIndex());
			i++;
			
		}
		
		for(int i=sec*2;i<population;i++) {
			MatObj bbest = bestOrdering(Listpopulations, sim);
			filtered[i] = bbest.getOrdering();
			Listpopulations.remove(bbest.getIndex());
		}
		
		
		return filtered;
	}
	
	
	
	public static MatObj bestOrdering (ArrayList<int []> populations, double [][] sim) {
		int v = populations.get(0).length;
		double cost = v*v*v*v*v*v*v;
		MatObj best = new MatObj(sim, cost, null, -1);
		int index = -1;
		for(int i=0;i<populations.size();i++) {
			if(SideFunctions.fitnessFunction(populations.get(i), sim).getCost()<cost) {
				best = SideFunctions.fitnessFunction(populations.get(i), sim);
				cost = SideFunctions.fitnessFunction(populations.get(i), sim).getCost();
				index = i;
			}
		}
		//System.out.println(best.ToString());
		best.setIndex(index);
		return best;
	}
	
	
	public static int [][] first_random_population (int population, int v){
		
		int [][] populations = new int [population][v];
		ArrayList<Integer> k = new ArrayList<Integer>();
		for(int p=0;p<v;p++)
			k.add(p);
			
		for(int p=0;p<population;p++) {
			ArrayList<Integer> temp = new ArrayList<Integer>();
			temp.addAll(k);
			 Collections.shuffle(temp);
			 for(int i=0;i<v;i++)
				populations[p][i] = temp.get(i);
		}
		
		return populations;
	}

	
	
	
}
