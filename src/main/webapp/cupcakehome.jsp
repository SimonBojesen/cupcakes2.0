<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<%-- 
    Document   : cupcakeide
    Created on : 26-09-2018, 23:57:12
    Author     : Rasmu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<link rel="stylesheet" type="text/css" media="all" href="CSS/main.css">

<div class="container register">
    <div class="row">
        <div class="col-md-3 register-left">
            <img src="https://pro2-bar-s3-cdn-cf4.myportfolio.com/cacac6b6e47e6135f27164e74f70129f/fdb8086e-2ac9-484f-accf-c1c7dd0663ff_rw_1920.png?h=f139fe80413c5335535f23a6d5091b62" alt=""/>
            <h3>Welcome</h3>
            <p>Order the most delicious cupcakes right here!</p>
            <input type="submit" name="" value="Previouse Invoices"/><br/>
        </div>
        <div class="col-md-9 register-right">
            <ul class="nav nav-tabs nav-justified" id="myTab" role="tablist">
                <li class="nav-item">
                    <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true">Pick Up</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile" aria-selected="false">Delivery</a>
                </li>
            </ul>
            <div class="tab-content" id="myTabContent">
                <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                    <h3 class="register-heading">Choose bottom and topping</h3>
                    <div class="row register-form">
                        <div class="col-md-10">
                            <div class="form-group">
                                <select class="form-control">
                                    <option class="hidden"  selected disabled>Please select your Bottom</option>
                                    <option>What is your Birthdate?</option>
                                    <%
                                            int count = 1;
                                        for (int i = 0; i < 10; i++) {
                                                out.println("<option>" + count);
                                                count++;
                                            }
                                        %>
                                </select>
                            </div>
                            <div class="form-group">
                                <select class="form-control">
                                    <option class="hidden"  selected disabled>Please select your Topping</option>
                                    <option>What is your Birthdate?</option>
                                    <option>What is Your old Phone Number</option>
                                    <option>What is your Pet Name?</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <input type="text" class="form-control" placeholder="Enter Your quantity *" value="" />
                            </div>
                            <input type="submit" class="btnAddToCard"  value="Add to cart"/>
                            
                            </div>
                            
                            <div class="col-md-9 register-bottom">
                                <h6 class="register-heading">name - quantity - totalprice</h6>
                                
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>

</div>