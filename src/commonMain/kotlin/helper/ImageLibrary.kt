package helper

import com.soywiz.korge.view.Image
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.file.std.resourcesVfs

class ImageLibrary {
    companion object {
        private const val ART_FOLDER_PATH = "art/"

        const val BACKGROUND_IMAGE_FLAT = "background/frog_flat"

        suspend fun loadImage(filePath: String): Image {
            return Image(resourcesVfs[ART_FOLDER_PATH + filePath].readBitmap())
        }
    }
}
