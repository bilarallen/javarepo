
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static entities.User.ID;
import static entities.User.LOGIN;

public class BodyTestWithSimpleMap extends BaseClass{

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
    public void returnsCorrectLogin() throws IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT + "/users/mojombo");

       response = client.execute(get);

     String jsonBody = EntityUtils.toString(response.getEntity());

        JSONObject jsonObject = new JSONObject(jsonBody);

      String loginValue = (String)  getValueFor(jsonObject, LOGIN);

        Assert.assertEquals(loginValue, "mojombo");

    }

    @Test
    public void returnsCorrectId() throws IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT + "/users/mojombo");

        response = client.execute(get);

        String jsonBody = EntityUtils.toString(response.getEntity());

        JSONObject jsonObject = new JSONObject(jsonBody);

        Integer loginValue = (Integer)  getValueFor(jsonObject, ID);

        Assert.assertEquals(loginValue, Integer.valueOf(1));

    }

    private Object getValueFor(JSONObject jsonObject, String key) {
        return jsonObject.get(key);

    }

}
