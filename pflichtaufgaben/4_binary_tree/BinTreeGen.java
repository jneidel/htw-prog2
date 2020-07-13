import java.lang.Comparable;

/* Feedback:
 * Deutlich besser als Pflichtaufgabe 3.
 *
 * + Viel Freiheit in der Implementierung (Aufgabenstellung offen genug gesetzt.)
 * + Interessante (und auch anspruchsvolle) Aufgaben und keine stumpfe Wiederholung.
 * + Gute, geforderte Test-cases (Knoten 3,9,4,7,10). Diese haben Fehler in meiner Implementierung offenbart und waren hilfreich.
 *
 * - Es ist nervig, dass keine built-in LinkedList verwendet werden darf.
 *   Mittlerweise sollte es klar sein das dieses Konzept verstanden wurde. Warum muss ich das immer noch selbst implementieren?
 *   Die built-in Variante zu nutzen löst das gegebene Problem für den Studenten nicht allein und bedeutet
 *   mehr Arbeit für beide Parteien: in der Implementierung und Korrektur.
 */

public class BinTreeGen<E extends Comparable<E>> {
  private BinNodeGen<E> root;

  public BinTreeGen( BinNodeGen<E> root ) {
    this.root = root;
  }
  public BinTreeGen() {
    this( null );
    System.err.println( "Created a BinTreeGen without a root" );
  }

  private int countNodes( BinNodeGen<E> n ) {
    int counter = 0;
    if ( n != null ) {
     counter = 1 + countNodes( n.left ) + countNodes( n.right );
    }
    return counter;
  }
  public int countNodes() {
    return countNodes( this.root );
  }

   private void isSorted( BinNodeGen<E> node, LinkedList<E> list ) {
    if ( node != null ) {
      isSorted( node.left, list );
      list.add( node.data );
      isSorted( node.right, list );
    }
  }
  public boolean isSorted() {
    LinkedList<E> list = new LinkedList<>();
    isSorted( root, list );

    Comparable[] arr = list.toArray();
    // check if list is ordered from small -> big
    for ( int i = 1; i < arr.length; i++ ) {
      if ( arr[i].compareTo( arr[i-1] ) < 0 ) { // return false if prev item > cur item
        return false;
      }
    }

    return true;
  }

  private boolean searchNode( BinNodeGen<E> node, E value ) {
    int compare = value.compareTo( node.data );
    if ( compare == 0 ) {
      return true;
    } else if ( compare < 0 && node.left != null ) {
      return searchNode( node.left, value );
    } else if ( compare > 0 && node.right != null ) {
      return searchNode( node.right, value );
    }

    return false; // not found
  }
  public boolean searchNode( E value ) {
    if ( root == null ) {
      return false;
    }
    if ( !this.isSorted() ) {
      return false;
    }

    return searchNode( root, value );
  }

  // removeNode helper functions
  private BinNodeGen<E> getLeftmostChild( BinNodeGen<E> node ) {
    if ( node.left == null ) return node;
    return getLeftmostChild( node.left );
  }
  private void replaceOnParent( BinNodeGen<E> node, BinNodeGen<E> replacementNode ) {
    if ( node.parent == null ) {
      if ( node.data.compareTo( root.data ) == 0 ) { // node is root
        this.root = replacementNode;
        return;
      } else {
        System.err.printf( "Non-root node is missing a parent.%nNode: %s%n", node.toString() );
        return;
      }
    }

    if ( node.parent.left.data.compareTo( node.data ) == 0 ) { // is left child
      node.parent.left = replacementNode;
    } else { // is right child
      node.parent.right = replacementNode;
    }
  }

  private boolean removeNode( BinNodeGen<E> node, E value ) {
    int compare = value.compareTo( node.data );
    if ( compare == 0 ) {
      // replace the node with the correct one to keep the tree sorted
      if ( node.left == null && node.right == null ) { // has no children, just remove it from the parent
        replaceOnParent( node, null );
      } else if ( node.left == null && node.right != null ) { // only right child
        replaceOnParent( node, node.right );
      } else if ( node.left != null && node.right == null ) { // only left child
        replaceOnParent( node, node.left );
      } else { // has two children, replace with the leftmost child of the right child
        BinNodeGen<E> leftmostChild = getLeftmostChild( node.right );

        // remove reference on current parent
        replaceOnParent( leftmostChild, null );
        // leftmostChild has no children of its own, take over node's children
        leftmostChild.left = node.left;
        if ( node.right == null || leftmostChild.data.compareTo( node.right.data ) != 0 ) {
          leftmostChild.right = node.right; // dont link yourself as a child
        }
        // replace node on it's parent
        replaceOnParent( node, leftmostChild );
      }

      return true;
    } else if ( compare < 0 && node.left != null ) {
      return removeNode( node.left, value );
    } else if ( compare > 0 && node.right != null ) {
      return removeNode( node.right, value );
    }

    return false; // not found
  }
  public boolean removeNode( E value ) {
    if ( root == null ) {
      return false;
    }
    if ( !this.isSorted() ) {
      return false;
    }

    return removeNode( root, value );
  }

  public String toString() {
    return this.root.toString();
  }
}
