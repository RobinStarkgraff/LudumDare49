package objects

import com.soywiz.korge.view.Circle
import com.soywiz.korge.view.Image
import com.soywiz.korge.view.xy
import com.soywiz.korim.color.RGBA

class WifiRouter(private val x: Double, private val y: Double, radius: Double, private val wifiRouterImg: Image) {

    companion object {
       lateinit var wifiRouters: List<WifiRouter>
    }

    private val circle = Circle(radius, fill = RGBA(255, 44, 52, 150))

    fun drawImage() {
        wifiRouterImg.xy(x, y);
        circle.xy(x, y)
    }
}
