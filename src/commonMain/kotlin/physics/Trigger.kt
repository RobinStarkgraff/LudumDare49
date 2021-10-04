package physics

abstract class Trigger(var callback : (PhysicsBody)-> Unit) {
    abstract fun isOverlapping(other : Collider) : Boolean
    init {
        physics.PhysicsSimulation.triggers.add(this)
    }
}