package Scenes

import Player
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.Container


class MenuScene() : Scene() {
    override suspend fun Container.sceneInit() {
        val player = Player(this@MenuScene)
    }
}
