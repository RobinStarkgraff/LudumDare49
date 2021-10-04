package objects

import com.soywiz.korma.geom.Vector2D
import physics.PhysicsBody
import physics.primitives.BoxCollider
import scene.Level

class MovingBlock(
        var platformWidth: Double,
        var platformHeight: Double,
        private val moveSpeed: Double,
        private var waypoints: List<Vector2D>,
        private val loop: Boolean,
        private val scene: Level
) : MovingObject(platformWidth, platformHeight, moveSpeed, waypoints, loop, scene) {

    init {
        physicsBody = PhysicsBody()
        BoxCollider(Vector2D(), platformWidth, platformHeight, physicsBody)
    }
}