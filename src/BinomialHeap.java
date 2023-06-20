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
	
	public int links = 0;
	public int deletedMinRank = 0;
	
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
		
		if(this.last == null) {
			this.last = hn;
			this.min = hn;
			this.size++;
			this.numTrees++;
			return hi;	
		}
		
		if(this.last.next.rank != 0) {
			HeapNode first = this.last.next;
			this.last.next = hn;
			hn.next = first;
			if(hi.key < this.min.item.key) {
				this.min = hn;
			}
			this.numTrees++;
			this.size++;
		}
		
		else {
			BinomialHeap bh = new BinomialHeap();

			bh.min = hn;
			bh.last = hn;
			bh.numTrees = 1;
			bh.size = 1;
			
			this.meld(bh);
		}

		
		return hi;
		
	}

	/**
	 * 
	 * Delete the minimal item
	 *
	 */
	public void deleteMin()
	{
		deletedMinRank += min.rank;
		// will contain this heap without the min tree
		BinomialHeap heap1 = new BinomialHeap();
		heap1.size = (int) (this.size - Math.pow(2,this.min.rank));
		heap1.numTrees = this.numTrees - 1;
		if (heap1.size !=0) {
			HeapNode newMin = null;
			HeapNode newLast = null;
			HeapNode cur = this.min.next;
			HeapNode [] arr = new HeapNode[this.last.rank+1];
			
			HeapNode cur2 = this.last.next;
			for(int i = 0; i < arr.length; i++) {
				if(cur2 != this.min) {
					if(cur2.rank == i) {
						arr[i] = cur2;
						cur2 = cur2.next;
					}
					else {
						arr[i] = new HeapNode();
						arr[i].isVirtual = true;
					}
				}
				else {
					arr[i] = new HeapNode();
					arr[i].isVirtual = true;
					cur2 = cur2.next;
				}

			}
			
			HeapNode firstOf= null;
			HeapNode lastOf= null;
			HeapNode minOf= null;
			HeapNode prev= null;
			int sizeOf = 0;
			int numOfTrees = 0;
			
			for(int i = 0; i < arr.length; i++) {
				if(!arr[i].isVirtual) {
					numOfTrees++;
					sizeOf+=Math.pow(2, i);
					lastOf = arr[i];
					if(firstOf == null) {
						firstOf = arr[i];
					}
					if(prev != null) {
						prev.next = arr[i];
					}
					prev = arr[i];
					if(minOf == null || arr[i].item.key < minOf.item.key) {
						minOf = arr[i];
					}
				}
			}
			lastOf.next = firstOf;
			heap1.min = minOf;
			heap1.last = lastOf;
			heap1.size = sizeOf;
			heap1.numTrees = numOfTrees;
			
		}
		
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
			heap2.min = newMin2;
		}
		
		heap1.meld(heap2);
		this.switchHeaps(heap1);
		this.links += heap1.links;
		

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
		item.key -= diff;
		// if new item key is the new min it will become its tree root.
		if(item.key < this.min.item.key) {
			this.min = item.node;
		}
		// item is already root so no need to go up for fixes
		if(item.node.parent == null) {	
			return;
		}
		int tempkey;
		String tempinfo;
		while(item.node.parent != null && item.node.parent.item.key > item.key) {
			HeapNode hn1 = item.node;
			HeapNode hn2 = item.node.parent;
			
			HeapItem tempItem = item;
			
			hn1.item = hn2.item;
			hn2.item = tempItem;
			item.node = hn2;
			hn1.item.node = hn1;
			
			this.min = hn2;
		}

	}

	/**
	 * 
	 * Delete the item from the heap.
	 *
	 */
	public void delete(HeapItem item) 
	{    
		this.decreaseKey(item, item.key);
		this.deleteMin();
	}

	private void ins2arr3(HeapNode[] arr, HeapNode n)
	{
		assert(arr.length==3);
		for(int i=0; i<arr.length; i++)
		{
			if (arr[i].isVirtual) {
				arr[i] = n;
				return;
			}
		}
		
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
			this.switchHeaps(heap2);
			return;
		}
		if(heap2.size == 0) {
			return;
		}
		
		int arraysLength = Math.max(this.last.rank, heap2.last.rank) + 2;
		HeapNode [] heap1Arr = new HeapNode[arraysLength];
		HeapNode [] heap2Arr = new HeapNode [arraysLength];
		HeapNode [] meldedHeapArr = new HeapNode[arraysLength];
		HeapNode [] sum = new HeapNode[3];
		HeapNode toInsert;
		
		sum[0] = new HeapNode();
		sum[1] = new HeapNode();
		sum[2] = new HeapNode();
		
		sum[0].isVirtual = true;
		sum[1].isVirtual = true;
		sum[2].isVirtual = true;
		
		HeapNode carry = new HeapNode();
		carry.isVirtual = true;
		
		HeapNode cur1 = this.last.next;
		HeapNode cur2 = heap2.last.next;
		
		for(int i = 0; i < arraysLength; i++) {
			if(cur1.rank == i) {
				heap1Arr[i] = cur1;
				cur1 = cur1.next;
			}
			else {
				HeapNode temp = new HeapNode();
				temp.isVirtual = true;
				temp.rank = i;
				heap1Arr[i] = temp;
			}
		}
		for(int i = 0; i < arraysLength; i++) {
			if(cur2.rank == i) {
				heap2Arr[i] = cur2;
				cur2 = cur2.next;
			}
			else {
				HeapNode temp = new HeapNode();
				temp.isVirtual = true;
				temp.rank = i;
				heap2Arr[i] = temp;
			}
		}
		for(int i = 0; i < arraysLength; i++) {
			if(!heap1Arr[i].isVirtual) {
				this.ins2arr3(sum, heap1Arr[i]);
			}
			if(!heap2Arr[i].isVirtual) {
				this.ins2arr3(sum, heap2Arr[i]);
			}
			if(!carry.isVirtual) {
				this.ins2arr3(sum, carry);
			}
			toInsert = new HeapNode();
			carry = new HeapNode();
			toInsert.isVirtual = true;
			carry.isVirtual = true;
			
			if (!sum[2].isVirtual) {
				toInsert = sum[2];
			}
			if(!sum[1].isVirtual) {
				carry = Link(sum[0],sum[1]);
				links++;
			}
			else {
				toInsert = sum[0];
			}
			meldedHeapArr[i] = toInsert;
			
			sum[0] = new HeapNode();
			sum[1] = new HeapNode();
			sum[2] = new HeapNode();
			
			sum[0].isVirtual = true;
			sum[1].isVirtual = true;
			sum[2].isVirtual = true;
			
			
		}
		
		HeapNode firstOf= null;
		HeapNode lastOf= null;
		HeapNode minOf= null;
		HeapNode prev= null;
		int sizeOf = 0;
		int numOfTrees = 0;
		
		for(int i = 0; i < arraysLength; i++) {
			if(!meldedHeapArr[i].isVirtual) {
				numOfTrees++;
				sizeOf+=Math.pow(2, i);
				lastOf = meldedHeapArr[i];
				if(firstOf == null) {
					firstOf = meldedHeapArr[i];
				}
				if(prev != null) {
					prev.next = meldedHeapArr[i];
				}
				prev = meldedHeapArr[i];
				if(minOf == null || meldedHeapArr[i].item.key < minOf.item.key) {
					minOf = meldedHeapArr[i];
				}
			}
		}
		lastOf.next = firstOf;
		this.min = minOf;
		this.last = lastOf;
		this.size = sizeOf;
		this.numTrees = numOfTrees;
		
		
		
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
		return numTrees;
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
		public boolean isVirtual = false;
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
				tree2.next = tree2;
				tree1.next = tree1;
				tree1.rank++;
				return tree1;
			}
			else {
				tree2.child = tree1;
				tree1.parent = tree2;
				tree1.next = tree1;
				tree2.next = tree2;
				tree2.rank++;
				return tree2;
			}
		}
		// Tree 1 will be on top
		if(tree1.item.key < tree2.item.key) {
			tree2.next = tree1.child.next;
			tree1.child.next = tree2;
			tree1.child = tree2;
			tree2.parent = tree1;
			tree1.next = tree1.next;
			tree1.rank++;
			return tree1;
		}
		else {
			tree1.next = tree2.child.next;
			tree2.child.next = tree1;
			tree2.child = tree1;
			tree1.parent = tree2;
			tree2.next = tree2.next;
			tree2.rank++;
			return tree2;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
