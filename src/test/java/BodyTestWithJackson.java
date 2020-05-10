import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.NotFound;
import entities.RateLimit;
import entities.User;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertEquals;

public class BodyTestWithJackson extends BaseClass {

    CloseableHttpClient client;
    CloseableHttpResponse response;

    //Annotate client before each test
    @BeforeMethod
    public void setup(){
        client  = HttpClientBuilder.create().build();
    }
    //Annotate client and response closure of connection after each test
    @AfterMethod
    public void closeResource() throws IOException {
        client.close();
        response.close();
    }


    @Test
    public void returnsCorrectLogin() throws IOException{
        HttpGet get = new HttpGet(BASE_ENDPOINT + "/users/mojombo");

        response = client.execute(get);

        User user = ResponseUtils.unmarshallGeneric(response, User.class);
        assertEquals(user.getLogin(), "mojombo");
        
    }

    @Test
    public void returnsCorrectId() throws IOException{
        HttpGet get = new HttpGet(BASE_ENDPOINT + "/users/mojombo");

        response = client.execute(get);

        User user = ResponseUtils.unmarshallGeneric(response, User.class);
        assertEquals(user.getId(), 1);
    }

    @Test
    public void notFoundMessageIsCorrect() throws IOException{
        HttpGet get = new HttpGet(BASE_ENDPOINT + "/nonexistingendpoint");

        response = client.execute(get);

       NotFound notFoundMessage = ResponseUtils.unmarshallGeneric(response, NotFound.class);


        assertEquals(notFoundMessage.getMessage(), "Not Found");
    }

    @Test
    public void correctRateLimitAreSet() throws IOException{
        HttpGet get = new HttpGet(BASE_ENDPOINT + "/rate_limit");

        response = client.execute(get);

        RateLimit ratelimits = ResponseUtils.unmarshallGeneric(response, RateLimit.class);


        assertEquals(ratelimits.getCoreLimit(), 60);
        assertEquals(ratelimits.getSearchLimit(),"10");
    }



}
