package io.getdatadriven;


public class EnvApiKey extends ApiKey {

	private static final String GETDATADRIVEN_API_KEY = "GETDATADRIVEN_API_KEY";

	public EnvApiKey(){
		super(System.getenv(GETDATADRIVEN_API_KEY));
	}
}
