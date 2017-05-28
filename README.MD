# MJML4j

A [MJML API](https://mjml.io/api) client for Java


### Support

This library currently supports V1 of the MJML API

### Basic Use

###### Create a client

```java
MJMLClient client = MJMLClient.newDefaultClient()
                              .withApplicationID("...")
                              .withAPIKey("...");
```

###### Render some MJML

 - Build a `RenderRequest` and call the client
 
```java
    RenderRequest request = new RenderRequest("<mjml>...<mjml>");
    RenderResponse response = client.render(request);
    String mjml = response.getMJML();
```

 - Handling API errors
 ```java
    RenderRequest request = new RenderRequest("<mjml>...<mjml>");
    try {
        RenderResponse response = client.render(request);
        String mjml = response.getMJML();
    } catch (MJMLClientErrorException c) {
        // Do something
        // For example
        // log.warn("Render error: " + c.getMessage() + ", request-id: " + c.getRequestId());
    } catch (MJMLServerErrorException s) {
        // Do something
        // For example
        // log.warn("Render error: " + s.getMessage() + ", request-id: " + s.getRequestId());
    } catch (MJMLException e) {
        // Do something
        // For example
        // log.warn("Render error: " + s.getMessage());
    }
 ```