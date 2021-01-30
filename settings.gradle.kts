rootProject.name = "ClockApp"
rootProject.buildFileName = "build.gradle.kts"

include(
        ":app",
        ":data",
        ":common",
        ":feature_add_alarm",
        ":feature_alarms",
        ":feature_alarm_timeout"
)