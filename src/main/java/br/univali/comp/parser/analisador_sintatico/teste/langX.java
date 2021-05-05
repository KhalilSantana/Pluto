/* langX.java */
/* Generated By:JavaCC: Do not edit this line. langX.java */
//package parser;
import java.io.*;


public class langX implements langXConstants {
    public static void main(String args[])  throws ParseException {
        langX parser = null;
        String textToAnalyze = "program abc";
        if(args.length == 0){
            System.out.println("Reading from received text!");
            java.io.InputStream targetStream = new java.io.ByteArrayInputStream(textToAnalyze.getBytes());
            parser = new langX(targetStream);
        }
        else if(args.length == 1){
            String filename = args[0];
            try{
                System.out.println("Reading from file " + filename + " ... ");
                parser = new langX(new java.io.FileInputStream(args[0]));
            }
            catch(java.io.FileNotFoundException e){
                System.err.println(filename + " was not found." );
                System.err.println(e);
            }
        }
        else{
            System.out.println("Use:\njava langX < inputFile");
            System.out.println("or java langX inputFile");
        }

        try{
            parser.program();
        }catch (ParseException e){
            System.out.println(e.getMessage());
            //parser.contParseError = 1; //Não existe recuperação de erros
        }
        finally{
            System.out.println("parser.token_source.foundLexError()" + " Lexical Errors found");
            System.out.println("parser.contParserError" + " Syntactic Errors found");
        }
    }

  final public void program() throws ParseException {
    trace_call("program");
    try {

      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case 0:{
        classlist();
        break;
        }
      default:
        jj_la1[0] = jj_gen;
        ;
      }
      jj_consume_token(0);
    } finally {
      trace_return("program");
    }
}

  final public void classlist() throws ParseException {
    trace_call("classlist");
    try {

      jj_consume_token(0);
    } finally {
      trace_return("classlist");
    }
}

  final public void classdecl() throws ParseException {
    trace_call("classdecl");
    try {

      jj_consume_token(CLASS);
      jj_consume_token(IDENT);
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case EXTENDS:{
        jj_consume_token(EXTENDS);
        jj_consume_token(IDENT);
        break;
        }
      default:
        jj_la1[1] = jj_gen;
        ;
      }
      classbody();
    } finally {
      trace_return("classdecl");
    }
}

  final public void classbody() throws ParseException {
    trace_call("classbody");
    try {

      jj_consume_token(LBRACE);
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case 0:{
        classlist();
        break;
        }
      default:
        jj_la1[2] = jj_gen;
        ;
      }
      label_1:
      while (true) {
        if (jj_2_1(3)) {
          ;
        } else {
          break label_1;
        }
        vardecl();
        jj_consume_token(SEMICOLON);
      }
      label_2:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case CONSTRUCTOR:{
          ;
          break;
          }
        default:
          jj_la1[3] = jj_gen;
          break label_2;
        }
        constructdecl();
      }
      label_3:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case INT:
        case STRING:
        case IDENT:{
          ;
          break;
          }
        default:
          jj_la1[4] = jj_gen;
          break label_3;
        }
        methoddecl();
      }
      jj_consume_token(RBRACE);
    } finally {
      trace_return("classbody");
    }
}

  final public void vardecl() throws ParseException {
    trace_call("vardecl");
    try {

      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case INT:{
        jj_consume_token(INT);
        break;
        }
      case STRING:{
        jj_consume_token(STRING);
        break;
        }
      case IDENT:{
        jj_consume_token(IDENT);
        break;
        }
      default:
        jj_la1[5] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      jj_consume_token(IDENT);
      label_4:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case LBRACKET:{
          ;
          break;
          }
        default:
          jj_la1[6] = jj_gen;
          break label_4;
        }
        jj_consume_token(LBRACKET);
        jj_consume_token(RBRACKET);
      }
      label_5:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case COMMA:{
          ;
          break;
          }
        default:
          jj_la1[7] = jj_gen;
          break label_5;
        }
        jj_consume_token(COMMA);
        jj_consume_token(IDENT);
        label_6:
        while (true) {
          switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
          case LBRACKET:{
            ;
            break;
            }
          default:
            jj_la1[8] = jj_gen;
            break label_6;
          }
          jj_consume_token(LBRACKET);
          jj_consume_token(RBRACKET);
        }
      }
    } finally {
      trace_return("vardecl");
    }
}

  final public void constructdecl() throws ParseException {
    trace_call("constructdecl");
    try {

      jj_consume_token(CONSTRUCTOR);
      methodbody();
    } finally {
      trace_return("constructdecl");
    }
}

  final public void methoddecl() throws ParseException {
    trace_call("methoddecl");
    try {

      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case INT:{
        jj_consume_token(INT);
        break;
        }
      case STRING:{
        jj_consume_token(STRING);
        break;
        }
      case IDENT:{
        jj_consume_token(IDENT);
        break;
        }
      default:
        jj_la1[9] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      label_7:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case RBRACKET:{
          ;
          break;
          }
        default:
          jj_la1[10] = jj_gen;
          break label_7;
        }
        jj_consume_token(RBRACKET);
        jj_consume_token(LBRACKET);
      }
      jj_consume_token(IDENT);
      methodbody();
    } finally {
      trace_return("methoddecl");
    }
}

  final public void methodbody() throws ParseException {
    trace_call("methodbody");
    try {

      jj_consume_token(LPAREN);
      paramlist();
      jj_consume_token(RPAREN);
      statement();
    } finally {
      trace_return("methodbody");
    }
}

  final public void paramlist() throws ParseException {
    trace_call("paramlist");
    try {

      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case INT:
      case STRING:
      case IDENT:{
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case INT:{
          jj_consume_token(INT);
          break;
          }
        case STRING:{
          jj_consume_token(STRING);
          break;
          }
        case IDENT:{
          jj_consume_token(IDENT);
          break;
          }
        default:
          jj_la1[11] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
        jj_consume_token(IDENT);
        label_8:
        while (true) {
          switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
          case RBRACKET:{
            ;
            break;
            }
          default:
            jj_la1[12] = jj_gen;
            break label_8;
          }
          jj_consume_token(RBRACKET);
          jj_consume_token(LBRACKET);
        }
        label_9:
        while (true) {
          switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
          case COMMA:{
            ;
            break;
            }
          default:
            jj_la1[13] = jj_gen;
            break label_9;
          }
          jj_consume_token(COMMA);
          switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
          case INT:{
            jj_consume_token(INT);
            break;
            }
          case STRING:{
            jj_consume_token(STRING);
            break;
            }
          case IDENT:{
            jj_consume_token(IDENT);
            break;
            }
          default:
            jj_la1[14] = jj_gen;
            jj_consume_token(-1);
            throw new ParseException();
          }
          jj_consume_token(IDENT);
          label_10:
          while (true) {
            switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
            case RBRACKET:{
              ;
              break;
              }
            default:
              jj_la1[15] = jj_gen;
              break label_10;
            }
            jj_consume_token(RBRACKET);
            jj_consume_token(LBRACKET);
          }
        }
        break;
        }
      default:
        jj_la1[16] = jj_gen;
        ;
      }
    } finally {
      trace_return("paramlist");
    }
}

  final public void statement() throws ParseException {
    trace_call("statement");
    try {

      if (jj_2_2(2)) {

      } else {
        jj_consume_token(-1);
        throw new ParseException();
      }
      vardecl();
    } finally {
      trace_return("statement");
    }
}

  private boolean jj_2_1(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return (!jj_3_1()); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(0, xla); }
  }

  private boolean jj_2_2(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return (!jj_3_2()); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(1, xla); }
  }

  private boolean jj_3R_vardecl_90_6_13()
 {
    if (jj_scan_token(COMMA)) return true;
    return false;
  }

  private boolean jj_3R_vardecl_88_5_11()
 {
    if (!jj_rescan) trace_call("vardecl(LOOKING AHEAD...)");
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(2)) {
    jj_scanpos = xsp;
    if (jj_scan_token(3)) {
    jj_scanpos = xsp;
    if (jj_scan_token(4)) { if (!jj_rescan) trace_return("vardecl(LOOKAHEAD FAILED)"); return true; }
    }
    }
    if (jj_scan_token(IDENT)) { if (!jj_rescan) trace_return("vardecl(LOOKAHEAD FAILED)"); return true; }
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_vardecl_89_14_12()) { jj_scanpos = xsp; break; }
    }
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_vardecl_90_6_13()) { jj_scanpos = xsp; break; }
    }
    { if (!jj_rescan) trace_return("vardecl(LOOKAHEAD SUCCEEDED)"); return false; }
  }

  private boolean jj_3R_vardecl_89_14_12()
 {
    if (jj_scan_token(LBRACKET)) return true;
    return false;
  }

  private boolean jj_3_2()
 {
    if (jj_scan_token(0)) return true;
    return false;
  }

  private boolean jj_3_1()
 {
    if (jj_3R_vardecl_88_5_11()) return true;
    if (jj_scan_token(SEMICOLON)) return true;
    return false;
  }

  /** Generated Token Manager. */
  public langXTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private Token jj_scanpos, jj_lastpos;
  private int jj_la;
  private int jj_gen;
  final private int[] jj_la1 = new int[17];
  static private int[] jj_la1_0;
  static {
	   jj_la1_init_0();
	}
	private static void jj_la1_init_0() {
	   jj_la1_0 = new int[] {0x1,0x4000,0x1,0x400,0x1c,0x1c,0x40,0x80,0x40,0x1c,0x20,0x1c,0x20,0x80,0x1c,0x20,0x1c,};
	}
  final private JJCalls[] jj_2_rtns = new JJCalls[2];
  private boolean jj_rescan = false;
  private int jj_gc = 0;

  {
      enable_tracing();
  }
  /** Constructor with InputStream. */
  public langX(java.io.InputStream stream) {
	  this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public langX(java.io.InputStream stream, String encoding) {
	 try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
	 token_source = new langXTokenManager(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 17; i++) jj_la1[i] = -1;
	 for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
	  ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
	 try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
	 token_source.ReInit(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 17; i++) jj_la1[i] = -1;
	 for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor. */
  public langX(java.io.Reader stream) {
	 jj_input_stream = new SimpleCharStream(stream, 1, 1);
	 token_source = new langXTokenManager(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 17; i++) jj_la1[i] = -1;
	 for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
	if (jj_input_stream == null) {
	   jj_input_stream = new SimpleCharStream(stream, 1, 1);
	} else {
	   jj_input_stream.ReInit(stream, 1, 1);
	}
	if (token_source == null) {
 token_source = new langXTokenManager(jj_input_stream);
	}

	 token_source.ReInit(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 17; i++) jj_la1[i] = -1;
	 for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor with generated Token Manager. */
  public langX(langXTokenManager tm) {
	 token_source = tm;
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 17; i++) jj_la1[i] = -1;
	 for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(langXTokenManager tm) {
	 token_source = tm;
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 17; i++) jj_la1[i] = -1;
	 for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  private Token jj_consume_token(int kind) throws ParseException {
	 Token oldToken;
	 if ((oldToken = token).next != null) token = token.next;
	 else token = token.next = token_source.getNextToken();
	 jj_ntk = -1;
	 if (token.kind == kind) {
	   jj_gen++;
	   if (++jj_gc > 100) {
		 jj_gc = 0;
		 for (int i = 0; i < jj_2_rtns.length; i++) {
		   JJCalls c = jj_2_rtns[i];
		   while (c != null) {
			 if (c.gen < jj_gen) c.first = null;
			 c = c.next;
		   }
		 }
	   }
	   trace_token(token, "");
	   return token;
	 }
	 token = oldToken;
	 jj_kind = kind;
	 throw generateParseException();
  }

  @SuppressWarnings("serial")
  static private final class LookaheadSuccess extends java.lang.Error {
    @Override
    public Throwable fillInStackTrace() {
      return this;
    }
  }
  static private final LookaheadSuccess jj_ls = new LookaheadSuccess();
  private boolean jj_scan_token(int kind) {
	 if (jj_scanpos == jj_lastpos) {
	   jj_la--;
	   if (jj_scanpos.next == null) {
		 jj_lastpos = jj_scanpos = jj_scanpos.next = token_source.getNextToken();
	   } else {
		 jj_lastpos = jj_scanpos = jj_scanpos.next;
	   }
	 } else {
	   jj_scanpos = jj_scanpos.next;
	 }
	 if (jj_rescan) {
	   int i = 0; Token tok = token;
	   while (tok != null && tok != jj_scanpos) { i++; tok = tok.next; }
	   if (tok != null) jj_add_error_token(kind, i);
	 } else {
	   trace_scan(jj_scanpos, kind);
	 }
	 if (jj_scanpos.kind != kind) return true;
	 if (jj_la == 0 && jj_scanpos == jj_lastpos) throw jj_ls;
	 return false;
  }


/** Get the next Token. */
  final public Token getNextToken() {
	 if (token.next != null) token = token.next;
	 else token = token.next = token_source.getNextToken();
	 jj_ntk = -1;
	 jj_gen++;
	   trace_token(token, " (in getNextToken)");
	 return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
	 Token t = token;
	 for (int i = 0; i < index; i++) {
	   if (t.next != null) t = t.next;
	   else t = t.next = token_source.getNextToken();
	 }
	 return t;
  }

  private int jj_ntk_f() {
	 if ((jj_nt=token.next) == null)
	   return (jj_ntk = (token.next=token_source.getNextToken()).kind);
	 else
	   return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;
  private int[] jj_lasttokens = new int[100];
  private int jj_endpos;

  private void jj_add_error_token(int kind, int pos) {
	 if (pos >= 100) {
		return;
	 }

	 if (pos == jj_endpos + 1) {
	   jj_lasttokens[jj_endpos++] = kind;
	 } else if (jj_endpos != 0) {
	   jj_expentry = new int[jj_endpos];

	   for (int i = 0; i < jj_endpos; i++) {
		 jj_expentry[i] = jj_lasttokens[i];
	   }

	   for (int[] oldentry : jj_expentries) {
		 if (oldentry.length == jj_expentry.length) {
		   boolean isMatched = true;

		   for (int i = 0; i < jj_expentry.length; i++) {
			 if (oldentry[i] != jj_expentry[i]) {
			   isMatched = false;
			   break;
			 }

		   }
		   if (isMatched) {
			 jj_expentries.add(jj_expentry);
			 break;
		   }
		 }
	   }

	   if (pos != 0) {
		 jj_lasttokens[(jj_endpos = pos) - 1] = kind;
	   }
	 }
  }

  /** Generate ParseException. */
  public ParseException generateParseException() {
	 jj_expentries.clear();
	 boolean[] la1tokens = new boolean[15];
	 if (jj_kind >= 0) {
	   la1tokens[jj_kind] = true;
	   jj_kind = -1;
	 }
	 for (int i = 0; i < 17; i++) {
	   if (jj_la1[i] == jj_gen) {
		 for (int j = 0; j < 32; j++) {
		   if ((jj_la1_0[i] & (1<<j)) != 0) {
			 la1tokens[j] = true;
		   }
		 }
	   }
	 }
	 for (int i = 0; i < 15; i++) {
	   if (la1tokens[i]) {
		 jj_expentry = new int[1];
		 jj_expentry[0] = i;
		 jj_expentries.add(jj_expentry);
	   }
	 }
	 jj_endpos = 0;
	 jj_rescan_token();
	 jj_add_error_token(0, 0);
	 int[][] exptokseq = new int[jj_expentries.size()][];
	 for (int i = 0; i < jj_expentries.size(); i++) {
	   exptokseq[i] = jj_expentries.get(i);
	 }
	 return new ParseException(token, exptokseq, tokenImage);
  }

  private boolean trace_enabled;

/** Trace enabled. */
  final public boolean trace_enabled() {
	 return trace_enabled;
  }

  private int trace_indent = 0;
/** Enable tracing. */
  final public void enable_tracing() {
	 trace_enabled = true;
  }

/** Disable tracing. */
  final public void disable_tracing() {
	 trace_enabled = false;
  }

  protected void trace_call(String s) {
	 if (trace_enabled) {
	   for (int i = 0; i < trace_indent; i++) { System.out.print(" "); }
	   System.out.println("Call:	" + s);
	 }
	 trace_indent = trace_indent + 2;
  }

  protected void trace_return(String s) {
	 trace_indent = trace_indent - 2;
	 if (trace_enabled) {
	   for (int i = 0; i < trace_indent; i++) { System.out.print(" "); }
	   System.out.println("Return: " + s);
	 }
  }

  protected void trace_token(Token t, String where) {
	 if (trace_enabled) {
	   for (int i = 0; i < trace_indent; i++) { System.out.print(" "); }
	   System.out.print("Consumed token: <" + tokenImage[t.kind]);
	   if (t.kind != 0 && !tokenImage[t.kind].equals("\"" + t.image + "\"")) {
		 System.out.print(": \"" + TokenMgrError.addEscapes(t.image) + "\"");
	   }
	   System.out.println(" at line " + t.beginLine + " column " + t.beginColumn + ">" + where);
	 }
  }

  protected void trace_scan(Token t1, int t2) {
	 if (trace_enabled) {
	   for (int i = 0; i < trace_indent; i++) { System.out.print(" "); }
	   System.out.print("Visited token: <" + tokenImage[t1.kind]);
	   if (t1.kind != 0 && !tokenImage[t1.kind].equals("\"" + t1.image + "\"")) {
		 System.out.print(": \"" + TokenMgrError.addEscapes(t1.image) + "\"");
	   }
	   System.out.println(" at line " + t1.beginLine + " column " + t1.beginColumn + ">; Expected token: <" + tokenImage[t2] + ">");
	 }
  }

  private void jj_rescan_token() {
	 jj_rescan = true;
	 for (int i = 0; i < 2; i++) {
	   try {
		 JJCalls p = jj_2_rtns[i];

		 do {
		   if (p.gen > jj_gen) {
			 jj_la = p.arg; jj_lastpos = jj_scanpos = p.first;
			 switch (i) {
			   case 0: jj_3_1(); break;
			   case 1: jj_3_2(); break;
			 }
		   }
		   p = p.next;
		 } while (p != null);

		 } catch(LookaheadSuccess ls) { }
	 }
	 jj_rescan = false;
  }

  private void jj_save(int index, int xla) {
	 JJCalls p = jj_2_rtns[index];
	 while (p.gen > jj_gen) {
	   if (p.next == null) { p = p.next = new JJCalls(); break; }
	   p = p.next;
	 }

	 p.gen = jj_gen + xla - jj_la; 
	 p.first = token;
	 p.arg = xla;
  }

  static final class JJCalls {
	 int gen;
	 Token first;
	 int arg;
	 JJCalls next;
  }

      //main
}
