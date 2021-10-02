package objects

import com.soywiz.klock.milliseconds
import com.soywiz.korev.Key
import com.soywiz.korge.view.*
import com.soywiz.korma.geom.Vector2D
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import scene.Level

class Player(private val scene: Level) {
    companion object {
        const val SPEED = 3
        const val SIZE = 50

        var inventoryObject: PickupItem? = null
    }

    private var playerImage: SolidRect =
        scene.sceneView.solidRect(SIZE, SIZE).xy(scene.spawnpoint.x, scene.spawnpoint.y)

    init {
        movement()
        deathZoneCallback()
        inventoryItemCallbacks()
        download()
    }

    private fun deathZoneCallback() {
        playerImage.onCollision({ scene.deathZoneList.contains(it) }) {
            die()
        }
    }

    private fun inventoryItemCallbacks() {
        playerImage.addUpdater {
            for (pickUpItem in scene.pickupItemList) {
                val distanceToObject = Vector2D.distance(pickUpItem.image.globalXY(), playerImage.globalXY())
                if (distanceToObject > PickupItem.INTERACTION_DISTANCE || inventoryObject != null) {
                    break
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

    private fun download() {
        playerImage.addUpdater {
            GlobalScope.launch { scene.downloadManager?.download(4) }
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

