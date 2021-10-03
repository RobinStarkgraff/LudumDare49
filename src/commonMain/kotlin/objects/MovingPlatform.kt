package objects

import com.soywiz.korma.geom.Vector2D
import scene.Level

class MovingPlatform(
    var platformWidth: Double,
    var platformHeight: Double,
    private val moveSpeed: Double,
    private var waypoints: List<Vector2D>,
    private val loop: Boolean,
    private val scene: Level
) : MovingObject(platformWidth, platformHeight, moveSpeed, waypoints, loop, scene) {
    init {
        scene.movingObjectsList.add(this)
    }
}