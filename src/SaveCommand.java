
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SaveCommand extends Command implements Commands{

    private String filename;
    private ColorImage currentImage;

    public SaveCommand(Command command) {
        super(command.getCommandWord(), command.getSecondWord(), command.getThirdWord());
        this.filename = command.getSecondWord();
        this.currentImage = loadImage(filename);
    }

    @Override
    public void execute() {
        if (currentImage == null) {
            printHelp();
            return;
        }
        if (filename == null) {
            // if there is no filename, we don't know where to save...
            System.out.println("save where?");
            return;
        }

        try {
            File outputFile = new File(filename);
            ImageIO.write(currentImage, "jpg", outputFile);
            System.out.println("Image saved to " + filename);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            printHelp();
        }
    }
    private void printHelp() {
        System.out.println("You are using Fotoshop.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println("   open save look mono flipH rot90 help quit");
    }
}
