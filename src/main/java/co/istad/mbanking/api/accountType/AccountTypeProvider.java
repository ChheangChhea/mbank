package co.istad.mbanking.api.accountType;

import org.apache.ibatis.jdbc.SQL;

public class AccountTypeProvider {
    private final String tb_AccountTyp="account_types";
    public String buildSelectSQL(){
        return new SQL(){{
            //TODO
            SELECT("*");
            FROM(tb_AccountTyp);
        }}.toString();
    }
    //==================Insert Account Type==========================
    public String buildInsertSql(){
        return new SQL(){{
            INSERT_INTO(tb_AccountTyp);
           VALUES("name","#{n.name}");
        }}.toString();
    }
    //=============== Delete Account Type ============================
    public String buildDeleteAccountType(){
        return new SQL(){{
                DELETE_FROM(tb_AccountTyp);
                WHERE("id= #{id}");
        }}.toString();
    }

    public String buildSelectByIdSql() {
        return new SQL() {{
            SELECT("*");
            FROM("account_types");
            WHERE("id = #{id}");
        }}.toString();
    }
}
