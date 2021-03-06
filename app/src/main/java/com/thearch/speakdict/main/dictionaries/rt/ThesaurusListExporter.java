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

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.util.List;

import com.thearch.speakdict.R;
import com.thearch.speakdict.main.dictionaries.ResultListExporter;

public class ThesaurusListExporter implements ResultListExporter<List<RTEntryViewModel>> {
    private final Context mContext;

    public ThesaurusListExporter(Context context) {
        mContext = context;
    }

    @Override
    public String export(@NonNull String word,
                         @Nullable String filter, /* results only include rhymes of filter */
                         @NonNull List<RTEntryViewModel> entries) {
        final String title;
        if (TextUtils.isEmpty(filter)) {
            title = mContext.getString(R.string.share_thesaurus_title, word);
        } else {
            title = mContext.getString(R.string.share_thesaurus_title_with_filter, word, filter);
        }
        StringBuilder builder = new StringBuilder(title);
        for (RTEntryViewModel entry : entries) {
            int entryResId;
            if (entry.type == RTEntryViewModel.Type.HEADING) entryResId = R.string.share_rt_heading;
            else if (entry.type == RTEntryViewModel.Type.SUBHEADING) entryResId = R.string.share_rt_subheading;
            else entryResId = R.string.share_rt_entry;
            builder.append(mContext.getString(entryResId, entry.text));
        }
        return builder.toString();
    }
}
