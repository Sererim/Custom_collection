package org.collection.lists;

/**
 * Inner Node class.
 * @param <I>
 */
class Node<I> {

  Node<I> prev = null;
  Node<I> next = null;
  I       data = null;

  /**
   * Empty node.
   */
  Node() {}

  /**
   * Node without links.
   * @param data of type I.
   */
  Node(I data) {
    this.data = data;
  }

  /**
   * Node with a previous link.
   * @param prev previous Node.
   * @param data data of type I.
   */
  Node(Node<I> prev, I data) {
    this.prev = prev;
    this.data = data;
  }

  /**
   * Node with a next link.
   * @param next next Node.
   * @param data data of type I.
   */
  Node(I data, Node<I> next) {
    this.next = next;
    this.data = data;
  }

  /**
   * Full node.
   * @param prev previous Node.
   * @param next next Node.
   * @param data data of type I.
   */
  Node(Node<I> prev, I data, Node<I> next) {
    this.next = next;
    this.prev = prev;
    this.data = data;
  }
}
