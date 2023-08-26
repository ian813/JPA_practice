package jpabook.jpashop.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;

// spring Bean으로 spring이 등록해줌
@Repository
@RequiredArgsConstructor
// @PersistenceContext // 엔티티매니저를 주입받음
// private final EntityManager em;
// 	public MemberRepository(EntityManager em) {
// 		this.em = em;
// 	}
// 위 다섯줄을 RequiredArgsConstructor, private final EntityManager em;으로 줄일 수 있음.
public class MemberRepository {

	private final EntityManager em;

	public void save(Member member) {
		em.persist(member);
	}

	public Member findOne(Long id) {
		return em.find(Member.class, id);
	}

	public List<Member> findAll() {
		return em.createQuery("select m from Member m", Member.class)
			.getResultList();
	}

	public List<Member> findByName(String name) {
		return em.createQuery("select m from Member m where m.name = :name", Member.class)
			.setParameter("name", name) // "name" => :name에 바인딩
			.getResultList();
	}
}
