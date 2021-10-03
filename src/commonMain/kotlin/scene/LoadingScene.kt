package scene

import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.Container
import helper.SpriteLibrary

class LoadingScene() : Scene() {
    override suspend fun Container.sceneInit() {
        val spriteLibrary = SpriteLibrary()
		spriteLibrary.init()
		sceneContainer.changeTo<MenuScene>()
    }
}
