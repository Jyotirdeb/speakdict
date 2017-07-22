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

package com.thearch.speakdict.main.dictionaries.dictionary;

public class DictionaryEntry {
    public final String word;
    public final DictionaryEntryDetails[] details;

    public DictionaryEntry(String word, DictionaryEntryDetails[] details) {
        this.word = word;
        this.details = details;
    }

    public static class DictionaryEntryDetails {
        public final String partOfSpeech;
        public final String definition;

        public DictionaryEntryDetails(String partOfSpeech, String definition) {
            this.partOfSpeech = partOfSpeech;
            this.definition = definition;
        }
    }
}
