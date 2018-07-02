package abc.storage;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "merchants")
public class Merchant {
	@Id
	private String id;
	
	private String name;
	
	public Merchant(final String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Merchant [id=" + id + ", name=" + name + "]";
	}
}
