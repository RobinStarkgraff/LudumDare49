package scene

import com.soywiz.klock.milliseconds
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.Container
import helper.ScreenDebugger
import helper.SpriteLibrary
import kotlinx.coroutines.Delay
import kotlinx.coroutines.delay

class LoadingScene() : Scene() {
    override suspend fun Container.sceneInit() {
		SpriteLibrary.init()
		sceneContainer.changeTo<IntersectionLevel>()
        ScreenDebugger(sceneContainer)
    }
}
