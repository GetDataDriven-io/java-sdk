This is [GetDataDriven.io](http://getdatadriven.io) SDK for Java.

# Usage

## Streaming events

```java

final ApiKey apiKey = new BasicApiKey("your-api-key-goes-here");
// Instead of BasicApiKey you can also use EnvApiKey which reads api key from GETDATADRIVEN_API_KEY env variable

final EventsStream eventsStream = GetDataDriven.eventsStream(apiKey);

eventsStream.put(
        new Event("order_places")
                .with("customerId", "assdsd-qwqwqwqw-dsdsdsd")
                .with("basketValue", 2143.23)
);
		
```
