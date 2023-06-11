import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


//import junit.framework.Assert;
import org.junit.Assert;

class BinomialHeapTest {

	BinomialHeap bh;
	
	@BeforeEach
	void setUp() throws Exception {
		bh = new BinomialHeap();
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
}
