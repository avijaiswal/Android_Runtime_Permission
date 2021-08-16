package com.app.runtimepermission.constant

import android.Manifest

/**
 * <Use of class>
 *  <!--
 *  This is enum class that contains permission uri according to Permission type
 *  -->
 */
enum class PermissionUri(val requestUri: String) {

    //enum constant to get CAMERA permission uri
    CAMERA(Manifest.permission.CAMERA),

    /**
     * enum constant to get READ_EXTERNAL_STORAGE permission uri
     * This permission uri  comes under group of STORAGE permission
     */
    READ_STORAGE(Manifest.permission.READ_EXTERNAL_STORAGE),

    /**
     * enum constant to get WRITE_EXTERNAL_STORAGE permission uri
     * This permission uri  comes under group of STORAGE permission
     */
    WRITE_STORAGE(Manifest.permission.WRITE_EXTERNAL_STORAGE),

    /**
     * enum constant to get ACCESS_COARSE_LOCATION permission uri
     * This permission uri  comes under group of Location permission
     */
    ACCESS_COARSE_LOCATION(Manifest.permission.ACCESS_COARSE_LOCATION),

    /**
     * enum constant to get ACCESS_FINE_LOCATION permission uri
     * This permission uri  comes under group of Location permission
     */
    ACCESS_FINE_LOCATION(Manifest.permission.ACCESS_FINE_LOCATION),

    /**
     * enum constant to get READ_CALENDAR permission uri
     * This permission uri  comes under group of CALENDAR permission
     */
    READ_CALENDAR(Manifest.permission.READ_CALENDAR),

    /**
     * enum constant to get WRITE_CALENDAR permission uri
     * This permission uri  comes under group of CALENDAR permission
     */
    WRITE_CALENDAR(Manifest.permission.WRITE_CALENDAR),

    /**
     * enum constant to get READ_CONTACTS permission uri
     * This permission uri  comes under group of CONTACTS permission
     */
    READ_CONTACTS(Manifest.permission.READ_CONTACTS),

    /**
     * enum constant to get WRITE_CONTACTS permission uri
     * This permission uri comes under group of CONTACTS permission
     */
    WRITE_CONTACTS(Manifest.permission.WRITE_CONTACTS),

    /**
     * enum constant to get GET_ACCOUNTS permission uri
     * This permission uri  comes under group of CONTACTS permission
     */
    GET_ACCOUNTS(Manifest.permission.GET_ACCOUNTS),

    //enum constant to get RECORD_AUDIO permission uri
    RECORD_AUDIO(Manifest.permission.RECORD_AUDIO),

    /**
     * enum constant to get SEND_SMS permission uri
     * This permission uri  comes under group of SMS permission
     */

    SEND_SMS(Manifest.permission.SEND_SMS),

    /**
     * enum constant to get READ_SMS permission uri
     * This permission uri  comes under group of SMS permission
     */
    READ_SMS(Manifest.permission.READ_SMS),

    /**
     * enum constant to get RECEIVE_SMS permission uri
     * This permission uri  comes under group of SMS permission
     */

    RECEIVE_SMS(Manifest.permission.RECEIVE_SMS),

    /**
     * enum constant to get RECEIVE_WAP_PUSH permission uri
     * This permission uri  comes under group of SMS permission
     */
    RECEIVE_WAP_PUSH(Manifest.permission.RECEIVE_WAP_PUSH),

    /**
     * enum constant to get RECEIVE_SMS permission uri
     * This permission uri  comes under group of SMS permission
     */

    RECEIVE_MMS(Manifest.permission.RECEIVE_MMS),


    //enum constant to get BODY_SENSORS permission uri
    SENSOR(Manifest.permission.BODY_SENSORS),

    /**
     * enum constant to get READ_PHONE_STATE permission uri
     * This permission uri  comes under group of PHONE permission
     */
    READ_PHONE_STATE(Manifest.permission.READ_PHONE_STATE),

    /**
     * enum constant to get CALL_PHONE permission uri
     * This permission uri  comes under group of PHONE permission
     */
    CALL_PHONE(Manifest.permission.CALL_PHONE),

    /**
     * enum constant to get READ_CALL_LOG permission uri
     * This permission uri  comes under group of PHONE permission
     */
    READ_CALL_LOG(Manifest.permission.READ_CALL_LOG),

    /**
     * enum constant to get WRITE_CALL_LOG permission uri
     * This permission uri  comes under group of PHONE permission
     */
    WRITE_CALL_LOG(Manifest.permission.WRITE_CALL_LOG),

    /**
     * enum constant to get ADD_VOICEMAIL permission uri
     * This permission uri  comes under group of PHONE permission
     */
    ADD_VOICEMAIL(Manifest.permission.ADD_VOICEMAIL),

    /**
     * enum constant to get USE_SIP permission uri
     * This permission uri  comes under group of PHONE permission
     *
     */
    USE_SIP(Manifest.permission.USE_SIP)

}