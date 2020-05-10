import jdk.jfr.ContentType;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class DeleteAndPost extends BaseClass{
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
    public void deleteIsSuccessful () throws IOException{

        HttpDelete request  = new HttpDelete("htttps://api.github.com/repos/andrejs88/deleteme");
        response = client.execute(request);

        request.setHeader(HttpHeaders.AUTHORIZATION, "token"  );
        int actualStatusCode = response.getStatusLine().getStatusCode();
        Assert.assertEquals(actualStatusCode, 204);
    }

    @Test
    public void  createRepoReturns201() throws  IOException{
        //Create an HttpPost with a valid endpoint
        HttpPost request = new HttpPost("https://api.github.com/user/repos");
        //Set the Basic Auth Header
       // request.setHeader(HttpHeaders.AUTHORIZATION,);
        //Define Json to Post and set as Entity

        //Send
    }

