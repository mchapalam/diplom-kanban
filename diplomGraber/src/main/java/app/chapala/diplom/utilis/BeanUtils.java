package app.chapala.diplom.utilis;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import java.lang.reflect.Field;

@UtilityClass
public class BeanUtils {
    @SneakyThrows
    public void copyNonNullProperties(Object source, Object destination){
        Class<?> clazz = source.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields){
            field.setAccessible(true);
            Object values = field.get(source);

            if(values != null){
                field.set(destination, values);
            }
        }
    }

}
