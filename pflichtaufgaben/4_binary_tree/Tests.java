import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class Tests {
  BinTreeGen empty, rootOnly, abb1, abb2;

  @Before
  public void setup() {
    empty = new BinTreeGen( null );

    BinNodeGen n1 = new BinNodeGen( 1 );
    rootOnly = new BinTreeGen( n1 );

    BinNodeGen n2 = new BinNodeGen( 2 );
    BinNodeGen n3 = new BinNodeGen( 3 );
    BinNodeGen n5 = new BinNodeGen( 5 );
    BinNodeGen n7 = new BinNodeGen( 7 );
    BinNodeGen n8 = new BinNodeGen( 8 );
    BinNodeGen n9 = new BinNodeGen( 9 );

    BinNodeGen n21 = new BinNodeGen( 2, n1, n3 );
    n1.addParent( n21 );
    n3.addParent( n21 );
    BinNodeGen n91 = new BinNodeGen( 9, n8 );
    n8.addParent( n91 );
    BinNodeGen n41 = new BinNodeGen( 4, n21, n5 );
    n21.addParent( n41 );
    n5.addParent( n41 );
    BinNodeGen n71 = new BinNodeGen( 7, n41, n91 );
    n41.addParent( n71 );
    n91.addParent( n71 );
    abb1 = new BinTreeGen( n71 ); // as given as Abb. 1

    BinNodeGen n42 = new BinNodeGen( 4, n2, n7 );
    n2.addParent( n42 );
    n7.addParent( n42 );
    BinNodeGen n62 = new BinNodeGen( 6, n42, n9 );
    n42.addParent( n62 );
    n9.addParent( n62 );
    abb2 = new BinTreeGen( n62 ); // as given as Abb. 2
  }

  @Test
  public void countNodes() {
    assertEquals( 0, empty.countNodes() );
    assertEquals( 1, rootOnly.countNodes() );
    assertEquals( 8, abb1.countNodes() );
    assertEquals( 5, abb2.countNodes() );
  }

  @Test
  public void isSorted() {
    assertTrue( empty.isSorted() );
    assertTrue( rootOnly.isSorted() );
    assertTrue( abb1.isSorted() );

    assertFalse( abb2.isSorted() );
  }

  @Test
  public void searchNodeOnUnsortedTree() {
    assertFalse( abb2.searchNode( 6 ) );
  }
  @Test
  public void searchNode() {
    assertTrue( abb1.searchNode( 7 ) );
    assertTrue( abb1.searchNode( 3 ) );

    assertFalse( abb1.searchNode( 6 ) ); // not in tree
  }

  @Test
  public void removeNodeOnUnsortedTree() {
    assertFalse( abb2.removeNode( 6 ) );
  }
  @Test
  public void removeNode() {
    System.out.printf( "Tree pre-removal: %s%n", abb1 );

    assertTrue( abb1.removeNode( 3 ) );
    assertTrue( abb1.removeNode( 9 ) );
    assertTrue( abb1.removeNode( 4 ) );
    assertTrue( abb1.removeNode( 7 ) );

    assertFalse( abb1.removeNode( 10 ) ); // not in tree

    System.out.printf( "Tree post-removal: %s%n", abb1 );

    // assert successful removals
    assertFalse( abb1.searchNode( 3 ) );
    assertFalse( abb1.searchNode( 9 ) );
    assertFalse( abb1.searchNode( 4 ) );
    assertFalse( abb1.searchNode( 7 ) );

    assertTrue( abb1.isSorted() ); // still sorted
  }
}
