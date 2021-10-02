package scene

import objects.Player
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.SolidRect
import com.soywiz.korma.geom.Vector2D
import manager.DownloadManager
import objects.PickupItem

abstract class Level : Scene() {
    var downloadManager : DownloadManager? = null
    var player : Player? = null
    open val spawnpoint = Vector2D(0, 0)
    val collisionList = mutableListOf<SolidRect>()
    val deathZoneList = mutableListOf<SolidRect>()
    val pickupItemList = mutableListOf<PickupItem>()

    open suspend fun nextScene() {

    }
}
