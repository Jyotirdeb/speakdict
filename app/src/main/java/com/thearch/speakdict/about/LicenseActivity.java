/*
 * Copyright (c) 2016 Jyotirdeb Mukherjee
 *
 * This file is part of SpeakDict.
 *
 * SpeakDict is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SpeakDict is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with SpeakDict.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.thearch.speakdict.about;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.WorkerThread;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.thearch.speakdict.Constants;
import com.thearch.speakdict.R;
import com.thearch.speakdict.databinding.ActivityLicenseBinding;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class LicenseActivity extends AppCompatActivity {

    private static final String TAG = Constants.TAG + LicenseActivity.class.getSimpleName();
    private static final String EXTRA_TITLE = "title";
    private static final String EXTRA_LICENSE_TEXT_ASSET_FILE = "license_text_asset_file";

    private ActivityLicenseBinding mBinding;

    public static void start(Context context, String title, String licenseText) {
        Intent intent = new Intent(context, LicenseActivity.class);
        intent.putExtra(EXTRA_TITLE, title);
        intent.putExtra(EXTRA_LICENSE_TEXT_ASSET_FILE, licenseText);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_license);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(R.string.license_title);
        }

        Intent intent = getIntent();
        String title = intent.getStringExtra(EXTRA_TITLE);
        String licenseFile = intent.getStringExtra(EXTRA_LICENSE_TEXT_ASSET_FILE);
        mBinding.tvTitle.setText(title);
        Single.fromCallable(() -> readFile(licenseFile))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(licenseText -> mBinding.tvLicenseText.setText(licenseText));
    }

    @WorkerThread
    private String readFile(String fileName) {
        BufferedReader reader = null;
        try {
            InputStream is = getAssets().open(fileName);
            reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder builder = new StringBuilder();
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                builder.append(line).append('\n');
            }
            return builder.toString();
        } catch (IOException e) {
            Log.e(TAG, "Couldn't read license file " + fileName + ": " + e.getMessage(), e);
            return "";
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    Log.wtf(TAG, e.getMessage(), e);
                }
            }
        }
    }
}


