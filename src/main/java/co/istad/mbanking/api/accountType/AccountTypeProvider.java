package co.istad.mbanking.api.accountType;

import org.apache.ibatis.jdbc.SQL;

public class AccountTypeProvider {
    public String buildSelectSQL(){
        return new SQL(){{
            //TODO
            SELECT("*");
            FROM("account_types");
        }}.toString();
    }
}
