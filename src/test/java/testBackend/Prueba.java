package testBackend;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

public class Prueba {

    @Test
    @Order(2)
    public void test2() {
        System.out.println(2);
    }
    @Test
    @Order(3)
    public void test3() {
        System.out.println(3);
    }
    @Test
    @Order(4)
    public void test4() {
        System.out.println(4);
    }
    @Test
    @Order(1)
    public void test1() {
        System.out.println(1);
    }
    @Test
    @Order(5)
    public void test5() {
        System.out.println(5);
    }
}
