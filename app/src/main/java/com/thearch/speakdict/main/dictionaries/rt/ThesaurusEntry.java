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

class ThesaurusEntry {
    public final String word;
    public final ThesaurusEntryDetails[] entries;

    ThesaurusEntry(String word, ThesaurusEntryDetails[] entries) {
        this.word = word;
        this.entries = entries;
    }

    enum WordType {
        @SuppressWarnings("unused")ADJ,
        @SuppressWarnings("unused")ADV,
        @SuppressWarnings("unused")NOUN,
        @SuppressWarnings("unused")VERB,
        @SuppressWarnings("unused")UNKNOWN
    }

    static class ThesaurusEntryDetails {
        final WordType wordType;
        final String[] synonyms;
        final String[] antonyms;

        ThesaurusEntryDetails(WordType wordType, String[] synonyms, String[] antonyms) {
            this.wordType = wordType;
            this.synonyms = synonyms;
            this.antonyms = antonyms;
        }
    }
}
