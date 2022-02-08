package run.dn5.notify.futures.inp

import run.dn5.notify.Common
import run.dn5.notify.discord.EmbedObject
import run.dn5.notify.discord.Webhook
import java.nio.file.Path
import java.util.*

class InformNewPlayer(
    private val common: Common
) {

    private val directory = Path.of("${this.common.dataFolder}/InformNewPlayer")
    private val cache = Cache(directory)

    init {
        if(!directory.toFile().exists()) directory.toFile().mkdir()
    }

    fun execute(username: String, uuid: UUID, address: String){
        val config = this.common.getConfig()
        if(config.webhook.endsWith("example")) return
        if(cache.exists(uuid.toString())) return
        cache.add(uuid.toString())
        val webhook = Webhook(config.webhook)
        val embed = EmbedObject()
        embed.title = "New player joined!"
        embed.description = "新しいプレイヤーが参加しました。"
        embed.setImage("https://crafatar.com/avatars/${uuid}")
        embed.addField("Name", username, false)
            .addField("UUID", uuid.toString(),false)
            .addField("IP", address, false)
        webhook.embeds.add(embed)
        webhook.execute()
    }
}