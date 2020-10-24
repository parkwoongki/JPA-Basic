package hellojpa;

public class ValueMain {
    public static void main(String[] args) {

        Address address1 = new Address("city", "street", "10000");
        Address address2 = new Address("city", "street", "10000");

        System.out.println("address2.equals(address1) = " + address2.equals(address1));
    }
}
