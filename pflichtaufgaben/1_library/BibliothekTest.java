import org.junit.Test;
import static org.junit.Assert.*;

/*
 * No comments describing the tests needed, names are sufficient
 */

public class BibliothekTest {
  String[][] a1 = { { "Mandy", "Sult" } };
  String[][] a2 = { { "Ronald", "Dump" }, { "Mandy", "Sult" } };
  String[][] a3 = { { "Ronald", "Dump" } };
  String[][] a4 = { { "Anette", "Neumann" } };
  String[][] a5 = { { "Thomas", "Ulm" } };

  Buch[] books = {
    new Buch( "Theoretisches Testen gibt es nicht", a1, 2016 ),
    new Buch( "Java mal ganz anders", a2, 2010 ),
    new Buch( "Theoretisch gesagt, praktisch gemacht", a3, 2015 ),
    new Buch( "Grundlagen theoretischer Physik", a4, 2007 ),
    new Buch( "Grundlagen der Programmierung mit Java", a5, 2016 ),
  };

  Bibliotheksverzeichnis bibo = new Bibliotheksverzeichnis( books );

  // searchByLastname
  @Test
  public void searchByLastnameSingleMatch() {
    Buch[] expected = { books[4] };
    Buch[] result = bibo.searchByLastname( "Ulm" );

    assertEquals( result, expected );
  }

  @Test
  public void searchByLastnameMultipleMatches() {
    Buch[] expected = { books[0], books[1] };
    Buch[] result = bibo.searchByLastname( "Sult" );

    assertEquals( result, expected );
  }

  @Test
  public void searchByLastnameNoMatches() {
    Buch[] expected = {};
    Buch[] result = bibo.searchByLastname( "Nope" );

    assertEquals( result, expected );
  }

  @Test
  public void searchByLastnameDoesNotTakeRegex() {
    Buch[] expected = {};
    Buch[] result = bibo.searchByLastname( ".*" );

    assertEquals( result, expected );
  }

  @Test
  public void searchByLastnameDoesNotMatchCaseInsentive() {
    Buch[] expected = {};
    Buch[] result = bibo.searchByLastname( "sult" );

    assertEquals( result, expected );
  }

  // searchByQuery
  @Test
  public void searchByQuerySingleMatch() {
    Buch[] expected = { books[3] };
    Buch[] result = bibo.searchByQuery( "Physik" );

    assertEquals( expected, result );
  }

  @Test
  public void searchByQueryMultipleMatches() {
    Buch[] expected = { books[1], books[4] };
    Buch[] result = bibo.searchByQuery( "Java" );

    assertEquals( expected, result );
  }

  @Test
  public void searchByQueryMultipleCaseInsensitiveMatches() {
    Buch[] expected = { books[0], books[2], books[3] };
    Buch[] result = bibo.searchByQuery( "Theoretisch" );

    assertEquals( expected, result );
  }

  @Test
  public void searchByQueryNoMatches() {
    Buch[] expected = {};
    Buch[] result = bibo.searchByQuery( "JavaScript" );

    assertEquals( expected, result );
  }

  @Test
  public void searchByQueryDoesNotTakeRegex() {
    Buch[] expected = {};
    Buch[] result = bibo.searchByQuery( ".*" );

    assertEquals( expected, result );
  }
}
