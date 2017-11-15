/* 
    Document   : Utenti_Factory
    Created on : Maggio 2016
    Author     : Fabrizio Cara
*/
package PackageTechommerce;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Utenti_Factory {
    //Attributi
    private static Utenti_Factory singleton;
    String connectionString;
        
    public void setConnectionString(String s){
	this.connectionString = s;
    }
    
    public String getConnectionString(){
	return this.connectionString;
    } 
       
    public static Utenti_Factory getInstance() {
        if (singleton == null) {
            singleton = new Utenti_Factory();
        }
        return singleton;
    }
    
    //Costruttore
    private Utenti_Factory() {}
  
    //Metodi
    public Utente getUtenti_Clienti(int id){
        try {
            Connection conn = DriverManager.getConnection(connectionString, "fabrizio", "1234");
            
            String query = "select * from Cliente where id = " + id;
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery(query);
            
            if(res.next()){
                Utenti_Clienti cliente = new Utenti_Clienti();
                cliente.setId(res.getInt("id"));
                cliente.setNomeUtente(res.getString("nome"));
                cliente.setCognomeUtente(res.getString("cognome"));
                cliente.setUsername(res.getString("username"));
                cliente.setPassword(res.getString("password"));
                cliente.setCredito(new SaldoUtenti(res.getDouble("credito")));
                
                stmt.close();
                conn.close();
                return cliente;
            }
            stmt.close();
            conn.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    
    public Utente getUtenti_Clienti(Utente utente){
        try {
            Connection conn = DriverManager.getConnection(connectionString, "fabrizio", "1234");
            
            String query = "select * from Cliente where id = " + utente.getId();
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery(query);
            
            if(res.next()){
                Utenti_Clienti cliente = new Utenti_Clienti();
                cliente.setId(res.getInt("id"));
                cliente.setNomeUtente(res.getString("nome"));
                cliente.setCognomeUtente(res.getString("cognome"));
                cliente.setUsername(res.getString("username"));
                cliente.setPassword(res.getString("password"));
                cliente.setCredito(new SaldoUtenti(res.getDouble("credito")));
                
                stmt.close();
                conn.close();
                return cliente;
            }
            stmt.close();
            conn.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
    public Utente getUtenti_Venditori(Utente utente){
        try {
            Connection conn = DriverManager.getConnection(connectionString, "fabrizio", "1234");
            
            String query = "select * from Venditore where id = " + utente.getId();
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery(query);
            
            if(res.next()){
                Utenti_Venditori venditore = new Utenti_Venditori();
                venditore.setId(res.getInt("id"));
                venditore.setNomeUtente(res.getString("nome"));
                venditore.setCognomeUtente(res.getString("cognome"));
                venditore.setUsername(res.getString("username"));
                venditore.setPassword(res.getString("password"));
                venditore.setCredito(new SaldoUtenti(res.getDouble("credito")));
                
                stmt.close();
                conn.close();
                return venditore;
            }
            stmt.close();
            conn.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
    public Utente getUtente(String username, String password){
        try {
            try (Connection conn = DriverManager.getConnection(connectionString, "fabrizio", "1234")) {
                String query = "select * from Venditore where username = ? and password = ? ";
                
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, username);
                stmt.setString(2, password);
                
                ResultSet res = stmt.executeQuery();
                
                if(res.next()){
                    Utenti_Venditori venditore = new Utenti_Venditori();
                    venditore.setId(res.getInt("id"));
                    venditore.setNomeUtente(res.getString("nome"));
                    venditore.setCognomeUtente(res.getString("cognome"));
                    venditore.setUsername(res.getString("username"));
                    venditore.setPassword(res.getString("password"));
                    venditore.setCredito(new SaldoUtenti(res.getDouble("credito")));
                    
                    stmt.close();
                    conn.close();
                    return venditore;
                }
                
                query = "select * from Cliente where username = ? and password = ? ";
                stmt = conn.prepareStatement(query);
                stmt.setString(1, username);
                stmt.setString(2, password);
                res = stmt.executeQuery();
                
                if(res.next()){
                    Utenti_Clienti cliente = new Utenti_Clienti();
                    cliente.setId(res.getInt("id"));
                    cliente.setNomeUtente(res.getString("nome"));
                    cliente.setCognomeUtente(res.getString("cognome"));
                    cliente.setUsername(res.getString("username"));
                    cliente.setPassword(res.getString("password"));
                    cliente.setCredito(new SaldoUtenti(res.getDouble("credito")));
                    
                    stmt.close();
                    conn.close();
                    return cliente;
                }
                
                stmt.close();
                conn.close();
            }
        } 
        
        catch (Exception e) {
        }
        
        return null;
    }
    
    
    public Oggetti getOggetto(int id){
        try {       
        Connection conn = DriverManager.getConnection(connectionString, "fabrizio", "1234");
        String query = "select * from Oggetti where id = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, id);
        ResultSet res = stmt.executeQuery();
        
        if(res.next()){
            Oggetti oggetto = new Oggetti();
            oggetto.setId(res.getInt("id"));
            oggetto.setPrezzo(res.getDouble("prezzo"));
            oggetto.setNomeOggetto(res.getString("nome"));
            oggetto.setPezziDisponibili(res.getInt("pezzi"));
            oggetto.setDescrizione(res.getString("descrizione"));
            oggetto.setUrl(res.getString("url"));
            oggetto.setIdVenditore(res.getInt("idVenditore"));
            
            stmt.close();
            conn.close();
            return oggetto;
        }
        
        stmt.close();
        conn.close();
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;       
    }
    
    
    public Oggetti getOggetto(Oggetti obj){
        try {       
        Connection conn = DriverManager.getConnection(connectionString, "fabrizio", "1234");
        String query = "select * from Oggetti where id = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, obj.id);
        ResultSet res = stmt.executeQuery();
        
        if(res.next()){
            Oggetti oggetto = new Oggetti();
            oggetto.setId(res.getInt("id"));
            oggetto.setPrezzo(res.getDouble("prezzo"));
            oggetto.setNomeOggetto(res.getString("nome"));
            oggetto.setPezziDisponibili(res.getInt("pezzi"));
            oggetto.setDescrizione(res.getString("descrizione"));
            oggetto.setUrl(res.getString("url"));
            oggetto.setIdVenditore(res.getInt("idVenditore"));
            
            stmt.close();
            conn.close();
            return oggetto;
        }
        
        stmt.close();
        conn.close();
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;       
    }

    
    public ArrayList<Oggetti> getOggettiList(){
        ArrayList<Oggetti> lista = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(connectionString, "fabrizio", "1234");
            Statement stmt = conn.createStatement();
            String query = "select * from Oggetti";
            ResultSet set = stmt.executeQuery(query);
            
            while(set.next()){
                Oggetti oggetto = new Oggetti();
                oggetto.setId(set.getInt("id"));
                oggetto.setNomeOggetto(set.getString("nome"));
                oggetto.setPrezzo(set.getDouble("prezzo"));
                oggetto.setPezziDisponibili(set.getInt("pezzi"));
                oggetto.setDescrizione(set.getString("descrizione"));
                oggetto.setUrl(set.getString("url"));
                oggetto.setIdVenditore(set.getInt("idVenditore"));
                lista.add(oggetto);
            }
            
            stmt.close();
            conn.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return lista;        
    }
    
    
    public ArrayList<Oggetti> getOggettiList(Utente utente){
        ArrayList<Oggetti> lista = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(connectionString, "fabrizio", "1234");
            Statement stmt = conn.createStatement();
            String query = "select * from Oggetti where idVenditore =" + utente.getId();
            ResultSet set = stmt.executeQuery(query);
            
            while(set.next()){
                Oggetti oggetto = new Oggetti();
                oggetto.setId(set.getInt("id"));
                oggetto.setNomeOggetto(set.getString("nome"));
                oggetto.setPrezzo(set.getDouble("prezzo"));
                oggetto.setPezziDisponibili(set.getInt("pezzi"));
                oggetto.setDescrizione(set.getString("descrizione"));
                oggetto.setUrl(set.getString("url"));
                oggetto.setIdVenditore(set.getInt("idVenditore"));
                lista.add(oggetto);
            }
            
            stmt.close();
            conn.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return lista;        
    }

    
    public Double getSaldo(int idCliente) throws SQLException{
        Double conto = null;
        try {
            Connection conn = DriverManager.getConnection(connectionString, "fabrizio", "1234");
            String query = "select credito from Cliente where id = " + idCliente;
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery(query);
               
            if (res.next()){
                conto = res.getDouble("credito");
            }
            stmt.close();
            conn.close();
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
        return conto;
    }
        
    
    public String UpdateConto(Oggetti oggetto, Utente cliente) throws SQLException {
        Connection conn = DriverManager.getConnection(connectionString, "fabrizio", "1234");
        PreparedStatement updateOggetti = null;
        PreparedStatement updateCreditoCl = null;
        PreparedStatement updateCreditoVe = null;
        PreparedStatement updatePezziDisp = null;
        
        String deleteOggetto = "delete from Oggetti where id = ?";
        String updatePezzi = "update Oggetti set pezzi = pezzi - 1 where id = ? ";
        String updateSaldoCl = "update Cliente set credito = credito - ? where id = ? ";
        String updateSaldoVe = "update Venditore set credito = credito + ? where id = ? ";
        
        try {
            conn.setAutoCommit(false);
            
            double conto = getSaldo(cliente.getId());
            
            if (conto > oggetto.getPrezzo()){
                if (oggetto.getPezziDisponibili() == 1){
                    updateOggetti = conn.prepareStatement(deleteOggetto);
                    updateCreditoCl = conn.prepareStatement(updateSaldoCl);
                    updateCreditoVe = conn.prepareStatement(updateSaldoVe);
                
                    updateOggetti.setInt(1, oggetto.getId());
                
                    updateCreditoCl.setDouble(1, oggetto.getPrezzo());
                    updateCreditoCl.setInt(2, cliente.getId());
            
                    updateCreditoVe.setDouble(1, oggetto.getPrezzo());
                    updateCreditoVe.setInt(2, oggetto.getIdVenditore());              
                            
                    int res1 = updateOggetti.executeUpdate();
                    int res2 = updateCreditoCl.executeUpdate();
                    int res3 = updateCreditoVe.executeUpdate();
                
                    if(res1 != 1 || res2 !=1 || res3 != 1 ){
                        conn.rollback();
                        return "fallita";
                    }
                }
                else if (oggetto.getPezziDisponibili() > 1){
                    updatePezziDisp = conn.prepareStatement(updatePezzi);
                    updateCreditoCl = conn.prepareStatement(updateSaldoCl);
                    updateCreditoVe = conn.prepareStatement(updateSaldoVe);
                
                    updatePezziDisp.setInt(1, oggetto.getId());
                          
                    updateCreditoCl.setDouble(1, oggetto.getPrezzo());
                    updateCreditoCl.setInt(2, cliente.getId());
            
                    updateCreditoVe.setDouble(1, oggetto.getPrezzo());
                    updateCreditoVe.setInt(2, oggetto.getIdVenditore());              
                            
                    int res1 = updatePezziDisp.executeUpdate();
                    int res2 = updateCreditoCl.executeUpdate();
                    int res3 = updateCreditoVe.executeUpdate();
                
                    if(res1 != 1 || res2 !=1 || res3 != 1 ){
                        conn.rollback();
                        return "fallita";
                    }
                }   
                conn.commit();
                return "successo";
            }
                        
            else {
                return "nosoldi";
            }
        } 
        catch (SQLException e){
            conn.rollback();
            throw e;
        }
        finally {
            if(updateOggetti != null){
                updateOggetti.close();
            }
            if(updateCreditoCl != null){
                updateCreditoCl.close();
            }
            if(updateCreditoVe != null){
                updateCreditoVe.close();
            }
            if(updatePezziDisp != null){
                updatePezziDisp.close();
            }
        }
    }
    
    
    public void InsOggetto(Oggetti oggetto) {
        try {  
            Connection conn = DriverManager.getConnection(connectionString, "fabrizio", "1234");
            String query = "insert into Oggetti (id, nome, prezzo, pezzi, descrizione, url, idVenditore)"
                + "values (default, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            
            stmt.setString(1, oggetto.getNomeOggetto());
            stmt.setDouble(2, oggetto.getPrezzo());
            stmt.setInt(3, oggetto.getPezziDisponibili());        
            stmt.setString(4, oggetto.getDescrizione());
            stmt.setString(5, oggetto.getUrl());
            stmt.setInt(6, oggetto.getIdVenditore());     
        
            int r1 = stmt.executeUpdate();
        
            if (r1 == 1){
                System.out.println("\nInserimento andato a buon fine\n");
            }
            
            stmt.close();
            conn.close();
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    } 
   
    
    public void UpdateOggetto(Oggetti oggetto) {
        try {  
            Connection conn = DriverManager.getConnection(connectionString, "fabrizio", "1234");
            String query = "update Oggetti set nome = ?, prezzo = ?, pezzi = ?, descrizione = ?, url = ? where id = " + oggetto.getId();
            PreparedStatement stmt = conn.prepareStatement(query);
            
            stmt.setString(1, oggetto.getNomeOggetto());
            stmt.setDouble(2, oggetto.getPrezzo());
            stmt.setInt(3, oggetto.getPezziDisponibili());        
            stmt.setString(4, oggetto.getDescrizione());
            stmt.setString(5, oggetto.getUrl());
       
            int r1 = stmt.executeUpdate();
        
            if (r1 == 1){
                System.out.println("\nAggiornamento andato a buon fine\n");
            }
            
            stmt.close();
            conn.close();
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    } 

    
    public void DeleteOggetto(int id) {
        try {  
            Connection conn = DriverManager.getConnection(connectionString, "fabrizio", "1234");
            String query = "delete from Oggetti where id = " + id;
            PreparedStatement stmt = conn.prepareStatement(query);
       
            int r1 = stmt.executeUpdate();
        
            if (r1 == 1){
                System.out.println("\nCancellamento andato a buon fine\n");
            }
            
            stmt.close();
            conn.close();
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public ArrayList<Oggetti> getOggettiSearch(String text){
        ArrayList<Oggetti> lista = new ArrayList<>();
        
        try {
            Connection conn = DriverManager.getConnection(connectionString, "fabrizio", "1234");
            String query = "select * from Oggetti where Oggetti.nome LIKE ? OR Oggetti.descrizione LIKE ? ";
            
            PreparedStatement stmt = conn.prepareStatement(query);
            
            String testo = "%" + text + "%";
            stmt.setString(1,testo);
            stmt.setString(2, testo);
            
            ResultSet res = stmt.executeQuery();
            
            while(res.next()){
                Oggetti oggetto = new Oggetti();
                oggetto.setId(res.getInt("id"));
                oggetto.setNomeOggetto(res.getString("nome"));
                oggetto.setPrezzo(res.getDouble("prezzo"));
                oggetto.setPezziDisponibili(res.getInt("pezzi"));
                oggetto.setDescrizione(res.getString("descrizione"));
                oggetto.setUrl(res.getString("url"));
                oggetto.setIdVenditore(res.getInt("idVenditore"));
                lista.add(oggetto);
            }
            
            stmt.close();
            conn.close();       
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return lista;
    }
}
