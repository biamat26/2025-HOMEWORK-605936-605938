package it.uniroma3.diadia;
import java.util.Scanner;

public class IOConsole implements IO{
	
/*
	private static IOConsole ioConsole;
	
	
	private IOConsole() {
		
	}
	
	public static IOConsole getInstance() {
		if(ioConsole == null) {
			ioConsole = new IOConsole();
		}
		return ioConsole;
	}
*/
	
	@Override
	public void mostraMessaggio(String msg) {
		System.out.println(msg);
	}
	
	@Override
	public String leggiRiga() {
		Scanner scannerDiLinee = new Scanner(System.in);
		String riga = scannerDiLinee.nextLine();
		return riga;
	}
}