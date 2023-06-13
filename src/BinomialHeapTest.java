import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


//import junit.framework.Assert;
import org.junit.Assert;

class BinomialHeapTest {

	BinomialHeap bh0;
	BinomialHeap bh1;
	BinomialHeap bh2;
	BinomialHeap bh3;
	BinomialHeap bh4;
	BinomialHeap bh5;
	BinomialHeap bh6;
	
	@BeforeEach
	void setUp() throws Exception {
		bh0 = new BinomialHeap();
		bh1 = new BinomialHeap();
		bh2 = new BinomialHeap();
		bh3 = new BinomialHeap();
		bh4 = new BinomialHeap();
		bh5 = new BinomialHeap();
		bh6 = new BinomialHeap();
	}

	@Test
	void empty_bh_0_size() {
		Assert.assertEquals(0, bh0.size());
	}
	
	@Test
	void empty_bh_empty() {
		Assert.assertEquals(true, bh0.empty());
	}
	
	@Test
	void empty_bh_min_is_null() {
		Assert.assertEquals(null, bh0.findMin());
	}
	
	@Test
	void insert_first_to_empty_bh() {
		bh0.insert(1, "Please Word");
		Assert.assertEquals(1, bh0.findMin().key);
		Assert.assertEquals(1, bh0.last.item.key);
		Assert.assertEquals(1, bh0.size);
		Assert.assertEquals(1, bh0.numTrees);
	}
	
	@Test
	void insert_2_to_1_bh() {
		bh0.insert(1, "Please Word");
		bh0.insert(2, "Please Word again");
		Assert.assertEquals(1, bh0.findMin().key);
	}
	
	@Test
	void insert_two_to_one_bh() {
		
		//BinomialHeap bh1 = new BinomialHeap();;

		bh1.insert(2, "Please Word");
		bh1.insert(3, "Please Word again");
		bh1.insert(1, "Please Word again");

		Assert.assertEquals(1, bh1.findMin().key);
		Assert.assertEquals(2, bh1.last.item.key);
		Assert.assertEquals(2, bh1.findMin().node.next.item.key);
		Assert.assertEquals(3, bh1.last.child.item.key);
		Assert.assertEquals(1, bh1.last.next.item.key);
	}
	
	
	@Test
	void insert_two_to_two_bh() {
		
		//BinomialHeap bh1 = new BinomialHeap();;
		//BinomialHeap bh2 = new BinomialHeap();;

		bh3.insert(1, "Please Word");
		bh3.insert(3, "Please Word again");
		bh4.insert(4, "Please Word again");
		bh4.insert(6, "Please Word again");
		
		bh3.meld(bh4);

		Assert.assertEquals(1, bh3.findMin().key);
		Assert.assertEquals(1, bh3.last.item.key);
		Assert.assertEquals(2, bh3.findMin().node.rank);
		Assert.assertEquals(4, bh3.findMin().node.child.item.key);
		Assert.assertEquals(0, bh3.findMin().node.child.next.rank);
	}
	
	@Test
	 void meld_two_empty_heaps() {
		 bh0.meld(bh1);
		 Assert.assertEquals(0, bh0.size);
		 Assert.assertEquals(0, bh0.numTrees);
		 Assert.assertEquals(null, bh0.min);
		 Assert.assertEquals(null, bh0.last);
	 }
	
	@Test
	 void meld_one_with_two_heaps() {
		 
		bh0.insert(1, "hey");
		bh1.insert(2, "hey");
		bh1.insert(3, "hey");
		
		bh0.meld(bh1);
		
		Assert.assertEquals(3, bh0.size);
		Assert.assertEquals(2, bh0.numTrees);
		Assert.assertEquals(1, bh0.min.item.key);
		Assert.assertEquals(2, bh0.last.item.key);
	 }
	
	@Test
	 void meld_two_with_one_heaps() {
		 
		bh0.insert(1, "hey");
		bh1.insert(2, "hey");
		bh1.insert(3, "hey");
		
		bh0.meld(bh1);
		
		Assert.assertEquals(3, bh0.size);
		Assert.assertEquals(2, bh0.numTrees);
		Assert.assertEquals(1, bh0.min.item.key);
		Assert.assertEquals(2, bh0.last.item.key);
	 }
	
	@Test
	 void meld_two_with_one_heaps2() {
		 
		bh0.insert(6, "hey");
		bh1.insert(3, "hey");
		bh1.insert(1, "hey");
		
		bh0.meld(bh1);
		
		Assert.assertEquals(3, bh0.size);
		Assert.assertEquals(2, bh0.numTrees);
		Assert.assertEquals(1, bh0.min.item.key);
		Assert.assertEquals(1, bh0.last.item.key);
	 }
	
	
	@Test
	 void meld_two_with_one_heaps3() {
		 
		bh0.insert(6, "hey");
		bh1.insert(6, "hey");
		bh1.insert(1, "hey");
		
		bh0.meld(bh1);
		
		Assert.assertEquals(3, bh0.size);
		Assert.assertEquals(2, bh0.numTrees);
		Assert.assertEquals(1, bh0.min.item.key);
		Assert.assertEquals(1, bh0.last.item.key);
		Assert.assertEquals(6, bh0.last.next.item.key);
		Assert.assertEquals(0, bh0.last.next.rank);
		Assert.assertEquals(1, bh0.last.rank);
		Assert.assertEquals(0, bh0.last.child.rank);
		Assert.assertEquals(6, bh0.last.child.item.key);
	 }

	@Test
	 void meld_two_with_one_heaps4() {
		 
		bh0.insert(1, "hey");
		bh1.insert(1, "hey");
		bh1.insert(1, "hey");
		
		bh0.meld(bh1);
		
		Assert.assertEquals(3, bh0.size);
		Assert.assertEquals(2, bh0.numTrees);
		Assert.assertEquals(1, bh0.min.item.key);
		Assert.assertEquals(1, bh0.last.item.key);
		Assert.assertEquals(1, bh0.last.next.item.key);
		Assert.assertEquals(0, bh0.last.next.rank);

	 }
	
	
	@Test
	 void meld_8_with_6_heaps() {
		 
		bh5.insert(8, "hey");
		bh5.insert(4, "hey");
		bh5.insert(5, "hey");
		
		bh6.insert(7, "hey");
		bh6.insert(9, "hey");
		bh6.insert(1, "hey");
		bh6.insert(2, "hey");
		bh6.insert(3, "hey");
		bh6.insert(6, "hey");
		
		bh5.meld(bh6);
		
		Assert.assertEquals(9, bh5.size);
		Assert.assertEquals(2, bh5.numTrees);
		Assert.assertEquals(1, bh5.min.item.key);
		Assert.assertEquals(5, bh5.last.next.item.key);
		Assert.assertEquals(3, bh5.last.rank);
		Assert.assertEquals(0, bh5.last.next.rank);
		Assert.assertEquals(2, bh5.last.child.rank);
		Assert.assertEquals(3, bh5.last.child.item.key);
	 }
	
	
	
	
	
	
	
	
	
	
}
