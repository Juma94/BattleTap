package com.julab.battletap;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;

public class DiscoverDevicesAndServices extends AppCompatActivity
{

    private TextView out;
    private static final int REQUEST_ENABLE_BT = 1;
    private BluetoothAdapter btAdapter;
    private ArrayList<BluetoothDevice> btDeviceList = new ArrayList<BluetoothDevice>();

    /* This routine is called when an activity completes.*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ENABLE_BT)
        {
            CheckBTState();
        }
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        if (btAdapter != null)
        {
            btAdapter.cancelDiscovery();
        }
        unregisterReceiver(ActionFoundReceiver);
    }

    private void CheckBTState()
    {
        // Check for Bluetooth support and then check to make sure it is turned on
        // If it isn't request to turn it on
        // List paired devices
        // Emulator doesn't support Bluetooth and will return null
        if(btAdapter==null)
        {
            out.append("\nBluetooth NOT supported. Aborting.");
            return;
        }
        else if (btAdapter.isEnabled())
        {
            out.append("\nBluetooth is enabled...");

            // Starting the device discovery
            btAdapter.startDiscovery();
        }
        else
        {
            Intent enableBtIntent = new Intent(btAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }
    }

    private final BroadcastReceiver ActionFoundReceiver = new BroadcastReceiver()
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            String action = intent.getAction();
            if(BluetoothDevice.ACTION_FOUND.equals(action))
            {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                out.append("\n  Device: " + device.getName() + ", " + device);
                btDeviceList.add(device);
            }
            else if(BluetoothDevice.ACTION_UUID.equals(action))
            {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                Parcelable[] uuidExtra = intent.getParcelableArrayExtra(BluetoothDevice.EXTRA_UUID);
                for (int i=0; i<uuidExtra.length; i++)
                {
                    out.append("\n  Device: " + device.getName() + ", " + device + ", Service: " + uuidExtra[i].toString());
                }
            }
            else if(BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action))
            {
                out.append("\nDiscovery Started...");
            }
            else if(BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action))
            {
                out.append("\nDiscovery Finished");
                Iterator<BluetoothDevice> itr = btDeviceList.iterator();
                while (itr.hasNext())
                {
                    // Get Services for paired devices
                    BluetoothDevice device = itr.next();
                    out.append("\nGetting Services for " + device.getName() + ", " + device);
                    if(!device.fetchUuidsWithSdp())
                    {
                        out.append("\nSDP Failed for " + device.getName());
                    }
                }

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover_devices_and_services);

        out = (TextView) findViewById(R.id.out);
        //out.setMovementMethod(new ScrollingMovementMethod());

        //Register the BroadcastReceiver
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothDevice.ACTION_UUID);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        registerReceiver(ActionFoundReceiver, filter); // Don't forget to unregister during onDestroy

        // Getting the Bluetooth adapter
        btAdapter = BluetoothAdapter.getDefaultAdapter();
        out.append("\nAdapter: " + btAdapter);

        CheckBTState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_discover_devices_and_services, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
