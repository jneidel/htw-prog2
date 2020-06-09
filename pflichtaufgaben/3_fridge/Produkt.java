import java.util.GregorianCalendar;
import java.util.Calendar;

public class Produkt implements Lebensmittel {
  private String name;
  private GregorianCalendar verfallsDatum;

  public Produkt( String name, GregorianCalendar date ) {
    this.name = name;
    this.verfallsDatum = date;
  }

	/**
   * gibt den Namen zurueck
   * @return names des Produktes
   */
	public String getName() {
    return this.name;
  }

	/**
   * gibt das Verfallsdatum zurueck
   * @return verfallsdatum
   */
	public GregorianCalendar getVerfallsDatum() {
    return this.verfallsDatum;
  }

	/**
   * liefert true zurueck, wenn das Lebensmittel am angegebenen Datum abgelaufen ist
   * @param datum datum an welchem abgelaufenheit geprüft wird
   * @return true, wenn am geg. datum abgelaufen
   */
	public boolean istAbgelaufen(GregorianCalendar datum) {
    if ( this.verfallsDatum.compareTo( datum ) <= 0 ) {
      return true;
    } else {
      return false;
    }
  }

	/**
   * gibt alle Eigenschaften eines Lebensmittels in einer lesbaren Form als String zurueck
   * @return lesbare Form
   */
	public String toString() {
    int month = this.verfallsDatum.get(Calendar.MONTH) + 1;
    int day = this.verfallsDatum.get(Calendar.DAY_OF_MONTH);
    int year = this.verfallsDatum.get(Calendar.YEAR);
    
    return "Produkt: " + this.name + ", verfällt: " + day + "." + month + "." + year;
  }
}
