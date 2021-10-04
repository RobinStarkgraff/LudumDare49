package objects.interactables

import com.soywiz.korge.view.Sprite
import com.soywiz.korge.view.SpriteAnimation
import com.soywiz.korma.geom.Point
import helper.SoundPlayer
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import scene.Level

class StateSwapItem(
    override var scene: Level,
    private val sprite: Sprite,
    animation: SpriteAnimation,
    private val soundone: String?,
    private val soundtwo: String?,
    override var interactionDistance: Double = 50.0,
    val inventoryItem: PickupItem? = null
) :
    Interactable() {

    override var pos = sprite.pos

    private var state = false

    init {
        scene.interactableList.add(this)
        sprite.playAnimation(animation)
        sprite.setFrame(state.toInt())
    }

    override fun interact(): Boolean {
        if (inventoryItem == null) {
            changeState()
            return true
        }

        if (scene.player?.inventoryObject != inventoryItem || (scene.phone != null && !scene.phone!!.downloadComplete)) {
            return false
        }

        changeState()
        scene.player?.removeInventoryItem()
        return true
    }

    private fun changeState() {
        if (state){
            sprite.setFrame(0)
            if(soundone != null) GlobalScope.launch { SoundPlayer.playSound(soundone)}
        } else {
            sprite.setFrame(1)
            if(soundtwo != null) GlobalScope.launch {SoundPlayer.playSound(soundtwo)}
        }
        state = !state
    }

    fun Boolean.toInt() = if (this) 1 else 0
}
