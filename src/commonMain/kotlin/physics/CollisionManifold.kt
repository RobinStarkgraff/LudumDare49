package physics

import com.soywiz.korio.lang.unexpected
import com.soywiz.korma.geom.Vector2D
import physics.primitives.BoxCollider
import physics.primitives.CircleCollider
import physics.util.*
import kotlin.math.sign

class CollisionManifold(var contactPoints: MutableList<ContactPoint> = mutableListOf()) {
    companion object {
    }

    fun isColliding(): Boolean = contactPoints.count() > 0
}