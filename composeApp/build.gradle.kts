import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeHotReload)
}

kotlin {
    jvm()
    
    sourceSets {
        commonMain.dependencies {
            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.material3)
            implementation(libs.compose.ui)
            implementation(libs.compose.components.resources)
            implementation(libs.compose.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)

            // Navigation library
            implementation("org.jetbrains.androidx.navigation:navigation-compose:2.8.0-alpha10")
            // Export libraries
            implementation("org.apache.poi:poi-ooxml:5.2.3") // Excel
            implementation("com.github.librepdf:openpdf:1.3.30") // PDF
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3") // JSON/XML
            // Log4j2 implementation (required by Apache POI)
            implementation("org.apache.logging.log4j:log4j-core:2.20.0")
            implementation("org.apache.logging.log4j:log4j-api:2.20.0")
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        jvmMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutinesSwing)
        }
    }
}


compose.desktop {
    application {
        mainClass = "com.fanyicharllson.gradecalculator.MainKt"

        nativeDistributions {
//            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            targetFormats(TargetFormat.Msi)
            packageName        = "GradeCalculator"
            packageVersion     = "1.0.0"
            description        = "Student Grade Calculator — ICT University"
            copyright          = "Fanyi Charllson"
            vendor             = "Fanyi Charllson"

            windows {
                // Creates desktop shortcut
                shortcut           = true
                // Adds to Start Menu
                menu               = true
                menuGroup          = "Grade Calculator"
                // Icon file — place a .ico file in composeApp/src/desktopMain/resources/
//                iconFile.set(project.file("src/desktopMain/resources/icon.ico"))
                // Upgrade UUID — keeps installer consistent across versions
                upgradeUuid        = "8A3F2C1D-4B5E-6F7A-8B9C-0D1E2F3A4B5C"
            }
        }
    }
}
