<%-- 
    Document   : singleIncoive
    Created on : 27-09-2018, 10:06:52
    Author     : Bruger
--%>

<%@page import="java.util.List"%>
<%@page import="logic.entity.OrderLine"%>
<%@page import="logic.entity.Order"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Invoice</title>
    </head>
    <body>
        <input type="hidden" name ="origin" value="">
        <div>
            <%   
                Order or = (Order) request.getAttribute("information");
                List<OrderLine> l = or.getAllOrders();
            %>

            <h3>List of Invoices </h3>
            <ul class="list-group list-group-flush">

                <% 
                    for (int i = 0; i < l.size(); i++) {
                        out.print("<li class=\"list-group-item\">" + "Cupcake: " + l.get(i).getCupcake()
                                + " qty of cupcake: " + l.get(i).getQty() + " total price: " + l.get(i).getTotalPrice() + "</li>");
                    }
                %>
            </ul>  
        </div>
    </body>
</html>
