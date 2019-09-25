package LectureExamples1;

public class AnonymousClassExample {

    interface HelloWorld {
        public void greet();
        public void greetSomeone(String someone);
    }

    public void sayHello() {
        class EnglishGreeting implements HelloWorld {
            String name = "world";
            public void greet() {
                greetSomeone("world");
            }
            public void greetSomeone(String someone) {
                name = someone;
                System.out.println("Hello " + name);
            }
        }

        HelloWorld englishGreeting = new EnglishGreeting();

        HelloWorld frenchGreeting = new HelloWorld() {
            String name = "tout le monde";
            public void greet() {
                greetSomeone("tout le monde");
            }
            public void greetSomeone(String someone) {
                name = someone;
                System.out.println("Salut " + name);
            }
        };

        HelloWorld spanishGreeting = new HelloWorld() {
            String name = "mundo";
            public void greet() {
                greetSomeone("mundo");
            }
            public void greetSomeone(String someone) {
                name = someone;
                System.out.println("Hola, " + name);
            }
        };

        HelloWorld ukrainianGreeting = new HelloWorld() {
            @Override
            public void greet() {
                greetSomeone("Світ");
            }

            @Override
            public void greetSomeone(String someone) {
                System.out.printf("Привіт, %s!\n", someone);
            }
        };

        englishGreeting.greet();
        frenchGreeting.greetSomeone("Fred");
        spanishGreeting.greet();
        ukrainianGreeting.greet();
        ukrainianGreeting.greetSomeone("Kate");
    }

    public static void main(String[] args) {
        new AnonymousClassExample().sayHello();
    }
}
