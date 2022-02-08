package run.dn5.notify.discord

import com.google.gson.Gson
import java.io.IOException
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class Webhook(private val url: String) {
    var content: String? = null
    var username: String? = null
    var avatarUrl: String? = null
    var tts = false
    val embeds: MutableList<EmbedObject> = ArrayList()

    @Throws(IOException::class)
    fun execute(): String? {
        if (content == null && embeds.isEmpty()) {
            throw IllegalArgumentException("Set content or add at least one EmbedObject")
        }
        val gson = Gson()
        val json = gson.toJson(this)

        val url = URL(url)
        val connection = url.openConnection() as HttpsURLConnection
        connection.addRequestProperty("Content-Type", "application/json")
        connection.addRequestProperty("User-Agent", "Java-DiscordWebhook-BY-Gelox_")
        connection.doOutput = true
        connection.requestMethod = "POST"
        val stream = connection.outputStream
        stream.write(json.toByteArray())
        stream.flush()
        stream.close()
        connection.inputStream.close()
        connection.disconnect()
        return connection.responseMessage
    }
}
