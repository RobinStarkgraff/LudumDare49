package scene

import objects.Player
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.SolidRect
import com.soywiz.korge.view.addUpdater
import com.soywiz.korma.geom.Vector2D
import manager.DownloadManager
import objects.PickupItem

abstract class Level : Scene() {
    var player : Player? = null
    var downloadManager : DownloadManager? = null
    open val spawnpoint = Vector2D(0, 0)
    val collisionList = mutableListOf<SolidRect>()
    val deathZoneList = mutableListOf<SolidRect>()
    val pickupItemList = mutableListOf<PickupItem>()

    open suspend fun drawImages() {
        sceneView.addUpdater {
            sceneView.children.sortBy {
                it.pos.y
            }
        }
    }

    open suspend fun nextScene() {

    }
}
