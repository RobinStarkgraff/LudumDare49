package physics.trigger

import COLLISION_COLOR
import com.soywiz.korge.view.addUpdater
import com.soywiz.korge.view.anchor
import com.soywiz.korge.view.solidRect
import com.soywiz.korge.view.xy
import com.soywiz.korio.lang.unexpected
import com.soywiz.korma.geom.Vector2D
import physics.Collider
import physics.Trigger
import physics.primitives.BoxCollider
import physics.primitives.CircleCollider
import scene.Level

class BoxTrigger(var position: Vector2D = Vector2D(),
                 var width: Double = 50.0,
                 var height: Double = 50.0,
                 callback: (physicsBody: physics.PhysicsBody) -> Unit) : Trigger(callback) {
    fun render() {
        val solidRect = Level.SCENE_CONTAINER.currentScene?.sceneView?.solidRect(width, height, COLLISION_COLOR)?.anchor(0.5, 0.5)?.xy(position.x, position.y)
        Level.SCENE_CONTAINER.addUpdater {
            solidRect?.xy(position)
        }
    }

    override fun isOverlapping(other: Collider): Boolean {
        if (other is BoxCollider) {
            val posA = position
            val posB = other.getWorldPosition()
            val aHW = width / 2.0;
            val aHH = height / 2.0;
            val bHW = other.width / 2.0;
            val bHH = other.height / 2.0;
            if (    posA.x - aHW < posB.x + bHW &&
                    posA.x + aHW > posB.x - bHW &&
                    posA.y - aHH < posB.y + bHH &&
                    posA.y + aHH > posB.y - bHH) {
                return true
            }
        }
        if (other is CircleCollider) {
            throw unexpected("no circles allowed, this is a christian minecraft server")
        }
        return false
    }
}