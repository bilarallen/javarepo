import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class Options204 extends BaseClass{

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
    public void optionsReturnsCorrectMethodsList() throws IOException{
        String header = "Access-Control-Allow-Methods";
        String expectedReply = "GET, POST, PATCH, PUT, DELETE";

        HttpOptions request = new HttpOptions(BASE_ENDPOINT);
        response = client.execute(request);

        String actualValue = ResponseUtils.getHeader(response, header);

        Assert.assertEquals(actualValue, expectedReply);

    }
}
