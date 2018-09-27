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
import java.util.List;
import logic.entity.Bottom;
import logic.entity.Order;
import logic.entity.Topping;
import logic.entity.User;

/**
 *
 * @author Simon
 */
public class DataMapper {
    public ArrayList<Topping> getToppings() {
        ArrayList<Topping> l = new ArrayList();
        try {
            Connection c = new DBConnector().getConnection();
            Statement stmt = c.createStatement();
            String query
                    = "SELECT `tname`,`tprice` "
                    + "FROM `toppings`;";
            ResultSet res = stmt.executeQuery(query);
            while(res.next()) {
                Topping tp = new Topping(res.getString("tname"), res.getDouble("tprice"));
                l.add(tp);
            }
            return l;
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error");
        }
        return null;
    }
    
    public ArrayList<Bottom> getBottoms() {
        ArrayList<Bottom> b = new ArrayList();
        try {
            Connection c = new DBConnector().getConnection();
            Statement stmt = c.createStatement();
            String query
                    = "SELECT `bname`,`bprice` "
                    + "FROM `bottoms`;";
            ResultSet res = stmt.executeQuery(query);
            while (res.next()) {
                Bottom bt = new Bottom(res.getString("bname"), res.getDouble("bprice"));
                b.add(bt);
            }
            return b;
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

    public List<Order> getPreviouseInvoices(int orderId, int userId, boolean isPaid, String orderDate) {
        ArrayList<Order> oArray = new ArrayList();
        try {
            Connection c = new DBConnector().getConnection();
            Statement stmt = c.createStatement();
            String query
                    = "SELECT *\n"
                    + "FROM orders;";
            ResultSet res = stmt.executeQuery(query);
            while (res.next()) {
                Order o = new Order(res.getInt("orderid"), res.getInt("userid"), res.getBoolean("ispaid"), res.getString(orderDate));
                oArray.add(o);
            }
            return oArray;
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error");
        }
        return null;
    }

    //Denne funktion tager imod username og password strenge når en person prøver at logge ind. 
    //Den sammenligner så med dataen i vores users table i databasen, og returner til sidst et User Objekt.
    public User authenticateLogin(String username, String password) {
        String query;
        String dbUsername, dbPassword;
        User user;
        try {
            Connection c = new DBConnector().getConnection();
            Statement stmt = c.createStatement();
            query = "SELECT username, password FROM users;";
            ResultSet res = stmt.executeQuery(query);
            while (res.next()) {
                dbUsername = res.getString("username");
                dbPassword = res.getString("password");
                if (dbUsername.equals(username) && dbPassword.equals(password)) {
                    user = new User(username, password);
                    return user;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error");
        }
        return null;
    }

    //Denne funktion tager imod parametre og prøver at indsætte ny bruger i databasen. Hvis det lykkedes returnere den true.
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
    
    
}
