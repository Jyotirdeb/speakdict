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

package com.thearch.speakdict.wotd;

import android.annotation.TargetApi;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.support.annotation.VisibleForTesting;
import android.util.Log;

import java.util.List;

import com.thearch.speakdict.Constants;

/**
 * Word of the day task for API levels Lollipop and later.
 * This uses JobScheduler.
 */
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
@VisibleForTesting(otherwise = VisibleForTesting.PACKAGE_PRIVATE)
public final class WotdJob {
    private static final String TAG = Constants.TAG + WotdJob.class.getSimpleName();

    private WotdJob() {
        // prevent instantiation
    }

    static void reschedule(Context context) {
        Log.d(TAG, "reschedule() called with: " + "context = [" + context + "]");
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
        List<JobInfo> jobs = jobScheduler.getAllPendingJobs();
        Log.v(TAG, "Pending jobs: " + jobs);
        if (jobs.isEmpty()) schedule(context);
    }

    static void schedule(Context context) {
        Log.d(TAG, "schedule() called with: " + "context = [" + context + "]");
        JobInfo jobInfo = new JobInfo.Builder(TAG.hashCode(), new ComponentName(context, WotdJobService.class))
                .setBackoffCriteria(Wotd.NOTIFICATION_FREQUENCY_MS, JobInfo.BACKOFF_POLICY_EXPONENTIAL)
                .setRequiresDeviceIdle(false)
                .setPeriodic(Wotd.NOTIFICATION_FREQUENCY_MS)
                .setPersisted(true)
                .setRequiresCharging(false)
                .build();
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
        jobScheduler.schedule(jobInfo);
    }

    static void cancel(Context context) {
        Log.d(TAG, "cancel() called with: " + "context = [" + context + "]");
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
        jobScheduler.cancel(TAG.hashCode());
    }
}
