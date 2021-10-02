package scene

import objects.Player
import com.soywiz.korge.view.Container

class GameScene : Level() {
    override suspend fun Container.sceneInit() {
        val player = Player(this@GameScene)
    }
}
