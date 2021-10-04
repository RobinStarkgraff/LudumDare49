package objects

import RESOLUTION
import com.soywiz.korge.view.*
import com.soywiz.korim.color.RGBA
import com.soywiz.korma.geom.Vector2D
import helper.SpriteLibrary
import helper.StaticData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import scene.Level
import kotlin.math.pow
import kotlin.math.sqrt

class Phone(val scene: Level) {

    companion object {
        const val MARGIN = 50;
        const val MAX_DOWNLOAD = 99.0
        const val SPEED = 1.0
        const val DOWNLOAD_BAR_WIDTH = 138
        val DOWNLOAD_BAR_COLOR = RGBA(0, 100, 0, 255)
    }

    private var container = Container()
    private lateinit var phoneImage: Sprite
    private lateinit var downloadBar: SolidRect

    init {
        createPhone()
        timeMeasurement()
    }

    private fun createPhone() {
        container = scene.ui.container()
        phoneImage = container.sprite(SpriteLibrary.PHONE)
        phoneImage.setFrame(0)
        container.xy(RESOLUTION.x - phoneImage.width - MARGIN, RESOLUTION.y - phoneImage.height - MARGIN)
        createDownloadBar()
    }

    private fun createDownloadBar() {
        container.solidRect(DOWNLOAD_BAR_WIDTH, 9).xy(27, 132)
        downloadBar = container.solidRect(DOWNLOAD_BAR_WIDTH, 9, DOWNLOAD_BAR_COLOR).xy(27, 132)
    }

    private fun timeMeasurement() {
        container.addUpdater {
            val secondsPast = StaticData.timeSinceStart.elapsed.seconds
            // Please don't question it :)
            val calcValue = (700000000000000.0).pow(-0.0001 * secondsPast)
            val minutesToDisplay = 60 * (1-(calcValue))
        }
    }

    private var downloaded: Double = 0.0
    private var downloadComplete = false
    private var signalQuality = 0

    val wifiRouterList = mutableListOf<WifiRouter>()

    fun download(x: Double, y: Double) {
        if (downloaded >= MAX_DOWNLOAD && !downloadComplete) {
            downloadComplete = true
            GlobalScope.launch { scene.nextScene() }
            return
        }

        signalQuality = getSignalQuality(x, y)

        when (signalQuality) {
            0 -> downloaded += 0.0 * SPEED
            1 -> downloaded += 0.005 * SPEED
            2 -> downloaded += 0.01 * SPEED
            3 -> downloaded += 0.075 * SPEED
            4 -> downloaded += 0.2 * SPEED
        }

        updateUI()
    }

    fun updateUI() {
        downloadBar.width = DOWNLOAD_BAR_WIDTH * 0.01 * downloaded
        phoneImage.setFrame(signalQuality)
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
