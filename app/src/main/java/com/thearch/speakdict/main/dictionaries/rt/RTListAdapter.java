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

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thearch.speakdict.R;
import com.thearch.speakdict.databinding.ListItemHeadingBinding;
import com.thearch.speakdict.databinding.ListItemSubheadingBinding;
import com.thearch.speakdict.databinding.ListItemWordBinding;
import com.thearch.speakdict.main.Tab;
import com.thearch.speakdict.main.TextPopupMenu;
import com.thearch.speakdict.main.dictionaries.ResultListAdapter;


public class RTListAdapter extends ResultListAdapter<RTEntryViewModel> {

    private final OnWordClickListener mWordClickedListener;
    private final EntryIconClickListener mEntryIconClickListener;

    public RTListAdapter(Activity activity) {
        mWordClickedListener = (OnWordClickListener) activity;
        mEntryIconClickListener = new EntryIconClickListener();
    }

    @Override
    public int getItemViewType(int position) {
        RTEntryViewModel entry = getItem(position);
        return entry.type.ordinal();
    }

    @Override
    public ResultListAdapter.ResultListEntryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId;
        if (viewType == RTEntryViewModel.Type.HEADING.ordinal())
            layoutId = R.layout.list_item_heading;
        else if (viewType == RTEntryViewModel.Type.SUBHEADING.ordinal())
            layoutId = R.layout.list_item_subheading;
        else
            layoutId = R.layout.list_item_word;

        ViewDataBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                layoutId,
                parent,
                false);
        return new ResultListAdapter.ResultListEntryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ResultListAdapter.ResultListEntryViewHolder holder, int position) {
        RTEntryViewModel viewModel = getItem(position);
        if (viewModel.type == RTEntryViewModel.Type.HEADING) {
            ((ListItemHeadingBinding) holder.binding).setViewModel(viewModel);
        } else if (viewModel.type == RTEntryViewModel.Type.SUBHEADING) {
            ((ListItemSubheadingBinding) holder.binding).setViewModel(viewModel);
        } else {
            ListItemWordBinding wordBinding = (ListItemWordBinding) holder.binding;
            wordBinding.setViewModel(viewModel);
            wordBinding.setEntryIconClickListener(mEntryIconClickListener);
            TextPopupMenu.addPopupMenu(
                    viewModel.showButtons ? TextPopupMenu.Style.SYSTEM : TextPopupMenu.Style.FULL,
                    wordBinding.text1,
                    mWordClickedListener);
        }
        holder.binding.executePendingBindings();
    }

    public class EntryIconClickListener {

        private String getWord(View v) {
            ListItemWordBinding binding = DataBindingUtil.getBinding((View) v.getParent());
            return binding.text1.getText().toString();
        }

        public void onRhymerIconClicked(View v) {
            mWordClickedListener.onWordClick(getWord(v), Tab.RHYMER);
        }

        public void onThesaurusIconClicked(View v) {
            mWordClickedListener.onWordClick(getWord(v), Tab.THESAURUS);
        }

        public void onDictionaryIconClicked(View v) {
            mWordClickedListener.onWordClick(getWord(v), Tab.DICTIONARY);
        }
    }

}
