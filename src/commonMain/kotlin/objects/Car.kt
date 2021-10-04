package objects

import com.soywiz.klock.Frequency
import com.soywiz.klock.milliseconds
import com.soywiz.korge.view.*
import com.soywiz.korma.geom.Vector2D
import physics.PhysicsBody
import physics.PhysicsSimulation
import physics.trigger.BoxTrigger
import scene.Level

open class Car(
    val sprite: Sprite,
    private val moveSpeed: Double,
    private var waypoints: List<Vector2D>,
    private val loop: Boolean,
    private val scene: Level
) {
    var physicsBody = PhysicsBody(sprite.pos, dynamic = true)
    var boxTrigger = BoxTrigger(sprite.pos, sprite.width - 10, sprite.height - 10)  {
        if(it == scene.player?.physicsBody) {
            scene.player?.die()
        }
    }

    var currentWaypoint = 0

    init {
        physics.PhysicsSimulation.prePhysicsCallbacks.add {
            if (reachedWaypoint()) {
                if (waypoints.lastIndex == currentWaypoint) {
                    if (!loop) waypoints = waypoints.reversed()
                    currentWaypoint = 0;
                } else
                    currentWaypoint++;

                sprite.width = -sprite.width
            }
            move()
        }
    }

    private fun move() {
        println("move")
        val dir = (waypoints[currentWaypoint] - sprite.pos).normalized
        physicsBody.velocity = dir * moveSpeed
        println(physicsBody.velocity)
        sprite.xy(physicsBody.position)
        boxTrigger.position = sprite.pos + Vector2D(0.0, (sprite.height)/2)
    }

    private fun reachedWaypoint(): Boolean {
        return Vector2D.distance(sprite.pos, waypoints[currentWaypoint]) <= moveSpeed / 2
    }
}
