package scene

import objects.Player
import objects.MovingObject
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.solidRect
import com.soywiz.korge.view.xy
import com.soywiz.korma.geom.Vector2D
import manager.DownloadManager
import objects.Phone
import objects.WifiRouter

class Level1 : Level() {
    override suspend fun Container.sceneInit() {
        player = Player(this@Level1)
        downloadManager = DownloadManager(this@Level1)
        val death = solidRect(50, 50).xy(200, 200)
        deathZoneList.add(death)
        val router = WifiRouter(500.0, 500.0, 300.0, null, this@Level1)
        val movingObject = MovingObject(
            25.0,
            25.0,
            5.0,
            listOf(Vector2D(0, 0), Vector2D(300, 0), Vector2D(300, 300), Vector2D(0, 300)),
            true,
            this@Level1
        )
        deathZoneList.add(movingObject.image)
        val phone = Phone()
        addChild(phone.container)
    }

    override suspend fun nextScene() {
        println("Level2")
        sceneContainer.changeTo<Level2>()
        sceneDestroy()
    }
}
