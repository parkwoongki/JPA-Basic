package basicJPQL;

import jpql.Member;
import jpql.MemberType;
import jpql.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class CaseMain {
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
            member.setUsername("관리자");
//            member.setUsername("team1");
            member.setAge(10);
            member.setType(MemberType.ADMIN);
            member.changeTeam(team1);
            member.changeTeam(team2);

            em.persist(member);

            em.flush();
            em.clear();

//            String jpql =
//                    "select " +
//                            "case when m.age <= 10 then '학생요금' " +
//                                "when m.age >= 60 then '학생요금' " +
//                                "else '일반요금' " +
//                            "end " +
//                    "from Member m ";
//
//            List<String> result = em.createQuery(jpql, String.class).getResultList();
//
//            System.out.println("result.size() = " + result.size());
//            for (String s : result) {
//                System.out.println("s = " + s);
//            }

            String jpql =
//                    "select colesce(m.username, '이름 없는 회원') from Member m ";
            "select nullif(m.username, '관리자') from Member m ";

            List<String> result = em.createQuery(jpql, String.class).getResultList();

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
