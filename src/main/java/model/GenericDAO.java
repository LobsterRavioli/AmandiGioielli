package model;

import java.sql.SQLException;
import java.util.Collection;

public interface GenericDAO <T>
{

	public T doRetrieve(T item) throws SQLException;

	public Collection<T> doRetrieveAll(String filter) throws SQLException;

	public void doSave(T item) throws SQLException;

	public void doUpdate(T item) throws SQLException;

	public void doDelete(T item) throws SQLException;
}
