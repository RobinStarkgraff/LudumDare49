import kotlin.math.roundToInt

class LevelManager {
    companion object {
        const val MAX_DOWNLOAD: Double = 99.0
    }

    private var downloaded: Double = 0.0

    fun download(connectionBars: Int) {

        if (downloaded >= MAX_DOWNLOAD) {
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


