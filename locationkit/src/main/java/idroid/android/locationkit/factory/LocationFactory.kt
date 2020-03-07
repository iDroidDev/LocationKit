package idroid.android.locationkit.factory

import android.app.Activity
import idroid.android.locationkit.utils.DistributeType

class LocationFactory {
    companion object {
        fun getLocationFactory(context: Activity, type: DistributeType): Location? {
            return if (DistributeType.HUAWEI_SERVICES == type) {
                HuaweiLocationImpl(context)
            } else {
                GoogleLocationImpl(context)
            }
        }
    }
}