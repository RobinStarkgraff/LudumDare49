package scene

import RESOLUTION
import com.soywiz.korge.view.*
import com.soywiz.korma.geom.Vector2D
import helper.SoundPlayer
import helper.SpriteLibrary
import objects.*
import physics.PhysicsBody
import physics.primitives.BoxCollider

class IntersectionLevel : Level() {
    override suspend fun Container.sceneInit() {
        spawnpoint = Vector2D(355, 280)
        player = Player(this@IntersectionLevel)
        phone = Phone(this@IntersectionLevel)
        SoundPlayer.playBackgroundMusic(SoundPlayer.BGM1)
        drawImages()
        WifiRouter(605.0, 505.0, 100.0, null, this@IntersectionLevel)
    }

    override suspend fun drawImages() {
        super.drawImages()
        bg.sprite(SpriteLibrary.INTERSECTIONLEVEL_BASE).anchor(0.5, 0.5).xy(RESOLUTION.x/2, RESOLUTION.y/2)
        val level = PhysicsBody()

        BoxCollider(Vector2D(RESOLUTION.x/2, 240.0), 1500.0, 5.0, level)
        BoxCollider(Vector2D(630, 710), 1000.0, 5.0, level)
        BoxCollider(Vector2D(157.0, (RESOLUTION.y/2)), 5.0, 700.0, level)
        BoxCollider(Vector2D(1120.0, (RESOLUTION.y/2)), 5.0, 700.0, level)

        val car1sprite = il.sprite(SpriteLibrary.INTERSECTIONLEVEL_CAR).anchor(0.5, 0.0).xy(300.0, 420.0)
        val car2sprite = il.sprite(SpriteLibrary.INTERSECTIONLEVEL_CAR).anchor(0.5, 0.0).xy(300.0, 520.0)
        val car3sprite = il.sprite(SpriteLibrary.INTERSECTIONLEVEL_CAR).anchor(0.5, 0.0).xy(300.0, 470.0)
        car2sprite.width = -car2sprite.width
        car3sprite.width = -car3sprite.width

        val car1 = Car(
            car1sprite,
            150.0,
            listOf(Vector2D(-120, 420), Vector2D(1400, 420)),
            true,
            this
        )

        val car2 = Car(
            car2sprite,
            150.0,
            listOf(Vector2D(1400, 520), Vector2D(-120, 520)),
            true,
            this
        )

        val car3 = Car(
            car3sprite,
            200.0,
            listOf(Vector2D(1400, 470), Vector2D(-120, 470)),
            true,
            this
        )
    }

    override suspend fun nextScene() {
        println("Level3")
        sceneDestroy()
        sceneContainer.changeTo<Level2>()

    }
}
