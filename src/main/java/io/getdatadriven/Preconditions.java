package io.getdatadriven;


final class Preconditions {

	static void checkNotNull(final Object value, final String message) {
		if(value == null) {
			throw new NullPointerException(message);
		}
	}

	static void checkValid(final boolean valid, final String message) {
		if(!valid) {
			throw new GetDataDrivenException(message);
		}

	}
}
