package com.example.caoxinghua.myapplication.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.caoxinghua.myapplication.R;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Set;

public class TestBlueToothActivity extends AppCompatActivity implements View.OnClickListener{
    private BluetoothAdapter bluetoothAdapter;
    private BroadcastReceiver mBluetoothReceiver;
    private List<BluetoothDevice>  list=new ArrayList<BluetoothDevice>();
    private ListView listView;
    private MyAdapter adapter;
    private Button openBt;
    private Button discoveryBt;
    private Button cancelBt;
    private Button sendBt;
    private EditText sendEt;
    private TextView receiveTv;
    private TextView stateTv;

    private Handler handler;

    private BluetoothChatService chatService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_blue_tooth);

        initView();

        mBluetoothReceiver=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action=intent.getAction();
                Log.i("xxx","action:"+action);
                if(BluetoothDevice.ACTION_FOUND.equals(action)){
                    BluetoothDevice device=intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    if(device==null)return;
                    list.add(device);
                    Log.i("xxx","found:"+device.getName() + "\n" +device.getAddress()+"\n"+list.size());

                    adapter.notifyDataSetChanged();

                }else if(BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)){
                    adapter.notifyDataSetChanged();
                }else {

                }
            }
        };
        registerBroadCast();

//        WifiManager wifiManager= (WifiManager) getSystemService(WIFI_SERVICE);//7.0后获取不到
//        WifiInfo info=wifiManager.getConnectionInfo();
//        Log.i("xxx","mac1:"+getNewMac());
//        Log.i("xxx","mac2:"+getLocalMacAddressFromIp());

         handler=new Handler(){
           public void handleMessage(Message msg){
               switch (msg.what){
                   case Constants.MESSAGE_READ:
                       receiveTv.setText(new String((byte[]) msg.obj));
                       break;
                   case Constants.MESSAGE_WRITE:
                       break;
                   case Constants.MESSAGE_STATE_CHANGE:
                       stateTv.setText(msg.arg1+"");
                       break;
                   case Constants.MESSAGE_TOAST:
                       Bundle bundle=msg.getData();
                       Toast.makeText(TestBlueToothActivity.this,bundle.getString(Constants.TOAST),Toast.LENGTH_SHORT).show();
                       break;

               }
           }
         };

         if(bluetoothAdapter==null)return;
             if(!bluetoothAdapter.isEnabled()){
                 Intent intent=new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                 startActivityForResult(intent,666);
             }else {
                 chatService=new BluetoothChatService(this,handler);
                 chatService.start();
             }



    }

    private void initView(){
        openBt= (Button) findViewById(R.id.openBt);
        discoveryBt=(Button) findViewById(R.id.discoveryBt);
        cancelBt= (Button) findViewById(R.id.cancelBt);
        sendBt= (Button) findViewById(R.id.sendBt);
        sendEt= (EditText) findViewById(R.id.sendEt);
        receiveTv= (TextView) findViewById(R.id.receiveTv);
        stateTv= (TextView) findViewById(R.id.connectTv);
        listView= (ListView) findViewById(R.id.lv);
        adapter=new MyAdapter(this,list);
        listView.setAdapter(adapter);
        openBt.setOnClickListener(this);
        discoveryBt.setOnClickListener(this);
        cancelBt.setOnClickListener(this);
        sendBt.setOnClickListener(this);
        bluetoothAdapter=BluetoothAdapter.getDefaultAdapter();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(chatService!=null)chatService.connect(list.get(position),true);
            }
        });
    }
    private void registerBroadCast(){
        IntentFilter filter = new IntentFilter();
//发现设备
        filter.addAction(BluetoothDevice.ACTION_FOUND);
//设备连接状态改变
        filter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
//蓝牙设备状态改变
        filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        registerReceiver(mBluetoothReceiver, filter);
    }
    private void discoveryBlueTooth(){
        if(bluetoothAdapter!=null){
            bluetoothAdapter.startDiscovery();
        }
    }
    private void cancelDiscovery(){
        if(bluetoothAdapter!=null){
            bluetoothAdapter.cancelDiscovery();
        }
    }

    private void bondDevice(){
        Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();//已配对设备

// If there are paired devices
        if (pairedDevices.size() > 0) {
            // Loop through paired devices
            for (BluetoothDevice device : pairedDevices) {
                // Add the name and address to an array adapter to show in a ListView
                Log.i("xxx",device.getName() + "\n" + device.getAddress());
            }
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
        }
    }


    /**
     * 通过网络接口取
     * wifi连接可以获取到，部分手机wifi关闭时也能获取到
     * @return
     */
    private  String getNewMac() {
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                Log.i("xxx","nif:"+nif.getName());
                if (!nif.getName().equalsIgnoreCase("wlan0")) continue;

                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return null;
                }

                StringBuilder res1 = new StringBuilder();
                for (byte b : macBytes) {
                    res1.append(String.format("%02X:", b));
                }

                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }
                return res1.toString();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 根据IP地址获取MAC地址
     *  wifi连接时可以获取到
     * @return
     */
    private  String getLocalMacAddressFromIp() {
        String strMacAddr = null;
        try {
            //获得IpD地址
            InetAddress ip = getLocalInetAddress();
            byte[] b = NetworkInterface.getByInetAddress(ip).getHardwareAddress();
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < b.length; i++) {
                if (i != 0) {
                    buffer.append(':');
                }
                String str = Integer.toHexString(b[i] & 0xFF);
                buffer.append(str.length() == 1 ? 0 + str : str);
            }
            strMacAddr = buffer.toString().toUpperCase();
        } catch (Exception e) {

        }

        return strMacAddr;
    }

    /**
     * 获取移动设备本地IP
     *
     * @return
     */
    private  InetAddress getLocalInetAddress() {
        InetAddress ip = null;
        try {
            //列举
            Enumeration<NetworkInterface> en_netInterface = NetworkInterface.getNetworkInterfaces();
            while (en_netInterface.hasMoreElements()) {//是否还有元素
                NetworkInterface ni = (NetworkInterface) en_netInterface.nextElement();//得到下一个元素
                Enumeration<InetAddress> en_ip = ni.getInetAddresses();//得到一个ip地址的列举
                while (en_ip.hasMoreElements()) {
                    ip = en_ip.nextElement();
                    if (!ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1)
                        break;
                    else
                        ip = null;
                }

                if (ip != null) {
                    break;
                }
            }
        } catch (SocketException e) {

            e.printStackTrace();
        }
        return ip;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.openBt:
                if(bluetoothAdapter==null)return;
                if(!bluetoothAdapter.isEnabled()){
                    Intent intent=new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(intent,666);
                }
                break;
            case R.id.discoveryBt:
                discoveryBlueTooth();
                break;
            case R.id.cancelBt:
                if(chatService!=null) chatService.stop();
                break;
            case R.id.sendBt:
                String s=sendEt.getText().toString();
                if(chatService!=null) chatService.write(s.getBytes());
                break;
        }
    }

    class  MyAdapter extends BaseAdapter{
        private List<BluetoothDevice> list;
        private Context context;
        public MyAdapter(Context context,List<BluetoothDevice> list){
            this.context=context;
            this.list=list;
        }
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder= null;
            if(convertView==null){
                viewHolder=new ViewHolder();
                LayoutInflater inflater=LayoutInflater.from(context);
                convertView=inflater.inflate(R.layout.layout_bluetooth_item,null);
                viewHolder.nameTv= (TextView) convertView.findViewById(R.id.nameTv);
                viewHolder.macTv= (TextView) convertView.findViewById(R.id.macTv);
                convertView.setTag(viewHolder);
            }else {
                viewHolder= (ViewHolder) convertView.getTag();
            }
            BluetoothDevice bluetoothDevice=list.get(position);
            viewHolder.nameTv.setText(bluetoothDevice.getName()==null?"no name":bluetoothDevice.getName());
            viewHolder.macTv.setText(bluetoothDevice.getAddress());
            return convertView;
        }
    }
    class ViewHolder{
        private TextView nameTv;
        private TextView macTv;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mBluetoothReceiver);
    }

}

