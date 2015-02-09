import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.zip.DataFormatException;


public interface PageLoader {
	public Map<String,Page> loadPage(String fileName)
			throws FileNotFoundException, IOException, DataFormatException;
}
