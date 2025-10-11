package com.example.bluetoothfinder

import android.Manifest
import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.bluetooth.le.ScanResult
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    private var mBluetoothAdapter: BluetoothAdapter? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bluetoothManager: BluetoothManager = getSystemService(BluetoothManager::class.java)
        mBluetoothAdapter = bluetoothManager.adapter

        if (!hasPermissions()) {
            requestPermissions(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.BLUETOOTH_SCAN,
                    Manifest.permission.BLUETOOTH_CONNECT
                ), 1
            )
        }

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    mBluetoothAdapter?.let {
                        BLEScannerScreen(it)
                    } ?: run {
                        Text(
                            text = "Bluetooth not available",
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }
        }
    }

    private fun hasPermissions(): Boolean {
        val adapter = mBluetoothAdapter
        if (adapter == null || !adapter.isEnabled) {
            Log.d("DBG", "No Bluetooth LE capability")
            return false
        } else if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
            Log.d("DBG", "No fine location access")
            return false
        }
        return true
    }
}

// UI Composables
@SuppressLint("MissingPermission")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BLEScannerScreen(
    bluetoothAdapter: BluetoothAdapter,
    viewModel: BLEViewModel = viewModel()
) {
    val scanResults: List<ScanResult>? by viewModel.scanResults.observeAsState(emptyList())
    val isScanning: Boolean by viewModel.fScanning.observeAsState(false)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Bluetooth LE Scanner",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Button(
            onClick = {
                if (!isScanning) {
                    bluetoothAdapter.bluetoothLeScanner?.let { scanner ->
                        viewModel.scanDevices(scanner)
                    }
                }
            },
            enabled = !isScanning,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Text(if (isScanning) "Scanning..." else "Start Scan")
        }

        if (isScanning) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        Divider(modifier = Modifier.padding(vertical = 8.dp))

        Text(
            text = "Found Devices: ${scanResults?.size ?: 0}",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            scanResults?.let { results ->
                items(results) { result ->
                    DeviceItem(result)
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
@Composable
fun DeviceItem(scanResult: ScanResult) {
    val device = scanResult.device
    val deviceName = device.name ?: "Unknown Device"
    val deviceAddress = device.address
    val rssi = scanResult.rssi
    val isConnectable = scanResult.isConnectable

    // Calculate power in mW from dBm
    val powerMw = Math.pow(10.0, rssi / 10.0)
    val powerUnit = when {
        powerMw >= 1.0 -> "%.2f mW".format(powerMw)
        powerMw >= 0.001 -> "%.2f μW".format(powerMw * 1000)
        else -> "%.2f nW".format(powerMw * 1000000)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isConnectable)
                MaterialTheme.colorScheme.surface
            else
                MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Text(
                text = deviceName,
                style = MaterialTheme.typography.titleMedium,
                color = if (isConnectable) Color.Black else Color.Gray
            )
            Text(
                text = deviceAddress,
                style = MaterialTheme.typography.bodyMedium,
                color = if (isConnectable) Color.DarkGray else Color.LightGray
            )
            Text(
                text = "RSSI: $rssi dBm ($powerUnit)",
                style = MaterialTheme.typography.bodySmall,
                color = if (isConnectable) Color.DarkGray else Color.LightGray
            )
            if (!isConnectable) {
                Text(
                    text = "Not Connectable",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}