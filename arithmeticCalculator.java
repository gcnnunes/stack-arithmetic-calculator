import java.io.*;
import java.util.*;

public class arithmeticCalculator {

	public static void main(String args[]) {
		Scanner entrada = new Scanner(System.in);
		Vector<Elemento> infixa, posfixa;
		String expressao;
		float resultado;
		System.out.println("\nWelcome to the Arithmetic Expressions Calculator");
		System.out.println("Please, type a expression. For instance, 10+12*(4^2)-6/2");
		System.out.println("Expression:");


		// Le a entrada
		expressao = entrada.nextLine();


		// Decodifica a entrada
		infixa = Elemento.decode(expressao);


		// Transforma a expresao in-fixa em pos-fixa
		posfixa = inFixaToPosFixa(infixa);


		// Imprime a expressao pos-fixa
		Enumeration e = posfixa.elements();
		while(e.hasMoreElements()) {
			System.out.print(e.nextElement() + " ");
		}
		System.out.println();


		// Calcula a expressao pos-fixa
		resultado = calculaPosFixa(posfixa);


		// Imprime o resultado da expressao
		System.out.println(resultado);

	}


	public static boolean verificaOperador(Elemento elem) {
		char t = elem.toString().charAt(0);

		if ((t == '+') || (t == '-') || (t == '*') || (t == '/') || (t == '^') || (t == '(') || (t == ')'))
		{
		 return true;
		}
		return false;
	}

	public static Vector<Elemento> inFixaToPosFixa(Vector<Elemento> infixa) {
		Enumeration<Elemento> e = infixa.elements();
		Vector<Elemento> saida = new Vector<Elemento>();

		Pilha<Elemento> p = new Pilha<Elemento>();
		Elemento lixo;

		while (e.hasMoreElements())  //percorre o vetor enquanto existe elementos
		{
		    Elemento elem = e.nextElement(); //pega o elemento
			if(verificaOperador(elem))  // verifica se o elemento e um operador ou nao
			{

		    	 if((p.estaVazia())||(elem.tipo() == Elemento.TYPE_PARENTESES_OPEN))  //verifica se pode empilhar
			     {
			    	 p.empilhar(elem);
		         }
			     else
			     {
					Elemento topo = p.oTopo();  //pega o elemento do topo da pilha
					if(topo.toString().charAt(0)=='(')
					{
						p.empilhar(elem);
					}
					else
					{

                     	if (elem.tipo() == Elemento.TYPE_PARENTESES_CLOSE)
                     	{
							while(topo.tipo()!=Elemento.TYPE_PARENTESES_OPEN)  //desmpilhar a pilha para achar um parentes
							{
								saida.add(p.desempilhar()); //manda o topo pro vetor posfixa
								topo = p.oTopo();  //pega o novo topo
							}
							lixo = p.desempilhar(); //exclui
						}
						else
						{
						  if(Elemento.calculaProcedencia(elem)>Elemento.calculaProcedencia(topo)) //Compara Procedencia
                           {
							p.empilhar(elem);
						   }
						  else
						  {
							while(!p.estaVazia() && Elemento.calculaProcedencia(elem)<=Elemento.calculaProcedencia(p.oTopo()))  //desempilhar a pilha para achar um operador que tenha procedencia menor que o elemento atual
							{
								saida.add(p.desempilhar());

							}
							p.empilhar(elem);

						  }
						}
					}
			     }
		    }
			else
			{
             saida.add(elem);
			}

		}

		while (!p.estaVazia())
		  {
		   saida.add(p.desempilhar());
	      }

		return saida;
	}

	public static float calculaPosFixa(Vector<Elemento> posfixa)
	{
      Pilha<Elemento> p2 = new Pilha<Elemento>();
      Enumeration<Elemento> e = posfixa.elements();
      float resultado;
      float um;
      float dois;
      while (e.hasMoreElements())  //percorre o vetor enquanto existe elementos
	  		{
		    Elemento elem2 = e.nextElement(); //pega o elemento
		    if (verificaOperador(elem2))
		        {
					um = p2.desempilhar().numero();
					dois = p2.desempilhar().numero();

					if (elem2.tipo() == Elemento.TYPE_PLUS)
					{
						resultado = um + dois;

						String strSoma = String.valueOf(resultado);

						p2.empilhar(new Elemento(strSoma));
					}
					if (elem2.tipo() == Elemento.TYPE_MINUS)
					{
						resultado = dois - um;

						String strSubtrai = String.valueOf(resultado);

						p2.empilhar(new Elemento(strSubtrai));
					}
					if (elem2.tipo() == Elemento.TYPE_TIMES)
					{
						resultado = dois * um;

						String strMult = String.valueOf(resultado);

						p2.empilhar(new Elemento(strMult));
					}
					if (elem2.tipo() == Elemento.TYPE_DIV)
					{
						resultado = dois / um;

						String strDiv = String.valueOf(resultado);

						p2.empilhar(new Elemento(strDiv));
					}
					if (elem2.tipo() == Elemento.TYPE_EXP)
					{
						resultado = (float)Math.pow(dois, um);

						String strExp = String.valueOf(resultado);

						p2.empilhar(new Elemento(strExp));
					}
				}
			else
			    {
					 p2.empilhar(elem2);
				}
		    }
     	  resultado = p2.oTopo().numero();
     	  return resultado;
		}

}