package hellojpa;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "TEAM_ID")
    private Long teamId;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Team getTeam() {
        return team;
    }

    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }
}

//@Entity
//@SequenceGenerator(name = "member_seq_generator", sequenceName = "member_seq",initialValue = 1, allocationSize = 50) // 미리 50개 사이즈를 가져오고 메모리에서 1씩씀. 근데 처음에 1개 불러오고 다음에 50개 가져와서 51개 됨
////@TableGenerator(
////        name = "MEMBER_SEQ_GENERATOR",
////        table = "MY_SEQUENCES",
////        pkColumnValue = "MEMBER_SEQ", allocationSize = 1)
//public class Member {
//    @Id
////    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB야 알아서 해줘 이런느낌, persist()하면 바로 쿼리 날라감. 즉, 버퍼링처리해서 한번에 쿼리날리는 행위가 불가능
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="member_seq_generator") // 얘는 다음 시퀀스를 가져와서 넣어버림 persist()할때 영속성컨텍스트에 저장함 쿼리는 안날라감. 근데 시퀀스 불러오는 게 안좋지않나?
////    @GeneratedValue(strategy = GenerationType.TABLE,
////            generator = "MEMBER_SEQ_GENERATOR")
//    private Long id;
//
//    @Column(name = "name", updatable = false, nullable = false) // 생성에만 수정X unique는 컬럼에 안씀 테이블에 씀
//    private String username;
//
//    public Member() {
//
//    } // 기본 생성자를 만들어라
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//}
