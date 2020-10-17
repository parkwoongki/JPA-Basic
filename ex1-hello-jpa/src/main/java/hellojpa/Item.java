package hellojpa;

import javax.persistence.*;

@Entity
//@Inheritance(strategy = InheritanceType.JOINED)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // 한테이블에 때려박으니까 DTYPE이 필수적으로 들어감. 운영상 다 DTYPE이 있는게 좋다. 쿼리 한번나가고 조회도 심플
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) // 이거는 테이블 각각 다만드는거임 @DiscriminatorColumn 안먹힘 당연히 구분할 테이블이 없으니까. 단점 : 통합이안됨 쓰면안됨
@DiscriminatorColumn
public abstract class Item {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private int price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
