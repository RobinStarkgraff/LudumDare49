package objects


import com.soywiz.klock.TimeSpan
import scene.Level
import com.soywiz.klock.milliseconds
import com.soywiz.korev.Key
import com.soywiz.korge.view.*
import com.soywiz.korim.color.RGBA
import com.soywiz.korma.geom.Vector2D
import helper.SpriteLibrary


class Player(var scene: Level) {
    companion object {
        const val SPEED = 3
        const val SIZE = 3
        val COLLISION_SIZE = Vector2D(30, 20)
        val COLLISION_POS = Vector2D(16, 20)
        const val ANIMATION_FPS = 12
        const val IDLE_FPS = 8
        var inventoryObject: PickupItem? = null
    }

    private var playerParent = Container()
    private var playerImage = Sprite()
    private lateinit var collisionShape : SolidRect

    init {
        createContainer()
        createSprite()
        changeSprite(SpriteLibrary.static.PLAYER_IDLE_ANIM, IDLE_FPS)
        createCollisionShape()
        movement()
        deathZoneCallback()
        inventoryItemCallbacks()
        download()

    }

    private fun createContainer() {
        playerParent = scene.sceneView.container()
    }


    private fun createCollisionShape() {
        collisionShape = playerParent.solidRect(COLLISION_SIZE.x, COLLISION_SIZE.y, RGBA(0, 0, 0, 0))
        collisionShape.anchor(0.5, 0.5)
        collisionShape.xy(COLLISION_POS.x * SIZE, COLLISION_POS.y * SIZE)
    }

    private fun createSprite(){
        playerImage = playerParent.sprite().scale(SIZE, SIZE)
        playerImage.playAnimationLooped(spriteDisplayTime = TimeSpan(1000.0/ANIMATION_FPS))
    }

    private fun changeSprite(animation: SpriteAnimation, speed : Int = ANIMATION_FPS){
        playerImage.playAnimationLooped(animation, spriteDisplayTime = TimeSpan(1000.0/speed))
    }

    private fun deathZoneCallback() {
        collisionShape.onCollision({scene.deathZoneList.contains(it)}) {
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

    private fun inventoryItemCallbacks() {
        if (inventoryObject != null) {
            return
        }

        playerImage.addUpdater {
            for (pickUpItem in scene.pickupItemList) {
                val distanceToObject = Vector2D.distance(pickUpItem.image.globalXY(), playerImage.globalXY())
                if (distanceToObject > PickupItem.INTERACTION_DISTANCE) {
                    continue
                }

                //TODO: Give Pickup speechbubble

                if (scene.views.keys.pressing(Key.E)) {
                    inventoryObject = pickUpItem
                    inventoryObject?.putIntoInventory()
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

            if(movement.x > 0) changeSprite(SpriteLibrary.static.PLAYER_WALK_RIGHT_ANIM)
            else if (movement.x < 0) changeSprite(SpriteLibrary.static.PLAYER_WALK_LEFT_ANIM)
            else if (movement.y < 0) changeSprite(SpriteLibrary.static.PLAYER_WALK_UP_ANIM)
            else if (movement.y > 0) changeSprite(SpriteLibrary.static.PLAYER_WALK_DOWN_ANIM)
            else changeSprite(SpriteLibrary.static.PLAYER_IDLE_ANIM, IDLE_FPS)
        }

        collisionShape.onCollision({ scene.collisionList.contains(it) }) {
            if (movement.x != 0.0) {
                playerImage.x -= movement.x
            }
            if (movement.y != 0.0) {
                playerImage.y -= movement.y
            }
        }
    }
}

