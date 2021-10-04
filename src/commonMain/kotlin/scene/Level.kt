package scene

import objects.Player
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.*
import com.soywiz.korma.geom.Vector2D
import objects.MovingObject
import objects.PickupItem
import objects.Phone
import objects.interactables.Interactable

abstract class Level : Scene() {
    var player : Player? = null
    var phone : Phone? = null
    open var spawnpoint = Vector2D(0, 0)
    val collisionList = mutableListOf<RectBase>()
    val deathZoneList = mutableListOf<SolidRect>()
    val interactableList = mutableListOf<Interactable>()
    var bg = Container()
    var il = Container()
    var fg = Container()
    var ui = Container()

    open suspend fun drawImages() {
        sceneView.addChild(bg)
        sceneView.addChild(il)
        sceneView.addChild(fg)
        sceneView.addChild(ui)
        sceneView.addUpdater {
            il.children.sortBy {
                it.pos.y
            }
        }
    }

    open suspend fun nextScene() {

    }
}
