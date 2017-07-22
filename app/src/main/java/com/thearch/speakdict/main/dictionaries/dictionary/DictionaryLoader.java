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

package com.thearch.speakdict.main.dictionaries.dictionary;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import com.thearch.speakdict.Constants;
import com.thearch.speakdict.dagger.DaggerHelper;
import com.thearch.speakdict.main.dictionaries.ResultListData;
import com.thearch.speakdict.main.dictionaries.ResultListLoader;

public class DictionaryLoader extends ResultListLoader<ResultListData<DictionaryEntry.DictionaryEntryDetails>> {

    private static final String TAG = Constants.TAG + DictionaryLoader.class.getSimpleName();

    private final String mQuery;
    @Inject Dictionary mDictionary;

    public DictionaryLoader(Context context, String query) {
        super(context);
        DaggerHelper.getMainScreenComponent(context).inject(this);
        mQuery = query;
    }

    @Override
    public ResultListData<DictionaryEntry.DictionaryEntryDetails> loadInBackground() {
        Log.d(TAG, "loadInBackground() called with: " + "");
        List<DictionaryEntry.DictionaryEntryDetails> result = new ArrayList<>();
        if (TextUtils.isEmpty(mQuery)) return new ResultListData<>(mQuery, result);
        DictionaryEntry entry = mDictionary.lookup(mQuery);
        Collections.addAll(result, entry.details);
        return new ResultListData<>(entry.word, result);
    }

}
