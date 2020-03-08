package idroid.android.locationkit.utils

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import idroid.android.locationkit.constants.Constants

class Utils {
    companion object {
        fun checkPermission(context: Context): Boolean {
            val fineLocation =
                context.checkCallingOrSelfPermission(Constants.Permission.FINE_LOCATION)
            val coarseLocation =
                context.checkCallingOrSelfPermission(Constants.Permission.COARSE_LOCATION)
            val backgroundLocation =
                context.checkCallingOrSelfPermission(Constants.Permission.BACKGROUND_LOCATION)
            return if (isBuildVersionDownToP() && CheckServiceAvaiable.getAvailableService(context) == DistributeType.HUAWEI_SERVICES) fineLocation == PackageManager.PERMISSION_GRANTED && coarseLocation == PackageManager.PERMISSION_GRANTED && backgroundLocation == PackageManager.PERMISSION_GRANTED
            else (fineLocation == PackageManager.PERMISSION_GRANTED && coarseLocation == PackageManager.PERMISSION_GRANTED)
        }

        fun isBuildVersionDownToP(): Boolean = Build.VERSION.SDK_INT <= Build.VERSION_CODES.P
    }
}