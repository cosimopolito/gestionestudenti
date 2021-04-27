package it.prova.gestionestudenti.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map; 
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery; 
import org.apache.commons.lang3.StringUtils; 
import org.springframework.stereotype.Component; 
import it.prova.gestionestudenti.model.Aula;
import it.prova.gestionestudenti.model.Studente;
 

@Component
public class StudenteDAOImpl implements StudenteDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Studente> list() {
		return entityManager.createQuery("from Studente",Studente.class).getResultList();
	}

	@Override
	public Studente get(Long id) {
		return entityManager.find(Studente.class, id);
	}

	@Override
	public void update(Studente abitanteInstance) {
		abitanteInstance = entityManager.merge(abitanteInstance);
	}

	@Override
	public void insert(Studente abitanteInstance) {
		entityManager.persist(abitanteInstance);
	}

	@Override
	public void delete(Studente abitanteInstance) {
		entityManager.remove(entityManager.merge(abitanteInstance));
	}

	@Override
	public List<Studente> findByExample(Studente example) {
		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();

		StringBuilder queryBuilder = new StringBuilder("select s from Studente s where s.id = s.id ");

		if (StringUtils.isNotEmpty(example.getNome())) {
			whereClauses.add(" s.nome  like :nome ");
			paramaterMap.put("nome", "%" + example.getNome() + "%");
		}
		if (StringUtils.isNotEmpty(example.getCognome())) {
			whereClauses.add(" s.cognome like :cognome ");
			paramaterMap.put("cognome", "%" + example.getCognome() + "%");
		}
		if (example.getAula() != null) {
			whereClauses.add(" s.aula_id = :aulaId ");
			paramaterMap.put("aulaId" , example.getAula().getId()  );
		}
		if (example.getDataNascita() != null) {
			whereClauses.add(" s.data_nascita = :dataNascita ");
			paramaterMap.put("dataNascita" , example.getDataNascita()  );
		}
		
		queryBuilder.append(!whereClauses.isEmpty()?" and ":"");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));
		TypedQuery<Studente> typedQuery = entityManager.createQuery(queryBuilder.toString(), Studente.class);

		for (String key : paramaterMap.keySet()) {
			typedQuery.setParameter(key, paramaterMap.get(key));
		}

		return typedQuery.getResultList();
	}

	@Override
	public List<Studente> findAllByAula(Aula input) {
		TypedQuery<Studente> query = entityManager.createQuery(
				"select a FROM Studente a JOIN FETCH a.aula where a.aula =:aulaInput", Studente.class);
		return query.setParameter("aulaInput", input).getResultList();
	}
 
 

}
