
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
	
	
	public static void Exp2(int n) {
		System.out.println("");
		System.out.println("n: "+ n);

		
		ArrayList<Integer> arr = new ArrayList<Integer>();
		for(int i = 1; i <= n; i++) {
			arr.add(i);
		}
		//Collections.reverse(arr);
		Collections.shuffle(arr);
		
		long x = System.currentTimeMillis();

		
		BinomialHeap bh2 = new BinomialHeap();
		for(int item : arr) {
			bh2.insert(item, "hey");
		}
		
		for(int i = 1; i <= n/2 ; i++) {
			assert(bh2.findMin().key==i);
			bh2.deleteMin();
		}
		
		
		long y = System.currentTimeMillis();

		System.out.println("Links "+ bh2.links);
		System.out.println("NumTrees " + bh2.numTrees);
		System.out.println("Time "+(y-x));
		System.out.println("Ranks "+bh2.deletedMinRank);
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void Exp3(int n) {
		System.out.println("");
		System.out.println("n: "+ n);

		
		ArrayList<Integer> arr = new ArrayList<Integer>();
		for(int i = n; i >=1 ; i--) {
			arr.add(i);
		}
		//Collections.reverse(arr);
		//Collections.shuffle(arr);
		
		long x = System.currentTimeMillis();

		
		BinomialHeap bh2 = new BinomialHeap();
		for(int item : arr) {
			bh2.insert(item, "hey");
		}
		
		while(bh2.size > 31) {
			bh2.deleteMin();
		}
		
		
		long y = System.currentTimeMillis();

		System.out.println("Links "+ bh2.links);
		System.out.println("NumTrees " + bh2.numTrees);
		System.out.println("Time "+(y-x));
		System.out.println("Ranks "+bh2.deletedMinRank);
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void Exp_roey(int n) {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		for(int i = 1; i <= n; i++) {
			arr.add(i);
		}
		Collections.shuffle(arr);
		
		BinomialHeap bh2 = new BinomialHeap();
		for(int item : arr) {
			bh2.insert(item, "hey");
		}
		
		for(int i = 1; i <= n/2; i++) {
			assert(bh2.findMin().key==i);
			bh2.deleteMin();
		}
		
		ArrayList<Integer> arr2 = new ArrayList<Integer>();
		for(int i = 1; i <= n/2; i++) {
			arr2.add(i);
		}
		for(int i = n+1; i <= n*3/2; i++) {
			arr2.add(i);
		}
		Collections.shuffle(arr2);
		for(int item : arr2) {
			bh2.insert(item, "hey");
		}
		
		for(int i = 1; i <= n*3/2; i++) {
			assert(bh2.findMin().key==i);
			bh2.deleteMin();
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
