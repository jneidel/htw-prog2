import java.lang.Comparable;

public class BinNodeGen<E extends Comparable<E>> {
  public E data;
  public BinNodeGen<E> left, right, parent = null;

  public BinNodeGen() {
    this( null, null, null );
    System.err.println( "Created an empty BinNodeGen" );
  }
  public BinNodeGen( E data ) {
    this( data, null, null );
  }
  public BinNodeGen( E data, BinNodeGen<E> left ) {
    this( data, left, null );
  }
  public BinNodeGen( E data, BinNodeGen<E> left, BinNodeGen<E> right ) {
    this.data = data;
    this.left = left;
    this.right = right;
  }

  public void addParent( BinNodeGen<E> parent ) {
    this.parent = parent;
  }

  public String toString() {
    return
      ( this.left != null ?  "(" + this.left + ")" : "" ) +
      this.data +
      ( this.right != null ? "(" + this.right + ")" : "" );
  }
}
