package scene

import objects.Player
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.scene.SceneContainer
import com.soywiz.korge.view.*
import com.soywiz.korma.geom.Vector2D
import objects.Phone
import objects.interactables.Interactable
import objects.interactables.ObjectiveItem
import physics.PhysicsSimulation
import physics.primitives.BoxCollider

abstract class Level : Scene() {
    companion object {
        lateinit var SCENE_CONTAINER : SceneContainer;
    }

    var player : Player? = null
    var phone : Phone? = null
    open var spawnpoint = Vector2D(0, 0)
    val deathZoneList = mutableListOf<SolidRect>()
    val interactableList = mutableListOf<Interactable>()
    abstract val objective: ObjectiveItem
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

    abstract fun downloadComplete()

    open suspend fun nextScene() {
        PhysicsSimulation.clearPhysicsSimulation()
        sceneDestroy()
    }
}
