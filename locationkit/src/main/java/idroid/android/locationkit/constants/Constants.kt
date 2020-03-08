package idroid.android.locationkit.constants

object Constants {
    const val TAG = "LocationKit"

    object Permission {
        private const val PERMISSION_TAG = "android.permission."
        const val FINE_LOCATION = "${PERMISSION_TAG}ACCESS_FINE_LOCATION"
        const val COARSE_LOCATION = "${PERMISSION_TAG}ACCESS_COARSE_LOCATION"
        const val BACKGROUND_LOCATION = "${PERMISSION_TAG}ACCESS_BACKGROUND_LOCATION"

        const val PERMISSION_NEED = "Need Location Permission"
        const val PERMISSION_GRANTED = "Need Location Permission"
    }

    object LocationWarningMessage {
        const val LAST_KNOW_LOCATION_NULL = "Huawei Service Get Last Known Location is NULL.."
        const val LAST_KNOW_LOCATION_FAIL = "Huawei Services Last Know Location is fail Cause : "

        const val CURRENT_LOCATION_SUCCESS = "Huawei Request Location Updated.."
        const val CURRENT_LOCATION_FAIL = "Huawei Request Location Updated.."
        const val CURRENT_LOCATION_UPDATED = "Huawei Request Location Updated.."

        const val CURRENT_LOCATION_REMOVE_NULL = "Have not any location listener."
        const val CURRENT_LOCATION_REMOVE_SUCCESS = "Huawei Service Remove Location.."
        const val CURRENT_LOCATION_REMOVE_FAIL = "Huawei Service Remove Location Fail Cause : "
    }
}