public class LookCommand implements Commands {
    private Editor editor;
    private ColorImage currentImage;
    private String name;

    public LookCommand(Editor editor, ColorImage currentImage, String name) {
        this.editor = editor;
        this.currentImage = currentImage;
        this.name = name;
    }



    public void look(){
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
