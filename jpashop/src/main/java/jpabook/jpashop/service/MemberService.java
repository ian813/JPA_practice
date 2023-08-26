package jpabook.jpashop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true) // jpa의 모든 데이터 변경이나 로직들은 트랜잭션 안에서 실행되어야 함.
// readOnly = true로 하면 읽기 전용이 됨 -> 즉, 해당 메서드들은 데이터 변경이 안되고 불러오기만 가능
@RequiredArgsConstructor
// 	@Autowired
// 	public MemberService(MemberRepository memberRepository) {
// 		this.memberRepository = memberRepository;
// 	} 이걸 한 번에 RequiredArgsConstructor가 만들어줌
public class MemberService {

	private final MemberRepository memberRepository;

	/**
	 * 회원 가입
	 */
	@Transactional // 이건 데이터를 넣는 것이므로 읽기 전용으로 하면 안돼서 따로 달아줌.
	public Long join(Member member) {
		validateDuplicateMember(member); // 중복 회원 검증
		memberRepository.save(member);
		return member.getId();
	}

	private void validateDuplicateMember(Member member) {
		//Exception
		List<Member> findMembers = memberRepository.findByName(member.getName());
		if(!findMembers.isEmpty()) {
			throw new IllegalStateException("이미 존재하는 회원입니다.");
		}
	}

	/**
	 * 회원 전체 조회
	 */
	public List<Member> findMembers() {
		return memberRepository.findAll();
	}

	public Member findOne(Long memberId) {
		return memberRepository.findOne(memberId);
	}

}
