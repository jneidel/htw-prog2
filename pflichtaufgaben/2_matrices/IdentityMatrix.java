public class IdentityMatrix extends QuadraticMatrix {
  public IdentityMatrix( float[][] data ) throws IllegalArgumentException {
    super( data );

    for ( int i = 0; i < data.length; i++ ) {
      for ( int j = 0; j < data[i].length; j++ ) {
        if ( i == j ) {
          if ( data[i][j] != 1 ) {
            throw new IllegalArgumentException( "Is not an IdentityMatrix" );
          }
        } else {
          if ( data[i][j] != 0 ) {
            throw new IllegalArgumentException( "Is not an IdentityMatrix" );
          }
        }
      }
    }
  }

public String toString() {
    String output = "  IdentityMatrix:\n  ";

    for ( float[] line : this.getMatrix() ) {
      for ( float point : line ) {
        output += String.format("%8.1f", point) + " ";
      }
      output += "\n  ";
    }

    return output;
  }
}
