package com.imengququ.myrealphone;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.imengququ.myrealphone.Utis.Mnt;
import com.imengququ.myrealphone.Utis.SharedPref;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {


    LinearLayout linearLayout;
    Button btnclose,btnadvice,btnsave,btnrand,btnreturn;
    EditText ETandroidid ,ETbrand ,EThardware ,ETmanufacturer, ETmodel,ETsystemversion,
            ETphone , ETsimserial,ETsubscriberid,ETsimstate,EToperid,ETsdk,ETsdkint,
            ETsecuritypatch,ETpreviewsdkint,ETincremental,ETcodename,ETbaseos,
            ETopername, ETisocode, ETradio, ETwifibssid,ETwifimac, ETwifissid,Etuser,
            ETboard,ETbootoader,ETdevice,ETdisplay,ETfingerprint,EThost,ETid,ETserial,ETradioversion,
            ETcpu1,ETcpu2,ETtype,ETtags,ETproduct,ETtime,ETsimcountryiso,ETnetworkoperatorname,ETnetworktype;
    TelephonyManager localTelephonyManager;
    WifiManager localwifimanager;
    WifiInfo localWifiInfo;
    Context mcontext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mcontext=this;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        initview();
        Save();
        CPU();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private  void initview(){
        btnadvice= (Button) findViewById(R.id.btn_advice);
        btnadvice.setOnClickListener(this);
        btnclose= (Button) findViewById(R.id.btn_close);
        btnclose.setOnClickListener(this);
        btnrand= (Button) findViewById(R.id.btn_rand);
        btnrand.setOnClickListener(this);
        btnsave= (Button) findViewById(R.id.btn_save);
        btnsave.setOnClickListener(this);
        btnreturn= (Button) findViewById(R.id.btn_return);
        btnreturn.setOnClickListener(this);
       linearLayout= (LinearLayout) findViewById(R.id.container);
        getwifiinfo();
        getTelephoneinfo();
        getBuildValue();
     addAdId();
     addTelePhoneinfo();
  addBuildinfo();
      addwifiinfo();


    }
    private void addLabel(TableRow paramTableRow, String paramString)
    {
        TextView localTextView = new TextView(this);
        localTextView.setText(paramString);
        Log.d("realtool", "addLabel: "+paramString);
        localTextView.setLayoutParams(new TableRow.LayoutParams(350,-2));
        paramTableRow.addView(localTextView);
    }


    //添加Androidid
    private void addAdId()
    {

        TableRow localTableRow = new TableRow(this);
        addLabel(localTableRow, "安卓ID(android_id)");
        String str = Settings.Secure.getString(getContentResolver(), "android_id");
        ETandroidid = new EditText(this);
        ETandroidid.setId(R.id.android_id);
        ETandroidid.setLayoutParams(new TableRow.LayoutParams(-2, -2));
        localTableRow.addView(ETandroidid);
        linearLayout.addView(localTableRow);
           if (str!=null&&str.length()>0){
            ETandroidid.setText(str);
        }
    }

//添加厂商
    String user,//用户
        brand,
    hardware,
    board,bootloader,device,display,fingerprint,manufacturer,
    host,id,serial,radio,radioversion,cpu1,cpu2,
    type,tags,product,model,release,sdk,baseos,codename,incremental,securitypatch;
      Long time;
    int sdkint,previewsdkint;
   private  void getBuildValue(){
       try {
           user=Build.USER;
           brand=Build.BRAND;
           hardware=Build.HARDWARE;
           board=Build.BOARD;
           bootloader =Build.BOOTLOADER;
           device=Build.DEVICE;
           display =Build.DISPLAY;
           fingerprint=Build.FINGERPRINT;
           manufacturer =Build.MANUFACTURER;
           host =Build.HOST;
           id=Build.ID;
           serial =Build.SERIAL;
           radio =Build.RADIO;
           radioversion =Build.getRadioVersion();
           cpu1 =Build.CPU_ABI;
           cpu2=Build.CPU_ABI2;
           type =Build.TYPE;
           tags=Build.TAGS;
           time =Build.TIME;
           product =Build.PRODUCT;
           radioversion =Build.getRadioVersion();

           release =Build.VERSION.RELEASE;
           if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
               serial=Build.getSerial();
           }
           sdk =Build.VERSION.SDK;
           sdkint=Build.VERSION.SDK_INT;
           baseos =Build.VERSION.BASE_OS;
           codename =Build.VERSION.CODENAME;
           incremental=Build.VERSION.INCREMENTAL;
           securitypatch=Build.VERSION.SECURITY_PATCH;
           Log.d("Build", "getBuildValue: "+user+fingerprint+id+product);
//           previewsdkint=Build.VERSION.PREVIEW_SDK_INT;

       }catch (Exception E){

       }finally {

       }




    }

    public void addBuildinfo(String name,EditText editText,int id){

        Log.d("realltool", "addBuildinfo: "+name);
        TableRow localTableRow = new TableRow(this);
        addLabel(localTableRow,name);
        editText = new EditText(this);
        editText.setId(id);
        editText.setLayoutParams(new TableRow.LayoutParams(-2, -2));
        localTableRow.addView(editText);
        linearLayout.addView(localTableRow);
    }

        public void addBuildinfo()
    {

            addBuildinfo("用户",Etuser,R.id.user);

            addBuildinfo("品牌",ETbrand,R.id.brand);
            addBuildinfo("硬件",EThardware,R.id.hardware);
            addBuildinfo("主板",ETboard,R.id.board);
            addBuildinfo("系统启动程序版本号",ETbootoader,R.id.bootloader);
            addBuildinfo("设备参数",ETdevice,R.id.device);
            addBuildinfo("显示屏参数",ETdisplay,R.id.display);
            addBuildinfo("唯一识别码",ETfingerprint,R.id.fingerprint);
            addBuildinfo("硬件制造商",ETmanufacturer,R.id.manufacturer);
            addBuildinfo("HOST",EThost,R.id.host);
            addBuildinfo("修订版本列表",ETid,R.id.id);
            addBuildinfo("硬件序列号",ETserial,R.id.serial);
            addBuildinfo("无线电固件版本",ETradio,R.id.sradio);
            addBuildinfo("无线电固件版本",ETradioversion,R.id.radioversion);
                 addBuildinfo("CPU指令集",ETcpu1,R.id.cpu1);
                  addBuildinfo("CPU指令集2",ETcpu2,R.id.cpu2);

            addBuildinfo("Build 的类型",ETtype,R.id.type);
            addBuildinfo("标签",ETtags,R.id.tags);
            addBuildinfo("产品名称",ETproduct,R.id.product);
            addBuildinfo("机型",ETmodel,R.id.model);
            addBuildinfo("TIME",ETtime,R.id.time);
            addBuildinfo("系统版本",ETsystemversion,R.id.systemversion);
            addBuildinfo("系统版本值",ETsdkint,R.id.sdkint);
            addBuildinfo("Build.VERSION.SDK",ETsdk,R.id.sdk);
            addBuildinfo("Build.VERSION.BASE_OS",ETbaseos,R.id.baseos);
            addBuildinfo("Build.VERSION.CODENAME",ETcodename,R.id.codename);
            addBuildinfo("Build.VERSION.INCREMENTAL",ETincremental,R.id.incremental);
            addBuildinfo("Build.VERSION.SECURITY_PATCH",ETsecuritypatch,R.id.securitypatch);
            addBuildinfo("Build.VERSION.PREVIEW_SDK_INT",ETpreviewsdkint,R.id.previewsdkint);
          }

    public void addTelePhoneinfo(String strname, EditText editText, int id, String Value){

        TableRow localTableRow = new TableRow(this);
        addLabel(localTableRow, strname);
       editText = new EditText(this);
       editText.setId(id);
       editText.setLayoutParams(new TableRow.LayoutParams(-2, -2));
        localTableRow.addView(editText);

        linearLayout.addView(localTableRow);
        if(Value!=null&&Value.length()>1)

        {
            editText.setText(Value);
        }
    }



    String phonenumber,  simoperator,simserialnumber,subscriberid,simoperid,
            simopername,isocode,devicesoftwareversion,imei,meid,mmsuaprofurl,
            mmsuseragent,networkcontryiso,networkoperator,networkoperatorname,
            simcountryiso,deviceid;
    int phonecount,networktype,callstate,simstate;




 private   void getTelephoneinfo(){
    localTelephonyManager = (TelephonyManager)getSystemService(TELEPHONY_SERVICE);
     try {
         List allCellInfo=localTelephonyManager.getAllCellInfo();
         simserialnumber=localTelephonyManager.getSimSerialNumber();                      //  //sim 卡的序列号
         devicesoftwareversion=localTelephonyManager.getDeviceSoftwareVersion();
         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
             imei=localTelephonyManager.getImei();
             meid=localTelephonyManager.getMeid();
             phonecount=localTelephonyManager.getPhoneCount();
         }
         phonenumber=localTelephonyManager.getLine1Number();
         mmsuaprofurl=localTelephonyManager.getMmsUAProfUrl();
         mmsuseragent=localTelephonyManager.getMmsUserAgent();
         networkcontryiso=localTelephonyManager.getNetworkCountryIso();                    //注册的网络运营商的国家代码
         networkoperator=localTelephonyManager.getNetworkOperator();
         networkoperatorname=localTelephonyManager.getNetworkOperatorName();            //网络运营商名字
         simcountryiso=localTelephonyManager.getSimCountryIso();                        //sim卡国家iso
         subscriberid=localTelephonyManager.getSubscriberId();                          // imsi 46000 46001 46003
         deviceid=localTelephonyManager.getDeviceId();
         simstate=localTelephonyManager.getSimState();                                  //sim状态
         simoperator=localTelephonyManager.getSimOperator();                            //手机运行商
         simopername=localTelephonyManager.getSimOperatorName();
         callstate=localTelephonyManager.getCallState();
         networktype=localTelephonyManager.getNetworkType();        //网络类型
     }catch (Exception E){

     }

   }

    //添加手机卡的信息
    public void addTelePhoneinfo()
    {
       addTelePhoneinfo("手机号码",ETphone,R.id.phoneNumber,phonenumber);
        addTelePhoneinfo("手机卡状态",ETsimstate,R.id.simState, String.valueOf(simstate));
        addTelePhoneinfo("手机运营商",EToperid,R.id.simoperid,simoperator);
        addTelePhoneinfo("运营商名称",ETopername,R.id.simopername,simopername);
        addTelePhoneinfo("手机卡国家",ETsimcountryiso,R.id.simcountryiso,simcountryiso);
        addTelePhoneinfo("网络类型名",ETnetworkoperatorname,R.id.networkoperatorname,networkoperatorname);
        addTelePhoneinfo("网络类型",ETnetworktype,R.id.networktype, String.valueOf(networktype));

    }



        String wifibssid,wifissid,wifimac,wifiname;
        private  void getwifiinfo(){
          localwifimanager= (android.net.wifi.WifiManager) mcontext.getSystemService(WIFI_SERVICE);

            localWifiInfo = localwifimanager.getConnectionInfo();
                try {
                  wifibssid=localWifiInfo.getBSSID();
                    wifimac=localWifiInfo.getMacAddress();
                  wifissid=localWifiInfo.getSSID();

                    }catch (Exception E){

}


}
    //
    public void addwifiinfo(String name,EditText editText,int id,String  value){

        TableRow localTableRow = new TableRow(this);
        addLabel(localTableRow, name);
        editText = new EditText(this);
        Log.d("realtool", "addwifiinfo: "+name);
        editText.setId(id);
//       
        editText.setLayoutParams(new TableRow.LayoutParams(-2, -2));
        localTableRow.addView(editText);

        linearLayout.addView(localTableRow);
        if(value!=null&&value.length()>1){
            editText.setText(value);
        }
    }
    public void addwifiinfo(){
        addwifiinfo("Wifi B-SSID",ETwifibssid,R.id.wifibssid,wifibssid);
        addwifiinfo("wifi mac地址",ETwifimac,R.id.wifimac,wifimac);
        addwifiinfo("wifi 名称",ETwifissid,R.id.wifissid,wifissid);
    }

    public void addgpuinfo(String name ,EditText editText,int id ,String value){
        TableRow localTableRow = new TableRow(this);
        addLabel(localTableRow, name);
        editText = new EditText(this);
        Log.d("realtool", "addgpuinfo: "+name);
        editText.setId(id);
//
        editText.setLayoutParams(new TableRow.LayoutParams(-2, -2));
        localTableRow.addView(editText);

        linearLayout.addView(localTableRow);
        if(value!=null&&value.length()>1){
            editText.setText(value);
        }
    }
    public void addgpuinfo(){

    }





    private  void Save(){
        SharedPref mySP = new SharedPref(getApplicationContext());
    /*
      build 系列
     */


        mySP.setSharedPref("serial","aee5060e"); // 串口序列号
        mySP.setSharedPref("getBaseband","SCL23KDU1BNG3"); // get 参数
        mySP.setSharedPref("BaseBand", "REL" ); // 固件版本
        mySP.setSharedPref("board", "msm8916" ); //主板
        mySP.setSharedPref("brand", "Huawei" ); //设备品牌
        mySP.setSharedPref("ABI", "armeabi-v7a" ); //  设备指令集名称 1
        mySP.setSharedPref("ABI2", "armeabi" ); //   设备指令集名称 2
        mySP.setSharedPref("device", "hwG750-T01" ); //设备驱动名称
        mySP.setSharedPref("display", "R7c_11_151207" ); //设备显示的版本包 固件版本
        //  指纹 设备的唯一标识。由设备的多个信息拼接合成。
        mySP.setSharedPref("fingerprint", "Huawei/G750-T01/hwG750-T01:4.2.2/HuaweiG750-T01/C00B152:user/ota-rel-keys,release-keys" );
        mySP.setSharedPref("NAME", "mt6592" ); //设备硬件名称
        mySP.setSharedPref("ID", "KTU84P" ); //设备版本号
        mySP.setSharedPref("Manufacture", "HUAWEI" ); //设备制造商
        mySP.setSharedPref("model", "HUAWEI G750-T01" ); //手机的型号 设备名称
        mySP.setSharedPref("product", "hwG750-T01" ); //设备驱动名称
        mySP.setSharedPref("booltloader", "unknown" ); //设备引导程序版本号
        mySP.setSharedPref("host", "ubuntu-121-114" ); //设备主机地址
        mySP.setSharedPref("build_tags", "release-keys" ); //设备标签
        mySP.setSharedPref("shenbei_type", "user" ); //设备版本类型
        mySP.setSharedPref("incrementalincremental", "eng.root.20151207" ); //源码控制版本号
        mySP.setSharedPref("AndroidVer", "5.1" ); //系统版本
        mySP.setSharedPref("API", "19" ); //系统的API级别 SDK

        mySP.setintSharedPref("time",123456789);// 固件时间
        mySP.setSharedPref("AndroidID", "fc4ad25f66d554a8" ); //  android id
        mySP.setSharedPref("DESCRIPTION", "jfltexx-user 4.3 JSS15J I9505XXUEML1 release-keys" ); //用户的KEY





 /*
     TelephonyManager相关
     */
        mySP.setSharedPref("IMEI","506066104722640"); // 序列号IMEI
        mySP.setSharedPref("LYMAC","BC:1A:EA:D9:8D:98");//蓝牙 MAC
        mySP.setSharedPref("WifiMAC","a8:a6:68:a3:d9:ef"); // WIF mac地址
        mySP.setSharedPref("WifiName","免费WIFI"); // 无线路由器名
        mySP.setSharedPref("BSSID", "ce:ea:8c:1a:5c:b2"); // 无线路由器地址
        mySP.setSharedPref("IMSI","460017932859596");
        mySP.setSharedPref("PhoneNumber","13117511178"); // 手机号码
        mySP.setSharedPref("SimSerial", "89860179328595969501"); // 手机卡序列号
        mySP.setSharedPref("networktor","46001" ); // 网络运营商类型
        mySP.setSharedPref("Carrier","中国联通" );// 网络类型名
        mySP.setSharedPref("CarrierCode","46001" ); // 运营商
        mySP.setSharedPref("simopename","中国联通" );// 运营商名字
        mySP.setSharedPref("gjISO", "cn");// 国家iso代码
        mySP.setSharedPref("CountryCode","cn" );// 手机卡国家
        mySP.setSharedPref("deviceversion", "100"); // 返回系统版本

        mySP.setintSharedPref("getType",1); // 联网方式 1为WIFI 2为流量
        mySP.setintSharedPref("networkType", 6);//      网络类型
        mySP.setintSharedPref("phonetype",5 ); // 手机类型
        mySP.setintSharedPref("SimState", 10); // 手机卡状态
        mySP.setintSharedPref("width", 720); // 宽
        mySP.setintSharedPref("height", 1280); // 高
        mySP.setintSharedPref("getIP", -123456789); // 内网ip(wifl可用)
    /*
     屏幕相关
     */

        mySP.setintSharedPref("DPI",320); // dpi
        mySP.setfloatharedPref("density", (float) 2.0); // density
        mySP.setfloatharedPref("xdpi", (float) 200.123);
        mySP.setfloatharedPref("ydpi", (float) 211.123);
        mySP.setfloatharedPref("scaledDensity", (float) 2.0); // 字体缩放比例



 /*
    显卡信息
     */

        mySP.setSharedPref("GLRenderer", "Adreno (TM) 111"); // GPU
        mySP.setSharedPref("GLVendor", "UFU");// GPU厂商


            /*
            位置信息

        30.2425140000,120.1404220000 杭州
     */

        mySP.setfloatharedPref("lat", (float) 30.2425140000); // 纬度
        mySP.setfloatharedPref("log", (float) 120.1404220000); // 经度


        Toast.makeText(this,"保存成功",Toast.LENGTH_LONG).show();


    }

    /*
  创建 cpuinfo 文件 等待HOOK 重定向
 */

    private  void CPU() {

        String filePath = "/sdcard/Test/";
        String fileName = "cpuinfo";

        String hardware="GT1000";

        //生成文件夹之后，再生成文件，不然会出错
        Mnt.makeFilePath(filePath, fileName);

        String strFilePath = filePath+fileName;
        // 每次写入时，都换行写
        String strContent = "Processor	: ARMv7 Processor rev 0 (v7l)" + "\r\n";
        String strContent2 = "processor	: 0" + "\r\n";
        String strContent3 = "BogoMIPS	: 38.40";
        String strContent4 = "" + "\r\n";
        String strContent5 = "" + "\r\n";
        String strContent6 = "processor	: 1"+ "\r\n";
        String strContent7 = "BogoMIPS	: 38.40"+ "\r\n";
        String strContent8 = ""+ "\r\n";
        String strContent9 = "Features	: swp half thumb fastmult vfp edsp neon vfpv3 tls vfpv4 idiva idivt"+ "\r\n";
        String strContent10 = "CPU implementer	: 0x51"+ "\r\n";
        String strContent11 = "CPU architecture: 7"+ "\r\n";
        String strContent12 = "CPU variant	: 0x2"+ "\r\n";
        String strContent13 = "CPU part	: 0x06f"+ "\r\n";
        String strContent14 = "CPU revision	: 0"+ "\r\n";
        String strContent15 = ""+ "\r\n";
        String strContent16 = "Hardware	: "+hardware+ "\r\n";
        String strContent17 = "Revision	: 000d"+ "\r\n";
        String strContent18 = "Serial		: 0000088900004e4b"+ "\r\n";
        try {
            File file = new File(strFilePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }


            //要先将已有文件删除、避免干扰。
            if(file.exists()){
                file.delete();
            }

            RandomAccessFile raf = new RandomAccessFile(file, "rwd");
            raf.seek(file.length());
            raf.write(strContent.getBytes());
            raf.write(strContent2.getBytes());
            raf.write(strContent3.getBytes());
            raf.write(strContent4.getBytes());
            raf.write(strContent5.getBytes());
            raf.write(strContent6.getBytes());
            raf.write(strContent7.getBytes());
            raf.write(strContent8.getBytes());
            raf.write(strContent9.getBytes());
            raf.write(strContent10.getBytes());
            raf.write(strContent11.getBytes());
            raf.write(strContent12.getBytes());
            raf.write(strContent13.getBytes());
            raf.write(strContent14.getBytes());
            raf.write(strContent15.getBytes());
            raf.write(strContent16.getBytes());
            raf.write(strContent17.getBytes());
            raf.write(strContent18.getBytes());
            raf.close();
        } catch (Exception e) {
            Log.e("TestFile", "Error on write File:" + e);
        }



    }
    @Override
    public void onClick(View view) {
        int id=view.getId();
        switch (id){
            case R.id.btn_close:

              break;
            case R.id.btn_advice:

                break;
            case R.id.btn_save:

                break;
            case R.id.btn_rand:

                break;
            case R.id.btn_return:

                break;



        }
    }
}
