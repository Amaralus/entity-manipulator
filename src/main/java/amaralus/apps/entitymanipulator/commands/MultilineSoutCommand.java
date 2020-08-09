package amaralus.apps.entitymanipulator.commands;

public class MultilineSoutCommand implements InCommand {

    @Override
    public void execute(ExecutionResult executionResult) {
        execute(executionResult.getResult());
    }

    @Override
    public void execute(Object object) {
        object.toString().lines().forEach(System.out::println);
    }
}
