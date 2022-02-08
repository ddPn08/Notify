package run.dn5.notify.paper.listener

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import run.dn5.notify.PaperPlugin

class PlayerJoinListener(
    private val plugin: PaperPlugin
): Listener {
    @EventHandler
    fun onPlayerJoin(e: PlayerJoinEvent){
        this.plugin.common.informNewPlayer.execute(e.player.name, e.player.uniqueId, e.player.address.hostString)
    }
}