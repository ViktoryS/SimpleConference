package model.dao.utils;


public interface QueryBuilder {
    String SELECT = "SELECT";
    String ALL = "*";
    String SPACE = " ";
    String FROM = "FROM";
    String WHERE = "WHERE";
    String COMMA = ",";
    String EQUI = "=";
    String QUESTION = "?";
    String SET = "SET";
    String INSERT = "INSERT";
    String INTO = "INTO";
    String VALUES = "VALUES";
    String OPEN = "(";
    String CLOSE = ")";
    String ID = "id";
    String DOT = ".";
    String UPDATE = "UPDATE";
    String DELETE = "DELETE";
    String JOIN = "JOIN";
    String IS = "IS";
    String NULL = "NULL";
    String ON = "0N";


    static String getAll(String TABLE) {
        return new StringBuilder()
                .append(SELECT)
                .append(SPACE)
                .append(ALL)
                .append(SPACE)
                .append(FROM)
                .append(SPACE)
                .append(TABLE)
                .toString();
    }


    static String addEntity(String TABLE, String... parameters) {
        StringBuilder builder = new StringBuilder()
                .append(INSERT)
                .append(SPACE)
                .append(INTO)
                .append(SPACE)
                .append(TABLE)
                .append(OPEN);
        for (int i = 0; i < parameters.length; i++) {
            builder.append(parameters[i]);
            if (i != parameters.length - 1) {
                builder.append(COMMA)
                        .append(SPACE);
            }
        }
        builder.append(CLOSE)
                .append(SPACE)
                .append(VALUES)
                .append(SPACE)
                .append(OPEN);
        for (int i = 0; i < parameters.length; i++) {
            builder.append(QUESTION);
            if (i != parameters.length - 1) {
                builder.append(COMMA)
                        .append(SPACE);
            }
        }
        return builder.append(CLOSE).toString();
    }

    static String findById(String TABLE) {
        return new StringBuilder().append(SELECT)
                .append(SPACE)
                .append(ALL)
                .append(SPACE)
                .append(FROM)
                .append(SPACE)
                .append(TABLE)
                .append(SPACE)
                .append(WHERE)
                .append(SPACE)
                .append(TABLE)
                .append(DOT)
                .append(ID)
                .append(EQUI)
                .append(QUESTION)
                .toString();
    }

    static String update(String TABLE, String... parameters) {
        StringBuilder builder = new StringBuilder()
                .append(UPDATE)
                .append(SPACE)
                .append(TABLE)
                .append(SPACE)
                .append(SET)
                .append(SPACE);
        for (int i = 0; i < parameters.length; i++) {
            builder.append(parameters[i])
                    .append(EQUI)
                    .append(QUESTION);
            if (i != parameters.length - 1) {
                builder.append(COMMA)
                        .append(SPACE);
            }
        }
        return builder.append(SPACE)
                .append(WHERE)
                .append(SPACE)
                .append(TABLE)
                .append(DOT)
                .append(ID)
                .append(EQUI)
                .append(QUESTION)
                .toString();
    }

    static String delete(String TABLE) {
        return new StringBuilder().append(DELETE)
                .append(SPACE)
                .append(FROM)
                .append(SPACE)
                .append(TABLE)
                .append(SPACE)
                .append(WHERE)
                .append(SPACE)
                .append(TABLE)
                .append(DOT)
                .append(ID)
                .append(EQUI)
                .append(QUESTION)
                .toString();
    }

    static String findWithCondition(
            String TABLE_FROM,
            String TABLE_CONDITION,
            String FIELD_CONDITION) {
        return new StringBuilder().append(SELECT)
                .append(SPACE)
                .append(TABLE_FROM)
                .append(DOT)
                .append(ALL)
                .append(SPACE)
                .append(FROM)
                .append(SPACE)
                .append(TABLE_FROM)
                .append(COMMA)
                .append(SPACE)
                .append(TABLE_CONDITION)
                .append(SPACE)
                .append(WHERE)
                .append(SPACE)
                .append(fieldWithTable(TABLE_CONDITION, FIELD_CONDITION))
                .append(EQUI)
                .append(QUESTION)
                .toString();
    }

    static String findWithCondition(
            String TABLE_FROM,
            String FIELD_CONDITION) {
        return new StringBuilder().append(SELECT)
                .append(SPACE)
                .append(TABLE_FROM)
                .append(DOT)
                .append(ALL)
                .append(SPACE)
                .append(FROM)
                .append(SPACE)
                .append(TABLE_FROM)
                .append(SPACE)
                .append(WHERE)
                .append(SPACE)
                .append(fieldWithTable(TABLE_FROM, FIELD_CONDITION))
                .append(EQUI)
                .append(QUESTION)
                .toString();
    }

    static String findWithNULLCondition(
            String TABLE_FROM,
            String TABLE_CONDITION,
            String FIELD_CONDITION) {
        return new StringBuilder().append(SELECT)
                .append(SPACE)
                .append(TABLE_FROM)
                .append(DOT)
                .append(ALL)
                .append(SPACE)
                .append(FROM)
                .append(SPACE)
                .append(TABLE_FROM)
                .append(COMMA)
                .append(SPACE)
                .append(TABLE_CONDITION)
                .append(SPACE)
                .append(WHERE)
                .append(SPACE)
                .append(FIELD_CONDITION)
                .append(SPACE)
                .append(IS)
                .append(SPACE)
                .append(NULL)
                .toString();
    }

    static String findWithConditions(
            String TABLE_FROM,
            String FIELD_FROM,
            String TABLE_CONDITION,
            String FIELD_CONDITION) {
        return new StringBuilder().append(SELECT)
                .append(SPACE)
                .append(TABLE_FROM)
                .append(DOT)
                .append(ALL)
                .append(COMMA)
                .append(SPACE)
                .append(fieldWithTable(TABLE_CONDITION, FIELD_FROM))
                .append(SPACE)
                .append(FROM)
                .append(SPACE)
                .append(TABLE_CONDITION)
                .append(SPACE)
                .append(JOIN)
                .append(SPACE)
                .append(TABLE_FROM)
                .append(SPACE)
                .append(WHERE)
                .append(SPACE)
                .append(fieldWithTable(TABLE_CONDITION, FIELD_CONDITION))
                .append(EQUI)
                .append(QUESTION)
                .toString();
    }

    static String findWithConditionsManyToMany(
            String TABLE_FROM,
            String FIELD_FROM,
            String TABLE_CONDITION,
            String FIELD_CONDITION) {
        return new StringBuilder().append(SELECT)
                .append(SPACE)
                .append(TABLE_FROM)
                .append(DOT)
                .append(ALL)
                .append(SPACE)
                .append(FROM)
                .append(SPACE)
                .append(TABLE_CONDITION)
                .append(SPACE)
                .append(JOIN)
                .append(SPACE)
                .append(TABLE_FROM)
                .append(SPACE)
                .append(ON)
                .append(SPACE)
                .append(fieldWithTable(TABLE_FROM, ID))
                .append(EQUI)
                .append(fieldWithTable(TABLE_CONDITION, FIELD_FROM))
                .append(SPACE)
                .append(WHERE)
                .append(SPACE)
                .append(fieldWithTable(TABLE_CONDITION, FIELD_CONDITION))
                .append(EQUI)
                .append(QUESTION)
                .toString();
    }

    static String fieldWithTable(String table, String field){
        return new StringBuilder()
                .append(table)
                .append(DOT)
                .append(field)
                .toString();
    }
}

