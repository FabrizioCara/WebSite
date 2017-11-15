/* 
    Document   : Oggetti
    Created on : Maggio 2016
    Author     : Fabrizio Cara
*/
package PackageTechommerce;

public class Oggetti {
    int id;
    private double prezzo;
    private String nomeOggetto;
    private int pezziDisponibili;
    private String descrizione;
    private String url;
    private int idVenditore;
    
    //Costruttore
    public Oggetti(){
    id = 0;
    prezzo = 0;
    nomeOggetto ="";
    pezziDisponibili = 0;
    url = "";
    descrizione = "";
    idVenditore = 0;
    }

    public int getIdVenditore() {
        return idVenditore;
    }

    public void setIdVenditore(int idVenditore) {
        this.idVenditore = idVenditore;
    }

    //@return prezzo
    public double getPrezzo() {
        return prezzo;
    }
    
    //@param prezzo, prezzo to set
    public void setPrezzo(double prezzo) 
    throws IllegalArgumentException{
        this.prezzo = prezzo;
    }
    
    //@return nomeOggetto
    public String getNomeOggetto() {
        return nomeOggetto;
    }

    //@param nomeOggetto, nomeOggetto to set
    public void setNomeOggetto(String nomeOggetto) {
        this.nomeOggetto = nomeOggetto;
    }
    
    //@return descrizione
    public String getDescrizione() {
        return descrizione;
    }

    //@param descrizione, descrizione to set
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    //return id
    public int getId() {
        return id;
    }

    //@param id, id to set
    public void setId(int id) {
        this.id = id;
    }
    

    //return pezziDisponibili
    public int getPezziDisponibili() {
        return pezziDisponibili;
    }

    //@param pezziDisponibili, pezziDisponibili to set
    public void setPezziDisponibili(int pezziDisponibili) 
    throws IllegalArgumentException{
        this.pezziDisponibili = pezziDisponibili;
    }
    
    //@return url
    public String getUrl() {
        return url;
    }

    //@param url, url to set
    public void setUrl(String url) {
        this.url = url;
    }
}
