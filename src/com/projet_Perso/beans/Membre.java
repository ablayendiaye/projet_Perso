package com.projet_Perso.beans;

public class Membre {
    private String nom;
    private String prenom;
    
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) throws BeanException {
        if (nom.length() > 10) {
            throw new BeanException("Le nom est trop grand ! (15 caractères maximum)");
        }
        else {
            this.nom = nom; 
        }
    }
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}