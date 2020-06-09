import java.util.GregorianCalendar;
import java.util.Calendar;

public class Main {
  public static void main( String[] args ) {
    GregorianCalendar may1 = new GregorianCalendar(2020, Calendar.MAY, 1);
    GregorianCalendar may3 = new GregorianCalendar(2020, Calendar.MAY, 3);
    GregorianCalendar may5 = new GregorianCalendar(2020, Calendar.MAY, 5);
    GregorianCalendar may7 = new GregorianCalendar(2020, Calendar.MAY, 7);

    IntelliK k = new IntelliK();

    String[] marieEssbar = { "Milch", "Eier" };
    Person marie = new Person( "Marie", marieEssbar );

    String[] timEssbar = { "Schinken", "Eier" };
    Person tim = new Person( "Marie", timEssbar );

    k.setMinAnzahl( "Eier", 5 );
    k.setMinAnzahl( "Milch", 2 );
    k.setMinAnzahl( "Schinken", 1 );

    Produkt e1 = new Produkt( "Eier", may3 );
    Produkt e2 = new Produkt( "Eier", may5 );
    Produkt m1 = new Produkt( "Milch", may3 );
    Produkt m2 = new Produkt( "Milch", may5 );
    Produkt h1 = new Produkt( "Schinken", may3 );

    k.addProduct( e1 );
    k.addProduct( e1 );
    k.addProduct( e2 );
    k.addProduct( e2 );

    k.addProduct( m1 );
    k.addProduct( m2 );

    k.addProduct( h1 );

    System.out.println( k );

    System.out.printf( "Aktuelle anzahl der eier %d\n\n", k.getAnzahl( "Eier" ) );

    System.out.printf( "Sollen Eier gekauft werden: %s\n\n", k.zuKaufen( "Eier" ) );

    System.out.printf( "Sollen Eier am 3.5. gekauft werden: %s\n\n", k.zuKaufen( "Eier", may3 ) );

    System.out.printf( "Tim frage, wie viele seiner Produkte am 3.5. ablaufen: %d \n\n", k.getAbgelaufenAnzahl( tim, may3 ) );
    System.out.printf( "Marie frage, welche ihrer produkte am 3.5 ablaufen:\n" );
    for ( Lebensmittel p : k.getAbgelaufenListe( marie, may3 ) ) {
      System.out.print( p + "\n" );
    }
    System.out.println( );
    System.out.printf( "Tim möchte seine Einkaufsliste (für jetzt) erhalten:\n" );
    for ( String s :  k.generiereEinkaufsliste( tim ) ) {
      System.out.print( s + "\n" );
    }
    System.out.println();
    System.out.printf( "Marie möchte ihre einkaufsliste für den 3.5 erhalten:\n" );
    for ( String s :  k.generiereEinkaufsliste( marie, may3 ) ) {
      System.out.print( s + "\n" );
    }
    System.out.println();
  }
}
