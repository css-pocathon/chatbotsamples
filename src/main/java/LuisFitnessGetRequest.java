// This sample uses the Apache HTTP client from HTTP Components (http://hc.apache.org/httpcomponents-client-ga/)

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;

public class LuisFitnessGetRequest {

    public static void main(String[] args) {
        HttpClient httpclient = HttpClients.createDefault();

        try {

            // The ID of a public sample LUIS app that recognizes intents for turning on and off lights
            String AppId = "88915016-5ffd-4ed1-8867-a4b4fcc5f104";
            // Add your subscription key 
            // String SubscriptionKey = "YOUR-SUBSCRIPTION-KEY";
            String SubscriptionKey = "09b0903f75f24d76bd0f7567edc0dc3a";

            URIBuilder builder =
                    new URIBuilder("https://westus.api.cognitive.microsoft.com/luis/v2.0/apps/" + AppId + "?");

            builder.setParameter("q", "Fitnesscenter in Luzern");
           /* builder.setParameter("timezoneOffset", "0");
            builder.setParameter("verbose", "false");
            builder.setParameter("spellCheck", "false");
            builder.setParameter("staging", "false");*/

            URI uri = builder.build();
            HttpGet request = new HttpGet(uri);
            request.setHeader("Ocp-Apim-Subscription-Key", SubscriptionKey);

            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();


            if (entity != null) {
                // System.out.println(EntityUtils.toString(entity));

                String retSrc = EntityUtils.toString(entity);
                System.out.println("retSrc");
                System.out.println(retSrc);
                JSONArray result = ParseToJson.getJSonArrayFromEntityString(retSrc, "entities");
                System.out.println("entities: ");
                // System.out.println(result.toString());
                XStream xstream = new XStream(new JettisonMappedXmlDriver());
                xstream.setMode(XStream.NO_REFERENCES);
                System.out.println(xstream.toXML(result));

                for (int i = 0; i < result.length(); i++)
                {
                    String entityName = result.getJSONObject(i).getString("entity");
                    System.out.println("entity:" + entityName);
                }

                for (Object myObject:result){
                    JSONObject myJsonObject = (JSONObject)myObject;
                    String myValue = myJsonObject.getString("entity");
                    System.out.println("entity: " + myValue);
                }

                String oQuery = ParseToJson.getValueFromString(retSrc, "query");
                System.out.println("Json element: ");
                System.out.println(oQuery.toString());

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}