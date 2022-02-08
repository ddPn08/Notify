package run.dn5.notify.waterfall.listener

import net.md_5.bungee.api.event.LoginEvent
import net.md_5.bungee.api.event.PostLoginEvent
import net.md_5.bungee.api.plugin.Listener
import net.md_5.bungee.event.EventHandler
import run.dn5.notify.WaterfallPlugin
import java.net.InetSocketAddress

class LoginListener(
    private val plugin: WaterfallPlugin
): Listener {
    @EventHandler
    fun onLogin(e: PostLoginEvent){
        val inetAddress = e.player.socketAddress as InetSocketAddress
        this.plugin.common.informNewPlayer.execute(e.player.displayName, e.player.uniqueId, inetAddress.hostString)
    }
}