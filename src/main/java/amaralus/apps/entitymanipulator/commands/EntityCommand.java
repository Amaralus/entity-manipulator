package amaralus.apps.entitymanipulator.commands;

import amaralus.apps.entitymanipulator.source.EntitiesSource;

public class EntityCommand implements OutCommand {

    private final String entityName;
    private final EntitiesSource<?> entitiesSource;

    public EntityCommand(String entityName, EntitiesSource<?> entitiesSource) {
        this.entityName = entityName;
        this.entitiesSource = entitiesSource;
    }

    @Override
    public ExecutionResult execute() {
        String[] subNames = entityName.split("\\.");
        Object entity = null;

        for (int i = 0; i < subNames.length; i++) {
            entity = i == 0 ? entitiesSource.get(subNames[i]) : getEntityPropertyValue(entity, subNames[i]);

            if (entity == null) break;
        }

        if (entity != null)
            return new ExecutionResult(entity, true);
        else
            return new ExecutionResult("entity [" + entityName + "] not found", false);
    }

    private Object getEntityPropertyValue(Object entity, String propertyName) {
        try {
            var field = entity.getClass().getDeclaredField(propertyName);
            field.setAccessible(true);
            return field.get(entity);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            return null;
        }
    }

    public String getEntityName() {
        return entityName;
    }
}
