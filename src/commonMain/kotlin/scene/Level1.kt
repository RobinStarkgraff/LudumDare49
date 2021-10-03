package scene

import com.soywiz.korge.view.*
import com.soywiz.korge.view.tween.rotateBy
import com.soywiz.korma.geom.Vector2D
import com.soywiz.korma.geom.degrees
import manager.DownloadManager
import objects.*

class Level1 : Level() {
    override suspend fun Container.sceneInit() {

        downloadManager = DownloadManager(this@Level1)
        val death = solidRect(50, 50).xy(200, 200)
        MovingBlock(
            50.0,
            50.0,
            3.0,
            listOf(Vector2D(100, 100), Vector2D(100, 300), Vector2D(200, 300), Vector2D(200, 100)),
            true,
            this@Level1
        ).image
        player = Player(this@Level1)
        collisionList.add(death)
        death.anchor(0.5, .5)
        val router = WifiRouter(500.0, 500.0, 300.0, null, this@Level1)
        val phone = Phone()

        sceneView.addUpdater {
            sceneView.children.sortBy {
                it.pos.y
            }
        }
    }

    override suspend fun nextScene() {
        println("Level2")
        sceneContainer.changeTo<Level2>()
        sceneDestroy()
    }
}
