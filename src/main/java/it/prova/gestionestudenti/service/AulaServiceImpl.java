package it.prova.gestionestudenti.service;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import it.prova.gestionestudenti.dao.AulaDAO;
import it.prova.gestionestudenti.model.Aula;

@Service
public class AulaServiceImpl implements AulaService {
	@PersistenceContext
 	private EntityManager entityManagery;
	@Autowired
	private AulaDAO aulaDAO;

	@Transactional(readOnly = true)
	public List<Aula> listAllAula() {
		return aulaDAO.list();
	}
	@Transactional(readOnly = true)
	public Aula caricaSingolaAula(Long id) {
		return aulaDAO.get(id);
	}
 

	@Transactional
	public void aggiorna(Aula aulaInstance) {
		aulaDAO.update(aulaInstance);
	}

	@Transactional
	public void inserisciNuovo(Aula aulaInstance) {
		aulaDAO.insert(aulaInstance);
	}

	@Transactional
	public void rimuovi(Aula aulaInstance) throws Exception {
		aulaInstance = entityManagery.merge(aulaInstance);
		if (aulaInstance.getStudenti().size() > 0 ){
			throw new Exception("aula non eliminabile");
		}
		aulaDAO.delete(aulaInstance);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Aula> findByExample(Aula example) {
		return aulaDAO.findByExample(example);
	}

	@Override
	@Transactional(readOnly = true)
	public Aula caricaSingolaAulaEagerAbitanti(Long idAula) {
		return aulaDAO.findEagerFetch(idAula); 
	}
 
}
