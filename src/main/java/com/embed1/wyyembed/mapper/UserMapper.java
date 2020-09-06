package com.embed1.wyyembed.mapper;

        import com.embed1.wyyembed.model.User;
        import org.apache.ibatis.annotations.Insert;
        import org.apache.ibatis.annotations.Mapper;
        import org.apache.ibatis.annotations.Select;
        import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    @Insert("insert into user (username,password,userphone) values (#{username},#{password},#{userphone})")
    void adduser(User user);
    @Select("select * from user where username=#{username}")
    User finduserbyname(String username );
    @Select("select * from user where username=#{username} and password=#{password} and userphone=#{userphone}")
    User checkuser(String username, String password ,String userphone);
    @Update("update user set token=#{token} where username=#{username}")
    void updateUser(User user);

}