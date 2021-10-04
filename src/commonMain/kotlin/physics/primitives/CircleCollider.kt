package physics.primitives

import com.soywiz.korio.lang.unexpected
import com.soywiz.korma.geom.Vector2D
import physics.Collider
import physics.CollisionManifold
import physics.ContactPoint
import physics.PhysicsBody
import physics.util.squareMagnitude
import kotlin.reflect.typeOf

class CircleCollider(position: Vector2D, var radius: Float, owner: PhysicsBody) : Collider(position, owner) {

    override fun getCollisionInfo(other: Collider): CollisionManifold {
        if (other is CircleCollider)
            return WithCircle(other)
        throw unexpected("no collision solver for $other")
    }


    public fun WithCircle(other: CircleCollider): CollisionManifold {
        var sumRadii = this.radius + other.radius
        val posA = this.localPosition + this.owner.position
        val posB = other.localPosition + other.owner.position
        val deltaPosition = posA - posB

        if (deltaPosition.squareMagnitude() - sumRadii * sumRadii > 0)
            return CollisionManifold()
        val distance = deltaPosition.magnitude
        //momentum instead of fixed 2 maybe?
        val depth = (distance - sumRadii) / 2f
        val normal = deltaPosition.normalized
        val distanceToContact = this.radius - depth
        val contact = posA + normal * distanceToContact
        return CollisionManifold(mutableListOf(ContactPoint(depth, normal, contact)))
    }
}