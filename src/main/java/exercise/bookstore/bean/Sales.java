package exercise.bookstore.bean;

public class Sales {
	
	private final String printed;
	private final String amount;
	
	public Sales(String printed, String amount) {
		super();
		this.printed = printed;
		this.amount = amount;
	}

	public String getPrinted() {
		return printed;
	}

	public String getAmount() {
		return amount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((printed == null) ? 0 : printed.hashCode());
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sales other = (Sales) obj;
		if (printed == null) {
			if (other.printed != null)
				return false;
		} else if (!printed.equals(other.printed))
			return false;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Sales [printed=");
		builder.append(printed);
		builder.append(", amount=");
		builder.append(amount);
		builder.append("]");
		return builder.toString();
	}
	
	

}
