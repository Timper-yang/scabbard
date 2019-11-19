package com.timper.scabbard;

import com.timper.scabbard.interfaces.LoginBinder;

/**
 * User: tangpeng.yang
 * Date: 2019-11-18
 * Description:
 * FIXME
 */
public class Scabbard {

    public static LoginBinder loginBinder;

    public static void setLoginBinder(LoginBinder loginBinder) {
        Scabbard.loginBinder = loginBinder;
    }
}
