package com.app.runtimepermissions.constant

/**
 * <Use of class>
 *  <!--
 *  This is enum class that contains constant value of permission type
 *  -->
 */
enum class PermissionType {
    //enum constant to identify request for CAMERA permission
    CAMERA,

    //enum constant to identify request for READ and WRITE STORAGE permission

    /**
     * Enum constant to identify request for STORAGE permission
     * This permission is group permission i.e READ STORE, WRITE STORAGE
     * If any permission in a Permission Group is granted. Another permission in the same group will be automatically granted as well
     * @see PermissionU
     */
    STORAGE_GROUP,

    /**
     * Enum constant to identify request for READ_STORAGE permission
     * @see PermissionU
     */
    READ_STORAGE,

    /**
     * Enum constant to identify request for STORAGE permission
     * This permission is group permission i.e READ STORE, WRITE STORAGE
     * If any permission in a Permission Group is granted. Another permission in the same group will be automatically granted as well
     * @see PermissionU
     */
    WRITE_STORAGE,

    /**
     * Enum constant to identify request for LOCATION permission
     * This permission is group permission i.e ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION
     * If any permission in a Permission Group is granted. Another permission in the same group will be automatically granted as well
     */
    LOCATION_GROUP,


    /**
     * Enum constant to identify request for CALENDER permission
     * This permission is group permission i.e READ_CALENDAR, WRITE_CALENDAR
     * If any permission in a Permission Group is granted. Another permission in the same group will be automatically granted as well
     */
    CALENDER_GROUP,

    /**
     * Enum constant to identify request for READ_CALENDER permission
     */
    READ_CALENDAR,


    /**
     * Enum constant to identify request for WRITE_CALENDER permission
     */
    WRITE_CALENDAR,


    /**
     * Enum constant to identify request for CONTACT permission
     * This permission is group permission i.e READ_CONTACTS, WRITE_CONTACTS, GET_ACCOUNTS
     * If any permission in a Permission Group is granted. Another permission in the same group will be automatically granted as well
     */
    CONTACTS_GROUP,

    /**
     * Enum constant to identify request for READ_CONTACTS permission
     */
    READ_CONTACTS,

    /**
     * Enum constant to identify request for WRITE_CONTACTS permission
     */
    WRITE_CONTACTS,

    /**
     * Enum constant to identify request for GET_ACCOUNTS permission
     */
    GET_ACCOUNT,


    //enum constant to identify request for AUDIO (MICROPHONE) permission
    RECORD_AUDIO,

    /**
     * Enum constant to identify request for SMS permission
     * This permission is group permission i.e SEND_SMS, READ_SMS, RECEIVE_SMS, RECEIVE_WAP_PUSH, RECEIVE_MMS, READ_CELL_BROADCASTS
     * If any permission in a Permission Group is granted. Another permission in the same group will be automatically granted as well
     */
    SMS_GROUP,

    /**
     * Enum constant to identify request for READ AND WRITE SMS permission
     */
    SMS,

    /**
     * Enum constant to identify request for SEND_SMS permission
     */
    SEND_SMS,

    /**
     * Enum constant to identify request for READ_SMS permission
     */
    READ_SMS,

    /**
     * Enum constant to identify request for RECEIVE_SMS permission
     */
    RECEIVE_SMS,

    /**
     * Enum constant to identify request for RECEIVE_WAP_PUSH permission
     */
    RECEIVE_WAP_PUSH,

    /**
     * Enum constant to identify request for RECEIVE_MMS permission
     */
    RECEIVE_MMS,

    /**
     * Enum constant to identify request for PHONE permission
     * This permission is group permission i.e READ_PHONE_STATE, CALL_PHONE, READ_CALL_LOG, WRITE_CALL_LOG, ADD_VOICE_MAIL, USE_SIP
     * If any permission in a Permission Group is granted. Another permission in the same group will be automatically granted as well
     */
    PHONE_GROUP,

    /**
     * Enum constant to identify request for PHONE permission
     * This permission is group permission i.e READ_CALL_LOG, WRITE_CALL_LOG
     * If any permission in a Permission Group is granted. Another permission in the same group will be automatically granted as well
     */
    CALL_LOG_GROUP,

    /**
     * Enum constant to identify request for READ_PHONE_STATE permission
     */
    READ_PHONE_STATE,

    /**
     * Enum constant to identify request for CALL_PHONE permission
     */
    CALL_PHONE,

    /**
     * Enum constant to identify request for READ_CALL_LOG permission
     */
    READ_CALL_LOG,

    /**
     * Enum constant to identify request for WRITE_CALL_LOG permission
     */
    WRITE_CALL_LOG,

    /**
     * Enum constant to identify request for USE_SIP permission
     */
    USE_SIP,

    //enum constant to identify request for SENSOR (BODY_SENSOR) permission
    SENSOR



}