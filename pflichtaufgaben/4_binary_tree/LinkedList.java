import java.lang.Comparable;

public class LinkedList<T extends Comparable<T>> {
  Node head = null;

  private class Node {
    T data;
    Node next;
  }

  public void add( T data ) {
    Node node = new Node();
    node.data = data;
    node.next = null;

    if ( head == null ) {
      head = node;
    } else {
      Node temp = head;
      while ( temp.next != null ) {
        temp = temp.next;
      }
      temp.next = node;
    }
  };

  public boolean isEmpty() {
    return head == null;
  };
  public int size() {
    int counter = 0;

    Node temp = head;
    while ( temp != null ) {
      counter++;
      temp = temp.next;
    }

    return counter;
  };


  public Comparable[] toArray() {
    Comparable[] arr;
    int size = this.size();

    if ( isEmpty() ) {
      arr = new Comparable[0]; // T[0] is not that easy
      return arr;
    }

    arr = new Comparable[size];
    Node current = head;
    for ( int i = 0; i < size; i++ ) {
      arr[i] = current.data;
      Node next = current.next;
      current = next;
    }

    return arr;
  }
}
