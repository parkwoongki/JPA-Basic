package hellojpa;

import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;

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

            //== 상속 매핑 ==//
//            Movie movie = new Movie();
//            movie.setDirector("aaaa");
//            movie.setActor("bbbb");
//            movie.setName("바람과함께 사라지다");
//            movie.setPrice(10000);
//
//            em.persist(movie);
//
//            em.flush(); // 1차캐시 아무것도 안남게
//            em.clear();
//
//            Movie findMovie = em.find(Movie.class, movie.getId());
//            System.out.println("findMovie = " + findMovie);

//            Member member = new Member();
//            member.setUsername("user1");
//            member.setCreateBy("kim");
//            member.setCreateDate(LocalDateTime.now());
//
//            em.persist(member);

            /* 프록시 */
//            Member member = em.find(Member.class, 1L);
//            printMember(member); // 멤버만 가져오고 싶을때는? 여기서 팀까지 가져오면 낭비잖아?
//            printMemberAndTeam(member);

            Member member = new Member();
            member.setUsername("hello");

            em.persist(member);

            em.flush();
            em.clear();

            // 
//            Member findMember = em.find(Member.class, member.getId());
            Member findMember = em.getReference(Member.class, member.getId()); // 가짜엔티티 줌
            System.out.println("before findMember class = " + findMember.getClass());
            System.out.println("findMember.getId() = " + findMember.getId());
            System.out.println("findMember.getUsername() = " + findMember.getUsername());
            System.out.println("findMember.getUsername() = " + findMember.getUsername());
            System.out.println("after findMember class = " + findMember.getClass()); // before after이 변하지 않는다. 타입 비교시에는 == 이 아니고 instance of를 이용해야한다.

            Member member1 = new Member();
            member1.setUsername("member1");
            em.persist(member1);

            Member member2 = new Member();
            member1.setUsername("member2");
            em.persist(member2);

            em.flush();
            em.clear();

            Member m1 = em.find(Member.class, member1.getId());
            Member m2 = em.getReference(Member.class, member2.getId());

//            System.out.println("(m1.getClass() == m2.getClass()) = " + (m1.getClass() == m2.getClass())); // find로 찾아온거랑 getReference로 찾아온거랑 다름 instance of를 써서 비교
//            System.out.println("m1 = " + (m1 instanceof Member));
//            System.out.println("m2 = " + (m2 instanceof Member));
//
//            Member m3 = em.find(Member.class, member1.getId());
//            System.out.println("m3.getClass() = " + m3.getClass()); // 실제 Member임 프록시가 아니고
//            Member reference = em.getReference(Member.class, member1.getId());
//            System.out.println("reference.getClass() = " + reference.getClass()); // 근데 이것도 똑같이 Member임 1. 캐시에서 가져오는게 성능 더낫
//            // 2.
//            System.out.println("a == a : " + (m3 == reference)); // 자바 컬렉션처럼 되는거라 true반환해야함 이걸 프록시로 가져오면 타입조차 안맞으니 안된다.

            /* 영속성 컨텍스트 초기화되면? */
//            em.detach(m2); // m2 더이상 영속컨텍에서 관리안해. e.printStackTrace를 보면
//            em.close();
//            em.clear();
//            System.out.println("m2.getUsername() = " + m2.getUsername()); // ㅣLazyInitializationException 뜬다.

            /* 초기화가 됬는지 안됬는지 알수있는것들 */
            System.out.println("isLoaded = " + emf.getPersistenceUnitUtil().isLoaded(m2));
//            m2.getUsername(); // 강제 초기화1
//            System.out.println("isLoaded = " + emf.getPersistenceUnitUtil().isLoaded(m2));
            Hibernate.initialize(m2); // 강제 초기화2
            System.out.println("isLoaded = " + emf.getPersistenceUnitUtil().isLoaded(m2));

            tx.commit(); // 커밋안하면 반영이 안된다
        } catch (Exception e) {
            tx.rollback(); // 에러시 롤백
            e.printStackTrace();
        } finally {
            em.close();
        }

        emf.close();
    }

    /* 프록시 */
//    private static void printMember(Member member) {
//        System.out.println("member.setUsername(); = " + member.setUsername(););
//    }
//
//    private static void printMemberAndTeam(Member member) {
//        String username = member.getUsername();
//        System.out.println("username = " + username);
//
//        Team team = member.getTeam();
//        System.out.println("team.getName() = " + team.getName());
//    }

}
