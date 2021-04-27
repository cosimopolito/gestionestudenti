package it.prova.gestionestudenti.service;


import java.util.List;

import it.prova.gestionestudenti.model.Aula;
import it.prova.gestionestudenti.model.Studente;

public interface StudenteService {

	public List<Studente> listAllAbitanti();

	public Studente caricaSingoloStudente(Long id);

	public void aggiorna(Studente abitanteInstance);

	public void inserisciNuovo(Studente abitanteInstance) throws Exception;

	public void rimuovi(Studente abitanteInstance);

	public List<Studente> findByExample(Studente example);
	
	public List<Studente> cercaAbitantiInAula(Aula input);
 
}
