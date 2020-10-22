package cn.xiaozheng.domain;

/**
 * @Package: cn.xiaozheng.domain
 * @ClassName: Login
 * @Author: Bad Body
 * @CreateTime: 2020/7/15 21:09
 * @Description:
 */
public class Login {
    private String username;
    private  String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Login{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
