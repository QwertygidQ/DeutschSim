package com.qwertygid.deutschsim.Logic;

import java.util.ArrayList;

public class Table<T> {
	public Table() {
		empty();
	}
	
	public Table(final ArrayList<ArrayList<T>> table) {
		this.table = table;
	}
	
	public boolean valid() {
		if (table.size() == 0)
			return true;
		
		// Checks if all rows are of the same length
		int row_length = table.get(0).size();
		for (int row = 1; row < table.size(); row++)
			if (table.get(row).size() != row_length)
				return false;
			
		return true;
	}
	
	public void insert_element(final T element, final int row, final int col) {
		if (!is_in_table(row, col))
			throw new IllegalArgumentException("Position passed to insert_element() is out of bounds");
		
		table.get(row).set(col, element);
	}
	
	public T get_element(final int row, final int col) {
		if (!is_in_table(row, col))
			throw new IllegalArgumentException("Position passed to get_element() is out of bounds");
		
		return table.get(row).get(col);
	}
	
	public void remove_element(final int row, final int col) {
		if (!is_in_table(row, col))
			throw new IllegalArgumentException("Position passed to remove_element() is out of bounds");
		else if (table.get(row).get(col) == null)
			throw new IllegalArgumentException("Cannot delete a nonexistant element");
		
		table.get(row).set(col, null);
	}
	
	public void add_row(final int index) {
		if (index < 0 || index > get_row_count())
			throw new IllegalArgumentException("Index passed to add_row() is out of bounds");
		if (!valid())
			throw new RuntimeException("Table is not valid in add_row()");
		
		int size = (get_row_count() == 0 ? 0 : get_col_count());
		ArrayList<T> row = new ArrayList<T>();
		for (int i = 0; i < size; i++)
			row.add(null);
		
		table.add(index, row);
	}
	
	public void add_row() {
		add_row(get_row_count());
	}
	
	public void remove_row(final int index) {
		if (index < 0 || index > get_row_count() - 1)
			throw new IllegalArgumentException("Index passed to remove_row() is out of bounds");
		if (!valid())
			throw new RuntimeException("Table is not valid in remove_row()");
		
		table.remove(index);
	}
	
	public void remove_last_row() {
		remove_row(get_row_count() - 1);
	}
	
	public void add_col(final int index) {
		if (table.isEmpty())
			add_row();
		
		if (index < 0 || index > get_col_count())
			throw new IllegalArgumentException("Index passed to add_col() is out of bounds");
		if (!valid())
			throw new RuntimeException("Table is not valid in add_col()");
		
		for (ArrayList<T> row : table)
			row.add(index, null);
	}
	
	public void add_col() {
		int index = (is_empty() ? 0 : get_col_count());
		add_col(index);
	}
	
	public boolean is_col_empty(final int index) {
		if (index < 0 || index > get_col_count() - 1)
			throw new IllegalArgumentException("Index passed to is_col_empty() is out of bounds");
		if (!valid())
			throw new RuntimeException("Table is not valid in is_col_empty()");
		
		for (int row = 0; row < get_row_count(); row++) {
			if (table.get(row).get(index) != null)
				return false;
		}
		
		return true;
	}
	
	public void remove_col(final int index) {
		if (is_empty() || index < 0 || index > get_col_count() - 1)
			throw new IllegalArgumentException("Index passed to remove_col() is out of bounds");
		if (!valid())
			throw new RuntimeException("Table is not valid in remove_col()");
		
		for (ArrayList<T> row : table)
			row.remove(index);
	}
	
	public void remove_last_col() {
		if (is_empty())
			throw new RuntimeException("Cannot remove the last column, since table is empty");
		
		remove_col(get_col_count() - 1);
	}
	
	public int get_row_count() {
		if (!valid())
			throw new RuntimeException("Table is not valid in get_row_count()");
		
		return table.size();
	}
	
	public int get_col_count() {
		if (!valid())
			throw new RuntimeException("Table is not valid in get_col_count()");
		if (is_empty())
			return 0;
		
		return table.get(0).size();
	}
	
	public ArrayList<ArrayList<T>> get_data() {
		return table;
	}
	
	public boolean is_empty() {
		return (table.isEmpty()) || (table.get(0).isEmpty());
	}
	
	public void empty() {
		table = new ArrayList<ArrayList<T>>();
	}
	
	private boolean is_in_table(final int row, final int col) {
		if (!valid())
			throw new RuntimeException("Table is not valid");
		
		if (get_row_count() == 0)
			return false;
		
		return (row >= 0) && (row < get_row_count()) && (col >= 0) && (col < get_col_count());
				
	}
	
	private ArrayList<ArrayList<T>> table;
}
