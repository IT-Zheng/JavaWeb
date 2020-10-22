public class Generate {
    public static void main(String[] args) {
        Partner dx = new Partner("对象","女",19);//实例化对象
        dx.Print();
        dx.features("165","45","黄",
                "温和","可爱");//对象的特征
        dx.Print1();
        dx.speak();
        dx.play();
        dx.future();
    }
}
