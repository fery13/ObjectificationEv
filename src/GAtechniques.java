import java.util.ArrayList;

public class GAtechniques {

	
	public static int [][] CrossOver(int [] o0, int [] o1, int v) {
		
		int [][] crossed = new int [2][v];
		
		int pivot = (int)(Math.random()*(v-4));
		pivot += 2;
		
		ArrayList<Integer> cross0 = new ArrayList<Integer> ();
		ArrayList<Integer> cross1 = new ArrayList<Integer> ();
		
		for(int i=0;i<pivot;i++) {
			cross0.add(o0[i]);
			cross1.add(o1[i]);
		}
		for(int i=pivot;i<v;i++) {
			cross0.add(o1[i]);
			cross1.add(o0[i]);
		}
		
		
		ArrayList<Integer> mis0 = new ArrayList<Integer> ();
		ArrayList<Integer> mis1 = new ArrayList<Integer> ();
		
		for(int i=0;i<v;i++) {
			if(!cross0.contains(i))
				mis0.add(i);
			if(!cross1.contains(i))
				mis1.add(i);
		}


		//********duplication check ************
		for(int i=0;i<cross0.size();i++) {
			for(int j=i+1;j<cross0.size();j++) {
				if(cross0.get(i)==cross0.get(j)) {
					cross0.remove(i);
				}
			}
		}
		
		for(int i=0;i<cross1.size();i++) {
			for(int j=i+1;j<cross1.size();j++) {
				if(cross1.get(i)==cross1.get(j)) {
					cross1.remove(i);
				}
			}
		}
		
		for(int i=0;i<mis0.size();i++) {
			cross0.add(mis0.get(i));
		}
		for(int i=0;i<mis1.size();i++) {
			cross1.add(mis1.get(i));
		}
		
		
		
		
		//********End of duplication check ************
		for(int i=0;i<v;i++) {
			if(!cross0.contains(i))
				System.out.println("Something 0000000000000000000000 here baba  ");
			if(!cross1.contains(i))
				System.out.println("Something 1111111111111111111111 here baba  ");
		}
		
		
		for(int i=0;i<v;i++) {
			crossed[0][i] = cross0.get(i);
			crossed[1][i] = cross1.get(i);
		}
			
		
		
		return crossed;
	}
	
	
	public static int [] Mutation(int [] ord, int v) {
		
		int p = (int)(Math.random()*v);
		int q = (int)(Math.random()*v);
		
		while(p==q) {
			p = (int)(Math.random()*v);
			q = (int)(Math.random()*v);
		}
		
		int temp = ord[p];
		ord[p] = ord[q];
		ord[q] = temp;
		
		return ord;
	}
	
	
	
	
}
