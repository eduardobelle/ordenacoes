package ordenacoes;

import java.util.Random;

public class App {

    private static long numTrocas = 0;
    private static long numIterac = 0;

    public static int[] geraVetor(int tamanho) {
        Random random = new Random();
        int[] vetor = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = random.nextInt(100);
        }
        return vetor;
    }

    public static void bubbleSort(int[] vetor) {
        int numTrocas = 0;
        int numIterac = 0;
        
        int n = vetor.length;
        int temp = 0;

        long tempoInicial = System.currentTimeMillis();

        for (int i = 0; i < n; i++) {
            numIterac++;
            for (int j = 1; j < (n - i); j++) {
                numIterac++;    
                if (vetor[j - 1] > vetor[j]) {
                    temp = vetor[j - 1];
                    vetor[j - 1] = vetor[j];
                    vetor[j] = temp;
                    numTrocas++;
                }
            }
        }

        long tempoFinal = System.currentTimeMillis();

        System.out.println("====== Media Bubble Sort ======");
        System.out.println("Iteracoes: " + numIterac);
        System.out.println("Trocas: " + numTrocas);
        System.out.println("Tempo: " + (tempoFinal - tempoInicial) + " ms");
    }

    public static void mergeSort(int[] vetor, int[] aux, int inicio, int fim) {
        if (inicio < fim) {
            int meio = (inicio + fim) / 2;
            mergeSort(vetor, aux, inicio, meio);
            mergeSort(vetor, aux, meio + 1, fim);
            merge(vetor, aux, inicio, meio, fim);
        }
    }

    private static void merge(int[] vetor, int[] aux, int inicio, int meio, int fim) {
        for (int k = inicio; k <= fim; k++) {
            aux[k] = vetor[k];
        }

        int i = inicio;
        int j = meio + 1;

        for (int k = inicio; k <= fim; k++) {
            numIterac++;
            if (i > meio) {
                vetor[k] = aux[j];
                j++;
            } else if (j > fim) {
                vetor[k] = aux[i];
                i++;
            } else if (aux[j] < aux[i]) {
                vetor[k] = aux[j];
                j++;
                numTrocas++;
            } else {
                vetor[k] = aux[i];
                i++;
                numTrocas++;
            }
        }
    }

    public static void shellSort(int[] vetor) {
        int n = vetor.length;

        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i += 1) {
                int temp = vetor[i];
                int j;
                for (j = i; j >= gap && vetor[j - gap] > temp; j -= gap) {
                    numIterac++;
                    vetor[j] = vetor[j - gap];
                    numTrocas++;
                }
                vetor[j] = temp;
                numTrocas++;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        int tamanhoVetor = 10000;

        // Bubble Sort
        int[] vetorBubble = geraVetor(tamanhoVetor);
        bubbleSort(vetorBubble);

        // Merge Sort
        int[] vetorMerge = geraVetor(tamanhoVetor);
        int[] aux = new int[vetorMerge.length];
        numTrocas = 0;
        numIterac = 0;
        long tempoInicial = System.currentTimeMillis();
        mergeSort(vetorMerge, aux, 0, vetorMerge.length - 1);
        long tempoFinal = System.currentTimeMillis();
        System.out.println("====== Media Merge Sort ======");
        System.out.println("Iteracoes: " + numIterac);
        System.out.println("Trocas: " + numTrocas);
        System.out.println("Tempo: " + (tempoFinal - tempoInicial) + " ms");

        // Shell Sort
        int[] vetorShell = geraVetor(tamanhoVetor);
        numTrocas = 0;
        numIterac = 0;
        tempoInicial = System.currentTimeMillis();
        shellSort(vetorShell);
        tempoFinal = System.currentTimeMillis();
        System.out.println("====== Media Shell Sort ======");
        System.out.println("Iteracoes: " + numIterac);
        System.out.println("Trocas: " + numTrocas);
        System.out.println("Tempo: " + (tempoFinal - tempoInicial) + " ms");
    }
}
