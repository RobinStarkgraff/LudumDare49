package scenes

import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.SolidRect
import com.soywiz.korma.geom.Vector2D
import manager.LevelManager

abstract class Level : Scene() {
    var levelManager : LevelManager? = null
    var player : Player? = null
    open val spawnpoint = Vector2D(0, 0);
    val collisionList = mutableListOf<SolidRect>()
    val deathZoneList = mutableListOf<SolidRect>()
}
