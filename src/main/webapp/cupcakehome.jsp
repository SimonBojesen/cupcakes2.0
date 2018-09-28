<%@page import="logic.entity.User"%>
<%@page import="logic.entity.OrderLine"%>
<%@page import="java.util.List"%>
<%@page import="data.DataMapper"%>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<%-- 
    Document   : cupcakeide
    Created on : 26-09-2018, 23:57:12
    Author     : Rasmu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% DataMapper dm = new DataMapper(); %>
<link rel="stylesheet" type="text/css" media="all" href="CSS/main.css">

<div class="container register">
    <div class="row">
        <div class="col-md-3 register-left">
            <img src="https://pro2-bar-s3-cdn-cf4.myportfolio.com/cacac6b6e47e6135f27164e74f70129f/fdb8086e-2ac9-484f-accf-c1c7dd0663ff_rw_1920.png?h=f139fe80413c5335535f23a6d5091b62" alt=""/>
            <h3>Welcome</h3>
            <p>Order the most delicious cupcakes right here!</p>

            <div>
                <from id="veiwInvoices" action="Control" method="post">
                    <input type="hidden" name="origin" value="invoices">
                    <input type="submit" value="Previouse Invoices"/><br/>
                </from>
            </div>

        </div>
        <div class="col-md-9 register-right">
            <form action="Control" method="post">
                <input type="hidden" name="origin" value="balanceIncrease">
                <%
                    if (request.getSession().getAttribute("user") != null) {
                        User user = (User) request.getSession().getAttribute("user"); %>
                        <input type="text" placeholder="Enter amount" name="amountIncrease">
                        <input type="submit" value="IncreaseBalance">
                        <label>Logged in as: <b><% out.print(user.getUsername()); %></b></label>
                        <label>Account balance: <b><% out.print(user.getBalance()); %></b></label>
                        <%
                    }
                %>
            </form>
            <div class="tab-content" id="myTabContent">
                <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                    <h3 class="register-heading">Choose bottom and topping</h3>
                    <div class="row register-form">
                        <div class="col-md-10">
                            <form id="shoppingcart" action="Control" method="post">
                                <div class="form-group">
                                    <select name="bottom" class="form-control">
                                        <option class="hidden"  selected disabled>Please select your Bottom</option>
                                        <%                                            for (int i = 0; i < dm.getBottoms().size(); i++) {
                                                out.println("<option value=" + i + ">" + dm.getBottoms().get(i).getBname() + " " + dm.getBottoms().get(i).getBprice() + "</option>");
                                            }
                                        %>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <select name="topping" class="form-control">
                                        <option class="hidden"  selected disabled>Please select your Topping</option>
                                        <%
                                            for (int i = 0; i < dm.getToppings().size(); i++) {
                                                out.println("<option value=" + i + ">" + dm.getToppings().get(i).getTname() + " " + dm.getToppings().get(i).getTprice() + "</option>");
                                            }
                                        %>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control" placeholder="Enter Your quantity" name="qty" />
                                </div>
                                <div class="form-group">
                                    <input type="hidden" name="origin" value="shoppingcart">
                                    <input type="submit" class="btnAddToCard"  value="Add to cart" style="margin-bottom: 20px">
                                </div>
                            </form>
                        </div>

                        <div class="col-md-10 register-bottom">

                            <% List<OrderLine> ol = (List<OrderLine>) request.getSession().getAttribute("shoppingcart");  %>
                            <ul class="list-group list-group-flush">

                                <%
                                    if (ol != null) {
                                        for (int i = 0; i < ol.size(); i++) {
                                            out.print("<li class=\"list-group-item\">Cupcake: " + ol.get(i).getCupcake() + "<br> Quantity: " + ol.get(i).getQty() + "<br> Total price: " + ol.get(i).getTotalPrice() + "</li>");
                                        }
                                %> 
                                <div class="form-group" style="float:left;">
                                    <form id="remove" action="Control" method="post" >
                                        <input type="hidden" name="origin" value="clearCart">
                                        <input type="submit" class="btnFinishOrder"  value="Clear shoppingcart"/>
                                    </form>
                                </div>
                                <div class="form-group" style="float:left;">
                                    <form id="checkout" action="Control" method="post" >
                                        <input type="hidden" name="origin" value="checkout">
                                        <input type="submit" class="btnFinishOrder"  value="Check out"/>
                                    </form>
                                </div> 
                                <%
                                    }
                                %>
                            </ul> 
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>

</div>