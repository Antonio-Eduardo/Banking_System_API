package com.eduardodev.banking_system_api.interfaces;

import java.math.BigDecimal;


public interface Tax {
    BigDecimal tax(BigDecimal valor);
}
