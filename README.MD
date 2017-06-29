# MJML4j

A [MJML API](https://mjml.io/api) client for Java


### Support

This library currently supports V1 of the MJML API

### Basic Use

###### Dependency

 - Gradle: In your `build.gradle` add the following:
 
```groovy
repositories {
    mavenCentral()

    maven {
        url "http://nexus-ed0906.rhcloud.com/nexus/content/groups/public/"
    }
}

dependencies {
    compile 'ifusion:mjml-client:0.1.2'
}
```

 - Maven: in your `pom.xml` add the following `repository` and `dependency` nodes
 
 ```xml
<project>
    <repositories>
        <repository>
          <id>mjml-client-repo</id>
          <url>http://nexus-ed0906.rhcloud.com/nexus/content/groups/public/</url>
        </repository>
    </repositories>
    <dependencies>
        <dependency>
            <groupId>ifusion</groupId>
            <artifactId>mjml-client</artifactId>
            <version>0.1.2</version>
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