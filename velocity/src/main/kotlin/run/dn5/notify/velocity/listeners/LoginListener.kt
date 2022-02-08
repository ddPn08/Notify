package run.dn5.notify.velocity.listeners

import com.velocitypowered.api.event.Subscribe
import com.velocitypowered.api.event.connection.LoginEvent
import run.dn5.notify.VelocityPlugin

class LoginListener (
    private val plugin: VelocityPlugin
){
    @Subscribe
    fun onLogin(e: LoginEvent) {
        this.plugin.common.informNewPlayer.execute(e.player.username, e.player.uniqueId, e.player.remoteAddress.hostString)
    }
}