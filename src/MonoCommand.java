import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MonoCommand extends Command implements Commands{
    private Editor editor;
    private ColorImage currImage;
    private String filename;

    public MonoCommand(Command command) {
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

        ColorImage tmpImage = new ColorImage(currImage);
        //Graphics2D g2 = currentImage.createGraphics();
        int height = tmpImage.getHeight();
        int width = tmpImage.getWidth();
        for (int y=0; y<height; y++) {
            for (int x=0; x<width; x++) {
                Color pix = tmpImage.getPixel(x, y);
                int lum = (int) Math.round(0.299*pix.getRed()
                        + 0.587*pix.getGreen()
                        + 0.114*pix.getBlue());
                tmpImage.setPixel(x, y, new Color(lum, lum, lum));
            }
        }
        currImage = tmpImage;
        if (editor.filter1 == null) {
            editor.filter1 = "mono";
            Constants.filter1 = editor.filter1;
        } else if (editor.filter2 == null) {
            editor.filter2 = "mono";
            Constants.filter2 = "mono";
        } else if (editor.filter3 == null) {
            editor.filter3 = "mono";
            Constants.filter3 = "mono";
        } else if (editor.filter4 == null) {
            editor.filter4 = "mono";
            Constants.filter4 = "mono";
        }
    }
}
