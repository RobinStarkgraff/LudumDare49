package objects


import com.soywiz.klock.TimeSpan
import scene.Level
import com.soywiz.klock.milliseconds
import com.soywiz.korau.sound.SoundChannel
import com.soywiz.korev.Key
import com.soywiz.korge.view.*
import com.soywiz.korim.color.RGBA
import com.soywiz.korio.async.launch
import com.soywiz.korma.geom.Vector2D
import helper.SoundPlayer
import helper.SpriteLibrary
import kotlinx.coroutines.GlobalScope
import objects.interactables.PickupItem


class Player(var scene: Level) {
    companion object {
        const val SPEED = 3
        const val SCALE = 3.0
        val COLLISION_SIZE = Vector2D(30, 20)
        val COLLISION_POS = Vector2D(0.0, -2.5)
        const val ANIMATION_FPS = 12
        const val IDLE_FPS = 6
        var inventoryObject: PickupItem? = null
    }

    private var playerParent = Container()
    private var playerImage = Sprite()
    lateinit var collisionShape: SolidRect

    private lateinit var walkingSound: SoundChannel

    init {
        createContainer()
        createSprite()
        changeSprite(SpriteLibrary.PLAYER_IDLE_ANIM, IDLE_FPS)
        createCollisionShape()
        setupStepSound()
        movement()
        deathZoneCallback()
        interactableCallbacks()
        download()
    }

    private fun createContainer() {
        playerParent = scene.il.container().xy(scene.spawnpoint.x, scene.spawnpoint.y)
    }


    private fun createCollisionShape() {
        collisionShape = playerParent.solidRect(COLLISION_SIZE.x, COLLISION_SIZE.y, RGBA(255, 0, 0, 0))
        collisionShape.anchor(0.5, 0.5)
        collisionShape.xy(COLLISION_POS.x * SCALE, COLLISION_POS.y * SCALE)
    }

    private fun createSprite(){
        playerImage = playerParent.sprite().anchor(0.5, 0.8)
        playerImage.playAnimationLooped(spriteDisplayTime = TimeSpan(1000.0/ ANIMATION_FPS))
    }

    private fun changeSprite(animation: SpriteAnimation, speed: Int = ANIMATION_FPS) {
        playerImage.playAnimationLooped(animation, spriteDisplayTime = TimeSpan(1000.0 / speed))
    }

    private fun deathZoneCallback() {
        collisionShape.onCollision({ scene.deathZoneList.contains(it) }) {
            die()
        }
    }

    private fun download() {
        playerImage.addUpdater {
            scene.downloadManager?.download(playerParent.pos.x, playerParent.pos.y);
        }
    }

    private fun die() {
        //reset all stuff
        playerParent.xy(scene.spawnpoint.x, scene.spawnpoint.y)
    }

    private fun setupStepSound() {
        GlobalScope.launch { walkingSound = SoundPlayer.setContinuousSound(SoundPlayer.FOOTSTEPS) }
    }

    private fun interactableCallbacks() {
        playerImage.addUpdater {
            for (interactableItem in scene.interactableList) {
                //Keep in mind that this needs to be properly taken care of for every object
                val distanceToObject = Vector2D.distance(interactableItem.pos, playerParent.pos - collisionShape.pos)
                if (distanceToObject > interactableItem.interactionDistance) {
                    continue
                }

                //TODO: Give speechbubble
                if (scene.views.keys.justPressed(Key.E)) {
                    interactableItem.interact()
                }
            }
        }
    }

    private fun removeInventoryItem() {
        inventoryObject?.image?.removeFromParent()
        inventoryObject = null
    }

    private fun movement() {
        val input = scene.views.input

        var movement = Vector2D(0, 0)
        playerParent.addUpdater { dt ->
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

            if (movement.length > 0) SoundPlayer.startContinuousSound(walkingSound)
            else SoundPlayer.stopContinuousSound(walkingSound)

            if (movement.x > 0) changeSprite(SpriteLibrary.PLAYER_WALK_RIGHT_ANIM)
            else if (movement.x < 0) changeSprite(SpriteLibrary.PLAYER_WALK_LEFT_ANIM)
            else if (movement.y < 0) changeSprite(SpriteLibrary.PLAYER_WALK_UP_ANIM)
            else if (movement.y > 0) changeSprite(SpriteLibrary.PLAYER_WALK_DOWN_ANIM)
            else changeSprite(SpriteLibrary.PLAYER_IDLE_ANIM, IDLE_FPS)
        }

        collisionShape.onCollision({ scene.collisionList.contains(it) }) {
            if (movement.x != 0.0) {
                playerParent.x -= movement.x
            }
            if (movement.y != 0.0) {
                playerParent.y -= movement.y
            }
        }
    }
}

