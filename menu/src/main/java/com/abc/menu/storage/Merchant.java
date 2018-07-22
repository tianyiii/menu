package com.abc.menu.storage;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "merchants")
public class Merchant {
	@Data
	public static class Address {
		private String street;
		private String city;
		private String state;
		private String country;
		private int zipCode;

		public Address() {
		}

		public Address(final String street, final String city, final String state, final String country,
				final int zipCode) {
			this.street = street;
			this.city = city;
			this.state = state;
			this.country = country;
			this.zipCode = zipCode;
		}
	}

	@Id
	private String id;
	private String name;
	private Address address;
	private DateTime creationTime = new DateTime().withZone(DateTimeZone.forID("America/Los_Angeles"));

	public Merchant() {
	}

	public Merchant(final String name) {
		this.name = name;
	}
}
