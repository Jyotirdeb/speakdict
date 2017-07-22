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

package com.thearch.speakdict.main.dictionaries.rt;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.inject.Inject;

import com.thearch.speakdict.Constants;
import com.thearch.speakdict.Favorites;
import com.thearch.speakdict.R;
import com.thearch.speakdict.dagger.DaggerHelper;
import com.thearch.speakdict.main.dictionaries.ResultListData;
import com.thearch.speakdict.main.dictionaries.ResultListLoader;
import com.thearch.speakdict.settings.Settings;
import com.thearch.speakdict.settings.SettingsPrefs;

public class FavoritesLoader extends ResultListLoader<ResultListData<RTEntryViewModel>> {

    private static final String TAG = Constants.TAG + FavoritesLoader.class.getSimpleName();

    @Inject SettingsPrefs mPrefs;
    @Inject Favorites mFavorites;
    public FavoritesLoader(Context context) {
        super(context);
        DaggerHelper.getMainScreenComponent(context).inject(this);
    }

    @Override
    public ResultListData<RTEntryViewModel> loadInBackground() {
        Log.d(TAG, "loadInBackground()");

        List<RTEntryViewModel> data = new ArrayList<>();

        Set<String> favorites = mFavorites.getFavorites();
        if (favorites.isEmpty()) return emptyResult();

        TreeSet<String> sortedFavorites = new TreeSet<>(favorites);
        Settings.Layout layout = Settings.getLayout(mPrefs);
        int i = 0;
        for (String favorite : sortedFavorites) {
            @ColorRes int color = (i % 2 == 0)? R.color.row_background_color_even : R.color.row_background_color_odd;
            data.add(new RTEntryViewModel(
                    getContext(),
                    RTEntryViewModel.Type.WORD,
                    favorite,
                    ContextCompat.getColor(getContext(), color),
                    true,
                    layout == Settings.Layout.EFFICIENT));
            i++;
        }
        return new ResultListData<>(getContext().getString(R.string.favorites_list_header), data);
    }

    private ResultListData<RTEntryViewModel> emptyResult() {
        return new ResultListData<>(getContext().getString(R.string.favorites_list_header), new ArrayList<>());
    }

}
