package banque.model;

public class Transaction {
	private String idTransaction;
	private String numeroCompte;
	private double montant;
	private TypeTransaction type;
	private boolean suspecte;
	
	public Transaction(final String idTransaction, final String numeroCompte, final double montant, final TypeTransaction type) {
		this.idTransaction = idTransaction;
		this.numeroCompte = numeroCompte;
		this.montant = montant;
		this.type = type;
		this.suspecte = false;
	}
	
	public String getIdTransaction() {
		return idTransaction;
	}
	public void setIdTransaction(String idTransaction) {
		this.idTransaction = idTransaction;
	}
	
	public String getNumeroCompte() {
		return numeroCompte;
	}
	public void setNumeroCompte(String numeroCompte) {
		this.numeroCompte = numeroCompte;
	}

	public double getMontant() {
		return montant;
	}
	public void setMontant(double montant) {
		this.montant = montant;
	}

	public TypeTransaction getType() {
		return type;
	}
	public void setType(TypeTransaction type) {
		this.type = type;
	}
	
	public boolean getSuspecte() {
		return suspecte;
	}
	public void setSuspecte(boolean suspecte) {
		this.suspecte = suspecte;
	}
	
	public void afficherDetails() {
		System.out.println("----------------------------------------");
		System.out.println("ID Transaction : " + this.idTransaction
				+ "\nNuméro de compte : " + this.numeroCompte
				+ "\nMontant : " + this.montant
				+ "\nLe type de transaction effectué : " + this.type
				+ "\nFraude : " + this.suspecte);
	}
}