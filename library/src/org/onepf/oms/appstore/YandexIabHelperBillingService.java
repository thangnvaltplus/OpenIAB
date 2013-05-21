/*******************************************************************************
 * Copyright 2013 One Platform Foundation
 *
 *       Licensed under the Apache License, Version 2.0 (the "License");
 *       you may not use this file except in compliance with the License.
 *       You may obtain a copy of the License at
 *
 *           http://www.apache.org/licenses/LICENSE-2.0
 *
 *       Unless required by applicable law or agreed to in writing, software
 *       distributed under the License is distributed on an "AS IS" BASIS,
 *       WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *       See the License for the specific language governing permissions and
 *       limitations under the License.
 ******************************************************************************/

package org.onepf.oms.appstore;

import android.content.Context;
import android.content.Intent;
import com.android.vending.billing.IInAppBillingService;
import com.yandex.store.service.IStoreInAppService;

/**
 * Author: Yury Vasileuski
 * Date: 18.05.13
 */
public class YandexIabHelperBillingService extends IabHelperBillingService {
    public static final int BILLING_RESPONSE_RESULT_BUY_ALREADY_IN_PROGRESS= 10000;

    public YandexIabHelperBillingService(Context context) {
        super(context);
        LOG_PREFIX = "[Yandex Billing Service] ";
    }

    @Override
    public IInAppBillingService getInAppBillingService(android.os.IBinder service) {
        return (IInAppBillingService)IStoreInAppService.Stub.asInterface(service);
    }

    @Override
    public Intent getServiceIntent() {
        return new Intent("com.yandex.store.service.StoreInAppService.BIND");
    }

    @Override
    public boolean isDataSignatureSupported() {
        return false;
    }

    public static String getResponseDesc(int code) {
        String value;
        switch(code) {
            case BILLING_RESPONSE_RESULT_BUY_ALREADY_IN_PROGRESS:
                value = "10000:Already in progress";
                break;
            default:
                value = IabHelperBillingService.getResponseDesc(code);
        }
        return value;
    }
}
