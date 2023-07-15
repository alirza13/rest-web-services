package com.alirza.rest.webservices.restwebservices.versioning;

public class PersonV2 {
	private Name name;

	public Name getName() {
		return name;
	}

	public PersonV2(Name name) {
		super();
		this.name = name;
	}

	public void setName(Name name) {
		this.name = name;
	}

}
