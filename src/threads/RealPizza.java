package threads;

public class RealPizza implements Pizza {
	private String realPizza;

	public RealPizza() {
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
		}
		System.out.println("A real pizza is made!" + Thread.currentThread().getId());
		realPizza = "REAL PIZZA! " + Thread.currentThread().getId();
	}

	public String getPizza() {
		return realPizza;
	}

	public String getPizza(long timeout) throws CasherTimeoutException {
		return realPizza;
	}
}
