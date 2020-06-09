/*
 * Helper class for IntelliK for managing Mindestanzahl of products dynamically
 */
public class IntelliKMin {
  public String name;
  public int anzahl;

  public IntelliKMin( String produkt, int n ) {
    this.name = produkt;
    this.anzahl = n;
  }

  public String toString() {
    return this.name + ": " + this.anzahl;
  }
}
