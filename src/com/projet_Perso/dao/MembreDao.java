package com.projet_Perso.dao;
import java.util.List;

import com.projet_Perso.beans.Membre;

public interface MembreDao {
	void ajouter( Membre membre) throws DaoException;

	List<Membre> lister() throws DaoException;

}
