public class QuadraticMatrix extends Matrix {
  private int rank;

  public QuadraticMatrix( float[][] data ) throws IllegalArgumentException {
    super( data );

    if ( this.getRows() != this.getColumns() ) {
      throw new IllegalArgumentException( "Not quadratic" );
    }

    this.rank = this.getRows();
  }

  private QuadraticMatrix createIdentityMatrix( int n ) {
    float[][] idMatrixData = new float[n][n];
    for ( int i = 0; i < n; i++ ) {
      for ( int j = 0; j < n; j++ ) {
        if ( i == j ) {
          idMatrixData[i][j] = 1;
        } else {
          idMatrixData[i][j] = 0;
        }
      }
    }

    QuadraticMatrix idMatrix = new QuadraticMatrix( idMatrixData );
    return idMatrix;
  }

  public QuadraticMatrix pow( int n ) {
    if ( n == 0 ) {
      return this.createIdentityMatrix( this.rank );
    }

    float[][] matrix = this.getMatrix();
    float[][] resultMatrixData = new float[this.rank][this.rank];

    for ( int c = 0; c < this.rank; c++ ) {
      for ( int r = 0; r < this.rank; r++ ) {
        resultMatrixData[c][r] = (float) Math.pow( matrix[c][r], n );
      }
    }

    QuadraticMatrix resultMatrix = new QuadraticMatrix( resultMatrixData );
    return resultMatrix;
  }

  public String toString() {
    String output = "  QuadraticMatrix:\n  ";

    for ( float[] line : this.getMatrix() ) {
      for ( float point : line ) {
        output += String.format("%8.1f", point) + " ";
      }
      output += "\n  ";
    }

    return output;
  }
}
