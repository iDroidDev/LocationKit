package idroid.android.locationkit.utils

import android.content.Context
import android.content.pm.PackageManager
import idroid.android.locationkit.constants.PermissionConstants

class Utils {
    companion object {
        fun checkPermission(context: Context): Boolean {
            val fineLocation =
                context.checkCallingOrSelfPermission(PermissionConstants.FINE_LOCATION)
            val coarseLocation =
                context.checkCallingOrSelfPermission(PermissionConstants.COARSE_LOCATION)

            return (fineLocation == PackageManager.PERMISSION_GRANTED || coarseLocation == PackageManager.PERMISSION_GRANTED)
        }
    }
}