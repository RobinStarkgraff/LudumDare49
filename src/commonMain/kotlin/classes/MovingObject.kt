package classes

import Scenes.Level
import com.soywiz.klock.milliseconds
import com.soywiz.korge.view.SolidRect
import com.soywiz.korge.view.addUpdater
import com.soywiz.korge.view.solidRect
import com.soywiz.korge.view.xy
import com.soywiz.korma.geom.Vector2D

class MovingObject(
    private val width: Double,
    private val height: Double,
    private val speed: Double,
    private var waypoints: List<Vector2D>,
    private val loop: Boolean,
    private val scene: Level
) {
    var image: SolidRect = scene.sceneView.solidRect(width, height)
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
        val movement = dir * speed * scale
        image.xy(image.pos.x + movement.x, image.pos.y + movement.y)
    }

    private fun reachedWaypoint(): Boolean {
        return Vector2D.distance(image.pos, waypoints[currentWaypoint]) <= 1
    }
}