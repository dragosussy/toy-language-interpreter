package Views.MenuView.Commands;

import Controllers.Controller;

public class RunCommand extends Command {
    private final Controller controller;

    public RunCommand(String key, String description, Controller controller) {
        super(key, description);
        this.controller = controller;
    }

    @Override
    public void execute() {
        //this.controller.executeCurrentProgram();
        this.controller.executeProgram(Integer.parseInt(this.getKey()) - 1);
    }
}
