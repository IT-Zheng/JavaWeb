public class ascii {
    public static void main(String[] args) {
        char[] cs = new char[4];
        cs[0] = 0x2660;
        cs[1] = 0x2665;
        cs[2]   =   0x2663;
        cs[3]   =   0x2666;
        for(int i = 0;i < cs.length; i++){
            System.out.print(cs[i] + "  ");
            System.out.print((int)cs[i] + "  ");
        }
    }
}
