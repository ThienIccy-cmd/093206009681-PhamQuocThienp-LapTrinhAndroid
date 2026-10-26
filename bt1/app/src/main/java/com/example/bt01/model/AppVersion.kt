package com.example.bt01.model

/**
 * OOP representation of application version information.
 */
class AppVersion(
    val major: Int = 2,
    val minor: Int = 1,
    val patch: Int = 0
) {
    fun getFormattedVersion(): String {
        return "Phiên bản $major.$minor.$patch"
    }

    override fun toString(): String {
        return getFormattedVersion()
    }
}
