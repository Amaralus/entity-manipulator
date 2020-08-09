package amaralus.apps.entitymanipulator.commands;

public class SoutOutputCommand implements OutputCommand {

    @Override
    public void execute(ExecutionResult executionResult) {
        System.out.println(executionResult.getResult());
    }
}
