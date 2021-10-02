package objects

import com.soywiz.korge.view.Circle
import com.soywiz.korge.view.Image
import com.soywiz.korge.view.xy
import com.soywiz.korim.color.RGBA
import kotlin.math.sqrt

class WifiRouter(private val x: Double, private val y: Double, radius: Double, private val wifiRouterImg: Image) {

    companion object {
        lateinit var wifiRouters: List<WifiRouter>
    }

    private val circle = Circle(radius, fill = RGBA(255, 44, 52, 150))

    fun drawImage() {
        wifiRouterImg.xy(x, y)
        circle.xy(x, y)
    }

    fun calcConnectionBars(playerX: Double, playerY: Double): Int {

        val section: Double = circle.radius / 4

        val xDifference: Double = circle.x - playerX
        val yDifference: Double = circle.y - playerY

        var hypotenuse = xDifference * xDifference + yDifference * yDifference
        hypotenuse = sqrt(hypotenuse)

        return if (hypotenuse >= circle.radius) 0   // outside the wifi signal
        else if (hypotenuse >= section * 3) 1       // outermost section
        else if (hypotenuse >= section * 2) 2       // second outer section
        else if (hypotenuse >= section) 3           // the third section
        else 4                                      // inside the fourth section

    }
}
