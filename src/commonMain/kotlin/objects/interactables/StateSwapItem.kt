package objects.interactables

import com.soywiz.korge.view.Sprite
import com.soywiz.korge.view.SpriteAnimation
import scene.Level

class StateSwapItem(scene: Level, val sprite: Sprite, val animation: SpriteAnimation) :
    Interactable(scene, sprite.pos) {
    companion object {
        const val INTERACTION_DISTANCE = Interactable.INTERACTION_DISTANCE
        private var initialState = false
    }

    init {
        sprite.playAnimation(animation)
        interact()
    }

    override fun interact() {
        if (initialState) sprite.setFrame(0) else sprite.setFrame(1)
        initialState = !initialState
    }
}
