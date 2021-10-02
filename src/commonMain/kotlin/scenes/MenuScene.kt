package scenes

import classes.MovingObject
import classes.Player
import Scenes.Level
import com.soywiz.korge.view.Container
import com.soywiz.korma.geom.Vector2D

class MenuScene() : Level() {
    lateinit var player: Player
    override val spawnpoint: Vector2D = Vector2D(200, 200);
    override suspend fun Container.sceneInit() {

        player = Player(this@MenuScene)
        val movingObject = MovingObject(25.0, 25.0, 5.0, listOf(Vector2D(0, 0), Vector2D(300, 0), Vector2D(300, 300), Vector2D(0, 300)), true, this@MenuScene)
        deathZoneList.add(movingObject.image)
    }
}