package net.weblibrary.model;

public class Book {
	 	private int id;
	    private String name;
	    private String author;
	    private int count;
	    
	    public Book(int id, String name, String author, int count) {
	    	super();
	    	this.id = id;
	    	this.name = name;
	    	this.author = author;
	    	this.count = count;
	    }
	    
	    public Book(String name, String author, int count) {
	    	super();
	    	this.name = name;
	    	this.author = author;
	    	this.count = count;
	    }
	    
		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		
		public String getAuthor() {
			return author;
		}
		
		public void setAuthor(String author) {
			this.author = author;
		}    
		
		public int getCount() {
			return count;
		}
		
		public void setCount(int count) {
			this.count = count;
		} 
}

