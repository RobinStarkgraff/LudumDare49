package helper

import com.soywiz.korge.view.SolidRect
import com.soywiz.korge.view.onCollision
import com.soywiz.korge.view.onCollisionExit
import com.soywiz.korge.view.xy
import objects.interactables.Interactable
import scene.Level

class InvisibleTrigger(scene: Level, width: Int, height: Int, x: Int, y: Int, interactable: Interactable) {
    private var rect: SolidRect

    var currentlyActive = false

    init {
        rect = SolidRect(width, height, StaticData.triggerTestColor).xy(x, y)
        scene.sceneView.addChild(rect)

        rect.onCollision({it == scene.player?.collisionShape && !currentlyActive}) {
            currentlyActive = true
            interactable.interact()
        }

        rect.onCollisionExit {
            currentlyActive = false
            interactable.interact()
        }
    }
}
