public class Thing1 implements DaFace {

    int personalInt1;

    public Thing1(int i) {
        personalInt1 = i;
    }

    public int doThis(int num) {
        System.out.println(personalInt1);
        return num+num;
    }

    public int getPersonalInt1() {
        return personalInt1;
    }
}
