import jpql.Member;

import javax.persistence.*;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Member member = new Member();
            member.setUsername("member1");
            em.persist(member);

            TypedQuery<Member> query1 = em.createQuery("select m from Member m", Member.class);
            TypedQuery<String> query2 = em.createQuery("select m.username from Member m", String.class);
            Query query3 = em.createQuery("select m.username, m.age from Member m"); // 반환타입이 명확하지 않을 때

            TypedQuery<Member> query4 = em.createQuery("select m from Member m", Member.class);
            List<Member> result1 = query4.getResultList();

//            TypedQuery<Member> query5 = em.createQuery("select m from Member m where m.id = 1", Member.class);
//            Member result2 = query5.getSingleResult(); // 이건 문제가 있음 진짜 결과가 하나있을때만 써라 두개이상이면 인정인데 하나가 익셉션터지네.. 에반데
            // 나중에 Spring Data JPA -> 없으면 null 받아냄


            TypedQuery<Member> query6 = em.createQuery("select m from Member m where m.username = :username", Member.class);
            query6.setParameter("username", "member1");
            Member singleResult = query6.getSingleResult();
            System.out.println("singleResult = " + singleResult.getUsername());

            Member singleResult1 = em.createQuery("select m from Member m where m.username = :username", Member.class)
                    .setParameter("username", "member1")
                    .getSingleResult();
            System.out.println("singleResult1 = " + singleResult1.getUsername());
            
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

        emf.close();
    }

}
