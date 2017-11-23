
public class MatObj {

	double [][] matrix;
	int [] ordering;
	double cost;
	int index;
	
	public MatObj (double [][] matrix, double cost, int [] ordering, int index) {
		this.matrix = matrix;
		this.cost = cost;
		this.ordering = ordering;
		this.index = index;
	}
	
	
	public void setMtarix (double [][] matrix) {
		this.matrix = matrix;
	}
	public void setCost (double cost) {
		this.cost = cost;
	}
	public void setOrdering (int [] ordering) {
		this.ordering = ordering;
	}
	
	public double [][] getMtarix () {
		return this.matrix; 
	}
	public double getCost () {
		return this.cost; 
	}
	public int [] getOrdering () {
		return this.ordering; 
	}
	public String ToString() {
		int v = matrix.length;
		String answ = "The Matrix \n";
		for(int i=0;i<v;i++) {
			for(int j=0;j<v;j++) {
				if(i == j)
					answ += 0+"  ";
				else
					answ += (int)this.matrix[i][j]+"  ";
					
				
			}
			answ += "\n";
		}
		answ += "The Ordering \n";
		for(int i=0;i<v;i++) {
			answ += this.ordering[i]+"  ";
		}
		answ += "\nThe cost is: \n"+this.cost+"\n";
		
		return answ;
	}
	
	public int getIndex() {
		return this.index;
	}
	
	public void setIndex(int index) {
		this.index = index;
	}
	
	public static String Tostring(MatObj sol) {
		MatObj temp = SideFunctions.fitnessFunction(sol.getOrdering(), sol.getMtarix());
		int v = sol.getOrdering().length;
		String answ = "The Matrix \n";
		for(int i=0;i<v;i++) {
			for(int j=0;j<v;j++) {
				answ += (int)temp.getMtarix()[i][j]+"  ";
			}
			answ += "\n";
		}
		answ += "The Ordering \n";
		for(int i=0;i<v;i++) {
			answ += temp.getOrdering()[i]+"  ";
		}
		answ += "\nThe cost is: \n"+temp.getCost()+"\n";
		
		return answ;
	}
	
	
	
}
