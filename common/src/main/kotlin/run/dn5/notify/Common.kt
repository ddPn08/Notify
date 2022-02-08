package run.dn5.notify

import com.charleskorn.kaml.Yaml
import run.dn5.notify.futures.inp.InformNewPlayer
import java.io.File
import java.io.FileInputStream
import java.nio.file.Files

class Common(
    val dataFolder: File
) {

    val informNewPlayer = InformNewPlayer(this)

    init {
        val config = File(dataFolder, "config.yml")
        if(!config.exists()){
            this.javaClass.getResourceAsStream("/config.yml").use {
                if(it == null) throw Error("Config file not found")
                Files.copy(it, config.toPath())
            }
        }
    }

    fun getConfig(): CommonConfig {
        return Yaml.default.decodeFromStream(CommonConfig.serializer(), File(dataFolder, "config.yml").inputStream())
    }

}