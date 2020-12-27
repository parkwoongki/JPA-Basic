package SecondJPQL;

import jpql.Member;
import jpql.MemberType;
import jpql.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Collection;
import java.util.List;

public class PathExpressionJPQL {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member1 = new Member();
            member1.setUsername("관리자1");
            Member member2 = new Member();
            member2.setUsername("관리자2");

            em.persist(member1);
            em.persist(member2);

            em.flush();
            em.clear();

            String query =
//                    "select m.team from Member m "; // 묵시적 내부 조인 발생, 성능문제 발생 가능.. 묵시적 조인 안되게 만들어야됨
                    "select t.members from Team t "; // 컬렉션은 .으로 뭐 탐색 불가 .size는 가능

            List result = em.createQuery(query, Collection.class).getResultList();

            for (Object s : result) {
                System.out.println("s = " + s);
            }
            
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
