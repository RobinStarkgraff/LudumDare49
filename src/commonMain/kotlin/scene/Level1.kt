package scene

import com.soywiz.korge.view.*
import com.soywiz.korim.color.Colors
import objects.Player
import objects.MovingObject
import com.soywiz.korma.geom.Vector2D
import helper.ImageLibrary
import helper.SoundPlayer
import manager.DownloadManager
import objects.Phone
import objects.WifiRouter

class Level1 : Level() {
    override suspend fun Container.sceneInit() {
        player = Player(this@Level1)
        downloadManager = DownloadManager(this@Level1)
        SoundPlayer.playBackgroundMusic(SoundPlayer.BGM1)
        drawImages()
    }

    override suspend fun drawImages() {
        super.drawImages()
        val backgroundImage = ImageLibrary.loadImage(this, ImageLibrary.BACKGROUND_IMAGE_FLAT, 0.0, 0.0)
        val light = ImageLibrary.loadImage(this,ImageLibrary.FOREGROUND_LIGHT_FLAT, 0.0,0.0)
        val bathtub = ImageLibrary.loadImage(this, ImageLibrary.BATHTUB, 0.0, 0.0).xy(100,100)
        val bookshelf = ImageLibrary.loadImage(this, ImageLibrary.BOOKSHELF, 0.0, 0.0)
        val couch = ImageLibrary.loadImage(this, ImageLibrary.COUCH, 0.0,0.0)
        val couchtable = ImageLibrary.loadImage(this, ImageLibrary.COUCHTABLE, 0.0, 0.0)
        val desk = ImageLibrary.loadImage(this, ImageLibrary.DESK, 0.0, 0.0)
        val kitchen_assembly = ImageLibrary.loadImage(this, ImageLibrary.KITCHEN_ASSEMBLY, 0.0, 0.0)
        val mirror = ImageLibrary.loadImage(this, ImageLibrary.MIRROR, 0.0, 0.0)
        val night_stand = ImageLibrary.loadImage(this, ImageLibrary.NIGHT_STAND, 0.0, 0.0)


    }

    override suspend fun nextScene() {
        println("Level2")
        sceneContainer.changeTo<Level2>()
        sceneDestroy()
    }
}
