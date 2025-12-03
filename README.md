# Advent of Code 2025 — Kotlin solutions

This repository contains my solutions for the Advent of Code puzzles, implemented in Kotlin.

Repository highlights
- Language: Kotlin
- Build: Gradle (wrapper included)
- Structure: each day's solution is implemented as a `Puzzle` and the inputs are kept in `resources`.

Project layout

- src/main/kotlin/
  - Main.kt — program entry; it instantiates a `PuzzlePrinter` and prints answers for parts 1 and 2.
  - core/ — contains shared interfaces and utilities (e.g. `Puzzle`, `Utils`).
  - solution/ — contains puzzle implementations (e.g. `Puzzle1.kt`, `Puzzle2.kt`, `Puzzle3.kt`).
- src/main/resources/ — puzzle input files used when running the program.
- src/test/ — unit tests and test resources.
