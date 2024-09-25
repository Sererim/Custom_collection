package org.collection.lists;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class DoublyLinkedListTest {

  @Test
  void add() {
    DoublyLinkedList<Integer> list = new DoublyLinkedList<>();

    list.add(10);
    assertEquals(1, list.getSize());

    Stream.iterate(0, n -> n + 10)
        .limit(10)
        .forEach( list::add );
    assertEquals(11, list.getSize());
  }

  @Test
  void addAll() {
    ArrayList<String> array = new ArrayList<>();

    Stream.iterate("", n -> n + "s")
        .limit(10)
        .forEach( array::add );

    DoublyLinkedList<String> list = new DoublyLinkedList<>(array);
    assertEquals(array.get(array.size() - 1), list.getLast());
    assertEquals(array.get(0), list.getFirst());
    assertEquals(array.size(), list.getSize());
  }

  @Test
  void getFirst() {
    DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
    assertNull(list.getFirst());

    list.add(10);
    list.add(11);
    assertEquals(10, list.getFirst());

  }

  @Test
  void getLast() {
    DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
    assertNull(list.getLast());

    list.add(10);
    assertEquals(10, list.getLast());

    list.add(11);
    assertEquals(11, list.getLast());

  }

  @Test
  void isEmpty() {
    DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
    assertTrue(list.isEmpty());

    list.add(10);
    assertFalse(list.isEmpty());
  }

  @Test
  void getAndRemoveItem() {
    DoublyLinkedList<Integer> list = new DoublyLinkedList<>();

    Stream.iterate(0, n -> n + 1)
        .limit(10)
        .forEach(list::add);
    assertEquals(0, list.get(0));
    assertEquals(5, list.get(5));

    assertEquals(0, list.remove(0));
    assertEquals(9, list.getSize());

    assertEquals(5, list.remove(4));
    assertEquals(8, list.getSize());
  }
}