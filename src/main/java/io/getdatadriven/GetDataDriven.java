package io.getdatadriven;


public class GetDataDriven {

	private GetDataDriven(){
	}

	public static EventsStream eventsStream(final ApiKey apiKey) {
		return new EventsStream(apiKey);
	}
}
