package scene

import objects.Player
import objects.MovingObject
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.solidRect
import com.soywiz.korge.view.xy
import com.soywiz.korma.geom.Vector2D
import manager.DownloadManager
import objects.WifiRouter

class Level1 : Level() {
    override suspend fun Container.sceneInit() {
        player = Player(this@Level1)
        downloadManager = DownloadManager(this@Level1)
        val death = solidRect(50, 50).xy(200, 200)
        deathZoneList.add(death)
        val router = WifiRouter(500.0, 500.0, 300.0, null, this@Level1)
    }

    override suspend fun nextScene() {
        println("Level2")
        sceneContainer.changeTo<Level2>()
        sceneDestroy()
    }
}
