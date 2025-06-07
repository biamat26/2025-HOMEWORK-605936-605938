package it.uniroma3.diadia;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Configurazione {

    private static final String CONFIG_FILE = "diadia.properties";
    private static final HashMap<String, String> valori = new HashMap<>();
     
    static {
        try (BufferedReader br = new BufferedReader(new FileReader(CONFIG_FILE))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                if (linea.isEmpty() || linea.startsWith("#")) continue; // commenti o righe vuote
                String[] parti = linea.split("=");
                if (parti.length == 2)
                    valori.put(parti[0].trim(), parti[1].trim());
            }
        } catch (IOException e) {
            System.err.println("Errore nella lettura di " + CONFIG_FILE + ": " + e.getMessage());
            System.exit(1); // terminare se manca configurazione
        }
    }

    public static int getCfuIniziali() {
        return Integer.parseInt(valori.getOrDefault("cfu.iniziali", "20"));
    }

    public static int getPesoMaxBorsaDefault() {
        return Integer.parseInt(valori.getOrDefault("borsa.pesoMax", "10"));
    }
    
    public static int getNumeroMaxAttrezziBorsaDefault() {
    	return Integer.parseInt(valori.getOrDefault("stanza.numeroMaxBorsa", "10"));	
    }
    
    public static int getSogliaMagicaDefault() {
    	return Integer.parseInt(valori.getOrDefault("stanzaMagica.soglia", "3"));
    }
    
    public static int getNumeroMaxAttrezziStanzaDefault() {
    	return Integer.parseInt(valori.getOrDefault("stanza.numeroMaxAttrezzi", "10"));	
    }
    
    public static String getMessaggioDonoMagoDefault() {
    	return valori.getOrDefault("mago.messaggioDono", "Sei un vero simpaticone, " +
			"con una mia magica azione, troverai un nuovo oggetto " +
			"per il tuo borsone!");
    }
    
    public static String getMessaggioScuseMagoDefault() {
    	return valori.getOrDefault("mago.messaggioScuse", "Mi spiace, ma non ho piu' nulla...");
    }
}
