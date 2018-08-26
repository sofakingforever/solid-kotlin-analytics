package com.cobox.core.ui.update;

import android.support.annotation.NonNull;

/**
 * Created by nadavfima on 3/4/18.
 */

public class Version implements Comparable<Version> {
    private String version;

    public Version(String version) {
        super();
        this.version = version;
    }

    /**
     * @param appVer  - the local application's version name
     * @param currVer - the
     * @param minVer
     *
     * @return
     */
    public static VersionManager.VersionStatus getStatus(String appVer, String currVer, String minVer) {


        Version appVersion = new Version(appVer);
        Version minimumVersion = new Version(minVer);
        Version recommendedVersion = new Version(currVer);

        if (appVersion.compareTo(minimumVersion) < 0) {
            // lower than minVer - user MUST update
            return VersionManager.VersionStatus.UPDATE_FORCED;
        } else {
            // minVersion is VERSION_OK
            if (appVersion.compareTo(recommendedVersion) < 0) {
                // user should update
                return VersionManager.VersionStatus.UPDATE_AVAILABLE;
            } else {
                return VersionManager.VersionStatus.VERSION_OK;
            }


        }

    }

    @Override
    public int compareTo(@NonNull Version vers) {

        String[] levels1 = this.version.split("\\.");
        String[] levels2 = vers.version.split("\\.");

        int length = Math.max(levels1.length, levels2.length);
        for (int i = 0; i < length; i++) {
            Integer v1 = i < levels1.length ? Integer.parseInt(levels1[i]) : 0;
            Integer v2 = i < levels2.length ? Integer.parseInt(levels2[i]) : 0;
            int compare = v1.compareTo(v2);
            if (compare != 0) {
                return compare;
            }
        }

        return 0;
    }
}
