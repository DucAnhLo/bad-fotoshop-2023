import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class LookCommand extends Command implements Commands {

    private Editor editor;
    private ColorImage currentImage;
    private String name;

    public LookCommand(Command command) {
        super(command.getCommandWord(), command.getSecondWord(), command.getThirdWord());
        this.name = command.getSecondWord();
        this.currentImage = loadImage(name);

    }

    public void look(){
        this.editor = new Editor(Constants.filter1,Constants.filter2,Constants.filter3,Constants.filter4);
        System.out.println("The current image is " + name);
        System.out.print("Filters applied: ");
        if (editor.filter1 != null) {
            System.out.print(editor.filter1 + " ");
        }
        if (editor.filter2 != null) {
            System.out.print(editor.filter2 + " ");
        }
        if (editor.filter3 != null) {
            System.out.print(editor.filter3 + " ");
        }
        if (editor.filter4 != null) {
            System.out.print(editor.filter4 + " ");
        }
        System.out.println();
    }



    @Override
    public void execute() {
        look();
    }
}
