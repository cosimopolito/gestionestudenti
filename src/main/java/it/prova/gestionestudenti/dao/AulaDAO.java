package it.prova.gestionestudenti.dao;

import it.prova.gestionestudenti.model.Aula;

public interface AulaDAO extends IBaseDAO<Aula> {
	public Aula findEagerFetch(long idAula);
 }
