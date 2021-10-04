package scene

import RESOLUTION
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
import kotlin.math.max
import kotlin.math.min

class LoftyPeaks() : Level() {

    override lateinit var objective: ObjectiveItem


    override suspend fun Container.sceneInit() {
        var mapCenter = Vector2D(RESOLUTION.x / 2, RESOLUTION.y / 2)
        var halfSize = Vector2D(SpriteLibrary.LOFTYPEAKS_BACKGROUND.width, SpriteLibrary.LOFTYPEAKS_BACKGROUND.height) / 2
        spawnpoint = Vector2D(17 * 4, 124 * 4) + mapCenter
        player = Player(this@LoftyPeaks)
        var phone = Phone(this@LoftyPeaks)
        addChild(phone.container)
        SoundPlayer.playBackgroundMusic(SoundPlayer.BGMMENU)
        drawImages()
        moveContainer(0.0, 400.0, 760.0, 1460.0)


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
        val levelPhysicsBody = PhysicsBody()
        BoxCollider(mapCenter + Vector2D(19 * 4, 86 * 4), 50.0 * 4, 13.0 * 4, levelPhysicsBody)
    }

    override fun downloadComplete() {

    }

    override suspend fun nextScene() {
        //sceneDestroy()
        //sceneContainer.changeTo<Level2>()
    }
}