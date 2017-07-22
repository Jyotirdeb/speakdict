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

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import com.thearch.speakdict.Favorites;
import com.thearch.speakdict.dagger.DaggerHelper;
import com.thearch.speakdict.databinding.BindingCallbackAdapter;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ResultListHeaderViewModel {

    public final ObservableField<String> query = new ObservableField<>();
    public final ObservableField<String> filter = new ObservableField<>();
    public final ObservableBoolean isFavorite = new ObservableBoolean();
    @Inject
    Favorites mFavorites;

    ResultListHeaderViewModel(Context context, String queryText, String filterText) {
        DaggerHelper.getMainScreenComponent(context).inject(this);
        // When the query text changes, update the star icon
        query.addOnPropertyChangedCallback(new BindingCallbackAdapter(this::readFavorite));
        // When the user taps on the star icon, update the favorite in the DB
        isFavorite.addOnPropertyChangedCallback(mPersistFavoriteCallback);
        query.set(queryText); // This will cause the star icon to be updated too
        filter.set(filterText);
        EventBus.getDefault().register(this);
    }

    void destroy() {
        EventBus.getDefault().unregister(this);
    }

    private void readFavorite() {
        Single.fromCallable(() -> mFavorites.isFavorite(query.get()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(isFavoriteValue -> {
                    isFavorite.removeOnPropertyChangedCallback(mPersistFavoriteCallback);
                    isFavorite.set(isFavoriteValue);
                    isFavorite.addOnPropertyChangedCallback(mPersistFavoriteCallback);
                });
    }

    private final BindingCallbackAdapter mPersistFavoriteCallback =
        new BindingCallbackAdapter(() -> mFavorites.saveFavorite(query.get(), isFavorite.get()));

    @SuppressWarnings("unused")
    @Subscribe
    public void onFavoritesChanged(Favorites.OnFavoritesChanged event) {
        readFavorite();
    }

    @Override
    public String toString() {
        return "RTEntry{" +
                "query ='" + query.get() + '\'' +
                ", filter ='" + filter.get() + '\'' +
                ", isFavorite =" + isFavorite.get() +
                '}';
    }
}
