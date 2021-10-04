package objects.interactables

import com.soywiz.korma.geom.Point
import scene.Level

abstract class Interactable() {

    abstract var scene: Level
    abstract var pos: Point
    abstract var interactionDistance: Double

    abstract fun interact(): Boolean
}
