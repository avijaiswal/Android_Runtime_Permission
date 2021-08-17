package com.app.runtimepermission

import android.app.Activity
import android.content.pm.PackageManager
import android.util.Log
import com.app.runtimepermission.constant.PermissionUri
import com.app.runtimepermissions.MakePermissionRequest
import com.app.runtimepermissions.constant.PermissionCode
import com.app.runtimepermissions.constant.PermissionType
import com.app.runtimepermissions.interfaces.IPermissionCallbacks

/**
 * <Use of Class>
 * <!--
 *  This class is used to handle permission result
 * -->
 */
open class PermissionResultHelper() {

    var isCheckForAlwaysHide=false
    /**
     * < Use of method>
     *  <!--
     *  This method will call when we received permission results
     *  -->
     *
     *  @logic
     *  <!--
     *  We are checking permission grant/denied state on the basis of
     *  permission request code and result data and we will trigger callback according to it
     *  -->
     *
     * @param requestCode //permission request code, helps to identify permission type
     * @param permissions //requested permissions
     * @param grantResults //grant permissions
     * @param iCallBack //interface instance to trigger callback
     * @param activity//activity reference
     */
    fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<String>,
            grantResults: IntArray,
            iCallBack: IPermissionCallbacks?,
            activity: Activity
    ) {

        if(requestCode==PermissionCode.MULTIPLE_PERMISSION_REQUEST_CODE.requestCode)
        {
            handleMultiPermissionResult(
                    grantResults,
                    permissions,
                    iCallBack,activity)

            return
        }

        val permissionType=getPermissionTypeByRequestCode(requestCode)
        permissionType?.let {
            handlePermissionResult(
                    grantResults,
                    permissions,
                    iCallBack,
                    activity,
                    it
            )
        }
    }

    /**
     * Method will return permission type on the basis of request code
     * @param requestCode //request code of permission
     * @return // permission type
     * @see PermissionType
     *
     */
    private fun getPermissionTypeByRequestCode(requestCode: Int): PermissionType? {
        //getting permission type on the basis of request code
        return when(requestCode)
        {
            PermissionCode.CAMERA.requestCode -> PermissionType.CAMERA

            PermissionCode.LOCATION_GROUP.requestCode -> PermissionType.LOCATION_GROUP

            PermissionCode.STORAGE_GROUP.requestCode -> PermissionType.STORAGE_GROUP

            PermissionCode.READ_STORAGE.requestCode -> PermissionType.READ_STORAGE

            PermissionCode.WRITE_STORAGE.requestCode -> PermissionType.WRITE_STORAGE

            PermissionCode.SMS_GROUP.requestCode -> PermissionType.SMS_GROUP

            PermissionCode.SEND_SMS.requestCode -> PermissionType.SEND_SMS

            PermissionCode.RECEIVE_SMS.requestCode -> PermissionType.RECEIVE_SMS

            PermissionCode.SMS.requestCode -> PermissionType.SMS

            PermissionCode.READ_SMS.requestCode -> PermissionType.READ_SMS

            PermissionCode.RECEIVE_MMS.requestCode -> PermissionType.RECEIVE_MMS

            PermissionCode.RECEIVE_WAP_PUSH.requestCode -> PermissionType.RECEIVE_WAP_PUSH

            PermissionCode.CALENDER_GROUP.requestCode -> PermissionType.CALENDER_GROUP

            PermissionCode.READ_CALENDAR.requestCode -> PermissionType.READ_CALENDAR

            PermissionCode.WRITE_CALENDAR.requestCode -> PermissionType.WRITE_CALENDAR

            PermissionCode.RECORD_AUDIO.requestCode -> PermissionType.RECORD_AUDIO

            PermissionCode.SENSOR.requestCode -> PermissionType.SENSOR

            PermissionCode.CONTACTS_GROUP.requestCode -> PermissionType.CONTACTS_GROUP

            PermissionCode.READ_CONTACTS.requestCode -> PermissionType.READ_CONTACTS

            PermissionCode.WRITE_CONTACTS.requestCode -> PermissionType.WRITE_CONTACTS

            PermissionCode.GET_ACCOUNT.requestCode -> PermissionType.GET_ACCOUNT

            PermissionCode.PHONE_GROUP.requestCode -> PermissionType.PHONE_GROUP

            PermissionCode.USE_SIP.requestCode -> PermissionType.USE_SIP

            PermissionCode.READ_PHONE_STATE.requestCode -> PermissionType.READ_PHONE_STATE

            PermissionCode.CALL_PHONE.requestCode -> PermissionType.CALL_PHONE

            PermissionCode.READ_CALL_LOG.requestCode -> PermissionType.READ_CALL_LOG

            PermissionCode.WRITE_CALL_LOG.requestCode -> PermissionType.WRITE_CALL_LOG

            PermissionCode.CALL_LOG_GROUP.requestCode -> PermissionType.CALL_LOG_GROUP

            else-> null
        }
    }

    /**
     * @param grantResults array of grant result
     * @param permissions permission request list
     * @param iCallBack //interface to trigger call backs
     */
    private fun handleMultiPermissionResult(grantResults: IntArray, permissions: Array<String>, iCallBack: IPermissionCallbacks?, activity: Activity) {
        if(grantResults.size>0)
        {
            val grantedPermissionType= arrayListOf<PermissionType>()
            val deniedPermissionType= arrayListOf<PermissionType>()
            for(a in 0.. grantResults.size-1)
            {
                if(grantResults[a]== PackageManager.PERMISSION_GRANTED) {
                    getPermissionTypeByUri(permissions[a])?.let { grantedPermissionType.add(it) }
                }else
                    getPermissionTypeByUri(permissions[a])?.let { deniedPermissionType.add(it) }
            }

            if(permissions.size==grantedPermissionType.size)
            {
                Log.d(MakePermissionRequest.TAG, "Multiple permissions has granted")
                iCallBack?.onMultiplePermissionResult(true,grantedPermissionType,deniedPermissionType,false)
            }else if(permissions.size==deniedPermissionType.size) {
                Log.d(MakePermissionRequest.TAG, "Some of Multiple permissions has denied")
                iCallBack?.onMultiplePermissionResult(false, grantedPermissionType, deniedPermissionType, isAlwaysHidePermission(permissions, activity))
            }else {
                Log.d(MakePermissionRequest.TAG, "Some of Multiple permissions has denied")
                iCallBack?.onMultiplePermissionResult(false, grantedPermissionType, deniedPermissionType, false)
            }
        }else{
            val deniedPermissionType= arrayListOf<PermissionType>()
            for(uri in permissions)
            {
                getPermissionTypeByUri(uri)?.let { deniedPermissionType.add(it) }
            }
            Log.d(MakePermissionRequest.TAG, "Multiple permission has denied")

            iCallBack?.onMultiplePermissionResult(false,null,deniedPermissionType,isAlwaysHidePermission(permissions,activity))
        }
    }

    /**
     * Method to check if multiple permission request in always invisible state
     */
    private fun isAlwaysHidePermission(permissions: Array<String>, activity: Activity): Boolean {
        for (permission in permissions) {
            if (activity.shouldShowRequestPermissionRationale(permission)) {
                return false
            }
        }
        return true
    }


    /**
     * <Use of method>
     *<!--
     * Method will return permission type of the basis of permission uri
     * -->
     * @param permissionUri //requested uri
     * @return Permission type
     * @see PermissionType
     */
    fun getPermissionTypeByUri( permissionUri:String): PermissionType?
    {
        //getting permission type on the basis of request code
        return when(permissionUri)
        {
            PermissionUri.CAMERA.requestUri -> PermissionType.CAMERA

            PermissionUri.RECORD_AUDIO.requestUri -> PermissionType.RECORD_AUDIO

            PermissionUri.SENSOR.requestUri -> PermissionType.SENSOR

            PermissionUri.ACCESS_FINE_LOCATION.requestUri -> PermissionType.LOCATION_GROUP

            PermissionUri.ACCESS_COARSE_LOCATION.requestUri -> PermissionType.LOCATION_GROUP

            PermissionUri.WRITE_STORAGE.requestUri -> PermissionType.WRITE_STORAGE

            PermissionUri.READ_STORAGE.requestUri -> PermissionType.READ_STORAGE

            PermissionUri.WRITE_CONTACTS.requestUri -> PermissionType.WRITE_CONTACTS

            PermissionUri.READ_CONTACTS.requestUri -> PermissionType.READ_CONTACTS

            PermissionUri.GET_ACCOUNTS.requestUri -> PermissionType.GET_ACCOUNT

            PermissionUri.READ_CALENDAR.requestUri -> PermissionType.READ_CALENDAR

            PermissionUri.WRITE_CALENDAR.requestUri -> PermissionType.WRITE_CALENDAR

            PermissionUri.READ_SMS.requestUri -> PermissionType.READ_SMS

            PermissionUri.SEND_SMS.requestUri -> PermissionType.SEND_SMS

            PermissionUri.RECEIVE_SMS.requestUri -> PermissionType.RECEIVE_SMS

            PermissionUri.RECEIVE_MMS.requestUri -> PermissionType.RECEIVE_MMS

            PermissionUri.RECEIVE_WAP_PUSH.requestUri -> PermissionType.RECEIVE_WAP_PUSH

            PermissionUri.USE_SIP.requestUri -> PermissionType.USE_SIP

            PermissionUri.READ_PHONE_STATE.requestUri -> PermissionType.READ_PHONE_STATE

            PermissionUri.CALL_PHONE.requestUri -> PermissionType.CALL_PHONE

            PermissionUri.READ_CALL_LOG.requestUri -> PermissionType.READ_CALL_LOG

            PermissionUri.WRITE_CALL_LOG.requestUri -> PermissionType.WRITE_CALL_LOG

            else -> null
        }

    }

    /**
     * <Use of method>
     * <!--
     * Method will use to handle denied state of requested permissions
     * -->
     * @param permissions list of requested permission
     * @param iCallBack interface instance to trigger callbacks
     * @param activity activity reference to check rational state
     * @param permissionType requested permission type
     */
    private fun handlePermissionDenied(permissionType: PermissionType, permissions: Array<String>, iCallBack: IPermissionCallbacks?, activity: Activity) {
        if(!isShowRationalPermission(permissions,activity) && isCheckForAlwaysHide) {
            Log.d(MakePermissionRequest.TAG, permissionType.name+ " is in always hide user clicks on don't ask me again")
            iCallBack?.onShowRationalPermissionDialog(permissionType, true)//don't ask me again state
        }else {
            Log.d(MakePermissionRequest.TAG, permissionType.name+ " has denied")
            iCallBack?.onPermissionDenied(permissionType)//denied
        }
    }

    /**
     * <Use of method>
     * <!--
     * Method will use to check rational state of requested permission
     * -->
     * @param permissions list of requested permission
     * @param activity activity reference to check rational state
     * @return
     */
    private fun isShowRationalPermission(permissions: Array<String>, activity: Activity):Boolean {

        var showRationale = false
        for (permission in permissions) {
            if (activity.shouldShowRequestPermissionRationale(permission)) {
                showRationale = true
                break
            }
        }
        return showRationale
    }


    /**
     * <Use of method>
     * <!--
     * Method will use to handle permission result
     * -->
     * @param grantResults list of granted result
     * @param permissions list of requested permission
     * @param iCallBack interface instance to trigger callbacks
     * @param activity activity reference to check rational state
     * @param permissionType requested permission type
     */
    private fun handlePermissionResult(
            grantResults: IntArray,
            permissions: Array<String>,
            iCallBack: IPermissionCallbacks?,
            activity: Activity, permissionType: PermissionType

    ) {
        if(grantResults.size>0)
        {
            var grantedResultCount=0
            for( a in grantResults)
            {
                if(grantResults[0]== PackageManager.PERMISSION_GRANTED)
                    grantedResultCount++
            }
            if(grantedResultCount==grantResults.size)
            {
                //permissionn granted
                iCallBack?.onPermissionGranted(permissionType)
                Log.d(MakePermissionRequest.TAG, "Permissions granted")

            }else
                handlePermissionDenied(permissionType, permissions, iCallBack, activity)
        }else{
            handlePermissionDenied(permissionType, permissions, iCallBack, activity)
        }
    }


}