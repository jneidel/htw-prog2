public class Matrix {
  private float[][] matrix;
  private int rows;
  private int columns;
  private int rank;

  public Matrix( float[][] data ) throws IllegalArgumentException {
    int verticalLength = data.length;
    int horizontalLength = data[0].length;

    for ( float[] line : data ) {
      if ( line.length != horizontalLength ) {
        throw new IllegalArgumentException( "Horizontal arrays don't have the same length" );
      }

      // for ( float point : line ) {
      //   if ( point == null ) {
      //     throw new IllegalArgumentException( "Data points shall not be null" );
      //   }
      // }
    }

    this.matrix = data;
    this.rows = horizontalLength;
    this.columns = verticalLength;

    if ( verticalLength <= horizontalLength ) {
      this.rank = verticalLength;
    } else {
      this.rank = horizontalLength;
    }
  }

  public String toString() {
    String output = "  Matrix:\n  ";

    for ( float[] line : this.matrix ) {
      for ( float point : line ) {
        output += String.format("%8.1f", point) + " ";
      }
      output += "\n  ";
    }

    return output;
  }

  public float[][] getMatrix() {
    return this.matrix;
  }
  public int getRows() {
    return this.rows;
  }
  public int getColumns() {
    return this.columns;
  }

  public boolean hasSameDimensions( Matrix compareMatrix ) {
    if ( this.getRows() != compareMatrix.getRows() ) {
      return false;
    } else if ( this.getColumns() != compareMatrix.getColumns() ) {
      return false;
    } else {
      return true;
    }
  }

  public boolean equals( Matrix compareMatrix ) {
    if ( this.hasSameDimensions( compareMatrix ) == false ) {
      return false;
    }

    float[][] compareData = compareMatrix.getMatrix();
    for ( int c = 0; c < this.columns; c++ ) {
      for ( int r = 0; r < this.rows; r++ ) {
        if ( this.matrix[c][r] != compareData[c][r] ) {
          return false;
        }
      }
    }

    return true;
  }

  public Matrix add( Matrix addMatrix ) throws IllegalArgumentException {
    if ( this.hasSameDimensions( addMatrix ) == false ) {
      throw new IllegalArgumentException( "Matrices can't be added if they don't have the same dimensions" );
    }

    float[][] addData = addMatrix.getMatrix();
    float[][] resultMatrixData = new float[this.columns][this.rows];
    for ( int c = 0; c < this.columns; c++ ) {
      for ( int r = 0; r < this.rows; r++ ) {
        resultMatrixData[c][r] = this.matrix[c][r] + addData[c][r];
      }
    }

    Matrix resultMatrix = new Matrix( resultMatrixData );
    return resultMatrix;
  }

  public Matrix multiplyScalar( float scalar ) {
    float[][] resultMatrixData = new float[this.columns][this.rows];
    for ( int c = 0; c < this.columns; c++ ) {
      for ( int r = 0; r < this.rows; r++ ) {
        resultMatrixData[c][r] = this.matrix[c][r] * scalar;
      }
    }

    Matrix resultMatrix = new Matrix( resultMatrixData );
    return resultMatrix;
  }

  public Matrix multiply( Matrix multiplicationMatrix ) throws IllegalArgumentException {
    if ( this.rows != multiplicationMatrix.getColumns() ) {
      throw new IllegalArgumentException( "Matrices are unchainable (nicht verkettbar)" );
    }
    int dimension = this.rows;

    float[][] resultMatrixData = new float[this.columns][multiplicationMatrix.getRows()];
    float[][] multMatrixData = multiplicationMatrix.getMatrix();
    for ( int c = 0; c < this.columns; c++ ) {
      for ( int r = 0; r < multiplicationMatrix.getRows(); r++ ) {
        float result = 0;
        for ( int d = 0; d < dimension; d++ ) {
          result += this.matrix[c][d] * multMatrixData[d][r];
        }
        resultMatrixData[c][r] = result;
      }
    }

    Matrix resultMatrix = new Matrix( resultMatrixData );
    return resultMatrix;
  }
}
