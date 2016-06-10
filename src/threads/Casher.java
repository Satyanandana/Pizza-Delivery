package threads;

public class Casher {

	public Pizza order() {
		Future future = new Future();
		System.out.println("An order is made.");
		Thread t = new Thread(() -> {
			RealPizza realPizza = new RealPizza();
			try {
				Thread.sleep(2000);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			future.setRealPizza(realPizza);
		});
		t.start();
		return future;
	}

	public static void main(String[] args) throws CasherTimeoutException {
		Casher casher = new Casher();
		System.out.println("Ordering pizzas at a casher counter.");
		Pizza p1 = casher.order();
		Pizza p2 = casher.order();
		Pizza p3 = casher.order();

		System.out.println("Doing something, reading newspapers, magazines, etc., " + "until pizzas are ready to pick up...");
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
		}

		System.out.println("Let's see if pizzas are ready to pick up...");
		System.out.println(p1.getPizza());
		System.out.println(p2.getPizza(100));
		System.out.println(p3.getPizza());
	}
}
