import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.IOException;

import static org.testng.Assert.assertEquals;

public class Get200 extends BaseClass{

    //Declare closeable http client and response
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
    public void baseURlReturns200() throws IOException {
        //Declare variable "get" pointing BASE URL
        HttpGet get = new HttpGet(BASE_ENDPOINT);
        //Execute get variable through client
        client.execute(get);
        //Store client execution in response
        response = client.execute(get);
        //Declare variable to hold value of response status code
        int actualStatus =  response.getStatusLine().getStatusCode();
        assertEquals(actualStatus, 200);
    }

    @Test
    public void rateLimitReturns200() throws IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT+ "/rate_limit");

        client.execute(get);

         response = client.execute(get);

        int actualStatus =  response.getStatusLine().getStatusCode();
        assertEquals(actualStatus, 200);
    }

    @Test
    public void searchReposReturns200() throws IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT+ "/search/repositories?q=java");

        client.execute(get);

        response = client.execute(get);

        int actualStatus =  response.getStatusLine().getStatusCode();
        assertEquals(actualStatus, 200);
    }

}
