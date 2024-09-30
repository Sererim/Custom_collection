package org.collection.lists;

import java.util.*;
import java.util.function.Consumer;

public class ListArray<A> implements Comparable <ListArray<A>> {

  private int pointer = -1;
  private int size;
  private Object[] array;
  private boolean sorted = false;

  public ListArray() {
    this.size = 12;
    this.array = new Object[this.size];
  }

  public ListArray(int size) {
    this.size = size;
    this.array = new Object[this.size];
  }

  public ListArray(Collection<? extends A> collection) {
    this(collection.size());
    this.array = new Object[this.size];
    addAll(collection);
  }

  public ListArray(Collection<? extends A> collection, int size) {
    this(size);
    this.array = new Object[this.size];
    addAll(collection);
  }

  public void add(A item) {
    this.pointer++;
    this.sorted = false;
    // Check if the array was filled.
    if (this.pointer == this.size) {
      resize();
    }
    this.array[this.pointer] = item;
  }

  public void addAll(Collection<? extends A> collection) {
    collection.forEach(
        this::add
    );
  }

  private void resize() {
    Object[] arrayNew = new Object[this.size << 1];
    if (this.size >= 0)
      System.arraycopy(this.array, 0, arrayNew, 0, this.size);

    this.size = arrayNew.length;
    this.array = arrayNew;
  }

  public A pop() {
    return (A) this.array[this.pointer--];
  }

  public A get(int index) {
    if (isEmpty()) return null;
    if (index > pointer || index < 0) return null;

    return (A) this.array[index];
  }

  /**
   * Method for in place sorting.
   */
  public void sort() {
    if (this.sorted) return;

    bubbleSort();
    this.sorted = true;
  }

  private void bubbleSort() {
    int n = this.getLength();
    for (int i = 0; i < n - 1; i++)
      for (int j = 0; j < n - i - 1; j++)
        if (this.array[j].hashCode() > this.array[j + 1].hashCode()) {
          A temp = (A) this.array[j];
          this.array[j] = this.array[j + 1];
          this.array[j + 1] = temp;
        }
  }

  public boolean isEmpty() {
    return (pointer == -1);
  }

  public int getLength() {
    return this.pointer + 1;
  }

  public int getSize() {
    return this.size;
  }

  @Override
  public String toString() {
    StringBuilder result = new StringBuilder();
    result.append("[ ");
    for (Object item : this.array) {
      if (item == null) break;
      result.append(item).append(" ");
    }
    result.append(" ]");
    return result.toString();
  }

  public Iterator<A> iterator() {
    return new Itr();
  }

  private class Itr implements Iterator<A> {

    int cursor;
    int lastRet = -1;

    Itr() {}

    @Override
    public boolean hasNext() {
      return (cursor != size);
    }

    @Override
    public A next() {
      int i = cursor;
      if (i >= size)
        throw new NoSuchElementException();
      Object[] elementData = ListArray.this.array;
      if (i >= elementData.length)
        throw new ConcurrentModificationException();
      cursor = i + 1;
      return (A) elementData[lastRet = i];
    }

    /**
     * Dont use.
     */
    @Override
    public void remove() {
      Iterator.super.remove();
    }

    /**
     * Don't use.
     * @param action The action to be performed for each element
     */
    @Override
    public void forEachRemaining(Consumer<? super A> action) {
      Iterator.super.forEachRemaining(action);
    }
  }

  @Override
  public int compareTo(ListArray<A> o) {
    if (this.pointer == o.pointer) {
      return 0;
    } else if (this.pointer > o.pointer) {
      return 1;
    }
    return -1;
  }
}
