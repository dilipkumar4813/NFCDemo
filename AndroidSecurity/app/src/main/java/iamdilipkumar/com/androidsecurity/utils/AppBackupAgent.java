package iamdilipkumar.com.androidsecurity.utils;

import android.app.backup.BackupAgentHelper;
import android.app.backup.SharedPreferencesBackupHelper;

/**
 * Created on 23/10/17.
 *
 * @author dilipkumar4813
 * @version 1.0
 */

public class AppBackupAgent extends BackupAgentHelper {

    // The name of the SharedPreferences file
    static final String PREFS = "myprefs";

    // A key to uniquely identify the set of backup data
    static final String PREFS_BACKUP_KEY = "myprefs";

    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferencesBackupHelper helper = new SharedPreferencesBackupHelper(this, PREFS);
        addHelper(PREFS_BACKUP_KEY, helper);
    }

    // ADB commands for backup and restore
    // schedule backup
    // adb shell bmgr backup iamdilipkumar.com.androidsecurity
    // ensure scheduled backup run
    // adb shell bmgr run
    // to restore you backup use bmgr restore
    // adb shell bmgr restore iamdilipkumar.com.androidsecurity
}
