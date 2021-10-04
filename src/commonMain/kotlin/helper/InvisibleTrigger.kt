package helper

import com.soywiz.korge.view.*
import com.soywiz.korma.geom.Vector2D
import objects.interactables.Interactable
import physics.PhysicsSimulation
import physics.trigger.BoxTrigger
import scene.Level

class InvisibleTrigger(scene: Level, width: Double, height: Double, x: Int, y: Int, interactable: Interactable) {
    private var rect: BoxTrigger

    var wasActive = false
    var currentlyActive = false

    init {
        rect = BoxTrigger(Vector2D(x, y), width, height) {
            wasActive = currentlyActive
            currentlyActive = true
        }

        PhysicsSimulation.postPhysicsCallbacks.add {
            if(wasActive != currentlyActive) {
                interactable.interact()
            }
        }
    }
}
