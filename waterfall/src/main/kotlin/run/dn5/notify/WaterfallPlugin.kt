package run.dn5.notify

import net.md_5.bungee.api.plugin.Plugin
import net.md_5.bungee.config.Configuration
import net.md_5.bungee.config.ConfigurationProvider
import net.md_5.bungee.config.YamlConfiguration
import run.dn5.notify.waterfall.listener.LoginListener
import java.io.File


class WaterfallPlugin: Plugin() {

    lateinit var common: Common

    override fun onEnable() {
        if(!this.dataFolder.exists()) this.dataFolder.mkdir()
        this.reload()
    }

    private fun reload(){
        listOf(
            LoginListener(this)
        ).forEach { this.proxy.pluginManager.registerListener(this, it) }

        this.common = Common(this.dataFolder)
    }
}