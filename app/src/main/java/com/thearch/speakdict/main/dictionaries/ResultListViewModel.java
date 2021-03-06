/*
 * Copyright (c) 2017 Jyotirdeb Mukherjee
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

package com.thearch.speakdict.main.dictionaries;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableField;
import android.util.Log;

import com.thearch.speakdict.BR;
import com.thearch.speakdict.Constants;
import com.thearch.speakdict.main.Tab;

public class ResultListViewModel<T> extends BaseObservable {
    private static final String TAG = Constants.TAG + ResultListViewModel.class.getSimpleName();

    final ObservableField<ResultListData<T>> data = new ObservableField<>();
    private ResultListAdapter<T> mAdapter;
    private final Tab mTab;

    ResultListViewModel(Tab tab) {
        mTab = tab;
    }

    void setAdapter(ResultListAdapter<T> adapter) {
        mAdapter = adapter;
    }

    @Bindable
    public boolean isDataAvailable() {
        return mAdapter != null && mAdapter.getItemCount() > 0;
    }

    void setData(ResultListData<T> loadedData) {
        Log.v(TAG, mTab + "/setData " + loadedData);
        mAdapter.clear();
        if (loadedData != null) mAdapter.addAll(loadedData.data);
        data.set(loadedData);
        notifyPropertyChanged(BR.dataAvailable);
    }

    String getUsedQueryWord() {
        return data.get() == null ? null : data.get().matchedWord;
    }

}
