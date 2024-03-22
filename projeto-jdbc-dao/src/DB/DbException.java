package DB;
import java.sql.SQLException;

public class DbException extends SQLException {
    public DbException(String msg){super(msg);}
}