package objects.interactables

import com.soywiz.korge.view.Image
import com.soywiz.korge.view.xy
import com.soywiz.korma.geom.Point
import objects.Player
import scene.Level

class PickupItem(scene: Level, val image: Image) : Interactable(scene, image.pos) {
    companion object {
        const val INTERACTION_DISTANCE = Interactable.INTERACTION_DISTANCE
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
