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
		return; // should be replaced by student code

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

	/**
	 * 
	 * Meld the heap with heap2
	 *
	 */
	public void meld(BinomialHeap heap2)
	{
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

		int loopSize = Math.max(this.numTrees, heap2.numTrees) + 1;
		HeapNode [] carrys = new HeapNode[loopSize+1];

		HeapNode prev = this.last;
		//////////////////////////////////////////////////
		// need to handle the case when this is an empty heap
		HeapNode minRank1 = this.last.next; 
		HeapNode minRank2 = heap2.last.next;
		
		for(int i = 0; i < loopSize; i++) {
			if(minRank1 == null || minRank1.rank != i) {
				if(minRank2 == null || minRank2.rank != i) { // heap2 does not have a tree with rank i
					if(carrys[i] == null) { // no carry - only heap1
						continue;
					}
					else { // 
						carrys[i].next = minRank1 != null ? minRank1 : this.min;
						prev.next = carrys[i];
						numTrees++;
						size += Math.pow(2, i);
						if(this.last == null || carrys[i].rank > this.last.rank) {
							this.last = carrys[i];
						}
						if(this.min == null || carrys[i].item.key < this.min.item.key) {
							this.min = carrys[i];
						}					
					}
				}
				else { // heap1 does not have, heap2 has
					if(carrys[i] == null) {
						minRank2.next = prev.next;
						prev.next = minRank2;
						size += Math.pow(2, i);
						numTrees++;
						if(this.last == null || minRank2.rank > this.last.rank) {
							this.last = minRank2;
						}
						if(this.min == null || minRank2.item.key < this.min.item.key) {
							this.min = minRank2;
						}	
					}
					else {
						carrys[i+1] = Link(carrys[i], minRank2);
					}
				}
			}
			else {
				if(minRank2 == null || minRank2.rank != i) { // heap2 does not have a tree with rank i
					if(carrys[i] == null) { // no carry - only heap1
						continue;
					}
					else { // 
						carrys[i+1] = Link(minRank1,carrys[i]);
						prev.next = minRank1.next;
						size -= Math.pow(2, i);
						numTrees--;
						if(size == 0) {
							this.min = carrys[i+1];
							this.last = carrys[i+1];
							minRank1 = null;
						}
					}
				}
				else { // heap2 has
					if(carrys[i] == null) {
						carrys[i+1] = Link(minRank1,minRank2);
						prev.next = minRank1.next;
						size -= Math.pow(2, i);
						numTrees--;
						if(size == 0) {
							this.min = carrys[i+1];
							this.last = carrys[i+1];
							minRank1 = null;
						}
					}
					else {
						carrys[i+1] = Link(carrys[i], minRank2);
					}
				}
			}
			
			if(minRank1 != null && minRank1.rank == i) {
				minRank1 = minRank1.next;
				prev = prev.next;
			}
			if(minRank2 != null && minRank2.rank == i) {
				minRank2 = minRank2.next;
			}
			
		}
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
