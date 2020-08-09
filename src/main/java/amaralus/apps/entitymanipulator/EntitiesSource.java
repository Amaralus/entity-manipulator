package amaralus.apps.entitymanipulator;

import java.util.Collection;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

public class EntitiesSource<E> {

    private final Supplier<Collection<E>> entities;
    private final Function<E, Object> keyFunction;

    public EntitiesSource(Supplier<Collection<E>> entities, Function<E, Object> keyFunction) {
        this.entities = Objects.requireNonNull(entities);
        this.keyFunction = Objects.requireNonNull(keyFunction);
    }

    public Object get(Object object) {
        for (E entity : entities.get())
            if (Objects.equals(object, keyFunction.apply(entity)))
                return entity;
        return null;
    }
}
