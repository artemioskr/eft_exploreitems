import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;



public class api {
    public static void main(String[] args) throws IOException {
        workWithApi();
    }

    public static void workWithApi() throws IOException {
        URL url = new URL("https://www.eftdb.one/static/data/attachments.json");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type",
                "application/x-www-form-urlencoded");

        DataOutputStream wr = new DataOutputStream (connection.getOutputStream());
//        wr.writeBytes("8");
        wr.close();

//        InputStream is = connection.getInputStream();
//        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
//        StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
//        String line;
//        while ((line = rd.readLine()) != null) {
//            response.append(line);
//            response.append('\r');
//        }
//        rd.close();
//        System.out.println(response.toString());
    }
}
