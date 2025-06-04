package it.uniroma3.diadia.ambienti;

public class StanzaBuia extends Stanza{

	private final static String ATTREZZO_LUMINOSO_DEFAULT = "lanterna";
	private String attrezzoLuminoso;

	public StanzaBuia(String nome) {
		this(nome, ATTREZZO_LUMINOSO_DEFAULT);
	}

	public StanzaBuia(String nome, String attrezzoLuminoso) {
		super(nome);
		this.attrezzoLuminoso = attrezzoLuminoso;
	}
	
	@Override
	public boolean isBuia() {
		return true;
	}
	
	@Override
	public String getDescrizione(){
		return this.toString();
	}
	
	@Override
	public int hashCode() {
		return super.hashCode() + attrezzoLuminoso.hashCode();
	}
	
	@Override
	public boolean equals(Object o) {
		if(!super.equals(o)) return false;
		if(o.getClass() != this.getClass()) return false;
		StanzaBuia that = (StanzaBuia) o;
		
		return this.attrezzoLuminoso.equals(that.attrezzoLuminoso);
	}
	
	@Override 
	public String toString() {
		if (!hasAttrezzo(this.attrezzoLuminoso)) {
			return "\nQui c'è buio pesto...";
		}
		return super.toString();
	}
}
