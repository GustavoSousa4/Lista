import java.lang.annotation.Retention;
import java.lang.reflect.*;

public class ListaSimplesDesordenada<X> {
    private class No {
        private X info;
        private No prox;

        public No(X i, No p) {
            this.info = i;
            this.prox = p;
        }

        public No(X i) {
            this.info = i;
            this.prox = null;
        }

        public X getInfo() {
            return this.info;
        }

        public No getProx() {
            return this.prox;
        }

        public void setInfo(X i) {
            this.info = i;
        }

        public void setProx(No p) {
            this.prox = p;
        }
    } // fim da classe No

    private No primeiro, ultimo;

    public ListaSimplesDesordenada() {
        this.primeiro = this.ultimo = null;
    }

    private X meuCloneDeX(X x) {
        X ret = null;

        try {
            Class<?> classe = x.getClass();
            Class<?>[] tipoDosParms = null;
            Method metodo = classe.getMethod("clone", tipoDosParms);
            Object[] parms = null;
            ret = (X) metodo.invoke(x, parms);
        } catch (NoSuchMethodException erro) {
        } catch (IllegalAccessException erro) {
        } catch (InvocationTargetException erro) {
        }

        return ret;
    }

    public void guardeUmItemNoInicio(X i) throws Exception {
        if (i == null)
            throw new Exception("Informacao ausente");

        X inserir = null;
        if (i instanceof Cloneable)
            inserir = (X) meuCloneDeX(i);
        else
            inserir = i;

        this.primeiro = new No(inserir, this.primeiro);

        if (this.ultimo == null)
            this.ultimo = this.primeiro;
    }

    public void guardeUmItemNoFinal(X i) throws Exception {
        if (i == null)
            throw new Exception("Informacao ausente");

        X inserir = null;
        if (i instanceof Cloneable)
            inserir = (X) meuCloneDeX(i);
        else
            inserir = i;

        if (this.ultimo == null) // && this.primeiro==null
        {
            this.ultimo = new No(inserir);
            this.primeiro = this.ultimo;
        } else {
            this.ultimo.setProx(new No(inserir));
            this.ultimo = this.ultimo.getProx();
        }
    }

    // Invertendo a lista
    public void inverterLista() throws Exception {
        ListaSimplesDesordenada<X> lista = this.getReverse();
        this.primeiro = lista.primeiro;
        this.ultimo = lista.ultimo;
    }

    // Retornando a lista invertida
    public ListaSimplesDesordenada<X> getReverse() throws Exception {
        ListaSimplesDesordenada<X> listaSimplesDesordenada = new ListaSimplesDesordenada<>();
        for (No atual = this.primeiro; atual != null; atual = atual.getProx())
            try {
                listaSimplesDesordenada.guardeUmItemNoInicio(atual.getInfo());
            } catch (Exception e) {
            }
        return listaSimplesDesordenada;
    }

    public X recupereItemDoInicio() throws Exception {
        if (this.primeiro == null/* &&this.fim==null) */)
            throw new Exception("Nada a obter");

        X ret = this.primeiro.getInfo();
        if (ret instanceof Cloneable)
            ret = meuCloneDeX(ret);

        return ret;
    }

    public X recupereItemDoFinal() throws Exception {
        if (this.primeiro == null/* &&this.ultimo==null) */)
            throw new Exception("Nada a obter");

        X ret = this.ultimo.getInfo();
        if (ret instanceof Cloneable)
            ret = meuCloneDeX(ret);

        return ret;
    }

    public void removaItemDoInicio() throws Exception {
        if (this.primeiro == null /* && this.ultimo==null */)
            throw new Exception("Nada a remover");

        if (this.primeiro == this.ultimo) // so 1 elemento
        {
            this.primeiro = this.ultimo = null;
            return;
        }

        this.primeiro = this.primeiro.getProx();
    }

    public void removaItemDoFinal() throws Exception {
        if (this.primeiro == null)
            throw new Exception("Nada a remover");
        if (this.primeiro == this.ultimo) {
            this.primeiro = this.ultimo = null;
            return;
        }

        No atual;
        for (atual = this.primeiro; atual.getProx() != this.ultimo; atual = atual.getProx())
            atual.setProx(null);
        this.ultimo = atual;
    }

    public int getQuantidade() {
        No atual = this.primeiro;
        int ret = 0;

        while (atual != null) {
            ret++;
            atual = atual.getProx();
        }

        return ret;
    }

    // Concatenando a Lista passada como parametro à this
    public ListaSimplesDesordenada<X> concatenando(ListaSimplesDesordenada<X> lis) throws Exception {
        if (lis == null)
            throw new Exception("Lista vazia");
        for (No prox = lis.primeiro; prox != null; prox = prox.getProx())
            this.guardeUmItemNoFinal(prox.getInfo());
        return this;
    }

    // Verifica quais itens são iguais e retorna dentro de uma nova lista
    public ListaSimplesDesordenada<X> elemIguais(ListaSimplesDesordenada<X> lis) throws Exception {
        if (lis == null)
            throw new Exception("Lista vazia");
        ListaSimplesDesordenada<X> ret = new ListaSimplesDesordenada<>();
        No atual, prox;
        for (prox = lis.primeiro; prox != null; prox = prox.getProx()) {
            for (atual = this.primeiro; atual != null; atual = atual.getProx()) {
                if (atual.getInfo().equals(prox.getInfo()))
                    ret.guardeUmItemNoFinal(atual.getInfo());
            }
        }
        return ret;
    }

    // Retorna a informação pedida pela iezima posição da Lista
    public X getIezima(int i) throws Exception {
        if (i < 0)
            throw new Exception("Posição invalida");
        No atual = this.primeiro;
        int posi = 0;
        for (;;) {
            if (atual == null)
                throw new Exception("Posição inválida");
            if (posi == i) {
                if (atual.getInfo() instanceof Cloneable)
                    return meuCloneDeX(atual.getInfo());
                else
                    return atual.getInfo();
            }
            posi++;
            atual = atual.getProx();
        }
    }

    /*
     * Retorna em String se há mais de uma informação igual ao passado pelo
     * parametro
     * dentro da mesma lista
     */
    public String getQuantidadeElement(X info) throws Exception {
        if (info == null)
            throw new Exception("Informacao ausente");
        No atual = this.primeiro;
        int ret = 0;
        while (atual != null) {
            if (atual.getInfo().equals(info))
                ret++;
            atual = atual.getProx();
        }
        if (ret == 1)
            return "A lista não possui '" + info + "' se repetindo";
        return "A lista possui: " + ret + " elementos iguais";

    }

    // retorna o tamanho da Lista
    public int getTamanhoLista() throws Exception {

        int contador = 0;
        No atual = this.primeiro;
        while (atual != null) {
            contador++;
            atual = atual.getProx();
        }
        return contador;
    }

    public boolean tem(X i) throws Exception {
        if (i == null)
            throw new Exception("Informacao ausente");

        No atual = this.primeiro;

        while (atual != null) {
            if (i.equals(atual.getInfo()))
                return true;

            atual = atual.getProx();
        }

        return false;
    }

    public void removaItemIndicado(X i) throws Exception {
        if (i == null)
            throw new Exception("Informacao ausente");

        boolean removeu = false;

        for (;;) // FOR EVER (repete até break)
        {
            if (this.primeiro == null/* &&this.ultimo==null */)
                break;

            if (!i.equals(this.primeiro.getInfo()))
                break;

            if (this.ultimo == this.primeiro)
                this.ultimo = null;

            this.primeiro = this.primeiro.getProx();

            removeu = true;
        }

        if (this.primeiro != null/* &&this.ultimo!=null */) {
            No atual = this.primeiro;

            forever: for (;;) // repete ate break
            {
                if (atual.getProx() == null)
                    break;

                while (i.equals(atual.getProx().getInfo())) {
                    if (this.ultimo == atual.getProx())
                        this.ultimo = atual;

                    atual.setProx(atual.getProx().getProx());

                    removeu = true;

                    if (atual == this.ultimo)
                        break forever;
                }

                atual = atual.getProx();
            }
        }

        if (!removeu)
            throw new Exception("Informacao inexistente");
    }

    public boolean isVazia() {
        return this.primeiro == null/* &&this.ultimo==null */;
    }

    public String toString() {
        String ret = "[";

        No atual = this.primeiro;

        while (atual != null) {
            ret = ret + atual.getInfo();

            if (atual != this.ultimo)
                ret = ret + ",";

            atual = atual.getProx();
        }

        return ret + "]";
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (this.getClass() != obj.getClass())
            return false;

        ListaSimplesDesordenada<X> lista = (ListaSimplesDesordenada<X>) obj;

        No atualThis = this.primeiro;
        No atualLista = lista.primeiro;

        while (atualThis != null && atualLista != null) {
            if (!atualThis.getInfo().equals(atualLista.getInfo()))
                return false;

            atualThis = atualThis.getProx();
            atualLista = atualLista.getProx();
        }

        if (atualThis != null /* && atualLista==null */)
            return false;

        if (atualLista != null /* && atualThis ==null */)
            return false;

        // atualThis==null && atualLista==null
        return true;
    }

    public int hashCode() {
        final int PRIMO = 13; // qualquer número primo serve

        int ret = 666; // qualquer inteiro positivo serve

        for (No atual = this.primeiro; atual != null; atual = atual.getProx())
            ret = PRIMO * ret + atual.getInfo().hashCode();

        if (ret < 0)
            ret = -ret;

        return ret;
    }

    // construtor de copia
    public ListaSimplesDesordenada(ListaSimplesDesordenada<X> modelo) throws Exception {
        if (modelo == null)
            throw new Exception("Modelo ausente");

        if (modelo.primeiro == null)
            return; // sai do construtor, pq this.primeiro ja é null

        this.primeiro = new No(modelo.primeiro.getInfo());

        No atualDoThis = this.primeiro;
        No atualDoModelo = modelo.primeiro.getProx();

        while (atualDoModelo != null) {
            atualDoThis.setProx(new No(atualDoModelo.getInfo()));
            atualDoThis = atualDoThis.getProx();
            atualDoModelo = atualDoModelo.getProx();
        }

        this.ultimo = atualDoThis;
    }

    public Object clone() {
        ListaSimplesDesordenada<X> ret = null;

        try {
            ret = new ListaSimplesDesordenada(this);
        } catch (Exception erro) {
        } // sei que this NUNCA é null e o contrutor de copia da erro quando seu parametro
          // é null

        return ret;
    }
}
