public class Person implements Nutzer {
  private String name;
  private String[] produkte;

  public Person( String name, String[] products ) {
    this.name = name;
    this.produkte = products;
  }

	/** gibt eine Liste der essbaren Produkte des Nutzers zurueck
	 * @return Liste der essbaren Produkte
	 */
	public String[] getEssbar() {
    return this.produkte;
  }

	/**
	 * beantwortet die Frage, ob ein Produkt vom Nutzer essbar ist, oder nicht
	 * @param produkt Produkt, von welchem erfragt wird, ob es essbar ist
	 * @return true, wenn produkt essbar ist
	 */
	public boolean istEssbar(String produkt) {
    for ( String p : this.produkte ) {
      if ( p == produkt ) {
        return true;
      }
    }
    return false;
  }

	/**
	 * empfaengt eine Meldung
	 * @param meldung Meldung, die empfangen wird
	 */
	public void empfangeMeldung(String meldung) {
    System.out.printf( "ok" );
  }

	/**
	 * gibt alle Eigenschaften des Nutzers in einer lesbaren Form als String zurueck
	 * @return textuelle Repraesentation des Objektes
	 */
	public String toString() {
    String out = "Person: " + this.name + ", essbar:";

    for ( String p : this.produkte ) {
      out += " " + p;
    }

    return out;
  }
}
