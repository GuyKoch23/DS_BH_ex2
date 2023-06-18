
import java.util.ArrayList;
import java.util.Collections;

public class mast {
	public static void Main(String [] args) {
		

		
		
	}
	
	public static void Exp1(double n) {
		System.out.println("");
		System.out.println("n: "+ n);

		long x = System.currentTimeMillis();
		BinomialHeap bh1 = new BinomialHeap();
		for(int i = 1; i<= n ; i++) {
			bh1.insert(i, "hey");
		}
		long y = System.currentTimeMillis();
		System.out.println("Links "+ bh1.links);
		System.out.println("NumTrees " + bh1.numTrees);
		System.out.println("Time "+(y-x));
	
	}
	
	
	public static void Exp2(double n) {
		System.out.println("");
		System.out.println("n: "+ n);
		long x = System.currentTimeMillis();

		
		ArrayList<Double> arr = new ArrayList<Double>();
		for(double i = 1; i <= n; i++) {
			arr.add(i);
		}
		Collections.shuffle(arr);
		
		BinomialHeap bh2 = new BinomialHeap();
		for(double item : arr) {
			bh2.insert((int)item, "hey");
		}
		
		for(int i = 0; i < n/2 ; i++) {
			bh2.deleteMin();
		}
		
		
		long y = System.currentTimeMillis();

		System.out.println("Links "+ bh2.links);
		System.out.println("NumTrees " + bh2.numTrees);
		System.out.println("Time "+(y-x));
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
