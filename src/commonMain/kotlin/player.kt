import Scenes.Level
import com.soywiz.klock.milliseconds
import com.soywiz.korev.Key
import com.soywiz.korge.view.*
import com.soywiz.korma.geom.Vector2D

const val SPEED = 3
const val SIZE = 50

class Player(private val scene: Level) {
    private var image : SolidRect = scene.sceneView.solidRect(SIZE, SIZE).xy(scene.spawnpoint.x, scene.spawnpoint.y);
    init {
        movement()
        deathZoneCallback()
    }

    private fun deathZoneCallback() {
        image.onCollision({scene.deathZoneList.contains(it)}) {
            die()
        }
    }

    public fun die() {
        //reset all stuff
        image.xy(scene.spawnpoint.x, scene.spawnpoint.y)
    }

    private fun movement() {
        val input = scene.views.input

        var movement = Vector2D(0, 0)
        image.addUpdater { dt ->
            movement = Vector2D(0, 0)
            val scale = dt / 16.milliseconds
            if (input.keys.pressing(Key.LEFT) || input.keys.pressing(Key.A)) movement.x -= 1.0
            if (input.keys.pressing(Key.RIGHT) || input.keys.pressing(Key.D)) movement.x += 1.0
            if (input.keys.pressing(Key.UP) || input.keys.pressing(Key.W)) movement.y -= 1.0
            if (input.keys.pressing(Key.DOWN) || input.keys.pressing(Key.S)) movement.y += 1.0

            if (movement.length > 0) {
                movement.normalize()
                movement *= SPEED
                movement *= scale
                xy(x + movement.x, y + movement.y)
            }
        }

        image.onCollision({scene.collisionList.contains(it)}) {
            if (movement.x != 0.0) {
                image.x -= movement.x
            }
            if (movement.y != 0.0) {
                image.y -= movement.y
            }
        }
    }
}