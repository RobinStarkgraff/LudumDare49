package objects

import com.soywiz.korge.view.circle
import com.soywiz.korge.view.xy
import helper.StaticData.Companion.wifiRouterRadiusColor
import scene.Level

class WifiRouter(val x: Double, val y: Double, val radius: Double, scene: Level) {
    init {
        //add wifi image
        scene.sceneView.circle(radius, fill = wifiRouterRadiusColor).xy(x - radius, y - radius)
        scene.phone?.wifiRouterList?.add(this)
    }
}
