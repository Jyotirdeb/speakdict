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

package com.thearch.speakdict.main.dictionaries;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.thearch.speakdict.main.dictionaries.rt.RTEntryViewModel;

/**
 * Exports the data displayed in a {@link ResultListFragment} to text format.
 * @param <T> must be a List of {@link RTEntryViewModel} or a List of {@link com.thearch.speakdict.main.dictionaries.dictionary.DictionaryEntry.DictionaryEntryDetails}.
 */
public interface ResultListExporter<T> {

    /**
     * @param word the word the user looked up
     * @param filter an optional filter that the user applied to the search
     * @param entries the matching entries for the user's word and optional filter
     * @return a String representation of the entries.
     */
    String export(@NonNull String word, @Nullable String filter, @NonNull T entries);
}
