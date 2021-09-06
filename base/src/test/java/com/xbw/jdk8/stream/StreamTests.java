package com.xbw.jdk8.stream;


import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author xbw
 */
class StreamTests {
    private final List<Integer> list = Arrays.asList(1, -1, 2, -2, 4, 2, 3, 4, 6, 5);

    @Test
    void maxAndMin() {
        Integer max = list.stream().max(Integer::compare).orElse(Integer.MAX_VALUE);
        assertEquals(6, max);

        Integer min = list.stream().min(Integer::compare).orElse(Integer.MIN_VALUE);
        assertEquals(-2, min);
    }

    @Test
    void sorted() {
        List<Integer> sortedList = list.stream().sorted().collect(Collectors.toList());
        assertEquals(Arrays.asList(-2, -1, 1, 2, 2, 3, 4, 4, 5, 6), sortedList);

        sortedList = list.stream().sorted((i, j) -> Integer.compare(j, i)).collect(Collectors.toList());
        assertEquals(Arrays.asList(6, 5, 4, 4, 3, 2, 2, 1, -1, -2), sortedList);
    }

    @Test
    void distinct() {
        List<Integer> distinctList = list.stream().distinct().collect(Collectors.toList());
        assertEquals(Arrays.asList(1, -1, 2, -2, 4, 3, 6, 5), distinctList);
    }

    @Test
    void limit() {
        List<Integer> limitList = list.stream().limit(3).collect(Collectors.toList());
        assertEquals(Arrays.asList(1, -1, 2), limitList);
    }

    @Test
    void skip() {
        List<Integer> skipList = list.stream().skip(3).collect(Collectors.toList());
        assertEquals(Arrays.asList(-2, 4, 3, 6, 5), skipList);
    }


    @Test
    void filter() {
        List<Integer> filterList = list.stream().filter(i -> i % 2 == 0).collect(Collectors.toList());
        assertEquals(Arrays.asList(2, -2, 4, 2, 4, 6), filterList);
    }

    @Test
    void reduce() {
        Optional<Integer> optional = list.stream().reduce((i, j) -> i * j);
        if (optional.isPresent()) {
            Integer actual = optional.get();
            assertEquals(11520, actual);
        }
    }

    @Test
    void match() {
        assertTrue(list.stream().noneMatch(i -> i > 6));
        assertTrue(list.stream().anyMatch(i -> i >= 4));
        assertFalse(list.stream().allMatch(i -> i > 4));
    }


    @Test
    void map() {
        List<String> list = Arrays.asList("xbw", "java", "stream");
        List<Integer> mapList = list.stream().map(String::length).collect(Collectors.toList());
        assertEquals(Arrays.asList(3, 4, 6), mapList);
        System.out.printf("mapList = %s%n", mapList);
    }
}