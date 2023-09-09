package lets.play_in.platincasinotippsonline

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.findNavController
import lets.play_in.platincasinotippsonline.views.gameView.GameView
import java.io.File
import java.io.IOException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    val activityResultLauncher = registerForActivityResult<String, Boolean>(
    ActivityResultContracts.RequestPermission()
    ) { _: Boolean? ->
        // Set variables
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val chooserIntent = Intent(Intent.ACTION_CHOOSER)
        val old = Intent(Intent.ACTION_GET_CONTENT)
        val intentArray = arrayOf(takePictureIntent)
        var imgFile: File? = null
        // Infix functions
        infix fun String.put(intent: Array<Intent>) = chooserIntent.putExtra(this, intent)
        infix fun String.put(intent: Intent) = chooserIntent.putExtra(this, intent)
        infix fun String.put(uri: Uri) {
            takePictureIntent.putExtra(this, uri)
            val firstF = supportFragmentManager.fragments.first()
            if(firstF is GameView) {
                firstF.uc = uri
            }
        }
        infix fun String.category(type: String) {
            old.addCategory(this)
            old.type = type
        }
        infix fun Intent.start(requestCode: Int) = startActivityForResult(this, requestCode)
        // Set old settings
        Intent.CATEGORY_OPENABLE category  "*/*"
        // Take img file
        try {
            imgFile = File.createTempFile(
                "image",
                ".jpg",
                this.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            )
        } catch (_: Exception) {}
        // Other sets.
        MediaStore.EXTRA_OUTPUT put Uri.fromFile(imgFile)
        Intent.EXTRA_INTENT put old
        Intent.EXTRA_INITIAL_INTENTS put intentArray
        chooserIntent start 1
    }
}