import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class OpenCommand extends Command implements Commands {
    private String filename;
    private ColorImage currImage;

    public OpenCommand(Command command) {
        super(command.getCommandWord(), command.getSecondWord(), command.getThirdWord());
        this.filename = command.getSecondWord();
        this.currImage = loadImage(filename);
    }

    @Override
    public void execute() {
        if (filename == null) {
            // if there is no filename, we don't know what to open...
            System.out.println("open what?");
            return;
        }

        ColorImage img = loadImage(filename);
        if (img == null) {
            printHelp();
        } else {
            Editor editor = new Editor(null,null,null,null);
            currImage = img;
            editor.name = filename;
            System.out.println("Loaded " + editor.name);
        }
    }


    private void printHelp() {
        System.out.println("You are using Fotoshop.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println("   open save look mono flipH rot90 help quit");
    }
}
