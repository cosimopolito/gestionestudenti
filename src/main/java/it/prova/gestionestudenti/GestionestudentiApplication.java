package it.prova.gestionestudenti;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GestionestudentiApplication implements CommandLineRunner {

	@Autowired
	private BatteriaDiTestService batteriaDiTestService;

	public static void main(String[] args) {
		SpringApplication.run(GestionestudentiApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		// DA VALORIZZARE SECONDO I CASI ESPOSTI NELLE COSTANTI SOPRA
		// ##########################################################
		String casoDaTestare = BatteriaDiTestService.RIMUOVI_AULA_E_STUDENTI;
		// ##########################################################

		System.out.println("################ START   #################");
		System.out.println("################ eseguo il test " + casoDaTestare + "  #################");

		batteriaDiTestService.eseguiBatteriaDiTest(casoDaTestare);

		System.out.println("################ FINE   #################");
	}

}
