import java.awt.*;

public class Rot90Command extends Command implements Commands {
    private Editor editor;
    private ColorImage currImage;
    private String filename;

    public Rot90Command(Command command) {
        super(command.getCommandWord(), command.getSecondWord(),command.getThirdWord());
        this.editor = new Editor(Constants.filter1,Constants.filter2,Constants.filter3,Constants.filter4);
        this.filename = command.getSecondWord();
        this.currImage = this.loadImage(filename);
    }

    @Override
    public void execute() {
        if (editor.filter4 != null) {
            System.out.println("Filter pipeline exceeded");
            return;
        }
        // R90 = [0 -1, 1 0] rotates around origin
        // (x,y) -> (-y,x)
        // then transate -> (height-y, x)
        int height = currImage.getHeight();
        int width = currImage.getWidth();
        ColorImage rotImage = new ColorImage(height, width);
        for (int y=0; y<height; y++) { // in the rotated image
            for (int x=0; x<width; x++) {
                Color pix = currImage.getPixel(x,y);
                rotImage.setPixel(height-y-1,x, pix);
            }
        }
        currImage = rotImage;
        if (editor.filter1 == null) {
            editor.filter1 = "flipH";
            Constants.filter1 = editor.filter1;
        } else if (editor.filter2 == null) {
            editor.filter2 = "flipH";
            Constants.filter2 = "flipH";
        } else if (editor.filter3 == null) {
            editor.filter3 = "flipH";
            Constants.filter3 = "flipH";
        } else if (editor.filter4 == null) {
            editor.filter4 = "flipH";
            Constants.filter4 = "flipH";
        }
    }
}
