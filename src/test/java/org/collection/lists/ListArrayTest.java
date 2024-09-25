package org.collection.lists;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ListArrayTest {

  @Test
  void add() {
    ListArray<Integer> array = new ListArray<>();

    array.add(10);
    assertEquals(array.getLength(), 1);
    assertEquals(array.getSize(), 12);
    assertEquals(array.get(0), 10);
  }

  @Test
  void addAll() {
    ArrayList<Integer> arrayList = new ArrayList<>();

    Stream.iterate(0, n -> n + 1).limit(12)
        .forEach(arrayList::add);

    ListArray<Integer> list = new ListArray<>(arrayList);
    assertEquals(12, list.getLength());
    assertEquals(11, list.get(list.getLength() - 1));

    list.add(12);
    list.add(13);
    assertEquals(24, list.getSize());
    assertEquals(14, list.getLength());
  }

  @Test
  void pop() {
    ListArray<Integer> list = new ListArray<>();

    list.add(10);
    list.add(11);
    list.add(12);
    list.add(13);
    assertEquals(13, list.pop());
  }

  @Test
  void get() {
    ListArray<Integer> list = new ListArray<>();

    list.add(10);
    list.add(11);
    list.add(12);
    list.add(13);
    assertEquals(11, list.get(1));
  }

  @Test
  void sort() {
    ListArray<Integer> list = new ListArray<>();

    Stream.iterate(10, n -> n - 1).limit(10)
        .forEach(list::add);

    list.add(-10000);

    list.sort();

    assertEquals(-10000, list.get(0).intValue());

  }

  @Test
  void isEmpty() {
    ListArray<Integer> list = new ListArray<>();
    assertTrue(list.isEmpty());

    list.add(10);
    assertFalse(list.isEmpty());
  }

  @Test
  void getLength() {
    ListArray<Integer> list = new ListArray<>();

    list.add(10);
    assertEquals(1, list.getLength());

    list.add(11);
    assertEquals(2, list.getLength());

    list.add(12);
    assertEquals(3, list.getLength());

    list.add(13);
    assertEquals(4, list.getLength());
  }

  @Test
  void simpleCompare() {
    ListArray<Integer> list1 = new ListArray<>();
    list1.add(1);
    list1.add(2);
    list1.add(3);
    list1.add(4);

    ListArray<Integer> list2 = new ListArray<>();
    list2.add(1);
    list2.add(2);
    list2.add(3);
    list2.add(4);

    assertEquals(0, list1.compareTo(list2));

    list1.add(10);
    assertEquals(1, list1.compareTo(list2));

    list2.add(10);
    list2.add(11);
    assertEquals(-1, list1.compareTo(list2));
  }

  @Test
  void iterateOver() {
    ListArray<Integer> list = new ListArray<>();
    Stream.iterate(0, n -> n + 1).limit(10)
        .forEach(list::add);

    for (Iterator<Integer> it = list.iterator(); it.hasNext(); ) {
      Integer e = it.next();
      System.out.println(e);
    }
  }


}