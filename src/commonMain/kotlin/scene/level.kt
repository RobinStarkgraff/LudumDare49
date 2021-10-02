package scene

import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.SolidRect
import com.soywiz.korma.geom.Vector2D

abstract class Level : Scene() {
    open val spawnpoint = Vector2D(0, 0);
    val collisionList = mutableListOf<SolidRect>()
    val deathZoneList = mutableListOf<SolidRect>()
}
