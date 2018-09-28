<%-- 
    Document   : veiwInvoices
    Created on : 27-09-2018, 09:11:08
    Author     : Bruger
--%>

<%@page import="data.DataMapper"%>
<%@page import="java.sql.Date"%>
<%@page import="java.util.TimeZone"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.List"%>
<%@page import="logic.entity.Order"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <input type="hidden" name ="origin" value="">
        <h1>Liste af personer</h1>
        <h3>List of Invoices </h3>
        <% List<Order> orders = (List) request.getAttribute("orders"); %>
        <% int count = 1; %>
        <ul class="list-group list-group-flush">
            <%
                for (int i = 0; i < orders.size(); i++) {
                    out.print("<a href=\"Control?origin=singleInvoice=" + orders.get(i).getOrderId() + "<li class=\"list-group-item\">" + "Invoice nr:" + count + " userId: " + orders.get(i).getUserId() + "is piad: " + orders.get(i).isIsPaid() + "</li></a>");
                    count++;
                }
            %>

        </ul>
    </body>
</html>
