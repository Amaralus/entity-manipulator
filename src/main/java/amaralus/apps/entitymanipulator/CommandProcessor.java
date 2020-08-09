package amaralus.apps.entitymanipulator;

import amaralus.apps.entitymanipulator.commands.Command;
import amaralus.apps.entitymanipulator.commands.CommandExecutionException;
import amaralus.apps.entitymanipulator.commands.EntityCommand;
import amaralus.apps.entitymanipulator.commands.MultilineSoutCommand;
import amaralus.apps.entitymanipulator.source.EntitiesSource;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CommandProcessor {

    private final ExecutorService executorService;
    private final EntitiesSource<?> entitiesSource;

    private Command defaultLogCommand = new MultilineSoutCommand();

    public CommandProcessor(EntitiesSource<?> entitiesSource) {
        executorService = Executors.newSingleThreadExecutor();
        this.entitiesSource = entitiesSource;
    }

    public void process(String commandLine) {
        executorService.submit(() -> {
            var args = Arrays.asList(commandLine.split(" "));

            if (args.get(0).equals("entity")) {
                process(new CommandPipeline()
                        .add(new EntityCommand(args.get(1), entitiesSource))
                        .add(defaultLogCommand));
            } else
                defaultLogCommand.execute("wrong command [" + args.get(0) + "]");
        });
    }

    public void process(CommandPipeline pipeline) {
        try {
            pipeline.execute();
        } catch (CommandExecutionException e) {
            defaultLogCommand.execute(e.getMessage());
        }
    }

    public void stop() {
        executorService.shutdownNow();
    }
}
