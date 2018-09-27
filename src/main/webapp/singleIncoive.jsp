<%-- 
    Document   : singleIncoive
    Created on : 27-09-2018, 10:06:52
    Author     : Bruger
--%>

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
            <% Order or = (Order) request.getAttribute("information"); %>

            <h3>List of Invoices </h3>
            <ul class="list-group list-group-flush">

                <%
                    for (int i = 0; i < or.getAllOrders().size(); i++) {
                        out.print("<li class=\"list-group-item\">" + "Cupcake: " + or.getAllOrders().get(i).getCupcake()
                                + " qty of cupcake: " + or.getAllOrders().get(i).getQty() + " total price: " + or.getAllOrders().get(i).getTotalPrice() + "</li>");
                    }
                %>
            </ul>  
        </div>
    </body>
</html>
