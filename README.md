# ToyJourney-Gradle

## Disclaimer

I am not the owner or creator of the original project.
This is a fork of the original project,
I Just a mentor who is helping a student in 204114 course to make a gradle version of the original project.

Goal of this project is to make a gradle version of the original project.

## Original Project

- [Original Project](https://github.com/Kazuki-Int/ToysJourney)

## Requirements

### For macOS

- Java 21 or later
- Gradle 8.0 or later

You can skip the following steps if you already have the requirements installed.

> If you don't have `homebrew` installed, you can
> visit [here](https://github.com/CSCMU-65s/cs-wiki101/blob/main/instructions/en/homebrew.md) to install it.

```bash
brew install openjdk gradle 
```

## How to run

- Clone this project

- Open terminal and run the following command

```bash
git clone git@github.com:AppleBoiy/ToysJourney-Gradle.git
```

- Compile java files

```bash
./gradlew build && ./gradlew jar
```

- Run the jar file

```bash
java -jar build/libs/ToysJourney-gradle-1.0-SNAPSHOT.jar
```

## More Information

Project Structure

```bash
.
├── README.md // this file
├── build.gradle.kts // gradle build file
├── gradle
│   └── wrapper
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── gradlew // gradle wrapper for unix (linux, mac)
├── gradlew.bat // gradle wrapper for windows
├── settings.gradle.kts // gradle settings file
└── src
    ├── main
    │   ├── java
    │   │   └── org
    │   │       └── toysjourney // main package
    │   │           ├── Main.java // main class
    │   │           └── ... // Rest of the classes will be here
    │   └── resources // resources folder
    │       └── ... // Images, sounds, Texts, etc. will be here
    └── test // For this project we don't have test
     
```