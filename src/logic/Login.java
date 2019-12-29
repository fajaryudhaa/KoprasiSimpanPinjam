/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import koneksi.koneksiDB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author BIMBIMBUM
 */
public class Login {
    public Login(){
    
    }
    public Login(String user, String pass){
        this.user=user;
        this.pass=pass;
        
    }
    private String user;
    private String pass;
    private ResultSet rsLogin;
    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * @return the pass
     */
    public String getPass() {
        return pass;
    }

    /**
     * @param pass the pass to set
     */
    public void setPass(String pass) {
        this.pass = pass;
    }
   
    public boolean doLogin() {
        boolean b = false;
        String q1 = "select * from dt_user where username='"+user+
                    "' and pass='"+pass+"'";
        rsLogin = koneksiDB.getRS(q1);
        try {
            if (rsLogin.next()) {
                b = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        return b;
    }
}
