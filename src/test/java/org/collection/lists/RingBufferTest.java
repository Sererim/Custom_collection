package org.collection.lists;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class RingBufferTest {

  @Test
  void addAll() {
    ArrayList<Integer> array = new ArrayList<>();
    Stream.iterate(0, n -> n + 1).limit(12)
        .forEach(array::add);

    RingBuffer<Integer> ring1 = new RingBuffer<>(array);
    assertEquals(11, ring1.getLength());
    assertEquals(1, ring1.getOld());

    array.add(13);
    RingBuffer<Integer> ring2 = new RingBuffer<>(array);
    assertEquals(11, ring2.getLength());
    assertEquals(2, ring2.getOld());

  }

  @Test
  void remove() {
    RingBuffer<Integer> ring = new RingBuffer<>();
    assertNull(ring.remove());

    ring.add(10);
    assertEquals(10, ring.remove());

    ring.add(11);
    ring.add(12);
    ring.add(13);
    assertEquals(11, ring.remove());
  }

  @Test
  void getNew() {
    RingBuffer<Integer> ring = new RingBuffer<>();
    assertNull(ring.getNew());

    ring.add(10);
    assertEquals(10, ring.getNew());

    ring.add(11);
    assertEquals(11, ring.getNew());
  }

  @Test
  void getOld() {
    RingBuffer<Integer> ring = new RingBuffer<>();
    assertNull(ring.getOld());

    ring.add(10);
    assertEquals(10, ring.getOld());

    ring.add(11);
    assertEquals(10, ring.getOld());
  }

  @Test
  void isEmpty() {
    RingBuffer<Integer> ring = new RingBuffer<>();
    assertTrue(ring.isEmpty());

    ring.add(10);
    assertFalse(ring.isEmpty());
    assertEquals(1, ring.getLength());
  }

  @Test
  void getLength() {
    RingBuffer<Integer> ring = new RingBuffer<>();
    assertEquals(0, ring.getLength());

    Stream.iterate(0, n -> n + 1)
        .limit(12)
        .forEach(ring::add);
    assertEquals(11, ring.getLength());

    ring.add(13);
    assertEquals(11, ring.getLength());
  }

  @Test
  void getMaxSize() {
    RingBuffer<Integer> ring1 = new RingBuffer<>();
    RingBuffer<Integer> ring2 = new RingBuffer<>(20);

    assertAll(
        () -> assertEquals(12 , ring1.getMaxSize()),
        () -> assertEquals(20, ring2.getMaxSize())
    );
  }
}