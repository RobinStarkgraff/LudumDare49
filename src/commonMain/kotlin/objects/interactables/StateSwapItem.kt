package objects.interactables

import com.soywiz.korge.view.SolidRect
import com.soywiz.korge.view.Sprite
import com.soywiz.korge.view.SpriteAnimation
import com.soywiz.korge.view.size
import com.soywiz.korma.geom.Point
import helper.SoundPlayer
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import physics.primitives.BoxCollider
import scene.Level

class StateSwapItem(
    scene: Level,
    private val sprite: Sprite,
    animation: SpriteAnimation,
    private val soundone: String,
    private val soundtwo: String,
    override val interactionDistance: Double = 100.0,
    private val collider: BoxCollider? = null
) :
    Interactable(scene, sprite.pos) {

    private var state = false
    private val rectSize: Point?

    init {
        sprite.playAnimation(animation)
        sprite.setFrame(state.toInt())
        rectSize = if (collider == null) null else Point(collider.width, collider.height)
    }

    override fun interact() {
        if (state) {
            sprite.setFrame(0)
            GlobalScope.launch { SoundPlayer.playSound(soundone) }
            rectSize?.let {
                collider?.width = rectSize.x
                collider?.height = rectSize.y
            }
        } else {
            sprite.setFrame(1)
            GlobalScope.launch { SoundPlayer.playSound(soundtwo) }
            collider?.width = 0.0
            collider?.height = 0.0
        }
        state = !state
    }

    fun Boolean.toInt() = if (this) 1 else 0
}
