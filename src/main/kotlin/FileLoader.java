import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class FileLoader {
public String loadFile(String path) {
    InputStream inputStream = getClass().getResourceAsStream(path);
    if (inputStream != null) {
        try (
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            return reader.lines().map(str -> str +="\n").collect(Collectors.joining());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    return "File cant read"; // Handle the case where the file isn't found
}

}
