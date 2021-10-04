package physics

import com.soywiz.korma.geom.Vector2D

class PhysicsBody(var position : Vector2D = Vector2D(),
                  var velocity: Vector2D = Vector2D(),
                  var colliders: MutableList<Collider> = mutableListOf<Collider>(),
                  var mass: Double = 1.0,
                  var dynamic: Boolean = false) {

    init {
        PhysicsSimulation.physicsBodies.add(this)
    }
    public fun registerCollider(collider: Collider) {
        colliders.add((collider))
    }
}