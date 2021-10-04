package objects.interactables

import com.soywiz.korma.geom.Point
import scene.Level

abstract class Interactable(val scene: Level, val pos: Point) {
    companion object {
        const val INTERACTION_DISTANCE = 100.0
    }

    init {
        scene.interactableList.add(this)
    }

    abstract fun interact()
}
