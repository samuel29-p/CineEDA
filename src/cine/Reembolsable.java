package cine;

public interface Reembolsable {

    double calcularReembolso(int horasAntes);

    boolean esReembolsable(int horasAntes);
}