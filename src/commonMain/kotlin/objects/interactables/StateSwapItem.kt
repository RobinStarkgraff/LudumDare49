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
    companion object {
        private var initialState = false
    }

    private var setupsoundone = false
    private var setupsoundtwo = false

    init {
        sprite.playAnimation(animation)
        interact()
        interact()
    }

    override fun interact() {
        if (initialState){
            sprite.setFrame(0)
            if (setupsoundone) GlobalScope.launch { SoundPlayer.playSound(soundone) } else setupsoundone = true
        } else {
            sprite.setFrame(1)
            if (setupsoundtwo) GlobalScope.launch {SoundPlayer.playSound(soundtwo)} else setupsoundtwo = true
        }
        initialState = !initialState
    }
}
