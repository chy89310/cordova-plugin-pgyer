package wang.imchao.plugin;

import android.util.Log;

import com.pgyersdk.feedback.PgyFeedbackShakeManager;
import com.pgyersdk.update.PgyUpdateManager;

import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;

public class PgyerPlugin extends CordovaPlugin {
    private static String TAG = "PgyerPlugin";

    @Override
    public void initialize(final CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        cordova.getThreadPool().execute(new Runnable() {
            public void run() {
                new PgyUpdateManager.Builder().register();
                Log.i(TAG, "Pgyer update check registered");
            }
        });
    }

    @Override
    public void onResume(boolean multitasking) {
        super.onResume(multitasking);
        // 默认采用摇一摇弹出 Dialog 方式
        new PgyerFeedbackManager.PgyerFeedbackBuilder().builder().register();
    }

    @Override
    public void onPause(boolean multitasking) {
        super.onPause(multitasking);
    }
}
