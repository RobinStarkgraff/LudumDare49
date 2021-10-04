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
        const val KITCHEN_WALLS = "background/kitchenWalls.png"
        const val BATHROOM_WALLS = "background/bathroomWalls.png"
        const val FOREGROUND_LIGHT_FLAT = "foreground/FrogFlat_lightOverlay.png"
        const val BATHTUB = "furniture/bathtub.png"
        const val BOOKSHELF = "furniture/bookshelf.png"
        const val COUCH = "furniture/couch.png"
        const val COUCHTABLE = "furniture/couchtable.png"
        const val DESK = "furniture/desk.png"
        const val KITCHEN_ASSEMBLY = "furniture/kitchen_assembly.png"
        const val MIRROR = "furniture/mirror.png"
        const val NIGHT_STAND = "furniture/night_stand"
        const val FLOWER_POT = "objects/flower_pot.png"

        suspend fun loadImage(parent: Level, filePath: String, anchorX: Double, anchorY: Double): Image {
            return parent.sceneView.image(resourcesVfs[ART_FOLDER_PATH + filePath].readBitmap(), anchorX, anchorY)
        }
    }
}
