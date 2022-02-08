package run.dn5.notify.futures.inp

import com.charleskorn.kaml.Yaml
import kotlinx.serialization.Serializable
import java.io.File
import java.nio.file.Path


class Cache(
    private val directory: Path
) {
    @Serializable
    data class CacheData (
        val players: MutableList<String>
    )

    private val file = File("${this.directory}/cache.yml")
    init {
        if(!this.directory.toFile().exists()) {
            this.directory.toFile().mkdir()
            this.file.createNewFile()
            this.file.writeText("players: []")
        }
    }
    private fun getCache(): CacheData {
        return Yaml.default.decodeFromStream(CacheData.serializer(), this.file.inputStream())
    }
    private fun saveCache(cache: CacheData){
        Yaml.default.encodeToStream(CacheData.serializer(), cache, this.file.outputStream())
    }
    fun add(key: String){
        val cache = this.getCache()
        cache.players.add(key)
        this.saveCache(cache)
    }
    fun exists(key: String): Boolean{
        return this.getCache().players.contains(key)
    }
}