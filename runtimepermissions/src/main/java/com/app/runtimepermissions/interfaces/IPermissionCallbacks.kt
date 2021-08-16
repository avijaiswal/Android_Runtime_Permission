package com.app.runtimepermissions.interfaces

import com.app.runtimepermissions.constant.PermissionType

/**
 * <Use of interface>
 * <!--
 * This interface is used to handle callbacks of runtime permission request
 * Classes that uses PermissionUtil must implement this interface to handle callbacks
 * -->
 */
interface IPermissionCallbacks {
    /**
     * <Use of method >
     * <!--
     * This method will trigger when requested permission granted
     * @param permissionType //used to identify granted permission type, value should be null or enum type
     * @see PermissionType
     * -->
     */

    fun onPermissionGranted(permissionType: PermissionType?)

    /**
     * <Use of method >
     * <!--
     * This method will trigger when requested permission denied
     * @param permissionType //used to identify denied permission type, value should be null or enum type
     * @param permissionTypeList //this parameter contains list of denied permission , value should be null or list of enum type
     * @see PermissionType
     * -->
     */

    fun onPermissionDenied(permissionType: PermissionType?)

    /**
     * <Use of method >
     * <!--
     * This method will used to show rational permission dialog
     * @param permissionType // used to identify granted permission type, value should be null or enum type
     * @param isAlwaysHide // this parameter is used to identify if native permission dialog
     * is always hidden (always hide when user choose don't ask me or continue denied two times)
     * @see PermissionType
     * -->
     */

    fun onShowRationalPermissionDialog(permissionType: PermissionType, isAlwaysHide: Boolean)

    /**
     * <Use of method >
     * <!--
     * This method will trigger when get result from multiple permission request
     * @param deniedPermissionList //this parameter contains list of granted permission
     * @param grantedPermissionList //this parameter contains list of granted permission
     * @param isGranted //it will used to validate if all multiple permission request is granted or not
     * @param isAlwaysHide true if requested permissions is always hide
     * @see PermissionType
     * -->
     */

    fun onMultiplePermissionResult(isGranted: Boolean,grantedPermissionList: List<PermissionType>?
                                   ,deniedPermissionList: List<PermissionType>?, isAlwaysHide: Boolean)


}