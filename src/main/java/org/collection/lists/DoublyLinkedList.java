package org.collection.lists;


import java.util.Collection;
import java.util.stream.Stream;

public class DoublyLinkedList<I> {

  private int size = 0;

  // We have a single pointer in the List.
  Node<I> head = null;

  /**
   * Empty constructor.
   */
  public DoublyLinkedList() {}

  /**
   * Construct doubly linked list from a collection.
   * @param collection any object that implements Collection interface.
   */
  public DoublyLinkedList(Collection<? extends I> collection) {
    this();
    addAll(collection);
  }

  /**
   * Add a node to the list.
   * @param item of type I.
   */
  public void add(I item) {
    Node<I> node = null;
    if (isEmpty()) {
      node = new Node<>(item);
      this.head = node;
    } else {
      node = new Node<>(getTail(), item);
      node.prev.next = node;
    }
    this.size++;
  }

  public void add(I item, int index) {
    Node<I> node = null;

    // Index is 0.
    if (getNode(index) == getHead()) {
      node = new Node<>(item, this.head);
      node.next.prev = node;
      this.head = node;
    } else {
      node = new Node<>(getNode(index).prev, item, getNode(index));
      node.prev.next = node;
      node.next.prev = node;
    }
    this.size++;
  }

  public void addAll(Collection<? extends I> collection) {
    collection.forEach( this::add );
  }

  /**
   * Get i node's data.
   * @return data of i node over type I.
   */
  public I get(int index) {
    if (isEmpty()) return null; // List is empty.

    // Wrong index.
    if (   index  < 0
        || index >= size)
      return null;

    // Everything is OK.
    return getNode(index).data;
  }

  private Node<I> getNode(int index) {
    Node<I> node = getHead();
    for (int iterator = 0; iterator < index; iterator++) {
      node = node.next;
    }
    return node;
  }

  public I remove(int index) {

    Node<I> node = getNode(index);

    // Node to remove is the Head.
    if (node == getHead()) {
      this.head = node.next;
      node.next = null;
    }
    // Node to remove is the Tail.
    else if (node == getTail()) {
      node.prev.next = null;
      node.prev = null;
    }
    // Node to remove is neither.
    else {
      node.prev.next = node.next;
      node.next.prev = node.prev;

      node.prev = null;
      node.next = null;
    }

    this.size--;
    return node.data;
  }

  public int getSize() {
    return this.size;
  }

  /**
   * Get the head node.
   * @return head node.
   */
  private Node<I> getHead() {
    return head;
  }

  /**
   * Get data from the first node in the list.
   * @return data of the head node.
   */
  public I getFirst() {
    if (isEmpty()) return null;
    return getHead().data;
  }

  /**
   * Get the last node in the list.
   * @return last node in the list.
   */
  private Node<I> getTail() {
    if (isEmpty()) return null; // No tail

    // Iterate through the list.
    Node<I> node = getHead();

    while (node.next != null) {
      node = node.next;
    }
    return node;
  }

  /**
   * Get last node's data.
   * @return data of the last node.
   */
  public I getLast() {
    if (isEmpty()) return null;
    return getTail().data;
  }

  /**
   * Check if list is empty.
   * @return true if list is empty false if at least Node is present.
   */
  public boolean isEmpty() {
    return (this.head == null);
  }

  @Override
  public String toString() {
    Node<I> node = this.head;
    StringBuilder result = new StringBuilder("[ " + node.data);

    while (node.next != null) {
      node = node.next;
      result.append(" ").append(node.data);
    }
    return result.append(" ]").toString();
  }
}
