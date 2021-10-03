import scene.MenuScene
import com.soywiz.korge.Korge
import com.soywiz.korge.scene.Module
import com.soywiz.korge.scene.Scene
import com.soywiz.korinject.AsyncInjector
import scene.Level1
import scene.Level2
import scene.LoadingScene
import kotlin.reflect.KClass

suspend fun main() = Korge(Korge.Config(module = ConfigModule))

object ConfigModule : Module() {

	override val mainScene: KClass<out Scene> = LoadingScene::class

	override suspend fun AsyncInjector.configure() {
		mapPrototype { LoadingScene() }
		mapPrototype { MenuScene() }
		mapPrototype { Level1() }
		mapPrototype { Level2() }
	}
}

