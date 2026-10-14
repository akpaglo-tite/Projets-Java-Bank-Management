package banque.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import banque.exception.SoldeInsuffisantException;
import banque.model.Transaction;
import banque.model.TypeTransaction;

public class GestionnaireTransaction {
	private Map<String, Double> soldeComptes;
	private List<Transaction> historiqueTransactions;
	Transaction transaction;
	
	public GestionnaireTransaction() {
		soldeComptes = new HashMap<String, Double>();
		historiqueTransactions = new ArrayList<Transaction>();
		chargerDonneesParDefaut();
	}

	private void chargerDonneesParDefaut() {
		this.soldeComptes.put("CC-101", 2000.0);
		this.soldeComptes.put("CC-202", 500.0);
	}

	public void traiterTransaction(String id, String numCompte, double montant, TypeTransaction type) throws SoldeInsuffisantException {
		if (!soldeComptes.containsKey(numCompte)) {
			System.out.println("Compte inexistant !");
			return;
		}

		double soldeActuel = soldeComptes.get(numCompte);

		// 1. Vérification du solde et mise à jour
		if (type == TypeTransaction.RETRAIT) {
			if (montant > soldeActuel) {
				throw new SoldeInsuffisantException(montant);
			}
			this.soldeComptes.put(numCompte, soldeActuel - montant);
		} else if (type == TypeTransaction.DEPOT) {
			this.soldeComptes.put(numCompte, soldeActuel + montant);
		}

		// 2. Instanciation de la transaction locale
		Transaction nouvelleTransaction = new Transaction(id, numCompte, montant, type);

		// 3. Règle 1 : Montant > 5000
		if (montant > 5000.0) {
			nouvelleTransaction.setSuspecte(true);
		}

		// 4. Règle 2 : Plus de 3 retraits
		int nombreTransactions = 0;
		for (Transaction historique : historiqueTransactions) {
			if (historique.getType() == TypeTransaction.RETRAIT && historique.getNumeroCompte().equals(numCompte)) {
				nombreTransactions++;
			}
		}
		if (nombreTransactions >= 3) {
			nouvelleTransaction.setSuspecte(true);
		}

		// 5. Enregistrement systématique dans l'historique
		this.historiqueTransactions.add(nouvelleTransaction);
		System.out.println("Transaction effectuée avec succès !");
	}
	
	public void afficherToutesLesTransactions() {
		if (historiqueTransactions.isEmpty()) {
			System.out.println("Aucune transaction enregistrée.");
			return;
		}
		for (Transaction compteTransaction : historiqueTransactions) {
			compteTransaction.afficherDetails();
		}
	}
	
	public void afficherAlertesFraude() {
		boolean alerteTrouvee = false;
		for (Transaction compte : historiqueTransactions) {
			if (compte.getSuspecte()) {
				compte.afficherDetails();
				alerteTrouvee = true;
			}
		}
		if (!alerteTrouvee) {
			System.out.println("Aucune alerte de fraude à afficher.");
		}
	}
	
	public double obtenirSolde(String numCompte) {
		if (this.soldeComptes.containsKey(numCompte)) {
			return this.soldeComptes.get(numCompte);
		}
		return -1.0;
	}
}