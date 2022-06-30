import java.util.*;

public class Elemento {
	public static final int TYPE_NUMBER = 0;
	public static final int TYPE_PLUS   = 1;
	public static final int TYPE_MINUS  = 2;
	public static final int TYPE_TIMES  = 3;
	public static final int TYPE_DIV    = 4;
	public static final int TYPE_EXP    = 5;
	public static final int TYPE_PARENTESES_OPEN = 6;
	public static final int TYPE_PARENTESES_CLOSE = 7;

	/**
	 * decode
	 * params
	 *		String s: recebe a string com a expressao
	 * return
	 * 		Vetor de elementos (numeros, ou sinais)
	 */
	public static Vector<Elemento> decode(String s) {
		Vector<Elemento> v = new Vector<Elemento>(0,1);
		String ss = "";
		for(int i=0; i<s.length(); i++) {
			switch(s.charAt(i)) {
				case '+': case '-': case '*': case '/': case '^': case '(': case ')':
					if(ss.length() > 0) {
						v.add(new Elemento(ss));
					}
					v.add(new Elemento(""+s.charAt(i)));
					ss = "";
					break;
				case '0': case '1': case '2': case '3': case '4': case '5': case '6': case '7': case '8': case '9': case '.':
					ss = ss + s.charAt(i);
					break;
				case ',':
					ss = ss + '.';
					break;
			}
		}
		if(ss.length() > 0) {
			v.add(new Elemento(ss));
		}
		return v;
	}

	private int type;
	private float number;

	public Elemento(String s) {
		if(s.equals("+"))      { this.type = TYPE_PLUS; }
		else if(s.equals("-")) { this.type = TYPE_MINUS; }
		else if(s.equals("*")) { this.type = TYPE_TIMES; }
		else if(s.equals("/")) { this.type = TYPE_DIV; }
		else if(s.equals("^")) { this.type = TYPE_EXP; }
		else if(s.equals("(")) { this.type = TYPE_PARENTESES_OPEN; }
		else if(s.equals(")")) { this.type = TYPE_PARENTESES_CLOSE; }
		else {
			this.type = TYPE_NUMBER;
			this.number = Float.parseFloat(s);
		}
	}

	public String toString() {
		switch(this.type) {
			case TYPE_NUMBER: return String.valueOf(this.number);
			case TYPE_PLUS: return "+";
			case TYPE_MINUS: return "-";
			case TYPE_TIMES: return "*";
			case TYPE_DIV: return "/";
			case TYPE_EXP: return "^";
			case TYPE_PARENTESES_OPEN: return "(";
			case TYPE_PARENTESES_CLOSE: return ")";
		}
		return "";
	}

	public int tipo()
	{
		return this.type;
	}

	public float numero()
	{
		return this.number;
	}

	public static int calculaProcedencia(Elemento e)
	{

	 if((e.type == TYPE_PLUS)||(e.type == TYPE_MINUS))
	  {
		  return 1;
      }
     if ((e.type == TYPE_TIMES)||(e.type == TYPE_DIV))
      {
	      return 2;
	  }
     if (e.type == TYPE_EXP)
      {
		  return 3;
	  }

	  return -1;
    }
}

