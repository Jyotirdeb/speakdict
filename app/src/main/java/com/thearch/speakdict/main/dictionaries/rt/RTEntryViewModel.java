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
import android.databinding.ObservableBoolean;
import android.support.annotation.ColorInt;

import javax.inject.Inject;

import com.thearch.speakdict.Favorites;
import com.thearch.speakdict.dagger.DaggerHelper;
import com.thearch.speakdict.databinding.BindingCallbackAdapter;

public class RTEntryViewModel {
    enum Type {
        HEADING,
        SUBHEADING,
        WORD
    }

    public final Type type;
    public final String text;
    public final @ColorInt int backgroundColor;
    public final ObservableBoolean isFavorite = new ObservableBoolean();
    public final boolean hasDefinition;
    public final boolean showButtons;

    @Inject Favorites mFavorites;

    public RTEntryViewModel(Context context, Type type, String text) {
        this(context, type, text, 0, false, false);
    }

    public RTEntryViewModel(Context context, Type type, String text, @ColorInt int backgroundColor, boolean isFavorite, boolean showButtons) {
        this(context, type, text, backgroundColor, isFavorite, true, showButtons);
    }

    public RTEntryViewModel(Context context, Type type, String text, @ColorInt int backgroundColor, boolean isFavorite, boolean hasDefinition, boolean showButtons) {
        DaggerHelper.getMainScreenComponent(context).inject(this);
        this.type = type;
        this.text = text;
        this.backgroundColor = backgroundColor;
        this.isFavorite.set(isFavorite);
        this.hasDefinition = hasDefinition;
        this.showButtons = showButtons;
        this.isFavorite.addOnPropertyChangedCallback(
                new BindingCallbackAdapter(() -> mFavorites.saveFavorite(text, this.isFavorite.get())));
    }

    @Override
    public String toString() {
        return "RTEntry{" +
                "text='" + text + '\'' +
                ", type=" + type +
                '}';
    }
}
