package org.example;

public class Main {
    public static void main(String[] args) {
        CodeBlock codeBlock = new CodeBlock.Builder()
                .setCode("System.out.println(\"Hello, world!\");")
                .setLanguage("java")
                .build();

        System.out.println(codeBlock.toString());
    }
}