package scene

import com.soywiz.klock.Frequency
import com.soywiz.klock.milliseconds
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.*
import com.soywiz.korim.color.Colors
import com.soywiz.korim.color.RGBA
import physics.PhysicsSimulation
//import helper.ScreenDebugger
import helper.SpriteLibrary

class LoadingScene() : Scene() {
    override suspend fun Container.sceneInit() {
        val start = roundRect(300.0, 50.0, 5.0, fill = Colors["#b9aea0"]).xy(500, 300)
        val starttext = text("Loading", textSize = 34.0, color = RGBA(0, 0, 0, 255)).xy(600, 305)
        SpriteLibrary.init()
        Level.SCENE_CONTAINER = sceneContainer
		sceneContainer.changeTo<MenuScene>()
        sceneContainer.addUpdater(Frequency.from(PhysicsSimulation.fixedDeltaTime.milliseconds)){
            PhysicsSimulation.physicsStep()
        }


        sceneDestroy()
        ScreenDebugger(sceneContainer)
    }
}

