package banque;


import java.util.Scanner;

import banque.model.CompteBancaire;
import banque.model.CompteEpargne;
import banque.service.Banque;

public class Main {

	public static void main(String[] args) {
		Banque banque =  new Banque();
		
		String choix = "1";
		double montant;
		String numCompte;
		Scanner scanner = new Scanner(System.in);
		
		while (!choix.equals("6")) {
			System.out.print("=== MENU BANQUAIRE INTERACTIF === \n1"
					+ " Afficher tous les comptes (triés par solde)\n"
					+ "2. Chercher un compte par son numéro\n"
					+ "3. Effectuer un dépôt\n"
					+ "4. Effectuer un retrait\n"
					+ "5. Appliquer les intérêts (sur les comptes épargne\n"
					+ "6. Quitter\n");
			
			System.out.print("Entrer votre choix : ");
			choix = scanner.nextLine();
			switch (choix) {
			case "1": {
				banque.listerComptesTriesParSolde();
				break;
			}
			case "2": {
				System.out.print("Entrer un numéro de compte : ");
				numCompte = scanner.nextLine();
				CompteBancaire compteTrouve = banque.chercherCompte(numCompte); 
				if(compteTrouve != null) {
					compteTrouve.afficherDetails();
				}else {
					System.out.print("Aucun compte trouvé avec ce numéro.");
				}
				break;
			}
			case "3": {
				System.out.print("Entrer votre numéro de compte : ");
				numCompte = scanner.nextLine();
				CompteBancaire compteTrouve = banque.chercherCompte(numCompte);
				if (compteTrouve != null) {
					System.out.print("Entrer le montant à déposer : ");
					montant = scanner.nextDouble();
					scanner.nextLine();
					compteTrouve.deposer(montant);
					System.out.print("Dépôt effectué avec succès\n");
					
				} else {
					System.out.print("Compte inexistant ");
				}
				break;
			}
			case "4": {
				System.out.print("Entrer votre numéro de compte : ");
				numCompte = scanner.nextLine();
				
				CompteBancaire compteTrouve = banque.chercherCompte(numCompte);
				if (compteTrouve != null) {
					System.out.print("Entrer le montant à retirer : ");
					montant = scanner.nextDouble();
					scanner.nextLine();
		
					if(compteTrouve.retirer(montant)) {
						System.out.print("Retrait effectué avec succès.");
					}
					
				} else {
					System.out.print("Compte inexistant.");

				}
				break;
			}
			case "5": {
				for(CompteBancaire compte : banque.getComptes().values()) {
					if (compte instanceof CompteEpargne) {
						CompteEpargne epargne = (CompteEpargne) compte;
						System.out.print("Compte : " + epargne.getNumeroCompte() + "-" );
						epargne.calculerInterets();
					} 
				}
				break;
			}
			case "6": {
				System.out.print("Vous venez de terminer votre opération\n");
				break;
			}
			default:
				System.out.print("Vous avez tapé un mauvais choix\n");
			}
		}
		scanner.close();
	}
}
