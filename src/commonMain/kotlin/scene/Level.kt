package scene

import objects.Player
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.*
import com.soywiz.korma.geom.Vector2D
import manager.DownloadManager
import objects.PickupItem

abstract class Level : Scene() {
    var player : Player? = null
    var downloadManager : DownloadManager? = null
    open var spawnpoint = Vector2D(0, 0)
    val collisionList = mutableListOf<RectBase>()
    val deathZoneList = mutableListOf<SolidRect>()
    val pickupItemList = mutableListOf<PickupItem>()
    var bg = Container()
    var il = Container()
    var fg = Container()

    open suspend fun drawImages() {
        sceneView.addChild(bg)
        sceneView.addChild(il)
        sceneView.addChild(fg)
        sceneView.addUpdater {
            il.children.sortBy {
                it.pos.y
            }
        }
    }

    open suspend fun nextScene() {

    }
}
