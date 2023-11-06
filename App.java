package ordenacoes;

import java.util.Random;

public class App {

    // variáveis para armazenar o número de trocas e iterações
    private static long numTrocas = 0;
    private static long numIterac = 0;

    // método para calcular o tamanho do vetor (substitui o uso de vetor.length)
    public static int CriarLength(int[] vetor) {
        int tamanho = 0;
        for (int element : vetor) {
            tamanho++;
        }
        return tamanho;
    }

    // método para gerar um vetor de inteiros aleatórios
    public static int[] geraVetor(int tamanho) {
        Random random = new Random();
        int[] vetor = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            // preenche o vetor com números até 100
            vetor[i] = random.nextInt(100); 
        }
        return vetor;
    }

    // ======================= Bubble Sort =======================
    
    // é fácil mas não muito eficiente
    public static void bubbleSort(int[] vetor) {
        int numTrocas = 0;
        int numIterac = 0;
        
        int n = CriarLength(vetor);
        // variável temporária para ajudar na troca
        int temp = 0; 
        
        // começa a contar o tempo
        long tempoInicial = System.currentTimeMillis(); 

        // dois loops para percorrer o vetor e fazer as trocas (slides)
        // loop externo:
        for (int i = 0; i < n; i++) {
            numIterac++;
            // loop interno: (para cada vez que que o i é incrementado o j percorre todo o array)
            for (int j = 1; j < (n - i); j++) {
                numIterac++;    
                if (vetor[j - 1] > vetor[j]) {
                    // troca os elementos de lugar
                    temp = vetor[j - 1];
                    vetor[j - 1] = vetor[j];
                    vetor[j] = temp;
                    numTrocas++;
                }
            }
        }
        // finaliza a contagem do tempo
        long tempoFinal = System.currentTimeMillis();

        // print métricas
        System.out.println("====== Media Bubble Sort ======");
        System.out.println("Iteracoes: " + numIterac);
        System.out.println("Trocas: " + numTrocas);
        System.out.println("Tempo: " + (tempoFinal - tempoInicial) + " ms");
    }

    // ======================= Merge Sort =======================
    
    // é mais complexo mas muito eficiente
    public static void mergeSort(int[] vetor, int[] aux, int inicio, int fim) {
        if (inicio < fim) {
            int meio = (inicio + fim) / 2; // encontra o meio
            mergeSort(vetor, aux, inicio, meio); // ordena a primeira metade
            mergeSort(vetor, aux, meio + 1, fim); // ordena a segunda metade
            merge(vetor, aux, inicio, meio, fim); // junta as duas metades ordenadas
        }
    }

    // método auxiliar do merge sort que faz a junção das metades
    private static void merge(int[] vetor, int[] aux, int inicio, int meio, int fim) {
        // copia os elementos para o array auxiliar
        for (int k = inicio; k <= fim; k++) {
            aux[k] = vetor[k];
        }

        int i = inicio; // início do primeiro subvetor
        int j = meio + 1; // início do segundo subvetor

        // junta os subvetores de volta no vetor principal
        for (int k = inicio; k <= fim; k++) {
            numIterac++;
            
            //verificamos se terminamos de percorrer algum dos subvetores (i > meio ou j > fim)
            //se sim, pegamos os elementos restantes do outro subvetor e colocamos no vetor principal pois eles já estão ordenados.
            
            if (i > meio) { // se o primeiro subvetor estiver vazio
                vetor[k] = aux[j++];
            } else if (j > fim) { // se o segundo subvetor estiver vazio
                vetor[k] = aux[i++];
            } else if (aux[j] < aux[i]) { // se o elemento do segundo subvetor for menor
                vetor[k] = aux[j++];
                numTrocas++;
            } else { // se o elemento do primeiro subvetor for menor ou igual
                vetor[k] = aux[i++];
                numTrocas++;
            }
        }
    }

    // ======================= Shell Sort =======================
    
    // uma melhoria do Bubble Sort
    public static void shellSort(int[] vetor) {
        // calcula o comprimento do vetor
        int n = CriarLength(vetor);

        // começa com um grande gap e vai reduzindo
        // inicialmente como metade do tamanho do vetor, esse gap vai diminuindo a cada rodada dividindo por 2.
        for (int gap = n / 2; gap > 0; gap /= 2) {
            // faz a ordenação com esse gap
            
            // para cada tamanho de gap ele percorre o vetor, a variável temp armazena o elemento atual
            // que vamos comparar com seus antecessores (separados pelo gap).
            
            for (int i = gap; i < n; i += 1) {
                int temp = vetor[i]; // elemento a ser comparado
                int j;
                // faz a comparação e a troca se necessário
                for (j = i; j >= gap && vetor[j - gap] > temp; j -= gap) {
                    numIterac++;
                    vetor[j] = vetor[j - gap];
                    numTrocas++;
                }
                vetor[j] = temp;
                // após o loop interno se a posição final de temp for diferente da sua posição inicial
                // contabiliza a troca apenas se aconteceu
                if (j != i) { 
                    numTrocas++;
                }
            }
        }
    }

    // método principal onde os vetores são gerados e os métodos de ordenação chamados
    public static void main(String[] args) throws Exception {
        int tamanhoVetor = 10000;

        // Bubble Sort
        int[] vetorBubble = geraVetor(tamanhoVetor);
        bubbleSort(vetorBubble);

        // Merge Sort
        int[] vetorMerge = geraVetor(tamanhoVetor);
        int[] aux = new int[tamanhoVetor];
        numTrocas = 0; // zera o contador de trocas
        numIterac = 0; // zera o contador de iterações
        long tempoInicial = System.currentTimeMillis(); // inicia o contador de tempo
        mergeSort(vetorMerge, aux, 0, tamanhoVetor - 1);
        long tempoFinal = System.currentTimeMillis(); // finaliza o contador de tempo
        System.out.println("====== Media Merge Sort ======");
        System.out.println("Iteracoes: " + numIterac);
        System.out.println("Trocas: " + numTrocas);
        System.out.println("Tempo: " + (tempoFinal - tempoInicial) + " ms");

        // Shell Sort
        int[] vetorShell = geraVetor(tamanhoVetor);
        numTrocas = 0; // zera novamente para a próxima ordenação
        numIterac = 0;
        tempoInicial = System.currentTimeMillis(); // reinicia o contador de tempo
        shellSort(vetorShell);
        tempoFinal = System.currentTimeMillis();
        System.out.println("====== Media Shell Sort ======");
        System.out.println("Iteracoes: " + numIterac);
        System.out.println("Trocas: " + numTrocas);
        System.out.println("Tempo: " + (tempoFinal - tempoInicial) + " ms");
    }
}
