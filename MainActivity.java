package app.khatwa;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.util.Locale;

public class MainActivity extends Activity {
    WebView wv;
    TextToSpeech tts;
    boolean ready = false;

    public class Bridge {
        @JavascriptInterface
        public void speak(String text, String lang, float rate) {
            if (!ready) return;
            tts.setLanguage(Locale.forLanguageTag(lang));
            tts.setSpeechRate(rate);
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, "u");
        }
    }

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        tts = new TextToSpeech(this, status -> ready = (status == TextToSpeech.SUCCESS));
        wv = new WebView(this);
        setContentView(wv);
        WebSettings ws = wv.getSettings();
        ws.setJavaScriptEnabled(true);
        ws.setDomStorageEnabled(true);
        wv.addJavascriptInterface(new Bridge(), "Android");
        wv.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView v, WebResourceRequest r) {
                Uri u = r.getUrl();
                if ("file".equals(u.getScheme())) return false;
                startActivity(new Intent(Intent.ACTION_VIEW, u));
                return true;
            }
        });
        wv.loadUrl("file:///android_asset/index.html");
    }

    @Override
    public void onBackPressed() {
        if (wv.canGoBack()) wv.goBack(); else super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        if (tts != null) tts.shutdown();
        super.onDestroy();
    }
}
