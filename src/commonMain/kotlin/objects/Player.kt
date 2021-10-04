package objects


import com.soywiz.klock.TimeSpan
import scene.Level
import com.soywiz.klock.milliseconds
import com.soywiz.korev.Key
import com.soywiz.korge.view.*
import com.soywiz.korim.color.RGBA
import com.soywiz.korma.geom.*
import helper.SpriteLibrary
import physics.primitives.BoxCollider


class Player(var scene: Level) {
    companion object {
        const val SPEED = 120
        const val SCALE = 3.0
        val COLLISION_SIZE = Vector2D(20, 20)
        val COLLISION_POS = Vector2D(0.0, -2.5)
        const val ANIMATION_FPS = 12
        const val IDLE_FPS = 6
        var inventoryObject: PickupItem? = null
    }

    private var playerParent = Container().xy(20,20)
    private var playerImage = Sprite()
    private var physicsBody = physics.PhysicsBody(position = playerParent.pos, dynamic = true)
    private var boxCollider = BoxCollider(Vector2D(), 20.0, 20.0,  physicsBody)
    //private lateinit var collisionShape: SolidRect

    init {
        createContainer()
        createSprite()
        changeSprite(SpriteLibrary.static.PLAYER_IDLE_ANIM, IDLE_FPS)
        //createCollisionShape()
        movementCallback()
        deathZoneCallback()
        inventoryItemCallbacks()
        download()
    }

    private fun createContainer() {
        playerParent = scene.sceneView.container()
    }

    /*private fun createCollisionShape() {
        collisionShape = playerParent.solidRect(COLLISION_SIZE.x, COLLISION_SIZE.y, RGBA(255, 0, 0, 255))
        collisionShape.anchor(0.5, 0.5)
        collisionShape.xy(COLLISION_POS.x * SCALE, COLLISION_POS.y * SCALE)
    }*/

    private fun createSprite() {
        playerImage = playerParent.sprite().anchor(0.5, 0.8)
        playerImage.playAnimationLooped(spriteDisplayTime = TimeSpan(1000.0 / ANIMATION_FPS))
    }

    private fun changeSprite(animation: SpriteAnimation, speed: Int = ANIMATION_FPS) {
        playerImage.playAnimationLooped(animation, spriteDisplayTime = TimeSpan(1000.0 / speed))
    }

    private fun deathZoneCallback() {
        /*collisionShape.onCollision({ scene.deathZoneList.contains(it) }) {
            die()
        }*/
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

    private fun movementCallback() {
        val input = scene.views.input

        playerParent.addUpdater { dt ->
            var velocity = Vector2D(0, 0)
            val scale = dt / 16.milliseconds
            if (input.keys.pressing(Key.LEFT) || input.keys.pressing(Key.A)) velocity.x -= 1.0
            if (input.keys.pressing(Key.RIGHT) || input.keys.pressing(Key.D)) velocity.x += 1.0
            if (input.keys.pressing(Key.UP) || input.keys.pressing(Key.W)) velocity.y -= 1.0
            if (input.keys.pressing(Key.DOWN) || input.keys.pressing(Key.S)) velocity.y += 1.0

            if (velocity.length > 0) {
                velocity.normalize()
                velocity *= SPEED
                velocity *= scale
                playerParent.xy(x + velocity.x, y + velocity.y)
            }

            if (velocity.x > 0) changeSprite(SpriteLibrary.static.PLAYER_WALK_RIGHT_ANIM)
            else if (velocity.x < 0) changeSprite(SpriteLibrary.static.PLAYER_WALK_LEFT_ANIM)
            else if (velocity.y < 0) changeSprite(SpriteLibrary.static.PLAYER_WALK_UP_ANIM)
            else if (velocity.y > 0) changeSprite(SpriteLibrary.static.PLAYER_WALK_DOWN_ANIM)
            else changeSprite(SpriteLibrary.static.PLAYER_IDLE_ANIM, IDLE_FPS)
            physicsBody.velocity = velocity
            playerParent.pos = physicsBody.position
        }

        /*collisionShape.onCollision({ scene.collisionList.contains(it) }) {
            if (velocity.magnitude <= 0) return@onCollision

            val dir = playerParent.pos + collisionShape.pos - it.pos
            val normal = getNormal(it.rotation, dir)

            val tanUp = normal.rotate((kotlin.math.PI / 2).radians)
            val tanUpAngle = Vector2D.angle(velocity, tanUp)

            val tanDown = normal.rotate((kotlin.math.PI / -2).radians)
            val tanDownAngle = Vector2D.angle(velocity, tanUp)

            var tan = Vector2D()

            if (tanDownAngle > tanUpAngle) {
                tan = tanUp.normalized
            } else {
                tan = tanDown.normalized
            }

            tan = (tan + normal * 10E-2).normalized


            var scalar = scalar(velocity, tan)

            if (scalar < 10E-5) {
                scalar = 0.0
            }

            val vel = (tan * scalar - velocity)

            playerParent.xy(playerParent.x + vel.x, playerParent.y + vel.y)
        }*/



        /*collisionShape.onCollision {
            val movingObjects = mutableListOf<SolidRect>()
            for (movingObject in scene.movingObjectsList) movingObjects.add(movingObject.image)
            if (!movingObjects.contains(it)) return@onCollision
            val movingObject = scene.movingObjectsList[movingObjects.indexOf(it)]
            velocity = movingObject.velocity
            playerParent.xy(playerParent.pos.x + movingObject.velocity.x, playerParent.pos.y + movingObject.velocity.y)
        }*/
    }

    private fun getNormal(rotation: Angle, dir: Vector2D): Vector2D {
        val normals = listOf(Vector2D(0, 1), Vector2D(1, 0), Vector2D(0, -1), Vector2D(-1, 0))

        var minAngle = (181).degrees
        var correctNormal = Vector2D(0, 0)
        for (normal in normals) {
            val rotatedNormal = normal.rotate(rotation)
            val angle = Vector2D.angle(dir, rotatedNormal)
            if (angle < minAngle) {
                minAngle = angle
                correctNormal = rotatedNormal
            }
        }
        return correctNormal
    }

    private fun scalar(a: Vector2D, b: Vector2D): Double {
        return (a.x * b.x + a.y * b.y) / a.magnitude * b.magnitude
    }
}

