# Android_Runtime_Permission

This projects is use to take Android runtime permissions

Easy to manage runtime permissions in android.

Very short code to request for runtime permisson 

Hanlded "don't ask again" and Rational condition for any type of runtime permissions.

Can request from any context (Activity, Service, Fragment, etc).

Developer can also check multiple permissions at once. 

Handled "don't akk again" for multiple permission if all the requested permission is denied and in "don't ask again" state

Written in Kotlin 

Support permission updates from Android 11 

Supports major runtime permission like : **CAMERA, READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE , ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION , READ_CALENDAR, WRITE_CALENDAR , READ_CONTACTS, WRITE_CONTACTS, GET_ACCOUNTS, RECORD_AUDIO , SEND_SMS, READ_SMS, RECEIVE_SMS, RECEIVE_WAP_PUSH, RECEIVE_MMS, BODY_SENSORS, READ_PHONE_STATE, CALL_PHONE, READ_CALL_LOG, WRITE_CALL_LOG, ADD_VOICEMAIL, USE_SIP
**   


Steps to use this library 

STEP 1: Add permissions in Manifest.xml 

       <uses-permission android:name="android.permission.CAMERA" />

STEP 2: Register callback listner in OnCreate() of your Activity or Fragment or From the file where you want to get the callbacks and don't forgot to set is as null 
with the same reference from you have registed it when your activity or fragments gets destroyed

        MakePermissionRequest.getInstance().registerPermissionCallback(this)
 
        MakePermissionRequest.getInstance().registerPermissionCallback(null)   

STEP 3: Override callbacks methods and handle your logic accordingly 


    override fun onPermissionGranted(permissionType: PermissionType?) {
        //Todo handle permission granted
    }

    override fun onPermissionDenied(permissionType: PermissionType?) {
      //Todo handle permission denied
    }

    override fun onMultiplePermissionResult(isGranted: Boolean, grantedPermissionList: List<PermissionType>?,
                                            deniedPermissionList: List<PermissionType>?, isAlwaysHide: Boolean) {
     //Todo handle multiple permission results                                        
    }

    override fun onShowRationalPermissionDialog(permissionType: PermissionType, isAlwaysHide: Boolean) {
     //Todo handle permission rational state i.e show custom dialog before asking permission second time or to show custom dialog before moving to settings if isAlwaysHide is true
    }

STEP 4: Initiate permission request like below 

     MakePermissionRequest.getInstance().requestForPermission(PermissionType.CAMERA, this, true)
 


To handle multiple request at a once follow the same steps from STEP 1 to STEP 3 and make request like below 

 STEP 4:    
           
           val requestType = arrayOf(PermissionType.CAMERA, PermissionType.CONTACTS_GROUP, PermissionType.STORAGE_GROUP, PermissionType.SMS_GROUP)
          
            MakePermissionRequest.getInstance().requestForPermission(requestType, this, true)
         


STEP 5: In your Activity or Fragment override onRequestPermissionsResult method to handle result and Call MakePermissionRequest method onRequestPermissionsResult
        like below
           

           override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        MakePermissionRequest.getInstance().onRequestPermissionsResult(requestCode, permissions as Array<String>,grantResults,this)
    }

Open source
