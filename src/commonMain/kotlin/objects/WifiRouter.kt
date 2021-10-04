package objects

import com.soywiz.korge.view.Image
import com.soywiz.korge.view.circle
import com.soywiz.korge.view.xy
import com.soywiz.korim.color.RGBA
import scene.Level

class WifiRouter(val x: Double, val y: Double, val radius: Double, private val wifiRouterImg: Image?, scene: Level) {
    init {
        //add wifi image
        scene.phone?.wifiRouterList?.add(this)
    }
}
