# CZ3002-Chatbot-Backend

## Publish JAR file 
    1. File -> Project Structure -> Project Settings -> Artifacts -> Click green plus sign -> Jar -> From modules with dependencies
    2. Select Main Class
    3. Select extract to the target JAR
    4. replace "java" with "resources": ...CZ3002-Chatbot-Backend/demo/src/main/java -> ...CZ3002-Chatbot-Backend/demo/src/main/resources
    5. Build -> Build Artifacts
    6. Change output dirctory to demo folder

## Add JAR file in demo pom.xml
    1. `<dependencies>
        <dependency>
            <groupId>commons-httpclient</groupId>
            <artifactId>commons-httpclient</artifactId>
            <version>3.1</version>
        </dependency>
    </dependencies>`



## Open Swagger to view all available apis by entering <http://localhost:8080/swagger-ui/index.html>