import java.util.LinkedList;

public class Bibliotheksverzeichnis {
  private Buch[] books;

  public void setBooks( Buch[] b ) {
    this.books = b;
  }
  public Buch[] getBooks() {
    return this.books;
  }

  public Bibliotheksverzeichnis( Buch[] b ) {
    this.setBooks( b );
  }

  public String toString() {
    String res = "Bücherliste:\n";

    for ( Buch b : this.books ) {
      res = res + b + "\n";
    }

    return res;
  }

  // Utility for search funcs
  private Buch[] bookLLToArr( LinkedList<Buch> list ) {
    Buch[] books = new Buch[list.size()];

    for ( int i = 0; i < list.size(); i++ ) {
      books[i] = list.get(i);
    }

    return books;
  }

  public Buch[] searchByLastname( String name ) {
    // Temp LinkedList as I won't know how many matches I'll get in advance
    LinkedList<Buch> list = new LinkedList<Buch>();

    for ( Buch b : this.books ) {
      for ( String[] a : b.getAuthor() ) {
        if ( a[1] == name ) {
          list.add( b );
        }
      }
    }

    Buch[] books = bookLLToArr( list );
    return books;
  }
  public Buch[] searchByQuery( String query ) {
    LinkedList<Buch> list = new LinkedList<Buch>();

    for ( Buch b : this.books ) {
      String title = b.getTitle();

      // Case insenstive match: (?i)
      String regex = "(?i)(.*)" + query + "(.*)";
      if ( title.matches( regex ) ) {
        list.add( b );
      }
    }

    Buch[] books = bookLLToArr( list );
    return books;
  }
}
