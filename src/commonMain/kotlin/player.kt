import com.soywiz.klock.milliseconds
import com.soywiz.korev.Key
import com.soywiz.korge.view.*
import com.soywiz.korge.scene.Scene
import com.soywiz.korma.geom.Vector2D

const val SPEED = 5
const val SIZE = 100

class Player(private val scene : Scene) {
    init {
        val image = scene.sceneView.solidRect(SIZE, SIZE)
        movement(image)
    }

    private fun movement(player: SolidRect){
        val input = scene.views.input
        player.addUpdater { dt ->
            val scale = dt / 16.milliseconds
            var movement = Vector2D(0, 0)

            if (input.keys.pressing(Key.LEFT) || input.keys.pressing(Key.A)) movement.x -= 2.0 * scale
            if (input.keys.pressing(Key.RIGHT) || input.keys.pressing(Key.D)) movement.x += 2.0 * scale
            if (input.keys.pressing(Key.UP) || input.keys.pressing(Key.W)) movement.y -= 2.0 * scale
            if (input.keys.pressing(Key.DOWN) || input.keys.pressing(Key.S)) movement.y += 2.0 * scale

            if (movement.length > 0) {
                movement.normalize()
                movement *= SPEED
                println(movement)
                xy(x + movement.x, y + movement.y)
            }
        }
    }
}