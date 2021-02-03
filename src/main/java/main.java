import com.jayway.jsonpath.JsonPath;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Set;

public class main {




    public static void main (String[] args) throws AWTException, InterruptedException, IOException {

        startWork();
        moveToEFT();
        writeAndSearch(workWithApi());

    }

    public static void startWork () throws InterruptedException {
        System.out.println("Open EFT with borderless mode");
        System.out.println("Waiting 5 seconds, be ready.");
        Thread.sleep(5000);
    }

    public static void moveToEFT() throws AWTException, InterruptedException {
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ALT);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_ALT);
        Thread.sleep(5000);
    }


    public static String workWithApi() throws IOException {
        URL url = new URL("https://www.eftdb.one/static/data/attachments.json");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type",
                "application/x-www-form-urlencoded");

        DataOutputStream wr = new DataOutputStream (connection.getOutputStream());
//        wr.writeBytes("8");
        wr.close();

        InputStream is = connection.getInputStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
        StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
        String line;
        while ((line = rd.readLine()) != null) {
            response.append(line);
            response.append('\r');
        }
        rd.close();
        System.out.println(response.toString());

        return response.toString();

    }

    public static List<String> filterJson(String filter) {
        List<String> names = JsonPath.read(filter,"$..name");

        return names;
    }


    public static void writeAndSearch(String items) throws AWTException, InterruptedException {

        List<String> item = filterJson(items);

        for (int i = 0; i < item.size(); i++) {
            Robot robot = new Robot();
            robot.mouseMove(150,125);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);

            Clipboard clip     = Toolkit.getDefaultToolkit().getSystemClipboard();
            StringSelection stringSelection= new StringSelection(item.get(i));
            clip.setContents(stringSelection, null);

            robot.keyPress(KeyEvent.VK_BACK_SPACE);
            robot.keyRelease(KeyEvent.VK_BACK_SPACE);
            Thread.sleep(2000);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);

            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            Thread.sleep(3000);

            robot.mouseMove(210,165);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
            Thread.sleep(3000);

            robot.mouseMove(909,250);
            robot.mousePress(InputEvent.BUTTON2_MASK);
            robot.mouseRelease(InputEvent.BUTTON2_MASK);
            Thread.sleep(3000);

            System.out.println(item.get(i) + " learning");
        }
    }
}
