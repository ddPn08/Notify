package run.dn5.template

import com.google.inject.Inject;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import org.slf4j.Logger;

@Plugin(id = "template", name = "MinecraftPluginTemplate", version = "1.0",
    url = "https://dn5.run", description = "", authors = ["ddPn08"]
)

class VelocityPlugin(
    @Inject
    private val server: ProxyServer,
    @Inject
    private val logger: Logger
)  {




}