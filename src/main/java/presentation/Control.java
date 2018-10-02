/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import data.DataMapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.entity.Bottom;
import logic.entity.CupCake;
import logic.entity.Order;
import logic.entity.OrderLine;
import logic.entity.Topping;
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
                    case "shoppingcart":
                        System.out.println("shopping-----------");
                        caseShoppingcart(request, dm, response);
                        break;
                    case "checkout":
                        caseCheckOut(request, response);
                        break;

                    case "invoices":
                        System.out.println("invoice buttom--------------");
                        List<Order> orders = dm.getPreviouseInvoices();
                        request.setAttribute("orders", orders);
                        request.getRequestDispatcher("veiwInvoices.jsp").forward(request, response);
                        break;
                    case "balanceIncrease":
                        caseBalanceincrease(request, dm, response);
                        break;
                    case "clearCart":
                        caseClearcart(request, response);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Check stacktrace error");
        }
    }

    private void caseCheckOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<OrderLine> ol = (ArrayList) request.getSession().getAttribute("shoppingcart");
        User user = (User) request.getSession().getAttribute("user");
        double totalprice = 0;
        boolean ispaid = false;
        for (int i = 0; i < ol.size(); i++) {
            totalprice += ol.get(i).getTotalPrice();
        }
        
        if (user.getBalance() > totalprice) {
            double newBalance = user.getBalance() - totalprice;
            ispaid = true;
        }
        
        Order order = new Order(user.getUserid(), ispaid);
//                        dm.checkOutOrder(order, user);
ol.clear();
request.getRequestDispatcher("cupcakehome.jsp").forward(request, response);
    }

    private void caseClearcart(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<OrderLine> ol = (ArrayList) request.getSession().getAttribute("shoppingcart");
        ol.clear();
        request.getRequestDispatcher("cupcakehome.jsp").forward(request, response);
    }

    private void caseBalanceincrease(HttpServletRequest request, DataMapper dm, HttpServletResponse response) throws IOException, NumberFormatException, ServletException {
        User user = (User) request.getSession().getAttribute("user");
        double currentBalance = user.getBalance();
        double increaseAmount = Double.parseDouble(request.getParameter("amountIncrease"));
        double newBalance = currentBalance + increaseAmount;
        dm.updateBalance(newBalance, user);
        user.setBalance(newBalance);
        request.getRequestDispatcher("cupcakehome.jsp").forward(request, response);
    }

    private void caseShoppingcart(HttpServletRequest request, DataMapper dm, HttpServletResponse response) throws IOException, ServletException, NumberFormatException {
        List<OrderLine> ol = null;
        if (request.getSession().getAttribute("shoppingcart") == null) {
            ol = new ArrayList();
            System.out.println("session not found");
            request.getSession().setAttribute("shoppingcart", ol);
        } else {
            System.out.println("session found");
            ol = (List<OrderLine>) request.getSession().getAttribute("shoppingcart");
        }
        System.out.println("getting information");
        Topping top = dm.getToppings().get(Integer.parseInt(request.getParameter("topping")));
        Bottom bot = dm.getBottoms().get(Integer.parseInt(request.getParameter("bottom")));
        int qty = Integer.parseInt(request.getParameter("qty"));
        double total = qty * (top.getTprice() + bot.getBprice());
        ol.add(new OrderLine(new CupCake(bot, top), total, qty));
        System.out.println("added information");
        request.getRequestDispatcher("cupcakehome.jsp").forward(request, response);
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
