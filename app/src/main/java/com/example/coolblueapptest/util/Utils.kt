package com.example.coolblueapptest.util

import com.afollestad.materialdialogs.DialogCallback
import com.afollestad.materialdialogs.MaterialDialog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket

class Utils {
    companion object {
        private val TAG: String = Utils::class.java.simpleName

        /**
         * Show dialog.
         */
        fun showDialog(dialog: MaterialDialog,
                       title: String?,
                       message: String?,
                       positive: String?,
                       negative: String?,
                       positiveAction: DialogCallback? = null,
                       negativeAction: DialogCallback? = null) =
            dialog.cancelable(false)
                .title(text = title)
                .message(text = message)
                .clearPositiveListeners().positiveButton(text = positive, click = positiveAction)
                .clearNegativeListeners().negativeButton(text = negative, click = negativeAction)
                .show()

        /**
         * Check internet availability by pinging Google's DNS server.
         *
         * @return Boolean
         */
        suspend fun isOnline(): Boolean = withContext(Dispatchers.IO) {
            return@withContext try {
                val sock = Socket()
                sock.connect(InetSocketAddress("8.8.8.8", 53), 1500)
                sock.close()
                true
            } catch (e: IOException) {
                false
            }
        }
    }
}