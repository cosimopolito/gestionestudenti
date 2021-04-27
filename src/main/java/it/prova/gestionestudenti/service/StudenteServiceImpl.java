package it.prova.gestionestudenti.service;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestionestudenti.dao.StudenteDAO;
import it.prova.gestionestudenti.model.Aula;
import it.prova.gestionestudenti.model.Studente;

@Service
public class StudenteServiceImpl implements StudenteService {

	@PersistenceContext
 	private EntityManager entityManagery;
	@Autowired
	private StudenteDAO studenteDAO;

	@Transactional(readOnly = true)
	public List<Studente> listAllAbitanti() {
		return studenteDAO.list();
	}

	@Transactional(readOnly = true)
	public Studente caricaSingoloStudente(Long id) {
		return studenteDAO.get(id);
	}

	@Transactional
	public void aggiorna(Studente studenteInstance) {
		studenteDAO.update(studenteInstance);
	}

	@Transactional
	public void inserisciNuovo(Studente studenteInstance) throws Exception {
 		Aula aula = studenteInstance.getAula();
		if (aula!= null) {
			aula = entityManagery.merge(aula);
			if(aula.getCapienza().equals( aula.getStudenti().size()) ) {
				studenteDAO.insert(studenteInstance);
			}
			else {
				throw new Exception("capienza aula raggiunta");
			}
		}
	}

	@Transactional
	public void rimuovi(Studente studenteInstance) {
		studenteDAO.delete(studenteInstance);
	}

	@Transactional(readOnly = true)
	public List<Studente> findByExample(Studente example) {
		return studenteDAO.findByExample(example);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Studente> cercaAbitantiInAula(Aula input) {
		return studenteDAO.findAllByAula(input);
	}
  
}
