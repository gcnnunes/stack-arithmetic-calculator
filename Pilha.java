 public class Pilha<T>
 {
	 private static final int MAXIMO = 500;
	 private int maximo;
	 private Object[] p;
	 private int topo;

	public Pilha()
	{
		this(MAXIMO);
	}

	public Pilha(int max)
	{
		maximo = max;
		p = new Object[maximo];
		topo = -1;
	}

	 public int tamanho()
	 {
		 return topo+1;
	 }

	 public boolean estaVazia()
	 {
		 return topo < 0;
	 }

	@SuppressWarnings("unchecked")

	 public T oTopo()
	 {
		 if(estaVazia())
		 	throw new RuntimeException("pilha vazia");
		 return (T)p[topo];
	 }

	 public void empilhar(T o)
	 {
		 if(tamanho() == maximo)
		 	throw new RuntimeException("pilha cheia");
		 topo++;
		 p[topo] = o;
	 }

	 @SuppressWarnings("unchecked")

	 public T desempilhar()
	 {
		 if(estaVazia())
		 	throw new RuntimeException("pilha vazia");
		 T o = (T)p[topo];
		 p[topo] = null;
		 topo--;
		 return o;
	 }


	//public String toString() {
	//return;
	//}

 }