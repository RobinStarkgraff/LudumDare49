package objects

import com.soywiz.klock.milliseconds
import com.soywiz.korge.view.Sprite
import com.soywiz.korge.view.addUpdater
import com.soywiz.korge.view.xy
import com.soywiz.korio.dynamic.dyn
import com.soywiz.korma.geom.Point
import com.soywiz.korma.geom.Vector2D
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import objects.interactables.Interactable
import physics.PhysicsSimulation
import physics.trigger.BoxTrigger
import scene.Level

class Ferry(val sprite: Sprite, val waypoints : MutableList<Vector2D>, val speed: Double, override var scene: Level,val time: Long) :  Interactable() {

    var canInteract = false
    var wasUsed = false

    val boxTrigger = BoxTrigger(sprite.pos + Vector2D(sprite.width/2.0, sprite.height/2), sprite.width, sprite.height) {
        canInteract = true
    }

    override var pos = sprite.pos
    override var interactionDistance = 0.0

    init {
        interactionDistance = 100.0
        PhysicsSimulation.prePhysicsCallbacks.add {
            canInteract = false
        }
        PhysicsSimulation.postPhysicsCallbacks.add {
            move()
        }
        boxTrigger.render()
    }

    override fun interact(): Boolean {
        if(reachedWaypoint() && canInteract && !wasUsed) {
            wasUsed
            waypoints.reverse()
            scene.player?.physicsBody?.dynamic = false
            GlobalScope.launch { playerCanWalk(time) }
            return true
        }
        return false
    }

    suspend fun playerCanWalk(time :Long) {
        delay(time)
        scene.player?.physicsBody?.dynamic = true
    }

    fun move() {
        if(!reachedWaypoint()) {
            val dir = (waypoints[0] - sprite.pos).normalized
            val velocity = dir * speed
            scene.player?.physicsBody?.position = scene.player?.physicsBody?.position?.plus(velocity)!!
            sprite.xy(sprite.pos + velocity)
            boxTrigger.position = sprite.pos + Vector2D(sprite.width/2.0, sprite.height/2)
        }
    }

    private fun reachedWaypoint(): Boolean {
        return Vector2D.distance(sprite.pos, waypoints[0]) <= speed / 2
    }
}
