package objects.interactables

import com.soywiz.korge.view.Sprite
import com.soywiz.korge.view.SpriteAnimation
import scene.Level

class StateSwapItem(
    scene: Level,
    private val sprite: Sprite,
    animation: SpriteAnimation,
    override val interactionDistance: Double = 100.0
) :
    Interactable(scene, sprite.pos) {
    companion object {
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
