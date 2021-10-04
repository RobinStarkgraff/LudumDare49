package scene

import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.Container
import helper.ScreenDebugger
import helper.SpriteLibrary

class LoadingScene() : Scene() {
    override suspend fun Container.sceneInit() {
		SpriteLibrary.init()
		sceneContainer.changeTo<MenuScene>()
        ScreenDebugger(sceneContainer)
    }
}
