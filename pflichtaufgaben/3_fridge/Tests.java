import org.junit.Test;
import static org.junit.Assert.*;
import java.util.GregorianCalendar;
import java.util.Calendar;

/*
 * Der erste Test pro Methode ist immer der Normallfall,
 * alle anderen Grenzfälle
 */

  //@Test( expected = IllegalArgumentException.class )

public class Tests {
  // Lebensmittel
  GregorianCalendar may1 = new GregorianCalendar(2020, Calendar.MAY, 1);
  GregorianCalendar may3 = new GregorianCalendar(2020, Calendar.MAY, 3);
  GregorianCalendar may5 = new GregorianCalendar(2020, Calendar.MAY, 5);
  GregorianCalendar may7 = new GregorianCalendar(2020, Calendar.MAY, 7);

  Produkt e1 = new Produkt( "Eier", may3 );
  Produkt e2 = new Produkt( "Eier", may5 );
  Produkt m1 = new Produkt( "Milch", may3 );
  Produkt m2 = new Produkt( "Milch", may5 );

  @Test public void istAbgelaufen() {
    assertFalse( e1.istAbgelaufen( may1 ) );
    assertTrue( e1.istAbgelaufen( may3 ) );
    assertTrue( e1.istAbgelaufen( may5 ) );
  }

  // Nutzer
  String[] marieEssbar = { "Milch", "Eier" };
  Person marie = new Person( "Marie", marieEssbar );

  String[] timEssbar = { "Schinken", "Eier" };
  Person tim = new Person( "Marie", timEssbar );

  @Test public void istEssbar() {
    assertTrue( marie.istEssbar( marieEssbar[0] ) );
    assertTrue( marie.istEssbar( marieEssbar[1] ) );

    assertTrue( tim.istEssbar( timEssbar[0] ) );
    assertTrue( tim.istEssbar( timEssbar[1] ) );

    assertFalse( marie.istEssbar( timEssbar[0] ) );
    assertFalse( tim.istEssbar( marieEssbar[0] ) );
  }

  // IntelliK
  @Test public void getSetMinAnzahl() {
    IntelliK k = new IntelliK();

    k.setMinAnzahl( "Eier", 5 );
    k.setMinAnzahl( "Milch", 2 );
    k.setMinAnzahl( "Schinken", 1 );

    assertEquals( k.getMinAnzahl( "Eier" ), 5 );
    assertEquals( k.getMinAnzahl( "Milch" ), 2 );
    assertEquals( k.getMinAnzahl( "Schinken" ), 1 );
  }

  @Test public void zuKaufen() {
    IntelliK k = new IntelliK();

    k.setMinAnzahl( "Eier", 5 );

    k.addProduct( e1 );
    k.addProduct( e1 );
    k.addProduct( e1 );

    assertTrue( k.zuKaufen( "Eier" ) );

    k.addProduct( e1 );
    k.addProduct( e1 );

    assertFalse( k.zuKaufen( "Eier" ) );
  }

  @Test public void zuKaufenZumDatum() {
    IntelliK k = new IntelliK();

    k.setMinAnzahl( "Eier", 5 );

    k.addProduct( e1 );
    k.addProduct( e1 );
    k.addProduct( e2 );
    k.addProduct( e2 );
    k.addProduct( e2 );

    assertFalse( k.zuKaufen( "Eier", may1 ) );
    assertTrue( k.zuKaufen( "Eier", may5 ) );
  }

  @Test public void getAbgelaufenAnzahl() {
    IntelliK k = new IntelliK();

    k.setMinAnzahl( "Eier", 5 );

    k.addProduct( e1 );
    k.addProduct( e1 );
    k.addProduct( e2 );
    k.addProduct( e2 );
    k.addProduct( e2 );

    assertEquals( k.getAbgelaufenAnzahl( tim, may1 ), 0 );
    assertEquals( k.getAbgelaufenAnzahl( tim, may3 ), 2 );
    assertEquals( k.getAbgelaufenAnzahl( tim, may5 ), 5 );
    assertEquals( k.getAbgelaufenAnzahl( tim, may7 ), 5 );
  }

  @Test public void getAbgelaufenAnzahlNutzer() {
    IntelliK k = new IntelliK();

    k.setMinAnzahl( "Eier", 2 );
    k.setMinAnzahl( "Milch", 2 );

    k.addProduct( e1 );
    k.addProduct( e2 );
    k.addProduct( m1 );
    k.addProduct( m2 );

    assertEquals( k.getAbgelaufenAnzahl( tim, may1 ), 0 );
    assertEquals( k.getAbgelaufenAnzahl( tim, may3 ), 1 );
    assertEquals( k.getAbgelaufenAnzahl( tim, may5 ), 2 );

    assertEquals( k.getAbgelaufenAnzahl( marie, may1 ), 0 );
    assertEquals( k.getAbgelaufenAnzahl( marie, may3 ), 2 );
    assertEquals( k.getAbgelaufenAnzahl( marie, may5 ), 4 );
  }

  @Test public void getAbgelaufenListe() {
    IntelliK k = new IntelliK();

    k.setMinAnzahl( "Eier", 2 );

    k.addProduct( e1 );
    k.addProduct( e2 );

    Produkt[] empty = {};
    Produkt[] eggs1 = { e1 };
    Produkt[] eggs2 = { e1, e2 };

    assertEquals( k.getAbgelaufenListe( may1 ), empty );
    assertEquals( k.getAbgelaufenListe( may3 ), eggs1 );
    assertEquals( k.getAbgelaufenListe( may5 ), eggs2 );
  }

  @Test public void getAbgelaufenListeNutzer() {
    IntelliK k = new IntelliK();

    k.setMinAnzahl( "Eier", 1 );
    k.setMinAnzahl( "Milch", 1 );

    k.addProduct( e1 );
    k.addProduct( m1 );

    Produkt[] empty = {};
    Produkt[] le1 = { e1 };
    Produkt[] le1m1 = { e1, m1 };

    assertEquals( k.getAbgelaufenListe( tim, may1 ), empty );
    assertEquals( k.getAbgelaufenListe( marie, may1 ), empty );

    assertEquals( k.getAbgelaufenListe( tim, may3 ), le1 );
    assertEquals( k.getAbgelaufenListe( marie, may3 ), le1m1 );

    assertEquals( k.getAbgelaufenListe( tim, may5 ), le1 );
    assertEquals( k.getAbgelaufenListe( marie, may5 ), le1m1 );
  }

  @Test public void generiereEinkaufsliste() {
    IntelliK k = new IntelliK();

    k.setMinAnzahl( "Eier", 3 );
    k.setMinAnzahl( "Milch", 2 );

    k.addProduct( e1 );
    k.addProduct( e2 );
    k.addProduct( e2 );
    k.addProduct( m1 );

    String[] empty = {};
    String[] lm1 = { "Milch" };

    assertEquals( k.generiereEinkaufsliste( tim ), empty );
    assertEquals( k.generiereEinkaufsliste( marie ), lm1 );
  }

  @Test public void generiereEinkaufslisteMitDatum() {
    IntelliK k = new IntelliK();

    k.setMinAnzahl( "Eier", 3 );
    k.setMinAnzahl( "Milch", 2 );

    String[] empty = {};
    String[] lm1 = { "Milch" };
    String[] le1 = { "Eier" };
    String[] le1e1 = { "Eier", "Eier" };
    String[] lm1e1m1 = { "Milch", "Eier", "Milch" };

    k.addProduct( e1 );

    assertEquals( k.generiereEinkaufsliste( tim, may1 ), le1e1 );

    k.addProduct( e2 );
    k.addProduct( e2 );
    k.addProduct( m1 );

    assertEquals( k.generiereEinkaufsliste( tim, may1 ), empty );
    assertEquals( k.generiereEinkaufsliste( marie, may1 ), lm1 );
    assertEquals( k.generiereEinkaufsliste( tim, may3 ), le1 );
    assertEquals( k.generiereEinkaufsliste( marie, may3 ), lm1e1m1 );
  }
}
