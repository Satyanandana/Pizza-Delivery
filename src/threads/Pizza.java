package threads;

public interface Pizza {
	public abstract String getPizza();

	public abstract String getPizza(long timeout) throws CasherTimeoutException;
}
