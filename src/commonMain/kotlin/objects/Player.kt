import scenes.Level
import com.soywiz.klock.milliseconds
import com.soywiz.korev.Key
import com.soywiz.korge.view.*
import com.soywiz.korma.geom.Vector2D

class Player(private val scene: Level) {
    companion object {
        const val SPEED = 3
        const val SIZE = 50
    }

    private var playerImage: SolidRect =
        scene.sceneView.solidRect(SIZE, SIZE).xy(scene.spawnpoint.x, scene.spawnpoint.y)

    init {
        movement()
        deathZoneCallback()
        download()
    }

    private fun deathZoneCallback() {
        playerImage.onCollision({ scene.deathZoneList.contains(it) }) {
            die()
        }
    }

    private fun download() {
        playerImage.addUpdater {
            scene.levelManager?.download(3)
        }
    }

    public fun die() {
        //reset all stuff
        playerImage.xy(scene.spawnpoint.x, scene.spawnpoint.y)
    }

    private fun movement() {
        val input = scene.views.input

        var movement = Vector2D(0, 0)
        playerImage.addUpdater { dt ->
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

        playerImage.onCollision({ scene.collisionList.contains(it) }) {
            if (movement.x != 0.0) {
                playerImage.x -= movement.x
            }
            if (movement.y != 0.0) {
                playerImage.y -= movement.y
            }
        }
    }
}
