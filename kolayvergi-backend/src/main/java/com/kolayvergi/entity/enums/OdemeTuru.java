package com.kolayvergi.entity.enums;

public enum OdemeTuru {

    /**
     * Nakit ödeme:
     * - Taksit olmayabilir.
     */
    NAKIT,

    /**
     * Kredi ile ödeme:
     * - Taksit zorunludur.
     * - En fazla 6 taksit yapılabilir.
     */
    KREDI,

    /**
     * Kredi kartı ile ödeme:
     * - Taksit zorunludur.
     * - En fazla 3 taksit yapılabilir.
     */
    KREDI_KARTI

}
