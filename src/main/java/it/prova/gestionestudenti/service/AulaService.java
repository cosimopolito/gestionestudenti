package it.prova.gestionestudenti.service;


import java.util.List;

 import it.prova.gestionestudenti.model.Aula;

public interface AulaService {

	public List<Aula> listAllAula();

	public Aula caricaSingolaAula(Long id);

	public Aula caricaSingolaAulaEagerAbitanti(Long idAula);

	public void aggiorna(Aula aulaInstance);

	public void inserisciNuovo(Aula aulaInstance);

	public void rimuovi(Aula aulaInstance) throws Exception;

	public List<Aula> findByExample(Aula example);
 
}
