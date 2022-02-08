package run.dn5.notify

import kotlinx.serialization.Serializable

@Serializable
data class CommonConfig (
    var webhook: String
)