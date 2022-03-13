# CZ3002-Chatbot-Backend

## Publish JAR file 
    1. File -> Project Structure -> Project Settings -> Artifacts -> Click green plus sign -> Jar -> From modules with dependencies
    2. Select Main Class
    3. Select extract to the target JAR
    4. replace "java" with "resources": ...CZ3002-Chatbot-Backend/demo/src/main/java -> ...CZ3002-Chatbot-Backend/demo/src/main/resources
    5. Build -> Build Artifacts
    6. Change output dirctory to demo folder

## Add JAR file in demo pom.xml
    1. <dependency>
            <groupId>cz3002.kopio</groupId>
            <artifactId>bot</artifactId>
            <version>1.0-SNAPSHOT</version>
            <scope>system</scope>
            <systemPath>${project.basedir}/lib/bot.jar</systemPath>
        </dependency>



## Open Swagger to view all available apis by entering <http://localhost:8080/swagger-ui/index.html>

## How to use bot Jar file
    1. import chatbot.Bot
    2. Bot bot = new Bot("0", /dir/to/data.xml,/dir/to/faq.xml,/dir/to/rating.xml);

## Get Faq
    1. Prepare faq.xml file
    Check template under demo/chatbotData/faq.xml
    Format as below:
    
    <Config>
    <Question id="1">
        faq1
    </Question>
    </Config>
    
    2. Execute getFAQ(int topRows) in Bot Class
        bot.getFAQ(10)
        It will return array of top 10 questiones. Change the id key value to determine sequence of top questiones
        
## Rate an answer
    1. rating.xml file template can be found under demo/chatbotData/rating.xml
    2. Execute rateAnswer(String ans, String rate) in Bot Class
        bot.rateAnswer("answer","5")
        
    It will save the record in rating.xml
    

