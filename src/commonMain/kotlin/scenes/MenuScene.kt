package scenes

import Player
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.solidRect
import com.soywiz.korge.view.xy
import com.soywiz.korma.geom.Vector2D

class MenuScene() : Level() {
    lateinit var player: Player
    override val spawnpoint: Vector2D = Vector2D(200, 200);
    override suspend fun Container.sceneInit() {

        val deathZone = solidRect(10, 10).xy(100, 100)
        deathZoneList.add(deathZone)
        player = Player(this@MenuScene)

    }
}