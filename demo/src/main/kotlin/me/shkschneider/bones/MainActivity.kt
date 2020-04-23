package me.shkschneider.bones

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val bone = Bones.clavicle
    private val provider = Json(JsonConfiguration.Stable)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
        text.text = bone.toString()
        fab.setOnClickListener { _ ->
            val json: String = provider.stringify(Bone.serializer(), bone)
            text.append(json)
            val bone2 = provider.parse(Bone.serializer(), json)
            text.append(bone2.toString())
        }
    }

}
