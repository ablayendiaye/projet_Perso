package com.projet_Perso.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.projet_Perso.beans.BeanException;
import com.projet_Perso.beans.Membre;

public class MembreDaoImpl implements MembreDao {
    private DaoFact daoFactory;

    MembreDaoImpl(DaoFact daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void ajouter(Membre membre) throws DaoException {
        Connection connexion = null;
        
        PreparedStatement preparedStatement = null;

        try {//on etabli la connection
            connexion = daoFactory.getConnection();
            
            //on cree les requetes préparées avec preparedStatement
            preparedStatement = connexion.prepareStatement("INSERT INTO participant(nom, prenom) VALUES(?, ?);");
            preparedStatement.setString(1, membre.getNom());
            preparedStatement.setString(2, membre.getPrenom());

            preparedStatement.executeUpdate();
            connexion.commit();
        } catch (SQLException e) {
            try {
                if (connexion != null) {
                    connexion.rollback();
                }
            } catch (SQLException e2) {
            }
            throw new DaoException("Impossible de communiquer avec la base de données ou donnée duppliée");
        }
        finally {
            try {
                if (connexion != null) {
                    connexion.close();  
                }
            } catch (SQLException e) {
                throw new DaoException("Impossible de communiquer avec la base de données");
            }
        }

    }

    @Override
    public List<Membre> lister() throws DaoException {
        List<Membre> utilisateurs = new ArrayList<>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery("SELECT nom, prenom FROM participant;");

            while (resultat.next()) {
                String nom = resultat.getString("nom");
                String prenom = resultat.getString("prenom");

                Membre membre = new Membre();
                membre.setNom(nom);
                membre.setPrenom(prenom);

                utilisateurs.add(membre);
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de communiquer avec la base de données");
        } catch (BeanException e) {
            throw new DaoException("Les données de la base sont invalides");
        }
        finally {
            try {
                if (connexion != null) {
                    connexion.close();  
                }
            } catch (SQLException e) {
                throw new DaoException("Impossible de communiquer avec la base de données");
            }
        }
        return utilisateurs;
    }

}

