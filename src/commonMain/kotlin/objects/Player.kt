import scene.Level
import com.soywiz.klock.milliseconds
import com.soywiz.korge.*
import com.soywiz.korev.Key
import com.soywiz.korge.view.*
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.file.std.resourcesVfs
import com.soywiz.korma.geom.Vector2D
import helper.SpriteLibrary
import kotlinx.coroutines.*

class Player(var scene: Level) {
    companion object {
        const val SPEED = 3
        const val SIZE = 50
    }

    private lateinit var playerImage: Sprite

    init {
        movement()
        deathZoneCallback()
        download()
    }

    fun play(){
        val idleAnimation = SpriteAnimation(
            spriteMap = SpriteLibrary.static.PLAYER_IDLE,
            spriteWidth = 32,
            spriteHeight = 32,
            columns = 10,
            rows = 1,
        )
        playerImage = scene.sceneView.sprite(idleAnimation)
        playerImage.playAnimation()
    }

    private fun deathZoneCallback() {
        playerImage.onCollision({ scene.deathZoneList.contains(it) }) {
            die()
        }
    }
    fun download() {
        playerImage.addUpdater {
            GlobalScope.launch {scene.downloadManager?.download(4)}
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

