package com.julab.battletap;

import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/*
 * This is the battle activity
 */
public class BluetoothBattle extends Activity
{
    // Game variables
    private int[] TabJeu = new int[0];

    private TextView nbCaught, nbToCatch, nbTaps;
    private int current = 0, nbCurrentTaps, nbCurrentCaught;

    private AlertDialog waitMessage;

    // Debugging
    private static final String TAG = "BluetoothBattle";
    private static final boolean isDebuggingMode = true;

    // Message types sent from the BluetoothBattleService Handler
    public static final int MESSAGE_STATE_CHANGE = 1;
    public static final int MESSAGE_READ = 2;
    public static final int MESSAGE_WRITE = 3;
    public static final int MESSAGE_DEVICE_NAME = 4;
    public static final int MESSAGE_TOAST = 5;

    // Key names received from the BluetoothBattleService Handler
    public static final String DEVICE_NAME = "device_name";
    public static final String TOAST = "toast";

    // Intent request codes
    private static final int REQUEST_CONNECT_DEVICE = 1;
    public static final int DISCOVERABLE_REQUEST = 2;

    // Layout Views
    private Button btnPush;
    private Button btnConfirm;

    // Name of the connected device
    private String connectedDeviceName = null;
    // String buffer for outgoing messages
    private StringBuffer outStringBuffer;
    // Local Bluetooth adapter
    private BluetoothAdapter bluetoothAdapter = null;
    // Member object for the chat services
    private BluetoothBattleService battleService = null;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (isDebuggingMode) Log.e(TAG, "+++ ON CREATE +++");

        // Set up the window layout
        setContentView(R.layout.activity_board_game_multi);

        // Get local Bluetooth adapter
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        // If the adapter is null, then Bluetooth is not supported
        if (bluetoothAdapter == null)
        {
            // Changer le toast par un dialog
            Toast.makeText(this, R.string.bluetooth_unavailable, Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        // Initialize the push button with a listener that for click events
        btnPush = (Button) findViewById(R.id.btnPush);
        btnPush.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                if (event.getAction() == MotionEvent.ACTION_UP)
                {
                    btnPush.setBackgroundResource(R.drawable.push_button);
                    nbCurrentTaps++;
                    nbTaps.setText(nbCurrentTaps + "");
                }
                else if (event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    btnPush.setBackgroundResource(R.drawable.pushed_button);
                }
                return true;
            }
        });

        // Initialize the confirm button with a listener for click events
        btnConfirm = (Button) findViewById(R.id.btnConfirm);
        btnConfirm.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                if (event.getAction() == MotionEvent.ACTION_UP)
                {
                    // si fin tableau message Terminer + lancement summarry
                    if (current < 9)
                    {
                        btnConfirm.setBackgroundResource(R.drawable.btn_valider);
                        current++;
                        nbCurrentCaught++;
                        nbCurrentTaps = 0;
                        updateNumbers();
                    }
                    else
                    {
                        sendMessage("Finish");
                    }

                }
                else if (event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    btnConfirm.setBackgroundResource(R.drawable.btn_valider_pushed);
                }
                return true;
            }
        });

        // create a dialog to make wait the user
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Waiting player...");
        builder.setCancelable(false);
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                BluetoothBattle.this.finish();
            }
        });
        waitMessage = builder.create();
    }

    public void updateNumbers()
    {
        nbTaps = (TextView) findViewById(R.id.board_game_multi_nbTaps_id);
        nbTaps.setText(nbCurrentTaps + "");

        nbCaught = (TextView) findViewById(R.id.board_game_multi_nb_caught_current_id);
        nbCaught.setText(nbCurrentCaught + "/" + TabJeu.length);

        nbToCatch = (TextView) findViewById(R.id.board_game_multi_nb_to_catch_current_id);
        nbToCatch.setText(TabJeu[current] + "");
    }

    @Override
    public void onStart()
    {
        super.onStart();
        if (isDebuggingMode) Log.e(TAG, "++ ON START ++");

        // If BT is not on, request that it be enabled.
        if (!bluetoothAdapter.isEnabled())
        {
            bluetoothAdapter.enable();

            // wait for bluetoothAdapter enabling
            while (!bluetoothAdapter.isEnabled()) ;
        }

        if (battleService == null) setupGame();

    }

    @Override
    public synchronized void onResume()
    {
        super.onResume();
        if (isDebuggingMode) Log.e(TAG, "+ ON RESUME +");

        // Performing this check in onResume() covers the case in which BT was
        // not enabled during onStart(), so we were paused to enable it...
        // onResume() will be called when ACTION_REQUEST_ENABLE activity returns.
        if (battleService != null)
        {
            // Only if the state is STATE_NONE, do we know that we haven't started already
            if (battleService.getState() == BluetoothBattleService.STATE_NONE)
            {
                // Start the Bluetooth services
                battleService.start();
            }
        }

        // if join session button pressed then show a list of device for connection
        if (getIntent().getExtras().getBoolean("IsClient"))
        {
            showDeviceList();
            getIntent().putExtra("IsHostSelected", true);
            getIntent().putExtra("IsClient", false);
        }
        // if it's not a client and not host session button pressed then make it discoverable and wait a client connection
        else if (!getIntent().getExtras().getBoolean("IsClient") && !getIntent().getExtras().getBoolean("IsHostSelected"))
        {
            ensureDiscoverable();
            waitMessage.show();
        }
    }

    private void setupGame()
    {
        Log.d(TAG, "setupGame()");

        // Initialize the BluetoothBattleService to perform bluetooth connections
        battleService = new BluetoothBattleService(this, mHandler);

        // Initialize the buffer for outgoing messages
        outStringBuffer = new StringBuffer("");
    }

    @Override
    public synchronized void onPause()
    {
        super.onPause();
        if (isDebuggingMode) Log.e(TAG, "- ON PAUSE -");
    }

    @Override
    public void onStop()
    {
        super.onStop();
        if (isDebuggingMode) Log.e(TAG, "-- ON STOP --");
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        // Stop the Bluetooth battle services
        if (battleService != null) battleService.stop();
        if (isDebuggingMode) Log.e(TAG, "--- ON DESTROY ---");
    }

    private void ensureDiscoverable()
    {
        if (isDebuggingMode) Log.d(TAG, "ensure discoverable");
        if (bluetoothAdapter.getScanMode() != BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE)
        {
            Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
            discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
            startActivityForResult(discoverableIntent, DISCOVERABLE_REQUEST);
        }
    }

    /**
     * Sends a message.
     *
     * @param message A string of text to send.
     */
    private void sendMessage(String message)
    {
        // Check that we're actually connected before trying anything
        if (battleService == null || battleService.getState() != BluetoothBattleService.STATE_CONNECTED)
        {
            Toast.makeText(this, R.string.not_connected, Toast.LENGTH_SHORT).show();
            return;
        }

        // Check that there's actually something to send
        if (message.length() > 0)
        {
            // Get the message bytes and tell the BluetoothBattleService to write
            byte[] send = message.getBytes();
            battleService.write(send);
        }
    }

    // The Handler that gets information back from the BluetoothBattleService
    private final Handler mHandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            switch (msg.what)
            {
                case MESSAGE_STATE_CHANGE:
                    if (isDebuggingMode) Log.i(TAG, "MESSAGE_STATE_CHANGE: " + msg.arg1);
                    switch (msg.arg1)
                    {
                        case BluetoothBattleService.STATE_CONNECTED:
                            // here, you can display a message for view that is connected
                            break;
                        case BluetoothBattleService.STATE_CONNECTING:
                            // here, you can display a message for view that is connected
                            Toast.makeText(getApplicationContext(), "Connecting...", Toast.LENGTH_SHORT).show();
                            break;
                        case BluetoothBattleService.STATE_LISTEN:
                        case BluetoothBattleService.STATE_NONE:
                            // here, you can display a message for view that is not connected
                            break;
                    }
                    break;
                case MESSAGE_WRITE: // go here if it is this app which send a message
                    byte[] writeBuf = (byte[]) msg.obj;
                    // construct a string from the buffer
                    String writeMessage = new String(writeBuf);

                    //////////////////////////////////////
                    // Do an Action with sended message //
                    //////////////////////////////////////

                    break;
                case MESSAGE_READ: // go here if the remote app send a message
                    byte[] readBuf = (byte[]) msg.obj;

                    // construct a string from the valid bytes in the buffer
                    String readMessage = new String(readBuf, 0, msg.arg1);

                    // interaction with received message
                    if (readMessage.equals("Finish"))
                    {
                        finish();
                    }
                    else if(isNumeric(readMessage))
                    {
                        initTabNumber(Integer.parseInt(readMessage));
                    }
                    break;
                case MESSAGE_DEVICE_NAME: // go here when a device is connected
                    // save the connected device's name
                    connectedDeviceName = msg.getData().getString(DEVICE_NAME);
                    Toast.makeText(getApplicationContext(), "Connected to " + connectedDeviceName, Toast.LENGTH_SHORT).show();

                    // dismiss dialog waiting player
                    waitMessage.dismiss();

                    // init tab with a number specified by the host user
                    if (!getIntent().getExtras().getBoolean("IsClient") && !getIntent().getExtras().getBoolean("IsHostSelected"))
                    {
                        NumberPickerDialog numberPickerDialog = new NumberPickerDialog(BluetoothBattle.this, new NumberPickerDialog.DialogListener()
                        {
                            @Override
                            public void okButtonPressed(int n)
                            {
                                Log.i("NumPicker", "Nombre select : " + n);
                                initTabNumber(n);
                                BluetoothBattle.this.sendMessage("" + n);
                            }
                        });
                        numberPickerDialog.show();
                    }
                    break;
                case MESSAGE_TOAST:
                    Toast.makeText(getApplicationContext(), msg.getData().getString(TOAST), Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    private void initTabNumber(int n)
    {
        TabJeu = new int[n];
        for (int i = 0; i < TabJeu.length; i++)
        {
            TabJeu[i] = (int) (Math.random() * 100) + 1;
        }
        updateNumbers();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (isDebuggingMode) Log.d(TAG, "onActivityResult " + resultCode);
        switch (requestCode)
        {
            case REQUEST_CONNECT_DEVICE:
                // When DeviceListActivity returns with a device to connect
                if (resultCode == Activity.RESULT_OK)
                {
                    // Get the device MAC address
                    String address = data.getExtras().getString(DeviceListActivity.EXTRA_DEVICE_ADDRESS);
                    // Get the BLuetoothDevice object
                    BluetoothDevice device = bluetoothAdapter.getRemoteDevice(address);
                    // Attempt to connect to the device
                    battleService.connect(device);
                }
                break;
            case DISCOVERABLE_REQUEST:
                if(resultCode == 0)
                {
                    finish();
                }
                break;
        }
    }

    public void showDeviceList()
    {
        // Launch the DeviceListActivity to see devices and do scan
        Intent serverIntent = new Intent(this, DeviceListActivity.class);
        startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE);
    }

    public static boolean isNumeric(String str)
    {
        try
        {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }

    // Creation boutons menu

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.scan:
                showDeviceList();
                return true;
            case R.id.discoverable:
                // Ensure this device is discoverable by others
                ensureDiscoverable();
                return true;
        }
        return false;
    }
    */
}