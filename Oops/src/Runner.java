import java.util.HashMap;

public class Runner {

    public static void main(String args[]) {
        HashMap<Integer, DaFace> map = new HashMap<Integer, DaFace>();

        map.put(new Integer(0), new Thing1(2));
        map.put(new Integer(1), new Thing2(3));

        //should return 20
        DaFace thing1 = map.get(new Integer(0));
        System.out.println(thing1.doThis(10));

        //should return 100
        DaFace thing2 = map.get(new Integer(1));
        System.out.println(thing2.doThis(10));
        thing2.getPersonalInt2();

    }

}
