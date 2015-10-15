package com.julab.battletap;

import android.bluetooth.BluetoothAdapter;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Bundle;
import android.os.Message;

/**
 * Created by Abdelhamid on 15/10/2015.
 */
public class ListenBluetoothUnenablingAsync extends AsyncTask
{
    private BluetoothAdapter bluetoothAdapter;
    private Handler handler;

    public ListenBluetoothUnenablingAsync(BluetoothAdapter bluetoothAdapter, Handler handler)
    {
        this.bluetoothAdapter = bluetoothAdapter;
        this.handler = handler;
    }
    @Override
    protected Object doInBackground(Object[] params)
    {
        while(bluetoothAdapter.isEnabled());

        // notify to board game that connection was lost
        Message msg = handler.obtainMessage(BluetoothBattle.CONNECTION_FAILED);
        handler.sendMessage(msg);

        // Send a failure message back to the Activity
        msg = handler.obtainMessage(BluetoothBattle.MESSAGE_TOAST);
        Bundle bundle = new Bundle();
        bundle.putString(BluetoothBattle.TOAST, "Bluetooth adapter was desabled");
        msg.setData(bundle);
        handler.sendMessage(msg);

        return null;
    }
}
