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
import android.content.Context;

public class DaggerHelper {
    private static AppComponent sAppComponent;

    public static AppComponent.MainScreenComponent getMainScreenComponent(Context context) {
        return getAppComponent(context).getMainScreenComponent();
    }

    public static AppComponent.MainScreenComponent getMainScreenComponent(Application application) {
        return getAppComponent(application).getMainScreenComponent();
    }

    public static AppComponent.SettingsComponent getSettingsComponent(Context context) {
        return getAppComponent(context).getSettingsComponent();
    }

    public static AppComponent.SettingsComponent getSettingsComponent(Application application) {
        return getAppComponent(application).getSettingsComponent();
    }

    public static AppComponent.WotdComponent getWotdComponent(Context context) {
        return getAppComponent(context).getWotdComponent();
    }

    public static AppComponent.WotdComponent getWotdComponent(Application application) {
        return getAppComponent(application).getWotdComponent();
    }

    private static AppComponent getAppComponent(Context context) {
        return getAppComponent((Application) context.getApplicationContext());
    }

    private static AppComponent getAppComponent(Application application) {
        if (sAppComponent == null) {
            sAppComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(application))
                    .build();
        }
        return sAppComponent;
    }
}
