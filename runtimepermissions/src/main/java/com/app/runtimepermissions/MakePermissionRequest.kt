package com.app.runtimepermissions

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import android.util.Log
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat
import com.app.runtimepermission.PermissionResultHelper
import com.app.runtimepermission.constant.PermissionUri
import com.app.runtimepermissions.Util.PermissionNotAddedInManifest
import com.app.runtimepermissions.constant.PermissionCode
import com.app.runtimepermissions.constant.PermissionType
import com.app.runtimepermissions.interfaces.IPermissionCallbacks


/**
 * <Use of Class>
 * <!--
 *  This is Singlton class , used to generate permission request and handle its result
 *
 *  @see PermissionResultHelper this is base class of #PermissionUtil in which we are handling permission result
 *
 *  To handle the callback from this class, call #registerPermissionCallback(callbacks: IPermissionCallbacks?)
 *
 *  Don't forgot to call , #registerPermissionCallback(callbacks: IPermissionCallbacks?)
 *  with null value to prevent from memory leak when our activity or fragment destroyed
 * -->
 */
class MakePermissionRequest:PermissionResultHelper() {

    //IPermissionCallbacks object reference to handle callback
    private var iCallBack: IPermissionCallbacks?=null



    //creating singleton object of this class
    companion object {
        val TAG=MakePermissionRequest::class.simpleName
        const val SETTINGS_INTENT_REQUEST_CODE = 0X5000

        //declaring instance variable of this class as volatile for thread safety
        @Volatile private var instance: MakePermissionRequest? = null

        /**
         * <Use of method>
         * <!--
         * This method is used to initialize and return the instance of this class
         * -->
         */
        fun getInstance() = instance ?: synchronized(this) { instance ?: MakePermissionRequest().also{
            //   PermissionResultUtil.getInstance()
            Log.d(TAG, "instanse created for " + MakePermissionRequest::class.simpleName)
            instance = it } }
    }

    /**
     * <Use of method>
     * <!--
     * This method is used to register listener to handle callbacks from this class
     * -->
     * @param callbacks //reference of listener , value should be null or reference of listener class
     */
    fun registerPermissionCallback(callbacks: IPermissionCallbacks?)=iCallBack.also { iCallBack=callbacks }

    /**
     * <Use of method>
     * <!--
     * This method is used to initiate request for runtime permission
     * -->
     * @param permissionType //this is first parameter that shows request type
     * @param activity //second parameter holds activity reference and used to request runtime permission
     *
     * Below parameter is used show some custom dialog to user when permission is in rational state
     * pass this parameter value as false when user takes positive action from the custom UI to take permission
     * @param isCheckForRationState //value should be true to show custom UI in permission rational state else false
     *
     * @logic
     * <!--
     * In this method we are generating permission uri and permission request code according to requested permission type
     * @see PermissionType
     * -->
     */
    @Throws(PermissionNotAddedInManifest::class)
    fun requestForPermission(permissionType: PermissionType, activity: Activity, isCheckForRationState: Boolean)
    {
        isCheckForAlwaysHide=false
        var requestCode:Int?=null

        //getting permission uri
        val permission=  when(permissionType)
        {
            PermissionType.CAMERA -> {
                requestCode = PermissionCode.CAMERA.requestCode
                initiatePermissionRequest(PermissionUri.CAMERA.requestUri, activity, PermissionType.CAMERA, isCheckForRationState)
            }
            PermissionType.LOCATION_GROUP -> {
                requestCode = PermissionCode.LOCATION_GROUP.requestCode
                initiatePermissionRequest(arrayOf(PermissionUri.ACCESS_COARSE_LOCATION.requestUri,
                        PermissionUri.ACCESS_FINE_LOCATION.requestUri), activity, PermissionType.LOCATION_GROUP, isCheckForRationState)
            }

            PermissionType.STORAGE_GROUP -> {
                requestCode = PermissionCode.STORAGE_GROUP.requestCode
                initiatePermissionRequest(arrayOf(PermissionUri.READ_STORAGE.requestUri,
                        PermissionUri.WRITE_STORAGE.requestUri), activity, PermissionType.STORAGE_GROUP, isCheckForRationState)
            }

            PermissionType.READ_STORAGE -> {
                requestCode = PermissionCode.READ_STORAGE.requestCode
                initiatePermissionRequest(PermissionUri.READ_STORAGE.requestUri, activity, PermissionType.READ_STORAGE, isCheckForRationState)
            }

            PermissionType.WRITE_STORAGE -> {
                requestCode = PermissionCode.WRITE_STORAGE.requestCode
                initiatePermissionRequest(PermissionUri.WRITE_STORAGE.requestUri, activity, PermissionType.WRITE_STORAGE, isCheckForRationState)
            }

            PermissionType.CALENDER_GROUP -> {
                requestCode = PermissionCode.CALENDER_GROUP.requestCode
                initiatePermissionRequest(arrayOf(PermissionUri.READ_CALENDAR.requestUri,
                        PermissionUri.WRITE_CALENDAR.requestUri), activity, PermissionType.CALENDER_GROUP, isCheckForRationState)
            }

            PermissionType.READ_CALENDAR -> {
                requestCode = PermissionCode.READ_CALENDAR.requestCode
                initiatePermissionRequest(PermissionUri.READ_CALENDAR.requestUri, activity, PermissionType.READ_CALENDAR, isCheckForRationState)
            }

            PermissionType.WRITE_CALENDAR -> {
                requestCode = PermissionCode.WRITE_CALENDAR.requestCode
                initiatePermissionRequest(PermissionUri.WRITE_CALENDAR.requestUri, activity, PermissionType.WRITE_CALENDAR, isCheckForRationState)
            }

            PermissionType.CONTACTS_GROUP -> {
                requestCode = PermissionCode.CONTACTS_GROUP.requestCode
                initiatePermissionRequest(arrayOf(PermissionUri.READ_CONTACTS.requestUri,
                        PermissionUri.WRITE_CONTACTS.requestUri, PermissionUri.GET_ACCOUNTS.requestUri),
                        activity, PermissionType.CONTACTS_GROUP, isCheckForRationState)
            }


            PermissionType.READ_CONTACTS -> {
                requestCode = PermissionCode.READ_CONTACTS.requestCode
                initiatePermissionRequest(PermissionUri.READ_CONTACTS.requestUri, activity, PermissionType.READ_CONTACTS, isCheckForRationState)
            }


            PermissionType.WRITE_CONTACTS -> {
                requestCode = PermissionCode.WRITE_CONTACTS.requestCode
                initiatePermissionRequest(PermissionUri.WRITE_CONTACTS.requestUri, activity, PermissionType.WRITE_CONTACTS, isCheckForRationState)
            }

            PermissionType.GET_ACCOUNT -> {
                requestCode = PermissionCode.GET_ACCOUNT.requestCode
                initiatePermissionRequest(PermissionUri.GET_ACCOUNTS.requestUri, activity, PermissionType.GET_ACCOUNT, isCheckForRationState)
            }


            PermissionType.SMS_GROUP -> {
                requestCode = PermissionCode.SMS_GROUP.requestCode
                initiatePermissionRequest(arrayOf(PermissionUri.READ_SMS.requestUri,
                        PermissionUri.SEND_SMS.requestUri,
                        PermissionUri.RECEIVE_MMS.requestUri,
                        PermissionUri.RECEIVE_SMS.requestUri,
                        PermissionUri.RECEIVE_WAP_PUSH.requestUri), activity, PermissionType.SMS_GROUP, isCheckForRationState)
            }

            PermissionType.SMS -> {
                requestCode = PermissionCode.SMS.requestCode
                initiatePermissionRequest(arrayOf(PermissionUri.READ_SMS.requestUri,
                        PermissionUri.SEND_SMS.requestUri), activity, PermissionType.SMS, isCheckForRationState)
            }

            PermissionType.SEND_SMS -> {
                requestCode = PermissionCode.SEND_SMS.requestCode
                initiatePermissionRequest(PermissionUri.SEND_SMS.requestUri, activity, PermissionType.SEND_SMS, isCheckForRationState)
            }

            PermissionType.READ_SMS -> {
                requestCode = PermissionCode.READ_SMS.requestCode
                initiatePermissionRequest(PermissionUri.READ_SMS.requestUri, activity, PermissionType.READ_SMS, isCheckForRationState)
            }

            PermissionType.RECEIVE_MMS -> {
                requestCode = PermissionCode.RECEIVE_MMS.requestCode
                initiatePermissionRequest(PermissionUri.RECEIVE_MMS.requestUri, activity, PermissionType.RECEIVE_MMS, isCheckForRationState)
            }

            PermissionType.RECEIVE_SMS -> {
                requestCode = PermissionCode.RECEIVE_SMS.requestCode
                initiatePermissionRequest(PermissionUri.RECEIVE_SMS.requestUri, activity, PermissionType.RECEIVE_SMS, isCheckForRationState)
            }

            PermissionType.RECEIVE_WAP_PUSH -> {
                requestCode = PermissionCode.RECEIVE_WAP_PUSH.requestCode
                initiatePermissionRequest(PermissionUri.RECEIVE_WAP_PUSH.requestUri, activity, PermissionType.RECEIVE_WAP_PUSH, isCheckForRationState)
            }

            PermissionType.RECORD_AUDIO -> {
                requestCode = PermissionCode.RECORD_AUDIO.requestCode
                initiatePermissionRequest(PermissionUri.RECORD_AUDIO.requestUri, activity, PermissionType.RECORD_AUDIO, isCheckForRationState)
            }

            PermissionType.PHONE_GROUP -> {
                requestCode=PermissionCode.PHONE_GROUP.requestCode
                initiatePermissionRequest(arrayOf(PermissionUri.CALL_PHONE.requestUri,
                        PermissionUri.USE_SIP.requestUri,
                        PermissionUri.READ_PHONE_STATE.requestUri,
                        PermissionUri.READ_CALL_LOG.requestUri,
                        PermissionUri.WRITE_CALL_LOG.requestUri), activity, PermissionType.PHONE_GROUP, isCheckForRationState)
            }

            PermissionType.CALL_LOG_GROUP -> {
                requestCode = PermissionCode.CALL_LOG_GROUP.requestCode
                initiatePermissionRequest(arrayOf(PermissionUri.READ_CALL_LOG.requestUri,
                        PermissionUri.WRITE_CALL_LOG.requestUri), activity, PermissionType.CALL_LOG_GROUP, isCheckForRationState)
            }

            PermissionType.CALL_PHONE -> {
                requestCode = PermissionCode.CALL_PHONE.requestCode
                initiatePermissionRequest(PermissionUri.CALL_PHONE.requestUri, activity, PermissionType.CALL_PHONE, isCheckForRationState)
            }

            PermissionType.USE_SIP -> {
                requestCode = PermissionCode.USE_SIP.requestCode
                initiatePermissionRequest(PermissionUri.USE_SIP.requestUri, activity, PermissionType.USE_SIP, isCheckForRationState)
            }

            PermissionType.READ_PHONE_STATE -> {
                requestCode = PermissionCode.READ_PHONE_STATE.requestCode
                initiatePermissionRequest(PermissionUri.READ_PHONE_STATE.requestUri, activity, PermissionType.READ_PHONE_STATE, isCheckForRationState)
            }

            PermissionType.READ_CALL_LOG -> {
                requestCode = PermissionCode.READ_CALL_LOG.requestCode
                initiatePermissionRequest(PermissionUri.READ_CALL_LOG.requestUri, activity, PermissionType.READ_CALL_LOG, isCheckForRationState)
            }

            PermissionType.WRITE_CALL_LOG -> {
                requestCode = PermissionCode.WRITE_CALL_LOG.requestCode
                initiatePermissionRequest(PermissionUri.WRITE_CALL_LOG.requestUri, activity, PermissionType.WRITE_CALL_LOG, isCheckForRationState)
            }

            PermissionType.SENSOR -> {
                requestCode = PermissionCode.SENSOR.requestCode
                initiatePermissionRequest(PermissionUri.SENSOR.requestUri, activity, PermissionType.SENSOR, isCheckForRationState)
            }

        }
        //below code will throw error when requested permissions uri are not added in manifest, user will required to handle it in try catch block

        if(permission!=null )
        {
            val notDeclaredPermissions=isPemissionAddedInManifest(permission,activity)
            if(notDeclaredPermissions==null || notDeclaredPermissions.size>0) {
                var notFoundPermission = ""
                notDeclaredPermissions?.iterator()?.forEach {
                    notFoundPermission = notFoundPermission + it + "\n"
                }
                throw PermissionNotAddedInManifest("Requested permissions are not added in manifest Please add following permission in you manifest \n" +notFoundPermission)
            }
        }
        //getting permission request code according to  permission type
        permission?.let { permissions->requestCode.let { takePermission(permissions, it, activity) }}
    }


    /**
     * <Use of method>
     * <!--
     * This method is used to initiate multiple runtime permission
     * -->
     * @param permissionType //this is first parameter that shows array of request type
     * @param activity //second parameter holds activity reference and used to request runtime permission
     *
     * Below parameter is used show some custom dialog to user when permission is in rational state
     * pass this parameter value as false when user takes positive action from the custom UI to take permission
     * @param isCheckForRationState //value should be true to show custom UI in permission rational state else false (it will directly request for permission without checking rational state)
     *
     * @logic
     * <!--
     * In this method we are generating permission uri and permission request code according to requested permission type
     * @see PermissionType
     * -->
     *
     * Before calling this method just make sure you have added permission in your app manifest file otherwise it will throw error
     */
    @Throws(PermissionNotAddedInManifest::class)
    fun requestForPermission(permissionType: Array<PermissionType>, activity: Activity, isCheckForRationState: Boolean)
    {
        isCheckForAlwaysHide=false

        if(permissionType.size==0) return
        if(permissionType.size==1)
        {
            requestForPermission(permissionType.get(0), activity, isCheckForRationState)
            return
        }

        val permissionUri = arrayListOf<String>()

        for(item in permissionType)
        {
            //getting permission uri
            val permission=  when(item)
            {
                PermissionType.CAMERA -> addMultiplePermissionUri(PermissionUri.CAMERA.requestUri, activity)

                PermissionType.LOCATION_GROUP -> {
                    addMultiplePermissionUri(PermissionUri.ACCESS_COARSE_LOCATION.requestUri, activity)
                    addMultiplePermissionUri(PermissionUri.ACCESS_FINE_LOCATION.requestUri, activity)
                }

                PermissionType.STORAGE_GROUP -> {
                    addMultiplePermissionUri(PermissionUri.READ_STORAGE.requestUri, activity)
                    addMultiplePermissionUri(PermissionUri.WRITE_STORAGE.requestUri, activity)
                }

                PermissionType.READ_STORAGE -> {
                    addMultiplePermissionUri(PermissionUri.READ_STORAGE.requestUri, activity)
                }

                PermissionType.WRITE_STORAGE -> {
                    addMultiplePermissionUri(PermissionUri.WRITE_STORAGE.requestUri, activity)
                }

                PermissionType.CALENDER_GROUP -> {
                    addMultiplePermissionUri(PermissionUri.READ_CALENDAR.requestUri, activity)
                    addMultiplePermissionUri(PermissionUri.WRITE_CALENDAR.requestUri, activity)
                }

                PermissionType.READ_CALENDAR -> {
                    addMultiplePermissionUri(PermissionUri.READ_CALENDAR.requestUri, activity)
                }

                PermissionType.WRITE_CALENDAR -> {
                    addMultiplePermissionUri(PermissionUri.WRITE_CALENDAR.requestUri, activity)
                }

                PermissionType.CONTACTS_GROUP -> {
                    addMultiplePermissionUri(PermissionUri.READ_CONTACTS.requestUri, activity)
                    addMultiplePermissionUri(PermissionUri.WRITE_CONTACTS.requestUri, activity)
                    addMultiplePermissionUri(PermissionUri.GET_ACCOUNTS.requestUri, activity)
                }

                PermissionType.READ_CONTACTS -> {
                    addMultiplePermissionUri(PermissionUri.READ_CONTACTS.requestUri, activity)
                }

                PermissionType.WRITE_CONTACTS -> {
                    addMultiplePermissionUri(PermissionUri.WRITE_CONTACTS.requestUri, activity)
                }

                PermissionType.GET_ACCOUNT -> {
                    addMultiplePermissionUri(PermissionUri.GET_ACCOUNTS.requestUri, activity)
                }

                PermissionType.SMS_GROUP -> {
                    addMultiplePermissionUri(PermissionUri.READ_SMS.requestUri, activity)
                    addMultiplePermissionUri(PermissionUri.SEND_SMS.requestUri, activity)
                    addMultiplePermissionUri(PermissionUri.RECEIVE_MMS.requestUri, activity)
                    addMultiplePermissionUri(PermissionUri.RECEIVE_SMS.requestUri, activity)
                    addMultiplePermissionUri(PermissionUri.RECEIVE_WAP_PUSH.requestUri, activity)
                }

                PermissionType.SMS -> {
                    addMultiplePermissionUri(PermissionUri.READ_SMS.requestUri, activity)
                    addMultiplePermissionUri(PermissionUri.SEND_SMS.requestUri, activity)
                }

                PermissionType.READ_SMS -> {
                    addMultiplePermissionUri(PermissionUri.READ_SMS.requestUri, activity)
                }

                PermissionType.SEND_SMS -> {
                    addMultiplePermissionUri(PermissionUri.SEND_SMS.requestUri, activity)
                }

                PermissionType.RECEIVE_MMS -> {
                    addMultiplePermissionUri(PermissionUri.RECEIVE_MMS.requestUri, activity)
                }

                PermissionType.RECEIVE_SMS -> {
                    addMultiplePermissionUri(PermissionUri.RECEIVE_SMS.requestUri, activity)
                }

                PermissionType.RECEIVE_WAP_PUSH -> {
                    addMultiplePermissionUri(PermissionUri.RECEIVE_WAP_PUSH.requestUri, activity)
                }

                PermissionType.PHONE_GROUP -> {
                    addMultiplePermissionUri(PermissionUri.USE_SIP.requestUri, activity)
                    addMultiplePermissionUri(PermissionUri.READ_PHONE_STATE.requestUri, activity)
                    addMultiplePermissionUri(PermissionUri.CALL_PHONE.requestUri, activity)
                    addMultiplePermissionUri(PermissionUri.READ_CALL_LOG.requestUri, activity)
                    addMultiplePermissionUri(PermissionUri.WRITE_CALL_LOG.requestUri, activity)
                }

                PermissionType.CALL_LOG_GROUP -> {
                    addMultiplePermissionUri(PermissionUri.READ_CALL_LOG.requestUri, activity)
                    addMultiplePermissionUri(PermissionUri.WRITE_CALL_LOG.requestUri, activity)
                }

                PermissionType.USE_SIP -> {
                    addMultiplePermissionUri(PermissionUri.USE_SIP.requestUri, activity)
                }

                PermissionType.READ_PHONE_STATE -> {
                    addMultiplePermissionUri(PermissionUri.READ_PHONE_STATE.requestUri, activity)
                }


                PermissionType.CALL_PHONE -> {
                    addMultiplePermissionUri(PermissionUri.CALL_PHONE.requestUri, activity)
                }

                PermissionType.READ_CALL_LOG -> {
                    addMultiplePermissionUri(PermissionUri.READ_CALL_LOG.requestUri, activity)
                }


                PermissionType.WRITE_CALL_LOG -> {
                    addMultiplePermissionUri(PermissionUri.WRITE_CALL_LOG.requestUri, activity)
                }

                PermissionType.SENSOR -> addMultiplePermissionUri(PermissionUri.SENSOR.requestUri, activity)


                PermissionType.RECORD_AUDIO -> addMultiplePermissionUri(PermissionUri.RECORD_AUDIO.requestUri, activity)

            }
            permission?.let { permissionUri.add(it)}
        }

        //below code will throw error when requested permissions uri are not added in manifest, user will required to handle it in try catch block
        if(permissionUri.size>0)
        {
            val notDeclaredPermissions=isPemissionAddedInManifest(permissionUri.toTypedArray(),activity)
            if(notDeclaredPermissions==null || notDeclaredPermissions.size>0) {
                var notFoundPermission = ""
                notDeclaredPermissions?.iterator()?.forEach {
                    notFoundPermission = notFoundPermission + it + "\n"
                }
                throw PermissionNotAddedInManifest("Requested permissions are not added in manifest Please add following permission in you manifest \n" +notFoundPermission)
            }
        }

        val requestCode=PermissionCode.MULTIPLE_PERMISSION_REQUEST_CODE.requestCode
        if(permissionUri.size>0) {
            val finalRequest = permissionUri.toTypedArray();
            takePermission(finalRequest, requestCode, activity)
        }else{
            Log.d(TAG, "Permissions granted")
            iCallBack?.onMultiplePermissionResult(true, permissionType.toList(), null, false)
        }
    }

    private fun addMultiplePermissionUri(requestUri: String, activity: Activity): String? {
        if(isPermissionGranted(requestUri, activity))return null
        return requestUri
    }


    /**
     * <Use of method>
     * <!--
     * This method is used to generate permission request
     * -->
     * @param permissionUri //uri for requested permission
     * @param activity //activity reference to initiate permission request
     * @param permissionType // it define type of request permission
     * @see PermissionType
     * @param isCheckForRationState //true if user wants to check rational state else false
     * @return //null if permission granted or in rational state otherwise it will return permission request uri
     */
    private fun initiatePermissionRequest(permissionUri: String, activity: Activity, permissionType: PermissionType, isCheckForRationState: Boolean):Array<String>? {
        isCheckForAlwaysHide=!isShowRationPermission(permissionUri, activity)

        if (isPermissionGranted(permissionUri, activity)) {
            //already have permission
            iCallBack?.onPermissionGranted(permissionType)
            Log.d(TAG, permissionUri+" permissions granted ")
            return null
        } else if (isCheckForRationState && isShowRationPermission(permissionUri, activity)) {
            //permission is in rational state i.e. user already denied it first time
            Log.d(TAG, permissionUri+" is in rational state")
            iCallBack?.onShowRationalPermissionDialog(permissionType, false)
            return null
        } else //returning permission uri object
            return arrayOf(permissionUri)
    }


    /**
     * <Use of method>
     * <!--
     * This method is used to generate permission request
     * -->
     * @param permissionUri //list of permission uri for requested permission
     * @param activity //activity reference to initiate permission request
     * @param permissionType // it define type of request permission
     * @see PermissionType
     * @param isCheckForRationState //true if user wants to check rational state else false
     * @return //null if permission granted or in rational state otherwise it will return permission request uri
     *
     */
    private fun initiatePermissionRequest(permissionUri: Array<String>, activity: Activity, permissionType: PermissionType, isCheckForRationState: Boolean):Array<String>? {

        //loop to check permission is always hide state i.e (user clicks don't ask me again with denied action)
        for(item in permissionUri) {
            isCheckForAlwaysHide = !isShowRationPermission(item, activity)
            if (!isCheckForRationState)
                isCheckForAlwaysHide = !isShowRationPermission(item, activity)
            else
                break
        }

        //loop to check requested permission is already granted or not
        var isPermissionGranted=true
        for(item in permissionUri) {
            if(!isPermissionGranted(item, activity))
            {
                isPermissionGranted=false
                break
            }
        }

        //loop to check rational state of each requested permission
        var isShowRational=true
        for(item in permissionUri) {
            if(!isShowRationPermission(item, activity))
            {
                isShowRational=false
            }
        }

        if(isPermissionGranted)
        {
            //already have all the permissions
            iCallBack?.onPermissionGranted(permissionType)
            Log.d(TAG, "all permission has granted")

            return null
        }else if(isCheckForRationState && isShowRational)
        {
            //some of requested permision is in ratioal state
            iCallBack?.onShowRationalPermissionDialog(permissionType, false)
            Log.d(TAG, permissionType.name+ "Permission is in rational state")
            return null
        }
        else //returning permissions uri object
            return permissionUri

    }


    /**
     * <Use of method>
     *  <!--
     *  This method will used to check rational state of requested permission
     *  -->
     *  @param permissions //uri of requested permission
     *  @param activity //activity reference to check rational state
     *  @return true if in rational state otherwise false
     */
    private fun isShowRationPermission(permissions: String, activity: Activity): Boolean =activity.shouldShowRequestPermissionRationale(permissions)


    /**
     * <Use of method>
     * <!--
     * This method will used to check grant state of requested permission
     * -->
     *  @param permissions //uri of requested permission
     *  @param activity //activity reference to check grant state
     *  @return true if have permission otherwise else
     */
    private fun isPermissionGranted(permissions: String, activity: Activity) :Boolean = ContextCompat.checkSelfPermission(activity, permissions) == PackageManager.PERMISSION_GRANTED



    /**
     * <Use of method>
     * <!--
     * Method will use to request runtime permission
     * -->
     * @param permissions //uri of requested permissions
     * @param permissionCode // request code to identify request type
     * @param activity //activity reference to initiate permission request
     */
    private fun takePermission(permissions: Array<String>, permissionCode: Int, activity: Activity)=activity.requestPermissions(permissions, permissionCode)



    /**
     * < Use of method>
     *  <!--
     *  This method will call when we received runtime permission callback from activity
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
     */
    fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<String>,
            grantResults: IntArray,
            activity: Activity
    ) {
        onRequestPermissionsResult(requestCode, permissions, grantResults, iCallBack, activity)
    }


    /**
     * <Use of method>
     * <!--
     * Method will call to open app permission settings to allow permission manually when permission is in don't ask me again state
     * -->
     * @param activity //activity reference to fetch package name and to launch settings activity
     */
    fun openSettings(activity: Activity)
    {
        val intent = Intent()
        intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        val uri = Uri.fromParts("package", activity.packageName, null)
        intent.data = uri
        activity.startActivityForResult(intent, SETTINGS_INTENT_REQUEST_CODE)
    }


    /**
     * <Use of <Method>
     * <!--
     * This method is used to check requested permissions are added in manifest or not as it will required to add runtime permission in app manifest also
     * -->
     */
    fun isPemissionAddedInManifest(requestedPermissions: Array<String>,activity: Activity): Array<String>?
    {
        val packageInfo=activity.packageManager.getPackageInfo(activity.packageName,PackageManager.GET_PERMISSIONS)
        val permission=packageInfo.requestedPermissions
        if(permission==null || permission.isEmpty())
            return null
        requestedPermissions.filter {request-> !permission.contains(request) }.also {
            return it.toTypedArray()
        }
    }
}