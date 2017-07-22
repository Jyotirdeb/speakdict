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

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.text.format.DateUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TimeZone;

import javax.inject.Inject;

import com.thearch.speakdict.Constants;
import com.thearch.speakdict.Favorites;
import com.thearch.speakdict.R;
import com.thearch.speakdict.dagger.DaggerHelper;
import com.thearch.speakdict.main.dictionaries.ResultListData;
import com.thearch.speakdict.main.dictionaries.ResultListLoader;
import com.thearch.speakdict.main.dictionaries.dictionary.Dictionary;
import com.thearch.speakdict.settings.Settings;
import com.thearch.speakdict.settings.SettingsPrefs;

public class WotdLoader extends ResultListLoader<ResultListData<WotdEntryViewModel>> {

    private static final String TAG = Constants.TAG + WotdLoader.class.getSimpleName();

    @Inject Dictionary mDictionary;
    @Inject SettingsPrefs mPrefs;
    @Inject Favorites mFavorites;

    public WotdLoader(Context context) {
        super(context);
        DaggerHelper.getWotdComponent(context).inject(this);
    }

    @Override
    public ResultListData<WotdEntryViewModel> loadInBackground() {
        Log.d(TAG, "loadInBackground()");

        List<WotdEntryViewModel> data = new ArrayList<>(100);

        Cursor cursor = mDictionary.getRandomWordCursor();
        if (cursor == null || cursor.getCount() == 0) return emptyResult();

        try {
            Set<String> favorites = mFavorites.getFavorites();
            Calendar calendar = Wotd.getTodayUTC();
            Calendar calendarDisplay = Wotd.getTodayUTC();
            calendarDisplay.setTimeZone(TimeZone.getDefault());
            Settings.Layout layout = Settings.getLayout(mPrefs);
            for (int i = 0; i < 100; i++) {
                Random random = new Random();
                random.setSeed(calendar.getTimeInMillis());
                String date = DateUtils.formatDateTime(getContext(),
                        calendarDisplay.getTimeInMillis(),
                        DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
                int position = random.nextInt(cursor.getCount());
                if (cursor.moveToPosition(position)) {
                    String word = cursor.getString(0);
                    @ColorRes int color = (i % 2 == 0) ? R.color.row_background_color_even : R.color.row_background_color_odd;
                    data.add(new WotdEntryViewModel(
                            getContext(),
                            word,
                            date,
                            ContextCompat.getColor(getContext(), color),
                            favorites.contains(word),
                            layout == Settings.Layout.EFFICIENT));
                }
                calendar.add(Calendar.DAY_OF_YEAR, -1);
                calendarDisplay.add(Calendar.DAY_OF_YEAR, -1);

            }

        } finally {
            cursor.close();
        }
        return new ResultListData<>(getContext().getString(R.string.wotd_list_header), data);
    }

    private ResultListData<WotdEntryViewModel> emptyResult() {
        return new ResultListData<>(getContext().getString(R.string.wotd_list_header), new ArrayList<>());
    }

}
