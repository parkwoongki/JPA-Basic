package hellojpa;

import javax.persistence.*;

/*
    로그에 쿼리가 보이려면
    <property name="hibernate.show_sql" value="true"/>
    <property name="hibernate.format_sql" value="true"/>
    <property name="hibernate.use_sql_comments" value="true"/>
* */
public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello"); // 데이터베이스당 하나 있어야됨
        EntityManager em = emf.createEntityManager(); // 엔티티 메니저를 통해서 작업

        EntityTransaction tx = em.getTransaction(); // 데이터베이스 모든 변경은 트랜잭션 안에서 일어나야함
        tx.begin();

        // code
        try {
//            //== JPA 시작==//
//            /* 회원 등록 */
//            Member member = new Member();
//            member.setId(2L);
//            member.setName("HelloB"); // 여기까지 해도 안됨 Transaction안에서 작업해야함
//
//            em.persist(member); // 저장!
//
//            /* 단건 조회 */
//            Member findMember = em.find(Member.class, 1L);
//            System.out.println("findMember.getId() = " + findMember.getId());
//            System.out.println("findMember.getName() = " + findMember.getName());
//
//            /* 수정 */
//            findMember.setName("HelloJPA");
//
//            /* 리스트 불러오기 */
//            List<Member> result = em.createQuery("select m from Member as m", Member.class)
//                    .setFirstResult(5)
//                    .setMaxResults(8)
//                    .getResultList(); // 멤버 객체를 대상으로 함
//            // 대상이 테이블이 아니고 객체임
//
//            for (Member m : result) {
//                System.out.println("member.getName() = " + m.getName());
//            }


            //== 영속성 컨텍스트 ==//
//            Member member = new Member();
//            member.setId(101L);
//            member.setName("HelloC");
//
//            System.out.println("Before");
//            em.persist(member); // 여기서부터 영속 상태가 된다. 이때 DB에 저장되는게 아니고 tx.commit 하는 시점에 날라감
//            System.out.println("after");
//
//            Member findMember = em.find(Member.class, 101L);
//            System.out.println("findMember.getId() = " + findMember.getId());
//            System.out.println("findMember.getName() = " + findMember.getName()); // 이걸 찍는데 데이터 베이스에 Select 쿼리가 안나갔다. 이것의 의미

//            Member findMember1 = em.find(Member.class, 101L); // 여기서는 쿼리가 나가야되고
//            Member findMember2 = em.find(Member.class, 101L); // 여기서는 쿼리가 나가면 안된다.
//            // 즉 쿼리 1번만 나간다.
//            System.out.println("result = " +(findMember1==findMember2)); // 동일성을 보장한다. 1차 캐시가 있기 때문에 가능한 것이다.

//            /* 쿼리 시점 */
//            Member member1 = new Member(150L, "A");
//            Member member2 = new Member(160L, "B"); // 영속성 컨텍스트에 차곡차곡 쌓아두고,commit 시점에 쿼리 날라감
//            // 이것의 장점은? buffer기능을 쓸 수 있다.
//
//            em.persist(member1);
//            em.persist(member2);
//            //  <property name="hibernate.jdbc.batch_size" value="10"/> 이런설정을 주면 10개 모았다가 한방에 쿼리침 이런거 알면 성능 더잘나옴
//
//            System.out.println("========================");

//            /* 변경감지 */
//            Member member = em.find(Member.class, 150L);
//            member.setName("ZZZㅋㅋ"); // flush가 일어나면 1차 캐시에 엔티티와 스냅샷을 비교해서 다르면 update 쿼리를 쓰기 지연 저장소에 두고 commit할때 쿼리날림
//
////            em.persist(member); // 이걸 해야할 것 같지만 아니다. 이코드를 쓰면안됨

//            /* flush */
//            Member member = new Member(200L,"member200");
//            em.persist(member);
//
//            em.flush(); // 즉시 쿼리 날라감 1차캐시 안지워짐 그냥 쿼리만 날라가는거임
//            System.out.println("========================"); // 이 선 위로 쿼리 날라감

            /* 준영속상태? */
//            Member member = em.find(Member.class, 150L);
//            member.setName("AAAAA");
////            em.detach(member); // JPA 에서 관리 안함
//            em.clear();// 통째로 날리기 or em.close();
//
//            Member member1 = em.find(Member.class, 150L);

//            Member member = new Member();
//            member.setUsername("C");
//
//            System.out.println("=====================");
//            em.persist(member);
//            System.out.println("member.getId() = " + member.getId());
//            System.out.println("=====================");


            //== 연관 관계 ==//
//            Team team = new Team();
//            team.setName("TeamA");
//            em.persist(team);
//
//            Member member = new Member();
//            member.setUsername("Member1");
////            member.setTeamId(team.getId());
//            member.setTeam(team);
//            em.persist(member);
//
//            em.flush();
//            em.clear();

            //
//            Member findMember = em.find(Member.class, member.getId());
//            Long findTeamId = findMember.getTeamId();
//            Team findTeam = em.find(Team.class, findTeamId);
            //// 이건 객체지향스럽지 않다.

//            Member findMember = em.find(Member.class, member.getId());
//            Team findTeam = findMember.getTeam();
//            System.out.println("findTeam.getName() = " + findTeam.getName());

//            Team newTeam = em.find(Team.class, 100L);
//            findMember.setTeam(newTeam);

//            Member findMember = em.find(Member.class, member.getId());
//            List<Member> members = findMember.getTeam().getMembers();
//
//            for (Member m : members) {
//                System.out.println("m.getUsername() = " + m.getUsername());
//            }



            //== 연관 관계 매핑 주인==//
//            Team team = new Team();
//            team.setName("TeamA");
////            team.getMembers().add(member); // 안됨
//            em.persist(team);
//
//            Member member = new Member();
//            member.setUsername("Member1");
//            member.changeTeam(team);
//            em.persist(member);
//
//            em.flush();
//            em.clear();

//            //== 일대다 ==//
//            Member member = new Member();
//            member.setUsername("asd");
//
//            em.persist(member);
//
//            Team team = new Team();
//            team.setName("TeamA");
//            team.getMembers().add(member);
//
//            em.persist(team);

            tx.commit(); // 커밋안하면 반영이 안된다
        } catch (Exception e) {
            tx.rollback(); // 에러시 롤백
        } finally {
            em.close();
        }

        emf.close();
    }

}
