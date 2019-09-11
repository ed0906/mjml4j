# MJML4j

A [MJML API](https://mjml.io/api) client for Java. 

You can use this library to communicate with the MJML REST Api and convert your pre-built MJML to a HTML email.


### Support

This library currently supports V1 of the MJML API

### Basic Use

###### Dependency

 - Gradle: In your `build.gradle` add the following:
 
```groovy
repositories {
    mavenCentral()
}

dependencies {
    compile 'io.camassia:mjml-client:1.0.0'
}
```

 - Maven: in your `pom.xml` add the following `repository` and `dependency` nodes
 
 ```xml
<project>
    <dependencies>
        <dependency>
            <groupId>io.camassia</groupId>
            <artifactId>mjml-client</artifactId>
            <version>1.0.0</version>
        </dependency>
    </dependencies>
</project>
```

###### Create a client

```java
MJMLClient client = MJMLClient.newDefaultClient()
                              .withApplicationID("...")
                              .withAPIKey("...");
```

You can get an `ApplicationID` and `APIKey` from https://mjml.io/api


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
 
 ### Building MJML Dynamically
 
 This library currently only supports the conversion of a pre-built MJML document to HTML.
 MJML document building is a work in progress but for now you can use libraries such as [Jdom2](http://o7planning.org/en/10149/java-jdom2-tutorial)/[Jsoup](http://howtodoinjava.com/jsoup/complete-jsoup-tutorial/) to build your MJML Document Object Model. You can then use this library to convert it to a HTML email.