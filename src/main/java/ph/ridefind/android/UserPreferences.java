package ph.ridefind.android;

import com.googlecode.androidannotations.annotations.sharedpreferences.SharedPref;

@SharedPref(value = SharedPref.Scope.UNIQUE)
public interface UserPreferences {
    String registrationId();

    boolean loggedIn();

    String mall();

    String fsqId();

    String token();
}
