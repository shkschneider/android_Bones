object Bones {

    const val name = "Bones"
    const val id = "me.shkschneider.bones"
    const val version = "0.1.0"

    val versionCode: Int
        get() {
            val parts = version.takeWhile { it != '-' }.split(".")
            return parts[0].toInt() * 1_000_000 + parts[1].toInt() * 1_000 + parts[2].toInt()
        }

}
