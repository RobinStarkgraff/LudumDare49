package scene

import objects.Player
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.sprite
import com.soywiz.korge.view.xy
import com.soywiz.korma.geom.Vector2D
import helper.SpriteLibrary
import objects.Ferry

class Level2 : Level() {
    override suspend fun Container.sceneInit() {
        spawnpoint = Vector2D(430, 250)
        player = Player(this@Level2)
        val sprite = sceneView.sprite(SpriteLibrary.LEVEL1_COUCH).xy(100, 100)
        val movingObject = Ferry(
                sprite,
                mutableListOf(Vector2D(100, 100), Vector2D(100, 400)),
                1.0,
                this@Level2
        )
        drawImages()
    }

    override suspend fun nextScene() {
        println("MenuScene")
        sceneContainer.changeTo<MenuScene>()
    }
}
