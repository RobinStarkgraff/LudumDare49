package objects.interactables

class ObjectiveItem(private val interactable: Interactable) : Interactable() {

    override var scene = interactable.scene
    override var pos = interactable.pos
    override var interactionDistance = interactable.interactionDistance

    init {
        scene.interactableList.remove(interactable)
        scene.interactableList.add(this)
    }

    var completed = false

    override fun interact(): Boolean {
        completed = interactable.interact()
        return completed
    }
}
