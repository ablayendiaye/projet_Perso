package com.projet_Perso.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoFact {
    private String url;
    private String username;
    private String password;

    DaoFact(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public static DaoFact getInstance() {
        try {
        	/*chargement du Driver jdbc*/
            Class.forName("com.mysql.jdbc.Driver");
        } 
        catch (ClassNotFoundException e) {

        }
        
        DaoFact instance = new DaoFact
        		(
                "jdbc:mysql://localhost:8080/tontine", "root", "917015AB");
        return instance;
    }

    public Connection getConnection() throws SQLException {
        Connection connexion = DriverManager.getConnection(url, username, password);
        connexion.setAutoCommit(false);
        return connexion; 
    }
 
    // Récupération du Dao
    public MembreDao getMembreDao() 
    	{
    		return new MembreDaoImpl(this);
    	}
}
