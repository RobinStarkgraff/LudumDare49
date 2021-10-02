package objects

import com.soywiz.korge.view.Circle
import com.soywiz.korge.view.Image
import com.soywiz.korge.view.xy
import com.soywiz.korim.color.RGBA
import kotlin.math.pow
import kotlin.math.sqrt

class WifiRouter(private val x: Double, private val y: Double, radius: Double, private val wifiRouterImg: Image) {

    companion object {
       lateinit var wifiRouters: List<WifiRouter>
    }

    private val circle = Circle(radius, fill = RGBA(255, 44, 52, 150))

    fun drawImage() {
        wifiRouterImg.xy(x, y);
        circle.xy(x, y)
    }

    fun calcConnectionBars(playerX: Double, playerY: Double): Int {

        val section: Double = circle.radius / 4

        val xDifference: Double = if (circle.x >= playerX) circle.x - playerX else playerX - circle.x;
        val yDifference: Double = if (circle.y >= playerY) circle.y - playerY else playerY - circle.y;

        var hypotenuse = xDifference.pow(2) + yDifference.pow(2)
        hypotenuse = sqrt(hypotenuse)

        return if (hypotenuse <= section) 4                                 // the innermost section
        else if (hypotenuse <= section * 2 && hypotenuse > section) 3       // second inner section, hypotenuse must be longer than fits into the first section
        else if (hypotenuse <= section * 3 && hypotenuse > section * 2) 2   // third inner section
        else if (hypotenuse <= circle.radius && hypotenuse > section * 3) 1 // the outermost section
        else 0                                                              // outside of the wifi radius

    }
}
