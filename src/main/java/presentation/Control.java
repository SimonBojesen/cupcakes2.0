/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import data.DataMapper;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.entity.User;

/**
 *
 * @author Simon
 */
@WebServlet(name = "Control", urlPatterns = {"/Control"})
public class Control extends HttpServlet {

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
        String origin = request.getParameter("origin");
        System.out.println(origin);
        try {
            DataMapper dm = new DataMapper();
            if (origin != null) {
                switch (origin) {
                    case "login":
                        caseLogin(request, dm, response);
                        break;
                    case "register":
                        caseRegister(request, dm, response);
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Check stacktrace error");
        }
    }

    private void caseRegister(HttpServletRequest request, DataMapper dm, HttpServletResponse response) throws IOException {
        String usernameReg = request.getParameter("usernameRegister");
        String passwordReg = request.getParameter("passwordRegister");
        String emailReg = request.getParameter("email");
        boolean createUserResult = dm.createUser(usernameReg, passwordReg, emailReg);
        
        if (createUserResult == true) {
            request.setAttribute("CreateSucced", "Your account has been made");
            System.out.println(request.getAttribute("CreateSucced"));
            response.sendRedirect("login_and_register.html");
        } else {
            request.setAttribute("error", "Something went wrong, please try again");
            System.out.println(request.getAttribute("error"));
            response.sendRedirect("login_and_register.html");
        }
    }

    private void caseLogin(HttpServletRequest request, DataMapper dm, HttpServletResponse response) throws IOException, ServletException {
        String usernameLogin = request.getParameter("usernameLogin");
        String passwordLogin = request.getParameter("passwordLogin");
        User user = dm.authenticateLogin(usernameLogin, passwordLogin);
        
        if (user != null) {
            request.getSession().setAttribute("user", user);
            request.getRequestDispatcher("cupcakehome.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Unknown user, please try again");
            System.out.println(request.getAttribute("error"));
            request.getRequestDispatcher("login_and_register.html").forward(request, response);
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
