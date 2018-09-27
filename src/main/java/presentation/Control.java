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
        try {
            DataMapper dm = new DataMapper();
            if (origin != null) {
                switch (origin) {
                    case "login":
                        String loginHidden = request.getParameter("loginFormCheck");
                        String registerHidden = request.getParameter("registerFormCheck");
                        if (loginHidden.equals("LoginForm")) {
                            String username = request.getParameter("username");
                            String password = request.getParameter("psw");
                            User user = dm.authenticateLogin(username, password);

                            if (user != null) {
                                request.getSession().setAttribute("user", user);
                                response.sendRedirect("Home");
                            } else {
                                request.setAttribute("error", "Unknown user, please try again");
                                request.getRequestDispatcher("/Login.jsp").forward(request, response);
                            }
                            request.setAttribute("error", "Login not yet made");
                            request.getRequestDispatcher("error.jsp").forward(request, response);
                            break;
                        } else if (registerHidden.equals("RegisterForm")) {
                            String username = request.getParameter("username");
                            String password = request.getParameter("psw");
                            String mail = request.getParameter("mail");
                            boolean createUserResult = dm.createUser(username, password, mail);

                            if (createUserResult == true) {
                                response.sendRedirect("Login");
                            } else {
                                request.setAttribute("error", "Something went wrong, please try again");
                            }
                        }

                }
            }
        } catch (Exception e) {

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
