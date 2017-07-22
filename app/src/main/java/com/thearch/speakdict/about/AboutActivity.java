/*
 * Copyright (c) 2016-2017 Jyotirdeb Mukherjee
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

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.thearch.speakdict.R;
import com.thearch.speakdict.compat.VectorCompat;
import com.thearch.speakdict.databinding.ActivityAboutBinding;


public class AboutActivity extends AppCompatActivity {

    private ActivityAboutBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(getString(R.string.about_title, getString(R.string.app_name)));
        }
        String versionName;
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            versionName = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            // Should never happen
            throw new AssertionError(e);
        }
        String appVersionText = getString(R.string.about_app_version, getString(R.string.app_name), versionName);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_about);
        mBinding.txtVersion.setText(appVersionText);
        hackSetIcons();
    }

    @SuppressWarnings("unused")
    public void onClickAppLicense(@SuppressWarnings("UnusedParameters") View view) {
        LicenseActivity.start(this, getString(R.string.app_name), "LICENSE.txt");
    }

    @SuppressWarnings("unused")
    public void onClickRhymerLicense(@SuppressWarnings("UnusedParameters") View view) {
        LicenseActivity.start(this, getString(R.string.about_license_rhyming_dictionary), "LICENSE-rhyming-dictionary.txt");
    }

    @SuppressWarnings("unused")
    public void onClickThesaurusLicense(@SuppressWarnings("UnusedParameters") View view) {
        LicenseActivity.start(this, getString(R.string.about_license_thesaurus), "LICENSE-thesaurus-wordnet.txt");
    }

    @SuppressWarnings("unused")
    public void onClickDictionaryLicense(@SuppressWarnings("UnusedParameters") View view) {
        LicenseActivity.start(this, getString(R.string.about_license_dictionary), "LICENSE-dictionary-wordnet.txt");
    }

    @SuppressWarnings("unused")
    public void onClickGoogleNgramDatasetLicense(@SuppressWarnings("UnusedParameters") View view) {
        LicenseActivity.start(this, getString(R.string.about_license_google_ngram_dataset), "LICENSE-google-ngram-dataset.txt");
    }

    /**
     * The support library 23.3.0 dropped support for using vector drawables in the
     * "drawableLeft" attribute of TextViews.  We set them programmatically here.
     */
    private void hackSetIcons() {
        hackSetIcon(mBinding.tvSourceCode, R.drawable.ic_source_code);
        hackSetIcon(mBinding.tvBugReport, R.drawable.ic_bug_report_24dp);
        hackSetIcon(mBinding.tvRate, R.drawable.ic_rate);
        hackSetIcon(mBinding.tvLegal, R.drawable.ic_legal);
        hackSetIcon(mBinding.tvPrivacyPolicy, R.drawable.ic_bullet);
        hackSetIcon(mBinding.tvPoetAssistantLicense, R.drawable.ic_bullet);
        hackSetIcon(mBinding.tvRhymerLicense, R.drawable.ic_bullet);
        hackSetIcon(mBinding.tvThesaurusLicense, R.drawable.ic_bullet);
        hackSetIcon(mBinding.tvDictionaryLicense, R.drawable.ic_bullet);
        hackSetIcon(mBinding.tvGoogleNgramDatasetLicense, R.drawable.ic_bullet);
        hackSetIcon(mBinding.tvEventBusLicense, R.drawable.ic_bullet);
        hackSetIcon(mBinding.tvRetrolambdaLicense, R.drawable.ic_bullet);
        hackSetIcon(mBinding.tvDaggerLicense, R.drawable.ic_bullet);
        hackSetIcon(mBinding.tvRxJavaLicense, R.drawable.ic_bullet);
        hackSetIcon(mBinding.tvStemmerLicense, R.drawable.ic_bullet);
    }

    private void hackSetIcon(TextView textView, @DrawableRes int iconRes) {
        VectorCompat.setCompoundVectorDrawables(this, textView, iconRes, 0, 0, 0);
    }
}

