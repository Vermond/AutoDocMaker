package groupId;

import groupId.frame.MainFrame;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        MainFrame f = new MainFrame(1000, 1000);
        f.setVisible(true);
    }
}
