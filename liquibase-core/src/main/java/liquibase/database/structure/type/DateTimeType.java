package liquibase.database.structure.type;

import liquibase.statement.DatabaseFunction;
import liquibase.database.Database;

public class DateTimeType extends DataType {
    public DateTimeType() {
        super("DATETIME", 0, 1);
    }

    public DateTimeType(String dataTypeName) {
        super(dataTypeName, 0, 1);
    }

    @Override
    public boolean getSupportsPrecision() {
        return true;
    }

    @Override
    public String convertObjectToString(Object value, Database database) {
        if (value == null) {
            return null;
        } else if (value instanceof DatabaseFunction) {
            return ((DatabaseFunction) value).getValue();
        } else if (database.getDatabaseFunctions() != null && database.getDatabaseFunctions().contains(new DatabaseFunction(value.toString()))) {
            return value.toString();
        }

        return database.getDateTimeLiteral(((java.sql.Timestamp) value));
    }
}
