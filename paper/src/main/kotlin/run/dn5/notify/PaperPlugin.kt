package run.dn5.notify

import org.bukkit.plugin.java.JavaPlugin
import run.dn5.notify.paper.listener.PlayerJoinListener

class PaperPlugin: JavaPlugin() {
    lateinit var common: Common

    override fun onEnable() {
        if(!this.dataFolder.exists()) this.dataFolder.mkdir()
        this.reload()
    }

    private fun reload(){
        listOf(
            PlayerJoinListener(this)
        ).forEach { this.server.pluginManager.registerEvents(it, this) }

        this.common = Common(this.dataFolder)
    }
}