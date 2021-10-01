package Scenes

import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.solidRect
import com.soywiz.korge.view.xy

class MenuScene() : Scene() {
    override suspend fun Container.sceneInit() {
        solidRect(100,100).xy(10,10)
    }
}
