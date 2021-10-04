package objects


import com.soywiz.klock.TimeSpan
import com.soywiz.klock.milliseconds
import com.soywiz.korau.sound.SoundChannel
import com.soywiz.korev.Key
import com.soywiz.korge.view.*
import com.soywiz.korim.atlas.AtlasInfo
import com.soywiz.korim.bitmap.*
import com.soywiz.korim.color.RGBA
import com.soywiz.korim.format.nativeImageLoadingEnabled
import com.soywiz.korio.async.launch
import com.soywiz.korma.geom.Rectangle
import com.soywiz.korma.geom.Vector2D
import com.soywiz.korma.geom.length
import helper.SoundPlayer
import helper.SpriteLibrary
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import objects.interactables.PickupItem
import physics.primitives.BoxCollider
import scene.Level


class Player(var scene: Level) {
    companion object {
        const val SPEED = 120
        const val SCALE = 3.0
        val COLLISION_SIZE = Vector2D(20, 20)
        val COLLISION_POS = Vector2D(0.0, -2.5)
        const val ANIMATION_FPS = 12
        const val IDLE_FPS = 6
    }

    var inventoryObject: PickupItem? = null
    var playerParent = Container()
    private var playerImage = Sprite()
    var physicsBody = physics.PhysicsBody(dynamic = true)
    private var boxCollider = BoxCollider(Vector2D(), 20.0, 20.0,  physicsBody)
    var speechBubbleParent : Container? = null
    var hasInteracted = false

    private var walkingSound: SoundChannel? = null

    init {
        createContainer()
        createSprite()
        changeSprite(SpriteLibrary.PLAYER_IDLE_ANIM, IDLE_FPS)
        movementCallback()
        setupStepSound()
        deathZoneCallback()
        interactableCallbacks()
        download()
    }

    private fun createContainer() {
        playerParent = scene.il.container().xy(scene.spawnpoint.x, scene.spawnpoint.y)
        physicsBody.position = playerParent.pos

    }

    private fun createSprite() {
        playerImage = playerParent.sprite().anchor(0.5, 0.8)
        playerImage.playAnimationLooped(spriteDisplayTime = TimeSpan(1000.0 / ANIMATION_FPS))
    }

    private fun changeSprite(animation: SpriteAnimation, speed: Int = ANIMATION_FPS) {
        playerImage.playAnimationLooped(animation, spriteDisplayTime = TimeSpan(1000.0 / speed))
    }

    private fun deathZoneCallback() {

    }

    private fun download() {
        playerImage.addUpdater {
            scene.phone?.download(playerParent.pos.x, playerParent.pos.y);
        }
    }

    public fun die() {
        scene.phone?.downloaded = 0.0
        physicsBody.position = Vector2D(scene.spawnpoint.x, scene.spawnpoint.y)
    }

    private fun setupStepSound() {
        GlobalScope.launch { walkingSound = SoundPlayer.setContinuousSound(SoundPlayer.FOOTSTEPS) }
    }

    private fun interactableCallbacks() {
        playerImage.addUpdater {
            for (interactableItem in scene.interactableList) {
                //Keep in mind that this needs to be properly taken care of for every object
                val distanceToObject = Vector2D.distance(interactableItem.pos, playerParent.pos)
                if (distanceToObject > interactableItem.interactionDistance) {
                    continue
                }

                if(!hasInteracted) {
                    say("E", 1)
                    hasInteracted = true
                }
                if (scene.views.keys.justPressed(Key.E)) {
                    interactableItem.interact()
                }
            }
        }
    }

    fun removeInventoryItem() {
        inventoryObject?.destroySelf()
        inventoryObject = null
    }

    fun say(text: String, time: Long) {
        if(speechBubbleParent != null) return
        speechBubbleParent = scene.fg.container()
        val ninePatch = speechBubbleParent!!.ninePatch(SpriteLibrary.SPEECH_BUBBLE, 100.0, 50.0)
        val text = speechBubbleParent!!.text(text, 20.0, RGBA(0, 0, 0)).xy(10, 7)
        ninePatch.width = text.width + 20
        speechBubbleParent!!.xy(0.0, -100.0)
        GlobalScope.launch { deleteText(time) }
    }

    suspend fun deleteText(time : Long) {
        delay(time * 1000)
        speechBubbleParent?.removeFromParent()
        speechBubbleParent = null
    }

    private fun movementCallback() {
        val input = scene.views.input

        playerParent.addUpdater { dt ->
            var velocity = Vector2D(0, 0)
            val scale = dt / 16.milliseconds
            //println("Physicsbody is: ${physicsBody.dynamic}")
            if(physicsBody.dynamic)
            {
                if (input.keys.pressing(Key.LEFT) || input.keys.pressing(Key.A)) velocity.x = -1.0
                if (input.keys.pressing(Key.RIGHT) || input.keys.pressing(Key.D)) velocity.x = 1.0
                if (input.keys.pressing(Key.UP) || input.keys.pressing(Key.W)) velocity.y = -1.0
                if (input.keys.pressing(Key.DOWN) || input.keys.pressing(Key.S)) velocity.y = 1.0

                if (velocity.length > 0) {
                    velocity.normalize()
                    velocity *= SPEED
                    velocity *= scale
                }

                physicsBody.velocity = velocity
            }

            playerParent.pos = physicsBody.position
            if(speechBubbleParent != null) speechBubbleParent!!.pos = physicsBody.position - Vector2D(0, 100)

            if (velocity.length > 0) SoundPlayer.startContinuousSound(walkingSound)
            else SoundPlayer.stopContinuousSound(walkingSound)

            if (velocity.x > 0) changeSprite(SpriteLibrary.PLAYER_WALK_RIGHT_ANIM)
            else if (velocity.x < 0) changeSprite(SpriteLibrary.PLAYER_WALK_LEFT_ANIM)
            else if (velocity.y < 0) changeSprite(SpriteLibrary.PLAYER_WALK_UP_ANIM)
            else if (velocity.y > 0) changeSprite(SpriteLibrary.PLAYER_WALK_DOWN_ANIM)
            else changeSprite(SpriteLibrary.PLAYER_IDLE_ANIM, IDLE_FPS)

            println(playerParent.pos)
        }
    }
}

