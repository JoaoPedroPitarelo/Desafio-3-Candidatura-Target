package org.jopitarelo.desafio_target_1;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class JurosService {

    private static final BigDecimal TAXA_DIARIA = new BigDecimal("0.025");

    public BigDecimal calcularJuros(BigDecimal valor, LocalDate vencimento) {
        return calcularJuros(valor, vencimento, LocalDate.now());
    }

    public BigDecimal calcularJuros(BigDecimal valor, LocalDate vencimento, LocalDate hoje) {
        if (valor == null || valor.signum() <= 0) {
            throw new IllegalArgumentException("O valor deve ser maior que zero.");
        }
        if (vencimento == null) {
            throw new IllegalArgumentException("Informe a data de vencimento.");
        }

        long diasAtraso = ChronoUnit.DAYS.between(vencimento, hoje);
        if (diasAtraso <= 0) {
            return BigDecimal.ZERO.setScale(2);
        }

        return valor
                .multiply(TAXA_DIARIA)
                .multiply(BigDecimal.valueOf(diasAtraso))
                .setScale(2, RoundingMode.HALF_UP);
    }

    public long diasEmAtraso(LocalDate vencimento, LocalDate hoje) {
        return Math.max(0, ChronoUnit.DAYS.between(vencimento, hoje));
    }
}
