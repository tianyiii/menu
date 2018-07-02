package abc.storage;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MerchantMongoRepository extends MongoRepository<Merchant, String>{
	Merchant findMerhantByName(final String name);
}
