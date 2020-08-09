package amaralus.apps.entitymanipulator;

import amaralus.apps.entitymanipulator.commands.Command;

public class CommandPipeline {

    private Stage first;
    private Stage last;

    public CommandPipeline add(Command command) {
        if (first == null) {
            first = new Stage(null, command, null);
            last = first;
        } else {
            var stage = new Stage(last, command, null);
            if (first.equals(last))
                first.next = stage;
            else
                last.next = stage;
            last = stage;
        }
        return this;
    }

    public void execute() {
        for (var stage = first; stage != null; stage = stage.next) {
            if (stage.prev == null)
                stage.execute(null);
            else
                stage.execute(stage.prev.executionResult);
        }
    }

    private static class Stage {
        private Stage next;
        private Stage prev;

        private Command command;
        private Object executionResult;

        public Stage(Stage prev, Command command, Stage next) {
            this.prev = prev;
            this.command = command;
            this.next = next;
        }

        private void execute(Object object) {
            executionResult = command.execute(object);
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("commandPipeline: \n");
        for (var stage = first; stage != null; stage = stage.next)
            builder.append(stage.command.toString()).append("\n");
        return builder.toString();
    }
}
