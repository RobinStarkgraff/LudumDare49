package objects

import com.soywiz.korev.Key
import com.soywiz.korge.view.Image
import com.soywiz.korge.view.View
import com.soywiz.korge.view.xy
import com.soywiz.korma.geom.Point
import scene.Level

class PickupItem(image: View) : InteractableItem(image) {
    companion object {
        const val INTERACTION_DISTANCE = InteractableItem.INTERACTION_DISTANCE
        private val INVENTORY_LOCATION = Point(255, 255)
    }

    fun putIntoInventory() {
        image.xy(INVENTORY_LOCATION)
    }

    override fun interact(scene: Level) {
        if (scene.views.keys.pressing(Key.E)) {
            Player.inventoryObject = this
            Player.inventoryObject?.putIntoInventory()
        }
    }
}
