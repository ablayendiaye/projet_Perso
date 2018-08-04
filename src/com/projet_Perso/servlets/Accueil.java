package com.projet_Perso.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.projet_Perso.beans.Membre;
import com.projet_Perso.dao.*;

/**
 * Servlet implementation class Test
 */
@WebServlet("/Test")
public class Accueil extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private MembreDao membreDao;

    public void init() throws ServletException {
        DaoFact daoFactory = DaoFact.getInstance();
        this.membreDao = daoFactory.getMembreDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setAttribute("utilisateurs", membreDao.lister());
        }
        catch (DaoException e) {
            request.setAttribute("erreur", e.getMessage());
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/Tontine.jsp").forward(request, response);
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        try {
            Membre membre = new Membre();
            membre.setNom(request.getParameter("nom"));
            membre.setPrenom(request.getParameter("prenom"));
            
            membreDao.ajouter(membre);
            request.setAttribute("utilisateurs", membreDao.lister());
        }
        catch (Exception e) {
            request.setAttribute("erreur", e.getMessage());
        }
        
        this.getServletContext().getRequestDispatcher("/WEB-INF/Tontine.jsp").forward(request, response);
    }
    
    

}
