public class Thing2 implements DaFace {

    int personalInt2;

    public Thing2(int i) {
        personalInt2 = i;
    }

    public int doThis(int num) {
        System.out.println(personalInt2);
        return num*num;
    }

    public int getPersonalInt2() {
        return personalInt2;
    }

}
