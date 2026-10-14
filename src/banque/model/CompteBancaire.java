package banque.model;

public class CompteBancaire {

	private String numeroCompte;
	private double solde;
	public CompteBancaire(final String numeroCompte, final double solde) {
		this.numeroCompte = numeroCompte;
		this.solde = solde;
	}
	
	public String getNumeroCompte() {
		return numeroCompte;
	}
	public void setNumeroCompte(String numeroCompte) {
		this.numeroCompte = numeroCompte;
	}
	
	public double getSolde() {
		return solde;
	}
	public void setSolde(double solde) {
		this.solde = solde;
	}
	
	public void deposer(double montant) {
		if (montant > 0) {
			this.solde += montant;
		}else {
			System.out.println("Votre montant à ajouter est inférieur ou égal à 0");
		}
	}
	
	public boolean retirer(double montant) {
		boolean verifier = false;
		if(this.solde >= montant) {
			this.solde -= montant;
			verifier = true; 
		}else {
			System.out.println("Votre compte est insuffisant !");
		}
		return verifier;
	}
	
	public void afficherDetails() {
		System.out.println("Numéro de compte :" +this.numeroCompte+"\n Solde du compte : "+this.solde);
	}
}
