import android.app.Application
import com.facebook.stetho.Stetho
import timber.log.Timber

class RecipeApp: Application() {

    override fun onCreate() {
        super.onCreate()

        //Timber
        Timber.plant(Timber.DebugTree())

        //Stetho
        Stetho.initializeWithDefaults(this@RecipeApp)
    }
}