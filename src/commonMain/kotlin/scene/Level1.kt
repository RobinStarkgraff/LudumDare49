package scene

import objects.Player
import classes.MovingObject
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.solidRect
import com.soywiz.korge.view.xy
import com.soywiz.korma.geom.Vector2D
import manager.DownloadManager

class Level1 : Level() {
    override suspend fun Container.sceneInit() {
        player = Player(this@Level1)
        downloadManager = DownloadManager(this@Level1)
        val death = solidRect(50, 50).xy(200, 200)
        deathZoneList.add(death)
    }

    override suspend fun sceneDestroy() {

    }

    override suspend fun nextScene() {
        println("Level2")
        sceneContainer.changeTo<Level2>()
        sceneDestroy()
    }
}
