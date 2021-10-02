import com.soywiz.klock.milliseconds
import com.soywiz.korev.Key
import com.soywiz.korge.view.*
import com.soywiz.korge.scene.Scene
import com.soywiz.korma.geom.Vector2D

const val SPEED = 5
const val SIZE = 100

class Player(private val scene: Scene) {
    init {

        val image = scene.sceneView.solidRect(SIZE, SIZE).xy(200, 200)
        movement(image)
        val levelManager = LevelManager()
        image.addUpdater{
            levelManager.download(4)
        }
    }


    private fun movement(player: SolidRect) {
        player.addUpdater { dt ->
            val input = scene.views.input
            val scale = dt / 16.milliseconds
            var movement = Vector2D(0, 0)

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

            val collisionList = listOf(player)
            player.onCollision({collisionList.contains(it)}) {
                if (movement.x != 0.0) {
                    player.x -= movement.x
                }
                if (movement.y != 0.0) {
                    player.y -= movement.y
                }
            }
        }
    }
}