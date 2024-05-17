package com.plass.travlingadmin.ui.feature.nfc

import android.app.Activity
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.nfc.tech.Ndef
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LifecycleStartEffect
import com.plass.travlingadmin.MainActivity
import com.plass.travlingadmin.R
import com.plass.travlingadmin.ui.theme.TravelingTheme
import com.plass.travlingadmin.utiles.showShortToast
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NfcTagDialog(
    title: String,
    description: String,
    onClickCancel: () -> Unit,
    onClickConfirm: () -> Unit
) {
    val context = LocalContext.current
    val activity = (context as Activity) as MainActivity
    val adapter = activity.getNfcAdapter()

    val modalBottomSheetState = rememberModalBottomSheetState()
    val coroutineScope = rememberCoroutineScope()
    val bottomPadding = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()

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
                    sendMessage("(0) ${e.message.toString()}")
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

    ModalBottomSheet(
        onDismissRequest = { onClickCancel() },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
        containerColor = TravelingTheme.colorScheme.White
    ) {
        Column(
            modifier = Modifier
                .padding(top = 10.dp, start = 10.dp, end = 10.dp, bottom = bottomPadding)
                .fillMaxWidth()
                .height(300.dp)
                .background(TravelingTheme.colorScheme.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                textAlign = TextAlign.Center,
                color = TravelingTheme.colorScheme.Black,
                style = TravelingTheme.typography.title2B
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = description,
                textAlign = TextAlign.Center,
                color = TravelingTheme.colorScheme.Black57,
                style = TravelingTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(27.dp))
            Image(
                modifier = Modifier.size(127.dp),
                painter = painterResource(id = R.drawable.ic_nfc_real),
                contentDescription = "NFC 사진"
            )
        }
    }
}
