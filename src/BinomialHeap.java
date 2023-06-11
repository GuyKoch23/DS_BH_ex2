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
		return; // should be replaced by student code
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
		if(heap2.size == 0) {
			return;
		}

		int loopSize = Math.max(this.numTrees, heap2.numTrees) + 1;
		HeapNode carry = null;
		HeapNode prev = this.last;
		//////////////////////////////////////////////////
		// need to handle the case when this is an empty heap
		HeapNode minRank1 = this.last.next; 
		HeapNode minRank2 = heap2.last.next;
		
		for(int i = 0; i < loopSize; i++) {
			if(minRank1.rank != i) {
				if(minRank2.rank != i) { // heap2 does not have a tree with rank i
					if(carry == null) { // no carry - only heap1
						continue;
					}
					else { // 
						carry.next = minRank1;
						prev.next = carry;
						numTrees++;
						size += Math.pow(2, i);
						carry = null;
					}
				}
				else { // heap1 does not have, heap2 has
					if(carry == null) {
						minRank2.next = prev.next;
						prev.next = minRank2;
						size += Math.pow(2, i);
						numTrees++;
					}
					else {
						carry = Link(carry, minRank2);
					}
				}
			}
			else {
				if(minRank2.rank != i) { // heap2 does not have a tree with rank i
					if(carry == null) { // no carry - only heap1
						continue;
					}
					else { // 
						carry = Link(minRank1,carry);
						prev.next = minRank1.next;
						size -= Math.pow(2, i);
						numTrees--;
					}
				}
				else { // heap2 has
					if(carry == null) {
						carry = Link(minRank1,minRank2);
						prev.next = minRank1.next;
						size -= Math.pow(2, i);
						numTrees--;
					}
					else {
						carry = Link(carry, minRank2);
					}
				}
			}
			
			
			
			
			
			
			if(minRank1.rank == i) {
				minRank1 = minRank1.next;
			}
			if(minRank2.rank == i) {
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
		if(tree1.item.key < tree2.item.key) {
			tree2.next = tree1.child.next;
			tree1.child = tree2;
			return tree1;
		}
		else {
			tree1.next = tree2.child.next;
			tree2.child = tree1;
			return tree2;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
