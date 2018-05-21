package errors.impl;

public class MyError implements errors.GenericError {
	
	private final String description;

	public MyError(String description) {
		super();
		this.description = description;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		return "MyError [description=" + description + "]";
	}

}
