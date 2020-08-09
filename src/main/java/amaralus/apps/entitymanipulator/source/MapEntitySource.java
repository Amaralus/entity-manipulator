package amaralus.apps.entitymanipulator.source;

import java.util.Map;

public class MapEntitySource extends EntitiesSource<Map.Entry<Object, Object>> {

    public MapEntitySource(Map<Object, Object> map) {
        super(map::entrySet, Map.Entry::getKey);
    }

    @Override
    public Object get(Object object) {
        var entry = (Map.Entry<?, ?>) super.get(object);
        return entry == null ? null : entry.getValue();
    }
}
