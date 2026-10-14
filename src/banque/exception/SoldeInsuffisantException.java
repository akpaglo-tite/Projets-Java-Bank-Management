package banque.exception;

public class SoldeInsuffisantException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public double montantRetrait;

	public SoldeInsuffisantException(double montantRetrait) {
		super("Solde insuffisant pour effectuer le retrait de " + montantRetrait + " €");
		this.montantRetrait = montantRetrait;
	}

}
