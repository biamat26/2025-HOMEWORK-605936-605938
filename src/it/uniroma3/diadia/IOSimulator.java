package it.uniroma3.diadia;

public class IOSimulator implements IO {

    private String[] input;
    private int contatoreInput;
    private String[] output;
    private int contatoreOutput ;


    public IOSimulator(String[] input) {
        this.input = input;
        this.contatoreInput = 0;
        this.output = new String[input.length + 2];
        this.contatoreOutput = 0;
    }

    @Override
    public void mostraMessaggio(String messaggio) {
        if(contatoreOutput > output.length) 
            return;
        output[contatoreOutput++] = messaggio;
    }

    @Override
    public String leggiRiga() {
        if(contatoreInput < input.length)
            return input[contatoreInput++];
        return "";
    }

    public String[] getOutput() {
        return output;
    }
}