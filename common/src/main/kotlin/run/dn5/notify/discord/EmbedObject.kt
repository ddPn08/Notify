package run.dn5.notify.discord

import java.awt.Color

open class EmbedObject {
    var title: String? = null
    var description: String? = null
    var url: String? = null
    var color: Int? = null
    var footer: Footer? = null
    var thumbnail: Thumbnail? = null
    var image: Image? = null
    var author: Author? = null
    val fields: MutableList<Field> = ArrayList()

    fun setColor(color: Color){
        var rgb = color.red
        rgb = (rgb shl 8) + color.green
        rgb = (rgb shl 8) + color.blue
        this.color = rgb
    }

    fun addField(name: String, value: String, inline: Boolean): EmbedObject {
        fields.add(Field(name, value, inline))
        return this
    }
    fun setFooter(text: String, icon: String): EmbedObject {
        footer = Footer(text, icon)
        return this
    }
    fun setThumbnail(url: String): EmbedObject {
        thumbnail = Thumbnail(url)
        return this
    }
    fun setImage(url: String): EmbedObject {
        image = Image(url)
        return this
    }
    fun setAuthor(name: String, url: String, icon: String): EmbedObject {
        author = Author(name, url, icon)
        return this
    }

    inner class Footer(val text: String, val iconUrl: String)
    inner class Thumbnail(val url: String)
    inner class Image(val url: String)
    inner class Author(
        val name: String,
        val url: String,
        val iconUrl: String
    )
    inner class Field(
        val name: String,
        val value: String,
        val isInline: Boolean
    )
}