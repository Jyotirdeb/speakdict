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

package com.thearch.speakdict.main.dictionaries.dictionary;

import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.thearch.speakdict.R;
import com.thearch.speakdict.databinding.ListItemDictionaryEntryBinding;
import com.thearch.speakdict.main.TextPopupMenu;
import com.thearch.speakdict.main.dictionaries.ResultListAdapter;
import com.thearch.speakdict.main.dictionaries.rt.OnWordClickListener;


public class DictionaryListAdapter extends ResultListAdapter<DictionaryEntry.DictionaryEntryDetails> {

    private final OnWordClickListener mListener;

    public DictionaryListAdapter(OnWordClickListener listener) {
        mListener = listener;
    }

    @Override
    public ResultListAdapter.ResultListEntryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ListItemDictionaryEntryBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.list_item_dictionary_entry,
                parent,
                false);
        return new ResultListAdapter.ResultListEntryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ResultListAdapter.ResultListEntryViewHolder holder, int position) {
        DictionaryEntry.DictionaryEntryDetails entry = getItem(position);
        ListItemDictionaryEntryBinding binding = (ListItemDictionaryEntryBinding) holder.binding;
        TextPopupMenu.addSelectionPopupMenu(binding.definition,  mListener);
        binding.setEntry(entry);
        binding.executePendingBindings();
    }
}
