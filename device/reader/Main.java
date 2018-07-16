package com.reader;

public class Main {

    private static String read() {
        SerialPortCommunication communication = new SerialPortCommunication("/dev/ttyUSB0");
        int i = 0;
        int[] arr = new int[16];
        StringBuilder c = new StringBuilder();
        long currTime = 0;
        long prevUid = 0;
        try {
            communication.connect();
            while (true) {
                int a = communication.readSymbol();
                if (a != -1) {
                    if (i < 14) {
                        arr[i] = a;
                        i++;
                    } else {
                        c.append(toEight(Integer.toBinaryString(arr[12])))
                                .append(toEight(Integer.toBinaryString(arr[11])))
                                .append(toEight(Integer.toBinaryString(arr[10])))
                                .append(toEight(Integer.toBinaryString(arr[9])));
                        long uid = Long.parseLong(c.toString(), 2);

                        if (prevUid != uid || System.currentTimeMillis() - currTime >= 1000) {
                            return String.valueOf(uid);
                        }
                        c = new StringBuilder();
                        arr = new int[16];
                        arr[i] = a;
                        i = 0;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String toEight(String s) {
        StringBuilder result = new StringBuilder();
        if (s.length() != 8) {
            int size = 8 - s.length();
            for (int i = 0; i < size; i++) {
                result.append("0");
            }
            result.append(s);
        } else {
            return s;
        }
        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println("Start listening");
        System.out.println(read());
        System.out.println("Finished");
        System.exit(0);
    }
}
