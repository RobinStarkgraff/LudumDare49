package scene

import com.soywiz.klock.Frequency
import com.soywiz.klock.milliseconds
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.addUpdater
import helper.SpriteLibrary
import physics.PhysicsSimulation
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
		sceneContainer.changeTo<MenuScene>()
        sceneContainer.addUpdater(Frequency.from(PhysicsSimulation.fixedDeltaTime.milliseconds)){
            PhysicsSimulation.physicsStep()
        }
        ScreenDebugger(sceneContainer)
    }
}
