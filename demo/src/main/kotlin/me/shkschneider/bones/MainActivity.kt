package me.shkschneider.bones

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private var bone by BoneDelegate(Bone.serializer())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
        bone = Bone.clavicle
        text.append("$bone\n")
        fab.setOnClickListener { _ ->
            text.append("$bone\n")
        }
    }

}
