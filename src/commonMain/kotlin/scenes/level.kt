package scenes

import Player
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.SolidRect
import com.soywiz.korma.geom.Vector2D

abstract class Level : Scene() {
    open val spawnpoint = Vector2D(0, 0);
    public val collisionList = mutableListOf<SolidRect>()
    public val deathZoneList = mutableListOf<SolidRect>()
}