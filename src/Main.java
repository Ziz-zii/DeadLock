void main() {

    class DeadLockExample {

        private static final Object resA = new Object();
        private static final Object resB = new Object();

        public static void main(String[] args) {

            Thread thread1 = new Thread(() -> {
                System.out.println("Поток 1: Пытается захватить A");
                synchronized (resA) {
                    System.out.println("Поток 1: Захватил A");

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("Поток 1: Пытается захватить B");
                    synchronized (resB) {
                        System.out.println("Поток 1: Захватил B");
                    }
                }
            });

            Thread thread2 = new Thread(() -> {
                System.out.println("Поток 2: Пытается захватить B");
                synchronized (resB) {
                    System.out.println("Поток 2: Захватил B");

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("Поток 2: Пытается захватить A");
                    synchronized (resA) {
                        System.out.println("Поток 2: Захватил A");
                    }
                }
            });

            thread1.start();
            thread2.start();

            try {
                thread1.join();
                thread2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Программа завершена");
        }
    }

}



