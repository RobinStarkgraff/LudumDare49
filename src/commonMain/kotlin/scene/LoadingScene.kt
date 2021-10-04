package scene

import RESOLUTION
import com.soywiz.klock.Frequency
import com.soywiz.klock.milliseconds
import com.soywiz.korge.component.removeFromView
import com.soywiz.korge.input.mouse
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.*
import physics.PhysicsSimulation
//import helper.ScreenDebugger
import helper.SpriteLibrary

class LoadingScene() : Scene() {
    override suspend fun Container.sceneInit() {
        SpriteLibrary.init()
        var title = sceneContainer.sprite(SpriteLibrary.TITLE).anchor(0.5,1.0).xy(RESOLUTION.x / 2,RESOLUTION.y)
        sceneContainer.mouse.onClick {
            Level.SCENE_CONTAINER = sceneContainer
            sceneContainer.changeTo<Level1>()
            sceneContainer.removeChild(title)
            sceneContainer.mouse.removeFromView()
        }
        sceneContainer.addUpdater(Frequency.from(PhysicsSimulation.fixedDeltaTime.milliseconds)) {
            PhysicsSimulation.physicsStep()
        }
        //ScreenDebugger(sceneContainer)
    }
}

