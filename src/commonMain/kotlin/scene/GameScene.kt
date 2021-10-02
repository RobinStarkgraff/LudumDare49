package scene

import Player
import classes.MovingObject
import com.soywiz.korge.view.Container
import com.soywiz.korma.geom.Vector2D
import scenes.Level

class GameScene : Level() {
    override suspend fun Container.sceneInit() {
        val player = Player(this@GameScene)
        val movingObject = MovingObject(
            25.0,
            25.0,
            5.0,
            listOf(Vector2D(0, 0), Vector2D(300, 0), Vector2D(300, 300), Vector2D(0, 300)),
            true,
            this@GameScene
        )
        deathZoneList.add(movingObject.image)
    }
}
