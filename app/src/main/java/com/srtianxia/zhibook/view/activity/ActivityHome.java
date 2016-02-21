package com.srtianxia.zhibook.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;
import com.facebook.drawee.view.SimpleDraweeView;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;
import com.qiniu.android.utils.UrlSafeBase64;
import com.srtianxia.zhibook.R;
import com.srtianxia.zhibook.app.BaseActivity;
import com.srtianxia.zhibook.view.fragment.FragmentQuestion;

import org.hybridsquad.android.library.CropHandler;
import org.hybridsquad.android.library.CropHelper;
import org.hybridsquad.android.library.CropParams;
import org.json.JSONObject;

import java.io.File;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ActivityHome extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener,CropHandler  {
    private static final String TAG = "ActivityHome";
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.nav_view)
    NavigationView navView;


    private FragmentManager manager;
    private FragmentTransaction transaction;

    private CropParams cropParams = new CropParams();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        toolbar.setTitle(R.string.toolbar_home);
        setSupportActionBar(toolbar);

        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        FragmentQuestion fragmentQuestion = new FragmentQuestion();
        transaction.replace(R.id.fragment_container, fragmentQuestion);
        transaction.commit();


        SimpleDraweeView draweeView = (SimpleDraweeView) navView.getHeaderView(0).findViewById(R.id.img_person_head);
        draweeView.setImageURI(Uri.parse("http://www.91danji.com/attachments/201509/27/13/4cevsjye7.jpg"));
        draweeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item[] = new String[]{"相册", "拍照"};
                new MaterialDialog.Builder(ActivityHome.this)
                        .theme(Theme.LIGHT)
                        .title("选择")
                        .items(item)
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                                if (which == 0){
                                    Intent intent = CropHelper.buildCropFromGalleryIntent(new CropParams());
                                    CropHelper.clearCachedCropFile(cropParams.uri);
                                    startActivityForResult(intent, CropHelper.REQUEST_CROP);
                                }else {

                                }
                            }
                        }).show();
            }
        });



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        CropHelper.handleResult(this, requestCode, resultCode, data);
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

        if (id == R.id.nav_home) {

        } else if (id == R.id.nav_find) {
            Intent intent = new Intent(this,ActivityFind.class);
            startActivity(intent);
        }  else if (id == R.id.nav_note){
            Intent intent = new Intent(this,ActivityNote.class);
            startActivity(intent);
        }else if (id == R.id.nav_chat){
            Intent intent = new Intent(this,ActivityChat.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }

    @Override
    public void onPhotoCropped(Uri uri) {
        try {
            // 1 构造上传策略
            JSONObject _json = new JSONObject();
            long _dataline = System.currentTimeMillis() / 1000 + 3600;
            _json.put("deadline", _dataline);// 有效时间为一个小时
            _json.put("scope", "kymobile");
            String _encodedPutPolicy = UrlSafeBase64.encodeToString(_json
                    .toString().getBytes());
            byte[] _sign = HmacSHA1Encrypt(_encodedPutPolicy, "Gn4RGayGkbnePw0vT4VU1LDpZlVGGnSdmG9CKSuA");
            String _encodedSign = UrlSafeBase64.encodeToString(_sign);
            String _uploadToken = "5rP0uETgTI0QGWYS0WJ2yNySaxO9q2tQMLj4F-HT" + ':' + _encodedSign + ':'
                    + _encodedPutPolicy;
            String SAVE_FILE_DIRECTORY = Environment.getExternalStorageDirectory() + File.separator + "crop_cache_file.jpg";
            UploadManager uploadManager = new UploadManager();
            uploadManager.put(SAVE_FILE_DIRECTORY, null, _uploadToken,
                    new UpCompletionHandler() {
                        @Override
                        public void complete(String key, ResponseInfo info,
                                             JSONObject response) {
                            Log.e("qiniu", info.toString());
                        }
                    }, null);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCropCancel() {

    }

    @Override
    public void onCropFailed(String message) {
        Log.d("123",message);
    }

    @Override
    public CropParams getCropParams() {
        return cropParams;
    }

    @Override
    public Activity getContext() {
        return getContext();
    }

    private static final String MAC_NAME = "HmacSHA1";
    private static final String ENCODING = "UTF-8";

    /**
     *
     * 这个签名方法找了半天 一个个对出来的、、、、程序猿辛苦啊、、、 使用 HMAC-SHA1 签名方法对对encryptText进行签名
     *
     * @param encryptText
     *            被签名的字符串
     * @param encryptKey
     *            密钥
     * @return
     * @throws Exception
     */
    public static byte[] HmacSHA1Encrypt(String encryptText, String encryptKey)
            throws Exception {
        byte[] data = encryptKey.getBytes(ENCODING);
        // 根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称
        SecretKey secretKey = new SecretKeySpec(data, MAC_NAME);
        // 生成一个指定 Mac 算法 的 Mac 对象
        Mac mac = Mac.getInstance(MAC_NAME);
        // 用给定密钥初始化 Mac 对象
        mac.init(secretKey);
        byte[] text = encryptText.getBytes(ENCODING);
        // 完成 Mac 操作
        return mac.doFinal(text);
    }
}
