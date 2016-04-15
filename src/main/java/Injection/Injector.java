package Injection;

import CommandService.interfaces.IService;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class Injector {

    public static <E> void doInject(E object) throws IllegalAccessException {
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields){
            Annotation annotation = field.getAnnotation(Inject.class);
            if (annotation != null){
                IService service = ServiceContainer.getService(field.getType().getName());
                field.setAccessible(true);
                field.set(object, service);
                field.setAccessible(false);
            }
        }
    }
}
