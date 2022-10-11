public class Hello {
    private static final int COUNTER = 10;
    private void sayHello(Box box){
        box.greetingsCounter++;
        System.out.println("Hello world!!! " +  box.greetingsCounter);
    }
    public static void main(String[] args) {
        Hello hi = new Hello();  // hi to referencja
        Hello hi2 = hi;
        Box box = new Box();
        hi.sayHello(box);
        for (int i = 0; i < COUNTER; i++){
            hi.sayHello(box);
        }
        hi2.sayHello(box);
    }
}