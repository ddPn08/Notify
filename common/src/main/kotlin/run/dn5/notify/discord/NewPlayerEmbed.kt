package run.dn5.notify.discord

import java.util.*

class NewPlayerEmbed(
    username: String,
    uuid: UUID,
    address: String,
): EmbedObject() {
    init {
        this.title = "New player joined!"
        this.description = "新しいプレイヤーが参加しました。"
        this.setImage("https://crafatar.com/avatars/${uuid}")
        this.addField("Name", username, false)
            .addField("UUID", uuid.toString(),false)
            .addField("IP", address, false)
    }
}