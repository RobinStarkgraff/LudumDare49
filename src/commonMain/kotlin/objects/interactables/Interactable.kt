package objects.interactables

import com.soywiz.korma.geom.Point
import scene.Level

abstract class Interactable(val scene: Level, val pos: Point, open val interactionDistance: Double = 100.0) {
    init {
        scene.interactableList.add(this)
    }

    abstract fun interact()
}
