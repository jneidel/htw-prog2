import org.junit.Test;
import static org.junit.Assert.*;

/*
 * Der erste Test pro Methode ist immer der Normallfall,
 * alle anderen Grenzfälle
 */

public class Tests {
  // Matrix
  float[][] m43Data = {
    { 1, 2, 3 },
    { 4, 5, 6 },
    { 7, 8, 9 },
    { 10, 11, 12 },
  };
  Matrix m43 = new Matrix( m43Data );

  float[][] m33Data = {
    { 1, 2, 3 },
    { 4, 5, 6 },
    { 7, 8, 9 },
  };
  Matrix m33 = new Matrix( m33Data );

  float[][] m22Data = {
    { 1, 2 },
    { 0, 1 },
  };
  Matrix m22 = new Matrix( m22Data );

  float[][] m23Data = {
    { 1, 0, 1 },
    { 2, 0, 0 },
  };
  Matrix m23 = new Matrix( m23Data );

  // equals
  @Test
  public void equalsIdenticalMatrices() {
    Matrix m43_2 = new Matrix( m43Data );

    assertEquals( m43_2.equals( m43 ), true );
  }

  @Test
  public void equalsWrongColumnsMatrices() {
    assertEquals( m43.equals( m33 ), false );
  }

  @Test
  public void equalsWrongValueMatrices() {
    float[][] m43v2Data = {
      { 2, 3, 4 },
      { 5, 6, 7 },
      { 8, 9, 10 },
      { 11, 12, 13 },
    };
    Matrix m43v2 = new Matrix( m43v2Data );

    assertEquals( m43.equals( m43v2 ), false );
  }

  // add
  @Test
  public void addIdenticalMatrices() {
    Matrix m43_2 = new Matrix( m43Data );
    float[][] expectedMatrixData = {
      { 2, 4, 6 },
      { 8, 10, 12 },
      { 14, 16, 18 },
      { 20, 22, 24 },
    };
    Matrix expectedMatrix = new Matrix( expectedMatrixData );

    Matrix resultMatrix = m43.add( m43_2 );

    assertTrue( resultMatrix.equals( expectedMatrix ) );
  }

  @Test( expected = IllegalArgumentException.class )
  public void addDifferentDimensionMatrices() {
    Matrix result = m43.add( m33 );
  }

  // multiplyScalar
  @Test
  public void multiplyScalarTimes2() {
    float[][] expectedMatrixData = {
      { 2, 4, 6 },
      { 8, 10, 12 },
      { 14, 16, 18 },
      { 20, 22, 24 },
    };
    Matrix expectedMatrix = new Matrix( expectedMatrixData );

    Matrix resultMatrix = m43.multiplyScalar( 2 );

    assertTrue( resultMatrix.equals( expectedMatrix ) );
  }

  // multiply
  @Test
  public void muliplyValidMatrices() {
    float[][] expectedMatrixData = {
      { 5, 0, 1 },
      { 2, 0, 0 }
    };
    Matrix expectedMatrix = new Matrix( expectedMatrixData );

    Matrix resultMatrix = m22.multiply( m23 );
    assertTrue( resultMatrix.equals( expectedMatrix ) );
  }

  @Test( expected = IllegalArgumentException.class )
  public void muliplyUnchainableMatrices() {
    Matrix resultMatrix = m23.multiply( m22 );
  }

  // Vector
  @Test
  public void lengthVector() {
    float[][] vData = {
      { 2, 2, 2, 2 }
    };
    Vector v = new Vector( vData );

    assertEquals( 4f, v.length(), 0 );
  }

  // QuadraticMatrix
  @Test( expected = IllegalArgumentException.class )
  public void cantCreateNonQuadraticMatrix() {
    float[][] data = {
      { 1, 2 },
      { 3, 4, 5 }
    };
    QuadraticMatrix m = new QuadraticMatrix( data );
  }

  // pow
  @Test
  public void powQuadraticMatix() {
    float[][] mData = {
      { 2, 2 },
      { 2, 2 }
    };
    QuadraticMatrix m = new QuadraticMatrix( mData );

    float[][] expectedData = {
      { 4, 4 },
      { 4, 4 }
    };
    QuadraticMatrix expectedMatrix = new QuadraticMatrix( expectedData );

    QuadraticMatrix result = m.pow( 2 );
    assertTrue( expectedMatrix.equals( result ) );
  }

  @Test
  public void pow0EqualsIdentityMatrix() {
    float[][] qmData = {
      { 2, 2 },
      { 2, 2 }
    };
    QuadraticMatrix m = new QuadraticMatrix( qmData );

    float[][] data = {
      { 1, 0 },
      { 0, 1 }
    };
    IdentityMatrix i = new IdentityMatrix( data );

    QuadraticMatrix result = m.pow( 0 );
    assertTrue( result.equals( i ) );
  }

  // IdentityMatrix
  @Test( expected = IllegalArgumentException.class )
  public void cantCreateNonIdentityMatrix() {
    float[][] data = {
      { 1, 0 },
      { 1, 0 }
    };
    IdentityMatrix m = new IdentityMatrix( data );
  }
}
