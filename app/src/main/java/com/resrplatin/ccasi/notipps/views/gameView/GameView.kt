@file:Suppress("DEPRECATION")

package com.resrplatin.ccasi.notipps.views.gameView

import android.Manifest
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.webkit.CookieManager
import android.webkit.ValueCallback
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.core.view.allViews
import com.resrplatin.ccasi.notipps.MainActivity
import com.resrplatin.ccasi.notipps.R
import com.resrplatin.ccasi.notipps.util.MainWebView
import com.resrplatin.ccasi.notipps.views.ViewFragment

class GameView: ViewFragment() {
    private var wv: MainWebView? = null
    private var fpc: ValueCallback<Array<Uri>>? = null
    var uc: Uri? = null
    private var startUrl: String? = null

    private infix fun MainWebView.setCookieManager(value: Boolean) {
        CookieManager.getInstance().apply {
            setAcceptThirdPartyCookies(this@setCookieManager.webView, value)
            setAcceptCookie(value)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.resources?.configuration?.run {
            keyboardHidden = Configuration.KEYBOARDHIDDEN_YES
            orientation = Configuration.ORIENTATION_PORTRAIT
            screenLayout = Configuration.SCREENLAYOUT_SIZE_UNDEFINED
        }
        activity?.window?.setFlags(
            WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN,
        )
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        setRequestPermissionLauncher()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflateDefault(R.layout.view_game, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if(wv?.webView?.canGoBack() == true) {
                    wv?.webView?.goBack()
                }
            }
        })
        wv = MainWebView(view.allViews.filter { it is WebView }.first() as WebView)
        configurationForWebView()
    }

    fun configurationForWebView() {
        val webView = wv
        if(webView != null) {
            webView.setSomeSettingsInts()
            webView.settings.javaScriptEnabled = true
            webView.setSomeSettingsBoolean()
            webView.setUserSettingsAgent()
            webView.settings.allowFileAccessFromFileURLs = true
            webView setCookieManager true
            webView.webChromeClientFileChooser = {
                requestPermissionLauncher.launch(Manifest.permission.CAMERA)
                fpc = it
            }
            webView.settings.allowUniversalAccessFromFileURLs = true
            webView.webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(
                    view: WebView,
                    request: WebResourceRequest
                ): Boolean {
                    var uri = request.url.toString()
                    val oldUri = uri
                    val condition = uri.contains("/") to uri.contains("http")
                    if (condition.first && condition.second) {
                        return false
                    } else {
                        uri = if (condition.first) {
                            if (uri.startsWith("://")) {
                                "http$uri"
                            } else if (uri.startsWith("//")) {
                                "http:"
                            } else {
                                ""
                            }
                        } else {
                            if (uri.startsWith("http:")) {
                                uri.substring(0, 5) + "//" + uri.substring(5)
                            } else {
                                ""
                            }
                        }
                    }
                    return if (uri != "") {
                        Log.e("Uri", uri)
                        false
                    } else {
                        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(oldUri)))
                        true
                    }
                }

                override fun onReceivedLoginRequest(
                    view: WebView?,
                    realm: String,
                    account: String?,
                    args: String
                ) {
                    Log.i(
                        "onReceivedLoginRequest",
                        "Let's look... View is ${if (view == null) "null" else "not null"}, realm is $realm, account is $account and args is $args"
                    )
                    super.onReceivedLoginRequest(view, realm, account, args)
                }
            }

            arguments?.getString("url")?.let { startUrl ->
                webView.loadUrl(startUrl)
            } ?: throw NullPointerException("Url is null.")
        }
    }


    private lateinit var requestPermissionLauncher: ActivityResultLauncher<String>

    private fun setRequestPermissionLauncher() {
        requestPermissionLauncher = (requireActivity() as MainActivity).activityResultLauncher
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (fpc == null) return
        fun justNull() {
            fpc?.onReceiveValue(null)
        }
        if (resultCode == -1) {
            fun checkUc() {
                if (uc != null) {
                    fpc?.onReceiveValue(arrayOf(uc!!))
                } else {
                    justNull()
                }
            }
            fun checkUcToo() {
                Log.i("checkUcToo" ,"Called.")
                checkUc()
            }
            if (data != null) {
                val d = data.dataString
                if (d != null) {
                    val u = Uri.parse(d)
                    fpc?.onReceiveValue(arrayOf(u))
                    Log.i("onActivityResult", "All true!!!")
                } else {
                    checkUcToo()
                }
            } else {
                checkUc()
            }
        } else {
            justNull()
        }
        fpc = null
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        wv?.saveState(outState)
    }
}