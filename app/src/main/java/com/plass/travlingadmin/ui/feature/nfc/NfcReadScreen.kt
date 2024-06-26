package com.plass.travlingadmin.ui.feature.nfc

import android.app.Activity
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.nfc.tech.Ndef
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.LifecycleStartEffect
import androidx.navigation.NavController
import com.plass.travlingadmin.MainActivity
import com.plass.travlingadmin.utiles.showShortToast
import kotlinx.coroutines.launch

@Composable
fun NfcReadScreen(
    navController: NavController,
) {
    val context = LocalContext.current
    val activity = (context as Activity) as MainActivity
    val adapter = activity.getNfcAdapter()

    val coroutineScope = rememberCoroutineScope()

    var insertText by remember { mutableStateOf("") }
    val sendMessage: (String) -> Unit = {
        coroutineScope.launch {
            context.showShortToast(it)
        }
    }

    LifecycleStartEffect(key1 = Unit) {
        adapter.enableReaderMode(
            activity, NfcAdapter.ReaderCallback { tag: Tag? ->
                try {
                    val ndef = Ndef.get(tag)
                    if (ndef != null) {
                        ndef.connect()
                        val record = ndef.ndefMessage.records.first()

                        sendMessage(String(record.payload))
                    }
                } catch (e: Exception) {
                    Log.i("writeError", e.message.toString());
                }},
            NfcAdapter.FLAG_READER_NFC_A
                    or NfcAdapter.FLAG_READER_NFC_B
                    or NfcAdapter.FLAG_READER_NFC_F
                    or NfcAdapter.FLAG_READER_NFC_V
                    or NfcAdapter.FLAG_READER_NFC_BARCODE,
            null)
        onStopOrDispose {
            adapter.disableReaderMode(activity)
        }
    }
}