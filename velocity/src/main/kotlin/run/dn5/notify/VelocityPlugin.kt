package run.dn5.notify

import com.google.inject.Inject
import com.velocitypowered.api.event.Subscribe
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent
import com.velocitypowered.api.plugin.Plugin
import com.velocitypowered.api.plugin.annotation.DataDirectory
import com.velocitypowered.api.proxy.ProxyServer
import org.slf4j.Logger
import run.dn5.notify.velocity.listeners.LoginListener
import java.nio.file.Path

@Plugin(id = "notify", name = "Notify", version = "", authors = [])
class VelocityPlugin @Inject constructor(
    val server: ProxyServer,
    val logger: Logger,
    @DataDirectory
    val dataFolder: Path
) {
    companion object{
        lateinit var instance: VelocityPlugin
    }

    lateinit var common: Common

    init {
        if (!this.dataFolder.toFile().exists()) this.dataFolder.toFile().mkdir()
    }

    @Subscribe
    fun onEnable(e: ProxyInitializeEvent) {
        instance = this
        this.reload()
    }

    private fun reload(){
        listOf(
            LoginListener(this)
        ).forEach { this.server.eventManager.register(this, it) }
        this.common = Common(this.dataFolder.toFile())
    }
}