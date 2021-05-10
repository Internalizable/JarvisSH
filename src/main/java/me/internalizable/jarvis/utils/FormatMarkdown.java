package me.internalizable.jarvis.utils;

import lombok.Getter;

public enum FormatMarkdown {

    BOLD(1), ITALIC(3), STRIKETHROUGH(9), DIM(2), UNDERLINED(4), BLINK(5), INVERTED(7);

    @Getter
    private final int code;

    FormatMarkdown(int code) {
        this.code = code;
    }

}
