package scene

import RESOLUTION
import com.soywiz.korge.view.*
import com.soywiz.korma.geom.Vector2D
import objects.Player
import helper.SoundPlayer
import helper.SpriteLibrary
import objects.MovingObject
import objects.Phone
import objects.WifiRouter
import physics.PhysicsBody
import physics.primitives.BoxCollider

class IntersectionLevel : Level() {
    override suspend fun Container.sceneInit() {
        spawnpoint = Vector2D(355, 250)
        player = Player(this@IntersectionLevel)
        phone = Phone(this@IntersectionLevel)
        SoundPlayer.playBackgroundMusic(SoundPlayer.BGM1)
        drawImages()
        WifiRouter(RESOLUTION.x / 2, RESOLUTION.y / 2, 100.0, null, this@IntersectionLevel)
    }

    override suspend fun drawImages() {
        super.drawImages()
        bg.sprite(SpriteLibrary.INTERSECTIONLEVEL_BASE).anchor(0.5, 0.5).xy(RESOLUTION.x/2, RESOLUTION.y/2)
        bg.sprite(SpriteLibrary.INTERSECTIONLEVEL_HOUSE).xy(180.0, 30.0)
        val level = PhysicsBody()


        BoxCollider(bg.sprite(SpriteLibrary.INTERSECTIONLEVEL_HOUSE_FENCE_SOUTH).xy(160.0, 205.0), level)
        BoxCollider(bg.sprite(SpriteLibrary.INTERSECTIONLEVEL_HOUSE_FENCE_EAST).xy(494.0, 17.0), level)

        val fenceGardenNorth = BoxCollider(bg.sprite(SpriteLibrary.INTERSECTIONLEVEL_GARDEN_FENCE_NORTH).xy(195.0, 445.0), level)
        fenceGardenNorth.position.y = 487.8
        fenceGardenNorth.height = 25.0

        BoxCollider(bg.sprite(SpriteLibrary.INTERSECTIONLEVEL_GARDEN_FENCE_SOUTH).xy(195.0, 625.0), level)
        BoxCollider(bg.sprite(SpriteLibrary.INTERSECTIONLEVEL_GARDEN_FENCE_WESTEAST).xy(468.0, 475.5), level)
        BoxCollider(bg.sprite(SpriteLibrary.INTERSECTIONLEVEL_GARDEN_FENCE_WESTEAST).xy(180.0, 475.5), level)

        BoxCollider(bg.sprite(SpriteLibrary.INTERSECTIONLEVEL_TREE_1).xy(930.0, 30.0), level)
        BoxCollider(bg.sprite(SpriteLibrary.INTERSECTIONLEVEL_TREE_2).xy(820.0, 110.0), level)
        BoxCollider(bg.sprite(SpriteLibrary.INTERSECTIONLEVEL_TREE_3).xy(720.0, 30.0), level)

        BoxCollider(Vector2D(630, 70), 1000.0, 5.0, level)
        BoxCollider(Vector2D(630, 710), 1000.0, 5.0, level)
        BoxCollider(Vector2D(157.0, (RESOLUTION.y/2)), 5.0, 700.0, level)
        BoxCollider(Vector2D(1120.0, (RESOLUTION.y/2)), 5.0, 700.0, level)

        val car = BoxCollider(il.sprite(SpriteLibrary.INTERSECTIONLEVEL_CAR).xy(300.0, 300.0), level)

        val movingObject = MovingObject(
            50.0,
            25.0,
            50.0,
            listOf(Vector2D(1030, 300), Vector2D(90, 300)),
            true,
            this
        )

        BoxCollider(movingObject.image.xy(1030, 300), level)
    }

    override suspend fun nextScene() {
//        println("Level3")
//        sceneDestroy()
//        sceneContainer.changeTo<Level2>()

    }
}
