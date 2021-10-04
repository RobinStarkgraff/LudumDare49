package scene

import objects.Player
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.sprite
import com.soywiz.korge.view.xy
import objects.Ferry
import RESOLUTION
import com.soywiz.korge.view.*
import com.soywiz.korma.geom.Vector2D
import helper.SoundPlayer
import helper.SpriteLibrary
import objects.Phone
import objects.WifiRouter
import objects.interactables.ObjectiveItem
import physics.PhysicsBody
import physics.primitives.BoxCollider

import kotlin.math.max
import kotlin.math.min

class Level2 : Level() {
    val level = PhysicsBody()

    override suspend fun Container.sceneInit() {
        spawnpoint = Vector2D(430, 250)

        var router = WifiRouter(22.0, 834.0, 100.0, this@Level2)
        SoundPlayer.playBackgroundMusic(SoundPlayer.BGM2, - 0.3)
        drawImages()
        phone = Phone(this@Level2)
        moveContainer(520.0, 400.0, 760.0, 1460.0)
        val turtleSprite = bg.sprite(SpriteLibrary.PARK_TURTLE).xy(410, 1640)
        val turtle = Ferry(
            turtleSprite,
            mutableListOf(Vector2D(410, 1640), Vector2D(300, 1600)),
            1.0,
            this@Level2,
            1800
        )
        interactableList.add(turtle)

        val crocodileSprite = bg.sprite(SpriteLibrary.PARK_CROCUP).xy(125, 1250)
        val crocodile = Ferry(
            crocodileSprite,
            mutableListOf( Vector2D(100, 1250), Vector2D(69, 800)),
            1.0,
            this@Level2,
            5500
        )
        crocodile.interactionDistance = 200.0
        interactableList.add(crocodile)
        player = Player(this@Level2)
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
        bg.sprite(SpriteLibrary.PARK_PARK).anchor(0.5, 0.0).xy(RESOLUTION.x / 2, -50.0)
        bg.sprite(SpriteLibrary.PARK_LANDINGSTAGE).anchor(0.5, 0.0).xy(RESOLUTION.x / 2, -50.0)

        BoxCollider(il.sprite(SpriteLibrary.PARK_WALL).xy(-107, 420), level)
        BoxCollider(il.sprite(SpriteLibrary.PARK_WALL_BOTTOM).anchor(0.0, 0.5).xy(517, 1755), level)
        BoxCollider(il.sprite(SpriteLibrary.PARK_WIFI_STONE).anchor(0.0, 0.5).xy(-30, 775), level)
        BoxCollider(il.sprite(SpriteLibrary.PARK_CAULIFLOWER).anchor(0.0, 0.5).xy(1047, 300), level)
        BoxCollider(il.sprite(SpriteLibrary.PARK_PUMPKINS).anchor(0.0, 0.5).xy(1237, 300), level)
        BoxCollider(il.sprite(SpriteLibrary.PARK_TURNIPS).anchor(0.0, 0.5).xy(1161, 140), level)
        BoxCollider(il.sprite(SpriteLibrary.PARK_LILY_PAD).xy(180, 1107), level)
        BoxCollider(il.sprite(SpriteLibrary.PARK_LILY_PAD).xy(300, 1400), level)
        BoxCollider(il.sprite(SpriteLibrary.PARK_LILY_PAD).xy(0, 1300), level)
        BoxCollider(il.sprite(SpriteLibrary.PARK_LILY_PAD).xy(-40, 1000), level)
        BoxCollider(il.sprite(SpriteLibrary.PARK_ROCK).xy(-50, 1700), level)
        BoxCollider(il.sprite(SpriteLibrary.PARK_ROCK).xy(-50, 1200), level)
        BoxCollider(il.sprite(SpriteLibrary.PARK_ROCK).xy(-107, 580), level)
        BoxCollider(il.sprite(SpriteLibrary.PARK_TREE_ONE).anchor(0.0, 0.8).xy(681, 677), level)
        BoxCollider(il.sprite(SpriteLibrary.PARK_TREE_TWO).anchor(0.0, 0.8).xy(504, 775), level)
        BoxCollider(il.sprite(SpriteLibrary.PARK_TREE_THREE).anchor(0.0, 0.8).xy(1100, 805), level)
        BoxCollider(il.sprite(SpriteLibrary.PARK_TREE_TWO).anchor(0.0, 0.8).xy(585, 1060), level)
        BoxCollider(il.sprite(SpriteLibrary.PARK_TREE_THREE).anchor(0.0, 0.8).xy(860, 265), level)
        BoxCollider(il.sprite(SpriteLibrary.PARK_TREE_ONE).anchor(0.0, 0.8).xy(806, 386), level)
        BoxCollider(il.sprite(SpriteLibrary.PARK_TREE_ONE).anchor(0.0, 0.8).xy(768, 1352), level)
        BoxCollider(il.sprite(SpriteLibrary.PARK_WALL).xy(-107, 420),level)
        BoxCollider(il.sprite(SpriteLibrary.PARK_WALL_BOTTOM).anchor(0.0, 0.5).xy(517, 1755),level)
        BoxCollider(il.sprite(SpriteLibrary.PARK_WIFI_STONE).anchor(0.0,0.5).xy(-30, 775),level)
        BoxCollider(il.sprite(SpriteLibrary.PARK_CAULIFLOWER).anchor(0.0, 0.5).xy(1047, 300),level)
        BoxCollider(il.sprite(SpriteLibrary.PARK_PUMPKINS).anchor(0.0, 0.5).xy(1237, 300),level)
        BoxCollider(il.sprite(SpriteLibrary.PARK_TURNIPS).anchor(0.0, 0.5).xy(1161, 140),level)
        BoxCollider(il.sprite(SpriteLibrary.PARK_LILY_PAD).xy(180, 1107),level)
        BoxCollider(il.sprite(SpriteLibrary.PARK_LILY_PAD).xy(300, 1400),level)
        BoxCollider(il.sprite(SpriteLibrary.PARK_LILY_PAD).xy(0, 1300),level)
        BoxCollider(il.sprite(SpriteLibrary.PARK_LILY_PAD).xy(-40, 1000),level)
        BoxCollider(il.sprite(SpriteLibrary.PARK_ROCK).xy(-50, 1700),level)
        BoxCollider(il.sprite(SpriteLibrary.PARK_ROCK).xy(-50, 1200),level)
        BoxCollider(il.sprite(SpriteLibrary.PARK_ROCK).xy(-107, 580),level)
        il.sprite(SpriteLibrary.PARK_TREE_ONE).anchor(0.5,1.0).xy(681, 677)
        BoxCollider(Vector2D(681, 677), 30.0, 5.0, level)
        il.sprite(SpriteLibrary.PARK_TREE_TWO).anchor(0.5,1.0).xy(504, 775)
        BoxCollider(Vector2D(504, 775), 30.0, 5.0, level)
        il.sprite(SpriteLibrary.PARK_TREE_THREE).anchor(0.5,1.0).xy(1100, 805)
        BoxCollider(Vector2D(1100, 805), 30.0, 5.0, level)
        il.sprite(SpriteLibrary.PARK_TREE_TWO).anchor(0.5,1.0).xy(585, 1060)
        BoxCollider(Vector2D(585, 1060), 30.0, 5.0, level)
        il.sprite(SpriteLibrary.PARK_TREE_THREE).anchor(0.5,1.0).xy(860, 265)
        BoxCollider(Vector2D(860, 265), 30.0, 5.0, level)
        il.sprite(SpriteLibrary.PARK_TREE_ONE).anchor(0.5,1.0).xy(806, 386)
        BoxCollider(Vector2D(806, 386), 30.0, 5.0, level)
        il.sprite(SpriteLibrary.PARK_TREE_ONE).anchor(0.5,1.0).xy(768, 1352)
        BoxCollider(Vector2D(768, 1352), 30.0, 5.0, level)

        //Collider beach
        BoxCollider(Vector2D(23, 100), 3000.0, 5.0, level)
        BoxCollider(Vector2D(-75, 100), 5.0, 3000.0, level)
        BoxCollider(Vector2D(1355, 100), 5.0, 5000.0, level)
        BoxCollider(Vector2D(485, 1800), 50.0, 245.0, level)
        BoxCollider(Vector2D(430, 1660), 50.0, 550.0, level)
        BoxCollider(Vector2D(485, 1550), 50.0, 150.0, level)
        BoxCollider(Vector2D(380, 1700), 50.0, 1000.0, level)
        BoxCollider(Vector2D(330, 1200), 50.0, 100.0, level)
        BoxCollider(Vector2D(280, 1150), 50.0, 100.0, level)
        BoxCollider(Vector2D(230, 1050), 50.0, 800.0, level)
        BoxCollider(Vector2D(200, 670), 50.0, 100.0, level)
        BoxCollider(Vector2D(150, 620), 50.0, 100.0, level)
        BoxCollider(Vector2D(100, 620), 50.0, 100.0, level)
        BoxCollider(Vector2D(0, 570), 50.0, 100.0, level)
        BoxCollider(Vector2D(50, 570), 50.0, 100.0, level)
        BoxCollider(Vector2D(-50, 520), 50.0, 100.0, level)

        //zaun
        BoxCollider(Vector2D(1065, 175), 100.0, 5.0, level)
        BoxCollider(Vector2D(1065, 410), 150.0, 5.0, level)
        BoxCollider(Vector2D(1000, 290), 5.0, 250.0, level)
        BoxCollider(Vector2D(1330, 255), 5.0, 285.0, level)
        BoxCollider(Vector2D(1330, 410), 200.0, 5.0, level)
        BoxCollider(Vector2D(1100, 0), 5.0, 350.0, level)

        //islands
        BoxCollider(Vector2D(330, 1520), 60.0, 100.0, level)
        BoxCollider(Vector2D(380, 1700), 50.0, 1000.0, level)
        BoxCollider(Vector2D(320, 1680), 50.0, 100.0, level)
        BoxCollider(Vector2D(280, 1730), 50.0, 100.0, level)
        BoxCollider(Vector2D(230, 1780), 50.0, 100.0, level)
        BoxCollider(Vector2D(180, 1780), 50.0, 100.0, level)
        BoxCollider(Vector2D(130, 1730), 50.0, 100.0, level)
        BoxCollider(Vector2D(80, 1730), 50.0, 100.0, level)
        BoxCollider(Vector2D(50, 1680), 50.0, 100.0, level)
        BoxCollider(Vector2D(0, 1630), 50.0, 100.0, level)
        BoxCollider(Vector2D(-50, 1580), 50.0, 100.0, level)
        BoxCollider(Vector2D(-50, 1480), 50.0, 100.0, level)
        BoxCollider(Vector2D(280, 1450), 50.0, 100.0, level)
        BoxCollider(Vector2D(230, 1400), 50.0, 100.0, level)
        BoxCollider(Vector2D(230, 1300), 50.0, 100.0, level)
        BoxCollider(Vector2D(180, 1300), 50.0, 100.0, level)
        BoxCollider(Vector2D(150, 1300), 50.0, 100.0, level)
        BoxCollider(Vector2D(100, 1350), 50.0, 100.0, level)
        BoxCollider(Vector2D(50, 1400), 50.0, 100.0, level)
        BoxCollider(Vector2D(0, 1400), 50.0, 100.0, level)

        BoxCollider(Vector2D(180, 850), 50.0, 100.0, level)
        BoxCollider(Vector2D(130, 950), 50.0, 100.0, level)
        BoxCollider(Vector2D(80, 1000), 50.0, 100.0, level)
        BoxCollider(Vector2D(30, 1000), 50.0, 100.0, level)
        BoxCollider(Vector2D(0, 950), 50.0, 100.0, level)
        BoxCollider(Vector2D(-50, 900), 50.0, 100.0, level)
        BoxCollider(Vector2D(-100, 850), 50.0, 100.0, level)
        BoxCollider(Vector2D(-100, 800), 50.0, 100.0, level)
        BoxCollider(Vector2D(-100, 780), 50.0, 100.0, level)
        BoxCollider(Vector2D(-50, 730), 50.0, 100.0, level)
        BoxCollider(Vector2D(0, 630), 50.0, 100.0, level)
        BoxCollider(Vector2D(50, 630), 50.0, 100.0, level)
        BoxCollider(Vector2D(80, 680), 50.0, 100.0, level)
        BoxCollider(Vector2D(130, 730), 50.0, 100.0, level)
    }

    override val objective: ObjectiveItem
        get() = TODO("Not yet implemented")



    override fun downloadComplete() {
        TODO("Not yet implemented")
    }

    override suspend fun nextScene() {
        super.nextScene()
        sceneContainer.changeTo<MenuScene>()
    }
}
