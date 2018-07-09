package abc.menu.storage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import abc.storage.Merchant;
import abc.storage.Merchant.Address;
import abc.storage.MerchantMongoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MerchantMongoRepositoryTest {
	@Autowired
	private MerchantMongoRepository repository;
	
	@Before
	public void setUp() {
		this.repository.deleteAll();
		Merchant merchant1 = new Merchant("test1");
		Merchant merchant2 = new Merchant("test2");
		assertNull(merchant1.getId());
		assertNull(merchant2.getId());
		this.repository.save(merchant1);
		this.repository.save(merchant2);
		assertNotNull(merchant1.getId());
		assertNotNull(merchant2.getId());
	}

	@Test
	public void testFetchData() {
		Merchant merchant1 = repository.findMerhantByName("test1");
		assertNotNull(merchant1);
		assertNull(repository.findMerhantByName("not_exist"));
		
		List<Merchant> merchants = repository.findAll();
		assertEquals(2, merchants.size());
	}
	
	@Test
	public void testUpdateData() {
		Merchant merchant1 = repository.findMerhantByName("test1");
		merchant1.setAddress(new Address("street", "city", "state", "country", 123));
		repository.save(merchant1);
		Merchant newFetch = repository.findMerchantById(merchant1.getId());
		assertNotNull(newFetch);
		assertEquals("street", newFetch.getAddress().getStreet());
		assertEquals("city", newFetch.getAddress().getCity());
		assertEquals("state", newFetch.getAddress().getState());
		assertEquals("country", newFetch.getAddress().getCountry());
		assertEquals(123, newFetch.getAddress().getZipCode());
	}
	
	@After
    public void tearDown() {
      this.repository.deleteAll();
    }
}
