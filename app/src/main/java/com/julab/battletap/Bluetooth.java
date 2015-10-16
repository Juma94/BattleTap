package com.julab.battletap;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by Abdelhamid on 01/10/2015.
 */
public class Bluetooth
{
    private static final int REQUEST_ENABLE_BT = 10;
    private BluetoothAdapter blueAdapter;
    private Activity activity;

    public Bluetooth(Activity activity) throws Exception
    {
        this.activity = activity;

        // initialise le bluetooth
        blueAdapter = BluetoothAdapter.getDefaultAdapter();
        if (blueAdapter == null)
        {
            throw new Exception();
        }

        // active le bluetooth
        if (!blueAdapter.isEnabled())
        {
            blueAdapter.enable();
        }

        // rend l'appareil visible en bluetooth pendant 300s
        Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 120); // Cette ligne permet de définir une durée de visibilité de notre choix
        activity.startActivityForResult(discoverableIntent, REQUEST_ENABLE_BT);

        /*
        // On crée un BroadcastReceiver pour ACTION_FOUND
        BroadcastReceiver receiver = new BroadcastReceiver()
        {
            public void onReceive(Context context, Intent intent)

                String action = intent.getAction();
                // Quand la recherche trouve un terminal
                if (BluetoothDevice.ACTION_FOUND.equals(action))
                {
                    // On récupère l'object BluetoothDevice depuis l'Intent
                    BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    // On ajoute le nom et l'adresse du périphérique dans un ArrayAdapter (par exemple pour l'afficher dans une ListView)
                    mArrayAdapter.add(device.getName() + "\n" + device.getAddress());
                }
            }
        };

        // Inscrire le BroadcastReceiver
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        activity.registerReceiver(receiver, filter); // N'oubliez pas de le désinscrire lors du OnDestroy() !
        */
    }

    public Set<BluetoothDevice> getDeviceAppaired()
    {
        ArrayList<String> mArrayAdapter = new ArrayList<String>();

        // recupere la liste des devices appaires et les stock dans un arrayList
        Set<BluetoothDevice> pairedDevices = blueAdapter.getBondedDevices();
        // If there are paired devices
        if (pairedDevices.size() > 0)
        {
            // Loop through paired devices
            for (BluetoothDevice device : pairedDevices)
            {
                Log.i("Device", device.getName() + "\n" + device.getAddress());
                // Add the name and address to an array adapter to show in a ListView
                mArrayAdapter.add(device.getName() + "\n" + device.getAddress());
            }
        }

        return pairedDevices;
    }

    public void getNewDevices()
    {
        final ArrayList<String> mArrayAdapter = new ArrayList<String>();

        // On crée un BroadcastReceiver pour ACTION_FOUND
        final BroadcastReceiver receiver = new BroadcastReceiver()
        {
            public void onReceive(Context context, Intent intent)
            {
                String action = intent.getAction();
                // Quand la recherche trouve un terminal
                if (BluetoothDevice.ACTION_FOUND.equals(action))
                {
                    // On récupère l'object BluetoothDevice depuis l'Intent
                    BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    // On ajoute le nom et l'adresse du périphérique dans un ArrayAdapter (par exemple pour l'afficher dans une ListView)
                    mArrayAdapter.add(device.getName() + "\n" + device.getAddress());
                }
            }
        };

        blueAdapter.startDiscovery();

        blueAdapter.cancelDiscovery();

        // Inscrire le BroadcastReceiver
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        activity.registerReceiver(receiver, filter); // N'oubliez pas de le désinscrire lors du OnDestroy() !
    }
    // onActivityResult() à redéfinir dans l'activité pour savoir si le bluetooth est bien activé ou si le discovering a été accepté

    // a voir ?????
    /*
    private static final UUID MY_UUID = UUID.fromString("0000110E-0000-1000-8000-00805F9B34FB");

    // Permet de récupérer la connexion bluetooth via un device deja connecte
    if(device.getBondState()==device.BOND_BONDED){
    Log.d(TAG,device.getName());
    //BluetoothSocket mSocket=null;
    try {
        mSocket = device.createInsecureRfcommSocketToServiceRecord(MY_UUID);
    } catch (IOException e1) {
        // TODO Auto-generated catch block
        Log.d(TAG,"socket not created");
        e1.printStackTrace();
    }
    try{
        mSocket.connect();
    }
    catch(IOException e){
        try {
            mSocket.close();
            Log.d(TAG,"Cannot connect");
        } catch (IOException e1) {
            Log.d(TAG,"Socket not closed");
            e1.printStackTrace();
        }
    }

     */

}
