package com.plass.travling

import android.app.PendingIntent
import android.content.Intent
import android.nfc.NfcAdapter
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.plass.travling.ui.feature.home.HomeScreen
import com.plass.travling.ui.feature.join.JoinScreen
import com.plass.travling.ui.feature.locate.LocateItem
import com.plass.travling.ui.feature.locate.LocateScreen
import com.plass.travling.ui.feature.login.LoginScreen
import com.plass.travling.ui.feature.nfc.NfcReadScreen
import com.plass.travling.ui.feature.nfc.NfcTagDialog
import com.plass.travling.ui.feature.nfc.NfcWriteScreen
import com.plass.travling.ui.feature.root.BottomNavItem
import com.plass.travling.ui.feature.root.BottomNavigation
import com.plass.travling.ui.feature.root.NavRoot
import com.plass.travling.ui.theme.TravelingTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private lateinit var nfcAdapter: NfcAdapter
    private lateinit var pending : PendingIntent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        nfcAdapter = NfcAdapter.getDefaultAdapter(this)
        val launchIntent = Intent(this, this.javaClass)
        launchIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        pending = PendingIntent.getActivity(this, 0, launchIntent, PendingIntent.FLAG_IMMUTABLE)

        setContent {
            val coroutineScope = rememberCoroutineScope()
            val navHostController = rememberNavController()
            var navItems by remember { mutableStateOf(listOf(
                BottomNavItem(NavRoot.HOME, true, R.drawable.ic_home),
                BottomNavItem("nfc", false, R.drawable.ic_nfc),
                BottomNavItem(NavRoot.LOCATE, false, R.drawable.ic_locate),
            )) }

            var isShowBottomNavigationBar by remember { mutableStateOf(true) }

            val changeBottomNav: (visible: Boolean) -> Unit = {
                coroutineScope.launch {
                    isShowBottomNavigationBar = it
                }
            }
            
            var isShowNfcDialog by remember { mutableStateOf(false) }

            TravelingTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if (isShowNfcDialog) {
                        NfcTagDialog(
                            title = "태깅 준비 완료",
                            description = "NFC를 휴대폰 뒤쪽에 태깅해주세요",
                            onClickCancel = {
                                coroutineScope.launch {
                                    isShowNfcDialog = false
                                }
                            },
                            onClickConfirm = {

                            }
                        )
                    }
                    
                    Scaffold(
                        bottomBar = {
                            if (isShowBottomNavigationBar) {
                                BottomNavigation(
                                    items = navItems,
                                    onClickItem = { item ->
                                        val items = navItems.map {
                                            it.copy(
                                                isSelected = it.title == item.title
                                            )
                                        }
                                        coroutineScope.launch {
                                            navHostController.navigate(item.title)
                                            navItems = items
                                        }
                                    },
                                    onClickNfc = {
                                        coroutineScope.launch {
                                            isShowNfcDialog = true
                                        }
                                    }
                                )
                            }
                        }
                    ) {
                        NavHost(
                            modifier = Modifier.padding(it),
                            navController = navHostController,
                            startDestination = NavRoot.HOME
                        ) {

                            composable(NavRoot.NFC_WRITE) {
                                NfcWriteScreen(navController = navHostController)
                            }

                            composable(NavRoot.NFC_READ) {
                                NfcReadScreen(navController = navHostController)
                            }
                            composable(NavRoot.LOCATE) {
                                LocateScreen(navController = navHostController)
                            }
                            composable(NavRoot.LOGIN) {
                                LoginScreen(navController = navHostController) {
                                    changeBottomNav(false)
                                }
                            }
                            composable(NavRoot.JOIN) {
                                JoinScreen(navController = navHostController) {
                                    changeBottomNav(false)
                                }
                            }
                            composable(NavRoot.HOME) {
                                HomeScreen(navController = navHostController)
                            }
                        }
                    }
                }
            }
        }
    }


    fun getNfcAdapter(): NfcAdapter = nfcAdapter
}
