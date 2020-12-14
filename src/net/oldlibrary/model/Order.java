package net.weblibrary.model;

public class Order {
		private int id;
	 	private int reader_id;
	 	private int book_id;
	 	private boolean status;
	 	
	 	public Order(int id, int reader_id, int book_id, boolean status) {
			super();
			this.id = id;
			this.reader_id = reader_id;
			this.book_id = book_id;
			this.status = status;
		}
	 	
		public Order(int reader_id, int book_id, boolean status) {
			super();
			this.reader_id = reader_id;
			this.book_id = book_id;
			this.status = status;
		}

		public int getid() {
			return id;
		}

		public void setid(int id) {
			this.id = id;
		}
		
		public int getReader_id() {
			return reader_id;
		}

		public void setReader_id(int reader_id) {
			this.reader_id = reader_id;
		}

		public int getBook_id() {
			return book_id;
		}

		public void setBook_id(int book_id) {
			this.book_id = book_id;
		}	
		
		public boolean getStatus() {
			return status;
		}

		public void setStatus(boolean status) {
			this.status = status;
		}	
}
