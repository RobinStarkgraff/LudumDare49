package helper

import com.soywiz.korge.view.Image
import com.soywiz.korge.view.image
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.file.std.resourcesVfs
import scene.Level

class ImageLibrary {
    companion object {
        private const val ART_FOLDER_PATH = "art/"

        const val BACKGROUND_IMAGE_FLAT = "background/frog_flat.png"

        suspend fun loadImage(parent: Level, filePath: String, anchorX: Double, anchorY: Double): Image {
            return parent.sceneView.image(resourcesVfs[ART_FOLDER_PATH + filePath].readBitmap(), anchorX, anchorY)
        }
    }
}
