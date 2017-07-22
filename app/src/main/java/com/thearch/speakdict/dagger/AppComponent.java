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

package com.thearch.speakdict.dagger;

import javax.inject.Singleton;

import com.thearch.speakdict.main.MainActivity;
import com.thearch.speakdict.main.dictionaries.ResultListFragment;
import com.thearch.speakdict.main.dictionaries.ResultListHeaderFragment;
import com.thearch.speakdict.main.dictionaries.ResultListHeaderViewModel;
import com.thearch.speakdict.main.dictionaries.dictionary.DictionaryEntry;
import com.thearch.speakdict.main.dictionaries.dictionary.DictionaryLoader;
import com.thearch.speakdict.main.dictionaries.rt.FavoritesLoader;
import com.thearch.speakdict.main.dictionaries.rt.PatternLoader;
import com.thearch.speakdict.main.dictionaries.rt.RTEntryViewModel;
import com.thearch.speakdict.main.dictionaries.rt.RhymerLoader;
import com.thearch.speakdict.main.dictionaries.rt.ThesaurusLoader;
import com.thearch.speakdict.main.dictionaries.search.Search;
import com.thearch.speakdict.main.dictionaries.search.SuggestionsCursor;
import com.thearch.speakdict.main.dictionaries.search.SuggestionsProvider;
import com.thearch.speakdict.main.reader.ReaderViewModel;
import com.thearch.speakdict.settings.SettingsActivity;
import com.thearch.speakdict.settings.VoicePreference;
import com.thearch.speakdict.wotd.WotdBootReceiver;
import com.thearch.speakdict.wotd.WotdBroadcastReceiver;
import com.thearch.speakdict.wotd.WotdEntryViewModel;
import com.thearch.speakdict.wotd.WotdJobService;
import com.thearch.speakdict.wotd.WotdLoader;
import dagger.Component;
import dagger.Subcomponent;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    MainScreenComponent getMainScreenComponent();
    SettingsComponent getSettingsComponent();
    WotdComponent getWotdComponent();

    @Subcomponent
    interface MainScreenComponent {
        void inject(MainActivity mainActivity);
        void inject(ResultListFragment<RTEntryViewModel> resultListFragment);
        void injectWotd(ResultListFragment<WotdEntryViewModel> resultListFragment);
        void injectDict(ResultListFragment<DictionaryEntry> resultListFragment);
        void inject(RTEntryViewModel rtEntry);
        void inject(ResultListHeaderFragment resultListHeaderFragment);
        void inject(ResultListHeaderViewModel resultListHeaderViewModel);
        void inject(ReaderViewModel readerViewModel);
        void inject(RhymerLoader rhymerLoader);
        void inject(ThesaurusLoader thesaurusLoader);
        void inject(DictionaryLoader dictionaryLoader);
        void inject(PatternLoader patternLoader);
        void inject(FavoritesLoader favoritesLoader);
        void inject(SuggestionsCursor suggestionsCursor);
        void inject(SuggestionsProvider suggestionsProvider);
        void inject(Search search);
    }

    @Subcomponent
    interface SettingsComponent {
        void inject(SettingsActivity settingsActivity);
        void inject(SettingsActivity.GeneralPreferenceFragment generalPreferenceFragment);
        void inject(VoicePreference voicePreference);
    }

    @Subcomponent
    interface WotdComponent {
        void inject(WotdBroadcastReceiver wotdBroadcastReceiver);
        void inject(WotdJobService wotdJobService);
        void inject(WotdBootReceiver wotdBootReceiver);
        void inject(WotdLoader wotdLoader);
        void inject(WotdEntryViewModel entry);
    }
}
