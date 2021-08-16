package com.app.permissions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.app.runtimepermissions.MakePermissionRequest
import com.app.runtimepermissions.constant.PermissionType
import com.app.runtimepermissions.interfaces.IPermissionCallbacks
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), IPermissionCallbacks {

    lateinit var permissionUtil:MakePermissionRequest

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        permissionUtil = MakePermissionRequest.getInstance()
        permissionUtil.registerPermissionCallback(this)

        camera.setOnClickListener({
            permissionUtil.requestForPermission(PermissionType.CAMERA, this, true)
        })

        location.setOnClickListener({
            permissionUtil.requestForPermission(PermissionType.LOCATION_GROUP, this, true)
        })


        audio.setOnClickListener({
            permissionUtil.requestForPermission(PermissionType.RECORD_AUDIO, this, true)

        })

        bodysensor.setOnClickListener({
            permissionUtil.requestForPermission(PermissionType.SENSOR, this, true)
        })

        storage.setOnClickListener({
            permissionUtil.requestForPermission(PermissionType.STORAGE_GROUP, this, true)
        })

        rstorage.setOnClickListener({
            permissionUtil.requestForPermission(PermissionType.READ_STORAGE, this, true)
        })

        wstorage.setOnClickListener({
            permissionUtil.requestForPermission(PermissionType.WRITE_STORAGE, this, true)
        })

        calender.setOnClickListener({
            permissionUtil.requestForPermission(PermissionType.CALENDER_GROUP, this, true)
        })


        rcalendar.setOnClickListener({
            permissionUtil.requestForPermission(PermissionType.READ_CALENDAR, this, true)
        })

        wcalendar.setOnClickListener({
            permissionUtil.requestForPermission(PermissionType.WRITE_CALENDAR, this, true)
        })

        contact.setOnClickListener({
            permissionUtil.requestForPermission(PermissionType.CONTACTS_GROUP, this, true)
        })

        gaccounts.setOnClickListener({
            permissionUtil.requestForPermission(PermissionType.GET_ACCOUNT, this, true)
        })

        rcontact.setOnClickListener({
            permissionUtil.requestForPermission(PermissionType.READ_CONTACTS, this, true)
        })

        wcontact.setOnClickListener({
            permissionUtil.requestForPermission(PermissionType.WRITE_CONTACTS, this, true)
        })

        smsgroup.setOnClickListener({
            permissionUtil.requestForPermission(PermissionType.SMS_GROUP, this, true)
        })

        sms.setOnClickListener({
            permissionUtil.requestForPermission(PermissionType.SMS, this, true)
        })

        sendsms.setOnClickListener({
            permissionUtil.requestForPermission(PermissionType.SEND_SMS, this, true)
        })


        rsms.setOnClickListener({
            permissionUtil.requestForPermission(PermissionType.READ_SMS, this, true)
        })

        receivesms.setOnClickListener({
            permissionUtil.requestForPermission(PermissionType.RECEIVE_SMS, this, true)
        })

        receive_mms.setOnClickListener({
            permissionUtil.requestForPermission(PermissionType.RECEIVE_MMS, this, true)
        })

        phonegroup.setOnClickListener({
            permissionUtil.requestForPermission(PermissionType.PHONE_GROUP, this, true)
        })

        sip.setOnClickListener({
            permissionUtil.requestForPermission(PermissionType.USE_SIP, this, true)
        })

        call_phone.setOnClickListener({
            permissionUtil.requestForPermission(PermissionType.CALL_PHONE, this, true)
        })


        readcalllog.setOnClickListener({
            permissionUtil.requestForPermission(PermissionType.READ_CALL_LOG, this, true)
        })

        writecalllog.setOnClickListener({
            permissionUtil.requestForPermission(PermissionType.WRITE_CALL_LOG, this, true)
        })


        readphonestate.setOnClickListener({
            permissionUtil.requestForPermission(PermissionType.READ_PHONE_STATE, this, true)
        })

        add_voice_mail.setOnClickListener({
            permissionUtil.requestForPermission(PermissionType.ADD_VOICE_MAIL, this, true)
        })

        multi.setOnClickListener({
            val requestType = arrayOf(PermissionType.CAMERA, PermissionType.CONTACTS_GROUP, PermissionType.STORAGE_GROUP, PermissionType.SMS_GROUP)
            permissionUtil.requestForPermission(requestType, this, false)
        })

        receivewap.setOnClickListener({
            permissionUtil.requestForPermission(PermissionType.RECEIVE_WAP_PUSH, this, true)
        })

        call_log_group.setOnClickListener({
            permissionUtil.requestForPermission(PermissionType.CALL_LOG_GROUP, this, true)
        })






    }


    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        permissionUtil.onRequestPermissionsResult(requestCode, permissions as Array<String>,grantResults,this)
    }

    override fun onPermissionGranted(permissionType: PermissionType?) {
        Toast.makeText(this,"Granted "+permissionType+" Permission",Toast.LENGTH_SHORT).show()
    }

    override fun onPermissionDenied(permissionType: PermissionType?) {
        Toast.makeText(this,"Denied "+permissionType+" Permission",Toast.LENGTH_SHORT).show()
    }

    override fun onMultiplePermissionResult(isGranted: Boolean, grantedPermissionList: List<PermissionType>?,
                                            deniedPermissionList: List<PermissionType>?, isAlwaysHide: Boolean) {
        if(isGranted)
            Toast.makeText(this,"Multiple "+" Permission has Granted",Toast.LENGTH_SHORT).show()
        else {
            if(isAlwaysHide)
            {
                Toast.makeText(this, "Permission always hide Open Settings", Toast.LENGTH_SHORT).show()
                permissionUtil.openSettings(this@MainActivity)

            }else {
                deniedPermissionList?.let {
                    Toast.makeText(this, "Some of Multiple " + " Permission has Denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onShowRationalPermissionDialog(permissionType: PermissionType, isAlwaysHide: Boolean) {
        if(isAlwaysHide)
        {
            Toast.makeText(this,"Always hide  "+permissionType+" Permission Open Settings",Toast.LENGTH_SHORT).show()
            permissionUtil.openSettings(this@MainActivity)
        }else {
            Toast.makeText(this, "show rational  " + permissionType + " Permission", Toast.LENGTH_SHORT).show()
            permissionUtil.requestForPermission(permissionType,this,false)
        }
    }

}