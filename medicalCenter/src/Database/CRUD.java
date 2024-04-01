package Database;

import java.util.List;

public interface CRUD {
    public Object insert(Object obj);
    public List<Object> readAll();
    public boolean update(Object obj);
    public boolean delete (Object obj);
}
