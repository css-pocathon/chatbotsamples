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

public class LuisSchadensmeldungGetRequest {
    public static void schadensmeldungAnalyse(String myquestion){
        System.out.println("---------- question: ------------------------------------");
        System.out.println(myquestion);
        HttpClient httpclient = HttpClients.createDefault();

        try {

            // The ID of a public sample LUIS app that recognizes intents for turning on and off lights
            String AppId = "b0379511-6144-4885-8051-fd46e50a05eb";
            // Add your subscription key
            // String SubscriptionKey = "YOUR-SUBSCRIPTION-KEY";
            String SubscriptionKey = "09b0903f75f24d76bd0f7567edc0dc3a";

            URIBuilder builder =
                    new URIBuilder("https://westus.api.cognitive.microsoft.com/luis/v2.0/apps/" + AppId + "?");

            // builder.setParameter("q", "Ich habe gestern ein Unfall mit meinem Fahrrad gehabt.");
            builder.setParameter("q",myquestion);

            URI uri = builder.build();
            HttpGet request = new HttpGet(uri);
            request.setHeader("Ocp-Apim-Subscription-Key", SubscriptionKey);

            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();


            if (entity != null) {
                // System.out.println(EntityUtils.toString(entity));

                String retSrc = EntityUtils.toString(entity);
                // System.out.println("retSrc");
                // System.out.println(retSrc);
                JSONArray result = ParseToJson.getJSonArrayFromEntityString(retSrc, "entities");
                // System.out.println("entities: ");
                // System.out.println(result.toString());
                XStream xstream = new XStream(new JettisonMappedXmlDriver());
                xstream.setMode(XStream.NO_REFERENCES);
                // System.out.println(xstream.toXML(result));

                for (Object myObject:result){
                    JSONObject myJsonObject = (JSONObject)myObject;
                    String myValue = myJsonObject.getString("entity");
                    String myType = myJsonObject.getString("type");
                    System.out.println("entity:" + myValue);
                    System.out.println("type: " + myType);
                }

              /*  String oQuery = ParseToJson.getValueFromString(retSrc, "query");
                System.out.println("Json element: ");
                System.out.println(oQuery.toString());*/

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("----------------------------------------------");
    }

    public static void main(String[] args) {

        schadensmeldungAnalyse("Ich habe gestern ein Unfall mit meinem Fahrrad gehabt.");
        schadensmeldungAnalyse("Beim fahren mit dem Velo habe ich mir das Bein gebrochen am 3. Januar 2017");
        schadensmeldungAnalyse("Ich habe gestern am 30. November ein Unfall gehabt, dabei habe ich mein Arm gebrochen und bin kaum noch in der Lage zu telefonieren");
        schadensmeldungAnalyse("Was für ein Pech: beim laufen bin ich von der Treppe gefallen und habe dabei beide Beine gebrochen");
        schadensmeldungAnalyse("Beim Weihnachtsbaum aufstellen habe ich bemerkt, dass ich ein allergie habe");
    }

}