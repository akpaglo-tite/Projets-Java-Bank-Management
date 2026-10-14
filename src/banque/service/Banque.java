package banque.service;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import banque.model.CompteBancaire;
import banque.model.CompteEpargne;

public class Banque {

	private Map<String, CompteBancaire> comptes;
	
	public  Banque() {
		this.comptes = new HashMap<String, CompteBancaire>();
		chargerCompteParDefaut();
	}
	
	private void chargerCompteParDefaut() {
		CompteBancaire compteBancaire1 = new CompteBancaire("CC-101", 1500.0);
		CompteBancaire compteBancaire2 = new CompteBancaire("CC-303", 350.0);
		CompteEpargne compteEpargne = new CompteEpargne("CE-202", 5000.0, 0.04);
		comptes.put("CC-101", compteBancaire1);
		comptes.put("CE-202", compteEpargne);
		comptes.put("CC-303", compteBancaire2);
	}
	
	public void ajouterCompte(CompteBancaire compte) {
		this.comptes.put(compte.getNumeroCompte(), compte);
	}
	
	public CompteBancaire chercherCompte(String numeroCompte) {
		return comptes.get(numeroCompte);
	}
	
	public void listerComptesTriesParSolde() {
		List<CompteBancaire> comptesTries = new ArrayList<CompteBancaire>(comptes.values());
		comptesTries.sort(Comparator.comparingDouble(CompteBancaire::getSolde).reversed());
		for(CompteBancaire compteBancaire : comptesTries) {
			compteBancaire.afficherDetails();
		}
	}
	public Map<String, CompteBancaire> getComptes() {
		return comptes;
	}
}
