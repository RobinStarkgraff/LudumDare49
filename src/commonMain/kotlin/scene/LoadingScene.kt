package scene

import com.soywiz.klock.Frequency
import com.soywiz.klock.milliseconds
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.addUpdater
import helper.SpriteLibrary
import physics.PhysicsSimulation

class LoadingScene() : Scene() {
    override suspend fun Container.sceneInit() {
        val spriteLibrary = SpriteLibrary()
		spriteLibrary.init()
		sceneContainer.changeTo<MenuScene>()
        sceneContainer.addUpdater(Frequency.from(PhysicsSimulation.fixedDeltaTime.milliseconds)){
            PhysicsSimulation.physicsStep()
        }
    }
}
