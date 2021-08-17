package com.app.runtimepermissions.constant

/**
 * <Use of class>
 *  <!--
 *  This is enum class that contains permission request code according to Permission type
 *  -->
 */
enum class PermissionCode(val requestCode: Int) {
    /**
     * constant to identify request code for CAMERA permission
     * @see PermissionType.CAMERA
     */
    CAMERA(0X5001),

    /**
     * constant to identify request code for STORAGE permission
     * @see PermissionType.STORAGE_GROUP
     */
    STORAGE_GROUP(0X5002),

    /**
     * constant to identify request code for READ_STORAGE permission
     * @see PermissionType.READ_STORAGE
     */
    READ_STORAGE(0X5003),


    /**
     * constant to identify request code for WRITE_STORAGE permission
     * @see PermissionType.WRITE_STORAGE
     */
    WRITE_STORAGE(0X5004),

    /**
     * constant to identify request code for LOCATION permission
     * @see PermissionType.LOCATION_GROUP
     */
    LOCATION_GROUP(0X5005),

    /**
     * constant to identify request code for CALENDER permission
     * @see PermissionType.CALENDER_GROUP
     */
    CALENDER_GROUP(0X5006),

    /**
     * constant to identify request code for READ_CALENDAR permission
     * @see PermissionType.READ_CALENDAR
     */
    READ_CALENDAR(0X5007),


    /**
     * constant to identify request code for WRITE_CALENDAR permission
     * @see PermissionType.WRITE_CALENDAR
     */
    WRITE_CALENDAR(0X5008),

    /**
     * constant to identify request code for CONTACT permission
     * @see PermissionType.CONTACTS_GROUP
     */
    CONTACTS_GROUP(0X5009),

    /**
     * constant to identify request code for READ_CONTACTS permission
     * @see PermissionType.READ_CONTACTS
     */
    READ_CONTACTS(0X5010),

    /**
     * constant to identify request code for WRITE_STORAGE permission
     * @see PermissionType.WRITE_CONTACTS
     */
    WRITE_CONTACTS(0X5011),

    /**
     * constant to identify request code for GET_ACCOUNT permission
     * @see PermissionType.GET_ACCOUNT
     */
    GET_ACCOUNT(0X5012),

    /**
     * constant to identify request code for AUDIO permission
     * @see PermissionType.RECORD_AUDIO
     */
    RECORD_AUDIO(0X5013),

    /**
     * constant to identify request code for SMS permission
     * @see PermissionType.SMS_GROUP
     */
    SMS_GROUP(0X5014),


    /**
     * constant to identify request code for SMS permission
     * @see PermissionType.SMS
     */
    SMS(0X5015),


    /**
     * constant to identify request code for SEND_SMS permission
     * @see PermissionType.SEND_SMS
     */
    SEND_SMS(0X5016),

    /**
     * constant to identify request code for READ_SMS permission
     * @see PermissionType.READ_SMS
     */
    READ_SMS(0X5017),

    /**
     * constant to identify request code for RECEIVE_SMS permission
     * @see PermissionType.RECEIVE_SMS
     */
    RECEIVE_SMS(0X5018),

    /**
     * constant to identify request code for RECEIVE_WAP_PUSH permission
     * @see PermissionType.RECEIVE_WAP_PUSH
     */
    RECEIVE_WAP_PUSH(0X5019),

    /**
     * constant to identify request code for RECEIVE_MMS permission
     * @see PermissionType.RECEIVE_MMS
     */
    RECEIVE_MMS(0X5020),


    /**
     * constant to identify request code for SENSOR permission
     * @see PermissionType.SENSOR
     */
    SENSOR(0X5021),

    /**
     * constant to identify request code for PHONE_GROUP permission
     * @see PermissionType.PHONE_GROUP
     */
    PHONE_GROUP(0X5022),

    /**
     * constant to identify request code for CALL_LOG_GROUP permission
     * @see PermissionType.CALL_LOG_GROUP
     */
    CALL_LOG_GROUP(0X5023),

    /**
     * constant to identify request code for READ_PHONE_STATE permission
     * @see PermissionType.READ_PHONE_STATE
     */
    READ_PHONE_STATE(0X5024),


    /**
     * constant to identify request code for CALL_PHONE permission
     * @see PermissionType.CALL_PHONE
     */
    CALL_PHONE(0X5025),

    /**
     * constant to identify request code for READ_CALL_LOG permission
     * @see PermissionType.READ_CALL_LOG
     */
    READ_CALL_LOG(0X5026),

    /**
     * constant to identify request code for WRITE_CALL_LOG permission
     * @see PermissionType.WRITE_CALL_LOG
     */
    WRITE_CALL_LOG(0X5027),



    /**
     * constant to identify request code for ADD_VOICE_MAIL permission
     * @see PermissionType.ADD_VOICE_MAIL
     */
    ADD_VOICE_MAIL(0X5028),

    /**
     * constant to identify request code for USE_SIP permission
     * @see PermissionType.USE_SIP
     */
    USE_SIP(0X5029),

    //constant to identify if user request multiple permission at once
    MULTIPLE_PERMISSION_REQUEST_CODE(0X5030)

}
