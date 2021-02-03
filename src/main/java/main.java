import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Set;

public class main {




    public static void main (String[] args) throws AWTException, InterruptedException {

        startWork();
        moveToEFT();
        writeAndSearch();
    }

    public static void startWork () {
        System.out.println("Open EFT with borderless mode");
        System.out.println("Write OK if you are ready.");
    }

    public static void moveToEFT() throws AWTException, InterruptedException {
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ALT);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_ALT);
        Thread.sleep(5000);
    }


//    public static String[] searchWithApi() {
//
//    }
    public static void writeAndSearch() throws AWTException, InterruptedException {
        String[] items = {"Tetriz portable game","Round pliers","OFZ 30x160mm shell","Military cable",
                "Military power filter", "Pressure gauge"};



        for (int i = 0; i < 2; i++) {
            Robot robot = new Robot();
            robot.mouseMove(50,125);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);

            Clipboard clip     = Toolkit.getDefaultToolkit().getSystemClipboard();
            StringSelection stringSelection= new StringSelection(items[i]);
            clip.setContents(stringSelection, null);

            robot.keyPress(KeyEvent.VK_BACK_SPACE);
            robot.keyRelease(KeyEvent.VK_BACK_SPACE);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);

            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            Thread.sleep(5000);

            robot.mouseMove(210,165);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
            Thread.sleep(5000);

            robot.mouseMove(909,250);
            robot.mousePress(InputEvent.BUTTON2_MASK);
            robot.mouseRelease(InputEvent.BUTTON2_MASK);
            Thread.sleep(5000);

            System.out.println(items[i] + " learning");
        }
    }
}
