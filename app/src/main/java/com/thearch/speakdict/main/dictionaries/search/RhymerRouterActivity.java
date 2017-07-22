/*
 * Copyright (c) 2017 Jyotirdeb Mukherjee
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
package com.thearch.speakdict.main.dictionaries.search;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.thearch.speakdict.main.Tab;

public class RhymerRouterActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ProcessTextRouter.handleIntent(this, getIntent(), Tab.RHYMER);
        finish();
    }
}
