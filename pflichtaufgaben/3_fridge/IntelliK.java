import java.util.GregorianCalendar;

/* Feedback:
 * Ich fand die Aufgabenstellung sehr einschränkended und mühsam, man wiederholt sich ständig und es ist
 * einfach nur stumpfe Arbeit (busywork im englischen). Keine Kreativität oder geiste Anstrengung erforderlich,
 * einfach nur herunterschreiben.
 *
 * Und warum nutzen wir GregorianCalendar?
 * SO meint dazu: 
 *   The Calendar and GregorianCalendar classes are confusing, poorly-designed, and troublesome.
 *   Avoid these classes and the related old legacy date-time classes. Now supplanted by the java.time classes.
 * (https://stackoverflow.com/a/48634615)
 */

public class IntelliK implements Kuehlschrank {
  IntelliKMin[] min = null;
  Produkt[] products = null;

	/**
   * liefert die Einstellung min. Anzahl eines Produktes
   * @param produkt produkt zu dem mindestanzahl erwünscht
   * @return geforderte mindestanzahl des produktes
   */
	public int getMinAnzahl(String produkt) {
    if ( this.min == null ) {
      return 0; // unititialized min
    } else {
      for ( IntelliKMin m : this.min ) {
        if ( m.name == produkt ) {
          return m.anzahl;
        }
      }
      return 0; // product not found
    }
  }

	/**
   * setzt die Einstellung min. Anzahl eines Produktes auf n
   * @param produkt name des produktes
   * @param n mindestanzahl des produktes
   */
	public void setMinAnzahl(String produkt, int n) throws IllegalArgumentException {
    if ( n < 1 ) {
      throw new IllegalArgumentException();
    }

    IntelliKMin m = new IntelliKMin( produkt, n );
    if ( this.min == null ) {
      IntelliKMin[] arr = { m };
      this.min = arr;
    } else {
      IntelliKMin[] arr = new IntelliKMin[this.min.length +1];

      for ( int i = 0; i < this.min.length; i++ ) {
        arr[i] = this.min[i];
      }
      arr[arr.length -1] = m;
      this.min = arr;
    }
  }

  /**
   * add a product to the IntelliK
   * @param p Produkt to be added
   */
  public void addProduct( Produkt p ) {
    if ( this.products == null ) {
      Produkt[] arr = { p };
      this.products = arr;
    } else {
      Produkt[] arr = new Produkt[this.products.length +1];

      for ( int i = 0; i < this.products.length; i++ ) {
        arr[i] = this.products[i];
      }

      arr[arr.length -1] = p;
      this.products = arr;
    }
  }

	/**
   * liefert aktuelle Anzahl eines Produktes
   * @return anzahl des produktes
   */
	public int getAnzahl(String produkt) {
    int counter = 0;

    for ( Produkt p : this.products ) {
      if ( p.getName() == produkt ) {
        counter++;
      }
    }

    return counter;
  }

	/**
   * soll das Produkt gekauft werden
   * @param produkt produkt welches überprüft werden soll
   * @return true, wenn zu kaufen
   */
	public boolean zuKaufen(String produkt) {
    int min = this.getMinAnzahl( produkt );
    int current = this.getAnzahl( produkt );

    if ( min > current ) {
      return true;
    } else {
      return false;
    }
  }

	/**
   * soll das produkt an diesem zeitpunkt eingekauft werden
   * @param produkt produkt welches überprüft werden soll
	 * @param datum zeitpunkt für welchen geprüft wird
   * @return true, wenn an dem geg. zeitpunkt das produkt eingekauft werden soll
   */
	public boolean zuKaufen(String produkt, GregorianCalendar datum) {
    int min = this.getMinAnzahl( produkt );
    int current = 0;

    for ( Produkt p : this.products ) {
      if ( p.getName() == produkt && ! p.istAbgelaufen( datum ) ) {
        current++;
      }
    }

    if ( min > current ) {
      return true;
    } else {
      return false;
    }
  }

	/**
   * liefert Anzahl der Produkte zurueck, die an einem Datum ablaufen
	 * @param datum zeitpunkt für welchen geprüft wird
   * @return anzahl der abgelaufenen produkte
   */
	public int getAbgelaufenAnzahl(GregorianCalendar datum) {
    int counter = 0;

    for ( Produkt p : this.products ) {
      if ( p.istAbgelaufen( datum ) ) {
        counter++;
      }
    }

    return counter;
  }

	/**
   * liefert Anzahl der Produkte von einem Nutzer zurueck, die an einem Datum ablaufen
	 * @param nutzer nutzer für dessen essgewohnheiten beachtet werden sollen
	 * @param datum zeitpunkt für welchen geprüft wird
   * @return anzahl der abgelaufenen produkte
   */
	public int getAbgelaufenAnzahl(Nutzer nutzer, GregorianCalendar datum) {
    int counter = 0;

    for ( Produkt produkt : this.products ) {
      if ( produkt.istAbgelaufen( datum ) ) {
        for ( String essbaresProdukt : nutzer.getEssbar() ) {
          if ( essbaresProdukt == produkt.getName() ) {
            counter++;
          }
        }
      }
    }

    return counter;
  }


	/**
   * liefert Liste der Produkte zurueck, die an einem Datum ablaufen
	 * @param datum zeitpunkt für welchen geprüft wird
   * @return list der abgelaufenen Produkte
   */
	public Produkt[] getAbgelaufenListe(GregorianCalendar datum) {
    Produkt[] res = {};

    for ( Produkt p : this.products ) {
      if ( p.istAbgelaufen( datum ) ) {
        Produkt[] arr = new Produkt[res.length +1];

        for ( int i = 0; i < res.length; i++ ) {
          arr[i] = res[i];
        }

        arr[arr.length -1] = p;
        res = arr;
      }
    }

    return res;
  }

	/**
   * liefert Liste der Produkte von einem Nutzer zurueck, die an einem Datum ablaufen
	 * @param nutzer nutzer für dessen essgewohnheiten beachtet werden sollen
	 * @param datum zeitpunkt für welchen geprüft wird
   * @return liste der abgelaufenen produkte
   */
	public Lebensmittel[] getAbgelaufenListe(Nutzer nutzer, GregorianCalendar datum) {
    Produkt[] res = {};

    for ( Produkt p : this.products ) {
      if ( p.istAbgelaufen( datum ) ) {
        for ( String essbaresProdukt : nutzer.getEssbar() ) {
          if ( essbaresProdukt == p.getName() ) {
            Produkt[] arr = new Produkt[res.length +1];

            for ( int i = 0; i < res.length; i++ ) {
              arr[i] = res[i];
            }

            arr[arr.length -1] = p;
            res = arr;
          }
        }
      }
    }

    return res;
  }

	/**
   * generiert personalisierte Einkaufsliste fuer einen Nutzer anhand der aktuellen Vorratslage
	 * @param nutzer nutzer für dessen essgewohnheiten beachtet werden sollen
   * @return list von produkten die einzukaufen sind
   */
	public String[] generiereEinkaufsliste(Nutzer nutzer) {
    String[] res = {};

    for ( String p : nutzer.getEssbar() ) {
      // check if min is meet for users products
      if ( this.zuKaufen( p ) ) {
        int min = this.getMinAnzahl( p );
        int current = this.getAnzahl( p );
        int diff = min - current;

        String[] arr = new String[res.length + diff];

        for ( int i = 0; i < res.length; i++ ) {
          arr[i] = res[i];
        }

        for ( int i = 0; i < diff; i++ ) {
          arr[i] = p;
        }

        res = arr;
      }
    }

    return res;
  }

	/**
   * generiert personalisierte Einkaufsliste fuer einen Nutzer fuer ein Datum anhand der aktuellen Vorratslage unter Beruecksichtigung der bis dahin ablaufenden Produkte
	 * @param nutzer nutzer für dessen essgewohnheiten beachtet werden sollen
	 * @param morgen zeitpunkt für welchen geprüft wird
   * @return list von produkten die einzukaufen sind
	 */
	public String[] generiereEinkaufsliste(Nutzer nutzer, GregorianCalendar morgen) {
    String[] res = this.generiereEinkaufsliste( nutzer );

    // any expired products?
    if ( this.getAbgelaufenAnzahl( nutzer, morgen ) > 0 ) {
      Lebensmittel[] expired = this.getAbgelaufenListe( nutzer, morgen );

      for ( Lebensmittel pp : expired ) {
        String[] arr = new String[res.length +1];

        for ( int i = 0; i < res.length; i++ ) {
          arr[i] = res[i];
        }
        arr[arr.length -1] = pp.getName();
        res = arr;
      }
    }

    return res;
  }

	/**
   * gibt alle Eingeschaften (Inhalt und Einstellungen) des Kuhlschranks in einer lesbaren Form als String zurueck
   */
	public String toString() {
    String res = "IntelliK:\n";

    for ( Produkt p : this.products ) {
      res += p + "\n";
    }

    res += "Minimal Inhalt: ";
    for ( IntelliKMin m : this.min ) {
      res += m + " ";
    }
    res += "\n";
    return res;
  }
}
