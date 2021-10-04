package physics

class PhysicsSimulation {
    companion object {
        var physicsBodies = mutableListOf<PhysicsBody>()
        var triggers = mutableListOf<Trigger>()
        var fixedDeltaTime = 1 / 50.0
        var iterations = 16
        var prePhysicsCallbacks = mutableListOf<(() -> Unit)?>()
        var postPhysicsCallbacks = mutableListOf<(() -> Unit)?>()


        fun physicsStep() {
            prePhysicsCallbacks.forEach { it?.invoke() }
            val collisions = mutableListOf<CollisionManifold>()
            val bodiesA = mutableListOf<PhysicsBody>()
            val bodiesB = mutableListOf<PhysicsBody>()
            val bodyCount = physicsBodies.count()

            //Find ALL collisions
            if (bodyCount > 0)
                for (i in 0 until bodyCount)
                    for (j in i until bodyCount) {
                        if (i == j)
                            continue
                        val physicsBodyA = physicsBodies[i]
                        val physicsBodyB = physicsBodies[j]
                        for (collider in physicsBodyA.colliders) {
                            for (other in physicsBodyB.colliders) {
                                val collisionResult = collider.getCollisionInfo(other)
                                if (collisionResult.isColliding()) {
                                    bodiesA.add(physicsBodyA)
                                    bodiesB.add(physicsBodyB)
                                    collisions.add((collisionResult))
                                }
                            }
                        }
                    }
            //Solve Collisions Iterative
            if (collisions.count() > 0)
                for (k in 0 until iterations) {
                    for (i in collisions.indices)
                        for (j in 0..collisions[i].contactPoints.count()) {
                            val physicsBodyA = bodiesA[i]
                            val physicsBodyB = bodiesB[i]
                            val collision = collisions[i]
                            applyImpulse(physicsBodyA, physicsBodyB, collision)
                        }
                }

            for (physicsBody in physicsBodies)
                physicsBody.position = physicsBody.position + physicsBody.velocity * fixedDeltaTime

            for (physicsBody in physicsBodies)
                for(trigger in triggers)
                    for(collider in physicsBody.colliders)
                        if(trigger.isOverlapping(collider)) {
                            trigger.callback.invoke(physicsBody)
                            break
                        }
            postPhysicsCallbacks.forEach { it?.invoke() }
        }

        private fun applyImpulse(a: PhysicsBody, b: PhysicsBody, c: CollisionManifold) {
            //linear velocity
            val invMassA = 1 / a.mass
            val invMassB = 1 / b.mass
            val invMassSum = invMassA + invMassB
            if (invMassSum == 0.0)
                return
            val relativeVelocity = b.velocity - a.velocity
            for (contact in c.contactPoints) {
                val relativeNormal = contact.normal.normalized
                val dot = relativeVelocity.dot(relativeNormal)
                if (dot > 0.0f)
                    continue
                //val e = 1
                val numerator = -2 * dot
                var j = numerator / invMassSum
                if (c.contactPoints.count() > 0 && j != 0.0)
                    j /= c.contactPoints.count()
                val impulse = relativeNormal * j
                if (a.dynamic)
                    a.velocity = a.velocity + impulse * invMassA * -1
                if (b.dynamic)
                    b.velocity = b.velocity + impulse * invMassB * 1
            }
        }
    }
}