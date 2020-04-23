object Project {

    const val name = "Bones"

    const val id = "me.shkschneider.bones"

    private val version = listOf(0, 1, 0)
    val versionName: String =
        version.joinToString(".")
    val versionCode: Int =
        with(version) {
            get(0) * 1_000_000 + get(1) * 1_000 + get(2)
        }

}
