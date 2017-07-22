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

package com.thearch.speakdict.wotd;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Build;
import android.util.Log;

import javax.inject.Inject;

import com.thearch.speakdict.Constants;
import com.thearch.speakdict.dagger.DaggerHelper;
import com.thearch.speakdict.main.dictionaries.dictionary.Dictionary;
import io.reactivex.schedulers.Schedulers;

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class WotdJobService extends JobService {
    private static final String TAG = Constants.TAG + WotdJobService.class.getSimpleName();
    @Inject Dictionary mDictionary;

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerHelper.getWotdComponent(getApplication()).inject(this);
    }

    @Override
    public boolean onStartJob(JobParameters params) {
        Log.v(TAG, "onStartJob: params " + params);
        Schedulers.io().scheduleDirect(() -> {
            Wotd.notifyWotd(getApplicationContext(), mDictionary);
            jobFinished(params, false);
        });
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }

}
