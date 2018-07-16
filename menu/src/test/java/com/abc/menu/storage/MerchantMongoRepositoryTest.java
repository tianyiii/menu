package com.abc.menu.storage;

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
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.abc.menu.storage.Merchant;
import com.abc.menu.storage.MerchantMongoRepository;
import com.abc.menu.storage.Merchant.Address;
import com.github.fakemongo.Fongo;
import com.mongodb.MongoClient;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration
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

	@Configuration
	@EnableMongoRepositories
	@ComponentScan(basePackageClasses = { MerchantMongoRepository.class })
	@PropertySource("classpath:application.properties")
	static class MerchantMongoRepositoryTestConfiguration extends AbstractMongoConfiguration {

		@Override
		protected String getDatabaseName() {
			return "unit_test";
		}

		@Override
		protected String getMappingBasePackage() {
			return "com.abc.menu.storage";
		}

		@Override
		public MongoClient mongoClient() {
			// Use in-memory test database
			return new Fongo("mongo-test").getMongo();
		}

	}
}
