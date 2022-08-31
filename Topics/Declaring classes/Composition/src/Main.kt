class OperatingSystem {
    var name = ""
}

class DualBoot {
    val operatingSystem = OperatingSystem()
    var primaryOs: OperatingSystem = operatingSystem
    var secondaryOs: OperatingSystem = operatingSystem
}