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
  private Buch[] extendArrayByOne( Buch[] bookArr, Buch bookToAdd ) {
    Buch[] newBookArr = new Buch[bookArr.length +1];

    for ( int i = 0; i < bookArr.length; i++ ) {
      newBookArr[i] = bookArr[i];
    }
    newBookArr[newBookArr.length -1] = bookToAdd;

    return newBookArr;
  }

  public Buch[] searchByLastname( String name ) {
    Buch[] books = new Buch[0];

    for ( Buch b : this.books ) {
      for ( String[] a : b.getAuthor() ) {
        if ( a[1] == name ) {
          books = extendArrayByOne( books, b );
        }
      }
    }

    return books;
  }
  public Buch[] searchByQuery( String query ) {
    Buch[] books = new Buch[0];

    for ( Buch b : this.books ) {
      String title = b.getTitle();

      // Case insenstive match: (?i)
      // Fixed string match (https://stackoverflow.com/a/60161): \Qstring\E
      String regex = "(?i)(.*)\\Q" + query + "\\E(.*)";
      if ( title.matches( regex ) ) {
        books = extendArrayByOne( books, b );
      }
    }

    return books;
  }
}
