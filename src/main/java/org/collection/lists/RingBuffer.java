package org.collection.lists;

import java.util.Collection;

public class RingBuffer<R> {

  // Static size, buffer cant grow larger.
  final private int maxSize;
  // Dynamic parameter
  private int length = 0;
  // Special node.
  private final Node<R> core = new Node<>();

  public RingBuffer() {
    this.maxSize = 12;
  }

  public RingBuffer(int maxSize) {
    this.maxSize = maxSize;
  }

  public RingBuffer(Collection<? extends R> collection, int maxSize) {
    this.maxSize = maxSize;
    this.addAll(collection);
  }

  public RingBuffer(Collection<? extends R> collection) {
    this();
    this.addAll(collection);
  }

  /**
   * Method to add an item to the ring buffer.
   * @param item of type R
   */
  public void add(R item) {
    Node<R> nodeNew = new Node<>(item);

    // Buffer is empty.
    if (core.next == null) {
      core.next = nodeNew;
      nodeNew.next = core;

      core.prev = nodeNew;
      nodeNew.prev = core;
      this.length++;
    } else {
      // Buffer is full, oldest element is removed, new one is added.
      if (this.length + 1 == this.maxSize) {
        pop();
        this.length--;
        add(item);
      }
      // Buffer has nodes.
      else {
        nodeNew.prev = core;
        core.next.prev = nodeNew;
        nodeNew.next = core.next;
        core.next = nodeNew;
        this.length++;
      }
    }
  }

  public void addAll(Collection<? extends R> collection) {
    collection.forEach( this::add );
  }

  /**
   * Method to remove the oldest element from the buffer.
   * @return data of the oldest element from the buffer. Can return NULL on empty list.
   */
  public R remove() {
    if (isEmpty()) return null;

    return pop();
  }

  /**
   * Internal method.
   * Remove the oldest element in the buffer.
   * @return oldest data of type R in the buffer.
   */
  private R pop() {
    Node<R> oldNode = getOldest();
    oldNode.prev.next = this.core;
    this.core.prev = oldNode.prev;

    oldNode.prev = null;
    oldNode.next = null;

    return oldNode.data;
  }

  /**
   * Method to get the newest data in the buffer.
   * @return Newest data of type R, returns NULL on empty buffer.
   */
  public R getNew() {
    if (isEmpty()) return null;

    return this.core.next.data;
  }

  /**
   * Method to get the oldest data in the buffer.
   * @return Oldest data of type R, returns NULL on empty buffer.
   */
  public R getOld() {
    if (isEmpty()) return null;

    return this.core.prev.data;
  }

  /**
   * Internal method.
   * Can return null on Empty list.
   * @return Newest node in the buffer.
   */
  private Node<R> getNewest() {
    return this.core.next;
  }

  /**
   * Internal method.
   * Can return null on Empty list.
   * @return Oldest node in the buffer.
   */
  private Node<R> getOldest() {
    return this.core.prev;
  }

  /**
   * Method to check if the buffer is empty.
   * @return returns true if buffer is empty and false if there is at least one element present.
   */
  public boolean isEmpty() {
    return (this.length == 0);
  }

  /**
   * Method to get the current length of the ring buffer.
   * length is dynamic, it signifies amount of present nodes in the ring buffer.
   * @return length of the ring buffer.
   */
  public int getLength() {
    return this.length;
  }

  /**
   * Method to get the max size of the ring buffer,
   * max size is final.
   * @return max size of the ring buffer.
   */
  public int getMaxSize() {
    return maxSize;
  }
}
