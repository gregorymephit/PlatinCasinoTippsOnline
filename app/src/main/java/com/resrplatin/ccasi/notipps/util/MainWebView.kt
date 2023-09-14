package com.resrplatin.ccasi.notipps.util

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient

class MainWebView(val webView: WebView) {
    val settings
        get() = webView.settings
    private var webChromeClient: WebChromeClient?
        get() = null
        set(value) {
            webView.webChromeClient = value
        }
    var webViewClient: WebViewClient?
        get() = null
        set(value) {
            if(value != null) {
                webView.webViewClient = value
            }
        }
    fun loadUrl(url: String) {
        webView.loadUrl(url)
    }

    fun saveState(outState: Bundle) {
        webView.saveState(outState)
    }

    fun setSomeSettingsBoolean() {
        settings.allowContentAccess = true
        settings.allowFileAccess = true
        settings.javaScriptCanOpenWindowsAutomatically = true
        settings.domStorageEnabled = true
        settings.databaseEnabled = true
        settings.useWideViewPort = true
        settings.loadWithOverviewMode = true
    }

    fun setSomeSettingsInts() {
        settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        settings.cacheMode = WebSettings.LOAD_DEFAULT
    }

    fun setUserSettingsAgent() {
        var userAgent = settings.userAgentString
        Log.i("User agent", "Before: $userAgent")
        val indexOfWV = userAgent.indexOf("; wv")
        if(indexOfWV != -1) {
            userAgent = userAgent.substring(0, indexOfWV) + userAgent.substring(indexOfWV + 4, userAgent.length)
        }
        Log.i("User agent", "After: $userAgent")
        settings.userAgentString = userAgent
    }

    var webChromeClientFileChooser: (ValueCallback<Array<Uri>>) -> Unit
        get() = {}
        set(value) {
            webChromeClient = object: WebChromeClient() {
                    override fun onShowFileChooser(
                        webView: WebView,
                        filePathCallback: ValueCallback<Array<Uri>>,
                        fileChooserParams: FileChooserParams
                    ): Boolean {
                        value(filePathCallback)
                        return true
                    }
            }
        }
}