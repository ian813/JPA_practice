package jpabook.jpashop.domain;

import javax.persistence.Embeddable;

import lombok.Getter;

// @Embeddable : 어딘 가에 내장될 수 있다.
@Embeddable
@Getter
public class Address {

	private String city;
	private String street;
	private String zipcode;

	// jpa 스펙 상 만들어 놓은 것.. public으로 하면 함부로 사용 가능하므로
	// protected로 막았다. 굳이 상속받을 일 없으니까 이렇게 두면 된다.
	protected Address() {
	}

	public Address(String city, String street, String zipcode) {
		this.city = city;
		this.street = street;
		this.zipcode = zipcode;
	}
}
