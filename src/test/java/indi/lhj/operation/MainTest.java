package indi.lhj.operation;

import org.junit.Test;

import java.util.Arrays;

public class MainTest {

    @Test
    public void test() {
        Arrays.stream("4 × 3/6".split("\\s+")).forEach(System.out::println);
    }

}
