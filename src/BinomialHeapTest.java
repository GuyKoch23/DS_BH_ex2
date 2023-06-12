import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


//import junit.framework.Assert;
import org.junit.Assert;

class BinomialHeapTest {

	BinomialHeap bh;
	BinomialHeap bh1;
	BinomialHeap bh2;
	BinomialHeap bh3;
	BinomialHeap bh4;
	
	@BeforeEach
	void setUp() throws Exception {
		bh = new BinomialHeap();
		bh1 = new BinomialHeap();
		bh2 = new BinomialHeap();
		bh3 = new BinomialHeap();
		bh4 = new BinomialHeap();
	}

	@Test
	void empty_bh_0_size() {
		Assert.assertEquals(0, bh.size());
	}
	
	@Test
	void empty_bh_empty() {
		Assert.assertEquals(true, bh.empty());
	}
	
	@Test
	void empty_bh_min_is_null() {
		Assert.assertEquals(null, bh.findMin());
	}
	
	@Test
	void insert_first_to_empty_bh() {
		bh.insert(1, "Please Word");
		Assert.assertEquals(1, bh.findMin().key);
		Assert.assertEquals(1, bh.last.item.key);
		Assert.assertEquals(1, bh.size);
		Assert.assertEquals(1, bh.numTrees);
	}
	
	@Test
	void insert_2_to_1_bh() {
		bh.insert(1, "Please Word");
		bh.insert(2, "Please Word again");
		Assert.assertEquals(1, bh.findMin().key);
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
		//Assert.assertEquals(2, bh1.findMin().node.rank);
		Assert.assertEquals(4, bh3.findMin().node.child.item.key);
		//Assert.assertEquals(0, bh1.findMin().node.child.next.rank);
	}
	
	
	
	
	
	
	
	
	
	

	
}
