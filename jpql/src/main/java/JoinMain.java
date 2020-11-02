import jpql.Member;
import jpql.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JoinMain {
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
//            member.setUsername("member1");
            member.setUsername("team1");
            member.setAge(10);
            member.changeTeam(team1);
            member.changeTeam(team2);

            em.persist(member);

            em.flush();
            em.clear();

//            String jpql = "select m from Member m inner join m.team t where t.name = :teamName";
//            String jpql = "select m from Member m left outer join m.team t";
//            String jpql = "select m from Member m left join m.team t";
//            String jpql = "select m from Member m, Team t where m.username = t.name";
            String jpql = "select m from Member m left join Team t on m.username = t.name";
            List<Member> result = em.createQuery(jpql, Member.class)
                    .getResultList();

            System.out.println("result.size() = " + result.size());
            for (Member member1 : result) {
                System.out.println("team = " + member1.getTeam().getName());
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
