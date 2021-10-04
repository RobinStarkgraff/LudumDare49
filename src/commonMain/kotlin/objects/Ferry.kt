package objects

import com.soywiz.klock.milliseconds
import com.soywiz.korge.view.Sprite
import com.soywiz.korge.view.addUpdater
import com.soywiz.korge.view.xy
import com.soywiz.korma.geom.Vector2D
import objects.interactables.Interactable
import physics.PhysicsSimulation
import physics.trigger.BoxTrigger
import scene.Level

class Ferry(val sprite: Sprite, val waypoints : MutableList<Vector2D>, val speed: Double, scene: Level) :  Interactable(scene, sprite.pos) {

    var canInteract = false



    val boxTrigger = BoxTrigger(sprite.pos + Vector2D(sprite.width/2.0, sprite.height/2), sprite.width/2, sprite.width/2) {
        canInteract = true
    }

    init {

        PhysicsSimulation.prePhysicsCallbacks.add {
            canInteract = false
        }
        PhysicsSimulation.postPhysicsCallbacks.add {
            move()
        }
    }

    override fun interact() {
        if(reachedWaypoint() && canInteract) {
            waypoints.reverse()
            scene.player?.physicsBody?.dynamic = false
            println("false")
        }
    }

    fun move() {
        if(!reachedWaypoint()) {
            val dir = (waypoints[0] - sprite.pos).normalized
            val velocity = dir * speed
            println(velocity)
            boxTrigger.position = sprite.pos + Vector2D(sprite.width/2.0, sprite.height/2)
            scene.player?.physicsBody?.position = scene.player?.physicsBody?.position?.plus(velocity)!!
            sprite.xy(sprite.pos + velocity)
        }
        else if(!scene.player?.physicsBody?.dynamic!!) {
            scene.player?.physicsBody?.dynamic = true
            println("true")
        }
    }

    private fun reachedWaypoint(): Boolean {
        return Vector2D.distance(sprite.pos, waypoints[0]) <= speed / 2
    }
}