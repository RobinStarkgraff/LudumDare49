package scene

import RESOLUTION
import com.soywiz.klock.milliseconds
import com.soywiz.korge.view.*
import com.soywiz.korma.geom.Vector2D
import objects.Player
import helper.SoundPlayer
import helper.SpriteLibrary
import manager.DownloadManager
import kotlin.math.max
import kotlin.math.min

class Level2 : Level() {

    override suspend fun Container.sceneInit() {
        spawnpoint = Vector2D(430, 250)
        player = Player(this@Level2)
        downloadManager = DownloadManager(this@Level2)
        SoundPlayer.playBackgroundMusic(SoundPlayer.BGM1)
        drawImages()
        moveContainer(520.0, 400.0, 760.0, 1460.0)
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

                sceneView.parent?.xy(-posX +(RESOLUTION.x / 2), -posY + (RESOLUTION.y /2))
            }
        }
    }

    override suspend fun drawImages() {
        super.drawImages()
        bg.sprite(SpriteLibrary.PARK_PARK).anchor(0.5, 0.0).xy(RESOLUTION.x / 2, -50.0)
        bg.sprite(SpriteLibrary.PARK_LANDINGSTAGE).anchor(0.5, 0.0).xy(RESOLUTION.x / 2, -50.0)

        val croc = il.sprite(SpriteLibrary.PARK_CROCDOWN).xy(143, 1207)
        BoxCollider.add(croc)
        croc.playAnimationLooped(spriteDisplayTime = 200.milliseconds)

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
        BoxCollider(il.sprite(SpriteLibrary.PARK_TREE_ONE).anchor(0.0,0.8).xy(681, 677),level)
        BoxCollider(il.sprite(SpriteLibrary.PARK_TREE_TWO).anchor(0.0,0.8).xy(504, 775),level)
        BoxCollider(il.sprite(SpriteLibrary.PARK_TREE_THREE).anchor(0.0,0.8).xy(1100, 805),level)
        BoxCollider(il.sprite(SpriteLibrary.PARK_TREE_TWO).anchor(0.0,0.8).xy(585, 1060),level)
        BoxCollider(il.sprite(SpriteLibrary.PARK_TREE_THREE).anchor(0.0,0.8).xy(860, 265),level)
        BoxCollider(il.sprite(SpriteLibrary.PARK_TREE_ONE).anchor(0.0,0.8).xy(806, 386),level)
        BoxCollider(il.sprite(SpriteLibrary.PARK_TREE_ONE).anchor(0.0,0.8).xy(768, 1352),level)







    }

    override suspend fun nextScene() {
        println("park")
        sceneContainer.changeTo<MenuScene>()
        sceneDestroy()
    }
}