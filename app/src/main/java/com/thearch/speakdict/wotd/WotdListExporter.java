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

package com.thearch.speakdict.wotd;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import com.thearch.speakdict.R;
import com.thearch.speakdict.main.dictionaries.ResultListExporter;

public class WotdListExporter implements ResultListExporter<List<WotdEntryViewModel>> {
    private final Context mContext;

    public WotdListExporter(Context context) {
        mContext = context;
    }

    @Override
    public String export(@NonNull String word,
                         @Nullable String filter,
                         @NonNull List<WotdEntryViewModel> entries) {
        final String title = mContext.getString(R.string.share_wotd_title);
        StringBuilder builder = new StringBuilder(title);
        for (int i = 0; i < 10 && i < entries.size(); i++) {
            WotdEntryViewModel entry = entries.get(i);
            builder.append(mContext.getString(R.string.share_wotd_entry, entry.date, entry.text));
        }
        return builder.toString();
    }

}
