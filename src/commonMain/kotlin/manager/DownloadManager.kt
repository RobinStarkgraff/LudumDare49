package manager

import scene.Level
import kotlin.math.roundToInt

class DownloadManager(private val scene: Level) {
    companion object {
        const val MAX_DOWNLOAD: Double = 99.0
    }

    private var downloaded: Double = 0.0

    suspend fun download(connectionBars: Int) {

        if (downloaded >= MAX_DOWNLOAD) {
            scene.player?.die()
            scene.nextScene()
            return
        }

        when (connectionBars) {
            0 -> downloaded += 0.0
            1 -> downloaded += 0.005
            2 -> downloaded += 0.01
            3 -> downloaded += 0.075
            4 -> downloaded += 0.2
        }
        println("${downloaded.roundToInt()} percent downloaded")
    }
}


