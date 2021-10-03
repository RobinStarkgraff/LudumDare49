package manager

import com.soywiz.korma.geom.Vector2D
import objects.WifiRouter
import scene.Level
import kotlin.math.sqrt

class DownloadManager(private val scene: Level) {
    companion object {
        const val MAX_DOWNLOAD = 99.0
        const val SPEED = 1.0
    }

    private var downloaded: Double = 0.0

    val wifiRouterList = mutableListOf<WifiRouter>()

    suspend fun download(x: Double, y: Double) {

        if (downloaded >= MAX_DOWNLOAD) {
            scene.player?.die()
            scene.nextScene()
            return
        }

        when (getSignalQuality(x, y)) {
            0 -> downloaded += 0.0 * SPEED
            1 -> downloaded += 0.005 * SPEED
            2 -> downloaded += 0.01 * SPEED
            3 -> downloaded += 0.075 * SPEED
            4 -> downloaded += 0.2 * SPEED
        }
        //println("${downloaded.roundToInt()} percent downloaded")
    }

    private fun getNextRouter(x: Double, y: Double) : WifiRouter? {
        if(wifiRouterList.size <= 0)
            return null
        var nearestRouter : WifiRouter = wifiRouterList[0]
        var minDistance = Double.MAX_VALUE

        for (router in wifiRouterList) {
            val distance = Vector2D.distance(Vector2D(router.x, router.y), Vector2D(x, y))
            if(distance <= minDistance) {
                nearestRouter = router
                minDistance = distance
            }
        }
//        println("${downloaded.roundToInt()} percent downloaded")
        return nearestRouter;
    }

    private fun getSignalQuality(x : Double, y : Double) : Int {
        val router = getNextRouter(x, y) ?: return 0

        val section: Double = router.radius / 4

        val xDifference: Double = router.x - x
        val yDifference: Double = router.y - y

        var hypotenuse = xDifference * xDifference + yDifference * yDifference
        hypotenuse = sqrt(hypotenuse)

        return if (hypotenuse >= router.radius) 0
        else if (hypotenuse >= section * 3) 1
        else if (hypotenuse >= section * 2) 2
        else if (hypotenuse >= section) 3
        else 4
    }
}


