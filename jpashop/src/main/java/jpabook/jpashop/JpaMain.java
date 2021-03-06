package jpabook.jpashop;

import jpabook.jpashop.domain.Book;
import jpabook.jpashop.domain.Item;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;

import javax.persistence.*;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello"); // 데이터베이스당 하나 있어야됨
        EntityManager em = emf.createEntityManager(); // 엔티티 메니저를 통해서 작업

        EntityTransaction tx = em.getTransaction(); // 데이터베이스 모든 변경은 트랜잭션 안에서 일어나야함
        tx.begin();

        try {

            Order order = new Order();
            order.addOrderItem(new OrderItem());

            Book book = new Book();
            book.setName("JPA");
            book.setAuthor("김영한");
            em.persist(book);

            em.createQuery("select i from Item i where type(i) = Book", Item.class).getResultList();

            tx.commit(); // 커밋안하면 반영이 안된다
        } catch (Exception e) {
            tx.rollback(); // 에러시 롤백
        } finally {
            em.close();
        }

        emf.close();
    }

}