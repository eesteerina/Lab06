package it.polito.tdp.meteo.model;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import it.polito.tdp.meteo.DAO.MeteoDAO;

public class Model {
	
	private final static int COST = 100;
	private final static int NUMERO_GIORNI_CITTA_CONSECUTIVI_MIN = 3;
	private final static int NUMERO_GIORNI_CITTA_MAX = 6;
	private final static int NUMERO_GIORNI_TOTALI = 15;
	
	private MeteoDAO meteoDAO;

	public Model() {
		this.meteoDAO = new MeteoDAO();
	}

	// of course you can change the String output with what you think works best
	public Map<String, Double> getUmiditaMedia(int mese) {
		
		List<String> allLocalita = this.meteoDAO.getAllCitta();
		Map<String, Double> ritorno = new TreeMap<String, Double>();
		
		for(String loc : allLocalita) {
			Citta c = new Citta(loc, this.meteoDAO.getAllRilevamentiLocalitaMese(mese, loc));
			ritorno.put(loc, c.getMediaUmiditaLocalita());
		}
		
		return ritorno;
	}
	
	// of course you can change the String output with what you think works best
	public List<Rilevamento> trovaSequenza(int mese) {
		
		List<Rilevamento> tutti = this.meteoDAO.getAllRilevamentiPrimaMetaMese(mese);
		Ricorsione ricors = new Ricorsione();
		ricors.trovaPercorso(tutti); 
		return ricors.getBestPath();
	}
	

}
