/* 
    Document   : Utente
    Created on : Maggio 2016
    Author     : Fabrizio Cara
*/
package PackageTechommerce;

public abstract class Utente {
    protected int id;
    protected String nomeUtente;
    protected String cognomeUtente;
    protected String username;
    protected String password;
    protected SaldoUtenti credito;

    //Costruttore
    public Utente ()
    {
        id= 0;
        nomeUtente = "";
        cognomeUtente = "";
        password = "";
        username="";
    }


    public SaldoUtenti getCredito() {
        return credito;
    } 
    
    public void setCredito(SaldoUtenti credito){
        this.credito = credito;
    }
            
    //return id
    public int getId() {
        return id;
    }

    //@param id, id to set
    public void setId(int id) {
        this.id = id;
    }   
    
    //@return nomeUtente
    public String getNomeUtente() {
        return nomeUtente;
    }
    
    //@param nomeUtente, nomeUtente to set
    public void setNomeUtente(String nomeUtente) {
        this.nomeUtente = nomeUtente;
    }

    //@return cognomeUtente
    public String getCognomeUtente() {
        return cognomeUtente;
    }

    //@param cognomeUtente, cognomeUtente to set
    public void setCognomeUtente(String cognomeUtente) {
        this.cognomeUtente = cognomeUtente;
    }
    
    //@return the username
    public String getUsername() {
        return username;
    }

    //@param username, username to set
    public void setUsername(String username) {
        this.username = username;
    }
    
    //@return password
    public String getPassword() {
        return password;
    }

    //@param password, password to set
    public void setPassword(String password) {
        this.password = password;
    }
  
}
