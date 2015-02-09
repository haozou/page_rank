import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;
import java.util.zip.DataFormatException;


public class Graph {
	
	private PageLoader pageLoader = new FileLoader();
	private Map<String,Page> pages = null;
	private Vector<Page> sortpages = new Vector<Page>();
	
	public Graph(String filename) {
		System.out.println("initialize...");
		try {
			pages = pageLoader.loadPage(filename);
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DataFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int count = 0;
		for (Iterator<String> i = pages.keySet().iterator(); i.hasNext();) {
			String s = i.next();
			pages.get(s).set_page_rank(1.0/(double)pages.size());
			pages.get(s).set_in_num(pages.get(s).get_in_links().size());
			for (String in_links : pages.get(s).get_in_links()) {
				pages.get(in_links).add_out_num();
				pages.get(in_links).set_is_sink(false);
				count++;
			}
		}
		System.out.println("count:" + count);
	}
	public Map<String, Page> get_pages() {
		return this.pages;
	}
	public void c_page_rank (int num) {
		System.out.println("calculate page rank...");
		double d = 0.85;
		double N = (double)pages.size();
		double sinkPR =  0.0;
		double page_rank =  0.0;
		while (num != 0) {
			sinkPR = 0.0;
			for (Map.Entry<String, Page> page : pages.entrySet())
			{
				if(page.getValue().get_is_sink())
				{
					sinkPR += page.getValue().get_page_rank();
				}
			}
			for (Iterator<String> i = pages.keySet().iterator(); i.hasNext();) {
				String s = i.next();
				page_rank = (1.0-d)/N;
				page_rank += d*sinkPR/N;
				for (String in_links : pages.get(s).get_in_links()) {
				
					page_rank += d*(pages.get(in_links).get_page_rank())
							/(double)pages.get(in_links).get_out_num();
				}
				pages.get(s).set_page_rank(page_rank);
			}
			num--;
			double pagerank = 0.0;
			for (Map.Entry<String, Page> page : pages.entrySet())
			{
				pagerank += page.getValue().get_page_rank();
			}
			System.out.println("total pagerank: "+(float)pagerank);
		}
		
		for (Map.Entry<String, Page> page : pages.entrySet())
		{
			sortpages.add(page.getValue());
		}
	}
	public double perplexity()
	{
		double perplexity = 0.0,sum = 0.0;
		for (Iterator<String> i = pages.keySet().iterator(); i.hasNext();) {
			String s = i.next();
			sum += (pages.get(s).get_page_rank())*Math.log(pages.get(s).get_page_rank())/
					Math.log(2);
		}
		perplexity = Math.pow(0.500000,sum);
		return perplexity;
	}
	public void sort_by_PR()
	{
		int i = 0, j = 0;
		
		Page temp;
		int gap = sortpages.size()+1;
		do
		{
			gap = gap/3 + 1;
			for (i = gap; i < sortpages.size(); i++)
			{
				if (sortpages.get(i).get_page_rank() > sortpages.get(i-gap).get_page_rank())
				{
					temp = sortpages.get(i);
					j = i - gap;
					do
					{
						sortpages.set(j+gap, sortpages.get(j));
						j = j-gap;
					
					}while(j >= 0&&temp.get_page_rank() > sortpages.get(j).get_page_rank());
					sortpages.set(j+gap, temp);
				}
			}
		}while(gap > 1);
		i = 0;
		System.out.println("sort by page rank:");
		for (Page page : sortpages) {
			if (i < 50) {
				System.out.println(page.get_page_name() +" "
					+"page_rank = "+page.get_page_rank()+" "
					+"in_num = "+page.get_in_num()+" "
					+"out_num = "+page.get_out_num()+" "
					+"is_sink = "+ page.get_is_sink());
			}
			i++;
		}
	}
	public void sort_by_in()
	{
		int i = 0, j = 0;
		Page temp;
		int gap = sortpages.size()+1;
		do
		{
			gap = gap/3 + 1;
			for (i = gap; i < sortpages.size(); i++)
			{
				if (sortpages.get(i).get_in_num() > sortpages.get(i-gap).get_in_num())
				{
					temp = sortpages.get(i);
					j = i - gap;
					do
					{
						sortpages.set(j+gap, sortpages.get(j));
						j = j-gap;
					
					}while(j >= 0&&temp.get_in_num() > sortpages.get(j).get_in_num());
					sortpages.set(j+gap, temp);
				}
			}
		}while(gap > 1);
		i = 0;
		System.out.println("sort by in link num:");
		for (Page page : sortpages) {
			if (i < 50) {
				System.out.println(page.get_page_name() +" "
					+"page_rank = "+page.get_page_rank()+" "
					+"in_num = "+page.get_in_num()+" "
					+"out_num = "+page.get_out_num()+" "
					+"is_sink = "+ page.get_is_sink());
			}
			i++;
		}
	}
}
