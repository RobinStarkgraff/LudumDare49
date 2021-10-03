package objects

import com.soywiz.korge.view.View
import scene.Level

open class InteractableItem(val image: View) {
    companion object {
        const val INTERACTION_DISTANCE = 50.0
    }

    open fun interact(scene: Level) {
    }
}