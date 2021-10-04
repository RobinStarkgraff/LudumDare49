package objects.interactables

import com.soywiz.korge.view.Sprite
import com.soywiz.korge.view.SpriteAnimation
import helper.SoundPlayer
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import scene.Level

class StateSwapItem(
    scene: Level,
    private val sprite: Sprite,
    animation: SpriteAnimation,
    private val soundone: String,
    private val soundtwo: String,
    override val interactionDistance: Double = 100.0
) :
    Interactable(scene, sprite.pos) {

    private var state = false

    init {
        sprite.playAnimation(animation)
        sprite.setFrame(state.toInt())
    }

    override fun interact() {
        if (state){
            sprite.setFrame(0)
            GlobalScope.launch { SoundPlayer.playSound(soundone)}
        } else {
            sprite.setFrame(1)
            GlobalScope.launch {SoundPlayer.playSound(soundtwo)}
        }
        state = !state
    }

    fun Boolean.toInt() = if (this) 1 else 0
}
