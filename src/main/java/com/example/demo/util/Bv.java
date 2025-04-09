package com.example.demo.util;


public class Bv {
    public static String get(int i) {
        // i 只要不超过 1491126880, 长度就固定为 6
        return "bv" + Base58.encode((Integer.MAX_VALUE) ^ i);
    }

    private static class Base58 {
        // "123456789ABCDEFGH JKLMN PQRSTUVWXYZabcdefghijk mnopqrstuvwxyz"
        static String chars = "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz";
        // b站 fZodR9XQDSUm21yCkr6zBqiveYah8bt4xsWpHnJE7jL5VG3guMTKNPAwcF

        public static String encode(int i) {
            StringBuilder sb = new StringBuilder(16);
            if (i == 0) {
                return String.valueOf(chars.charAt(0));
            }
            while (i > 0) {
                sb.append(chars.charAt(i % 58));
                i = i / 58;
            }
            return sb.reverse().toString();
        }

        public static int decode(String str) {
            StringBuilder reverse = new StringBuilder(str).reverse();
            int r = 0;
            for (int i = 0; i < str.length(); i++) {
                int n = chars.indexOf(reverse.charAt(i));
                r += n * (int) Math.pow(58, i);
            }
            return r;
        }

        /*public static void main(String[] args) {
            System.out.println(encode(58));
            System.out.println(encode(9999));
            System.out.println(encode(123456789));
            System.out.println(decode("21"));
            System.out.println(decode("3yQ"));
            System.out.println(decode("BukQL"));

            System.out.println(1491126880 - 2 ^ Integer.MAX_VALUE);
            System.out.println(1491126880 - 1 ^ Integer.MAX_VALUE);
            System.out.println(1491126880 ^ Integer.MAX_VALUE);
            System.out.println(1491126881 ^ Integer.MAX_VALUE);

            System.out.println(encode(656356769));
            System.out.println(encode(656356768));
            System.out.println(encode(656356767));
            System.out.println(encode(656356766));
        }*/
    }

    public static void main(String[] args) {
        String s = Bv.get(10);
        System.out.println(s);
    }
}
