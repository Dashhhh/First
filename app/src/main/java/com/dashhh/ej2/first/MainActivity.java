package com.dashhh.ej2.first;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import static android.content.Intent.ACTION_VIEW;

public class MainActivity extends AppCompatActivity {

    TextView checkUserID;
    private static final String DEFAULT_PATH = "testapp://repos/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

    }

    private void init() {

        checkUserID = findViewById(R.id.checkUserID);

        boolean appIsSet = getPackageList();
        if(appIsSet){
            String url = "testapp://repos/Dashhhh";
            checkUserID.setText(url);

            checkUserID.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = getPackageManager().getLaunchIntentForPackage("com.dashhh.ej2.testtargetapp");
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);

                }
            });


        } else {
            checkUserID.setText("SETUP PLZ");

        }

    }
    public boolean getPackageList() {
        boolean isExist = false;

        PackageManager pkgMgr = getPackageManager();
        List<ResolveInfo> mApps;
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        mApps = pkgMgr.queryIntentActivities(mainIntent, 0);

        try {
            for (int i = 0; i < mApps.size(); i++) {
                if(mApps.get(i).activityInfo.packageName.startsWith("com.dashhh.ej2.testtargetapp")){
                    isExist = true;
                    break;
                }
            }
        }
        catch (Exception e) {
            isExist = false;
        }
        return isExist;
    }

}
