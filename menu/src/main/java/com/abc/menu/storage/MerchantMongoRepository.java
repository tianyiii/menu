package com.abc.menu.storage;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MerchantMongoRepository extends MongoRepository<Merchant, String>{
	Merchant findMerhantByName(final String name);
	Merchant findMerchantById(final String id);
}
