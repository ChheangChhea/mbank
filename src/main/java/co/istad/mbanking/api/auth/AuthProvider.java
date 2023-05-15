package co.istad.mbanking.api.auth;

import org.apache.ibatis.jdbc.SQL;


public class AuthProvider {
    private static final String tableUser ="users";
            public String buildRegisterSql(){
                return new SQL() {{
                    INSERT_INTO(tableUser);
                    VALUES("email", "#{u.email}");
                    VALUES("password", "#{u.password}");
                    VALUES("is_verified", "#{u.isVerified}");
                    VALUES("is_deleted", "FALSE");
                }}.toString();
            }
    }

