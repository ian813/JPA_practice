package jpabook.jpashop.controller;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BookForm {

	private Long id;

	// 상품 속성
	private String name;
	private int price;
	private int stockQuantity;

	// 책 속성
	private String author;
	private String isbn;
}
