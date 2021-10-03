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
        val backgroundImage = ImageLibrary.loadImage(this, ImageLibrary.BACKGROUND_IMAGE_FLAT, 0.5, 0.5)

    }

    override suspend fun nextScene() {
        println("Level2")
        sceneContainer.changeTo<Level2>()
        sceneDestroy()
    }
}
