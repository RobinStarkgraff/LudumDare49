package scene

import objects.Player
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.xy
import com.soywiz.korma.geom.Vector2D
import objects.MovingObject

class Level2 : Level() {
    override suspend fun Container.sceneInit() {
        spawnpoint = Vector2D(430, 250)
        player = Player(this@Level2)
        val movingObject = MovingObject(
                50.0,
                25.0,
                50.0,
                listOf(Vector2D(100, 100), Vector2D(400, 100), Vector2D(300, 300), Vector2D(100, 300)),
                true,
                this@Level2
        )
        movingObject.image.xy(100, 100)
    }

    override suspend fun nextScene() {
        println("MenuScene")
        sceneContainer.changeTo<MenuScene>()
    }
}
