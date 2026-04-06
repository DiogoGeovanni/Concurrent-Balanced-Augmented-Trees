public class Main {

    static class Node {
        String key;
        Node left;
        Node right;

        Node(String key) {
            this.key = key;
        }

        Node(String key, Node left, Node right) {
            this.key = key;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {

        Node raiz = montarExemploMuitoGrande();

        System.out.println("ALTURA DA ARVORE ANTES: " + altura(raiz));
        System.out.println("\nARVORE ANTES DO BALANCEAMENTO:");
        imprimirArvore(raiz, 0);

        Node novaRaiz = rebalancearSeNecessario(raiz);

        System.out.println("\nALTURA DA ARVORE DEPOIS: " + altura(novaRaiz));
        System.out.println("\nARVORE DEPOIS DO BALANCEAMENTO:");
        imprimirArvore(novaRaiz, 0);
    }

    public static Node montarExemploMuitoGrande() {

        // folhas do patch principal
        Node alpha = new Node("alpha");
        Node beta  = new Node("beta");
        Node gamma = new Node("gamma");
        Node delta = new Node("delta");

        // patch do artigo
        Node C = new Node("C", alpha, beta);
        Node B = new Node("B", C, gamma);
        Node A = new Node("A", B, delta);

        // caminho longo acima do patch para aumentar altura
        Node p10 = new Node("P10", A, criarSubArvoreBalanceada("R10", 3));
        Node p9  = new Node("P9", p10, criarSubArvoreBalanceada("R9", 3));
        Node p8  = new Node("P8", p9, criarSubArvoreBalanceada("R8", 3));
        Node p7  = new Node("P7", p8, criarSubArvoreBalanceada("R7", 3));
        Node p6  = new Node("P6", p7, criarSubArvoreBalanceada("R6", 3));
        Node p5  = new Node("P5", p6, criarSubArvoreBalanceada("R5", 3));
        Node p4  = new Node("P4", p5, criarSubArvoreBalanceada("R4", 3));
        Node p3  = new Node("P3", p4, criarSubArvoreBalanceada("R3", 3));
        Node p2  = new Node("P2", p3, criarSubArvoreBalanceada("R2", 3));
        Node p1  = new Node("P1", p2, criarSubArvoreBalanceada("R1", 3));

        // lado direito grande também
        Node ladoDireito = criarSubArvoreBalanceada("Q", 5);

        // raiz principal
        Node root = new Node("ROOT", p1, ladoDireito);

        return root;
    }

    public static Node criarSubArvoreBalanceada(String prefixo, int nivel) {
        if (nivel == 0) {
            return new Node(prefixo + "_0");
        }

        Node esquerda = criarSubArvoreBalanceada(prefixo + "L", nivel - 1);
        Node direita = criarSubArvoreBalanceada(prefixo + "R", nivel - 1);

        return new Node(prefixo + "_" + nivel, esquerda, direita);
    }

    public static Node rebalancearSeNecessario(Node root) {

        if (root == null) {
            return null;
        }

        if (ehPatchDoExemplo(root)) {
            return aplicarPatchReplacement(root);
        }

        root.left = rebalancearSeNecessario(root.left);
        root.right = rebalancearSeNecessario(root.right);

        return root;
    }

    public static boolean ehPatchDoExemplo(Node A) {

        if (A == null) return false;
        if (A.left == null) return false;

        Node B = A.left;
        if (B.left == null) return false;

        if (!A.key.equals("A")) return false;
        if (!B.key.equals("B")) return false;
        if (!B.left.key.equals("C")) return false;

        return true;
    }

    public static Node aplicarPatchReplacement(Node A) {

        Node B = A.left;
        Node C = B.left;

        Node alpha = C.left;
        Node beta = C.right;
        Node gamma = B.right;
        Node delta = A.right;

        Node C_linha = new Node("C'", alpha, beta);
        Node A_novo = new Node("A", gamma, delta);
        Node B_linha = new Node("B'", C_linha, A_novo);

        return B_linha;
    }

    public static int altura(Node node) {
        if (node == null) {
            return 0;
        }

        int altEsq = altura(node.left);
        int altDir = altura(node.right);

        return 1 + Math.max(altEsq, altDir);
    }

    public static void imprimirArvore(Node node, int nivel) {

        if (node == null) return;

        imprimirArvore(node.right, nivel + 1);

        for (int i = 0; i < nivel; i++) {
            System.out.print("    ");
        }.

        System.out.println(node.key);

        imprimirArvore(node.left, nivel + 1);
    }
}
