package banque.model;

public class CompteEpargne extends CompteBancaire {
	private double tauxInteret;
	double nouveauSolde;
	public CompteEpargne(final String numeroCompte, final double solde,final double tauxInteret) {
		super(numeroCompte, solde);
		this.tauxInteret = tauxInteret;
	}
	
	public void calculerInterets() {
		double interet;
		interet = super.getSolde()*this.tauxInteret ;
		setSolde(getSolde() + interet);
		System.out.print("Intéret ajouté : "+interet + " nouveau solde : "+ getSolde());
	}
	
	@Override
	public void afficherDetails() {
		super.afficherDetails();
		System.out.print("Taux d'intéret : " + this.tauxInteret+"\n");
	}
	
	
}
