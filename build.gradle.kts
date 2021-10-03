import com.soywiz.korge.gradle.*

buildscript {
	val korgePluginVersion: String by project
 	val klockVersion = "2.4.3"

	repositories {
		mavenLocal()
		mavenCentral()
		google()
		maven { url = uri("https://plugins.gradle.org/m2/") }
		maven { url = uri("https://dl.bintray.com/korlibs/korlibs") }
	}

	dependencies {
		classpath("com.soywiz.korlibs.klock:klock:$klockVersion")

		classpath("com.soywiz.korlibs.korge.plugins:korge-gradle-plugin:$korgePluginVersion")
	}
}

apply<KorgeGradlePlugin>()

korge {
	id = "com.example.example"
// To enable all targets at once

	//targetAll()

// To enable targets based on properties/environment variables
	//targetDefault()

// To selectively enable targets

	targetJvm()
	targetJs()
	targetDesktop()
	targetIos()
	targetAndroidIndirect() // targetAndroidDirect()
}
