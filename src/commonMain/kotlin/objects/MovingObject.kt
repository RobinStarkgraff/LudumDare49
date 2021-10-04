package objects

import com.soywiz.klock.Frequency
import com.soywiz.klock.milliseconds
import com.soywiz.korge.view.*
import com.soywiz.korma.geom.Vector2D
import physics.PhysicsBody
import physics.PhysicsSimulation
import scene.Level

open class MovingObject(
        var width: Double,
        var height: Double,
        private val moveSpeed: Double,
        private var waypoints: List<Vector2D>,
        private val loop: Boolean,
        private val scene: Level
) {

    var image: SolidRect = scene.sceneView.container().solidRect(width, height).anchor(.5,.5).xy(waypoints[0])
    var physicsBody = PhysicsBody(image.pos)

    var currentWaypoint = 0

    init {
        image.addUpdater(Frequency.from(PhysicsSimulation.fixedDeltaTime.milliseconds)) {
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
        physicsBody.velocity = dir * moveSpeed * scale
        image.xy(physicsBody.position)
    }

    private fun reachedWaypoint(): Boolean {
        return Vector2D.distance(image.pos, waypoints[currentWaypoint]) <= moveSpeed / 2
    }
}
