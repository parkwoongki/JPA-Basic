package SecondJPQL;

import jpql.Member;
import jpql.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Collection;
import java.util.List;

public class FetchJoin1 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Team teamA = new Team();
            teamA.setName("teamA");
            em.persist(teamA);

            Team teamB = new Team();
            teamB.setName("teamB");
            em.persist(teamB);

            Member member1 = new Member();
            member1.setUsername("회원1");
            member1.setTeam(teamA);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("회원2");
            member2.setTeam(teamA);
            em.persist(member2);

            Member member3 = new Member();
            member3.setUsername("회원3");
            member3.setTeam(teamB);
            em.persist(member3);

            em.flush();
            em.clear();

//            String query =
////                    "select m from Member m ";
//                    "select m from Member m join fetch m.team"; // 얘는 더이상 프록시가 아니다! 지연로딩해도 페치조인이 먼저다. 주로 조회성에 많이쓴다.
//
//            List<Member> result = em.createQuery(query, Member.class).getResultList();
//
//            for (Member member : result) {
//                System.out.println("member= " + member.getUsername() + ", " + member.getTeam().getName());
//                //회원1, 팀A(SQL)
//                //회원2, 팀A(1차캐시)
//                //회원3, 팀A(SQL)
//                // 최악의 경우엔 회원마다 쿼리나간다.. 아주 성능떨어진다. 1+N 쿼리가 된다. 이건 즉시로딩이든 지연로딩이든 발생하니까 fetch join으로 하면 끝남
//            }

            /* 2. 컬렉션 페치 조인 */
            String query2 =
//                    "select t from Team t join fetch t.members";
//                    "select distinct t from Team t join fetch t.members"; // jpa distinct는 뻥튀기된거 줄여서 반환해주기도 한다.
                    "select t from Team t join t.members m"; // 그냥 조인이다. team만 가져온다. 이것도 뻥튀기. 근데 loop돌릴때 proxy가된다.
                                                            // 그래서 members가져올때마다 그때마다 db쿼리날린다..

            List<Team> result2 = em.createQuery(query2, Team.class)
                    .getResultList();

            System.out.println("result2.size() = " + result2.size());

            for (Team team : result2) {
                System.out.println("team = " + team.getName() + "|members = " + team.getMembers().size());
                for (Member member : team.getMembers()) {
                    System.out.println("-> member = " + member);
                }
            }

            // 정합성 때문에 별칭줘서 where로 이런거 x 별도의 쿼리를 날려라.
            // 페이징 일대다 페이징하지마 다 끍어와서 그리고 나서 페이징한다.. 큰일난다. => 일대다로 하지말고 다대일로 페이징처리해라!, 아니면 fetch조인 빼라, batchsize써라

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
