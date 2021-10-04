package scene

import com.soywiz.klock.Frequency
import com.soywiz.klock.milliseconds
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.addUpdater
import physics.PhysicsSimulation
import helper.ScreenDebugger
import helper.SpriteLibrary

class LoadingScene() : Scene() {
    override suspend fun Container.sceneInit() {
        SpriteLibrary.init()
        Level.SCENE_CONTAINER = sceneContainer
		sceneContainer.changeTo<MenuScene>()
        sceneContainer.addUpdater(Frequency.from(PhysicsSimulation.fixedDeltaTime.milliseconds)){
            PhysicsSimulation.physicsStep()
        }
        ScreenDebugger(sceneContainer)
    }
}

