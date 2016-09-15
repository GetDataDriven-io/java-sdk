package io.getdatadriven;


import net.sf.json.JSONObject;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.util.Scanner;

public class EventsStream {

	private final ApiKey apiKey;

	private final CloseableHttpClient httpClient = HttpClients.createDefault();


	EventsStream(final ApiKey apiKey) {
		Preconditions.checkNotNull(apiKey, "Api key is required.");
		Preconditions.checkValid(apiKey.isValid(), "Api key is not valid or is not set.");

		this.apiKey = apiKey;
	}

	public void put(final Event event) {
		Preconditions.checkNotNull(event, "Event is required.");

		try {
			final HttpPost post = new HttpPost(String.format("https://api.getdatadriven.io/events/%s", event.getName()));
			post.addHeader("Authorization", "Bearer " + apiKey.getKeyValue());
			post.setEntity(new StringEntity(event.jsonData(), ContentType.APPLICATION_JSON));
			try (final CloseableHttpResponse response = httpClient.execute(post)) {
				final int status = response.getStatusLine().getStatusCode();
				if (status >= 200 && status < 300) {
					return;
				}
				final Scanner scanner = new Scanner(response.getEntity().getContent()).useDelimiter("\\A");
				final String result = scanner.hasNext() ? scanner.next() : null;
				if (null == result) {
					throw new GetDataDrivenException("GetDataDriven.io error: " + status);
				}
				final JSONObject errorJson = JSONObject.fromObject(result);
				throw new GetDataDrivenException(String.format("GetDataDriven.io responded with %d error: '%s' (request ID: %s)", status, errorJson.getString("message"), errorJson.getString("request_id")));
			}
		} catch (final Exception ex) {
			if (ex instanceof GetDataDrivenException) {
				throw (GetDataDrivenException) ex;
			}
			throw new GetDataDrivenException("Cannot send event to GetDataDriven.io events stream", ex);
		}
	}
}
