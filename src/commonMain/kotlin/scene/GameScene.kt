package scene

import Player
import com.soywiz.korge.view.Container
import manager.LevelManager
import scenes.Level

class GameScene : Level() {
    override suspend fun Container.sceneInit() {
        levelManager = LevelManager(this@GameScene)
        player = Player(this@GameScene)
    }
}
