package co.istad.mbanking.api.user;

import org.apache.ibatis.jdbc.SQL;

public class UserProvider {

    private static final String tableName ="users";

    public String buildInsertSql(){
        return new SQL(){{
            INSERT_INTO(tableName);
            VALUES("name","#{u.name}");
            VALUES("gender","#{u.gender}");
            VALUES("one_signal_id","#{u.oneSignalId}");
            VALUES("student_card_id","#{u.studentCardId}");
            VALUES("is_student","#{u.isStudent}");
            VALUES("is_deleted","FALSE");
        }}.toString();
    }
    //====================== Update ============================
    public String buildUpdateByIdSql(){
        return new SQL(){{
            UPDATE(tableName);
            SET("name = #{u.name}");
            SET("gender = #{u.gender}");
            WHERE("id = #{u.id}");
        }}.toString();
    }
//=======================================================

public String buildSelectByIdSql(){
    return new SQL(){{
        SELECT("*");
        FROM(tableName);
        WHERE("id=#{id}","is_deleted=FALSE");
    }}.toString();
}
    //==================== Delete ===================================
    public String buildDeleteByIdSql(){
        return new SQL(){{
            DELETE_FROM(tableName);
            WHERE("id = #{id}");
        }}.toString();
    }


    public String isUpdateIsDeleteUserById() {
        return new SQL() {{
            UPDATE(tableName);
            SET("is_deleted = #{status}");
            WHERE("id = #{id}");
        }}.toString();
//

    }
    public String buildSelectSql(){
        return new SQL(){{
            SELECT("*");
            FROM("users");
            WHERE("name ILIKE '%' || #{name} || '%'","is_deleted=FALSE");
            ORDER_BY("id DESC");
        }

        }.toString();
    }
//=======================================
    public String buildSearch(){
        return new SQL(){{
            SELECT("*");
            FROM(tableName);
            WHERE("name = #{name}");
            LIMIT("name =#{name}");
            ORDER_BY("name ");
        }}.toString();
    }
    public String buildSelectByStudentCardIdSql(){

            return new SQL(){{
                SELECT("*");
                FROM(tableName);
                WHERE("student_card_id = #{studentCardId}","is_deleted=FALSE");
            }}.toString();
    }
}

//}
