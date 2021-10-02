package scene

import Player
import classes.MovingObject
import com.soywiz.korge.view.Container
import com.soywiz.korma.geom.Vector2D
import manager.DownloadManager

class Level2 : Level() {
    override suspend fun Container.sceneInit() {
        player = Player(this@Level2)
        downloadManager = DownloadManager(this@Level2)
        val movingObject = MovingObject(
            50.0,
            25.0,
            5.0,
            listOf(Vector2D(0, 0), Vector2D(300, 0), Vector2D(300, 300), Vector2D(0, 300)),
            true,
            this@Level2
        )
        deathZoneList.add(movingObject.image)
    }

    override suspend fun nextScene() {
        println("MenuScene")
        sceneContainer.changeTo<MenuScene>()
    }
}
