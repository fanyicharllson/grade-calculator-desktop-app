# Student Grade Calculator — Desktop App

A Kotlin Multiplatform desktop application (JVM) built with Jetpack Compose for Desktop.
Imports student scores from Excel, calculates grades using the ICT University grading scale,
and exports results in multiple formats.

---

## Features

- Import student scores from `.xlsx` Excel files
- Preview imported data before calculating
- Calculate grades automatically using the ICT University scale
- Export results as **Excel, PDF, HTML, or XML**
- Clean dark UI built with Compose Multiplatform

---

## ICT University Grading Scale

| Average  | Grade | Result |
|----------|-------|--------|
| 80 – 100 | A     | Pass   |
| 70 – 79  | B+    | Pass   |
| 60 – 69  | B     | Pass   |
| 55 – 59  | C+    | Pass   |
| 50 – 54  | C     | Pass   |
| 45 – 49  | D+    | Pass   |
| 40 – 44  | D     | Pass   |
| 0  – 39  | F     | Fail   |

Pass threshold: **40.0**

---

## Project Structure

```
grade-calculator/
│
├── kotlin-android/                        Android mobile app (Jetpack Compose)
├── kotlin-console/                        Kotlin console app (JVM)
├── dart/                                  Dart version (team member)
│
└── composeApp/
    └── src/
        └── desktopMain/
            └── kotlin/
                │
                ├── App.kt                 Entry point, navigation, loading & error screens
                │
                ├── model/
                │   ├── Student.kt         data class — student record
                │   ├── GradeResult.kt     sealed class — Pass, Fail, NoScores
                │   └── AppState.kt        sealed class — drives screen navigation
                │
                ├── calculator/
                │   ├── Calculable.kt      interface — calculator contract
                │   ├── BaseCalculator.kt  abstract class — shared logic + init block
                │   └── GradeCalculator.kt extends BaseCalculator, implements Calculable
                │
                ├── export/
                │   ├── ExcelHelper.kt     read/write .xlsx via Apache POI
                │   └── ExportHelper.kt    export to PDF, HTML, XML
                │
                ├── viewmodel/
                │   └── AppViewModel.kt    state management, drives UI via AppState
                │
                └── ui/
                    ├── theme/
                    │   └── Theme.kt       colors, Material3 dark theme
                    ├── HomeScreen.kt      file import screen
                    ├── PreviewScreen.kt   data preview + user confirmation
                    └── ResultScreen.kt    results table + export buttons
```

---

## OOP Concepts Used

| Concept                            | Where                                                          |
|------------------------------------|----------------------------------------------------------------|
| `data class`                       | `Student`, `GradeResult.Pass/Fail`, `AppState.Preview/Results` |
| `sealed class`                     | `GradeResult`, `AppState`                                      |
| `interface` + default impl         | `Calculable`                                                   |
| `abstract class` + `init {}` block | `BaseCalculator`                                               |
| Inheritance + `override`           | `GradeCalculator` extends `BaseCalculator`                     |
| Lambda functions                   | `AppViewModel.export{}`, `calculateAll`, result mapping        |

---

## Expected Excel Format

| Name  | Score 1 | Score 2 | Score 3 |
|-------|---------|---------|---------|
| Alice | 85      | 90      | 78      |
| Bob   | 60      | 55      | 70      |

- Row 1 must be a header row
- Column A = student full name
- Columns B onwards = one score per column
- File must be `.xlsx` format

---

## Tech Stack

| Tool                              | Purpose              |
|-----------------------------------|----------------------|
| Kotlin                            | Primary language     |
| Compose Multiplatform (Desktop)   | UI framework         |
| Material 3                        | Design system        |
| Apache POI 5.2.3                  | Excel read/write     |
| OpenPDF 1.3.30                    | PDF export           |
| AndroidX ViewModel                | State management     |
| Kotlinx Coroutines                | Async operations     |

---

## For Developers — Build and Run

> Requires **JDK 17+** and **IntelliJ IDEA** (or Android Studio)

Clone the repo:
```shell
git clone https://github.com/fanyicharllson/grade-calculator.git
cd grade-calculator
```

Run in development mode:

```shell
# Windows
.\gradlew.bat :composeApp:run

# macOS / Linux
./gradlew :composeApp:run
```

---

## For Users — Download and Run the App

> No coding or setup required. Just download and double-click.

### Step 1 — Package the app (done once by the developer)

The developer runs this command to generate a native installer:

```shell
# Windows — generates a .msi installer
.\gradlew.bat :composeApp:packageMsi

# macOS — generates a .dmg
./gradlew :composeApp:packageDmg

# Linux — generates a .deb package
./gradlew :composeApp:packageDeb
```

Output is placed in:
```
composeApp/build/compose/binaries/main/
```

### Step 2 — Share the installer

Upload the generated `.msi` / `.dmg` / `.deb` file to:
- GitHub Releases (recommended — go to your repo → Releases → New Release → attach the file)
- Google Drive, OneDrive, or any shared folder

### Step 3 — User installs and runs

**Windows:**
1. Download `GradeCalculator.msi` from the release link
2. Double-click to install — follow the setup wizard
3. Open **Grade Calculator** from the Start Menu or Desktop shortcut

**macOS:**
1. Download `GradeCalculator.dmg`
2. Open it and drag the app to Applications
3. Double-click to launch

**Linux:**
1. Download `GradeCalculator.deb`
2. Run `sudo dpkg -i GradeCalculator.deb`
3. Launch from your app menu

> The installed app works like any normal desktop program — no terminal, no Java setup needed.
> The JVM is bundled inside the installer automatically by Compose Multiplatform.

---

## Pair Programming

| Role                    | Name             |
|-------------------------|------------------|
| Developer (Kotlin Desktop) | Fanyi Charllson |
| Developer (Dart)           | Adrien Tello    |
| Tester                     | Fanyi & Tello   |

---

## Related Versions

| Version           | Description                        |
|-------------------|------------------------------------|
| `kotlin-android/` | Android mobile app (Jetpack Compose) |
| `kotlin-console/` | Kotlin console app (JVM)           |
| `dart/`           | Dart version (Adrien Tello)        |