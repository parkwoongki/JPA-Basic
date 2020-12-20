import jpql.Member;
import jpql.MemberType;
import jpql.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class TypeMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Team team1 = new Team();
            team1.setName("team1");
            Team team2 = new Team();
            team2.setName("team2");
            em.persist(team1);
            em.persist(team2);

            Member member = new Member();
            member.setUsername("member1");
//            member.setUsername("team1");
            member.setAge(10);
            member.setType(MemberType.ADMIN);
            member.changeTeam(team1);
            member.changeTeam(team2);

            em.persist(member);

            em.flush();
            em.clear();

            String jpql = "select m.username, 'HELLO', true from Member m " +
                          "where m.type = :userType";
            List<Object[]> result = em.createQuery(jpql)
                    .setParameter("userType", MemberType.ADMIN)
                    .getResultList();

            System.out.println("result.size() = " + result.size());
            for (Object[] objects : result) {
                System.out.println("objects[0] = " + objects[0]);
                System.out.println("objects[0] = " + objects[1]);
                System.out.println("objects[0] = " + objects[2]);
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
