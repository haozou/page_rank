import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.zip.DataFormatException;
public class FileLoader implements PageLoader{

	@Override
	public Map<String,Page> loadPage(String fileName) throws FileNotFoundException,
			IOException, DataFormatException {
		// TODO Auto-generated method stub
		Map<String,Page> pages = new HashMap<String, Page>();
		
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		String line =  reader.readLine();
		while (line != null) {

			Page page = null;

			page = readPage(line);
			
			pages.put(page.get_page_name(), page);

			line =  reader.readLine();
		}

		reader.close();
		
		return pages;
	}
	
	private Page readPage(String line) throws DataFormatException {
		StringTokenizer tokenizer = new StringTokenizer(line, " ");
		int num = tokenizer.countTokens();
		
		Page page = null;
		try {
			page = new Page(tokenizer.nextToken(),0.0,0,0,true);
			while (num!= 1) {
				page.add_in_links(tokenizer.nextToken());
				num--;
			}
		} catch (NumberFormatException  nfe)  {

			throw new DataFormatException(line);

		}
		
		return page;
	
	}
	

}
