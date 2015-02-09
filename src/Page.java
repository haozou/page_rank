import java.util.*;

public class Page implements Iterable<String>{
	
	private String page_name;
	private double page_rank;
	private int out_num;
	private int in_num;
	private boolean is_sink;
	private ArrayList<String> in_links;
	
	public Page(String page_name, double page_rank, 
			int out_num,int in_num,boolean is_sink) {
		// TODO Auto-generated constructor stub
		this.page_name = page_name;
		this.page_rank = page_rank;
		this.out_num = out_num;
		this.in_num = in_num;
		this.is_sink = is_sink;
		this.in_links = new ArrayList<String> ();
	}

	public Page() {
		// TODO Auto-generated constructor stub
	}

	public String get_page_name() {
		return this.page_name;
	}
	
	public double get_page_rank() {
		return this.page_rank;
	}
	
	public void set_page_rank(double page_rank) {
		this.page_rank = page_rank;
	}
	
	public int get_out_num() {
		return this.out_num;
	}
	
	public void set_out_num(int out_num) {
		this.out_num = out_num;
	}
	public int get_in_num() {
		return this.in_num;
	}
	
	public void set_in_num(int in_num) {
		this.in_num = in_num;
	}
	public boolean get_is_sink() {
		return this.is_sink;
	}
	
	public void set_is_sink(boolean flag) {
		this.is_sink = flag;
	}
	public void add_out_num() {
		this.out_num++;
	}
	@Override
	public Iterator<String> iterator() {
		// TODO Auto-generated method stub
		return this.in_links.iterator();
	}
	
	public void add_in_links(String in_link) {
		this.in_links.add(in_link);
	}
	public ArrayList<String> get_in_links() {
		return this.in_links;
	}
	

}
