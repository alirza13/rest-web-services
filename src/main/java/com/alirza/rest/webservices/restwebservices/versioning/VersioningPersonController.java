package com.alirza.rest.webservices.restwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {

	@GetMapping("/v1/person")
	public Person getFirstVersionOfPerson() {
		return new Person("Bob Charlie");
	}

	@GetMapping("/v2/person")
	public PersonV2 getSecondVersionOfPerson() {
		Name name = new Name("Bob", "Charlie");
		return new PersonV2(name);
	}

	@GetMapping(path = "/person", params = "version=1")
	public Person getFirstVersionOfPersonRequestParameter() {
		return new Person("Bob Charlie");
	}

	@GetMapping(path = "/person", params = "version=2")
	public PersonV2 getSecondVersionOfPersonRequestParameter() {
		Name name = new Name("Bob", "Charlie");
		return new PersonV2(name);
	}

	@GetMapping(path = "/person", headers = "X-API-VERSION=1")
	public Person getFirstVersionOfPersonRequestHeader() {
		return new Person("Bob Charlie");
	}

	@GetMapping(path = "/person", headers = "X-API-VERSION=2")
	public PersonV2 getSecondVersionOfPersonRequestHeader() {
		Name name = new Name("Bob", "Charlie");
		return new PersonV2(name);
	}

	@GetMapping(path = "/person", produces = "application/vnd.company.app-v1+json")
	public Person getFirstVersionOfPersonAcceptHeader() {
		return new Person("Bob Charlie");
	}
	
	@GetMapping(path = "/person", headers = "application/vnd.company.app-v2+json")
	public PersonV2 getSecondVersionOfPersonAcceptHeader() {
		Name name = new Name("Bob", "Charlie");
		return new PersonV2(name);
	}

}
