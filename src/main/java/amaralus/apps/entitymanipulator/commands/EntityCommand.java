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
        var entity = entitiesSource.get(entityName);
        if (entity != null)
            return new ExecutionResult(entity, true);
        else
            return new ExecutionResult("entity [" + entityName + "] not found", false);
    }

    public String getEntityName() {
        return entityName;
    }
}
