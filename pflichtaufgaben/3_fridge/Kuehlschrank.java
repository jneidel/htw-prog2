import java.util.GregorianCalendar;


public interface Kuehlschrank {
	
	//liefert die Einstellung min. Anzahl eines Produktes
	public abstract int getMinAnzahl(String produkt);	
	
	//setzt die Einstellung min. Anzhal eines Produktes auf n
	public abstract void setMinAnzahl(String produkt, int n) throws IllegalArgumentException;
	
	
	//liefert aktuelle Anzahl eines Produktes
	public abstract int getAnzahl(String produkt);
	
	
	//liefert true zurueck, wenn Produkt gekauft werden sollte, false sonst
	public abstract boolean zuKaufen(String produkt);
	
	//liefert true zurueck, wenn produkt an einem Datum gekauft werden sollte, 
	//weil es jetzt schon fehlt, oder vor diesem Datum ablaeuft, ansonsten false
	public abstract boolean zuKaufen(String produkt, GregorianCalendar datum);
	
	
	//liefert Anzahl der Produkte zurueck, die an einem Datum ablaufen
	public abstract int getAbgelaufenAnzahl (GregorianCalendar datum);
	
	//liefert Anzahl der Produkte von einem Nutzer zurueck, die an einem Datum ablaufen
	public abstract int getAbgelaufenAnzahl (Nutzer nutzer, GregorianCalendar heute);
	
	
	//liefert Liste der Produkte zurueck, die an einem Datum ablaufen
	public abstract Lebensmittel[] getAbgelaufenListe(GregorianCalendar datum);
	
	//liefert Liste der Produkte von einem Nutzer zurueck, die an einem Datum ablaufen
	public abstract Lebensmittel[] getAbgelaufenListe(Nutzer nutzer, GregorianCalendar datum);

	
	//generiert personalisierte Einkaufsliste fuer einen Nutzer anhand der aktuellen Vorratslage
	public abstract String[] generiereEinkaufsliste(Nutzer nutzer);
	
	//generiert personalisierte Einkaufsliste fuer einen Nutzer fuer ein Datum anhand der aktuellen Vorratslage
	//unter Beruecksichtigung der bis dahin ablaufenden Produkte
	public abstract String[] generiereEinkaufsliste(Nutzer nutzer, GregorianCalendar morgen);
	
	
	//gibt alle Eingeschaften (Inhalt und Einstellungen) des Kuhlschranks in einer lesbaren Form als String zurueck
	public abstract String toString();

}
