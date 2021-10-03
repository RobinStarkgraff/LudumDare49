package objects

import com.soywiz.klock.TimeSpan
import scene.Level
import com.soywiz.klock.milliseconds
import com.soywiz.korev.Key
import com.soywiz.korge.view.*
import com.soywiz.korma.geom.Vector2D
import helper.SpriteLibrary

class Player(var scene: Level) {
    companion object {
        const val SPEED = 3
        const val SIZE = 3
        const val ANIMATION_FPS = 10
        val IDLE = SpriteAnimation(
            spriteMap = SpriteLibrary.static.PLAYER_IDLE,
            spriteWidth = 32,
            spriteHeight = 32,
            columns = 8,
            rows = 1,
        )
        val RIGHT = SpriteAnimation(
            spriteMap = SpriteLibrary.static.PLAYER_WALK_RIGHT,
            spriteWidth = 32,
            spriteHeight = 32,
            columns = 10,
            rows = 1,
        )
        val LEFT = SpriteAnimation(
            spriteMap = SpriteLibrary.static.PLAYER_WALK_LEFT,
            spriteWidth = 32,
            spriteHeight = 32,
            columns = 10,
            rows = 1,
        )
        val UP = SpriteAnimation(
            spriteMap = SpriteLibrary.static.PLAYER_WALK_UP,
            spriteWidth = 32,
            spriteHeight = 32,
            columns = 10,
            rows = 1,
        )
        val DOWN = SpriteAnimation(
            spriteMap = SpriteLibrary.static.PLAYER_WALK_DOWN,
            spriteWidth = 32,
            spriteHeight = 32,
            columns = 10,
            rows = 1,
        )
    }

    private var playerImage = Sprite()

    init {
        createSprite()
        changeSprite(IDLE)
        movement()
        deathZoneCallback()
        download()

    }

    private fun createSprite(){
        playerImage = scene.sceneView.sprite().scale(SIZE, SIZE)
        playerImage.playAnimationLooped(spriteDisplayTime = TimeSpan(1000.0/ANIMATION_FPS))
    }

    private fun changeSprite(animation: SpriteAnimation){
        playerImage.playAnimationLooped(animation)
    }

    private fun deathZoneCallback() {
        playerImage.onCollision({ scene.deathZoneList.contains(it) }) {
            die()
        }
    }
    private fun download() {
        playerImage.addUpdater {
            scene.downloadManager?.download(0)
        }
    }

    private fun die() {
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

            if(movement.x > 0) changeSprite(RIGHT)
            else if (movement.x < 0) changeSprite(LEFT)
            else if (movement.y < 0) changeSprite(UP)
            else if (movement.y > 0) changeSprite(DOWN)
            else changeSprite(IDLE)
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

