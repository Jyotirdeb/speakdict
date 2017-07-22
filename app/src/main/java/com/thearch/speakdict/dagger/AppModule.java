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

import android.app.Application;

import javax.inject.Singleton;

import com.thearch.speakdict.Favorites;
import com.thearch.speakdict.Tts;
import com.thearch.speakdict.UserDb;
import com.thearch.speakdict.main.dictionaries.EmbeddedDb;
import com.thearch.speakdict.main.dictionaries.dictionary.Dictionary;
import com.thearch.speakdict.main.dictionaries.rt.Rhymer;
import com.thearch.speakdict.main.dictionaries.rt.Thesaurus;
import com.thearch.speakdict.main.dictionaries.search.Suggestions;
import com.thearch.speakdict.settings.Settings;
import com.thearch.speakdict.settings.SettingsPrefs;
import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private final Application mApplication;
    private final UserDb mUserDb;

    public AppModule(Application application) {
        mApplication = application;
        mUserDb = new UserDb(application);
    }

    @Provides @Singleton Tts providesTts(SettingsPrefs settingsPrefs) {
        return new Tts(mApplication, settingsPrefs);
    }

    @Provides @Singleton EmbeddedDb providesDbHelper() {
        return new EmbeddedDb(mApplication);
    }

    @Provides @Singleton Rhymer providesRhymer(EmbeddedDb embeddedDb, SettingsPrefs prefs) {
        return new Rhymer(embeddedDb, prefs);
    }

    @Provides @Singleton Thesaurus providesThesaurus(EmbeddedDb embeddedDb) {
        return new Thesaurus(embeddedDb);
    }

    @Provides @Singleton Dictionary providesDictionary(EmbeddedDb embeddedDb) {
        return new Dictionary(embeddedDb);
    }

    @Provides @Singleton SettingsPrefs providesSettingsPrefs() {
        Settings.migrateSettings(mApplication);
        return SettingsPrefs.get(mApplication);
    }

    @Provides Favorites providesFavorites() {
        return new Favorites(mUserDb);
    }

    @Provides Suggestions providesSuggestions() {
        return new Suggestions(mUserDb);
    }
}
