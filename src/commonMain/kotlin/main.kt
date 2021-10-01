import Scenes.MenuScene
import com.soywiz.korge.Korge
import com.soywiz.korge.scene.Module
import com.soywiz.korge.scene.Scene
import com.soywiz.korinject.AsyncInjector
import kotlin.reflect.KClass

suspend fun main() = Korge(Korge.Config(module = ConfigModule))

object ConfigModule : Module() {

	override val mainScene: KClass<out Scene> = Scenes.MenuScene::class

	override suspend fun AsyncInjector.configure() {
		mapPrototype { MenuScene() }
	}
}
