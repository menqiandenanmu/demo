package com.mall.util.common.lang.i18n;

/**
 * 代表一个<code>CharConverter</code>方案的提供者。
 *
 * @author caedmon
 */
public interface CharConverterProvider {
    /**
     * 创建一个新的converter。
     */
    CharConverter createCharConverter();
}
