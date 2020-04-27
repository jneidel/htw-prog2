public class Buch {
  private String title;
  private String[][] author; // Nested array consists of: firstname, lastname
  private int year;

  public void setTitle( String t ) {
    this.title = t;
  }
  public String getTitle() {
    return this.title;
  }

  public void setAuthor( String[][] a ) {
    this.author = a;
  }
  public String[][] getAuthor() {
    return this.author;
  }
  public String printAuthor() {
    String res = "";

    for ( int i = 0; i < author.length; i++ ) {
      if ( i != 0 ) {
        res = res + ", ";
      }
      String[] a = author[i];
      res = res + a[0] + " " + a[1];
    }

    return res;
  }

  public void setYear( int y ) {
    this.year = y;
  }
  public int getYear() {
    return this.year;
  }

  public Buch( String title, String[][] author, int year ) {
    this.setTitle( title );
    this.setAuthor( author ); // Create as: { { "first", "last" }, ... }
    this.setYear( year );
  }

  public String toString() {
    return this.printAuthor() + ": " + this.getTitle() + ", " + this.getYear();
  }
}
