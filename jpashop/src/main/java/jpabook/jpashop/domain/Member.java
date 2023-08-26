package jpabook.jpashop.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Member {

	@Id @GeneratedValue
	@Column(name = "member_id")
	private Long id;

	private String name;

	// 내장 타입임을 표시
	@Embedded
	private Address address;

	// order 테이블에 있는 member에 의해 매핑
	@OneToMany(mappedBy = "member")
	private List<Order> orders = new ArrayList<>();


}
