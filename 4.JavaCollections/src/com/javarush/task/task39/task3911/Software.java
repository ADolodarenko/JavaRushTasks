package com.javarush.task.task39.task3911;

import java.util.*;

public class Software {
    int currentVersion;

    private Map<Integer, String> versionHistoryMap = new LinkedHashMap<>();

    public void addNewVersion(int version, String description) {
        if (version > currentVersion) {
            versionHistoryMap.put(version, description);
            currentVersion = version;
        }
    }

    public int getCurrentVersion() {
        return currentVersion;
    }

    public Map<Integer, String> getVersionHistoryMap() {
        return Collections.unmodifiableMap(versionHistoryMap);
    }

    public boolean rollback(int rollbackVersion)
	{
		if (versionHistoryMap.containsKey(rollbackVersion))
		{
			Iterator<Map.Entry<Integer, String>> iterator = versionHistoryMap.entrySet().iterator();
			while (iterator.hasNext())
			{
				Map.Entry<Integer, String> pair = iterator.next();
				if (pair.getKey().intValue() > rollbackVersion)
					iterator.remove();
			}

			currentVersion = rollbackVersion;

			return true;
		}
		else
			return false;
    }
}
