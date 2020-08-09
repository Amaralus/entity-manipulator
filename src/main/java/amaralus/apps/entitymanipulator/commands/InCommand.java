package amaralus.apps.entitymanipulator.commands;

public interface InCommand {

    void execute(ExecutionResult result);

    void execute(Object object);
}
