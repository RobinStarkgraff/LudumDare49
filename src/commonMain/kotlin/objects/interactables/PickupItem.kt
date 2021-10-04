package objects.interactables

import com.soywiz.korge.view.Image
import com.soywiz.korge.view.xy
import com.soywiz.korma.geom.Point
import objects.Player
import scene.Level

class PickupItem(scene: Level, val image: Image, override val interactionDistance: Double = 100.0) : Interactable(scene, image.pos) {
    companion object {
        private val INVENTORY_LOCATION = Point(255, 255)
    }

    override fun interact() {
        Player.inventoryObject = this
        Player.inventoryObject?.putIntoInventory()
    }

    private fun putIntoInventory() {
        image.xy(INVENTORY_LOCATION)
    }
}
