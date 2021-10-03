package objects

import com.soywiz.korge.view.*
import helper.StaticData
import kotlin.math.pow

class Phone() {

    val container = Container()

    init {
        timeMeasurement()
    }

    private fun timeMeasurement() {
        container.addUpdater {
            val secondsPast = StaticData.timeSinceStart.elapsed.seconds
            // Please don't question it :)
            val calcValue = (700000000000000.0).pow(-0.0001 * secondsPast)
            val minutesToDisplay = 60 * (1-(calcValue))
        }
    }
}
