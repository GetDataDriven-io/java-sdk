package io.getdatadriven;


public class GetDataDrivenException extends RuntimeException {

	GetDataDrivenException(final String message) {
		super(message);
	}

	GetDataDrivenException(final String message, final Throwable ex) {
		super(message, ex);
	}
}
