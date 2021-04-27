package it.prova.gestionestudenti;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prova.gestionestudenti.model.Aula;
import it.prova.gestionestudenti.model.Studente;
import it.prova.gestionestudenti.service.AulaService;
import it.prova.gestionestudenti.service.StudenteService;

 

@Service
public class BatteriaDiTestService {

	@Autowired
	private AulaService aulaService;

	@Autowired
	private StudenteService studenteService;

 	public static final String INSERISCI_NUOVA_AULA = "INSERISCI_NUOVA_AULA";
	public static final String INSERISCI_STUDENTI_DATO_UN_AULA = "INSERISCI_STUDENTI_DATO_UN_AULA";
	public static final String CERCA_STUDENTE_DATO_ID_AULA = "CERCA_STUDENTE_DATO_ID_AULA";
	public static final String RIMUOVI_AULA_E_STUDENTI = "RIMUOVI_AULA_E_STUDENTI";
	public static final String ELENCA_TUTTE_LE_AULE = "ELENCA_TUTTE_LE_AULE";
	public static final String FIND_BY_EXAMPLE_BY_MATERIA = "FIND_BY_EXAMPLE_BY_MATERIA";
	public static final String UPDATE_STUDENTE_SET_NOME = "UPDATE_STUDENTE_SET_NOME";
	public static final String CARICA_AULA_EAGER = "CARICA_AULA_EAGER";
 	public static final String FIND_BY_EXAMPLE_BY_COGNOME_AND_NOME = "FIND_BY_EXAMPLE_BY_COGNOME_AND_NOME";

	public void eseguiBatteriaDiTest(String casoDaTestare) {
		try {
			switch (casoDaTestare) {
			case INSERISCI_NUOVA_AULA:
 				Aula nuovaAula = new Aula("A1","italiano",20);
				// salvo
				aulaService.inserisciNuovo(nuovaAula);
				System.out.println("Aula appena inserito: " + nuovaAula);
				break;
				 

			case INSERISCI_STUDENTI_DATO_UN_AULA:
				// / creo nuovo abitante
				Studente nuovoStudente = new Studente("cosimo", "polito", new SimpleDateFormat("yyyy-MM-dd").parse("22-02-2020"));
 				nuovoStudente.setAula(aulaService.caricaSingolaAula(1L));
				// salvo
				studenteService.inserisciNuovo(nuovoStudente);
				break;
				
			case CERCA_STUDENTE_DATO_ID_AULA:
				// stampo gli abitanti di un determinato municipio
				System.out.println(
						studenteService.cercaAbitantiInAula(aulaService.caricaSingolaAula(1L)));
				break;

			case RIMUOVI_AULA_E_STUDENTI:
  				aulaService.rimuovi(aulaService.caricaSingolaAula(1L));
				break;

		 
			case ELENCA_TUTTE_LE_AULE:
				// elencare i municipi
				System.out.println("Elenco le aule:");
				for (Aula aulaItem : aulaService.listAllAula()) {
					System.out.println(aulaItem);
				}
				break;

			case FIND_BY_EXAMPLE_BY_MATERIA:
				System.out.println("########### EXAMPLE ########################");
				// find by example: voglio ricercare i municipi con
				// ubicazione'Via dei Grandi'
				Aula aulaExample = new Aula();
				aulaExample.setMateria("Italiano");
				for (Aula aulaItem : aulaService.findByExample(aulaExample)) {
					System.out.println(aulaItem);
				}
				break;
 
			case FIND_BY_EXAMPLE_BY_COGNOME_AND_NOME:
				System.out.println("########### EXAMPLE ########################");
				// find by example: voglio ricercare le persone di cognome Rossi
				Studente studenteExample2 = new Studente();
				studenteExample2.setCognome("Rossi");
				studenteExample2.setNome("Mario");
				for (Studente studenteItem : studenteService.findByExample(studenteExample2)) {
					System.out.println(studenteItem);
				}
				break;

			case UPDATE_STUDENTE_SET_NOME:
				// carico un abitante e cambio eta
				Studente studenteEsistente = studenteService.caricaSingoloStudente(2L);
				if (studenteEsistente != null) {
					studenteEsistente.setNome("Mario");
					studenteService.aggiorna(studenteEsistente);
				}
				break;

			case CARICA_AULA_EAGER:
				 
				Aula aulaACaso = aulaService.caricaSingolaAulaEagerAbitanti(2L);
				if (aulaACaso != null) {
					for (Studente studenteItem : aulaACaso.getStudenti()) {
						System.out.println(studenteItem);
					}
				}
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
