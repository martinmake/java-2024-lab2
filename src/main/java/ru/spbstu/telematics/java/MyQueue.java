package ru.spbstu.telematics.java;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Collection;

public class MyQueue<T> implements Queue<T>, Iterable<T> {
  private Node<T> front;
  private Node<T> rear;
  private int size;

  private static class Node<T> {
    T data;
    Node<T> next;

    Node(T data) {
      this.data = data;
      this.next = null;
    }
  }

  public MyQueue() {
    this.front = this.rear = null;
    this.size = 0;
  }

  @Override
  public boolean add(T elem) {
    try {
      Node<T> newNode = new Node<>(elem);
      if (rear == null) {
        front = rear = newNode;
      } else {
        rear.next = newNode;
        rear = newNode;
      }
      size++;
      return true;
    } catch (OutOfMemoryError e) {
      throw new IllegalStateException("No space is currently available.");
    }
  }

  @Override
  public T element() {
    if (isEmpty()) {
      throw new NoSuchElementException("Queue is empty");
    }
    return front.data;
  }

  @Override
  public boolean offer(T data) {
    try {
      return add(data);
    } catch (IllegalStateException e) {
      return false;
    }
  }

  @Override
  public T peek() {
    return isEmpty() ? null : front.data;
  }

  @Override
  public T poll() {
    if (isEmpty()) {
      return null;
    } return remove();
  }

  @Override
  public T remove() {
    if (isEmpty()) {
      throw new NoSuchElementException("Queue is empty");
    }
    T data = front.data;
    front = front.next;
    if (front == null) {
      rear = null;
    }
    size--;
    return data;
  }

  @Override
  public boolean addAll(Collection<? extends T> c) {
    for (T item : c) {
      offer(item);
    }
    return true;
  }

  @Override
  public void clear() {
    front = rear = null;
    size = 0;
  }

  @Override
  public boolean contains(Object o) {
    for (T item : this) {
      if (item.equals(o)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean containsAll(Collection<?> c) {
    for (Object o : c) {
      if (!contains(o)) {
        return false;
      }
    }
    return true;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }

    MyQueue<T> other = (MyQueue<T>) obj;

    Node<T> current1 = this.front;
    Node<T> current2 = other.front;

    while (current1 != null && current2 != null) {
      if (current1.data != current2.data) {
        return false;
      }
      current1 = current1.next;
      current2 = current2.next;
    }

    return current1 == null && current2 == null;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public Iterator<T> iterator() {
    return new Iterator<T>() {
      private Node<T> current = front;

      @Override
      public boolean hasNext() {
        return current != null;
      }

      @Override
      public T next() {
        if (!hasNext()) {
          throw new NoSuchElementException();
        }
        T data = current.data;
        current = current.next;
        return data;
      }
    };
  }

  @Override
  public boolean remove(Object o) {
    if (isEmpty()) return false;

    if (front.data.equals(o)) {
      remove();
      return true;
    }

    Node<T> current = front;
    while (current.next != null) {
      if (current.next.data.equals(o)) {
        current.next = current.next.next;
        if (current.next == null) {
          rear = current;
        }
        size--;
        return true;
      }
      current = current.next;
    }
    return false;
  }

  @Override
  public boolean removeAll(Collection<?> c) {
    boolean modified = false;
    for (Object o : c) {
      while (remove(o)) {
        modified = true;
      }
    }
    return modified;
  }

  @Override
  public boolean retainAll(Collection<?> c) {
    if (c == null) {
      throw new NullPointerException("Collection cannot be null.");
    }

    boolean modified = false;
    Node<T> curr = front;
    Node<T> prev = null;

    while (curr != null) {
      if (!c.contains(curr.data)) {
        if (prev == null) {
          front = curr.next;
        } else {
          prev.next = curr.next;
        }
        size--;
        modified = true;
      } else {
        prev = curr;
      }
      curr = curr.next;
    }
    rear = prev;

    return modified;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public Object[] toArray() {
    Object[] array = new Object[size];
    int index = 0;
    for (T item : this) {
      array[index++] = item;
    }
    return array;
  }

  @Override
  public <E> E[] toArray(E[] a) {
    if (a.length < size) {
      a = (E[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), size);
    }
    int i = 0;
    for (T item : this) {
      a[i++] = (E) item;
    }
    if (a.length > size) {
      a[size] = null;
    }
    return a;
  }
}
