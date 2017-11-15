/* 
    Document   : LoginServlet
    Created on : Maggio 2016
    Author     : Fabrizio Cara
*/
package PackageTechommerceServlet;

import PackageTechommerce.Oggetti;
import PackageTechommerce.Utente;
import PackageTechommerce.Utenti_Factory;
import PackageTechommerce.Utenti_Venditori;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Login", urlPatterns = {"/Login.html"}, loadOnStartup = 0)
public class Login extends HttpServlet {
    
    private static final String JDBC_DRIVER = "org.apache.derby.jdbc.ClientDriver";
    private static final String PATH = "jdbc:derby://localhost:1527/WSDB";
    
    @Override 
    public void init(){
        String dbConnection = PATH;
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        Utenti_Factory.getInstance().setConnectionString(dbConnection);
    }
    
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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession();
	                     
        if(request.getParameter("Submit") == null) {
            ArrayList<Oggetti> listaOggetti = Utenti_Factory.getInstance().getOggettiList();
            
            if (session.getAttribute("loggedId") != null) {
                if(request.getParameter("logout") != null){
                session.invalidate();
                request.getRequestDispatcher("Login.jsp").forward(request, response);
                }
                
                switch ((String) session.getAttribute("Utente")) {
                    case ("cliente"): {
                        Utente u = Utenti_Factory.getInstance().getUtenti_Clienti((Utente)session.getAttribute("cliente"));
                        session.setAttribute("cliente", u);
                        request.setAttribute("listaOggetti", listaOggetti);
                        request.setAttribute("Buyer", true);
                        request.getRequestDispatcher("Login_Cliente.jsp").forward(request, response);
                        break;
                    }
                    case ("venditore"): {
                        if (request.getParameter("Valore") != null){
                            Utente u = Utenti_Factory.getInstance().getUtenti_Venditori((Utente)session.getAttribute("venditore"));
                            session.setAttribute("venditore", u);
                            request.setAttribute("Seller", true);
                            request.setAttribute("Update", true);
                            request.getRequestDispatcher("Login_Venditore.jsp").forward(request, response);
                        }
                        else {
                            Utente u = Utenti_Factory.getInstance().getUtenti_Venditori((Utente)session.getAttribute("venditore"));
                            session.setAttribute("venditore", u);
                            
                            request.setAttribute("Seller", true);
                            request.setAttribute("riepilogo", false);
                            request.setAttribute("Update", false);
                            
                            Utente u1 = Utenti_Factory.getInstance().getUtenti_Venditori((Utente)session.getAttribute("venditore"));
                            ArrayList<Oggetti> listaOggettiVend = Utenti_Factory.getInstance().getOggettiList(u1);
                            request.setAttribute("listaOggettiVendesi", listaOggettiVend);
                            
                            request.getRequestDispatcher("Login_Venditore.jsp").forward(request, response);
                        }
                        break;
                    }
                }   
            }
            else {
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            }
        }
        
        
        else if (request.getParameter("Submit") != null){    
            ArrayList<Oggetti> listaOggetti = Utenti_Factory.getInstance().getOggettiList();
            String username = request.getParameter("Username");
            String password = request.getParameter("Password");
            
            Utente u = Utenti_Factory.getInstance().getUtente(username, password);
            if(u != null) {   
                session.setAttribute("loggedId", true);
                session.setAttribute("id", u.getId());
                 
                if(u instanceof Utenti_Venditori) {
                    session.setAttribute("venditore", u);
                    session.setAttribute("Utente", "venditore");
                    request.setAttribute("Seller", true);
                    request.setAttribute("Update", false);
                    
                    Utente u1 = Utenti_Factory.getInstance().getUtenti_Venditori((Utente)session.getAttribute("venditore"));
                    ArrayList<Oggetti> listaOggettiVend = Utenti_Factory.getInstance().getOggettiList(u1);
                    request.setAttribute("listaOggettiVendesi", listaOggettiVend);
                    
                    request.getRequestDispatcher("Login_Venditore.jsp").forward(request, response);
                }
                else {
                    session.setAttribute("cliente", u);                    
                    session.setAttribute("Utente", "cliente");
                    request.setAttribute("Buyer", true);
                    request.setAttribute("listaOggetti", listaOggetti);
                    request.getRequestDispatcher("Login_Cliente.jsp").forward(request, response);
                }
            }
            
            request.setAttribute("errore", "Username e/o Password errati. Tentare di nuovo!");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
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
        processRequest(request, response);
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
        processRequest(request, response);
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
