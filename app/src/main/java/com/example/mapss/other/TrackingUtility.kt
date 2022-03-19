package com.example.mapss.other

import android.content.Context
import android.location.Location
import android.os.Build
import com.example.mapss.services.Polyline
import pub.devrel.easypermissions.EasyPermissions
import java.util.concurrent.TimeUnit
import java.util.jar.Manifest

object TrackingUtility {

    fun hasLocationPermission(context: Context) =
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O){
            EasyPermissions.hasPermissions(
                context,
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            )
        } else{
            EasyPermissions.hasPermissions(
                context,
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.ACCESS_BACKGROUND_LOCATION
            )
        }

    fun getFormattedStopWatchTime(millis: Long, includeMillis:Boolean = false): String =
            String.format(
                    "%02d : %02d : %02d",
                    TimeUnit.MILLISECONDS.toHours(millis),
                    TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(
                            TimeUnit.MILLISECONDS.toHours(millis)
                    ),
                    TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(
                            TimeUnit.MILLISECONDS.toMinutes(millis)
                    )
            )

    fun getFormattedStopWatchTime1(ms : Long, includeMillis:Boolean = false) : String{
        var milliseconds = ms
        val hours = TimeUnit.MILLISECONDS.toHours(milliseconds)
        milliseconds -= TimeUnit.HOURS.toMillis(hours)
        val  minutes = TimeUnit.MILLISECONDS.toMinutes(milliseconds)
        milliseconds -= TimeUnit.MINUTES.toMillis(milliseconds)
        val seconds = TimeUnit.MILLISECONDS.toSeconds(milliseconds)
        if (!includeMillis){
            return "${if (hours < 10)"0" else ""} $hours:" +
             "${if (minutes < 10)"0" else ""} $minutes:" +
             "${if (seconds < 10)"0" else ""} $seconds"

        }

        milliseconds -= TimeUnit.SECONDS.toMillis(seconds)
        milliseconds /= 10
        return "${if (hours < 10)"0" else ""} $hours:" +
                "${if (minutes < 10)"0" else ""} $minutes:" +
                "${if (seconds < 10)"0" else ""} $seconds:" +
                "${if (milliseconds < 10)"0" else ""} $milliseconds"


    }

    fun calculatePolylineLength(polyline: Polyline) : Float {
        var distance = 0f
        for (i in 0..polyline.size -2){
            val  pos1 = polyline[i]
            val pos2 = polyline[i]
            val result = FloatArray(1)
            Location.distanceBetween(
                    pos1.latitude,
                    pos1.longitude,
                    pos2.latitude,
                    pos2.longitude,
                    result
            )

            distance += result[0]
        }

        return distance
    }
}