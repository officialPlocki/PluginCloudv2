package me.refluxo.libary.spigot.utils.cloud;

import de.dytanic.cloudnet.driver.CloudNetDriver;
import de.dytanic.cloudnet.driver.service.ServiceConfiguration;
import de.dytanic.cloudnet.driver.service.ServiceEnvironmentType;
import de.dytanic.cloudnet.driver.service.ServiceInfoSnapshot;
import de.dytanic.cloudnet.driver.service.ServiceTemplate;

public class GameInstance {

    private final String ident;
    private final String uuid;
    private ServiceInfoSnapshot snapshot;
    private final int memory;

    public GameInstance(String ident, String uuid, int memory) {
        this.ident = ident;
        this.uuid = uuid;
        this.memory = memory;
    }

    public void startInstance() {
        snapshot = ServiceConfiguration.builder()
                .task(ident+uuid)
                .node(CloudNetDriver.getInstance().getNodeUniqueId())
                .autoDeleteOnStop(false)
                .staticService(false)
                .templates(new ServiceTemplate(ident, "default", "local"), new ServiceTemplate(ident, "saves", "local"))
                .maxHeapMemory(memory)
                .environment(ServiceEnvironmentType.MINECRAFT_SERVER)
                .build()
                .createNewService();
        if (snapshot != null) {
            snapshot.provider().start();
        }
    }

    public ServiceInfoSnapshot getInstance() {
        if(CloudNetDriver.getInstance().getCloudServiceProvider().getCloudServiceByName(ident+uuid) != null) {
            return CloudNetDriver.getInstance().getCloudServiceProvider().getCloudServiceByName(ident+uuid);
        }
        return null;
    }

    public void stopInstance() {
        CloudNetDriver.getInstance().getCloudServiceProvider(getInstance().getServiceId().getUniqueId()).stop();
        snapshot = null;
    }

}
