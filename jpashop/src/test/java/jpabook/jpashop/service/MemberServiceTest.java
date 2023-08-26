package jpabook.jpashop.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional // 자동으로 롤백해줌.. 보고 싶으면 rollback을 false로 바꾸기
public class MemberServiceTest {

	@Autowired MemberService memberService;
	@Autowired MemberRepository memberRepository;

	@Test
	// @Rollback(false)
	public void 회원가입() throws Exception {
		//given -> 주어졌을 떄
		Member member = new Member();
		member.setName("kim");

		//when -> 이걸 하면
		Long saveId = memberService.join(member);

		//then -> 이게 된다.
		assertEquals(member, memberRepository.findOne(saveId));
	}

	@Test(expected = IllegalStateException.class)
	public void 중복_회원_예외() throws Exception {
		//given
		Member member1 = new Member();
		member1.setName("kim");

		Member member2 = new Member();
		member2.setName("kim");

		//when
		memberService.join(member1);
		memberService.join(member2); // 예외가 발생해야 한다!!

		// try {
		// 	memberService.join(member2); // 예외가 발생해야 한다!!
		// } catch (IllegalStateException e) {
		// 	return;
		// }

		//then
		fail("예외가 발생해야 한다.");
	}
}