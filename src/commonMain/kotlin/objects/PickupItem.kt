package objects

import com.soywiz.korge.view.Image
import com.soywiz.korge.view.View
import com.soywiz.korge.view.xy
import com.soywiz.korma.geom.Point

class PickupItem(val image: View) {
    companion object {
        const val INTERACTION_DISTANCE = 50.0
        private val INVENTORY_LOCATION = Point(255, 255)
    }

    fun putIntoInventory() {
        image.xy(INVENTORY_LOCATION)
    }
}
