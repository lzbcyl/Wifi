package com.lzb.mylibrary;

public class LeoricConfigs {

    public final LeoricConfig PERSISTENT_CONFIG;
    public final LeoricConfig DAEMON_ASSISTANT_CONFIG;

    public LeoricConfigs(LeoricConfig persistentConfig, LeoricConfig daemonAssistantConfig) {
        this.PERSISTENT_CONFIG = persistentConfig;
        this.DAEMON_ASSISTANT_CONFIG = daemonAssistantConfig;
    }

    public static class LeoricConfig {

        final String processName;
        final String serviceName;
        final String receiverName;
        final String activityName;

        public LeoricConfig(String processName, String serviceName, String receiverName, String activityName) {
            this.processName = processName;
            this.serviceName = serviceName;
            this.receiverName = receiverName;
            this.activityName = activityName;
        }
    }
}