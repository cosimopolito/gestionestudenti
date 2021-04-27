package it.prova.gestionestudenti.dao;

import java.util.List;

import it.prova.gestionestudenti.model.Aula;
import it.prova.gestionestudenti.model.Studente;

public interface StudenteDAO extends IBaseDAO<Studente> {

	public List<Studente> findAllByAula(Aula input);

}
