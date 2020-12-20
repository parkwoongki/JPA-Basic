import jpql.Member;
import jpql.MemberDTO;

import javax.persistence.*;
import java.util.List;

public class SelectMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);
            em.persist(member);

            em.flush();
            em.clear();

//            List<Team> result = em.createQuery("select m.team from Member m", Team.class).getResultList();
//            List<Team> result = em.createQuery("select m.team from Member m join m.team t", Team.class).getResultList(); // 가능하면 이렇게 해라
//            em.createQuery("select o.address from Order o", Address.class).getResultList();

            /* 1. */
            List resultList = em.createQuery("select distinct m.username, m.age from Member m").getResultList();
            Object o = resultList.get(0);
            Object[] result = (Object[]) o;
            System.out.println("result[0] = " + result[0]);
            System.out.println("result[0] = " + result[1]);

            /* 2. */
            List<Object[]> resultList2 = em.createQuery("select distinct m.username, m.age from Member m").getResultList();
            System.out.println("result[0] = " + resultList2.get(0)[0]);
            System.out.println("result[0] = " + resultList2.get(0)[1]);

            /* 3. 제일 좋은 방법 */
            List<MemberDTO> resultList1 = em.createQuery("select  new jpql.MemberDTO(m.username, m.age) from Member m", MemberDTO.class).getResultList();
            MemberDTO memberDTO = resultList1.get(0);
            System.out.println("memberDTO.toString() = " + memberDTO.toString());

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
