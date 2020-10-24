package hellojpa;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Entity
public class Member /*extends BaseEntity*/ {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

//    @Column(name = "TEAM_ID")
//    private Long teamId;

    @ManyToOne(fetch = FetchType.LAZY) // EAGER은 즉시 가져오는 것
//    @JoinColumn(name = "TEAM_ID", insertable = false, updatable = false) // 야메로 읽기전용으로 만들어버린것
    @JoinColumn
    private Team team;

//    @OneToOne
//    @JoinColumn(name = "LOCKER_ID")
//    private Locker locker;
//
//    @OneToMany(mappedBy = "member")
//    private List<MemberProduct> products = new ArrayList<>();

    @Embedded
    private Period workPeriod;

    @Embedded
    private Address homeAddress;

    @ElementCollection
    @CollectionTable(name = "FAVORITE_FOOD", joinColumns = @JoinColumn(name = "MEMBER_ID"))
    @Column(name = "FOOD_NAME") // 하나 짜리는 예외적으로 가능함
    private Set<String> favoriteFoods= new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "ADDRESS", joinColumns = @JoinColumn(name = "MEMBER_ID"))
    private List<Address> addressHistory = new ArrayList<>();

//    @Embedded
//    @AttributeOverrides({
//            @AttributeOverride(name = "city",column = @Column(name = "WORK_CITY")),
//            @AttributeOverride(name = "street", column = @Column(name = "WORK_STREET")),
//            @AttributeOverride(name = "zipcode", column = @Column(name = "WORK_ZIPCODE"))
//    })
//    private Address workAddress;


    public Set<String> getFavoriteFoods() {
        return favoriteFoods;
    }

    public void setFavoriteFoods(Set<String> favoriteFoods) {
        this.favoriteFoods = favoriteFoods;
    }

    public List<Address> getAddressHistory() {
        return addressHistory;
    }

    public void setAddressHistory(List<Address> addressHistory) {
        this.addressHistory = addressHistory;
    }

    public Period getWorkPeriod() {
        return workPeriod;
    }

    public void setWorkPeriod(Period workPeriod) {
        this.workPeriod = workPeriod;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }

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

    public void setTeam(Team team) {
        this.team = team;
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
