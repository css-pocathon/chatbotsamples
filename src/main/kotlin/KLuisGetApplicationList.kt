import org.apache.http.HttpEntity
import org.apache.http.HttpResponse
import org.apache.http.client.HttpClient
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.utils.URIBuilder
import org.apache.http.impl.client.HttpClients
import org.apache.http.util.EntityUtils

import java.net.URI

object KLuisGetApplicationList {
    @JvmStatic
    fun main(args: Array<String>) {
        val httpclient = HttpClients.createDefault()

        try {

            // The ID of a public sample LUIS app that recognizes intents for turning on and off lights
            val AppId = "df67dcdb-c37d-46af-88e1-8b97951ca1c2"
            // Add your subscription key
            // String SubscriptionKey = "YOUR-SUBSCRIPTION-KEY";
            val SubscriptionKey = "09b0903f75f24d76bd0f7567edc0dc3a"


            val builder = URIBuilder("https://westus.api.cognitive.microsoft.com/luis/api/v2.0/apps/?")

            builder.setParameter("skip", "0")
            builder.setParameter("take", "100")

            val uri = builder.build()
            val request = HttpGet(uri)
            request.setHeader("Ocp-Apim-Subscription-Key", SubscriptionKey)

            val response = httpclient.execute(request)
            val entity = response.entity


            if (entity != null) {
                println(EntityUtils.toString(entity))
            }
        } catch (e: Exception) {
            println(e.message)
        }

    }
}
