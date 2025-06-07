package it.uniroma3.diadia.comandi;

import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class FabbricaDiComandiRiflessiva implements FabbricaDiComandi{

	@Override
	public Comando costruisciComando(String istruzione) {
		
		Scanner scannerDiParole = new Scanner(istruzione);
		
		String nomeComando = null;
		String parametro = null;
		
		Comando comando = null;
		
		if (scannerDiParole.hasNext()) {
			nomeComando = scannerDiParole.next();// prima parola: nome del comando
		}
		
		if (scannerDiParole.hasNext()) {
			parametro = scannerDiParole.next(); // seconda parola: eventuale param.
		}
		
		StringBuilder nomeTipo = new StringBuilder("it.uniroma3.diadia.comandi.Comando");
		nomeTipo.append(nomeComando.toUpperCase().charAt(0));
		nomeTipo.append(nomeComando.substring(1));
		
		try {
			@SuppressWarnings("unchecked")
			Class<? extends Comando> tipo = (Class<? extends Comando>) Class.forName(nomeTipo.toString());
			comando = tipo.getDeclaredConstructor().newInstance();		
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException | SecurityException e) {
			return new ComandoNonValido();
		} 
		
		comando.setParametro(parametro);
		
		return comando;
	}

}
