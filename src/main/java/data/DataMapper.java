/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import logic.entity.Bottom;
import logic.entity.Order;
import logic.entity.Topping;
import logic.entity.User;


public class DataMapper {
/**
 * Denne methode finder connectionen til den tilhørende database Laver query som henter 
 * alle informationerne for toppings tablet, og returnere name og price.
 * @return  topping
 */
    public ArrayList<Topping> getToppings() {
        ArrayList<Topping> topping = new ArrayList();
        try {
            Connection c = new DBConnector().getConnection();
            Statement stmt = c.createStatement();
            String query
                    = "SELECT `tname`,`tprice` "
                    + "FROM `toppings`;";
            ResultSet res = stmt.executeQuery(query);
            while (res.next()) {
                Topping tp = new Topping(res.getString("tname"), res.getDouble("tprice"));
                topping.add(tp);
            }
            return topping;
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error");
        }
        return null;
    }
/**
 * Denne methode finder connectionen til den tilhørende database Laver en query som henter 
 * alle informationerne for bottoms tablet, og returnere name og price
 * 
 * @return bottom
 */
    public ArrayList<Bottom> getBottoms() {
        ArrayList<Bottom> bottom = new ArrayList();
        try {
            Connection c = new DBConnector().getConnection();
            Statement stmt = c.createStatement();
            String query
                    = "SELECT `bname`,`bprice` "
                    + "FROM `bottoms`;";
            ResultSet res = stmt.executeQuery(query);
            while (res.next()) {
                Bottom bt = new Bottom(res.getString("bname"), res.getDouble("bprice"));
                bottom.add(bt);
            }
            return bottom;
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error");
        }
        return null;
    }

    public User getUser(String type) {
        try {
            Connection c = new DBConnector().getConnection();
            Statement stmt = c.createStatement();
            String query
                    = "SELECT *\n"
                    + "FROM `users`\n"
                    + "WHERE `username` = 'admin';";
            ResultSet res = stmt.executeQuery(query);
            res.next();
            User user = new User(res.getInt("userid"), res.getString("username"), res.getString("password"), res.getString("email"), res.getDouble("ballance"));
            return user;
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error");
        }
        return null;
    }

    public ArrayList<Order> getPreviouseInvoices() {
        ArrayList<Order> oArray = new ArrayList();
        try {
            Connection c = new DBConnector().getConnection();
            Statement stmt = c.createStatement();
            String query
                    = "SELECT * "
                    + "FROM orders;";
            ResultSet res = stmt.executeQuery(query);
            while (res.next()) {
                //Order o = new Order(res.getInt("orderid"), res.getInt("userid"), res.getBoolean("ispaid"));
                //oArray.add(o);
            }
            return oArray;
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error");
        }
        return null;
    }

    /**
     * Denne funktion tager imod username og password strenge når en person prøver at logge ind
     * Den sammenligner så med dataen i vores users table i databasen, og returner til sidst et User Objekt
     * @param username Users username
     * @param password Users password
     * @return User
     */
    public User authenticateLogin(String username, String password) {
        int dbId;
        double dbBalance;
        String query;
        String dbUsername, dbPassword, dbEmail;
        User user;
        try {
            Connection c = new DBConnector().getConnection();
            Statement stmt = c.createStatement();
            query = "SELECT * FROM users;";
            ResultSet res = stmt.executeQuery(query);
            while (res.next()) {
                dbId = Integer.parseInt(res.getString("userid"));
                dbUsername = res.getString("username");
                dbPassword = res.getString("password");
                dbBalance = Double.parseDouble(res.getString("balance"));
                dbEmail = res.getString("email");
                if (dbUsername.equals(username) && dbPassword.equals(password)) {
                    user = new User(dbId, username, password, dbEmail, dbBalance);
                    return user;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error");
        }
        return null;
    }

    
    /**
     * Denne funktion tager imod parametre og prøver at indsætte ny bruger i databasen Hvis det lykkedes returnere den true
     * @param username New username
     * @param password New password
     * @param email New email
     * @return True hvis bruger bliver oprettet, false hvis mislykkes
     */
    public boolean createUser(String username, String password, String email) {
        try {
            Connection c = new DBConnector().getConnection();
            Statement stmt = c.createStatement();
            String query = "INSERT INTO users "
                    + "VALUES (null, '" + username + "', '" + password + "', '" + email + "', '0');";
            int res = stmt.executeUpdate(query);
            return res != 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error");
        }
        return false;
    }
/**
 * Denne methode tager imod parameterne newBalance som er den nye balance for den user som er logget in på 
 * den kørende sessionen User parametere bruges til at få users på den kørende session og sammenligner users
 * id med DB for at få hans specifikke balance
 * 
 * @param newBalance Nye balance for user
 * @param user Session user
 */
    public void updateBalance(double newBalance, User user) {
        try {
            
            Connection c = DBConnector.getConnection();
            Statement stmt = c.createStatement();
            String query = "UPDATE Users "
                    + "SET balance = '" + newBalance + "' "
                    + "WHERE userid = " + user.getUserid() + ";";
            stmt.executeUpdate(query);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error");
        }
    }
///**
// * Denne methode tager imod et order object som bliver indsæt i databasen med de tilhørende orderlines knytter til
// * order objevtet Userernes id bliver tilknytter på order samt om bestilling er betalt, og tidspunktet orderne
// * er færdig gjordt
// * @param order Fulde ordre med orderlines
// * @param user Users 
// */
//    public void checkOutOrder(Order order, User user) {
//        try {
//            Connection c = new DBConnector().getConnection();
//            Statement stmt = c.createStatement();
//            Date d = new Date();
//            String query = "INSERT INTO Orders "
//                    + "VALUES (null, " + user.getUserid() + ", " + order.isIsPaid() +", " + d + ");";
//            int res = stmt.execute(query);
//            TODO Husk indsæt OrderLines!!! 
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            System.out.println("Error");
//        }
//    }
}
