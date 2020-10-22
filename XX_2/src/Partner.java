public class Partner {
    private String name;//姓名
    private String sex;//性别
    private int age;//年龄

    private String height;  //身高
    private String weight;  //体重
    private String skin_colour; //肤色
    private String character;  //性格
    private String type;    //类型

    public void speak(){
        System.out.println(name+"正在向你打招呼!");
    }
    public void play(){
        System.out.println(sex == "男"?"他":"她"+"正在陪你玩耍");
    }
    public void future(){
        System.out.println(name+"说:还有更多东东没有展示呐！");
    }

    public Partner(String name, String sex, int age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }
    //特征
    public void features(String height, String weight, String skin_colour,
                         String character, String type) {
        this.height = height;
        this.weight = weight;
        this.skin_colour = skin_colour;
        this.character = character;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }

    public void Print() {
        System.out.println("姓名：" + name + ", 性别：" + sex + ", 年龄" + age);
    }

    public void Print1() {
        System.out.println("身高：" + height +"cm"
                + ", 体重：" + weight +"kg"
                + ", 肤色：" + skin_colour+"色"
                + ", 性格：" + character
                + ", 类型：" + type);
    }
}
