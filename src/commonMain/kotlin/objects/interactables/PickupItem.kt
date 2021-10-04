package objects.interactables

import com.soywiz.korge.view.Image
import com.soywiz.korge.view.xy
import com.soywiz.korim.bitmap.Bitmap
import com.soywiz.korim.bitmap.slice
import com.soywiz.korma.geom.Point
import scene.Level

class PickupItem(override var scene: Level, val imageMap: Image, private val imageInventory: Bitmap, override var interactionDistance: Double = 100.0) : Interactable() {
    companion object {
        private val INVENTORY_LOCATION = Point(0, 0)
    }

    override var pos = imageMap.pos

    private var destroy = false

    init {
        scene.interactableList.add(this)
    }

    override fun interact(): Boolean {
        if (destroy) {
            return false
        }

        scene.player?.inventoryObject = this
        scene.player?.inventoryObject?.putIntoInventory()
        return true
    }

    private fun putIntoInventory() {
        imageMap.bitmap = imageInventory.slice()
        imageMap.xy(INVENTORY_LOCATION)
    }

    fun destroySelf() {
        imageMap.scale = 0.0
        destroy = true
    }
}
