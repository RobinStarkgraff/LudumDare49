package physics

import com.soywiz.korma.geom.Point
import com.soywiz.korma.geom.Vector2D

abstract class Collider(var localPosition : Vector2D, var owner : PhysicsBody) {

    public abstract fun getCollisionInfo(other: Collider) : CollisionManifold
    public fun getWorldPosition() : Vector2D = owner.position + localPosition
    init {
        owner.registerCollider(this)
    }
}