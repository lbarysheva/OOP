package org.example;

/**
 * Class representing a block of code in Markdown.
 */
public class CodeBlock extends Element {
    private final String code;
    private final String language; // язык программирования

    public CodeBlock(String code, String language) {
        this.code = code;
        this.language = language;
    }

    @Override
    public String toString() {
        // Форматирование Markdown для блока кода
        if (language == null || language.isEmpty()) {
            return "```\n" + code + "\n```";
        } else {
            return "```" + language + "\n" + code + "\n```";
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CodeBlock codeBlock = (CodeBlock) obj;
        return code.equals(codeBlock.code) &&
                (language != null ? language.equals(codeBlock.language) : codeBlock.language == null);
    }

    @Override
    public int hashCode() {
        int result = code.hashCode();
        result = 31 * result + (language != null ? language.hashCode() : 0);
        return result;
    }

    /**
     * Builder for creating instances of CodeBlock.
     */
    public static class Builder implements org.example.Builder {
        private String code;
        private String language;

        public Builder setCode(String code) {
            this.code = code;
            return this;
        }

        public Builder setLanguage(String language) {
            this.language = language;
            return this;
        }

        @Override
        public CodeBlock build() {
            return new CodeBlock(code, language);
        }
    }
}
