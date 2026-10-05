package org.jopitarelo.desafio_target_1;

import org.jopitarelo.desafio_target_3.services.JurosService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {

    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        JurosService service = new JurosService();

        try {
            System.out.print("Valor (ex: 1000,00): ");
            BigDecimal valor = new BigDecimal(scanner.nextLine().trim().replace(",", "."));

            System.out.print("Data de vencimento (dd/MM/yyyy): ");
            LocalDate vencimento = LocalDate.parse(scanner.nextLine().trim(), FORMATO);

            LocalDate hoje = LocalDate.now();
            BigDecimal juros = service.calcularJuros(valor, vencimento, hoje);
            long dias = service.diasEmAtraso(vencimento, hoje);

            System.out.printf("Dias em atraso: %d%n", dias);
            System.out.printf("Juros: R$ %.2f%n", juros);
            System.out.printf("Total a pagar: R$ %.2f%n", valor.add(juros));
        } catch (NumberFormatException e) {
            System.out.println("Erro: valor inválido.");
        } catch (DateTimeParseException e) {
            System.out.println("Erro: data inválida. Use o formato dd/MM/yyyy.");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}