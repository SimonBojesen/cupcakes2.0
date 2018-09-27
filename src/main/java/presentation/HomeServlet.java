/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import logic.entity.Bottom;
import logic.entity.CupCake;
import data.DataAccessorDataBase;
import logic.entity.OrderLine;
import logic.entity.Topping;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Simon
 */
@WebServlet(name = "Home", urlPatterns = {"/Home"})
public class HomeServlet extends HttpServlet {

    ArrayList<OrderLine> sb = new ArrayList();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void getOrderLineHTML() {
        out.println("<table>");
        out.println("<thead>");
        out.println("<tr>");
        out.println("<th>Name</th>");
        out.println("<th>Quantity</th>");
        out.println("<th>Price</th>");
        out.println("<th>Total</th>");
        out.println("<th>Remove</th>");
        out.println("</tr>");
        out.println("</thead>");
        out.println("<tbody>");

        for (int i = 0; i < sb.size(); i++) {
            out.println("<tr>");
            out.println("<td>" + sb.get(i).getCupcake().getBottom().getBname() + " with " + sb.get(i).getCupcake().getTopping().getTname() + "</td>");
            out.println("<td>" + sb.get(i).getQty() + "</td>");
            out.println("<td>" + sb.get(i).getCupcake().getPrice() + "</td>");
            out.println("<td>" + sb.get(i).getTotalPrice() + "</td>");
            out.println("<td>");
            out.println("<form action=\"Control\" method=\"POST\"><input type=\"hidden\" name=\"origin\" value=\"removeLine\"><input type=\"hidden\" name=\"lineId\" value=\"258\"><input type=\"submit\" value=\"remove\"></form>");
            out.println("</td>");
            out.println("</tr>");
        }
        out.println("<tr><td><h4>Total price</h4></td>");
        out.println("<td></td>");
        out.println("<td></td>");
        out.println("<td id=\"totalprice\"><h4>240.00</h4></td>");
        out.println("<td><form id=\"checkoutForm\" action=\"CheckOut\" method=\"POST\"><input type=\"hidden\" name=\"origin\" value=\"submitInvoice\"><input type=\"submit\" value=\"Check out your order\"></form></td>");
        out.println("</tbody>");
        out.println("</table>");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            DataAccessorDataBase da = new DataAccessorDataBase();

            ArrayList<Topping> topping = da.getTopping();
            ArrayList<Bottom> bottom = da.getBottom();

            int count = 1;
            int count2 = 1;
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet HomeServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div>");
            out.println("<h1>Compose cupcakes from 1 bottom and 1 top and add to shopping cart</h1>");
            out.println("<form action=\"addOrderline\" method=\"post\">");
            out.println("<table>");

            out.println("<thead>");
            out.println("<tr>");
            out.println("<th>Topping</th>");
            out.println("<th>Quantity</th>");
            out.println("<th>Select</th>");
            out.println("<th>            out.println(\"<th>Bottom</th>\");\n" +
"</th>");
            out.println("</tr>");
            out.println("</thead>");

            out.println("<tbody>");
            out.println("<tr>");
            out.println("<td><select name=\"bottom\" id=\"bottomSelect\"><option value=\"0\">Choose Bottom</option>");
            for (int i = 0; i < bottom.size(); i++) {
                out.println("<option value=\"" + count + "\">" + bottom.get(i).getBname() + ": " + bottom.get(i).getBprice() + " </option>");
                count++;
            }
            out.println("</select>");
            out.println("</td>");

            out.println("<td><select name=\"topping\" id=\"toppingSelect\"><option value=\"0\">Choose topping</option>");
            for (int i = 0; i < topping.size(); i++) {
                out.println("<option value=\"" + count2 + "\">" + topping.get(i).getTname() + ": " + topping.get(i).getTprice() + " </option>");
                count2++;
            }

            out.println("</select>");
            out.println("</td>");
            out.println("<td><input type=\"text\" name=\"qty\" placeholder=\"quantity\" id=\"qtyInput\"></td>");
            out.println("<td><input type=\"submit\" name=\"submit\" value=\"Add to cart\"></td>");
            out.println("<td><span id=\"errorContainer\"></span></td>");
            out.println("</tr>");
            out.println("</tbody>");
            out.println("</table>");
            out.println("</form>");
            //Funktion her til at ku se din tilføjede orderline
            if (!sb.isEmpty()) {
                this.getOrderLineHTML();
            }
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getParameter("submit") != null) {
            String bottomSelected = request.getParameter("bottom");
            String toppingSelected = request.getParameter("topping");
            String quantity = request.getParameter("qty");
            int qty = Integer.parseInt(quantity);

            String[] parts = bottomSelected.split(": ");
            String bName = parts[0];
            String bPrice = parts[1];
            double bp = Double.parseDouble(bPrice);

            String[] parts2 = toppingSelected.split(": ");
            String tName = parts2[0];
            String tPrice = parts2[1];
            double tp = Double.parseDouble(tPrice);

            Bottom b = new Bottom(bName, bp);
            Topping t = new Topping(tName, tp);

            CupCake cc = new CupCake(b, t);
            double olPrice = (bp + tp) * qty;
            OrderLine ol = new OrderLine(cc, olPrice, qty);

            sb.add(ol);
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
