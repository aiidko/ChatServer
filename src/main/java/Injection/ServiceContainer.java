package Injection;

import CommandService.interfaces.IService;

import java.util.HashMap;
import java.util.Map;

public class ServiceContainer {
    private static Map<String, IService> serviceContainer = new HashMap<String, IService>();

    public static void registerService(IService service){
        serviceContainer.put(service.getClass().getInterfaces()[0].getName(), service);
    }

    static IService getService(String serviceName){
        return serviceContainer.get(serviceName);
    }
}
