package logic.entity;

public class User {
private String username, password, email;
private int userid;
private double balance;

    public String getUsername() {
        return username;
    }
    
    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public int getUserid() {
        return userid;
    }

    public double getBalance() {
        return balance;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(int userid, String username, String password, String email, double balance) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.userid = userid;
        this.balance = balance;
    }
    
}
