import scene.MenuScene
import com.soywiz.korge.Korge
import com.soywiz.korge.scene.Module
import com.soywiz.korge.scene.Scene
import com.soywiz.korinject.AsyncInjector
import helper.SpriteLibrary
import scene.Level1
import scene.Level2
import kotlin.reflect.KClass

suspend fun main() = Korge(Korge.Config(module = ConfigModule))

object ConfigModule : Module() {

	override val mainScene: KClass<out Scene> = scene.MenuScene::class

	override suspend fun AsyncInjector.configure() {
		val spriteLibrary = SpriteLibrary()
		spriteLibrary.init()
		mapPrototype { MenuScene() }
		mapPrototype { Level1() }
		mapPrototype { Level2() }
	}
}

