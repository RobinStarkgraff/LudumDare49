package scene

import Player
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.SolidRect
import com.soywiz.korma.geom.Vector2D
import manager.DownloadManager
import objects.WifiRouter

abstract class Level : Scene() {
    var downloadManager : DownloadManager? = null
    var player : Player? = null
    open val spawnpoint = Vector2D(0, 0)
    val collisionList = mutableListOf<SolidRect>()
    val deathZoneList = mutableListOf<SolidRect>()

    open suspend fun nextScene() {

    }
}
