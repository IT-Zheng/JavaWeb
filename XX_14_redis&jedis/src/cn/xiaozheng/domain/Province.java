package cn.xiaozheng.domain;

/**
 * @Package: cn.xiaozheng.domain
 * @ClassName: Province
 * @Author: Bad Body
 * @CreateTime: 2020/7/28 0:33
 * @Description:
 */
public class Province {
    private int id;
    private String name;

    @Override
    public String toString() {
        return "Province{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
