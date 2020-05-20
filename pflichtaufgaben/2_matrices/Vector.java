public class Vector extends Matrix {
  private float[] vector;

  public Vector( float[][] data ) throws IllegalArgumentException {
    super( data ); // Validate as matrix

    // Validate as vector
    if ( data.length > 1 ) { // is colum vector
      // validate column vector and convert it to row vector
      float[] rowVector = new float[data.length];
      for ( int r = 0; r < data.length; r++ ) {
        if ( data[r].length > 1 ) {
          throw new IllegalArgumentException( "A column vectors row length shall be 1" );
        }
        rowVector[r] = data[r][0];
      }

      this.vector = rowVector;
    } else { // is row vector
      this.vector = data[0];
    }
  }

  public float[] getVector() {
    return this.vector;
  }

  public float length() {
    float res = 0;

    for ( float i : this.vector ) {
      res += Math.pow( i, 2 );
    }
    res = (float) Math.sqrt( res );

    return res;
  }

  public String toString() {
    String output = "  Vector:\n";

    for ( float point : this.vector ) {
      output += String.format("%8.1f", point) + " ";
    }
    output += "\n";
    return output;
  }
}
