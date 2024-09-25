package org.collection;

import org.collection.lists.DoublyLinkedList;
import org.collection.lists.ListArray;
import org.collection.lists.RingBuffer;

import java.util.ArrayList;
import java.util.stream.Stream;

public class Main {

  public static void main(String[] args) {

    DoublyLinkedList<Integer> list = new DoublyLinkedList<>();

    Stream.iterate(0, n -> n + 1)
        .limit(11)
        .forEach( list::add );

    System.out.println(list);

    RingBuffer<Integer> ring = new RingBuffer<>();
    Stream.iterate(0, n -> n + 1).limit(16)
        .forEach( ring::add );

    ListArray<Integer> array = new ListArray<>();
    Stream.iterate(10, n -> n - 1).limit(10)
        .forEach( array::add );

    System.out.println(array);

    array.sort();
    System.out.println(array);

    return;
  }
}
