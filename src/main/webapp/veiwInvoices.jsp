<%-- 
    Document   : veiwInvoices
    Created on : 27-09-2018, 09:11:08
    Author     : Bruger
--%>

<%@page import="java.sql.Date"%>
<%@page import="java.util.TimeZone"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.List"%>
<%@page import="logic.entity.Order"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Invoice</title>


        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    </head>
    <body>
        <input type="hidden" name ="origin" value="">

        <% List<Order> orders = (List) request.getAttribute("orders"); %>
        <% int count = 1; %>

        <h3>List of Invoices </h3>
        <ul class="list-group list-group-flush">

            <%
                for (Order order : orders) {
                    out.print("<a href=\"Control?origin=singleInvoice=" + order.getOrderId()+ "<li class=\"list-group-item\">" + "Invoice nr:" + count + " userId: " + order.getUserId() + "is piad: " + order.isIsPaid() + "</li></a>");
                    count++;
                }
            %>
        </ul>  
    </body>
</html>
