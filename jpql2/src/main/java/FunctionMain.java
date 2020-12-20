import jpql.Member;
import jpql.MemberType;
import jpql.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class FunctionMain {
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

            String jpql =
//                    "select 'a' || 'b' from Member m ";
//                    "select locate('de', 'abcdefg') from Member m "; // 4번째를 돌려줌
//            "select size(t.members) from Team t "; // 컬렉션의 사이즈
                    "";

            String query =
//                    "select function('group_concat', m.username) from Member m ";
                    "select group_concat(m.username) from Member m ";

            List<String> result = em.createQuery(query, String.class).getResultList();

            System.out.println("result.size() = " + result.size());
            for (String s : result) {
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
