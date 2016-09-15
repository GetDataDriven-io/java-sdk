package io.getdatadriven;


public abstract class ApiKey {

	private final String keyValue;

	ApiKey(final String keyValue) {
		this.keyValue = keyValue;
	}

	public String getKeyValue() {
		return keyValue;
	}

	boolean isValid() {
		return keyValue != null && keyValue.trim().length() > 3;
	}

}
