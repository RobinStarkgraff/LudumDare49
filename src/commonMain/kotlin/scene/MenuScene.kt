package scene

import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.solidRect
import com.soywiz.korim.color.Colors


class MenuScene : Scene() {
    override suspend fun Container.sceneInit() {
        println("Level1")
        sceneContainer.changeTo<Level1>()

    }
}
