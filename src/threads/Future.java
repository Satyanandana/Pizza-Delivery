package threads;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Future implements Pizza {
	private RealPizza realPizza = null;
	private ReentrantLock lock;
	private Condition ready;

	public Future() {
		lock = new ReentrantLock();
		ready = lock.newCondition();
	}

	public void setRealPizza(RealPizza real) {
		lock.lock();
		try {
			if (realPizza != null) {
				return;
			}
			realPizza = real;
			ready.signalAll();
		} finally {
			lock.unlock();
		}
	}

	public String getPizza() {
		String pizzaData = null;
		lock.lock();
		try {
			if (realPizza == null) {
				ready.await();
			}
			pizzaData = realPizza.getPizza();
		} catch (InterruptedException e) {
		} finally {
			lock.unlock();
		}
		return pizzaData;
	}

	public String getPizza(long timeout) throws CasherTimeoutException {
		String pizzaData = null;
		lock.lock();
		try {
			if (realPizza != null) {
				pizzaData = realPizza.getPizza();
				return pizzaData;
			}
			ready.await(timeout, TimeUnit.MILLISECONDS);
			if (realPizza != null) {
				pizzaData = realPizza.getPizza();
				return pizzaData;
			} else {
				throw new CasherTimeoutException();
			}
		} catch (InterruptedException e) {
		} finally {
			lock.unlock();
		}
		return pizzaData;
	}

	public boolean isReady() {
		boolean isReady = false;
		lock.lock();
		try {
			if (realPizza != null) {
				isReady = true;
			}
		}

		finally {
			lock.unlock();
		}
		return isReady;
	}
}
