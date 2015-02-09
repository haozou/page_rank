import java.io.*;
import java.util.*;


public class page_rank {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Graph g = new Graph("web.txt");
		g.c_page_rank(80);
		g.sort_by_PR();
		g.sort_by_in();
		
		//g.get_pages().get("dsa");
		
		System.out.println("perplexity: "+ (float)g.perplexity());
		
/*		System.out.println("name:"+ g.get_pages().get("WT25-B39-116").get_page_name()
				+" page_rank: "+g.get_pages().get("WT25-B39-116").get_page_rank()
				+" in_num:"+g.get_pages().get("WT25-B39-116").get_in_num()
				+" out_num:"+g.get_pages().get("WT25-B39-116").get_out_num()
				+" is_sink:"+g.get_pages().get("WT25-B39-116").get_is_sink());*/
		
	}
	
}
