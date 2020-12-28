package jpabook.jpashop;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    public Long save(User member) {
        em.persist(member);
        return member.getId();
    }

    public User find(Long id) {
        return em.find(User.class, id);
    }
}
