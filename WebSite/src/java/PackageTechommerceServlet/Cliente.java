/* 
    Document   : ClientiServlet
    Created on : Maggio 2016
    Author     : Fabrizio Cara
*/
package PackageTechommerceServlet;

import PackageTechommerce.Oggetti;
import PackageTechommerce.Utente;
import PackageTechommerce.Utenti_Factory;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Cliente", urlPatterns = {"/Cliente.html"})
public class Cliente extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     */
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        
        HttpSession session = request.getSession(false);
        request.setAttribute("riepilogo", false);
        
        ArrayList<Oggetti> listaOggetti = new ArrayList<>();
        listaOggetti = Utenti_Factory.getInstance().getOggettiList();
        
        //Controllo per accesso da Login a Cliente, passando per il link senza fare il login
        if(session.getAttribute("loggedId") == null){
            request.setAttribute("Seller", true);
            request.getRequestDispatcher("Login_Cliente.jsp").forward(request, response);
        }
        
        //Controllo per l'accesso inserendo una url Cliente
        else if (session.getAttribute("loggedId") != null){
            switch ((String)session.getAttribute("Utente")){
                case ("venditore"): {
                    request.setAttribute("Seller", true);
                    request.getRequestDispatcher("Login_Cliente.jsp").forward(request, response);
                    }
                case ("cliente"): { 
                    String a = request.getParameter("IdOggetto");          
                    if (request.getParameter("IdOggetto") != null){
                        Oggetti oggettoScelto = Utenti_Factory.getInstance().getOggetto(Integer.parseInt(request.getParameter("IdOggetto")));
                    
                        request.setAttribute("oggetto", oggettoScelto);
                        request.setAttribute("riepilogo", true);
                                                
                        request.getRequestDispatcher("Login_Cliente.jsp").forward(request, response);                     
                    }  
                    
                    else if (request.getParameter("Idoggetto_scelto") != null){
                        Oggetti oggettoScelto = Utenti_Factory.getInstance().getOggetto(Integer.parseInt(request.getParameter("Idoggetto_scelto")));
                        String operazione = Utenti_Factory.getInstance().UpdateConto(oggettoScelto, (Utente)session.getAttribute("cliente"));
                        Utente u = Utenti_Factory.getInstance().getUtenti_Clienti((Utente)session.getAttribute("cliente"));
                        session.setAttribute("cliente", u);
                                                
                        if (operazione != null) 
                            switch (operazione) {
                                case ("successo"):{
                                    request.setAttribute("oggetto", oggettoScelto);
                                    request.setAttribute("acquistato", true);
                                    request.getRequestDispatcher("Login_Cliente.jsp").forward(request, response);
                                    break;
                                }
                                case ("nosoldi"):{
                                    request.setAttribute("Noacquistato", true);
                                    request.getRequestDispatcher("Login_Cliente.jsp").forward(request, response);
                                    break;
                                }
                                case ("fallita"):{
                                    request.setAttribute("fallita", true);
                                    request.getRequestDispatcher("Login_Cliente.jsp").forward(request, response);
                                    break;
                                }
                            }
                    }
                        
                    else{
                        request.setAttribute("Buyer", true);
                        request.setAttribute("listaOggetti", listaOggetti);
                        request.setAttribute("cliente", (Utente)session.getAttribute("cliente"));
                        request.getRequestDispatcher("Login_Cliente.jsp").forward(request, response);                     
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
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
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
