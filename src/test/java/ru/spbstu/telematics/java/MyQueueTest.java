package ru.spbstu.telematics.java;

import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

public class MyQueueTest {
  private Queue<Integer> baseQueue;
  private MyQueue<Integer> myQueue;

  @Before
  public void setup() {
    baseQueue = new LinkedList<>();
    myQueue = new MyQueue<>();
  }

  @Test
  public void testAddAndElement() {
    baseQueue.add(10);
    myQueue.add(10);

    assertEquals(baseQueue.element(), myQueue.element());
    assertEquals(baseQueue.element(), myQueue.element());
  }

  @Test
  public void testAddNull() {
    assertTrue(baseQueue.add(null));
    assertTrue(myQueue.add(null));
  }

  @Test
  public void testElementOnEmptyQueue() {
    try {
      baseQueue.element();
      assertTrue("Expected NoSuchElementException", false);
    } catch (NoSuchElementException e) {
    }

    try {
      myQueue.element();
      assertTrue("Expected NoSuchElementException", false);
    } catch (NoSuchElementException e) {
    }
  }

  @Test
  public void testOfferAndPeek() {
    assertEquals(baseQueue.peek(), myQueue.peek());

    baseQueue.offer(20);
    myQueue.offer(20);

    assertEquals(baseQueue.peek(), myQueue.peek());
    assertEquals(baseQueue.peek(), myQueue.peek());
  }

  @Test
  public void testOfferWithNull() {
    assertTrue(baseQueue.offer(null));
    assertTrue(myQueue.offer(null));
  }

  @Test
  public void testPeekOnEmptyQueue() {
    assertNull(baseQueue.peek());
    assertNull(myQueue.peek());
  }

  @Test
  public void testAddAndPoll() {
    baseQueue.add(30);
    myQueue.add(30);

    assertEquals(baseQueue.poll(), myQueue.poll());
  }

  @Test
  public void testPollOnEmptyQueue() {
    assertNull(baseQueue.poll());
    assertNull(myQueue.poll());
  }

  @Test
  public void testRemove() {
    baseQueue.add(40);
    myQueue.add(40);

    assertEquals(baseQueue.remove(), myQueue.remove());
  }

  @Test
  public void testRemoveFromEmptyQueue() {
    try {
      baseQueue.remove();
      assertTrue("Expected NoSuchElementException", false);
    } catch (NoSuchElementException e) {
    }

    try {
      myQueue.remove();
      assertTrue("Expected NoSuchElementException", false);
    } catch (NoSuchElementException e) {
    }
  }

  @Test
  public void testAddAll() {
    List<Integer> list = Arrays.asList(50, 60, 70);
    baseQueue.addAll(list);
    myQueue.addAll(list);

    assertEquals(baseQueue.size(), myQueue.size());
    assertTrue(baseQueue.containsAll(list));
    assertTrue(myQueue.containsAll(list));
  }

  @Test
  public void testClear() {
    baseQueue.add(80);
    myQueue.add(80);

    baseQueue.clear();
    myQueue.clear();

    assertTrue(baseQueue.isEmpty());
    assertTrue(myQueue.isEmpty());
  }

  @Test
  public void testContains() {
    baseQueue.add(90);
    myQueue.add(90);

    assertTrue(baseQueue.contains(90));
    assertTrue(myQueue.contains(90));

    assertFalse(baseQueue.contains(100));
    assertFalse(myQueue.contains(100));
  }

  @Test
  public void testContainsAll() {
    baseQueue.add(10);
    baseQueue.add(20);
    myQueue.add(10);
    myQueue.add(20);

    List<Integer> list = Arrays.asList(10, 20);
    assertTrue(baseQueue.containsAll(list));
    assertTrue(myQueue.containsAll(list));

    list = Arrays.asList(30);
    assertFalse(baseQueue.containsAll(list));
    assertFalse(myQueue.containsAll(list));
  }

  @Test
  public void testEquals() {
    MyQueue<Integer> myOtherQueue = new MyQueue<>();

    myQueue.add(40);
    myOtherQueue.add(40);

    myQueue.add(50);
    myOtherQueue.add(50);

    assertTrue(myQueue.equals(myOtherQueue));
  }

  @Test
  public void testIsEmpty() {
    assertTrue(baseQueue.isEmpty());
    assertTrue(myQueue.isEmpty());

    baseQueue.add(60);
    myQueue.add(60);

    assertFalse(baseQueue.isEmpty());
    assertFalse(myQueue.isEmpty());
  }

  @Test
  public void testIterator() {
    baseQueue.add(70);
    baseQueue.add(80);
    myQueue.add(70);
    myQueue.add(80);

    Iterator<Integer> baseQueueIterator = baseQueue.iterator();
    Iterator<Integer> myQueueIterator = myQueue.iterator();

    while (baseQueueIterator.hasNext() && myQueueIterator.hasNext()) {
      assertEquals(baseQueueIterator.next(), myQueueIterator.next());
    }
  }

  @Test
  public void testRemoveObject() {
    baseQueue.add(10);
    baseQueue.add(20);
    myQueue.add(10);
    myQueue.add(20);

    assertTrue(baseQueue.remove(10));
    assertTrue(myQueue.remove(10));

    assertFalse(baseQueue.contains(10));
    assertFalse(myQueue.contains(10));
  }

  @Test
  public void testRemoveAll() {
    List<Integer> list = Arrays.asList(30, 40);
    baseQueue.add(30);
    baseQueue.add(40);
    myQueue.add(30);
    myQueue.add(40);

    assertTrue(baseQueue.removeAll(list));
    assertTrue(myQueue.removeAll(list));

    assertFalse(baseQueue.containsAll(list));
    assertFalse(myQueue.containsAll(list));
  }

  @Test
  public void testRetainAll() {
    baseQueue.add(50);
    baseQueue.add(60);
    myQueue.add(50);
    myQueue.add(60);

    List<Integer> list = Arrays.asList(50);
    assertTrue(baseQueue.retainAll(list));
    assertTrue(myQueue.retainAll(list));

    assertEquals(1, baseQueue.size());
    assertEquals(1, myQueue.size());
    assertTrue(baseQueue.contains(50));
    assertTrue(myQueue.contains(50));
  }

  @Test
  public void testSize() {
    baseQueue.add(50);
    myQueue.add(50);

    assertEquals(baseQueue.size(), myQueue.size());
  }

  @Test
  public void testToArray() {
    baseQueue.add(60);
    baseQueue.add(70);
    myQueue.add(60);
    myQueue.add(70);

    Object[] baseQueueArray = baseQueue.toArray();
    Object[] myQueueArray = myQueue.toArray();

    assertArrayEquals(baseQueueArray, myQueueArray);
  }

  @Test
  public void testToArrayWithType() {
    baseQueue.add(80);
    myQueue.add(80);

    Integer[] baseQueueArray = baseQueue.toArray(new Integer[0]);
    Integer[] myQueueArray = myQueue.toArray(new Integer[0]);

    assertArrayEquals(baseQueueArray, myQueueArray);
  }
}
