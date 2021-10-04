package objects

import com.soywiz.korge.view.addUpdater
import com.soywiz.korma.geom.Vector2D
import physics.PhysicsSimulation
import physics.Trigger
import physics.trigger.BoxTrigger
import scene.Level

/*class MovingPlatform(
        var platformWidth: Double,
        var platformHeight: Double,
        private val moveSpeed: Double,
        private var waypoints: List<Vector2D>,
        private val loop: Boolean,
        private val scene: Level
) : MovingObject(platformWidth, platformHeight, moveSpeed, waypoints, loop, scene) {
        var trigger = BoxTrigger(physicsBody.position, width, height) {
                if(it == scene.player?.physicsBody) {
                        println(it.velocity)
                        println(physicsBody.velocity)
                        it.velocity = it.velocity + physicsBody.velocity
                }
        }

        init {
            PhysicsSimulation.prePhysicsCallbacks.add {
                    trigger.position = physicsBody.position
            }
        }

}*/