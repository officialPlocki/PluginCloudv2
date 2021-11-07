package me.refluxo.libary.spigot.utils.cloud;

import de.dytanic.cloudnet.driver.CloudNetDriver;
import de.dytanic.cloudnet.driver.service.ServiceConfiguration;
import de.dytanic.cloudnet.driver.service.ServiceInfoSnapshot;
import de.dytanic.cloudnet.driver.service.ServiceTemplate;

public class GameInstance {

    public void startInstance() {
        ServiceInfoSnapshot snapshot = CloudNetDriver.getInstance().getCloudServiceFactory().createCloudService(ServiceConfiguration.builder()
                .serviceId(null)
                .task("task")
                .staticService(true)
                .taskId(1)
                .addTemplates(ServiceTemplate.parse("")).build());
    }

}
