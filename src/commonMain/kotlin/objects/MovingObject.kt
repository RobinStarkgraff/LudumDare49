package objects

import com.soywiz.klock.milliseconds
import com.soywiz.korge.view.*
import com.soywiz.korma.geom.Vector2D
import scene.Level

open class MovingObject(
    var width: Double,
    var height: Double,
    private val moveSpeed: Double,
    private var waypoints: List<Vector2D>,
    private val loop: Boolean,
    private val scene: Level
) {
    var image: SolidRect = scene.sceneView.solidRect(width, height)
    var velocity = Vector2D(0, 0)
    var currentWaypoint = 0

    init {
        image.addUpdater { dt ->
            val scale = dt / 16.milliseconds
            if (reachedWaypoint()) {
                if (waypoints.lastIndex == currentWaypoint) {
                    if (!loop) waypoints = waypoints.reversed()
                    currentWaypoint = 0;
                } else
                    currentWaypoint++;
            }
            move(scale)
        }
    }

    private fun move(scale: Double) {
        val dir = (waypoints[currentWaypoint] - image.pos).normalized
        velocity = dir * moveSpeed * scale
        image.xy(image.pos.x + velocity.x, image.pos.y + velocity.y)
    }

    private fun reachedWaypoint(): Boolean {
        return Vector2D.distance(image.pos, waypoints[currentWaypoint]) <= moveSpeed/2
    }
}
