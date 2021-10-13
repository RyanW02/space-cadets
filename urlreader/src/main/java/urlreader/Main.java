package urlreader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main implements Runnable {
    public static void main(String[] args) {
        System.out.print("Enter email ID: ");
        Scanner scanner = new Scanner(System.in);

        String emailId = scanner.nextLine();
        if (emailId.isEmpty()) {
            System.err.println("No ID provided");
            return;
        }

        Main main = new Main(emailId);
        main.run();
    }

    private final String emailId;

    public Main(String emailId) {
        this.emailId = emailId;
    }

    @Override
    public void run() {
        try {
            String page = readPage();
            String name = getNameFromHtml(page);
            if (name == null) {
                System.out.println("User not found");
            } else {
                System.out.println(name);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private String readPage() throws IOException {
        URL url = this.getUrl();

        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        try (BufferedInputStream is = new BufferedInputStream(conn.getInputStream())) {
            byte[] bytes = is.readAllBytes();
            return new String(bytes, StandardCharsets.UTF_8);
        }
    }

    private String getNameFromHtml(String html) {
        Document doc = Jsoup.parse(html);
        Elements el = doc.select("#page > article > div > h1");
        if (el.isEmpty()) {
            return null;
        } else {
            return el.first().text();
        }
    }

    private URL getUrl() throws MalformedURLException {
        return new URL(String.format("https://www.ecs.soton.ac.uk/people/%s", emailId));
    }
}
