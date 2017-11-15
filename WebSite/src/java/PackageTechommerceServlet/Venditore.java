/* 
    Document   : VenditoreServlet
    Created on : Maggio 2016
    Author     : Fabrizio Cara
*/
package PackageTechommerceServlet;

import PackageTechommerce.Oggetti;
import PackageTechommerce.Utente;
import PackageTechommerce.Utenti_Factory;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Venditore", urlPatterns = {"/Venditore.html"})
public class Venditore extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        
        HttpSession session = request.getSession(false);
        request.setAttribute("riepilogo", false);
                
        //Controllo per accesso da Login a Venditore, passando per il link senza fare il login
        if (session.getAttribute("loggedId") == null){
            request.setAttribute("Buyer", true);
            request.getRequestDispatcher("Login_Venditore.jsp").forward(request, response);
        }

        else if(session.getAttribute("loggedId") != null){
            switch ((String)session.getAttribute("Utente")){
                case("cliente"): {
                    request.setAttribute("Buyer", true);
                    request.getRequestDispatcher("Login_Venditore.jsp").forward(request, response);
                }
                case ("venditore"):{
                    //Form Venditore
                    if(request.getParameter("SubmitVenditore") != null){
                        Oggetti oggettoAggiunto = new Oggetti();
                        oggettoAggiunto.setNomeOggetto(request.getParameter("NomeOggetto"));
                        oggettoAggiunto.setUrl(request.getParameter("Url"));
                        oggettoAggiunto.setDescrizione(request.getParameter("Descrizione"));
                        
                        //settare nell'oggetto l'Id del venditore salvato in sessione
                        Utente c = Utenti_Factory.getInstance().getUtenti_Venditori((Utente)session.getAttribute("venditore"));
                        oggettoAggiunto.setIdVenditore(c.getId());
                        
                        try {
                            oggettoAggiunto.setPezziDisponibili(Integer.parseInt(request.getParameter("Quantita")));
                        } 
                        
                        catch (Exception e) {
                            oggettoAggiunto.setPezziDisponibili(0);
                        }

                        try {
                            oggettoAggiunto.setPrezzo(Double.parseDouble(request.getParameter("Prezzo")));
                        } 

                        catch (Exception e) {
                            oggettoAggiunto.setPrezzo(0);
                        }
                        
                        if (oggettoAggiunto.getNomeOggetto() != null && oggettoAggiunto.getUrl() != null && oggettoAggiunto.getDescrizione() != null &&
                            oggettoAggiunto.getPezziDisponibili() != 0 && oggettoAggiunto.getPrezzo() != 0.0) {
                          
                            Utenti_Factory.getInstance().InsOggetto(oggettoAggiunto);
                            request.setAttribute("oggettoAggiunto", oggettoAggiunto);
                            request.setAttribute("riepilogo", true);
                            request.setAttribute("Seller", true);
                            request.getRequestDispatcher("Login_Venditore.jsp").forward(request, response);
                    
                        }
                        else {
                            request.setAttribute("noCompilato", true);
                            request.setAttribute("Seller", true);
                            request.setAttribute("Update", true);
                            request.getRequestDispatcher("Login_Venditore.jsp").forward(request, response);
                        }
                    }  
                                  
                    else if (request.getParameter("OggettoCanc") != null){
                        int idOggettoC = Integer.parseInt(request.getParameter("ID"));
                        Oggetti oggettoCanc = Utenti_Factory.getInstance().getOggetto(idOggettoC);
                        Utenti_Factory.getInstance().DeleteOggetto(idOggettoC);
                        
                        request.setAttribute("oggettoAggiunto", oggettoCanc);
                        request.setAttribute("riepilogo", true);
                        request.setAttribute("Delete", true);
                        request.getRequestDispatcher("Login_Venditore.jsp").forward(request, response);
                    }
                    
                    else if (request.getParameter("OggettoMod") != null){
                        int idOggettoM = Integer.parseInt(request.getParameter("ID"));
                        Oggetti obj = Utenti_Factory.getInstance().getOggetto(idOggettoM);
                        session.setAttribute("Obj", obj);
                        
                        request.setAttribute("Update", true);
                        request.setAttribute("Seller", true);
                        request.getRequestDispatcher("Login_Venditore.jsp").forward(request, response);
                    }
                    
                    else if (request.getParameter("UpdateItem") != null){
                        Oggetti oggettoMod = new Oggetti();
                        oggettoMod.setNomeOggetto(request.getParameter("NomeOggetto"));
                        oggettoMod.setUrl(request.getParameter("Url"));
                        oggettoMod.setDescrizione(request.getParameter("Descrizione"));
                        
                        Oggetti object = Utenti_Factory.getInstance().getOggetto((Oggetti)session.getAttribute("Obj"));
                        oggettoMod.setId(object.getId());
                        
                        Utente v = Utenti_Factory.getInstance().getUtenti_Venditori((Utente)session.getAttribute("venditore"));
                        oggettoMod.setIdVenditore(v.getId());
                        
                        try {
                            oggettoMod.setPezziDisponibili(Integer.parseInt(request.getParameter("Quantita")));
                        } 
                        
                        catch (Exception e) {
                            oggettoMod.setPezziDisponibili(0);
                        }

                        try {
                            oggettoMod.setPrezzo(Double.parseDouble(request.getParameter("Prezzo")));
                        } 

                        catch (Exception e) {
                            oggettoMod.setPrezzo(0);
                        }
                        
                        if (oggettoMod.getNomeOggetto() != null && oggettoMod.getUrl() != null && oggettoMod.getDescrizione() != null &&
                            oggettoMod.getPezziDisponibili() != 0 && oggettoMod.getPrezzo() != 0.0) {
                          
                            Utenti_Factory.getInstance().UpdateOggetto(oggettoMod);
                            request.setAttribute("oggettoAggiunto", oggettoMod);
                            request.setAttribute("riepilogo", true);
                            request.setAttribute("Update", true);
                            request.getRequestDispatcher("Login_Venditore.jsp").forward(request, response);
                    
                        }
                        else {
                            request.setAttribute("noCompilato", true);
                            request.setAttribute("Update", true);
                            request.getRequestDispatcher("Login_Venditore.jsp").forward(request, response);
                        }
                    }
                    
                    else if(request.getParameter("SubmitVenditore") == null && request.getParameter("OggettoMod") == null && request.getParameter("UpdateItem") == null){
                        Utente u = Utenti_Factory.getInstance().getUtenti_Clienti((Utente)session.getAttribute("venditore"));
                        session.setAttribute("venditore", u);
                        request.getRequestDispatcher("Login_Venditore.jsp").forward(request, response);
                    }
                    
                }
            }
        } 
    }
       

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Venditore.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Venditore.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
