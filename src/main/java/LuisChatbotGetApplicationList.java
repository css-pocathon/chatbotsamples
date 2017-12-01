import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.net.URI;

public class LuisChatbotGetApplicationList {
    public static void main(String[] args)
    {
        HttpClient httpclient = HttpClients.createDefault();

        try
        {

            // The ID of a public sample LUIS app that recognizes intents for turning on and off lights
            String AppId = "90713323-f412-483b-b466-b782fc7e9712";
            // Add your subscription key
            // String SubscriptionKey = "YOUR-SUBSCRIPTION-KEY";
            String SubscriptionKey = "15195fb1aab4450e9b626f8e4f210c78";


            URIBuilder builder =
                    new URIBuilder("https://westus.api.cognitive.microsoft.com/luis/api/v2.0/apps/?" );

            builder.setParameter("skip", "0");
            builder.setParameter("take", "100");

            URI uri = builder.build();
            HttpGet request = new HttpGet(uri);
            request.setHeader("Ocp-Apim-Subscription-Key", SubscriptionKey);

            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();


            if (entity != null)
            {
                System.out.println(EntityUtils.toString(entity));
            }
        }

        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
