import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GA {
	
	int generation;
	int population;
	int tournoment;
	int crs;
	int matu;
	double reprod;
	int v;
	double [][] sim;
	
	public GA (int generation, int population, int tournoment, int crs, int matu, int v, double [][] sim) {
		this.generation = generation;
		this.population = population;
		this.tournoment = tournoment;
		this.crs = crs;
		this.matu = matu;
		this.reprod = 100 - (this.crs+this.matu);
		this.v = v;
		this.sim = sim;
	}
	
	
	
	
	
	public MatObj GArun() {
		
		int [][] populations = SideFunctions.first_random_population (this.population, this.v);   // create the first random population.
		
		int [] init = new int [this.v];
		for(int i=0;i<this.v;i++) 
			init[i] = i;
		System.out.println(SideFunctions.fitnessFunction(init, this.sim).ToString());
		
		for(int gen =0;gen<this.generation;gen++) {
			
				populations = SideFunctions.elicit(this.tournoment, populations, this.population, this.sim);  // tournoment process
				
				ArrayList<Integer> members = new ArrayList<Integer> ();
				for(int i=0;i<this.population;i++)
					members.add(i);
				int mems = 0;
				int [][] generation = new int [this.population][this.v]; 
						
				while(mems<this.population) {
					
					int chance = (int)(Math.random()*100);
					
					if(chance<this.crs && mems<this.population-2) {
						int c1 = (int)(Math.random()*members.size());
						int [] ord1 = populations[members.get(c1)];
						members.remove(c1);
						int c2 = (int)(Math.random()*members.size());
						int [] ord2 = populations[members.get(c2)];
						members.remove(c2);
						int [][] crossed = GAtechniques.CrossOver(ord1, ord2, this.v);
						generation[mems] = crossed[0].clone();
						mems++;
						generation[mems] = crossed[1].clone();
						mems++;
					}
					else
					if(chance>=this.crs && chance<(this.crs+this.matu)) {
						int c = (int)(Math.random()*members.size());
						int [] ord = populations[members.get(c)];
						members.remove(c);
						int [] mutated = GAtechniques.Mutation(ord, this.v);
						generation[mems] = mutated.clone();
						mems++;
					}
					else {
						int c = (int)(Math.random()*members.size());
						generation[mems] = populations[members.get(c)].clone();
						members.remove(c);
						mems++;
					}
				}
				populations = generation.clone();
				System.out.println("Gen:  "+gen+"  "+bestOrdering (populations).getCost());
		}
		MatObj best = bestOrdering (populations);
		
		System.out.println(PreparePrint(best));
		return bestOrdering (populations);
	}
	
	public static String PreparePrint(MatObj sol) {
		int v = sol.getOrdering().length;
		String answ = "The Matrix \n";
		for(int i=0;i<v;i++) {
			for(int j=0;j<v;j++) {
				answ += (int)sol.getMtarix()[i][j]+"  ";
			}
			answ += "\n";
		}
		answ += "The Ordering \n";
		for(int i=0;i<v;i++) {
			answ += sol.getOrdering()[i]+"  ";
		}
		answ += "\nThe cost is: \n"+sol.getCost()+"\n";
		
		return answ;
	}
	
	
	
	
	
	private MatObj bestOrdering (int [][] populations) {
		double cost = v*v*v*v*v*v*v;
		MatObj best = new MatObj(sim, cost, null, -1);
		
		for(int i=0;i<this.population;i++) {
			if(SideFunctions.fitnessFunction(populations[i], this.sim).getCost()<cost) {
				best = SideFunctions.fitnessFunction(populations[i], this.sim);
				cost = SideFunctions.fitnessFunction(populations[i], this.sim).getCost();
			}
		}
		//System.out.println(best.ToString());
		return best;
	}
	
	
	

}
