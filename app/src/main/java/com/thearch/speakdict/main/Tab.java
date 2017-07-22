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

package com.thearch.speakdict.main;

public enum Tab {
    RHYMER, THESAURUS, DICTIONARY, READER, FAVORITES, PATTERN, WOTD;

    public static Tab parse(String value) {
        if (FAVORITES.name().equalsIgnoreCase(value)) return FAVORITES;
        if (WOTD.name().equalsIgnoreCase(value)) return WOTD;
        if (PATTERN.name().equalsIgnoreCase(value)) return PATTERN;
        if (RHYMER.name().equalsIgnoreCase(value)) return RHYMER;
        if (THESAURUS.name().equalsIgnoreCase(value)) return THESAURUS;
        if (DICTIONARY.name().equalsIgnoreCase(value)) return DICTIONARY;
        if (READER.name().equalsIgnoreCase(value)) return READER;
        return null;
    }
}
