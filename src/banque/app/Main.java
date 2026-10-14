package banque.app;

import java.util.Scanner;

import banque.exception.SoldeInsuffisantException;
import banque.model.Transaction;
import banque.model.TypeTransaction;
import banque.service.GestionnaireTransaction;

public class Main {

	public static void main(String[] args) {
		
		GestionnaireTransaction gestionnaireTransaction = new GestionnaireTransaction();
		Transaction transaction = new Transaction("", "", 0.0, TypeTransaction.DEPOT);
		String choix = "1";
		String typeString;
		Scanner scanner = new Scanner(System.in);
		
		while (!choix.equals("5")) {
			
			System.out.print("\n=== SYSTEME DE DETECTION DE FRAUDE ET TRANSACTION ===\n"
					+ "1. Effectuer une transaction (Dépôt / Retrait)\n"
					+ "2. Consulter le solde d'un compte\n"
					+ "3. Afficher l'historique complet des transactions\n"
					+ "4. Afficher le rapport des alertes de FRAUDE\n"
					+ "5. Quitter\n");
			System.out.print("Entrer votre choix : ");
			choix = scanner.nextLine();

			switch (choix) {
			case "1": {
				try {
					System.out.print("Entrer votre identifiant : ");
					transaction.setIdTransaction(scanner.nextLine());

					System.out.print("Entrer votre numéro de compte : ");
					transaction.setNumeroCompte(scanner.nextLine());

					System.out.print("Entrer le montant à déposer ou à retirer : ");
					transaction.setMontant(scanner.nextDouble());
					scanner.nextLine(); // Nettoyage du tampon

					System.out.print("Entrer le type de transaction (DEPOT / RETRAIT) : ");
					typeString = scanner.nextLine();
					transaction.setType(TypeTransaction.valueOf(typeString.toUpperCase().trim()));

					// Transmission correcte : id, numCompte, montant, type
					gestionnaireTransaction.traiterTransaction(
						transaction.getIdTransaction(), 
						transaction.getNumeroCompte(), 
						transaction.getMontant(), 
						transaction.getType()
					);

				} catch (SoldeInsuffisantException e) {
					System.out.println("Erreur : " + e.getMessage());
				} catch (IllegalArgumentException e) {
					System.out.println("Type invalide ! Veuillez saisir 'DEPOT' ou 'RETRAIT'.");
				}
				break;
			}
			case "2": {
				System.out.print("Entrer le numéro de compte à consulter : ");
				transaction.setNumeroCompte(scanner.nextLine());
				double solde = gestionnaireTransaction.obtenirSolde(transaction.getNumeroCompte());
				if (solde != -1.0) {
					System.out.println("Solde actuel du compte " + transaction.getNumeroCompte() + " : " + solde + " €");
				} else {
					System.out.println("Compte introuvable.");
				}
				break;
			}
			case "3": {
				gestionnaireTransaction.afficherToutesLesTransactions();
				break;
			}
			case "4": {
				gestionnaireTransaction.afficherAlertesFraude();
				break;
			}
			case "5": {
				System.out.println("Vous venez de terminer votre opération.");
				break;
			}
			default:
				System.out.println("Opération inconnue !");
			}
		}
	
		scanner.close();
	}
}