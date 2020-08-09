package amaralus.apps.entitymanipulator.commands;

public class ExecutionResult {

    private final Object result;
    private final boolean success;

    public ExecutionResult(Object result, boolean success) {
        this.result = result;
        this.success = success;
    }

    public Object getResult() {
        return result;
    }

    public boolean isSuccess() {
        return success;
    }
}
