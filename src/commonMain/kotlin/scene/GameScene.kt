package scene

import Player
import com.soywiz.korge.view.Container
import manager.LevelManager
import scenes.Level

class GameScene : Level() {
    override suspend fun Container.sceneInit() {
        val player = Player(this@GameScene)
    }
}
