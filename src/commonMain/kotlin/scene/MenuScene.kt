package scene

import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.Container


class MenuScene : Scene() {
    override suspend fun Container.sceneInit() {
        println("Level1")
        sceneContainer.changeTo<Level1>()
    }
}
