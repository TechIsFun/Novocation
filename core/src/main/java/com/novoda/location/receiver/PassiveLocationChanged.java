/**
 * Copyright 2011 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Code modified by Novoda Ltd, 2011.
 */
package com.novoda.location.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import com.novoda.location.provider.LastLocationFinder;

public class PassiveLocationChanged extends BroadcastReceiver {

    private LastLocationFinder lastLocationFinder;

    public PassiveLocationChanged() {}

    public PassiveLocationChanged(LastLocationFinder locationFinder) {
        this.lastLocationFinder = locationFinder;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        new PassiveLocationChangedHandler(getLastLocationFinder(context)).onNewChange(intent);
    }

    private LastLocationFinder getLastLocationFinder(Context context) {
        return lastLocationFinder != null ? lastLocationFinder : createLastLocationFinder(context);
    }

    private LastLocationFinder createLastLocationFinder(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        return new LastLocationFinder(locationManager);
    }

}