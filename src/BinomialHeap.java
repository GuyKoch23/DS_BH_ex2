/**
 * BinomialHeap
 *
 * An implementation of binomial heap over non-negative integers.
 * Based on exercise from previous semester.
 */
public class BinomialHeap
{
	public int size;
	public HeapNode last;
	public HeapNode min;
	public int numTrees;
	
	private void switchHeaps(BinomialHeap heap2)
	{
		int temp = this.size;
		this.size = heap2.size;
		heap2.size = temp;
		
		temp = this.numTrees;
		this.numTrees = heap2.numTrees;
		heap2.numTrees = temp;
		
		HeapNode temp2 = this.last;
		this.last = heap2.last;
		heap2.last = temp2;
		
		temp2 = this.min;
		this.min = heap2.min;
		heap2.min = temp2;
	}

	/**
	 * 
	 * pre: key > 0
	 *
	 * Insert (key,info) into the heap and return the newly generated HeapItem.
	 *
	 */
	public HeapItem insert(int key, String info) 
	{    
		BinomialHeap bh = new BinomialHeap();
		HeapNode hn = new HeapNode();
		HeapItem hi = new HeapItem();
		
		hn.item = hi;
		hn.child = null;
		hn.next = hn;
		hn.parent = null;
		hn.rank = 0;
		
		hi.key = key;
		hi.info = info;
		hi.node = hn;
		
		bh.min = hn;
		bh.last = hn;
		bh.numTrees = 1;
		bh.size = 1;
		
		this.meld(bh);
		
		return hi;
		
	}

	/**
	 * 
	 * Delete the minimal item
	 *
	 */
	public void deleteMin()
	{
		
		// will contain this heap without the min tree
		BinomialHeap heap1 = new BinomialHeap();
		heap1.size = (int) (this.size - Math.pow(2,this.min.rank));
		heap1.numTrees = this.numTrees - 1;
		HeapNode newMin = null;
		HeapNode newLast = null;
		HeapNode cur = this.min.next;
		while(cur!=this.min) {
			if (newMin == null || cur.item.key <= newMin.item.key) {
				newMin = cur;
			}
			if (newLast == null || cur.rank > newLast.rank) {
				newLast = cur;
			}
			cur = cur.next;
		}
		heap1.last = newLast;
		heap1.min = newMin;
		
		// will contain the min tree children
		BinomialHeap heap2 = new BinomialHeap();
		heap2.size = (int) (Math.pow(2, this.min.rank) - 1);
		heap2.numTrees = this.min.rank;
		if (heap2.size != 0) {
			heap2.last = this.min.child;
			HeapNode newMin2 = heap2.last;
			HeapNode cur2 = heap2.last.next;
			while(cur2!=heap2.last) {
				if (cur2.item.key < newMin2.item.key) {
					newMin2 = cur2;
				}
				cur2 = cur2.next;
			}
		}
		
		heap1.meld(heap2);
		this.switchHeaps(heap1);
		

	}

	/**
	 * 
	 * Return the minimal HeapItem
	 *
	 */
	public HeapItem findMin()
	{
		if(min == null) {
			return null;
		}
		return min.item;
	} 

	/**
	 * 
	 * pre: 0<diff<item.key
	 * 
	 * Decrease the key of item by diff and fix the heap. 
	 * 
	 */
	public void decreaseKey(HeapItem item, int diff) 
	{    
		return; // should be replaced by student code
	}

	/**
	 * 
	 * Delete the item from the heap.
	 *
	 */
	public void delete(HeapItem item) 
	{    
		return; // should be replaced by student code
	}

	private void ins2arr3(HeapNode[] arr, HeapNode n)
	{
		assert(arr.length==3);
		for(int i=0; i<arr.length; i++)
		{
			if (arr[i] == null) {
				arr[i] = n;
				return;
			}
		}
		
		assert(false);
	}
	
	

	
	/**
	 * 
	 * Meld the heap with heap2
	 *
	 */
	public void meld(BinomialHeap heap2)
	{
		//Handle empty heap cases.
		if(this.size == 0 && heap2.size == 0) {
			return;
		}
		if(this.size == 0) {
			this.min = heap2.min;
			this.size = heap2.size;
			this.numTrees = heap2.numTrees;
			this.last = heap2.last;
			return;
		}
		if(heap2.size == 0) {
			return;
		}
		
		// Switch case 1 (this.numTress==1 and heap2.numTrees>1);
		// Switch case 2 (both are size 1, rank of this tree is smaller)
		if (this.numTrees==1) {
			if (heap2.numTrees>1) {
				this.switchHeaps(heap2);
			} else {
				// heap2 has 1 tree
				if (heap2.last.rank > this.last.rank) {
					this.switchHeaps(heap2);
				}
			}
		}

		// Initialize pointers
		int newSize = 0;
		int newTreeNum = 0;
		int loopSize = Math.max(this.last.rank, heap2.last.rank) + 2;
		HeapNode carry = null;
		HeapNode prev = this.last;	
		HeapNode cur1 = this.last.next; 
		HeapNode cur2 = heap2.last.next;
		HeapNode[] sum = new HeapNode[3];
		
		for(int i = 0; i < loopSize; i++) {
			HeapNode next1 = cur1;
			// Check if this heap has a tree with rank i to add to the sum array
			if (cur1!=null && cur1.rank == i) {
				ins2arr3(sum, cur1);
				next1 = cur1.next;
				if (next1 == cur1) {
					next1=null;
				}
			}
			// Check if this heap has a tree with rank i to add to the sum array
			HeapNode next2 = cur2;
			if (cur2!=null && cur2.rank ==i) {
				ins2arr3(sum, cur2);
				next2 = cur2.next;
				if (next2 == cur2) {
					next2=null;
				}
			}
			// Add the carry to the sum array (if it null nothing will change)
			ins2arr3(sum, carry);
			
			// Handle the summation
			HeapNode toInsert = null;
			carry = null;
			
			// 3 trees need to sum, last one will be the tree at rank i to enter the heap.
			if (sum[2] != null) {
				toInsert = sum[2];
			}
			// 3 or 2 trees need to sum. first 2 are the carry
			if (sum[1] != null) {
				carry = Link(sum[0], sum[1]);
			}
			// 0 or 1 trees needs to sum. if there is 1 it will be the tree at rank i to enter the heap.
			else {
				toInsert = sum[0];
			}
			
			if(toInsert != null) {
				newSize += Math.pow(2, i);
				newTreeNum++;
				if (next1!=null) {
					prev.next = toInsert;
					toInsert.next = next1;
					prev = toInsert;
					this.last = toInsert;
					if (this.min.item.key >= toInsert.item.key) {
						this.min = toInsert;
					}	
				}
				// handle the bad case when this heap was only 1 tree.
				else {
					// We think we will get here iff Both heaps have 1 tree with the same rank
					//assert(cur1==null && cur2==null && carry == null);
					this.last = toInsert;
					toInsert.next = toInsert;
					if (this.min == null || this.min.item.key >= toInsert.item.key) {
						this.min = toInsert;
					}
				}
			
			}
			cur1 = next1;
			cur2 = next2;
			sum = new HeapNode[3]; 
		}
		
		this.size = newSize;
		this.numTrees = newTreeNum;
	}
	


	/**
	 * 
	 * Return the number of elements in the heap
	 *   
	 */
	public int size()
	{
		return size;
	}

	/**
	 * 
	 * The method returns true if and only if the heap
	 * is empty.
	 *   
	 */
	public boolean empty()
	{
		return size == 0;
	}

	/**
	 * 
	 * Return the number of trees in the heap.
	 * 
	 */
	public int numTrees()
	{
		return 0; // should be replaced by student code
	}

	/**
	 * Class implementing a node in a Binomial Heap.
	 *  
	 */
	public class HeapNode{
		public HeapItem item;
		public HeapNode child;
		public HeapNode next;
		public HeapNode parent;
		public int rank;
	}

	/**
	 * Class implementing an item in a Binomial Heap.
	 *  
	 */
	public class HeapItem{
		public HeapNode node;
		public int key;
		public String info;
	}
	/////////////////////////////////////////////////////
	/////////////////// Custom Methods //////////////////
	/////////////////////////////////////////////////////
	
	/**
	 * Links two binomial trees of same degree
	 * @pre tree1.parent == null && tree2.parent == null
	 * @pre tree1 != null && tree2 != null
	 * @return
	 */
	public static HeapNode Link(HeapNode tree1, HeapNode tree2) {
		if(tree1.rank == 0) {// also tree2.rank == 0
			if(tree1.item.key < tree2.item.key) {
				tree1.child = tree2;
				tree2.parent = tree1;
				tree1.rank++;
				return tree1;
			}
			else {
				tree2.child = tree1;
				tree1.parent = tree2;
				tree2.rank++;
				return tree2;
			}
		}
		if(tree1.item.key < tree2.item.key) {
			tree2.next = tree1.child.next;
			tree1.child = tree2;
			tree2.parent = tree1;
			tree1.rank++;
			return tree1;
		}
		else {
			tree1.next = tree2.child.next;
			tree2.child = tree1;
			tree1.parent = tree2;
			tree2.rank++;
			return tree2;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
