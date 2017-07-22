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

package com.thearch.speakdict.main.dictionaries.rt;

import android.support.annotation.VisibleForTesting;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@VisibleForTesting(otherwise = VisibleForTesting.PACKAGE_PRIVATE)
public final class RTUtils {

    private RTUtils() {
        // prevent instantiation
    }

    /**
     * @return all the Strings in the array words which are present in the Set filter
     */
    static String[] filter(String[] words, Set<String> filter) {
        if (words == null) return new String[0];
        List<String> filteredWords = new ArrayList<>();
        for (String word : words) {
            if (filter.contains(word)) filteredWords.add(word);
        }
        return filteredWords.toArray(new String[filteredWords.size()]);
    }
}
