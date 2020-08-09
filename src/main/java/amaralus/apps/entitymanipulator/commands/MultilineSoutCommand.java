package amaralus.apps.entitymanipulator.commands;

public class MultilineSoutCommand implements Command {

    @Override
    public Object execute(Object object) {
        object.toString().lines().forEach(System.out::println);
        return null;
    }

    @Override
    public String toString() {
        return ">> sout";
    }
}
