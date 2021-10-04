import com.soywiz.korge.Korge
import com.soywiz.korge.scene.Module
import com.soywiz.korge.scene.Scene
import com.soywiz.korim.color.Colors
import com.soywiz.korim.color.RGBA
import com.soywiz.korinject.AsyncInjector
import com.soywiz.korma.geom.SizeInt
import com.soywiz.korma.geom.Vector2D
import scene.*
import kotlin.reflect.KClass

suspend fun main() = Korge(Korge.Config(module = ConfigModule))

val RESOLUTION = Vector2D(1280, 720)
val COLLISION_COLOR = RGBA(255, 0, 0, 100)

object ConfigModule : Module() {
	override val bgcolor = Colors["161824"]
	override val size = SizeInt(1280, 720)
	val virtualSize = SizeInt(1280, 720)

	override val mainScene: KClass<out Scene> = LoadingScene::class

	override suspend fun AsyncInjector.configure() {
		mapPrototype { LoadingScene() }
		mapPrototype { MenuScene() }
		mapPrototype { Level1() }
		mapPrototype { Level2() }
		mapPrototype { IntersectionLevel() }
		mapPrototype { LevelCafe() }
		mapPrototype { LoftyPeaks() }
	}
}

