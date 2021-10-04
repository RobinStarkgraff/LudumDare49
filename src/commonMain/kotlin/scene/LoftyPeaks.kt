package scene

import RESOLUTION
import com.soywiz.klock.milliseconds
import com.soywiz.korge.view.*
import com.soywiz.korma.geom.Vector2D
import helper.SoundPlayer
import helper.SpriteLibrary
import objects.Car
import objects.Phone
import objects.Player
import objects.WifiRouter
import objects.interactables.ObjectiveItem
import physics.PhysicsBody
import physics.primitives.BoxCollider
import physics.trigger.BoxTrigger
import kotlin.math.max
import kotlin.math.min

class LoftyPeaks() : Level() {

    override lateinit var objective: ObjectiveItem


    override suspend fun Container.sceneInit() {
        var mapCenter = Vector2D(RESOLUTION.x / 2, RESOLUTION.y / 2)
        var halfSize = Vector2D(SpriteLibrary.LOFTYPEAKS_BACKGROUND.width, SpriteLibrary.LOFTYPEAKS_BACKGROUND.height) / 2
        spawnpoint = Vector2D(17 * 4, 124 * 4) + mapCenter
        player = Player(this@LoftyPeaks)
        //var phone = Phone(this@LoftyPeaks)
        SoundPlayer.playBackgroundMusic(SoundPlayer.BGMMENU)
        drawImages()
        moveContainer(0.0, 0.0, 760.0, 1460.0)


    }

    fun moveContainer(minX: Double, minY: Double, maxX: Double, maxY: Double) {
        sceneView.addUpdater {
            player?.playerParent?.pos?.let { it1 ->
                var posX = it1.x
                posX = max(minX, posX)
                posX = min(maxX, posX)

                var posY = it1.y
                posY = max(minY, posY)
                posY = min(maxY, posY)

                sceneView.parent?.xy(-posX + (RESOLUTION.x / 2), -posY + (RESOLUTION.y / 2))
            }


        }
    }

    override suspend fun drawImages() {
        super.drawImages()
        var mapCenter = Vector2D(RESOLUTION.x / 2, RESOLUTION.y / 2)
        bg.sprite(SpriteLibrary.LOFTYPEAKS_BACKGROUND).anchor(0.5, 0.5).xy(mapCenter)
        il.sprite(SpriteLibrary.LOFTYPEAKS_PILGRIM_A).anchor(0.5, .8).xy(mapCenter + Vector2D(-28, -21) * 4)
        il.sprite(SpriteLibrary.LOFTYPEAKS_PILGRIM_C).anchor(0.5, .8).xy(mapCenter + Vector2D(-47, -24) * 4)
        il.sprite(SpriteLibrary.LOFTYPEAKS_PILGRIM_C).anchor(0.5, .8).xy(mapCenter + Vector2D(-76, 16) * 4)
        il.sprite(SpriteLibrary.LOFTYPEAKS_PILGRIM_A).anchor(0.5, .8).xy(mapCenter + Vector2D(55, 40) * 4)
        il.sprite(SpriteLibrary.LOFTYPEAKS_PILGRIM_B).anchor(0.5, .8).xy(mapCenter + Vector2D(14, 59) * 4)
        il.sprite(SpriteLibrary.LOFTYPEAKS_PILGRIM_A).anchor(0.5, .8).xy(mapCenter + Vector2D(58, 96) * 4)
        il.sprite(SpriteLibrary.LOFTYPEAKS_PILGRIM_B).anchor(0.5, .8).xy(mapCenter + Vector2D(49, 21) * 4)
        il.sprite(SpriteLibrary.LOFTYPEAKS_PILGRIM_B).anchor(0.5, .8).xy(mapCenter + Vector2D(61, 98) * 4)
        il.sprite(SpriteLibrary.LOFTYPEAKS_PILGRIM_C).anchor(0.5, .8).xy(mapCenter + Vector2D(80, 90) * 4)
        val levelPhysicsBody = PhysicsBody()
        BoxCollider(mapCenter + Vector2D(19 * 4, 86 * 4), 50.0 * 4, 13.0 * 4, levelPhysicsBody)
        BoxCollider(mapCenter + Vector2D(-31 * 4, 98 * 4), 12.0 * 4, 48.0 * 4, levelPhysicsBody)
        BoxCollider(mapCenter + Vector2D(-15 * 4, 116 * 4), 22.0 * 4, 10.0 * 4, levelPhysicsBody)
        BoxCollider(mapCenter + Vector2D(-9 * 4, 131 * 4), 10.0 * 4, 22.0 * 4, levelPhysicsBody)
        BoxCollider(mapCenter + Vector2D(16 * 4, 138 * 4), 55.0 * 4, 9.0 * 4, levelPhysicsBody)
        BoxCollider(mapCenter + Vector2D(38 * 4, 112 * 4), 11.0 * 4, 46.0 * 4, levelPhysicsBody)
        BoxCollider(mapCenter + Vector2D(-5 * 4, 56 * 4), 8.0 * 4, 49.0 * 4, levelPhysicsBody)
        BoxCollider(mapCenter + Vector2D(-51 * 4, 81 * 4), 52.0 * 4, 17.0 * 4, levelPhysicsBody)
        BoxCollider(mapCenter + Vector2D(-70 * 4, 38 * 4), 10.0 * 4, 72.0 * 4, levelPhysicsBody)
        BoxCollider(mapCenter + Vector2D(-29 * 4, 5 * 4), 71.0 * 4, 21.0 * 4, levelPhysicsBody)
        BoxCollider(mapCenter + Vector2D(24 * 4, 34 * 4), 45.0 * 4, 12.0 * 4, levelPhysicsBody)
        BoxCollider(mapCenter + Vector2D(34 * 4, 26 * 4), 24.0 * 4, 6.0 * 4, levelPhysicsBody)
        BoxCollider(mapCenter + Vector2D(35 * 4, -6 * 4), 10.0 * 4, 55.0 * 4, levelPhysicsBody)
        BoxCollider(mapCenter + Vector2D(-14 * 4, -36 * 4), 7.0 * 4, 57.0 * 4, levelPhysicsBody)
        BoxCollider(mapCenter + Vector2D(24 * 4, -45 * 4), 33.0 * 4, 37.0 * 4, levelPhysicsBody)
        BoxCollider(mapCenter + Vector2D(12 * 4, -71 * 4), 56.0 * 4, 14.0 * 4, levelPhysicsBody)


        var boxTrigger = BoxTrigger(mapCenter + Vector2D(-3, -61) * 4, 16.0, 6.0) {
            player?.say("Thanks for playing :]", 10000)
        }
    }

    override fun downloadComplete() {

    }

    override suspend fun nextScene() {
        //sceneDestroy()
        //sceneContainer.changeTo<Level2>()
    }
}